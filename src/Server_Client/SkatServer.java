package Server_Client;

import Activities.*;
import GameClasses.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import GameClasses.Karte;
import GameClasses.KartenStapel;
import GameClasses.Player;

public class SkatServer extends UnicastRemoteObject implements RemoteSkatServer {
	private KartenStapel k = new KartenStapel();
	private ArrayList<Karte> p1 = new ArrayList<Karte>();
	private ArrayList<Karte> p2 = new ArrayList<Karte>();
	private ArrayList<Karte> p3 = new ArrayList<Karte>();
	private ArrayList<Karte> skat = new ArrayList<Karte>();
	private RemoteSkatClient[] clients = new RemoteSkatClient[3];
	private Player[] players = new Player[3];
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

	public void register(String name, RemoteSkatClient client) {
		if (player < 3) {
			clients[player] = client;
			players[player] = new Player(name);
			try {
				client.setPos(player);
				if (player == 2) {// starten der Reizen Activity
					clients[0].startReizen();
					clients[1].startReizen();
					clients[2].startReizen();
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player++;
			System.out.println(name);

		}

	}

	private void verteilen() {
		for (int i = 0; i < 3; i++) {
			p1.add(k.getKarte());
		}
		for (int i = 0; i < 3; i++) {
			p2.add(k.getKarte());
		}
		for (int i = 0; i < 3; i++) {
			p3.add(k.getKarte());
		}
		skat.add(k.getKarte());
		skat.add(k.getKarte());
		for (int i = 3; i < 7; i++) {
			p1.add(k.getKarte());
		}
		for (int i = 3; i < 7; i++) {
			p2.add(k.getKarte());
		}
		for (int i = 3; i < 7; i++) {
			p3.add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			p1.add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			p2.add(k.getKarte());
		}
		for (int i = 7; i < 10; i++) {
			p3.add(k.getKarte());
		}
	}

	@Override
	public ArrayList<Karte> getKarten(int pos) throws RemoteException {
		// TODO Auto-generated method stub
		switch (pos) {
		case 0:
			return p1;
		case 1:
			return p2;
		case 2:
			return p3;
		}
		return null;
	}

}