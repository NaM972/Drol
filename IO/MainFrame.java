package IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import KERNEL.*;
import CPU.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
{
	//Variable
	private OutputManager m_om;
	private TitlePanel m_tp;
	private LobbyPanel m_lp;
	private GamePanel m_gp;
	private KeyListener m_kl;
	
	//Create
	public MainFrame(OutputManager om)
	{
		super(Const.D_TITLE);
		this.setSize(800,800);
		this.setResizable(false);
		this.m_om = om;
		
		//Créer le TitlePanel
		m_tp = new TitlePanel(this);
		this.setContentPane(m_tp);
		this.setVisible(true);
		
		//Ajoute un listener pour la fermeture
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	JFrame frame = (JFrame)e.getSource();
		    	 
		    	//Verification avant fermeture
		        int result = JOptionPane.showConfirmDialog(
		            frame,
		            Const.D_CLOSE_TEXT,
		            Const.D_CLOSE_TITLE,
		            JOptionPane.YES_NO_OPTION);
		        
		        //Fermeture
		        if (result == JOptionPane.YES_OPTION)
		        	frame.dispose();
		    }
		});
		
		//
	}
	
	//Si la frame n'est pas affichage -> on l'a fermée
	public boolean isClosed()
	{
		return !isDisplayable();
	}

	//Button
	public void actionPerformed(ActionEvent e) 
	{
		JButton source = (JButton) e.getSource();
		//System.out.println("MainFrame button pressed : " + source.getName());
		
		//Est bien une donnée
		if(source.getName().startsWith(Const.DT_START) )
		{
			//Convertir en donnée
			Data d = Data.convertStringToDataList(source.getName() ).get(0);
			
			//Créer la game
			if( (d.getType() == Const.DT_CLIENT || d.getType() == Const.DT_SERVER || d.getType() == Const.DT_SINGLE) && (m_lp == null && m_gp == null) )
			{
				//Nom du joueur
				String name = JOptionPane.showInputDialog(this, "Name");
				d.setS(name);
				
				//IP pour le client
				if(d.getType() == Const.DT_CLIENT)
				{
					String ip = JOptionPane.showInputDialog(this, "IP");
					d.setS(d.getS() + Const.DT_SEPAR + ip);
				}
			}
			else if(d.getType() == Const.DT_CLIENT || d.getType() == Const.DT_SERVER || d.getType() == Const.DT_SINGLE)
				d.setType(Const.DT_NULL);
			
			//Carnivore
			if(d.getType() == Const.DT_CARNIV)
			{
				if(d.getOperation() == Const.DTOP_HERBIV)
					d.setOperation(Const.DTOP_CARNIV);
				else
					d.setOperation(Const.DTOP_HERBIV);
			}
			
			//Equipe
			if(d.getType() == Const.DT_EQUIPE)
			{
				d.setOperation(d.getOperation() + 1);
				if(d.getOperation() == Const.S_MAXPLAYER)
					d.setOperation(0);
			}
			//Joueur
			if(d.getType() == Const.DT_JOUEUR)
			{
				if(d.getOperation() == Const.DTOP_HUMAN)
					d.setOperation(Const.DTOP_COMPU);
				else if(d.getOperation() == Const.DTOP_COMPU)
					d.setOperation(Const.DTOP_PNULL);
				else if(d.getOperation() == Const.DTOP_PNULL)
					d.setOperation(Const.DTOP_HUMAN);
			}
			//Temps
			if(d.getType() == Const.DT_TIME)
			{
				if(d.getOperation() == Const.DTOP_2MIN)
					d.setOperation(Const.DTOP_5MIN);
				else if(d.getOperation() == Const.DTOP_5MIN)
					d.setOperation(Const.DTOP_10MIN);
				else if(d.getOperation() == Const.DTOP_10MIN)
					d.setOperation(Const.DTOP_2MIN);
			}
				
			//Des choses � envoyer dans le chat ?
			if(d.getType() == Const.DT_CHAT && m_lp != null)
				d.setS(m_lp.getTextFromUser() );
			
			//Doit aller au lobby
			if(d.getType() == Const.DT_SINGLE || d.getType() == Const.DT_SERVER || d.getType() == Const.DT_CLIENT)
			{
				m_lp = new LobbyPanel(this,Const.S_MAXPLAYER);
				m_lp.setModal(false);
				m_lp.setVisible(true);
			}
			
			//Send
			this.m_om.sendDataToIM(d);
			
			//
		}
	}
	
	//Apply Change
	public void applyChange(Data d)
	{
		//Le lobby existe
		if(m_lp != null)
		{
			//Chat
			if(d.getType() == Const.DT_CHAT)
				m_lp.addTextChat(m_lp.getPlayer(d.getPlayer()) + " : " + d.getS() );
			//Change Name
			if(d.getType() == Const.DT_CHANGENAME)
				m_lp.changePlayerName(d.getPlayer(), d );
			//Carnivore ou Herbivore
			if(d.getType() == Const.DT_CARNIV)
				m_lp.setCarnivore(d.getPlayer(), d);
			//Equipe
			if(d.getType() == Const.DT_EQUIPE)
				m_lp.setEquipe(d.getPlayer(), d);
			//Joueur
			if(d.getType() == Const.DT_JOUEUR)
				m_lp.setPlayer(d.getPlayer(), d);
			//Temps
			if(d.getType() == Const.DT_TIME)
				m_lp.setTime(d);
			//Jouer
			if(d.getType() == Const.DT_PLAY)
			{
				//Action venant du Server ou solo
				if(d.getPlayer() == 0)
				{
					//System.out.println("GAME LAUCHED");
					m_om.launchGame(Data.convertStringToDataList(m_lp.convertLobbyToString()) );
					this.m_lp.dispose();
					this.m_lp = null;
					
					this.m_gp = new GamePanel();
					this.m_gp.addKeyListener(m_kl);
					this.m_gp.setFocusable(true);
				}
			}
		}
		//
	}
	
	//Add Keyboard
	public void addKeyboard(KeyListener kl)
	{
		this.m_kl = kl; 
	}
	
	//Get Lobby As String
	public String getLobbyAsString()
	{
		if(m_lp != null)
			return m_lp.convertLobbyToString();
		return "";
	}
	
	//Update GamePanel
	public void updateGamePanel(ArrayList<GameObject> gos, ArrayList<Player> players, int mainPlayer, int time)
	{
		if(this.m_gp != null)
			this.m_gp.update(gos,players,mainPlayer,time);
	}
	
	//
}
