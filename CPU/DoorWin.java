package CPU;

import KERNEL.Const;
import KERNEL.Sprite;

/*
 * Classe heritant des methodes gameobject.
 * DoorWin est une classe representant un objet qui rapporte beaucoup de point au joueur.
 * Elle apparait aleatoirement selon des constantes sur le terrain afin d offrir au joueur une sensation de recommencement et de difficultee.
 */
public class DoorWin extends GameObject{

	//Variable permettant son apparition à divers endroit
	private int SpriteTime = 0;
	
	//Constructeur par defaut
	public DoorWin(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setH(2*Const.Size_Height_Entity); 	
		this.setW(2*Const.Size_Width_Entity);		
		this.setMountable(false);
		this.setAggressive(false);
		this.setTexture(Sprite.DoorWin);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//A partir du SpriteTime on change de place la door
		if(SpriteTime == 2000) {
			this.setX(-800);
			this.setY(Const.Floor0_y-(2*Const.Size_Height_Entity));
		}
		if(SpriteTime == 2500) {
			this.setX(Const.Size_x_Panel-((2*Const.Size_Width_Entity)+300));
		}
		if(SpriteTime == 5000) {
			this.setX(2500);
		}
		if(SpriteTime == 5500) {
			this.setX(Const.Origine);
		}
		if(SpriteTime == 11000) {
			this.setX(-800);
		}
		if(SpriteTime == 11500) {
			this.setX(Const.Hero_Start_X_Pacifiq);
		}
		if(SpriteTime == 23000) {
			this.setX(2500);
			this.SpriteTime = 0;
		}
		
		this.SpriteTime = SpriteTime+1;	//Compteur SpriteTime
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}

}
