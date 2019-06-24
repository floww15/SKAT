

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SkatServer extends UnicastRemoteObject implements RemoteSkatServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SkatServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
