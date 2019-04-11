package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import KERNEL.*;

public class Server
{
	//Variable
	private ServerSocket m_server;
	private int m_max;
	private int m_nClient;
	private ConnectionManager m_cm;
	private boolean m_nvClient;
	
	private Socket[] m_clients;
	private BufferedReader[] m_in; //Entr√©e Serveur <--- Clients
	private PrintWriter[] m_out; //Sortie Serveur ---> Clients
	
	private ArrayList<Data> m_dataToSend;
		
	//Create
	public Server(int n)
	{
		//Attend la connection de tous les joueur
		try
		{
			//Cr√©er le serveur
			m_server = new ServerSocket(Const.S_PORT);
			m_max = n;
			m_nClient = 0;
			m_nvClient = false;
			
			//Connection aux clients
			m_clients = new Socket[n];
			m_in = new BufferedReader[n];
			m_out = new PrintWriter[n];
			
			System.out.println("Server crÈe au port " + Const.S_PORT);
			
			//Liste des donn√©es
			m_dataToSend = new ArrayList<Data>();
			
			//Cr√©er le thread qui va g√©rer les connections
			m_cm = new ConnectionManager(this, m_server);
			m_cm.start();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		//
	}
	
	//Add Client
	public void addClient(Socket s) throws IOException
	{
		m_clients[m_nClient] = s;
		m_in[m_nClient] = new BufferedReader( new InputStreamReader(s.getInputStream()) );
		m_out[m_nClient] = new PrintWriter(s.getOutputStream());
		m_nClient++;
		m_nvClient = true;
		System.out.println("Client ajoutÈ");
	}
	
	//Nouveau client
	public void setNewClient(boolean nv)
	{
		m_nvClient = nv;
	}
	
	//? Nouveau Client ?
	public boolean areThereNewClient()
	{
		return m_nvClient;
	}
	
	//Il y a de la place
	public boolean restePlace()
	{
		return m_nClient < m_max;
	}
	
	//Close
	public void close()
	{
		try
		{
			m_cm.close();
			m_server.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Close
	public void closeClient(int i)
	{
		try
		{
			m_clients[i].close();
			m_in[i].close();
			m_out[i].close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Close Active Client
	public void closeActiveClients()
	{
		for(int i = 0; i < m_clients.length; i++)
		{
			//Deconnecte le client s'il est encore connect√©
			if(m_clients[i].isConnected() )
				closeClient(i);
		}
	}
	
	//Add Data (liste)
	public void addData(ArrayList<Data> datas)
	{
		for(Data d : datas)
		{
			//VÈrifie que le type de donnÈe est autorisÈ
			if(authorizedType(d.getType() ) )
				m_dataToSend.add(d);
			//Si l'on doit changer le joueur principal
			if(d.getType() == Const.DT_MAINPLAYER)
			{
				System.out.println("BJR " + d.getPlayer());
				if(d.getPlayer() > 0 && d.getPlayer() <= m_out.length)
					m_out[d.getPlayer() - 1].print(d.convertToString() );
			}
			
		}
	}
	
	//Le serveur add Data (liste)
	public void serverAddData(ArrayList<Data> datas)
	{
		for(Data d : datas)
		{
			m_dataToSend.add(d);
		}
	}
	
	//Clear
	public void clearData()
	{
		m_dataToSend.clear();
	}
	
	//Get Data from Clients
	public void readDataFromClients()
	{
		try 
		{
			for(int i = 0; i < m_nClient; i++)
			{
				//Pour tous les clients, on ajoute les donn√©es lu √† la liste
				if(m_clients[i] != null && m_clients[i].isConnected() )
				{
					String txt = m_in[i].readLine();
					if(!txt.equals(""))
					{
						System.out.println(txt);
						ArrayList<Data> datas = Data.convertStringToDataList(txt);
						for(Data d : datas)
							d.setPlayer(i + 1);
						addData(datas);
					}
				} 
			}
			//
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Send to all clients
	public void sendToClients()
	{
		String txt = Data.convertListToString(m_dataToSend);
		sendStringToClients(txt);
	}
	
	//Send String to Clients
	public void sendStringToClients(String s)
	{
		for(int i = 0; i < m_nClient; i++)
		{
			//Pour tous les clients, on Ècrit les donnÈes
			if(m_clients[i] != null && m_clients[i].isConnected() )
			{
				m_out[i].println(s);
				m_out[i].flush();
			}
			//
		}
	}
	
	//Get Data
	public ArrayList<Data> getData()
	{
		return m_dataToSend;
	}
	
	//Type de donn√©e autoris√© pour client
	private boolean authorizedType(int type)
	{
		return type != Const.DT_JOUEUR && type != Const.DT_MAINPLAYER;
	}
	
	//
}
