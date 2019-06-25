package Server_Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSkatServer extends Remote {
	public void register(String name,RemoteSkatClient client) throws RemoteException;
	 

}
