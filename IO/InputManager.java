package IO;

import java.util.ArrayList;

import CPU.*;
import KERNEL.*;

/*
 * Classe InputManager qui represente les entrees de donnees entre joueur.
 * Donnees representees par la classe keyboard ou autre.
 */
public class InputManager extends Thread
{
	//Variable
	private boolean m_quit;
	private Keyboard m_kb;
	private boolean m_kb_added;
	
	//Bus
	private OutputManager m_om;
	private GameManager m_gm;
	
	//Data
	private ArrayList<Data> m_data;
	private boolean m_dataSent;
	private Client m_client;
	private Server m_server;
	
	private ArrayList<Data> m_nextData;
	
	//Constructeur
	public InputManager(String name){
		//Init
		super(name);
		m_quit = false;
		
		m_kb = new Keyboard();
		m_kb_added = false;
		
		m_data = new ArrayList<Data>();
		m_dataSent = false;
		
		m_nextData = new ArrayList<Data>();
	}
	
	//Run
	public void run(){
		//Tant qu'on ne quitte pas et que les "bus" sont etablis
		while(!m_quit && m_om != null && m_gm != null){			
			//Le KeyListener est ajoute au JPanel de OutputManager/Display
			if(!m_kb_added){
				m_om.addKeyboard(m_kb);
				m_kb_added = true;
			}
			
			//Input selon les bind du clavier
			char lastKey = m_kb.getLastKey();
			if(lastKey == Const.H_UP)
				m_data.add(new Data(Const.DT_KEY,0,Const.H_UP,"") );
			if(lastKey == Const.H_DOWN)
				m_data.add(new Data(Const.DT_KEY,0,Const.H_DOWN,"") );
			if(lastKey == Const.H_LEFT)
				m_data.add(new Data(Const.DT_KEY,0,Const.H_LEFT,"") );
			if(lastKey == Const.H_RIGHT)
				m_data.add(new Data(Const.DT_KEY,0,Const.H_RIGHT,"") );
			if(lastKey == Const.H_BULLET)
				m_data.add(new Data(Const.DT_KEY,0,Const.H_BULLET,""));
				
			//Add Next Data
			for(Data d : m_nextData)	//On parcourt la liste des donnees
				m_data.add(d);
			m_nextData.clear();
			
			//Single
			if(m_server == null && m_client == null)
				sendToGameManager();
			//Server
			else if(m_server != null)
				doServer();
			//Client
			else
				doClient();
			
			//Attendre
			try {
				sleep(Const.T_SLEEP);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			//
		}
		//
	}
	
	//Set Bus
	public synchronized void setBus(OutputManager om, GameManager gm){
		m_om = om;
		m_gm = gm;
	}
	
	//Add Data
	private synchronized void sendToGameManager(){		
		//Attend que G.M. met a† zero data et le reveille
		try {
			m_dataSent = true;

			while(m_dataSent)
				wait();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Reveille
	public synchronized void reveille(){
		m_dataSent = false;
		notifyAll();
	}
	
	//Data Sent
	public synchronized boolean areDataSent(){
		return m_dataSent;
	}
	
	//Get Data
	public synchronized ArrayList<Data> getData(){
		return m_data;
	}
	
	//Server
	@SuppressWarnings("unchecked")
	private synchronized void doServer(){			
		//Ajoute les donn√©es de ce IM, lis celles de tous les clients et envoie le tous au client
		m_server.serverAddData(m_data);
		
		m_server.readDataFromClients();
		
		//S'il y a un nouveau client, on update les Lobby du OM
		if(m_server.areThereNewClient() ){
			m_server.setNewClient(false);
			m_server.sendStringToClients(m_om.getLobbyAsString() );
		}
		
		m_server.sendToClients();

		//Applique les donnees de tous les clients au IM et reinitilise les donnees du serveur
		m_data = (ArrayList<Data>) m_server.getData().clone();
		m_server.clearData();
		
		m_gm.setMainPlayer(0);
		
		sendToGameManager();
	}
	
	//Client
	private synchronized void doClient(){
		m_client.send(m_data);
		
		ArrayList<Data> d = m_client.read();
		m_data = d;
		sendToGameManager();
	}
	
	//Create Server
	public void createServer(int n){
		if(m_server == null && m_client == null)
			m_server = new Server(n);
	}
	
	//Create Client
	public void createClient(String ip){
		if(m_client == null && m_server == null)
			m_client = new Client(ip);
	}
	
	//Quit
	public synchronized void doQuit(){
		m_quit = true;
	}
	
	//Send Data
	public synchronized void sendData(Data d){
		//System.out.println("SEND DATA " + d.getS() );
		m_data.add(d);
	}
	
	//Add Next Data
	public synchronized void addNextData(Data d){
		m_nextData.add(d);
	}
	
	//
}
