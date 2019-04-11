package IO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import KERNEL.*;

/*
 * Classe LobbyPanel representant le lobby pour choisir si on veut joueur pacifiste ou agressif en Multi ou en solo.
 */
public class LobbyPanel extends JDialog {
	
	private static final long serialVersionUID = 1L;

	//Variable
	private JTextArea m_txtArea;
	private JTextField m_txtField;
	private JButton m_sendButton;
	private JButton m_timeButton;
	private JButton m_playButton;
	
	private JButton[] m_bPlayer;
	private JButton[] m_bTeam;
	private JButton[] m_bPacif;
	
	//Constructeur
	public LobbyPanel(MainFrame mainFrame, int n){
		super(mainFrame);
		this.setSize(800,800);
		this.setResizable(false);
		//Player List
		JPanel playerList = new JPanel();
		playerList.setLayout(new GridLayout(n,3) );
		
		m_bPlayer = new JButton[n];
		m_bTeam = new JButton[n];
		m_bPacif = new JButton[n];
		for(int i = 0; i < n; i++){
			JButton e1 = new JButton(Const.D_PHUMAN + " " + i);
			JButton e2 = new JButton("" + i);
			e2.setForeground(intToColor(i) );
			JButton e3 = new JButton(Const.D_HERBIV);
			
			e1.setName(Const.DT_START + Const.DT_JOUEUR + Const.DT_SEPAR + i + Const.DT_SEPAR + Const.DTOP_HUMAN);
			e2.setName(Const.DT_START + Const.DT_EQUIPE + Const.DT_SEPAR + i + Const.DT_SEPAR + i);
			e3.setName(Const.DT_START + Const.DT_AGGRES + Const.DT_SEPAR + i + Const.DT_SEPAR + Const.DTOP_PACIF);
			
			e1.addActionListener(mainFrame);
			e2.addActionListener(mainFrame);
			e3.addActionListener(mainFrame);
			
			playerList.add(e1);
			playerList.add(e2);
			playerList.add(e3);
			
			m_bPlayer[i] = e1;
			m_bTeam[i] = e2;
			m_bPacif[i] = e3;
		}
		
		//Chat
		m_txtArea = new JTextArea();
		JScrollPane scrollChat = new JScrollPane(m_txtArea);
		
		//Send
		JPanel inputRule = new JPanel();
		inputRule.setLayout(new GridLayout(2,2) );
		
		m_txtField = new JTextField();
		inputRule.add(m_txtField);
		m_sendButton = new JButton(Const.D_SEND);
		m_sendButton.setName(Const.DT_START + Const.DT_CHAT + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "sampleText");
		m_sendButton.addActionListener(mainFrame);
		inputRule.add(m_sendButton);		
		
		//Rule
		m_timeButton = new JButton(Const.D_2MIN);
		m_timeButton.setName(Const.DT_START + Const.DT_TIME + Const.DT_SEPAR + "0" + Const.DT_SEPAR + Const.DTOP_2MIN + Const.DT_SEPAR + "0");
		m_timeButton.addActionListener(mainFrame);
		
		m_playButton = new JButton(Const.D_PLAY);
		m_playButton.setName(Const.DT_START + Const.DT_PLAY + Const.DT_SEPAR + "0" + Const.DT_SEPAR + "0");
		m_playButton.addActionListener(mainFrame);
		
		inputRule.add(m_timeButton);
		inputRule.add(m_playButton);
		
		//Center Panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2,1) );
		centerPanel.add(new JScrollPane(playerList) );
		centerPanel.add(scrollChat);
		
		//Add Panel
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(inputRule, BorderLayout.SOUTH);
	}
	
	//Add text to chat
	public void addTextChat(String txt){
		this.m_txtArea.setText(this.m_txtArea.getText() + "\n" + txt);
	}
	
	//Get text from user
	public String getTextFromUser(){
		return this.m_txtField.getText();
	}
	
	//Change Player Name
	public void changePlayerName(int i, Data d){
		//Verifie qu'on peut modifier
		if(i >= 0 && i < m_bPlayer.length)
			m_bPlayer[i].setText(d.getS() );
		m_bPlayer[i].setName(d.convertToString() );
		
		//Change le nom du bouton, mais met le type a  Joueur
		//d.setType(Const.DT_JOUEUR);
		//System.out.println("CONVERSION : " + d.convertToString());
	}
	
	//Set Agressif
	public void setAggressif(int i, Data d){
		if(d.getOperation() == Const.DTOP_PACIF)
			m_bPacif[i].setText(Const.D_HERBIV);
		else if(d.getOperation() == Const.DTOP_PACIF)
			m_bPacif[i].setText(Const.D_CARNIV);
		m_bPacif[i].setName(d.convertToString() );
	}
	
	//Set Equipe
	public void setEquipe(int i, Data d){
		m_bTeam[i].setText("" + d.getOperation());
		m_bTeam[i].setName(d.convertToString() );
		m_bTeam[i].setForeground(intToColor(d.getOperation()));
	}
	
	//Set Player
	public void setPlayer(int i,Data d){
		if(d.getOperation() == Const.DTOP_HUMAN)
			m_bPlayer[i].setText(Const.D_PHUMAN + " " + i);
		if(d.getOperation() == Const.DTOP_COMPU)
			m_bPlayer[i].setText(Const.D_PCOMPU + " " + i);
		if(d.getOperation() == Const.DTOP_PNULL)
			m_bPlayer[i].setText(Const.D_PNULL + "");
		m_bPlayer[i].setName(d.convertToString() );
	}
	
	//Set Time
	public void setTime(Data d){
		//Server ou solo
		if(d.getPlayer() == 0)
		{
			if(d.getOperation() == Const.DTOP_2MIN)
				m_timeButton.setText(Const.D_2MIN);
			if(d.getOperation() == Const.DTOP_5MIN)
				m_timeButton.setText(Const.D_5MIN);
			if(d.getOperation() == Const.DTOP_10MIN)
				m_timeButton.setText(Const.D_10MIN);
			m_timeButton.setName(d.convertToString() );
		}
	}
	
	//Conversion Lobby en String
	public String convertLobbyToString(){
		String all = m_timeButton.getName();
		
		for(int i = 0; i < m_bPlayer.length; i++){
			//BETA Ajouter les joueurs et modifier les noms et les scores
			
			/*
			all += m_bPlayer[i].getName();
			Data cn = Data.convertStringToDataList(m_bPlayer[i].getName()).get(0);
			cn.setType(Const.DT_CHANGENAME);
			all += cn.convertToString();
			*/
			
			//Ajoute le joueur, l'equipe et le agressif/pacifiste
			all += m_bPlayer[i].getName() + m_bTeam[i].getName() + m_bPacif[i].getName();
			
		}
		
		return all;
	}
	
	//Get Player Name
	public String getPlayer(int i){
		return m_bPlayer[i].getText();
	}
	
	//Init to Color
	public static Color intToColor(int i){
		switch(i){
		
			case 0:return Color.BLUE;
			case 1:return Color.RED;
			case 2:return Color.GREEN;
			case 3:return Color.YELLOW;
			case 4:return Color.CYAN;
			case 5:return Color.magenta;
			case 6:return Color.PINK;
			case 7:return Color.ORANGE;
			case 8:return Color.gray;
			case 9:return Color.BLACK;
			
		}
		return Color.BLACK;
	}
}
