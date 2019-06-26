package Server_Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSkatClient extends Remote{
	public void setPos(int pos) throws RemoteException;
	public void startReizen() throws RemoteException;

}
