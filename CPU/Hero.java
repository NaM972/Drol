package CPU;

import KERNEL.*;

/*	Classe Hero controle par un joueur selon son Identificateur Id. 
 * 	Elle herite des methodes de sa classe parent GameObject. Sa methode
 * 	update appel la liste des GameObjet initialisee dans le GameManager.
 * 																	*/
public class Hero extends GameObject{

	private int start_x, start_y;	//Coord. de depart du hero
	private Bullet b;				//Bullet du player
	private Boolean FreeFall;		//Boolean servant a savoir si le hero est en chute libre ou non
	
	//On recupere la liste des GameObject initialisee dans le GameManager
	private GameManager m_gm;
	
	//Constructeur par defaut
	public Hero(int start_x, int start_y, int iD, boolean aggressive, GameManager m_gm) {
		super();
		this.start_x = start_x;
		this.start_y = start_y;
		this.setX(start_x);
		this.setY(start_y);
		this.setW(Const.Size_Width_Entity);
		this.setH(Const.Size_Height_Entity);
		this.setID(iD);
		this.setAggressive(aggressive);
		this.m_gm = m_gm;
		this.setMountable(false);
		this.FreeFall = false;
		this.setTexture(Sprite.HeroRight);
		this.setDirection(Const.RIGHT);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//Si en chute libre 
		if(this.FreeFall == true) {
			this.setY(this.getY()+1);
		}
		
		//On regarde selon la collision si c'est un ennemie ou un objet
		
		//On parcourt la liste de GameObject du GameManager
		for(GameObject go : m_gm.getGameObjects()) {
			//Collision (si ce n est pas lui et utiliser l algorithme de collision dans classe parent GameObject)
			if(go != this && collidedWith(go)) {				
				
				//Si le go est agressif
				if(go.isAggressive()) {
					if(go.getClass() == Ennemy.class) {
						this.respawn();
					}
				}
				
				//Si le go est montable
				if(go.isMountable() == true) {
					//Si c'est une plateforme
					if(go.getClass() == Platform.class) {
						//Si la direction du hero est droite, gauche ou bas
						if(this.getDirection() == Const.DOWN || this.getDirection() == Const.RIGHT || this.getDirection() == Const.LEFT) {
							//Si le hero n est pas en chute libre 
							if(this.FreeFall != true) {
								this.setY(go.getY()-Const.Size_Height_Entity);
							}else {
								
								this.setY(go.getY()-Const.Size_Height_Entity);
								this.FreeFall = false;
								
								//Animation de recovery a droite si il down a droite etc
								if(this.getTexture() == Sprite.HeroDownR) {
									this.setTexture(Sprite.HeroRight);
									this.setDirection(Const.RIGHT);
								}else {
									this.setTexture(Sprite.HeroLeft);
									this.setDirection(Const.LEFT);
								}
							}
						}
						//Si la direction du hero est en haut
						if(this.getDirection() == Const.UP) {
							//On refait la meme
							if(this.FreeFall == true) {
								if(go.getY() <= this.getY()) {
									this.setY(go.getY()+Const.Size_Height_Entity);
								}else {
									this.setY(go.getY()-Const.Size_Height_Entity);
									this.FreeFall = false;
								
									//Animation de recovery a droite si il down en haut etc
									if(this.getTexture() == Sprite.HeroUpR) {
										this.setTexture(Sprite.HeroRight);
										this.setDirection(Const.RIGHT);
									}else {
										this.setTexture(Sprite.HeroLeft);
										this.setDirection(Const.LEFT);
									}
								}
							}
						}
					}
					
					if(go.getClass() == TrapDoor.class) {
						
						if(this.getDirection() == Const.DOWN) {
							if(this.FreeFall == true) {
								this.setY(go.getY()-Const.Size_Height_Entity);
							}else {
								this.setY(go.getY()+Const.Size_Height_Entity);
								this.FreeFall = true;
							}
						}
						
						if(this.getDirection() == Const.UP || this.getDirection() == Const.RIGHT || this.getDirection() == Const.LEFT) {
							if(this.getY() >= go.getY()) {
								this.setY(go.getY()-Const.Size_Height_Entity);
								//Animation
								if(this.getTexture() == Sprite.HeroUpR) {
									this.setTexture(Sprite.HeroRight);
									this.setDirection(Const.RIGHT);
								}else {
									this.setTexture(Sprite.HeroLeft);
									this.setDirection(Const.LEFT);
								}
								FreeFall = false;
							}
						}
					}
				}
				
				//Si le hero est sur la porte de fin
				if(go.getClass() == DoorWin.class) {
					this.addPoint();
					this.setX(start_x);
					this.setY(start_y);
				}
			}
		}
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		//On parcourt la liste des joueur du GameManager afin d enlever une vie au joueur 
		for(Player p : m_gm.getPlayers() )
		{
			if(p.getID() == this.Id)
			{
				p.setVie(p.getVie() - 1);
				//Plus de vie
				if(p.getVie() == 0)
					Id = -1;
			}
		}
		setX(start_x);	//On respawn les coord. x 
		setY(start_y);	//On respawn les coord. y
	}
	
