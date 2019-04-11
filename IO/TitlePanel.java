package IO;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import KERNEL.Const;
import KERNEL.Sprite;

/*
 * Classe JPanel qui set de Panel de presentation du Jeu.
 * C est la premiere chose que l utilisateur voit en lancant le jeu.
 * Il fait la jonction entre les divers fonctionnalites.
 */
public class TitlePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TitlePanel(MainFrame mainFrame)
	{
		this.setLayout(new GridLayout(5, 1));	// Set un GridLayout a 5 lignes et 1 colonne
		
		//Logo du TitlePanel
		this.add(new JLabel(new ImageIcon(Sprite.Title)));
		
		//On cree les 4 boutons qui font les jonctions :
		
		//Solo :
		JButton e1 = new JButton(Const.D_SINGLE);	//Nom du bouton
		e1.setName(Const.DT_START + Const.DT_SINGLE + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");	//On nomme le bouton selon une DATA
		e1.addActionListener(mainFrame);	//On ajoute une ActionListener a la frame principale
		this.add(e1);	//On ajoute le bouton au panel
		
		//Serveur :
		JButton e2 = new JButton(Const.D_SERVER);
		e2.setName(Const.DT_START + Const.DT_SERVER + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		e2.addActionListener(mainFrame);
		this.add(e2);
		
		//Client :
		JButton e3 = new JButton(Const.D_CLIENT);
		e3.setName(Const.DT_START + Const.DT_CLIENT + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		e3.addActionListener(mainFrame);
		this.add(e3);
		
		//Score : 
		JButton e4 = new JButton(Const.D_SCORE);
		e4.setName(Const.DT_START + Const.DT_SCORE + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		e4.addActionListener(mainFrame);
		this.add(e4);
	}
}
