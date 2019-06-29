package Server_Client;

import SpielAblauf.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSkatClient extends Remote{
	public void setPos(int pos) throws RemoteException;
	public int getPos() throws RemoteException;
	public void startReizen(Reizen reizen) throws RemoteException;
	public void reizenStartStats() throws RemoteException;

}
