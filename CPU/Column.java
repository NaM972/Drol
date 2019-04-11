package CPU;

import KERNEL.*;

/*
 * Classe heritant des methodes gameobject sans les utiliser.
 * Column est une classe representant un objet qui n est pas montable.
 */
public class Column extends GameObject{

	//Constructeur par défaut
	public Column(int x, int y) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		setW(Const.Size_Width);
		setH(Const.Col_H);
		setMountable(false);
		setTexture(Sprite.Colonnes);
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
