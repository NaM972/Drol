package IO;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;

import CPU.*;
import IO.*;
import KERNEL.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	//Variable
	private ArrayList<GameObject> m_gos;
	private ArrayList<Player> m_players;
	private int m_mainPlayer;
	private int m_time;
	
	//Dialog
	private JDialog m_dial;
	
	//Create
	public GamePanel()
	{
		m_dial = new JDialog();
		m_dial.setModal(false);
		m_dial.setVisible(true);
		m_dial.setSize(Const.Size_x,Const.Size_y+70);
		m_dial.add(this);

		this.repaint();
	}
	
	//Paint
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
				
		//Background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Const.Size_x, Const.Size_y);
		
		//Bandeau
		/*g.setColor(Color.black);
		g.fillRect(0, 0, Const.D_W, Const.G_BANDEAU - 2);
		g.setColor(Color.gray);
		g.fillRect(0, Const.G_BANDEAU - 2, Const.D_W, 2);*/
		
		/*
		//Joueur Principal
		g.setColor(Color.WHITE);
		if(m_players != null && m_mainPlayer >= 0 && m_mainPlayer < m_players.size())
		{
			g.drawString(m_players.get(m_mainPlayer).getNom(), 10, 20);
			g.drawString("Equipe : " + m_players.get(m_mainPlayer).getEquipe(), 10, 60);
			g.drawString("Score   : " + m_players.get(m_mainPlayer).getPoints(), 10, 80);
			g.drawString("Vie        : " + m_players.get(m_mainPlayer).getVie(), 10, 100);
		}
		
		//Temps
		if(m_time > Const.DTOP_2MIN - 20)
			g.setColor(Color.GREEN);
		else if(m_time > Const.DTOP_2MIN / 2)
			g.setColor(Color.ORANGE);
		else if(m_time % 2 == 0)
			g.setColor(Color.red);
		else
			g.setColor(Color.yellow);
		g.drawString("Temps : " + m_time, 100, 20);
		
		//Score
		g.setColor(Color.white);
		for(int i = 0; i < m_players.size(); i++)
			g.drawString(m_players.get(i).getNom() + " : " + m_players.get(i).getPoints(), 200 + (150 * (i/5)), 20 + ((i % 5) * 20) );
		
		//Score par Equipe
		for(int i = 0; i<m_players.size(); i++)
		{
			Color c = LobbyPanel.intToColor(m_players.get(i).getEquipe());
			if(c == Color.BLACK)
				c = Color.WHITE;
			g.setColor(c);
			g.drawString(" Equipe  "+m_players.get(i).getEquipe()+ " Score : "+ PointsByTeam(m_players).get(i), 500 +(150 * (i/5)), 20 + ((i % 5) * 20) );
		}
		*/
		//Draw GameObject
		if(m_gos != null)
		{
			for(GameObject go : m_gos)
				g.drawImage(go.getTexture(), go.getX(), go.getY(), go.getW(),go.getH(), this);
		}
			
	}
	
	//Update
	public void update(ArrayList<GameObject> gos, ArrayList<Player> players, int mainPlayer, int time)
	{
		this.m_gos = gos;
		this.m_players = players;
		this.m_mainPlayer = mainPlayer;
		this.m_time = time;
		this.repaint();
	}
	
	//Close
	public void close()
	{
		m_dial.dispose();
	}
	
	//Méthode de calcul de point par equipe
	public ArrayList<Integer> PointsByTeam(ArrayList<Player> p)
	{
		ArrayList<Integer> pts = new ArrayList<Integer>();
		for(int t = 0; t<p.size();t++)
		{
			switch(p.get(t).getEquipe())
			{
				case 0:
				{	int j =+ p.get(t).getPoints();
					pts.add(0,j);
					break;
				}
				
				case 1:
				{	int j =+ p.get(t).getPoints();
					pts.add(1,j);
					break;
				}
				
				case 2:
				{	int j =+ p.get(t).getPoints();
					pts.add(2,j);
					break;
				}
				
				case 3:
				{	int j =+ p.get(t).getPoints();
					pts.add(3,j);
					break;
				}
				
				case 4:
				{	int j =+ p.get(t).getPoints();
					pts.add(4,j);
					break;
				}
				
				case 5:
				{	int j =+ p.get(t).getPoints();
					pts.add(5,j);
					break;
				}
				
				case 6:
				{	int j =+ p.get(t).getPoints();
					pts.add(6,j);
					break;
				}
				
				case 7:
				{	int j =+ p.get(t).getPoints();
					pts.add(7,j);
					break;
				}
				
				case 8:
				{	int j =+ p.get(t).getPoints();
					pts.add(8,j);
					break;
				}
				
				case 9:
				{	int j =+ p.get(t).getPoints();
					pts.add(9,j);
					break;
				}
			}
		}
		return pts;
	}
	
	//
}
