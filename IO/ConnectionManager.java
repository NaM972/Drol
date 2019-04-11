package IO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Classe ConnectionManager fait partie des classes permettant un multijoueur.
 * Cette classe s'occupe de la connection de chaque joueur a un serveur
 */
public class ConnectionManager extends Thread{
	//Variable
	private Server m_server;
	private ServerSocket m_socket;
	private boolean m_run;
	
	//Constructeur
	public ConnectionManager(Server s, ServerSocket so){
		m_server = s;
		m_socket = so;
		m_run = true;
	}
	
	//Run
	public void run(){
		//Tant qu'il est ouvert
		while(m_run){
			try {
				if(m_server.restePlace()){
					Socket socket = m_socket.accept();
					m_server.addClient(socket);
				}
			} 
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	//Close
	public void close(){
		m_run = false;
	}
}
