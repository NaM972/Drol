package CPU;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject
{
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected int direction;
	protected BufferedImage texture;
	
	protected boolean aggressive;
	protected boolean mountable;
	protected boolean continue1;
	
	//Update
	public abstract void update();
	
	//Setter and Getter
	public int getX() {return x;}
	public int getY() {return y;}
	public int getW() {return w;}
	public int getH() {return h;}
	public BufferedImage getTexture() {return texture;}
	public boolean isAggressive() {return aggressive;}
	public boolean isMountable() {return mountable;}
	public int getDirection() {return direction;}
	public boolean isContinue() {return continue1;}
	
	public void setX(int i) {this.x = i;}
	public void setY(int i) {this.y = i;}
	public void setW(int i) {this.w = i;}
	public void setH(int i) {this.h = i;}
	public void setTexture(BufferedImage tex) {this.texture = tex;}
	public void setAggressive(boolean aggressive) {this.aggressive = aggressive;}
	public void setMountable(boolean mountable) {this.mountable = mountable;}
	public void setDirection(int direction) {this.direction = direction;}
	public void setContinue(boolean continue1) {this.continue1 = continue1;}
	
	
	//Algorithme de Collision
	public boolean collidedWith(GameObject go)
	{
		return intervalleAvecValeurCommun(this.x, this.x + this.w, go.x, go.x + go.w) && intervalleAvecValeurCommun(this.y, this.y + this.h, go.y, go.y + go.h);
	}
	
	private boolean intervalleAvecValeurCommun(int a, int b, int f, int g)
	{
		boolean cas1 = a <= f && b >= f && b <= g;
		boolean cas2 = f <= a && g >= a && g <= b;
		boolean cas3 = a <= f && g <= b;
		boolean cas4 = f <= a && b <= g;
		
		return cas1 || cas2 || cas3 || cas4;
	}
	
	//

}
