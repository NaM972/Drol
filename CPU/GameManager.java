package CPU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import IO.*;
import KERNEL.*;

/*
 * Classe GameManager qui gere l'ensemble du jeu.
 * Elle possede une liste de gameobject qui initialise le decor du jeu ainsi que les 
 * entites. Le thread permet de mettre a jour le jeu de chaque joueur.
 */
public class GameManager extends Thread{
	//Variable
	private boolean m_quit;	//Boolean qui indiquera si on a quitte le jeu ou non
	
	private boolean m_inGame;	//Est dans le jeu ou non
	private int m_msTime;		//Temps
	private ArrayList<GameObject> e;	//Collection de gameobjects qui sera envoye a plusieurs classe
	private ArrayList<Hero> m_heroes;	//Collection de heros
	private ArrayList<Player> m_players;	//Collection de joueurs
	private int m_mainPlayer;				//ID du joueur principal
	private List<String> allNames = new ArrayList<>();	//Liste des noms 
	private List<String> allScores = new ArrayList<>();	//et liste des scores afin de mettre en place un scoreboard a chaque fois que l on redemarre le jeu
	
	//Methode permettant d ecrire dans un fichier texte le scoreboard
	public void Scoring() {
		
		try {
			//On recupere le nom des joueurs present dans la collection de joueur puis on place le nom dans la liste allNames
	        for(int i = 0; i<m_players.size(); i++) {
	        	String name = m_players.get(i).getNom();
	        	allNames.add(name);
	    	}	
				
			//On lit les informations presentes dans le fichier names puis on les ajoutes a la liste allNames
			FileReader fReader = new FileReader("SCORE/names.txt");	//Vas chercher le fichier a lire
	        BufferedReader bReader = new BufferedReader(fReader);	//Lis
	        for (int i = 0; i < 5; i++) {
	            if((bReader.readLine())!=null)
	            this.allNames.add(bReader.readLine());	//ajoute par rapport a la ligne lu
	        }
	        bReader.close();	//On ferme le lecteur
	        fReader.close();	//On ferme le lecteur fichier
	            
	        //Apres on vas chercher le fichier dans lequel on vas ecrire tous les noms de allNames
	        FileWriter fileWriter = new FileWriter("SCORE/names.txt");
	        PrintWriter write = new PrintWriter(fileWriter);
	        for (int i = 0; i < allNames.size(); i++) {
	            write.printf("%s%n", this.allNames.get(i));
	        }
	        fileWriter.close();	//On ferme le FileWriter
	        write.close();	//On ferme le PrintWriter
	            
	        //On fait la meme chose pour les scores
	        for(int i = 0; i<m_players.size(); i++) {
	    		int score = m_players.get(i).getPoints();
	    		allScores.add(""+score+"");
	    	}	
	         
	        FileReader fReader2 = new FileReader("SCORE/scores.txt");
	        BufferedReader bReader2 = new BufferedReader(fReader2);
	        for (int i = 0; i < 5; i++) {
	            if((bReader2.readLine())!=null)
	            this.allScores.add(bReader2.readLine());
	        }
	        bReader2.close();
	        fReader2.close();	          

	        FileWriter fileWriter2 = new FileWriter("SCORE/scores.txt");
	        PrintWriter write2 = new PrintWriter(fileWriter2);
	        for (int i = 0; i < allScores.size(); i++) {
	            write2.printf("%s%n", this.allScores.get(i));
	        }
	        fileWriter2.close();
	        write2.close();

	    	} catch (IOException e1) {
	            e1.printStackTrace();
	        }
			
	}
	
	@SuppressWarnings("deprecation")
	public void Loose() {
		for(Player p : m_players){	//On parcourt la liste des joueur afin de voir si un a perdu, si oui, on arrette le jeu
			if(p.getVie() == 0) {
				this.stop();
			}
		}
	}
	
	
	//Bus de connexion
	private InputManager m_im;
	private OutputManager m_om;
	
	//Constructeur par defaut
	public GameManager(String name){
		//Initialisation
		super(name);
		m_quit = false;
		e = new ArrayList<GameObject>();
		m_inGame = false;
		m_mainPlayer = -1;
	}
	
