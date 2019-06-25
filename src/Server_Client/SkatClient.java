package Server_Client;

import Activities.*;
import GameClasses.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SkatClient extends UnicastRemoteObject implements RemoteSkatClient {
	RemoteSkatServer skatServer;
	int pos;
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected SkatClient() throws RemoteException {

	}

	public boolean connect(String ip) {
		boolean worked=true;
		try {
			skatServer= (RemoteSkatServer) Naming.lookup("//"+ip+":1099/SkatServer");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			worked=false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			worked=false;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			worked=false;
		}
		return worked;
	}

	public boolean register(String name) {
		boolean worked = false;
		try {
			worked = skatServer.register(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return worked;
	}

}
