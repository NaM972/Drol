package IO;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import KERNEL.*;

public class TitlePanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Create
	public TitlePanel(MainFrame mainFrame)
	{
		this.setLayout(new GridLayout(4, 1));

		//Logo
		this.add(new JLabel(new ImageIcon("img/logo.png") ) );
		
		//Single
		JButton e1 = new JButton(Const.D_SINGLE);
		e1.setName(Const.DT_START + Const.DT_SINGLE + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		e1.addActionListener(mainFrame);
		this.add(e1);
		
		//Server
		JButton e2 = new JButton(Const.D_SERVER);
		e2.setName(Const.DT_START + Const.DT_SERVER + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		e2.addActionListener(mainFrame);
		this.add(e2);
		
		//Client
		JButton e3 = new JButton(Const.D_CLIENT);
		e3.setName(Const.DT_START + Const.DT_CLIENT + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		e3.addActionListener(mainFrame);
		this.add(e3);
	}
	
	//
}
