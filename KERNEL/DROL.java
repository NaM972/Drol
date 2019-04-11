package KERNEL;

import CPU.GameManager;
import IO.InputManager;
import IO.OutputManager;

public class DROL {
	
	//Main
	public static void main(String[] args) 
	{
		Sprite.loadTexture();
		
		//Cr�er les thread
		InputManager im = new InputManager("InputManager");
		OutputManager om = new OutputManager("OutputManager");
		GameManager gm = new GameManager("GameManager");
		
		//Associe les thread entre eux
		im.setBus(om, gm);
		om.setBus(im, gm);
		gm.setBus(im, om);
		
		//Start
		im.start();
		om.start();
		gm.start();
		
		//Attend la fin des thread
		try 
		{
			im.join();
			om.join();
			gm.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}

}