	//Run
	public void run(){
		//Tant qu'on ne quitte pas et que les "bus" sont etablis
		while(!m_quit && m_im != null && m_om != null){
			//Lire les entrees
			if(m_im.areDataSent() )
				readData(m_im.getData() );
				
			//Une partie a commencee
			if(this.m_inGame){
				for(GameObject go : this.e)
					go.update();
				//Temps ecoule
				m_msTime -= Const.T_SLEEP;
				if(m_msTime == 0)
					endGame();
				Loose();
			}
			
			
			//Attendre
			try {
				sleep(Const.T_SLEEP);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Si la la liste des m players est different de null on appel la methode scoring()
		if(m_players!= null) {
		Scoring();
		}
		//
	}
	
	//Read Data et applique
	private synchronized void readData(ArrayList<Data> data){
		//Toutes les donnees
		Data nData = null;
		for(Data d : data){					
			//Operation
			if(d.getType() == Const.DT_SINGLE)
				nData = new Data(Const.DT_CHANGENAME,0,0,d.getS());
			if(d.getType() == Const.DT_SERVER){
				m_im.createServer(Const.S_MAXPLAYER);
				nData = new Data(Const.DT_CHANGENAME,0,0,d.getS());
			}
			if(d.getType() == Const.DT_CLIENT){
				String[] nameIp = d.getS().split(Const.DT_SEPAR);
				
				m_im.createClient(nameIp[1]);
				nData = new Data(Const.DT_CHANGENAME,0,0,nameIp[0]);
			}
			//Toutes les operations liee au OutputManager (Chat, ChangeName)
			if(d.getType() == Const.DT_CHAT || d.getType() == Const.DT_CHANGENAME || d.getType() == Const.DT_AGGRES || d.getType() == Const.DT_EQUIPE || d.getType() == Const.DT_JOUEUR || d.getType() == Const.DT_TIME || d.getType() == Const.DT_PLAY){
				//System.out.println("ac " + d.getS() );
				m_om.applyChange(d);
			}
			//Definir le joueur principal
			if(d.getType() == Const.DT_MAINPLAYER && m_mainPlayer == -1)
				m_mainPlayer = d.getPlayer();
			
			//Key
			if(d.getType() == Const.DT_KEY){
				//Get hero
				Hero hero = null;
				for(Hero h : m_heroes){
					if(h.getID() == d.getPlayer() )
						hero = h;
				}
				//Si on a le hero
				if(hero != null){
					if(d.getOperation() == Const.H_UP)
						{hero.updateMove(Const.UP);}
					if(d.getOperation() == Const.H_DOWN)
						{hero.updateMove(Const.DOWN);}
					if(d.getOperation() == Const.H_LEFT)
						{hero.updateMove(Const.LEFT);}
					if(d.getOperation() == Const.H_RIGHT)
						{hero.updateMove(Const.RIGHT);}
					if(d.getOperation() == Const.H_BULLET)
						{hero.updateMove(Const.BULLET);}

				}
			}
		}
		
		//Nettoie Data, envoie la nouvelle donnee, et reveille InputManager
		data.clear();
		
		m_im.reveille();
		
		//S'il y a une donnee a envoyee, on l'envoie
		if(nData != null)
			m_im.sendData(nData);
		
	}
	
	//Set Bus
	public synchronized void setBus(InputManager im, OutputManager om){
		m_im = im;
		m_om = om;
	}
	
	//Quit
	public synchronized void doQuit(){
		m_quit = true;
	}
	
	//Get GameObject
	public synchronized ArrayList<GameObject> getGameObjects(){
		return this.e;
	}
	
	//Configure Game
	public synchronized void configureGame(ArrayList<Data> data){
		int id = 0;
		//Creer la scene
		generateScene();
		
		//Ordonnancement des donnees : Temps, (Player, Equipe, Carniv)
		m_players = new ArrayList<Player>();
		m_heroes = new ArrayList<Hero>();
		
		//Donnee pour definir aux clients leur joueur respectif
		ArrayList<Data> dJR = new ArrayList<Data>();
		 
		//Temps
		if(data.get(0).getType() == Const.DT_TIME)
			m_msTime = data.get(0).getOperation() * 1000;
		
		//Joueurs
		for(int i = 1; i < data.size(); i += 3){
			if(data.get(i).getType() == Const.DT_CHANGENAME || (data.get(i).getType() == Const.DT_JOUEUR && data.get(i).getOperation() == Const.DTOP_COMPU) ){
				Player p = new Player();
				
				//Id Hero
				p.setID(id);
				id++;
				
				//Id
				p.setID(data.get(i).getPlayer() );
				
				//Joueur humain ou ordi
				if(data.get(i).getType() == Const.DT_CHANGENAME){
					p.setNom(data.get(i).getS() );
					p.setOrdi(false);
				}
				else{
					p.setNom(Const.D_PCOMPU + " " + p.getID());
					p.setOrdi(true);
				}
				
				//Equipe
				p.setEquipe(data.get(i+1).getOperation() );
				
				//AGRESSIF ?
				if(data.get(i+2).getOperation() == Const.DTOP_AGGRES)
					p.setAggressif(true);
				else
					p.setAggressif(false);
				
				//On l'ajoute a la liste des joueurs
				m_players.add(p);
				
				//On ajoute une donn�e pour le joueur cr�e
				dJR.add(new Data(Const.DT_MAINPLAYER,p.getID(),0,"") );
				
				//Creer les heros
				Hero h = null;
				if(!p.isAggressif())
					h = new Hero((p.getID()*60)+Const.Hero_Start_X_Pacifiq,Const.Hero_Start_Y_Pacifiq,p.getID(),p.isAggressif(),this);
				else
					h = new Hero((p.getID()*60)+Const.Hero_Start_X_Aggressive,Const.Hero_Start_Y_Aggressive,p.getID(),p.isAggressif(),this);
				e.add(h);
				m_heroes.add(h);

				//System.out.println("PLAYER : " + p.getID() + " " + p.getNom() + " " + p.getEquipe() + " " + p.isAggressif() + " " + p.isOrdi());
			}
		}
				
		for(Data d : dJR)
			m_im.addNextData(d);
			
		m_inGame = true;
	}
	
	//Get Players
	public synchronized ArrayList<Player> getPlayers(){
		return m_players;
	}
	
	//Get Main Player Id
	public synchronized int getMainPlayerID(){
		return m_mainPlayer;
	}
	
	//Get Time
	public synchronized int getTime(){
		return m_msTime / 1000;
	}
	
	//End Game
	private void endGame(){
		this.m_inGame = false;
	}
	
	//Generer la scene
	private void generateScene(){
		//Plateformes
		
		//Floor 0
		e.add(new Platform(-Const.Size_x_Panel, Const.Floor0_y, Const.TypePlat0));		
		e.add(new Platform(Const.Origine, Const.Floor0_y, Const.TypePlat0));
		e.add(new Column(Const.Colonne1_x-350, Const.Floor0_y-Const.Col_H));
		e.add(new Column(Const.Colonne1_x, Const.Floor0_y-Const.Col_H));
		e.add(new Column(Const.Colonne2_x, Const.Floor0_y-Const.Col_H));
		e.add(new Column(Const.Colonne3_x, Const.Floor0_y-Const.Col_H));
		e.add(new Column(Const.Colonne4_x, Const.Floor0_y-Const.Col_H));
		e.add(new Column(Const.Colonne5_x, Const.Floor0_y-Const.Col_H));
		e.add(new Column(Const.Colonne5_x+350, Const.Floor0_y-Const.Col_H));
		e.add(new Platform(Const.Size_x_Panel, Const.Floor0_y, Const.TypePlat0));
		
		e.add(new Ennemy(1995, Const.Floor0_y-170, Const.LEFT, 2));
		e.add(new Ennemy(-140, Const.Floor0_y-70, Const.RIGHT, 0));
		e.add(new Ennemy(-280, Const.Floor0_y-70, Const.RIGHT, 0));
		
		//Floor 1
		e.add(new Platform(-Const.Size_x_Panel, Const.Floor1_y, Const.TypePlat0));
		e.add(new Platform(Const.Origine, Const.Floor1_y, Const.TypePlat1));
		e.add(new TrapDoor(Const.Trp1_x, Const.Floor1_y));
		e.add(new Platform(Const.Plat2_x, Const.Floor1_y, Const.TypePlat2));
		e.add(new TrapDoor(Const.Trp2_x, Const.Floor1_y));
		e.add(new Platform(Const.Plat3_x, Const.Floor1_y, Const.TypePlat3));
		e.add(new TrapDoor(Const.Trp3_x, Const.Floor1_y));
		e.add(new Platform(Const.Plat4_x, Const.Floor1_y, Const.TypePlat1));
		e.add(new Column(Const.Colonne1_x-350, Const.Floor1_y-Const.Col_H));
		e.add(new Column(Const.Colonne1_x, Const.Floor1_y-Const.Col_H));
		e.add(new Column(Const.Colonne2_x, Const.Floor1_y-Const.Col_H));
		e.add(new Column(Const.Colonne3_x, Const.Floor1_y-Const.Col_H));
		e.add(new Column(Const.Colonne4_x, Const.Floor1_y-Const.Col_H));
		e.add(new Column(Const.Colonne5_x, Const.Floor1_y-Const.Col_H));
		e.add(new Column(Const.Colonne5_x+350, Const.Floor1_y-Const.Col_H));
		e.add(new Platform(Const.Size_x_Panel, Const.Floor1_y, Const.TypePlat0));
		
		e.add(new Ennemy(-140, Const.Floor1_y-170, Const.RIGHT, 2));
		e.add(new Ennemy(2205, Const.Floor1_y-70, Const.LEFT, 1));
		e.add(new Ennemy(2345, Const.Floor1_y-70, Const.LEFT, 1));
		
		//Floor 2
		e.add(new Platform(-Const.Size_x_Panel, Const.Floor2_y, Const.TypePlat0));
		e.add(new Column(Const.Colonne1_x-350, Const.Floor2_y-Const.Col_H));
		e.add(new Platform(Const.Origine, Const.Floor2_y, Const.TypePlat4));
		e.add(new Column(Const.Colonne1_x, Const.Floor2_y-Const.Col_H));
		e.add(new TrapDoor(Const.Trp4_x, Const.Floor2_y));
		e.add(new Platform(Const.Plat6_x, Const.Floor2_y, Const.TypePlat5));
		e.add(new Column(Const.Colonne2_x, Const.Floor2_y-Const.Col_H));
		e.add(new TrapDoor(Const.Trp5_x, Const.Floor2_y));
		e.add(new Platform(Const.Plat7_x, Const.Floor2_y, Const.TypePlat6));
		e.add(new Column(Const.Colonne3_x, Const.Floor2_y-Const.Col_H));
		e.add(new TrapDoor(Const.Trp6_x, Const.Floor2_y));
		e.add(new Column(Const.Colonne4_x, Const.Floor2_y-Const.Col_H));
		e.add(new Platform(Const.Plat8_x, Const.Floor2_y, Const.TypePlat4));
		e.add(new Column(Const.Colonne5_x, Const.Floor2_y-Const.Col_H));
		e.add(new Column(Const.Colonne5_x+350, Const.Floor2_y-Const.Col_H));
		e.add(new Platform(Const.Size_x_Panel, Const.Floor2_y, Const.TypePlat0));
		
		e.add(new Ennemy(2065, Const.Floor2_y-70, Const.LEFT, 0));
		e.add(new Ennemy(2205, Const.Floor2_y-70, Const.LEFT, 0));
		e.add(new Ennemy(-140, Const.Floor2_y-170, Const.RIGHT, 2));
		e.add(new Ennemy(-280, Const.Floor2_y-170, Const.RIGHT, 2));
		
		//Floor 3
		e.add(new Platform(-Const.Size_x_Panel, Const.Floor3_y, Const.TypePlat0));
		e.add(new Platform(Const.Origine, Const.Floor3_y, Const.TypePlat1));
		e.add(new TrapDoor(Const.Trp1_x, Const.Floor3_y));
		e.add(new Platform(Const.Plat2_x, Const.Floor3_y, Const.TypePlat2));
		e.add(new TrapDoor(Const.Trp2_x, Const.Floor3_y));
		e.add(new Platform(Const.Plat3_x, Const.Floor3_y, Const.TypePlat3));
		e.add(new TrapDoor(Const.Trp3_x, Const.Floor3_y));
		e.add(new Platform(Const.Plat4_x, Const.Floor3_y, Const.TypePlat1));	
		e.add(new Platform(Const.Size_x_Panel, Const.Floor3_y, Const.TypePlat0));
		
		e.add(new Ennemy(-70, Const.Floor3_y-170, Const.RIGHT, 2));
		e.add(new Ennemy(2065, Const.Floor3_y-70, Const.LEFT, 0));
		e.add(new Ennemy(2205, Const.Floor3_y-70, Const.LEFT, 0));
		
		e.add(new DoorWin(-800, Const.Floor0_y));
		
	}
	
	//Set Main Player
	public synchronized void setMainPlayer(int i){
		this.m_mainPlayer = i;
	}
	
	public void setScene(ArrayList<GameObject> e) {
		this.e = e;
	}
	
	public synchronized void addGameObject(GameObject o) {
		e.add(o);
	}
}
