package KERNEL;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*	Classe des Constantes Images des GameObjects*/
public class Sprite {
	
	//Sprite du Hero
	public static BufferedImage HeroUpR;
	public static BufferedImage HeroUpL;
	
	public static BufferedImage HeroDownR;
	public static BufferedImage HeroDownL;
	
	public static BufferedImage HeroLeft;
	public static BufferedImage HeroRight;
	
	//Sprite des colonnes
	public static BufferedImage Colonnes;
	
	//Sprite des plateformes
	public static BufferedImage Plat0;
	public static BufferedImage Plat1;
	public static BufferedImage Plat2;
	public static BufferedImage Plat3;
	public static BufferedImage Plat4;
	public static BufferedImage Plat5;
	public static BufferedImage Plat6;
	
	//Sprite des Trappes / Ici des Teleporteurs
	public static BufferedImage TrapDoor;
	
	//Sprite des Projectiles
	public static BufferedImage Bullet;
	
	//Sprite du Panel de presentation
	public static BufferedImage Title;
	
	//Sprite du GamePanel
	public static BufferedImage BackGround;
	public static BufferedImage Flag;
	
	//Sprite des Ennemies 
	public static BufferedImage	Ennemy0;
	public static BufferedImage Ennemy1;
	public static BufferedImage Ennemy2;
	
	//Sprite de l'objectif
	public static BufferedImage DoorWin;
	
	//Load des Sprites
	public static void loadTexture() {
		try {
				HeroUpR 	= ImageIO.read(new File("TEXTURE/HeroUpR.png"));
				HeroUpL 	= ImageIO.read(new File("TEXTURE/HeroUpL.png"));
				HeroDownR 	= ImageIO.read(new File("TEXTURE/HeroDownR.png"));
				HeroDownL 	= ImageIO.read(new File("TEXTURE/HeroDownL.png"));
				HeroLeft 	= ImageIO.read(new File("TEXTURE/HeroLeft.png"));
				HeroRight 	= ImageIO.read(new File("TEXTURE/HeroRight.png"));
				
				Colonnes	= ImageIO.read(new File("TEXTURE/Colonnes.png"));
				
				Plat0		= ImageIO.read(new File("TEXTURE/Plat0.png"));
				Plat1		= ImageIO.read(new File("TEXTURE/Plat1.png"));
				Plat2		= ImageIO.read(new File("TEXTURE/Plat2.png"));
				Plat3		= ImageIO.read(new File("TEXTURE/Plat3.png"));
				Plat4		= ImageIO.read(new File("TEXTURE/Plat4.png"));
				Plat5		= ImageIO.read(new File("TEXTURE/Plat5.png"));
				Plat6		= ImageIO.read(new File("TEXTURE/Plat6.png"));
				
				TrapDoor	= ImageIO.read(new File("TEXTURE/TrapDoor.png"));
				
				Bullet		= ImageIO.read(new File("TEXTURE/Bullet.png"));
				
				Title		= ImageIO.read(new File("TEXTURE/Title.png"));
				
				BackGround	= ImageIO.read(new File("TEXTURE/Background.png"));
				Flag		= ImageIO.read(new File("TEXTURE/Flag.png"));
				
				Ennemy0		= ImageIO.read(new File("TEXTURE/Ennemy0.png"));
				Ennemy1		= ImageIO.read(new File("TEXTURE/Ennemy1.png"));
				Ennemy2		= ImageIO.read(new File("TEXTURE/Ennemy2.png"));
				
				DoorWin		= ImageIO.read(new File("TEXTURE/DoorWin.png"));
				 
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	
}
