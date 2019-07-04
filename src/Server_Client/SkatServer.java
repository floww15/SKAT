package Server_Client;

import java.rmi.Naming;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.sun.jndi.cosnaming.RemoteToCorba;

import GameClasses.Hand;
import GameClasses.Karte;
import GameClasses.KartenStapel;
import GameClasses.NotYourTurnException;
import GameClasses.Player;
import GameClasses.WrongCardException;
import SpielAblauf.*;

public class SkatServer extends UnicastRemoteObject implements RemoteSkatServer {
	private KartenStapel k = new KartenStapel();
	ArrayList<Hand> haende = new ArrayList<Hand>();

	private ArrayList<Karte> skat = new ArrayList<Karte>();
	private RemoteSkatClient[] clients = new RemoteSkatClient[3];
	private Player[] players = new Player[3];
	Reizen reizen;
	private int playingAlone;
	Game game = new Game();
//	private Semaphore[] semsPlayer = new Semaphore[3];
	int player = 0;

	private String trumpf;
	boolean addOns[] = new boolean[4];

	public Game getGame() throws RemoteException {
		return game;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// commit it

	protected SkatServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		try {
			Naming.rebind("SkatServer", this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		haende.add(new Hand());
		haende.add(new Hand());
		haende.add(new Hand());
		verteilen();
	}
	// das ist ein test kommentar

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT); // Port binden
			Registry registry = LocateRegistry.getRegistry();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			SkatServer skatServer = new SkatServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void register(String name, RemoteSkatClient client) throws RemoteException {
		if (player < 3) {
			clients[player] = client;
			players[player] = new Player(name, haende.get(player), player);
			clients[player].setPlayer(players[player]);
			System.out.println(name);
			try {
				client.setPos(player);
				if (player == 2) {// starten der Reizen Activity

//					clients[0].startReizen();
//					clients[1].startReizen();
//					clients[2].startReizen();
//					clients[0].reizenStartStats();
//					clients[1].reizenStartStats();
//					clients[2].reizenStartStats();
					reizen = new Reizen(clients);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player++;
		}
	}

	public ArrayList<Karte> getSkat() throws RemoteException {
		return skat;
	}

	private void verteilen() {

		for (int i = 0; i < 3; i++) {
			haende.get(0).add(k.getKarte());
		}
		for (int i = 0; i < 3; i++) {
			haende.get(1).add(k.getKarte());
		}
		for (int i = 0; i < 3; i++) {
			haende.get(2).add(k.getKarte());
		}
		skat.add(k.getKarte());
		skat.add(k.getKarte());
		for (int i = 3; i < 7; i++) {
			haende.get(0).add(k.getKarte());
		}
		for (int i = 3; i < 7; i++) {
			haende.get(1).add(k.getKarte());
		}
		for (int i = 3; i < 7; i++) {
			haende.get(2).add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			haende.get(0).add(k.getKarte());
		}
		for (int j = 7; j < 10; j++) {
			haende.get(1).add(k.getKarte());
		}
		for (int x = 7; x < 10; x++) {
			haende.get(2).add(k.getKarte());
		}

	}

	@Override
	public void weg(int pos) throws RemoteException {
		// TODO Auto-generated method stub
		reizen.weg(pos);
	}

	@Override
	public void btnNextClick(int pos) throws RemoteException {
		// TODO Auto-generated method stub
		reizen.nextClick(pos);

	}

	@Override
	public RemoteSkatClient[] getClients() {
		return clients;
	}

	@Override
	public Player[] getPlayers() throws RemoteException {
		System.out.println("SkatServer");
		for (int i = 0; i < 3; i++) {
			System.out.println(players[i].getName());
		}
		// TODO Auto-generated method stub
		return players;
	}

	@Override
	public Hand getHand(int pos) throws RemoteException {
		// TODO Auto-generated method stub
		return haende.get(pos);
	}

	@Override
	public void setHand(int pos, Hand hand) throws RemoteException {
		// TODO Auto-generated method stub
		haende.set(pos, hand);
		players[pos].setHand(hand);
		System.out.println("hand1:");
		System.out.println(haende.get(0));
		System.out.println("hand2:");
		System.out.println(haende.get(1));
		System.out.println("hand3:");
		System.out.println(haende.get(2));

	}

	public void setTrumpf(String trumpf) {
		this.trumpf = trumpf;
		System.out.println(trumpf);
	}

	public void setSkat(ArrayList<Karte> skat) {
		this.skat = skat;
		System.out.println(skat.get(0) + "   " + skat.get(1));
	}

	public void setAddOns(boolean[] addOns) {
		this.addOns = addOns;
		for (int i = 0; i < 4; i++) {
			System.out.println(this.addOns[i]);
		}
	}

	@Override
	public void startGameActivities() throws RemoteException {
		// TODO Auto-generated method stub
		clients[0].startGamefromReizen();
		clients[1].startGamefromReizen();
		clients[2].startGamefromReizen();
	}

	@Override
	public void putCard(Player player, Karte karte) throws RemoteException {
		// TODO Auto-generated method stub
//		
//		if(player.getPos()) {
//			
//		}
	}

//	public Semaphore getSem(int pos) throws RemoteException {
//		return semsPlayer[pos];
//	}

	public int getPlayingAlone() {
		return playingAlone;
	}

	public void setPlayingAlone(int x) {
		playingAlone = x;
	}

	@Override
	public String getTrumpf() throws RemoteException {
		// TODO Auto-generated method stub
		return trumpf;
	}

	@Override
	public void legKarte(int id, Karte k) throws RemoteException, NotYourTurnException, WrongCardException {

		game.legKarte(id, k, trumpf, players[id]);
		System.out.println(game.sum);

	}
}
