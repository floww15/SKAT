package SpielAblauf;

import java.rmi.RemoteException;

import GameClasses.Karte;
import GameClasses.KartenComparator;
import GameClasses.NotYourTurnException;
import GameClasses.Stich;
import GameClasses.WrongCardException;
import Server_Client.SkatServer;
import GameClasses.Player;

public class Game {
	private boolean[] gelegt = new boolean[3];
	private Karte[] cards = new Karte[3];
	private Player[] player = new Player[3];
	private int turn = 1;
	public int sum = 0;
	private Karte first = null;
	String farbe = "";
	private SkatServer skatServer;
	
	public Game(SkatServer skatServer){
		this.skatServer=skatServer;
	}

	public int getTurn() {
		return turn;
	}

	public Karte[] getCards() {
		return cards;
	}


	public void legKarte(int id, Karte k, String trumpf) throws WrongCardException, NotYourTurnException, RemoteException {
		if(k==null) {
			throw new NullPointerException();
		}
		if (id != turn)
			throw new NotYourTurnException();
		if (sum == 0) {
			first = k;
			farbe = first.getFarbe();
			
			
		}
		System.out.println(farbe+ " = farbe,"+ trumpf+" =trumpf "+k.getFarbe()+" =farbe");
		if (sum == 1 || sum==2) {
			if (trumpf .equals("Null")) {
				if (!k.getFarbe().equals(farbe) && skatServer.getPlayers()[id].getHand().containsNull(farbe)) {
					throw new WrongCardException();
				}
			}
			if (trumpf.equals("Grand")) {
				if (first.getWert().equals("Bube") && k.getWert().equals("Bube") && skatServer.getPlayers()[id].getHand().containsBube())
					throw new WrongCardException();
				if (!first.getWert().equals("Bube")) {
					if ((!k.getFarbe().equals(farbe) )&&skatServer.getPlayers()[id].getHand().containsTrumpfGrand(farbe)) {
						throw new WrongCardException();
					}
				}
			}

			if (trumpf.equals(farbe)) {
				System.out.println(trumpf+ " "+farbe);
				System.out.println(skatServer.getPlayers()[id].getHand().containsTrumpf(trumpf));
				if (!k.getFarbe().equals(farbe) && !k.getWert().equals("Bube") && skatServer.getPlayers()[id].getHand().containsTrumpf(trumpf))
					throw new WrongCardException();

			}
			if (!trumpf.equals("Null")&& !trumpf.equals("Grand") && !trumpf.equals(farbe)) {
				System.out.println(skatServer.getPlayers()[id].getHand().contains(farbe)+" hier");
				if (!k.getFarbe().equals(farbe) && skatServer.getPlayers()[id].getHand().contains(farbe))
					throw new WrongCardException();

			}
		}
		
		cards[turn] = k;
		player[turn] = skatServer.getPlayers()[id];
		turn = (turn + 1) % 3;
		sum++;
		System.out.println(k);
		System.out.println(skatServer.getPlayers()[id].getHand().remove(k));
		System.out.println(skatServer.getPlayers()[id].getHand().getSize()+" +");
		System.out.println(skatServer.getPlayers()[id].getHand());
		if (sum == 3) {
			sum = 0;
			Stich s = new Stich(cards[0], cards[1], cards[2]);
			gelegt[0] = false;
			gelegt[1] = false;
			gelegt[2] = false;
			sum = 0;
			KartenComparator comp = new KartenComparator(trumpf, first.getFarbe());
			if (comp.compare(cards[0], cards[1]) < 0 && comp.compare(cards[0], cards[2]) < 0) {
				player[0].addStich(s);
				turn = 0;
				return;
			}
			if (comp.compare(cards[1], cards[0]) < 0 && comp.compare(cards[1], cards[2]) < 0) {
				player[1].addStich(s);
				turn = 1;
				return;

			}
			if (comp.compare(cards[2], cards[1]) < 0 && comp.compare(cards[2], cards[0]) < 0) {
				player[2].addStich(s);
				turn = 2;

				return;
			}
		}
	}

}
