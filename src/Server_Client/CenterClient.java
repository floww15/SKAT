package Server_Client;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Activities.*;
import GameClasses.*;
import javafx.application.Application;
import javafx.stage.Stage;

//Klasse zum Verwalten der Activities und des SkatClients
//N�tig, da nur ein fx Thread laufen darf
public class CenterClient extends Application {
	boolean alreadyRegistered = false;

	SkatClient client;
	StartActivity startActivity;
	ReizenActivity reizenActivity;
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

	public void startReizen(int pos, ArrayList<Karte> karten) {
//		System.out.println(pos);
		reizenActivity=startActivity.startReizen(pos, karten);
	}
	
//	public Semaphore getSem() throws RemoteException {
//		return client.getSem();
//	}
	
	public void reizenStartStats() throws RemoteException {
		reizenActivity.changeLabelstart();
	}
	
	public void btnWegClick() {
		client.btnWegClick();
	}

}
