package SpielAblauf;
import java.rmi.RemoteException;

import Server_Client.RemoteSkatClient;

public class Reizen{
	private int accWert=0;
	private boolean[] weg= new boolean[3];
	private RemoteSkatClient[] clients;
	
	public Reizen(RemoteSkatClient[] clients) {
		this.clients=clients;
		try {
			clients[0].startReizen();
			clients[1].startReizen();
			clients[2].startReizen();
			clients[0].reizenStartStats();
			clients[1].reizenStartStats();
			clients[2].reizenStartStats();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void weg(int pos) {
		weg[pos]=true;
		System.out.println("weg");
		System.out.println(weg[0]);
		System.out.println(weg[1]);
		System.out.println(weg[2]);
	}

}
