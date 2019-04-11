package IO;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import CPU.*;
import KERNEL.*;

/*
 * Classe OutputManager qui represente les sorties de donnees entre joueur.
 */
public class OutputManager extends Thread 
{
	//Variable
	private boolean m_quit;
	private MainFrame m_mainFrame;
	
	//Bus
	private InputManager m_im;
	private GameManager m_gm;
	
	//Constructeur
	public OutputManager(String name)
	{
		//Init
		super(name);
		m_quit = false;
		m_mainFrame = new MainFrame(this);
	}
	
	//Run
	public void run()
	{
		//Tant qu'on ne quitte pas et que les "bus" sont établis
		while(!m_quit && m_im != null && m_gm != null)
		{
			//La JFrame est fermée ?
			if(m_mainFrame.isClosed() )
			{
				m_im.doQuit();
				m_gm.doQuit();
				this.doQuit();
			}
			
			//Affiche les gameobjects 
			this.m_mainFrame.updateGamePanel(this.m_gm.getGameObjects(),this.m_gm.getPlayers(), this.m_gm.getMainPlayerID(), this.m_gm.getTime() );
			
			//Attendre
			try 
			{
				sleep(Const.T_SLEEP);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//Set Bus
	public synchronized void setBus(InputManager im, GameManager gm)
	{
		m_im = im;
		m_gm = gm;
	}
	
	//Quit
	public synchronized void doQuit()
	{
		m_quit = true;
	}
	
	//Add KeyListener
	public synchronized void addKeyboard(KeyListener kl)
	{
		this.m_mainFrame.addKeyboard(kl);
	}
	
	//Send Data to Input Manager
	public synchronized void sendDataToIM(Data d)
	{
		m_im.addNextData(d);
	}
	
	//Apply Change
	public synchronized void applyChange(Data d)
	{
		m_mainFrame.applyChange(d);
	}
	
	//Get Lobby as String
	public String getLobbyAsString()
	{
		return m_mainFrame.getLobbyAsString();
	}
	
	//Launch Game
	public void launchGame(ArrayList<Data> data)
	{
		m_gm.configureGame(data);
	}
}
