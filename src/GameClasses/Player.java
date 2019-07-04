package GameClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<Stich> stiche=new ArrayList<Stich>();
	private Hand h;
	private int pos;
	
	public Player(String name, Hand hand, int pos){
		this.name=name;
		h=hand;
		this.pos=pos;
	}
	public void addStich(Stich s) {
		stiche.add(s);
		System.out.println(s+"  added to "+pos);
	}
	public List<Stich> getStiche(){
		return stiche;
	}
	public String getName() {
		return name;
	}
	public Hand getHand() {
		return h;
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setHand(Hand h) {
		this.h=h;
	}
}
