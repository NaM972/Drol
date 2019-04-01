package CPU;

import KERNEL.*;

public class Colonne extends GameObject{

	public Colonne(int x, int y) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		setW(Const.Size_Width);
		setH(Const.Col_H);
		
		setMountable(false);
		setTexture(Texture.Colonnes);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
