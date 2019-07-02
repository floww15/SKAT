package Server_Client;

import java.net.MalformedURLException;

import GameClasses.Hand;
import GameClasses.Karte;
import GameClasses.Player;

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
		hand = skatServer.getKarten(pos);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				centerClient.startReizen(pos, hand);

			}
		});
	}

	public void startDruecken() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				centerClient.startDruecken(hand);

			}
		});
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
	public void setPlayer(Player p) throws RemoteException{
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

	public void changes(String Nr, String Weg, String Empty, String Gereizt, String Next, String Zustand)
			throws RemoteException {
		centerClient.changes(Nr, Weg, Empty, Gereizt, Next, Zustand);
	}
	
	public ArrayList<Karte> getSkat(){
		try {
			skat= skatServer.getSkat();
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
	
}
