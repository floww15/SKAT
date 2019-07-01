package GameClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<Stich> stiche=new ArrayList<Stich>();
	private Hand h;
	
	public Player(String name, Hand hand){
		this.name=name;
		h=hand;
	}
	public void addStich(Stich s) {
		stiche.add(s);
	}
	public List<Stich> getStiche(){
		return stiche;
	}
	public String getName() {
		return name;
	}
}
