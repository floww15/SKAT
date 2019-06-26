package Server_Client;
import GameClasses.*;
import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteSkatServer extends Remote {
	public void register(String name,RemoteSkatClient client) throws RemoteException;
	public ArrayList<Karte> getKarten(int pos)throws RemoteException;
	 

}
