import javafx.application.Application;
import javafx.stage.Stage;

//Klasse zum Verwalten der Activities und des SkatClients
//Nötig, da nur ein fx Thread laufen darf
public class CenterClient extends Application {
	boolean alreadyRegistered = false;

	SkatClient client;
	StartActivity startActivity;

	@Override
	public void start(Stage prime) throws Exception {
		client = new SkatClient();
		startActivity = new StartActivity(prime, this);

	}

	public static void main(String... args) {
		launch();
	}

	public void registerConnect(String ip, String name) {
//		System.out.println(ip+" "+name);
		if (!alreadyRegistered) {
			client.connect(ip);
			client.register(name);
			alreadyRegistered=true;
		}
	}

}
