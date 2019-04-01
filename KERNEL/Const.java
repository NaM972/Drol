package KERNEL;

public class Const 
{
	//Thread Sleep Time
	public static final int T_SLEEP = 10;
	
	//Keyboard
	public static final char H_UP = 'z';
	public static final char H_DOWN = 's';
	public static final char H_LEFT = 'q';
	public static final char H_RIGHT = 'd';
	public static final char H_BULLET = 't';
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int	RIGHT = 4;
	
	//Display
	public static final String D_TITLE = "DROL";
	public static final String D_CLOSE_TITLE = "MEGAMAN est dans la PLace !";
	public static final String D_CLOSE_TEXT = "Voulez vous quitter ?";
	public static final String D_SINGLE = "Jouer seul";
	public static final String D_SERVER = "Créer un serveur";
	public static final String D_CLIENT = "Se connecter à un serveur";
	public static final String D_HERBIV = "Pacifique";
	public static final String D_CARNIV = "Agressive";
	public static final String D_PHUMAN = "En attente d'un Humain";
	public static final String D_PCOMPU = "Ordinateur";
	public static final String D_PNULL = "Ordinhacker";
	public static final String D_2MIN = "Temps : 2 minutes";
	public static final String D_5MIN = "Temps : 5 minutes";
	public static final String D_10MIN = "Temps : 10 minutes";
	public static final String D_SEND = "Envoyer";
	public static final String D_PLAY = "Lancer la partie !";
	
	//Data
	public static final int DT_NULL = 0;
	public static final String DT_START = "D";
	public static final String DT_SEPAR = "/";
	public static final int DT_JOUEUR = 1;
	public static final int DT_EQUIPE = 2;
	public static final int DT_CARNIV = 3;
	public static final int DT_CHAT = 4;
	public static final int DT_TIME = 5;
	public static final int DT_PLAY = 6;
	public static final int DT_SINGLE = 7;
	public static final int DT_SERVER = 8;
	public static final int DT_CLIENT = 9;
	public static final int DT_CHANGENAME = 10;
	public static final int DT_KEY = 11;
	public static final int DT_MAINPLAYER = 12;
	
	//Data Operation
	public static final int DTOP_CARNIV = 1;
	public static final int DTOP_HERBIV = 0;
	public static final int DTOP_HUMAN = 0;
	public static final int DTOP_COMPU = 1;
	public static final int DTOP_PNULL = 2;
	public static final int DTOP_2MIN = 120;
	public static final int DTOP_5MIN = 300;
	public static final int DTOP_10MIN = 600;
	
	//Socket
	public static final int S_PORT = 8080;
	public static final int S_MAXPLAYER = 10;
	
	//Game
	public static final int G_VIE = 10;
	public static final int G_BANDEAU = 170;
	public static final int G_RIVERY = 212;
	public static final int G_RIVERH = 200;
	
	//Variables d'apparitions de Héro
	public static final int x = 945;
	public static final int y = 70;
	public static final int Hero = 1;
	
	//Variables de Terrain
	public static final int Floor0_y = 735;
	public static final int Floor1_y = 525;
	public static final int Floor2_y = 315;
	public static final int Floor3_y = 105;
	
	public static final int Trp1_x = 210;
	public static final int Trp2_x = 840;
	public static final int Trp3_x = 1610;
	
	public static final int Trp4_x = 455;
	public static final int Trp5_x = 980;
	public static final int Trp6_x = 1365;

	public static final int Plat2_x = 315;
	public static final int Plat3_x = 945;
	public static final int Plat4_x = 1715;

	public static final int Plat6_x = 560;
	public static final int Plat7_x = 1085;
	public static final int Plat8_x = 1470;
	
	public static final int TypePlat0 = 0;
	public static final int TypePlat1 = 1;
	public static final int TypePlat2 = 2;
	public static final int TypePlat3 = 3;
	public static final int TypePlat4 = 4;
	public static final int TypePlat5 = 5;
	public static final int TypePlat6 = 6;
	
	public static final int Colonne1_x = 175;
	public static final int Colonne2_x = 560;
	public static final int Colonne3_x = 945;
	public static final int Colonne4_x = 1330;
	public static final int Colonne5_x = 1715;
	
	//Variables de JFrame et JPanel
	public static final int Size_x = 800;
	public static final int Size_y = 735;
	
	//Variables des Entities
	public static final int Origine = 0;
	
	public static final int Size_Height = 35;
	public static final int Size_Width = 35;
	
	public static final int Plat0_W = 1925;
	public static final int Plat1_W = 210;
	public static final int Plat2_W = 525;
	public static final int Plat3_W = 665;
	public static final int Plat4_W = 455;
	public static final int Plat5_W = 420;
	public static final int Plat6_W = 280;
	
	public static final int TrpD_W = 105;
	
	public static final int Col_H = 175;
	
	//Variable de départ du héro
	public static final int Hero_START_X = 945;
	public static final int Hero_START_Y = 70;
	
	//Variable de Bullet
	public static final int Bullet_w = 7;
	public static final int Bullet_h = 5;
		
}
