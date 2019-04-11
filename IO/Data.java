package IO;

import java.util.ArrayList;

import CPU.*;
import KERNEL.*;

/*
 * Classe Data qui gere les donnees envoyes entre client et serveur.
 * Cette classe permet le multijoueur.
 */
public class Data{
	//Variable
	private int m_type;
	private int m_player;
	private int m_op;
	private String m_s;
	
	//Constructeur
	public Data(int type, int player, int op, String s){
		m_type = type;
		m_player = player;
		m_op = op;
		m_s = s;
	}
	
	//Clear
	public void clear(){
		m_type = Const.DT_NULL;
		m_player = Const.DT_NULL;
		m_op = Const.DT_NULL;
		m_s = "";
	}
	
	//Conversion en String
	public String convertToString(){
		return Const.DT_START + m_type + Const.DT_SEPAR + m_player + Const.DT_SEPAR + m_op + Const.DT_SEPAR + m_s;
	}
	
	//Conversion String en instance de DATA (donnees)
	public static ArrayList<Data> convertStringToDataList(String s){
		ArrayList<Data> data = new ArrayList<Data>();
		
		if(!s.equals("")){
			String[] stringData = s.split(Const.DT_START);
			for(String s2 : stringData){
				if(!s2.equals("")){
					String[] info = s2.split(Const.DT_SEPAR);
					if(info.length == 3){
						Data d = new Data(Integer.parseInt(info[0]), Integer.parseInt(info[1]) ,Integer.parseInt(info[2]) ,"");
						data.add(d);
					}
					else if(info.length == 4){
						Data d = new Data(Integer.parseInt(info[0]), Integer.parseInt(info[1]) ,Integer.parseInt(info[2]) ,info[3] );
						data.add(d);
					}
				}
			}
		}
		return data;
	}
	
	//Conversion ArrayList en String
	public static String convertListToString(ArrayList<Data> data){
		String res = "";
		
		for(Data d : data)
			res += d.convertToString();
		
		return res;
	}
	
	//Conversion ArrayList en GameObject
	public static ArrayList<GameObject> convertListToGameObjects(ArrayList<Data> data){
		//Liste
		ArrayList<GameObject> listGo = new ArrayList<GameObject>();

		//Return
		return listGo;
	}
	
	//Getter
	public int getType() { return m_type; };
	public int getPlayer() { return m_player; };
	public int getOperation() { return m_op; };
	public String getS() { return m_s; };
	
	//Setter
	public void setType(int i) { m_type = i; }
	public void setPlayer(int i) { m_player = i; }
	public void setOperation(int i) { m_op = i; }
	public void setS(String s) { m_s = s; }

}
