package Server_Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSkatServer extends Remote {
	public boolean register(String name) throws RemoteException;
	 

}
