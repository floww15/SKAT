package Server_Client;

import java.rmi.Naming;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import GameClasses.Hand;
import GameClasses.Karte;
import GameClasses.KartenStapel;
import GameClasses.Player;
import SpielAblauf.*;

public class SkatServer extends UnicastRemoteObject implements RemoteSkatServer {
	private KartenStapel k = new KartenStapel();
	ArrayList<Hand> haende= new ArrayList<Hand>();
	
	private ArrayList<Karte> skat = new ArrayList<Karte>();
	private RemoteSkatClient[] clients = new RemoteSkatClient[3];
	private Player[] players = new Player[3];
	Reizen reizen;
//	private Semaphore[] semsPlayer = new Semaphore[3];
	int player = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public synchronized void register(String name, RemoteSkatClient client) {
		if (player < 3) {
			clients[player] = client;
			players[player] = new Player(name);
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
					reizen= new Reizen(clients);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player++;
		}
	}
	
	public ArrayList<Karte> getSkat() throws RemoteException{
		return skat;
	}

	private void verteilen() {

		for (int i = 0; i < 3; i++) {
			haende.get(1).add(k.getKarte());
		}
		for (int i = 0; i < 3; i++) {
			haende.get(2).add(k.getKarte());
		}
		for (int i = 0; i < 3; i++) {
			haende.get(3).add(k.getKarte());
		}
		skat.add(k.getKarte());
		skat.add(k.getKarte());
		for (int i = 3; i < 7; i++) {
			haende.get(1).add(k.getKarte());
		}
		for (int i = 3; i < 7; i++) {
			haende.get(2).add(k.getKarte());
		}
		for (int i = 3; i < 7; i++) {
			haende.get(3).add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			haende.get(1).add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			haende.get(2).add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			haende.get(3).add(k.getKarte());
		}
	}

	@Override
	public Hand getKarten(int pos) throws RemoteException {
		// TODO Auto-generated method stub
		return haende.get(pos);
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
	
//	public Semaphore getSem(int pos) throws RemoteException {
//		return semsPlayer[pos];
//	}


}
