package Activities;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

import GameClasses.Player;
import GameClasses.Stich;
import Server_Client.CenterClient;
import Server_Client.SkatClient;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AuszaehlungActivity {
	Label lSpielArt, lPunkte, lSieger, lAufschreiben;
	CenterClient client;
	Semaphore sem = new Semaphore(1);
	String spielart, sieger1, sieger2, verlierer1, verlierer2, spieler;
	int insPunkte, punkte = 0;
	SkatClient[] clients;

	public AuszaehlungActivity(Stage prime, CenterClient client ) {
		start(prime);
		this.client = client;
		for (Stich t : client.getClient().getPlayer().getStiche())
			punkte += t.getPoints();
		insPunkte = client.getClient().getPunkte();
		insPunkte += punkte;
		client.getClient().setPunkte(insPunkte);
		try {
			clients = (SkatClient[]) client.getClient().getClients();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (client.getSkatClient().isFirst())
			spieler = client.getClient().getPlayer().getName();
		else {
			
			for (SkatClient c : clients) {
				if (c.isFirst()) {
					spieler =  c.getPlayer().getName();
				}
			}
		}
		int x = 0;
		for (SkatClient s : clients) {
			if (s.isFirst()) {
				for (Stich i : s.getPlayer().getStiche()) {
					if ( i.getPoints() >= 61) {
						sieger1 = s.getPlayer().getName();
						Player [] arr = s.getPlayers();
						for (int z= 0; z<arr.length; z++) {
							if (arr[z].getName().equals(s.getPlayer().getName()))
								x = z;
						}
						if (x == 0) {
							verlierer1 = arr[1].getName();
							verlierer2 = arr[2].getName();
						} else if (x == 1) {
							verlierer1 = arr[0].getName();
							verlierer2 = arr[2].getName();/** Verlierer und Sieger auswertung**/
						} else {
							verlierer1 = arr[1].getName();
							verlierer2 = arr[0].getName();
						}
					} else {
						verlierer1 = s.getPlayer().getName();
						Player [] arr = s.getPlayers();
						for (int z= 0; z<arr.length; z++) {
							if (arr[z].getName().equals(s.getPlayer().getName()))
								x = z;
						}
						if (x == 0) {
							sieger1 = arr[1].getName();
							sieger2 = arr[2].getName();
						} else if (x == 1) {
							sieger1 = arr[0].getName();
							sieger2 = arr[2].getName();
						} else {
							sieger1 = arr[1].getName();
							sieger2 = arr[0].getName();
						}
					}
				}
			}
			
				
		}
		
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		primaryStage.setResizable(false);
		

		HBox hBoxSpielArt = new HBox();
		lSpielArt = new Label("Spieler "+spieler+" hat "+spielart+" gespielt");
		lSpielArt.setStyle("-fx-border-width:2px");
		lSpielArt.setStyle("-fx-border-color:black");
		hBoxSpielArt.getChildren().add(lSpielArt);
		hBoxSpielArt.setAlignment(Pos.CENTER);

		HBox hBoxPunkte = new HBox();
		lPunkte = new Label("Punkte geholt: "+punkte);
		lPunkte.setStyle("-fx-border-width:2px");
		lPunkte.setStyle("-fx-border-color:black");
		hBoxPunkte.getChildren().add(lPunkte);
		hBoxPunkte.setAlignment(Pos.CENTER);

		HBox hBoxSieger = new HBox();
		if (sieger1 != null && sieger2 != null) {
			lSieger = new Label("Gewonnen haben: "+sieger1+" und "+sieger2+"\nVerloren hat "+verlierer1);
		} else {
			lSieger = new Label("Gewonnen hat: "+sieger1+" \nVerloren haben "+verlierer1+ " und "+verlierer2);
		}
		lAufschreiben = new Label("Addierte Punkte: "+insPunkte);
		lSieger.setStyle("-fx-border-width:2px");
		lSieger.setStyle("-fx-border-color:black");
		lAufschreiben.setStyle("-fx-border-width:2px");
		lAufschreiben.setStyle("-fx-border-color:black");
		hBoxSieger.getChildren().addAll(lSieger, lAufschreiben);
		hBoxSieger.setAlignment(Pos.CENTER);
		hBoxSieger.setSpacing(40);

		HBox hBoxButtons = new HBox();
		Button btnRestart = new Button("Restart");
		Button btnClose = new Button("Close");
		btnRestart.setPrefSize(80, 20);
		btnClose.setPrefSize(80, 20);
		hBoxButtons.getChildren().addAll(btnRestart, btnClose);
		hBoxButtons.setSpacing(20);
		hBoxButtons.setAlignment(Pos.CENTER);

		VBox vBoxAll = new VBox();
		vBoxAll.setAlignment(Pos.CENTER);
		vBoxAll.getChildren().addAll(hBoxSpielArt, hBoxPunkte, hBoxSieger, hBoxButtons);
		vBoxAll.setSpacing(20);

		Label labelHeader = new Label("Auszählung");
		labelHeader.setStyle("-fx-font-size:2em");
		// labelHeader.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);
		borderPane.setCenter(vBoxAll);

		Scene scene = new Scene(borderPane, 700, 430);
		primaryStage.setTitle("SKAT");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	
}
