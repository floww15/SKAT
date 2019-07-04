package SpielAblauf;

import GameClasses.Karte;
import GameClasses.KartenComparator;
import GameClasses.Stich;
import GameClasses.WrongCardException;
import GameClasses.Player;

public class Game {
	private boolean[] gelegt = new boolean[3];
	private Karte[] cards = new Karte[3];
	private Player[] player = new Player[3];
	private int turn =1;
	
	public int getTurn() {
		return turn;
	}
	

	public void karteLegen(int id, Karte k, String trumpf, Player p) throws WrongCardException {
		if(id!=turn)
			return;
		if (!gelegt[id]) {
			cards[id] = k;
			player[id] = p;
			gelegt[id]=true;
		}
		if (gelegt[0] && gelegt[1] && gelegt[2]) {
			Stich s = new Stich(cards[0], cards[1], cards[2]);
			String farbe = cards[turn].getFarbe();
			if (trumpf == null) {
				if (!cards[(turn+1)%3].getFarbe().equals(farbe) && player[(turn+1)%3].getHand().contains(farbe)) {
					throw new WrongCardException();
				}
				if (!cards[(turn+2)%3].getFarbe().equals(farbe) && player[(turn+2)%3].getHand().contains(farbe)) {
					throw new WrongCardException();
				}
			}
			if (trumpf != null && trumpf.equals("Grand")) {
				if (cards[turn].getWert().equals("bube") && cards[(turn+1)%3].getWert().equals("bube")
						&& player[1].getHand().containsBube())
					throw new WrongCardException();
				if (cards[turn].getWert().equals("bube") && cards[(turn+2)%3].getWert().equals("bube")
						&& player[2].getHand().containsBube())
					throw new WrongCardException();
				if (!cards[turn].getWert().equals("bube")) {
					if (!cards[(turn+1)%3].getFarbe().equals(farbe) && player[(turn+1)%3].getHand().containsTrumpfGrand(farbe)) {
						throw new WrongCardException();
					}
					if (!cards[(turn+2)%3].getFarbe().equals(farbe) && player[(turn+2)%3].getHand().containsTrumpfGrand(farbe)) {
						throw new WrongCardException();
					}
				}
			}
			if (trumpf != null && !trumpf.equals("Grand") && trumpf.equals(farbe)) {
				if (!cards[(turn+1)%3].getFarbe().equals(farbe) && player[(turn+1)%3].getHand().containsTrumpf(trumpf))
					throw new WrongCardException();
				if (!cards[(turn+2)%3].getFarbe().equals(farbe) && player[(turn+2)%3].getHand().containsTrumpf(trumpf))
					throw new WrongCardException();
			}
			if (trumpf != null && !trumpf.equals("Grand") && !trumpf.equals(farbe)) {
				if (!cards[(turn+1)%3].getFarbe().equals(farbe) && player[(turn+1)%3].getHand().contains(farbe))
					throw new WrongCardException();
				if (!cards[(turn+2)%3].getFarbe().equals(farbe) && player[(turn+2)%3].getHand().contains(farbe))
					throw new WrongCardException();
			}
			KartenComparator comp = new KartenComparator(trumpf, cards[0].getFarbe());
			if (comp.compare(cards[0], cards[1]) > 0 && comp.compare(cards[0], cards[2]) > 0) {
				player[0].addStich(s);
				turn=0;
			}
			if (comp.compare(cards[1], cards[0]) > 0 && comp.compare(cards[1], cards[2]) > 0) {
				player[1].addStich(s);
				turn=1;
			}
			if (comp.compare(cards[2], cards[1]) > 0 && comp.compare(cards[2], cards[0]) > 0) {
				player[2].addStich(s);
				turn =2;
		}

		}

	}

}
