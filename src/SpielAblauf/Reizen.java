package SpielAblauf;

import java.rmi.RemoteException;

import Server_Client.RemoteSkatClient;

public class Reizen {
	private int accWert = 0;
	private boolean[] weg = new boolean[3];
	private boolean ersteRunde = true;
	private boolean sagen = true;
	private RemoteSkatClient[] clients;
	private int[] werte = new int[] {0, 18, 20, 22, 23, 24, 27, 30, 33, 35, 36, 40, 44, 45, 46, 48, 50, 54, 55, 59, 60,
			63, 66, 70, 72, 77, 80, 81, 84, 88, 90, 96, 99, 100, 108, 110, 117, 120, 121, 126, 130, 132, 135, 140, 143,
			144, 153, 156, 160, 162, 165, 168, 170, 176, 180, 187, 192, 198, 204, 216, 240, 264 };

	public Reizen(RemoteSkatClient[] clients) {
		this.clients = clients;
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
		if (ersteRunde && pos == 0)
			return;
		weg[pos] = true;
		System.out.println("weg");
		System.out.println(weg[0]);
		System.out.println(weg[1]);
		System.out.println(weg[2]);
	}

	public void nextClick(int pos) {
		if (sagen && ersteRunde && pos==2) {
			accWert++;
			//Anzeige bei allen Spielern ändern.
			try {
//				clients[0].changeLGereizt(""+werte[accWert]);
//				clients[1].changeLGereizt(""+werte[accWert]);
//				clients[2].changeLGereizt(""+werte[accWert]);
//				clients[2].changeBtnNext(""+werte[accWert+1]);
				clients[0].changes(null, null, null, "gereizt bis "+werte[accWert], null, null);
				clients[1].changes(null, null, null, "gereizt bis "+werte[accWert], null, null);
				clients[2].changes(null, null, "", "gereizt bis "+werte[accWert], ""+werte[accWert+1], null);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sagen=false;
			return;
		}
		if(!sagen && ersteRunde && pos==1) {
			try {
//				clients[2].changeLEmpty("Ja von Sp2");
				clients[2].changes(null, null, "Ja von Sp2", null, null, null);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pos2 uebermitteln, dass weiter gereizt werden soll
			sagen=true;
			return;
		}
		
	}

}
