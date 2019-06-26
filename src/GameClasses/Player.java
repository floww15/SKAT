package GameClasses;

import java.io.Serializable;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Player(String name){
		this.name=name;
	}
}
