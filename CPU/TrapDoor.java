package CPU;

import KERNEL.*;

public class TrapDoor extends GameObject{

	public TrapDoor(int x, int y) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		setW(Const.TrpD_W);
		setH(Const.Size_Height);
		setMountable(true);
		setTexture(Texture.TrapDoor);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
