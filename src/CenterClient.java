import javafx.application.Application;
import javafx.stage.Stage;


//Klasse zum Verwalten der Activities und des SkatClients
//Nötig, da nur ein fx Thread laufen darf
public class CenterClient extends Application{

	SkatClient client;
	StartActivity startActivity;
	
	@Override
	public void start(Stage prime) throws Exception {
		client = new SkatClient();
		startActivity= new StartActivity(prime);
		
	}
	
	public static void main (String...args) {
		launch();
	}

}
