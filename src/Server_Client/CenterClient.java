package Server_Client;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

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

//	public Semaphore getSem() throws RemoteException {
//		return client.getSem();
//	}

	public void reizenStartStats() throws RemoteException {
		reizenActivity.changeLabelstart();
	}

	public void startAuszaehlung() {
//		auszaehlungactivity = gameActivity.startAuszaehlung();
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
}
