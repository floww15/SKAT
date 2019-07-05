package Server_Client;

import java.net.MalformedURLException;

import GameClasses.Hand;
import GameClasses.Karte;
import GameClasses.NotYourTurnException;
import GameClasses.Player;
import GameClasses.WrongCardException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import SpielAblauf.*;

public class SkatClient extends UnicastRemoteObject implements RemoteSkatClient {
	CenterClient centerClient;
	RemoteSkatServer skatServer;
	int pos = 0;
	Hand hand;
	Reizen reizen;
	ArrayList<Karte> skat;
	private int punkte = 0;
	private Player player;
	private boolean first = false;

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected SkatClient(CenterClient centerClient) throws RemoteException {
		this.centerClient = centerClient;
	}

	public boolean connect(String ip) {
		boolean worked = true;
		try {
			skatServer = (RemoteSkatServer) Naming.lookup("//" + ip + ":1099/SkatServer");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			worked = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			worked = false;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			worked = false;
		}
		return worked;
	}

	public void register(String name) {

		try {
			skatServer.register(name, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setPos(int pos) throws RemoteException {
		this.pos = pos;
	}

	@Override
	public void startReizen() throws RemoteException {
		hand = skatServer.getHand(pos);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				centerClient.startReizen(pos, hand);

			}
		});
	}

	public void startDruecken() throws RemoteException {
		skatServer.setPlayingAlone(pos);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				centerClient.startDruecken(hand);
				
			}
		});
	}

	@Override
	public void startGamefromReizen() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// GameActivity starten mit Konstruktor
				centerClient.startGamefromReizen();

			}
		});

	}

	public void startGamefromDruecken() throws RemoteException {
		Platform.runLater(new Runnable() {
			public void run() {
				centerClient.startGamefromDruecken();
			}
		});
	}

	public void startGameActivites() {
		try {
			skatServer.startGameActivities();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public Semaphore getSem() throws RemoteException {
//		return skatServer.getSem(pos);
//	}

	public void reizenStartStats() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				try {
					centerClient.reizenStartStats();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int getPos() throws RemoteException {
		// TODO Auto-generated method stub
		return pos;
	}

	public void btnWegClick() {
		try {
			skatServer.weg(pos);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnNextClick() {
		try {
			System.out.println("Client ReizenAcc");
			skatServer.btnNextClick(pos);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Player[] getPlayers() {
		System.out.println("SkatClient");
		for(int i=0; i<3; i++) {
			try {
				System.out.println(skatServer.getPlayers()[i].getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			return skatServer.getPlayers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int i) {
		punkte = i;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player p) throws RemoteException {
		player = p;
	}

	public RemoteSkatClient[] getClients() throws RemoteException {
		return skatServer.getClients();
	}

//	@Override
//	public void changeLGereizt(String value) throws RemoteException {
//		// TODO Auto-generated method stub
//		centerClient.changeLGereizt(value);
//	
//	}
//	public void changeLEmpty(String value) {
//		centerClient.changeLEmpty(value);
//	}
//	
//	public void changeBtnNext(String value) {
//		centerClient.changeBtnNext(value);
//	}

	public void changesReizen(String Nr, String Weg, String Empty, String Gereizt, String Next, String Zustand)
			throws RemoteException {
		centerClient.changesReizen(Nr, Weg, Empty, Gereizt, Next, Zustand);
	}

	public ArrayList<Karte> getSkat() {
		try {
			skat = skatServer.getSkat();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skat;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst() {
		first = true;
	}

	public void setChangesAfterDruecken(String trumpf, Hand hand, ArrayList<Karte> skat, boolean[] addOns) {
		try {
			skatServer.setTrumpf(trumpf);
			skatServer.setHand(pos, hand);
			skatServer.setSkat(skat);
			skatServer.setAddOns(addOns);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPlayingAlone() {
		try {
			return skatServer.getPlayingAlone();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setPlayingAlone( int x) {
		try {
			skatServer.setPlayingAlone(x);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTrumpf() {
		try {
			return skatServer.getTrumpf();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void legKarte(Karte k) throws RemoteException, WrongCardException, NotYourTurnException {
		skatServer.legKarte(pos, k);
		
	}

	public void setCard0Text(String s)throws RemoteException {
		System.out.println("skatclientText0");
		centerClient.setCard0Text(s);
		}
	public void setCard1Text(String s) throws RemoteException{
		centerClient.setCard1Text(s);
	}
	public void setCard2Text(String s)throws RemoteException {
		centerClient.setCard2Text(s);
	}



}
