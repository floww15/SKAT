package Activities;

import java.util.concurrent.Semaphore;

import GameClasses.Player;
import GameClasses.Stich;
import Server_Client.CenterClient;
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
	int insPunkte = 0, punkte = 0;
	Player[] clients;
	int playAlone;

	public AuszaehlungActivity(Stage prime, CenterClient client) {
		this.client = client;
		playAlone = client.getClient().getPlayingAlone();
		clients = client.getClient().getPlayers();
		spieler = client.getClient().getPlayers()[playAlone].getName();
		for (Player p : clients) {
			if (p.getName().equals(spieler)) {
				for (Stich i : p.getStiche()) {
					punkte += i.getPoints();
				}
				if (punkte >= 61) {
					sieger1 = spieler;
					if (playAlone == 0) {
						verlierer1 = clients[1].getName();
						verlierer2 = clients[2].getName();
					} else if (playAlone == 1) {
						verlierer1 = clients[0].getName();
						verlierer2 = clients[2].getName();/** Verlierer und Sieger auswertung **/
					} else {
						verlierer1 = clients[1].getName();
						verlierer2 = clients[0].getName();
					}
				} else {
					if (playAlone == 0) {
						verlierer1 = spieler;
<<<<<<< HEAD
//						punkte = punkte * -1;
=======
						punkte = punkte * -1;
						insPunkte += punkte;
>>>>>>> branch 'master' of https://github.com/floww15/SKAT.git
						sieger1 = clients[1].getName();
						sieger2 = clients[2].getName();
					} else if (playAlone == 1) {
						sieger1 = clients[0].getName();
						sieger2 = clients[2].getName();
					} else {
						sieger1 = clients[1].getName();
						sieger2 = clients[0].getName();
					}

				}
			}

		}

		HBox hBoxSpielArt = new HBox();
		lSpielArt = new Label("Spieler " + spieler + " hat " + client.getTrumpf() + " gespielt");
		lSpielArt.setStyle("-fx-border-width:2px");
		lSpielArt.setStyle("-fx-border-color:black");
		hBoxSpielArt.getChildren().add(lSpielArt);
		hBoxSpielArt.setAlignment(Pos.CENTER);

		HBox hBoxPunkte = new HBox();
		lPunkte = new Label("Punkte geholt: " + punkte);
		lPunkte.setStyle("-fx-border-width:2px");
		lPunkte.setStyle("-fx-border-color:black");
		hBoxPunkte.getChildren().add(lPunkte);
		hBoxPunkte.setAlignment(Pos.CENTER);

		HBox hBoxSieger = new HBox();
		if (sieger1 != null && sieger2 != null) {
			lSieger = new Label("Gewonnen haben: " + sieger1 + " und " + sieger2 + "\nVerloren hat " + verlierer1);
		} else {
			lSieger = new Label("Gewonnen hat: " + sieger1 + " \nVerloren haben " + verlierer1 + " und " + verlierer2);
		}
//		lAufschreiben = new Label("Addierte Punkte: " + insPunkte);
		lSieger.setStyle("-fx-border-width:2px");
		lSieger.setStyle("-fx-border-color:black");
//		lAufschreiben.setStyle("-fx-border-width:2px");
//		lAufschreiben.setStyle("-fx-border-color:black");
		hBoxSieger.getChildren().addAll(lSieger/*,lAufschreiben*/);
		hBoxSieger.setAlignment(Pos.CENTER);
		hBoxSieger.setSpacing(40);

		HBox hBoxButtons = new HBox();
//		Button btnRestart = new Button("Restart");
		Button btnClose = new Button("Close");
		btnClose.setOnAction(e->btnCloseClick());
//		btnRestart.setPrefSize(80, 20);
		btnClose.setPrefSize(80, 20);
		hBoxButtons.getChildren().addAll(/*btnRestart,*/ btnClose);
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
		prime.setTitle("SKAT");
		prime.setScene(scene);

	}

	private void btnCloseClick() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
