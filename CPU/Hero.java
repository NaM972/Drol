package CPU;

import KERNEL.*;
import CPU.*;

public class Hero extends GameObject {
	
	private GameManager m_gm;
	private int id;
	private int start_x;
	private int start_y;
	private CPU.Bullet b;
	private int BULLET;
	
	public Hero(int Start_x, int Start_y, int id, boolean aggressive, GameManager m_gm) {
		// TODO Auto-generated constructor stub
		setX(Start_x);
		setY(Start_y);
		
		start_x = x;
		start_y = y;
		
		setW(Const.Size_Width);
		setH(Const.Size_Height);
		setAggressive(aggressive);
		setMountable(false);
		this.m_gm = m_gm;
		this.id = id;
		setTexture(Texture.HeroRight);	
		BULLET = 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		boolean auMoinsUneColli = false;
		for(GameObject go : m_gm.getGameObjects() )
		{
			
			//Collision (si ce n'est pas lui et utiliser l'algo)
			if(go != this && collidedWith(go))
			{
				auMoinsUneColli = true;
				//Agressif
				if(go.isAggressive() )
					respawn();
				//Montable
				if(go.isMountable() == true) 
				{
					//Plateforme
					if(go.getClass() == Plateforme.class)
					{
						if(this.getY() <= go.getY())
							this.setY(go.getY()-35);
						if(this.direction == Const.UP)
							this.setY(go.getY()+35);
					}
				}
			}
		}
	}
	
	private void respawn()
	{
		for(Player p : m_gm.getPlayers() )
		{
			if(p.getID() == this.id)
			{
				p.setVie(p.getVie() - 1);
				//Plus de vie
				if(p.getVie() == 0)
					id = -1;
			}
		}
		setX(start_x);
		setY(start_y);
	}

	public int getId()
	{
		return this.id;
	}
	
	private void addPoint()
	{
		for(Player p : m_gm.getPlayers() )
		{
			if(p.getID() == this.id)
				p.setPoints(p.getPoints() + 10);
			setX(start_x);
			setY(start_y);
		}
	}
	
	public void updateMove(int Move) {
		switch(Move) {
		case Const.UP :
			{
				setTexture(Texture.HeroUp);
				if(getY() <= 0) {
					setY(this.y);
				}
				else
				setY(this.y-35);
				setDirection(Const.UP);
				break;
			}
			
		case Const.DOWN :
			{
				setTexture(Texture.HeroDown);
				if(getY() >= 700) {
					setY(this.y);
				}
				else
					setY(this.y + 35);
				setDirection(Const.DOWN);
				break;
			}
			
		case Const.LEFT :
			{
				setTexture(Texture.HeroLeft);
				if(getX() < 0) {
					setX(1925-35);
				}
				else
				setX(this.x - 35);
				setDirection(Const.LEFT);
				break;
			}
			
		case Const.RIGHT :
			{
				setTexture(Texture.HeroRight);
				if(getX() > 1960) {
					setX(0);
				}
				else
				setX(this.x + 35);
				setDirection(Const.RIGHT);
				break;	
			}
		}
	}

	public Bullet Bullet() {
		if(BULLET != 5) {
		if(getDirection() == Const.RIGHT)
			b = new Bullet(this.x+35, this.y+10, this.direction, m_gm);
		if(getDirection() == Const.LEFT)
			b = new Bullet(this.x, this.y+10, this.direction, m_gm);
		BULLET++;
		}
		else
			BULLET = 0;
		return b;
	}
	
}
