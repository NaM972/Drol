package KERNEL;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture 
{

	public static BufferedImage HeroUp;
	public static BufferedImage HeroDown;
	public static BufferedImage HeroLeft;
	public static BufferedImage HeroRight;
	public static BufferedImage Colonnes;
	public static BufferedImage Plat0;
	public static BufferedImage Plat1;
	public static BufferedImage Plat2;
	public static BufferedImage Plat3;
	public static BufferedImage Plat4;
	public static BufferedImage Plat5;
	public static BufferedImage Plat6;
	public static BufferedImage TrapDoor;
	public static BufferedImage Bullet;

	//Load
		public static void loadTexture()
		{
			try
			{
				HeroUp = ImageIO.read((new File("SPRITE/HeroUp.png"))); 
				HeroDown = ImageIO.read((new File("SPRITE/HeroDown.png")));
				HeroLeft = ImageIO.read((new File("SPRITE/HeroLeft.png")));
				HeroRight = ImageIO.read((new File("SPRITE/HeroRight.png")));
				Colonnes = ImageIO.read(new File("SPRITE/Colonnes.png"));
				Plat0 = ImageIO.read(new File("SPRITE/Plateforme0.png"));
				Plat1 = ImageIO.read(new File("SPRITE/Plateforme1.png"));
				Plat2 = ImageIO.read(new File("SPRITE/Plateforme2.png"));
				Plat3 = ImageIO.read(new File("SPRITE/Plateforme3.png"));
				Plat4 = ImageIO.read(new File("SPRITE/Plateforme4.png"));
				Plat5 = ImageIO.read(new File("SPRITE/Plateforme5.png"));
				Plat6 = ImageIO.read(new File("SPRITE/Plateforme6.png"));
				TrapDoor = ImageIO.read(new File("SPRITE/TrapDoor.png"));
				Bullet = ImageIO.read(new File("SPRITE/Bullet.png"));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		//
}
