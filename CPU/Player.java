package CPU;

import KERNEL.Const;

/*
 * Classe player representant un joueur et chaque jeu possede un player.
 * C est dans cette classe qu est repertorie le score, les vies et les differentes variable
 * d un hero que possede le joueur. En quelque sorte, le joueur possede un hero selon son ID.
 */
public class Player {
	
	private int m_vie;	//Nombre de vie
	private int m_id;	//Numero d identification
	private int m_equipe; //Numero d equipe
	private int m_points; //Nombre de point
	private boolean m_ordi; //Si c est un ordi
	private boolean m_Aggressif; 	//Si c est un ennemie
	private String m_nom;	//Le nom du joueur
	
	//Constructeur du joueur
	public Player()
	{
		m_points = 0;
		m_vie = Const.G_VIE;
	}
	
	//Getter et Setter
	public int getVie() {return m_vie;}
	public int getID() {return m_id;}
	public int getEquipe() {return m_equipe;}
	public int getPoints() {return m_points;}
	public String getNom() {return m_nom;}
	public boolean isOrdi() {return m_ordi;}
	public boolean isAggressif() {return m_Aggressif;}
	
	public void setVie(int i) {m_vie = i;}
	public void setID(int i) {m_id = i;}
	public void setEquipe(int i) {m_equipe = i;}
	public void setPoints(int i) {m_points = i;}
	public void setNom(String s) {m_nom = s;}
	public void setOrdi(boolean b) {m_ordi = b;}
	public void setAggressif(boolean b) {m_Aggressif = b;}
}
