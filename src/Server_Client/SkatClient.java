package Server_Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class SkatClient extends UnicastRemoteObject implements RemoteSkatClient {
	CenterClient centerClient;
	RemoteSkatServer skatServer;
	int pos=0;
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected SkatClient(CenterClient centerClient) throws RemoteException {
		this.centerClient = centerClient;
	}

	public boolean connect(String ip) {
		boolean worked = true;
		try {
			skatServer = (RemoteSkatServer) Naming.lookup("//" + ip + ":1099/SkatServer");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			worked = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			worked = false;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			worked = false;
		}
		return worked;
	}

	public void register(String name) {

		try {
			skatServer.register(name, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setPos(int pos) throws RemoteException {
		this.pos = pos;
	}

	@Override
	public void startReizen() throws RemoteException {
		centerClient.startReizen(pos, skatServer.getKarten(pos));

	}
	
	public Semaphore getSem() throws RemoteException {
		return skatServer.getSem(pos);
	}
	
	public void reizenStartStats() throws RemoteException {
		centerClient.reizenStartStats();
	}

	@Override
	public int getPos() throws RemoteException {
		// TODO Auto-generated method stub
		return pos;
	}

}
