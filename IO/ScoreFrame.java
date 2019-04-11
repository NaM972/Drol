package IO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import KERNEL.Const;

/*
 * Classe permettant l affichage des scores des precedants joueurs
 * Elle contient son propre panel.
 */
public class ScoreFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6930801178246593983L;

	public ScoreFrame(String title) {
		//On attribut la taille et le fait que la fenetre ne soit pas resize
		super(title);
		this.setSize(Const.Size_x_Score , Const.Size_y_Score);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	//Apparait au milieu de l'ecran
		this.getContentPane().setBackground(Color.lightGray); //Background
		
		//JLayeredPaneajoute de la profondeur a un conteneur JFC / Swing, 
		//permettant ainsi aux composants de se chevaucher en cas de besoin
		JLayeredPane lpane = new JLayeredPane();
		this.setLayout(new BorderLayout());
		this.add(lpane, BorderLayout.CENTER);
		lpane.setBounds(0, 0, Const.Size_x_Score, Const.Size_y_Score);
		
		//On cree un panel et on l'integre au JLayeredPane
		ScorePanel cp = new ScorePanel();
		cp.setBounds(0, 0, Const.Size_x_Score, Const.Size_y_Score);
		this.add(cp);
		lpane.add(cp,  new Integer(0), 0);
	}
	
	public static class ScorePanel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ScorePanel() {
			
			setLayout(null);
			
			//On cree des JLabel. C est pour faire joli
			JLabel team = new JLabel(Const.D_SCORE, JLabel.CENTER);
            team.setFont(new Font("Snap ITC", Font.PLAIN, 32));
            team.setBounds(0, 0, 500, 40);
            team.setLocation(640 / 2 - team.getWidth() / 2 + 50, 45);
            team.setForeground(Color.BLUE);
            add(team);
            
            //Les JLabel se superposent
            JLabel shadow = new JLabel(Const.D_SCORE, JLabel.CENTER);
            shadow.setFont(new Font("Snap ITC", Font.PLAIN, 32));
            shadow.setBounds(0, 0, 500, 40);
            shadow.setLocation(640 / 2 - team.getWidth() / 2 + 50 + 2, 45 + 2);
            shadow.setForeground(Color.gray);
            add(shadow);
            
            //On vas lire les noms des joueurs et leurs scores des fichiers txt et les 
            //replacer dans le tableau, pour ça on a besoin de deux Listes
            ArrayList<String> allScores = new ArrayList<>();
            ArrayList<String> allNames = new ArrayList<>();
            
            try {            		    
                	//On lit les noms
                	FileReader frNames = new FileReader("SCORE/names.txt");
                	BufferedReader brNames = new BufferedReader(frNames);
                	
                	//On lit les scores
                	FileReader fr = new FileReader("SCORE/scores.txt");
                	BufferedReader br = new BufferedReader(fr);
                	
                	//On lit ligne par ligne
                	for (int i = 0; i < 5; i++) {
                		allScores.add(br.readLine());
                		allNames.add(brNames.readLine());
                	}

                	frNames.close();	//On arrete les lecteurs
                	brNames.close();	//On arrete les lecteurs
                	br.close();			//On arrete les lecteurs
                	fr.close();			//On arrete les lecteurs
                	
            	}catch(IOException e1) {
            		e1.printStackTrace();
            	}
            
            //On cree une list d ArrayList qui afficheront les noms et scores
            ArrayList<JLabel> lables = new ArrayList<>();
            lables.add(new JLabel(allNames.get(0), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(1), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(2), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(3), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(4), JLabel.LEFT));

            lables.add(new JLabel(allScores.get(0), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(1), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(2), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(3), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(4), JLabel.LEFT));
            
            //Pour faire joli on leur donne un style de font et une couleur
            for (int i = 0; i < lables.size(); i++) {
                JLabel jl = lables.get(i);	//On recupere le lable de la liste
                int X = 140;				//Coord. en x du label
                int Y = (198 + i * 70);		//Coord. en y du lable * par l indice
                jl.setFont(new Font("SERIF", Font.BOLD, 25));	//On donne une Font et une taille de police
                jl.setForeground(Color.BLACK);	//On donne une couleur
                
                //Si i est superieur a 4 on change les Coord. en x et y la Font et la couleur
                if (i > 4) {
                    X = 400;
                    Y = (198 + (i - 5) * 70);
                    jl.setFont(new Font("Snap ITC", Font.PLAIN, 24));
                    jl.setForeground(Color.CYAN);
                }
                
                //On set la taille et les Coord. etablis pour la JLabel
                jl.setBounds(0, 0, 400, 22);
                jl.setLocation(X, Y);
                
                //On ajoute au Panel la JPanel
                this.add(jl);
            }
		}
	}
}
