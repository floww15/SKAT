package Server_Client;

import GameClasses.*;
import SpielAblauf.*;
import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public interface RemoteSkatServer extends Remote {
	public void register(String name, RemoteSkatClient client) throws RemoteException;

	

//	public Semaphore getSem(int pos) throws RemoteException;
	public void weg(int pos) throws RemoteException;
	public void btnNextClick(int pos) throws RemoteException;
	public ArrayList<Karte> getSkat() throws RemoteException;
	public Player[] getPlayers() throws RemoteException;
	public RemoteSkatClient[] getClients() throws RemoteException;
	
	public void setHand(int pos, Hand hand) throws RemoteException;
	public Hand getHand(int pos) throws RemoteException;
	
	public void setTrumpf(String trumpf) throws RemoteException;
	public void setSkat(ArrayList<Karte> skat) throws RemoteException;
	public void setAddOns(boolean[] addOns) throws RemoteException;
	public void startGameActivities() throws RemoteException;
	public void putCard(Player player, Karte karte) throws RemoteException;
	public int getPlayingAlone() throws RemoteException;
	public void setPlayingAlone(int x) throws RemoteException;
	public String getTrumpf() throws RemoteException;
}
