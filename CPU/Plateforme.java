package CPU;

import KERNEL.*;

public class Plateforme extends GameObject{

	public Plateforme(int x, int y, int plat) {
		setX(x);
		setY(y);
		setH(Const.Size_Height);
		setMountable(true);
		setAggressive(false);
		initPlat(plat);
	}
	
	public void initPlat(int plat) {
		switch(plat) {
			case Const.TypePlat0:{
				setW(Const.Plat0_W);
				setTexture(Texture.Plat0);
				break;
			}
			case Const.TypePlat1:{
				setW(Const.Plat1_W);
				setTexture(Texture.Plat1);
				break;
			}
			case Const.TypePlat2:{
				setW(Const.Plat2_W);
				setTexture(Texture.Plat2);
				break;
			}
			case Const.TypePlat3:{
				setW(Const.Plat3_W);
				setTexture(Texture.Plat3);
				break;
			}
			case Const.TypePlat4:{
				setW(Const.Plat4_W);
				setTexture(Texture.Plat4);
				break;
			}
			case Const.TypePlat5:{
				setW(Const.Plat5_W);
				setTexture(Texture.Plat5);
				break;
			}
			case Const.TypePlat6:{
				setW(Const.Plat6_W);
				setTexture(Texture.Plat6);
				break;
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
