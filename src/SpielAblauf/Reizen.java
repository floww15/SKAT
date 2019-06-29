package SpielAblauf;
import java.io.Serializable;
import java.rmi.RemoteException;

import Server_Client.RemoteSkatClient;

public class Reizen implements Serializable{
	private int accWert;
	private boolean[] weg= new boolean[3];
	private RemoteSkatClient[] clients;
	
	public Reizen(RemoteSkatClient[] clients) {
		this.clients=clients;
		try {
			clients[0].startReizen(this);
			clients[1].startReizen(this);
			clients[2].startReizen(this);
			clients[0].reizenStartStats();
			clients[1].reizenStartStats();
			clients[2].reizenStartStats();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