	private void addPoint() {
		//On parcourt la liste des joueurs et on ajoute les points au joueur qui possede le hero selon son ID
		for(Player p : m_gm.getPlayers() )
		{
			if(p.getID() == this.Id)
				p.setPoints(p.getPoints() + 1000);
			setX(start_x);
			setY(start_y);
		}
	}
	
	//Methode ou on fait bouger le hero selon les constantes predefinis
	public void updateMove(int Move) {
		switch(Move) {
			case Const.UP : {	
							
				if(this.getDirection() == Const.RIGHT) {	//On verifie si la direction est droite ou gauche et on change la texture et la direction
					this.setTexture(Sprite.HeroUpR);
				}
				else
				if(this.getDirection() == Const.LEFT) {
					this.setTexture(Sprite.HeroUpL);
				}
					
				if(getY() < Const.Hero_LimitUp) {	//Condition pour ne pas depasser une limite de Coord.
					this.setY(this.y);
				}else
					this.setY(this.y-Const.Size_Height_Object);
				
				this.setDirection(Const.UP);	//On change la direction
				this.FreeFall = true;			//Et le hero est en chute libre
				break;
				
			}
			
			case Const.DOWN : {	//Pratiquement la meme chose que pour Const.UP
				
				if(this.getTexture() == Sprite.HeroUpR) {
					this.setTexture(Sprite.HeroDownR);
				}
				
				if(this.getTexture() == Sprite.HeroUpL) {
					this.setTexture(Sprite.HeroDownL);
				}
				
				if(getY() >= Const.Floor0_y) {
					this.setY(this.y);
				}else
					this.setY(this.y+Const.Size_Height_Object);
				
				this.setDirection(Const.DOWN);
				break;
				
			}
			
			case Const.LEFT : {	//Pratiquement la meme chose que pour Const.RIGHT
				
				this.setTexture(Sprite.HeroLeft);
				
				if(this.getX() < Const.Origine) {
					this.setX(Const.Size_x_Panel-this.getW());
				}else
					this.setX(this.x-Const.Size_Width_Object);
				
				this.setDirection(Const.LEFT);
				break;
				
			}
			
			case Const.RIGHT : {

				this.setTexture(Sprite.HeroRight);
				
				if(this.getX() > Const.Size_x_Panel) {
					this.setX(Const.Origine);
				}else
					this.setX(this.x+Const.Size_Width_Object);
				
				this.setDirection(Const.RIGHT);
				break;
				
			}
			
			case Const.BULLET : {
				
				Bullet();	//On appel la methode Bullet
				break;
				
			}
		}
	}
	
	public synchronized Bullet Bullet() {
		//On verifie la direction du hero afin de faire partir le projectile dans le bon sens 
		if(this.getDirection() == Const.RIGHT || (this.getDirection() == Const.UP && this.getTexture() == Sprite.HeroUpR) || (this.getDirection() == Const.DOWN && this.getTexture() == Sprite.HeroDownR)) {
			this.b = new Bullet(this.getX()+Const.Size_Width_Entity, this.getY()+25, Const.RIGHT, this.m_gm, this);
		}
		if(this.getDirection() == Const.LEFT || (this.getDirection() == Const.UP && this.getTexture() == Sprite.HeroUpL) || (this.getDirection() == Const.DOWN && this.getTexture() == Sprite.HeroDownL)) {
			this.b = new Bullet(this.getX(), this.getY()+25,Const.LEFT, this.m_gm, this);
		}
		return b;
	}
	
}
