package CPU;

import java.awt.image.BufferedImage;

public abstract class GameObject {

		// TODO Auto-generated constructor stub
		
		//Variables de Chaque GameObject
		protected int x , y;				//Coordonnees
		protected int width , height;		//Largeur et Hauteur
		protected int direction;			//Direction pour le Hero et les ennemies
		protected int Id;					//Numéro d identification des Heros
		protected BufferedImage texture;	//Texture des GameObject
		
		//Boolean utiles pour l Algorithme de Collision entre les GameObject
		protected boolean aggressive;		
		protected boolean mountable;
		
		//Update et Respawn des methodes abstraites utilisees par les fils de cette classe
		public abstract void update();
		public abstract void respawn();
		
		//Setter and Getter
		public int getX() {return x;}
		public int getY() {return y;}
		public int getW() {return width;}
		public int getH() {return height;}
		public BufferedImage getTexture() {return texture;}
		public boolean isAggressive() {return aggressive;}
		public boolean isMountable() {return mountable;}
		public int getDirection() {return direction;}
		public int getID() {return Id;}
		
		public void setX(int i) {this.x = i;}
		public void setY(int i) {this.y = i;}
		public void setW(int i) {this.width = i;}
		public void setH(int i) {this.height = i;}
		public void setTexture(BufferedImage tex) {this.texture = tex;}
		public void setAggressive(boolean aggressive) {this.aggressive = aggressive;}
		public void setMountable(boolean mountable) {this.mountable = mountable;}
		public void setDirection(int direction) {this.direction = direction;}
		public void setID(int id) {this.Id = id;}
		
		//Algorithme de Collision
		public boolean collidedWith(GameObject go)
		{
			return intervalleAvecValeurCommun(this.x, this.x + this.width, go.x, go.x + go.width) && intervalleAvecValeurCommun(this.y, this.y + this.height, go.y, go.y + go.height);
		}
		
		private boolean intervalleAvecValeurCommun(int a, int b, int f, int g)
		{
			boolean cas1 = a <= f && b >= f && b <= g;
			boolean cas2 = f <= a && g >= a && g <= b;
			boolean cas3 = a <= f && g <= b;
			boolean cas4 = f <= a && b <= g;
			
			return cas1 || cas2 || cas3 || cas4;
		}

}
