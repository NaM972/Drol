package CPU;

import KERNEL.*;
/*
 * Classe heritant des methodes gameobject sans les utiliser.
 * TrapDoor est une classe representant un objet qui est montable et qui est traversable par le hero.
 */
public class TrapDoor extends GameObject{

	public TrapDoor(int x, int y) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		setW(Const.TrpD_W);
		setH(Const.Size_Height);
		setMountable(true);
		setTexture(Sprite.TrapDoor);
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
