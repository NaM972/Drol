package CPU;

import KERNEL.*;

/*
 * Classe representant les projectiles des heros. 
 * Elle se rajoute dans la liste des gameobject du GameManager afin 
 * de pouvoir l update dans le GamePanel. Elle permet aux heros de marquer
 * des points pour les joueurs.
 */
public class Bullet extends GameObject{
	
	private Hero h;	//Le hero possedant le projectile
	
	//On recupere la liste des GameObject initialisee dans le GameManager
	private GameManager m_gm;
	private int bulletLimit1;	//Limite du bullet en x a droite
	private int bulletLimit2;	//Limite du bullet en x a gauche
	
	//Constructeur par défaut
	public Bullet(int x, int y, int direction, GameManager m_gm, Hero h) {
		super();
		this.setX(x);
		this.setY(y);
		this.bulletLimit1 = x+Const.Bullet_Limit;
		this.bulletLimit2 = x-Const.Bullet_Limit;		
		this.setW(Const.Bullet_Width);
		this.setH(Const.Bullet_Height);
		this.setDirection(direction);
		this.m_gm = m_gm;
		this.h = h;
		this.setAggressive(true);
		this.setMountable(false);
		this.setTexture(Sprite.Bullet);
		m_gm.addGameObject(this);	//On ajoute l objet bullet a la liste des GameObject
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(this.getDirection() == Const.LEFT) {	//Condition pour l animation
			if(this.getX() < this.bulletLimit2) {	//Si le bullet depasse la limite
				this.setX(-800);	//On le print loin sans modification 
			}else
				this.setX(this.x - Const.Bullet_Speed);	//Le projectile avance
		}
		if(this.getDirection() == Const.RIGHT) {	//Condition pour l animation
			if(this.getX() > this.bulletLimit1) {	//Si le bullet depasse la limite
				this.setX(2500);	//On le print loin sans modification 
			}else
				this.setX(this.x + Const.Bullet_Speed);	//Le projectile avance
		}
		
		//On parcourt la liste de GameObject du GameManager
		for(GameObject go : m_gm.getGameObjects()){
			
			//Collision (si ce n est pas lui et utiliser l algorithme de collision dans classe parent GameObject)
			if(go != this && collidedWith(go)) {
				//Si le go est agressif
				if(go.isAggressive()) {
					//Si le go est une classe Ennemy 
					if(go.getClass() == Ennemy.class) {
						go.respawn();	//Appel a la fonction heritee 
						addPoint();		//On ajoute des points a son hero selon un index
						this.respawn();	//Appel a la methode respawn qui fait disparaitre de la fenetre
					}
					//Si le go est un autre hero agressif
					if(go.getClass() == Hero.class) {
						if(this.h.getID() != go.getID()) {
							go.respawn();	//Appel a la fonction heritee 
							addPoint();		//On ajoute des points a son hero selon un index
							this.respawn();	//Appel a la methode respawn qui fait disparaitre de la fenetre
						}
					}
				}
			}
		}
	}
	
	private void addPoint() {
		//On parcourt la liste des joueurs et on ajoute les points au joueur qui possede le hero selon son ID
		for(Player p : m_gm.getPlayers()) {
			if(p.getID() == this.h.getID())
				p.setPoints(p.getPoints() + 10);
		}
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		if(this.getDirection() == Const.LEFT) {
			this.setX(-800);
		}
		if(this.getDirection() == Const.RIGHT) {
			this.setX(2500);
		}
	}

}
