import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SkatClient extends UnicastRemoteObject implements RemoteSkatClient {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;

	protected SkatClient() throws RemoteException {
		
	}

	public static void main(String[] args) {
		
		
		
	}

}
