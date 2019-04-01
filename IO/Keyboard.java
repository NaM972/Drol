package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{

	//Variable de stockage
	private char m_lastKey;
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		System.out.println("k : " + e.getKeyChar());
		m_lastKey = e.getKeyChar();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Retourne la dernière touche tapée
	public char getLastKey()
	{
		char tmp = m_lastKey;
		m_lastKey = 0;
		return tmp;
	}

	//
}
