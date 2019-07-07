package Server_Client;

import java.rmi.RemoteException;

import java.util.ArrayList;


import Activities.*;
import GameClasses.*;
import javafx.application.Application;
import javafx.stage.Stage;

//Klasse zum Verwalten der Activities und des SkatClients
//Nötig, da nur ein fx Thread laufen darf
public class CenterClient extends Application {
	boolean alreadyRegistered = false;

	SkatClient client;
	StartActivity startActivity;
	ReizenActivity reizenActivity;
	DrueckenActivity drueckenActivity;
	GameActivity gameActivity;
	AuszaehlungActivity auszaehlungActivity;
	Stage prime;

	public static void main(String... args) {
		launch();
	}

	@Override
	public void start(Stage prime) throws Exception {
		this.prime = prime;
		client = new SkatClient(this);
		startActivity = new StartActivity(prime, this);
	}

	public void registerConnect(String ip, String name) {
//		System.out.println(ip+" "+name);
		if (!alreadyRegistered) {
			client.connect(ip);
			client.register(name);
			alreadyRegistered = true;
		}
	}

	public void startReizen(int pos, Hand karten) {
//		System.out.println(pos);
		reizenActivity = startActivity.startReizen(pos, karten);
	}

	public void startDruecken(Hand hand) {
		drueckenActivity = reizenActivity.startDruecken(hand);
	}
	
	public void startGamefromReizen() {
		gameActivity= reizenActivity.startGamefromReizen();
	}
	
	public void startGamefromDruecken() {
		gameActivity= drueckenActivity.startGamefromDruecken();
	}
	
	
	
	public void startGameActivites() {
		client.startGameActivites();
	}

//	public Semaphore getSem() throws RemoteException {
//		return client.getSem();
//	}

	public void reizenStartStats() throws RemoteException {
		reizenActivity.changeLabelstart();
	}

	public void startAuszaehlung() {
		auszaehlungActivity = gameActivity.startAuszaehlung();
	}

	public SkatClient getClient() {
		return client;
	}

	public void btnWegClick() {
		client.btnWegClick();
	}

	public void btnNextClick() {
		System.out.println("Next Center");
		client.btnNextClick();
	}

//	public void changeLGereizt(String value) {
//		reizenActivity.changeLGereizt(value);
//	}
//	
//	public void changeLEmpty(String value) {
//		reizenActivity.changeLEmpty(value);
//	}
//	
//	public void changeBtnNext(String value) {
//		reizenActivity.changeBtnNext(value);
//	}

	public void changesReizen(String Nr, String Weg, String Empty, String Gereizt, String Next, String Zustand) {
		reizenActivity.changes(Nr, Weg, Empty, Gereizt, Next, Zustand);
	}

	public ArrayList<Karte> getSkat() {
		return client.getSkat();
	}

	public SkatClient getSkatClient() {
		return client;
	}

	public void setChangesAfterDruecken(String trumpf, Hand hand, ArrayList<Karte> skat, boolean[] addOns) {
		client.setChangesAfterDruecken(trumpf, hand, skat, addOns);
	}
	
	public Player[] getPlayers() {
//		System.out.println("centerclient");
//		for(int i=0; i<3; i++) {
//			System.out.println(client.getPlayers()[i].getName());
//		}
		return client.getPlayers();
	}
	
	public String getTrumpf() {
		return client.getTrumpf();
	}
	
	public int getPlayingAlone() {
		return client.getPlayingAlone();
	}
	
	public int getPos() {
		try {
			return client.getPos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 99;
	}
	public void legKarte(Karte k) throws RemoteException, WrongCardException, NotYourTurnException {
		client.legKarte(k);
	}
	public void setCard0Text(String s) {
		gameActivity.CardChanges(s, null, null);
	}
	public void setCard1Text(String s) {
		gameActivity.CardChanges(null, s, null);
	}
	public void setCard2Text(String s) {
		gameActivity.CardChanges(null, null, s);
	}
}
