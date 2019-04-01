package CPU;

import KERNEL.Const;
import KERNEL.Texture;

public class Bullet extends GameObject{
	
	private GameManager m_gm;

	public Bullet(int x, int y, int direction, GameManager m_gm) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		
		setW(Const.Bullet_w);
		setH(Const.Bullet_h);
		setAggressive(false);
		
		setTexture(Texture.Bullet);
		setDirection(direction);
		
		this.m_gm = m_gm;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(direction == Const.LEFT) {
			if(getX() <= 0) {
				setX(-10);
			}
			else
			setX(this.x - 5);
		}
		
		if(direction == Const.RIGHT) {
			if(getX() > 1925) {
				setX(1926);
			}
			else
			setX(this.x + 5);
		}
		
	}

}
