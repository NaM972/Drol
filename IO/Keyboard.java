package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*	Cette classe simule un Clavier. C est grace a cette 
 * classe que les touches du clavier sont reconnus.		*/

public class Keyboard implements KeyListener{
	
	//Variable de stockage
	private char m_lastKey;
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("k :"+ e.getKeyChar());
		m_lastKey = e.getKeyChar();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Retourne la derniere touche tapee
	public char getLastKey() {
		char tmp = m_lastKey;
		m_lastKey = 0;			//On remet 'la variable a 0'
		return tmp;
	}
}
