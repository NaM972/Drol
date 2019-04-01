package CPU;

import java.util.ArrayList;

import IO.*;
import KERNEL.*;

public class GameManager extends Thread
{
	//Variable
	private boolean m_quit;
	
	private boolean m_inGame;
	private int m_msTime;
	private ArrayList<GameObject> e;
	private ArrayList<Hero> m_heroes;
	private ArrayList<Player> m_players;
	private int m_mainPlayer;
	
	
	//Bus
	private InputManager m_im;
	private OutputManager m_om;
	
	//Create
	public GameManager(String name)
	{
		//Init
		super(name);
		m_quit = false;
		e = new ArrayList<GameObject>();
		m_inGame = false;

		
		m_mainPlayer = -1;
	}
	
	//Run
	public void run()
	{
		//Tant qu'on ne quitte pas et que les "bus" sont Ã©tablis
		while(!m_quit && m_im != null && m_om != null)
		{
			//Lire les entréees
			if(m_im.areDataSent() )
				readData(m_im.getData() );
				
			//Une partie a commencé
			if(this.m_inGame)
			{
				for(GameObject go : this.e)
					go.update();
				//Temps écoulé
				m_msTime -= Const.T_SLEEP;
				if(m_msTime == 0)
					endGame();
			}
			
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
		//
	}
	
	//Read Data et applique
	private synchronized void readData(ArrayList<Data> data)
	{
		//Toutes les données
		Data nData = null;
		for(Data d : data)
		{					
			//Opération
			if(d.getType() == Const.DT_SINGLE)
				nData = new Data(Const.DT_CHANGENAME,0,0,d.getS());
			if(d.getType() == Const.DT_SERVER)
			{
				m_im.createServer(Const.S_MAXPLAYER);
				nData = new Data(Const.DT_CHANGENAME,0,0,d.getS());
			}
			if(d.getType() == Const.DT_CLIENT)
			{
				String[] nameIp = d.getS().split(Const.DT_SEPAR);
				
				m_im.createClient(nameIp[1]);
				nData = new Data(Const.DT_CHANGENAME,0,0,nameIp[0]);
			}
			//Toutes les opérations lié au OutputManager (Chat, ChangeName)
			if(d.getType() == Const.DT_CHAT || d.getType() == Const.DT_CHANGENAME || d.getType() == Const.DT_CARNIV || d.getType() == Const.DT_EQUIPE || d.getType() == Const.DT_JOUEUR || d.getType() == Const.DT_TIME || d.getType() == Const.DT_PLAY)
			{
				//System.out.println("ac " + d.getS() );
				m_om.applyChange(d);
			}
			//Définir le joueur principal
			if(d.getType() == Const.DT_MAINPLAYER && m_mainPlayer == -1)
				m_mainPlayer = d.getPlayer();
			
			//Key
			if(d.getType() == Const.DT_KEY)
			{
				//Get frog
				Hero hero = null;
				for(Hero h : m_heroes)
				{
					if(h.getId() == d.getPlayer() )
						hero = h;
				}
				//Si on a la grenouille
				if(hero != null)
				{
					if(d.getOperation() == Const.H_UP)
						hero.updateMove(Const.UP);
					if(d.getOperation() == Const.H_DOWN)
						hero.updateMove(Const.DOWN);
					if(d.getOperation() == Const.H_LEFT)
						hero.updateMove(Const.LEFT);
					if(d.getOperation() == Const.H_RIGHT)
						hero.updateMove(Const.RIGHT);
					if(d.getOperation() == Const.H_BULLET)
						e.add(hero.Bullet());
				}
				//
			}
		}
		
		//Nettoie Data, envoie la nouvelle donnée, et réveille Input Manager
		data.clear();
		
		m_im.reveille();
		
		//S'il y a une donnée a envoyé, on l'envoie
		if(nData != null)
			m_im.sendData(nData);
		
	}
	
	//Set Bus
	public synchronized void setBus(InputManager im, OutputManager om)
	{
		m_im = im;
		m_om = om;
	}
	
	//Quit
	public synchronized void doQuit()
	{
		m_quit = true;
	}
	
	//Get GameObject
	public synchronized ArrayList<GameObject> getGameObjects()
	{
		return this.e;
	}
	
	//Configure Game
	public synchronized void configureGame(ArrayList<Data> data)
	{
		//Créer la scene
		generateScene();
		
		//Ordonnancement des données : Temps, (Player, Equipe, Carniv)
		m_players = new ArrayList<Player>();
		m_heroes = new ArrayList<Hero>();
		
		//Donnée pour définir aux clients leur joueur respectif
		ArrayList<Data> dJR = new ArrayList<Data>();
		 
		//Temps
		if(data.get(0).getType() == Const.DT_TIME)
			m_msTime = data.get(0).getOperation() * 1000;
		
		//Joueurs
		for(int i = 1; i < data.size(); i += 3)
		{
			if(data.get(i).getType() == Const.DT_CHANGENAME || (data.get(i).getType() == Const.DT_JOUEUR && data.get(i).getOperation() == Const.DTOP_COMPU) )
			{
				Player p = new Player();
				
				//Id
				p.setID(data.get(i).getPlayer() );
				
				//Joueur humain ou ordi
				if(data.get(i).getType() == Const.DT_CHANGENAME)
				{
					p.setNom(data.get(i).getS() );
					p.setOrdi(false);
				}
				else
				{
					p.setNom(Const.D_PCOMPU + " " + p.getID());
					p.setOrdi(true);
				}
				
				//Equipe
				p.setEquipe(data.get(i+1).getOperation() );
				
				//Carnivore ?
				if(data.get(i+2).getOperation() == Const.DTOP_CARNIV)
					p.setCarniv(true);
				else
					p.setCarniv(false);
				
				//On l'ajoute à la liste des joueurs
				m_players.add(p);
				
				//On ajoute une donnée pour le joueur crée
				dJR.add(new Data(Const.DT_MAINPLAYER,p.getID(),0,"") );
				
				//Créer les héros
				Hero h = null;
				if(!p.isCarniv())
					h = new Hero(100 + (p.getID() * 60),Const.Hero_START_Y,p.getID(),p.isCarniv(),this);
				else
					h = new Hero(100 + (p.getID() * 60),Const.Hero_START_Y - (40*7),p.getID(),p.isCarniv(),this);
				e.add(h);
				m_heroes.add(h);
				
				//TODO test
				//System.out.println("PLAYER : " + p.getID() + " " + p.getNom() + " " + p.getEquipe() + " " + p.isCarniv() + " " + p.isOrdi());
			}
		}
				
		for(Data d : dJR)
			m_im.addNextData(d);
			
		m_inGame = true;
	}
	
	//Get Players
	public synchronized ArrayList<Player> getPlayers()
	{
		return m_players;
	}
	
	//Get Main Player Id
	public synchronized int getMainPlayerID()
	{
		return m_mainPlayer;
	}
	
	//Get Time
	public synchronized int getTime()
	{
		return m_msTime / 1000;
	}
	
	//End Game
	private void endGame()
	{
		this.m_inGame = false;
	}
	
	//Generer la scène
	private void generateScene()
	{
		//e.clear();
		//Plateformes
			//Floor 0
e.add(new Plateforme(Const.Origine, Const.Floor0_y, Const.TypePlat0));
e.add(new Colonne(Const.Colonne1_x, Const.Floor0_y-Const.Col_H));
e.add(new Colonne(Const.Colonne2_x, Const.Floor0_y-Const.Col_H));
e.add(new Colonne(Const.Colonne3_x, Const.Floor0_y-Const.Col_H));
e.add(new Colonne(Const.Colonne4_x, Const.Floor0_y-Const.Col_H));
e.add(new Colonne(Const.Colonne5_x, Const.Floor0_y-Const.Col_H));
//Floor 1
e.add(new Plateforme(Const.Origine, Const.Floor1_y, Const.TypePlat1));
e.add(new TrapDoor(Const.Trp1_x, Const.Floor1_y));
e.add(new Plateforme(Const.Plat2_x, Const.Floor1_y, Const.TypePlat2));
e.add(new TrapDoor(Const.Trp2_x, Const.Floor1_y));
e.add(new Plateforme(Const.Plat3_x, Const.Floor1_y, Const.TypePlat3));
e.add(new TrapDoor(Const.Trp3_x, Const.Floor1_y));
e.add(new Plateforme(Const.Plat4_x, Const.Floor1_y, Const.TypePlat1));
e.add(new Colonne(Const.Colonne1_x, Const.Floor1_y-Const.Col_H));
e.add(new Colonne(Const.Colonne2_x, Const.Floor1_y-Const.Col_H));
e.add(new Colonne(Const.Colonne3_x, Const.Floor1_y-Const.Col_H));
e.add(new Colonne(Const.Colonne4_x, Const.Floor1_y-Const.Col_H));
e.add(new Colonne(Const.Colonne5_x, Const.Floor1_y-Const.Col_H));
//Floor 2
e.add(new Plateforme(Const.Origine, Const.Floor2_y, Const.TypePlat4));
e.add(new Colonne(Const.Colonne1_x, Const.Floor2_y-Const.Col_H));
e.add(new TrapDoor(Const.Trp4_x, Const.Floor2_y));
e.add(new Plateforme(Const.Plat6_x, Const.Floor2_y, Const.TypePlat5));
e.add(new Colonne(Const.Colonne2_x, Const.Floor2_y-Const.Col_H));
e.add(new TrapDoor(Const.Trp5_x, Const.Floor2_y));
e.add(new Plateforme(Const.Plat7_x, Const.Floor2_y, Const.TypePlat6));
e.add(new Colonne(Const.Colonne3_x, Const.Floor2_y-Const.Col_H));
e.add(new TrapDoor(Const.Trp6_x, Const.Floor2_y));
e.add(new Colonne(Const.Colonne4_x, Const.Floor2_y-Const.Col_H));
e.add(new Plateforme(Const.Plat8_x, Const.Floor2_y, Const.TypePlat4));
e.add(new Colonne(Const.Colonne5_x, Const.Floor2_y-Const.Col_H));
//Floor 3
e.add(new Plateforme(Const.Origine, Const.Floor3_y, Const.TypePlat1));
e.add(new TrapDoor(Const.Trp1_x, Const.Floor3_y));
e.add(new Plateforme(Const.Plat2_x, Const.Floor3_y, Const.TypePlat2));
e.add(new TrapDoor(Const.Trp2_x, Const.Floor3_y));
e.add(new Plateforme(Const.Plat3_x, Const.Floor3_y, Const.TypePlat3));
e.add(new TrapDoor(Const.Trp3_x, Const.Floor3_y));
e.add(new Plateforme(Const.Plat4_x, Const.Floor3_y, Const.TypePlat1));	
	}
	
	//Set Main Player
	public synchronized void setMainPlayer(int i)
	{
		this.m_mainPlayer = i;
	}
	
	public void setScene(ArrayList<GameObject> e) {
		this.e = e;
	}
	//
}
