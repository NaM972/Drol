package CPU;

import KERNEL.*;

/*
 * Classe heritant des methodes gameobject sans les utiliser.
 * Platform est une classe representant un objet qui est montable.
 * Avec divers type de longueur selon un index.
 */
public class Platform extends GameObject{

	//Constructeur par défaut
	public Platform(int x, int y, int index) {
		this.setX(x);					//Meme chose que pour le hero
		this.setY(y);
		this.setH(Const.Size_Height_Object);	//Constante d'objet
		this.setMountable(true);				//Plateforme montable
		this.setAggressive(false);				//Agressive ? NON
		initPlatform(index);		//Methode initialisant la plateforme
	}
	
	public void initPlatform(int index) {
		switch(index) {
		case Const.TypePlat0:{
			setW(Const.Plat0_W);
			setTexture(Sprite.Plat0);
			break;
		}
		case Const.TypePlat1:{
			setW(Const.Plat1_W);
			setTexture(Sprite.Plat1);
			break;
		}
		case Const.TypePlat2:{
			setW(Const.Plat2_W);
			setTexture(Sprite.Plat2);
			break;
		}
		case Const.TypePlat3:{
			setW(Const.Plat3_W);
			setTexture(Sprite.Plat3);
			break;
		}
		case Const.TypePlat4:{
			setW(Const.Plat4_W);
			setTexture(Sprite.Plat4);
			break;
		}
		case Const.TypePlat5:{
			setW(Const.Plat5_W);
			setTexture(Sprite.Plat5);
			break;
		}
		case Const.TypePlat6:{
			setW(Const.Plat6_W);
			setTexture(Sprite.Plat6);
			break;
		}
	}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}

}
