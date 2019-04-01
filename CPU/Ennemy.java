package CPU;

public class Ennemy extends GameObject{

  private int start_x, start_y;

  public Ennemy(int start_x, int start_y, int direction){
    setX(start_x);
    setY(start_y);
    setDirection(direction);
    setTexture(Texture.Ennemy1); //setup ennemy1 dans la class Texture
  }

  public void update(){
    if(getDirection() == Const.LEFT && (getX()-35) >= 0){
      setX(getX() - 35);
    }
    else if(getDirection() == Const.LEFT && (getX()-35) < 0){
      setX(Const.Size_x);
    }
    else if(getDirection() == Const.RIGHT && (getX() + 35) <= Const.Size_x){
      setX(getX() + 35);
    }
    else if(getDirection() == Const.RIGHT && (getX() + 35) > Const.Size_x){
      setX(0);
    }
  }//fin update

  

}
