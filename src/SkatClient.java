import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SkatClient extends UnicastRemoteObject implements RemoteSkatClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SkatClient() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		StartActivity sa= new StartActivity();
	}

}
