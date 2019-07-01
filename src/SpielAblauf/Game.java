package SpielAblauf;

import GameClasses.Karte;
import GameClasses.KartenComparator;
import GameClasses.Stich;
import GameClasses.Player;

public class Game {
	private boolean[] gelegt=new boolean[3];
	private Karte[] cards=new Karte[3];
	private Player[] player=new Player[3];
 	
	
	public void karteLegen(int id,Karte k,String trumpf,Player p) {
		if(!gelegt[id]) {
			cards[id]=k;
			player[id]=p;
		}
		if(gelegt[0]&&gelegt[1]&& gelegt[2]) {
			Stich s=new Stich(cards[0],cards[1],cards[2]);
			KartenComparator comp=new KartenComparator(trumpf,cards[0].getFarbe());
			if(comp.compare(cards[0], cards[1])>0&&comp.compare(cards[0], cards[2])>0)
				player[0].addStich(s);
			if(comp.compare(cards[1], cards[0])>0&&comp.compare(cards[1], cards[2])>0)
				player[1].addStich(s);
			if(comp.compare(cards[2], cards[1])>0&&comp.compare(cards[2], cards[0])>0)
				player[2].addStich(s);
			
		}
		
	}

}
