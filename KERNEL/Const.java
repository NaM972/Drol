package KERNEL;

/*	Classe des Constantes appelées par toute les classes du Projet*/
public class Const {
	
	//Variables des GameObject
	public static final int Size_Width_Entity = 70;
	public static final int Size_Height_Entity = 70;
	
	public static final int Size_Width_Object = 35;
	public static final int Size_Height_Object = 35;
	
	public static final int Size_Width_Bonus = 60;
	public static final int	Size_Height_Bonus = 60;
	
	public static final int Size_Width_Door = 140;
	public static final int Size_Height_Door = 140;
	
	public static final int Hero_Start_X_Pacifiq = 945;
	public static final int Hero_Start_Y_Pacifiq = 275;
	
	public static final int Hero_Start_X_Aggressive = 945;
	public static final int Hero_Start_Y_Aggressive = 870;
	
	public static final int Hero_LimitUp = 100;
	
	public static final int Bullet_Width = 7;
	public static final int Bullet_Height = 5;
	public static final int Bullet_Limit = 345;
	public static final int Bullet_Speed = 5;
	
	
	public static final int Floor0_y = 905;
	public static final int Floor1_y = 695;
	public static final int Floor2_y = 485;
	public static final int Floor3_y = 275;
	
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
	
	public static final int TypeEnnemy0 = 0;
	public static final int TypeEnnemy1 = 1;
	public static final int TypeEnnemy2 = 2;
	public static final int SpeedEnnemy = 3;
	
	public static final int Colonne1_x = 175;
	public static final int Colonne2_x = 560;
	public static final int Colonne3_x = 945;
	public static final int Colonne4_x = 1330;
	public static final int Colonne5_x = 1715;
	
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
	
	//Variables de GamePanel
	public static final int Size_x_Panel = 1925;
	public static final int Size_y_Panel = 975;
	
	//Bandeau Noir des Informations
	public static final int Width_Flag = 1915;
	public static final int Height_Flag = 100;
	
	//Informations 
	public static final int Coord_x_Info = 10;
	public static final int Coord_x_InfoT = 100;
		
	public static final int Coord_y_InfoN = 30;
	public static final int Coord_x_InfoE = 50;
	public static final int Coord_x_InfoS = 70;
	public static final int Coord_x_InfoV = 90;
	public static final int Coord_y_InfoT = 10;
	
	//Thread Sleep Time
	public static final int T_SLEEP = 10;
	
	//Keyboard
	public static final char H_UP = 'z';
	public static final char H_DOWN = 's';
	public static final char H_LEFT = 'q';
	public static final char H_RIGHT = 'd';
	public static final char H_BULLET = 't';
	
	//Update Hero
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int	RIGHT = 4;
	public static final int BULLET = 5;
	
	//Display
	public static final String D_TITLE = "DROL";
	public static final String D_CLOSE_TITLE = "ROCKMAN est dans la place ! ";
	public static final String D_CLOSE_TEXT = "Voulez vous quitter ?";
	public static final String D_SINGLE = "Jouer seul";
	public static final String D_SERVER = "Creer un serveur";
	public static final String D_CLIENT = "Se connecter a un serveur";
	public static final String D_SCORE = "Tableau des Scores";
	public static final String D_HERBIV = "Pacifique";
	public static final String D_CARNIV = "Agressif";
	public static final String D_PHUMAN = "En attente d'un Humain";
	public static final String D_PCOMPU = "Ordinateur";
	public static final String D_PNULL = "Ordinhacker";
	public static final String D_2MIN = "Temps : 2 minutes";
	public static final String D_5MIN = "Temps : 5 minutes";
	public static final String D_10MIN = "Temps : 10 minutes";
	public static final String D_SEND = "Envoyer";
	public static final String D_PLAY = "Lancer la partie !";
	
	//DATA
	public static final int DT_NULL = 0;
	public static final String DT_START = "D";
	
	public static final String DT_SEPAR = "/";
	public static final int DT_JOUEUR = 1;
	public static final int DT_EQUIPE = 2;
	public static final int DT_AGGRES = 3;
	public static final int DT_CHAT = 4;
	public static final int DT_TIME = 5;
	public static final int DT_PLAY = 6;
	public static final int DT_SINGLE = 7;
	public static final int DT_SERVER = 8;
	public static final int DT_CLIENT = 9;
	public static final int DT_CHANGENAME = 10;
	public static final int DT_KEY = 11;
	public static final int DT_MAINPLAYER = 12;
	public static final int DT_SCORE = 13;
	
	//DATA OPERATION
	public static final int DTOP_AGGRES = 1;
	public static final int DTOP_PACIF = 0;
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
	public static final int G_VIE = 3;
	
	//ScoreFrame
	public static final int Size_x_Score = 660;
	public static final int Size_y_Score = 600;
	
}
