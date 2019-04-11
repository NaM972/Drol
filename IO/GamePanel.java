package IO;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;

import CPU.*;
import KERNEL.*;

/*
 * Classe GamePanel representant le panel du jeu.
 * Elle recupere la liste des joueurs et des gameobject initialises dans GameManager.
 */
public class GamePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variable
	private ArrayList<GameObject> m_gos;
	private ArrayList<Player> m_players;
	private int m_mainPlayer;
	private int m_time;
	
	//Dialog
	private JDialog m_dial;
	
	//Constructeur
	public GamePanel()
	{
		m_dial = new JDialog();
		m_dial.setModal(false);
		m_dial.setVisible(true);
		m_dial.setSize(Const.Size_x_Panel,Const.Size_y_Panel);
		m_dial.add(this);
		this.repaint();
	}
	
	//Paint les composantes
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);	
		
		//Background
		g.setColor(Color.CYAN);
		g.drawImage(Sprite.BackGround,0, Const.Height_Flag, Const.Size_x_Panel, Const.Size_y_Panel-Const.Height_Flag, this);
		
		//Bandeau
		g.drawImage(Sprite.Flag,0, 0, Const.Size_x_Panel, Const.Height_Flag, this);
		g.setColor(Color.gray);
		g.fillRect(0, Const.Height_Flag - 2, Const.Size_x_Panel, 2);
		
		
		//Joueur Principal
		g.setColor(Color.WHITE);
		if(m_players != null && m_mainPlayer >= 0 && m_mainPlayer < m_players.size())
		{
			
			g.drawString(m_players.get(m_mainPlayer).getNom(), 10, 30);
			g.drawString("Equipe : " + m_players.get(m_mainPlayer).getEquipe(), 10, 50);
			g.drawString("Score   : " + m_players.get(m_mainPlayer).getPoints(), 10, 70);
			g.drawString("Vie        : " + m_players.get(m_mainPlayer).getVie(), 10, 90);
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
		g.drawString("Temps : " + m_time, 100, 10);
		
		
		//Score
		int i = 0;
		g.setColor(Color.white);
		for(Player p: m_players) {
			g.drawString(p.getNom() + " : " + p.getPoints(), 200 + (150 * (i/5)), 30 + ((i % 5) * 20) );
			//System.out.println(p.getID());
			i++;
		}
		
		//Score par Equipe
		for(i = 0; i<m_players.size(); i++)
		{
			Color c = LobbyPanel.intToColor(m_players.get(i).getEquipe());
			if(c == Color.BLACK)
				c = Color.WHITE;
			g.setColor(c);
			g.drawString(" Equipe  "+m_players.get(i).getEquipe()+ " Score : "+ PointsByTeam(m_players).get(i), 500 +(150 * (i/5)), 30 + ((i % 5) * 20) );
		}
		
		//On affiche tout les GameObject recuperes de la liste du GameManager
		for(GameObject go : m_gos)
			g.drawImage(go.getTexture(), go.getX(), go.getY(), go.getW(),go.getH(), this);
		
		for(Player p : m_players){	//On parcourt la liste des joueur afin de voir si un a perdu, si oui, on affiche GameOver
			if(p.getVie() == 0 || m_time == 0) {
				paintComponentGameOver(g);
			}
		}
		
	}
	
	//Methode affichant gameOver en le superposant sur tous le panel
	protected void paintComponentGameOver(Graphics g) {
        super.paintComponent(g);
        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout((LayoutManager) null);
        
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5F));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(old);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", 1, 80));
        g.drawString("GAME OVER",Const.Size_x_Panel / 2 - 220, Const.Size_y_Panel / 2 - 40);
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
	
	//Methode de calcul de point par equipe selon la couleur de l equipe
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

}
