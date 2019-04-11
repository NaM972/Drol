package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import KERNEL.*;

/*
 * Classe client fait partie des classes permettant un multijoueur.
 * Chaque client possede une instance.
 */
public class Client{
	//Variable
	private Socket m_socket;
	private BufferedReader m_in; //Entree Client <--- Serveur
	private PrintWriter m_out; //Sortie Client ---> Serveur
	
	//Constructeur qui cree le client
	public Client(String ip){
		//Connection au serveur
		try{
			//System.out.println("Tentative de creation de Client");
			
			m_socket = new Socket(ip,Const.S_PORT);
			
			m_in = new BufferedReader(new InputStreamReader(m_socket.getInputStream() ) );
			m_out = new PrintWriter(m_socket.getOutputStream() );
			
			//System.out.println("Client connecte a  l'ip " + ip + " au port " + Const.S_PORT);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//Close
	public void close(){
		try{
			m_in.close();
			m_out.close();
			m_socket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Send
	public void send(ArrayList<Data> datas){
		String txt = Data.convertListToString(datas);
		m_out.println(txt);
		m_out.flush();
	}
	
	//Read
	public ArrayList<Data> read(){
		ArrayList<Data> res = new ArrayList<Data>();
		try {
			String txt = m_in.readLine();
			if(!txt.equals("") )
			{
				System.out.println(txt);
				res = Data.convertStringToDataList(txt);
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
