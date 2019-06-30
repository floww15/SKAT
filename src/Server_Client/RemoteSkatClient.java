package Server_Client;

import SpielAblauf.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSkatClient extends Remote{
	public void setPos(int pos) throws RemoteException;
	public int getPos() throws RemoteException;
	public void startReizen() throws RemoteException;
	public void startDruecken() throws RemoteException;
	public void reizenStartStats() throws RemoteException;
	
//	public void changeLGereizt(String value) throws RemoteException;
//	public void changeLEmpty(String value) throws RemoteException;
//	public void changeBtnNext(String value) throws RemoteException;
	public void changes(String Nr, String Weg, String Empty, String Gereizt, String Next, String Zustand) throws RemoteException;

}
