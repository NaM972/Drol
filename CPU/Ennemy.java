package CPU;

import KERNEL.*;

/*
 * Classe Ennemy qui herite des methodes de sa classe parent GameObject.
 * Un Ennemy est aggressif est peut tuer un hero si il le touche.
 * De plus il peut etre tuer par un bullet d un hero et respawn a ses coordonnees.
 * Selon un index chaque ennemy a un sprite different
 */
public class Ennemy extends GameObject{

	private int start_x, start_y;	//Coord. de depart de Ennemy
	
	//Constructeur par defaut
	public Ennemy(int start_x, int start_y, int direction, int Index){
		this.start_x = start_x;
		this.start_y = start_y;
		this.setX(start_x);
		this.setY(start_y);
		this.setW(Const.Size_Width_Entity);
		this.setH(Const.Size_Width_Entity);
		this.setDirection(direction);
		this.setAggressive(true);
		initEnnemySprite(Index);
	}
	
	//Methode selon un index change la texture de l Ennemy
	public void initEnnemySprite(int Index) {
		
		switch(Index) {
		
	  		case Const.TypeEnnemy0:{
	  			this.setTexture(Sprite.Ennemy0);
	  			break;
	  			
	  		}
	  		
	  		case Const.TypeEnnemy1:{
	  			this.setTexture(Sprite.Ennemy1);
	  			break;
	  			
	  		}
	  		
	  		case Const.TypeEnnemy2:{
	  			this.setTexture(Sprite.Ennemy2);
	  			break;
	  		}
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//On update chaque Ennemy selon sa direction
		if(getDirection() == Const.LEFT && (getX()-3) >= 0){
			this.setX(getX() - 1);
		}else 
			if(getDirection() == Const.LEFT && (this.getX() - Const.SpeedEnnemy) < 0){
				this.setX(Const.Size_x_Panel);
		    }else 
		    	if(getDirection() == Const.RIGHT && (getX() + Const.SpeedEnnemy) <= Const.Size_x_Panel){
		    		this.setX(getX() + 1);
		    	}else 
		    		if(getDirection() == Const.RIGHT && (getX() + Const.SpeedEnnemy) > Const.Size_x_Panel){
		    			this.setX(0);
		    		}
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		//Si un Ennemy est touche par un bullet d un hero on le fait respawn a ses coord. origine
		this.setX(start_x);
		this.setY(start_y);
	}

}
