package Server_Client;

import GameClasses.*;
import SpielAblauf.*;
import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public interface RemoteSkatServer extends Remote {
	public void register(String name, RemoteSkatClient client) throws RemoteException;

	public ArrayList<Karte> getKarten(int pos) throws RemoteException;

//	public Semaphore getSem(int pos) throws RemoteException;
	public void weg(int pos) throws RemoteException;
	public void btnNextClick(int pos) throws RemoteException;
	

}
