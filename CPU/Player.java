package CPU;

import KERNEL.*;

public class Player
{
	private int m_vie;
	private int m_id;
	private int m_equipe;
	private int m_points;
	private boolean m_ordi;
	private boolean m_carniv;
	private String m_nom;
	
	//Create
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
	public boolean isCarniv() {return m_carniv;}
	
	public void setVie(int i) {m_vie = i;}
	public void setID(int i) {m_id = i;}
	public void setEquipe(int i) {m_equipe = i;}
	public void setPoints(int i) {m_points = i;}
	public void setNom(String s) {m_nom = s;}
	public void setOrdi(boolean b) {m_ordi = b;}
	public void setCarniv(boolean b) {m_carniv = b;}
	
	//
}
