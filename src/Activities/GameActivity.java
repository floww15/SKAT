package Activities;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.concurrent.Semaphore;

import GameClasses.Hand;
import GameClasses.Karte;
import GameClasses.NotYourTurnException;
import GameClasses.Player;
import GameClasses.WrongCardException;
import Server_Client.CenterClient;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameActivity {
	Button[] btnCards;
	Label lPlayedCard3, lname3, lPlayedCard2, lname2, lPlayedCard1, lname1, lTeam, lSolo, lType,lError;
	Semaphore sem = new Semaphore(0);
	Player[] player;
	String type;
	int playingAlone;
	Stage prime;
	CenterClient centerClient;
	int id;
	

	public GameActivity(Stage prime, Player[] player, int ownNR, int playingAlone, String type,
			CenterClient centerClient) {
		System.out.println(player[(playingAlone) % 3].getName());
		System.out.println(player[(playingAlone + 1) % 3].getName());
		System.out.println(player[(playingAlone + 2) % 3].getName());
		this.prime = prime;
		this.playingAlone = playingAlone;
		this.player = player;
		this.type = type;
		this.centerClient = centerClient;
		id = ownNR;
		// TextArea taPlayer1= new TextArea("Flo");
		prime.setResizable(false);
		
		lError=new Label("Karte nicht spielbar. Bitte richtige Karte auswählen");
		lError.setVisible(false);
		lError.setTextFill(Color.RED);
		
		// Team, Solo, Spieltyp
		VBox vBoxTeam = new VBox();
		vBoxTeam.setStyle("-fx-border-width: 2px");
		vBoxTeam.setStyle("-fx-border-color: black");

		lTeam = new Label(
				"Team:" + player[(playingAlone + 1) % 3].getName() + " " + player[(playingAlone + 2) % 3].getName());
		vBoxTeam.getChildren().add(lTeam);

		VBox vBoxSolo = new VBox();
		vBoxSolo.setStyle("-fx-border-width: 2px");
		vBoxSolo.setStyle("-fx-border-color: black");
		lSolo = new Label("Solo: " + player[playingAlone].getName());
		vBoxSolo.getChildren().add(lSolo);

		VBox vBoxType = new VBox();
		vBoxType.setStyle("-fx-border-width: 2px");
		vBoxType.setStyle("-fx-border-color: black");
		lType = new Label("Type: " + type);
		vBoxType.getChildren().add(lType);

		HBox hBoxPlayers = new HBox();
		hBoxPlayers.getChildren().addAll(vBoxTeam, vBoxSolo, vBoxType);
		// hBoxPlayers.setStyle("-fx-border-width: 2px");
		// hBoxPlayers.setStyle("-fx-border-color: black");
		// hBoxPlayers.getChildren().add(new Label("flo"));
		hBoxPlayers.setStyle("-fx-border-width:2px");
		hBoxPlayers.setStyle("-fx-border-color:black");
		hBoxPlayers.setMaxSize(300, 200);
		hBoxPlayers.setAlignment(Pos.CENTER);
		hBoxPlayers.setSpacing(10);

		// Spieler und gespielte Karten
		VBox vBoxPlayer1 = new VBox();
		lname1 = new Label(player[0].getName());
		lname1.setStyle("-fx-border-width:2px");
		lname1.setStyle("-fx-border-color:black");
		lPlayedCard1 = new Label("---");
		lPlayedCard1.setStyle("-fx-border-width:2px");
		lPlayedCard1.setStyle("-fx-border-color:black");
		vBoxPlayer1.getChildren().addAll(lname1, lPlayedCard1);
		vBoxPlayer1.setSpacing(10);

		VBox vBoxPlayer2 = new VBox();
		lname2 = new Label(player[1].getName());
		lname2.setStyle("-fx-border-width:2px");
		lname2.setStyle("-fx-border-color:black");
		lPlayedCard2 = new Label("---");
		lPlayedCard2.setStyle("-fx-border-width:2px");
		lPlayedCard2.setStyle("-fx-border-color:black");
		vBoxPlayer2.getChildren().addAll(lname2, lPlayedCard2);
		vBoxPlayer2.setSpacing(10);

		VBox vBoxPlayer3 = new VBox();
		lname3 = new Label(player[2].getName());
		lname3.setStyle("-fx-border-width:2px");
		lname3.setStyle("-fx-border-color:black");
		lPlayedCard3 = new Label("---");
		lPlayedCard3.setStyle("-fx-border-width:2px");
		lPlayedCard3.setStyle("-fx-border-color:black");
		vBoxPlayer3.getChildren().addAll(lname3, lPlayedCard3);
		vBoxPlayer3.setSpacing(10);

		HBox hBoxPlay = new HBox();
		hBoxPlay.getChildren().addAll(vBoxPlayer1, vBoxPlayer2, vBoxPlayer3);
		hBoxPlay.setAlignment(Pos.CENTER);
		hBoxPlay.setStyle("-fx-border-width:2px");
		hBoxPlay.setStyle("-fx-border-color:black");
		hBoxPlay.setMaxSize(300, 200);
		hBoxPlay.setSpacing(20);

		btnCards = new Button[10];
		HBox hBoxCards1 = new HBox();
		HBox hBoxCards2 = new HBox();
		hBoxCards1.setAlignment(Pos.CENTER);
		hBoxCards1.setSpacing(10);
		hBoxCards2.setAlignment(Pos.CENTER);
		hBoxCards2.setSpacing(10);
		Collections.sort(player[ownNR].getHand().getHandkarten(), new Hand.HandComparator());
		for (int i = 0; i < 5; i++) {
			btnCards[i] = new Button("" + player[ownNR].getHand().get(i));
			hBoxCards1.getChildren().add(btnCards[i]);
		}
		for (int i = 5; i < 10; i++) {
			btnCards[i] = new Button("" + player[ownNR].getHand().get(i));
			hBoxCards2.getChildren().add(btnCards[i]);
		}

		btnCards[0].setOnAction(e -> btnCardsClick(0));
		btnCards[1].setOnAction(e -> btnCardsClick(1));
		btnCards[2].setOnAction(e -> btnCardsClick(2));
		btnCards[3].setOnAction(e -> btnCardsClick(3));
		btnCards[4].setOnAction(e -> btnCardsClick(4));
		btnCards[5].setOnAction(e -> btnCardsClick(5));
		btnCards[6].setOnAction(e -> btnCardsClick(6));
		btnCards[7].setOnAction(e -> btnCardsClick(7));
		btnCards[8].setOnAction(e -> btnCardsClick(8));
		btnCards[9].setOnAction(e -> btnCardsClick(9));

		VBox VBoxCards = new VBox();
		VBoxCards.getChildren().add(hBoxCards1);
		VBoxCards.getChildren().add(hBoxCards2);
		VBoxCards.setAlignment(Pos.CENTER);
		VBoxCards.setStyle("-fx-border-width:2px");
		VBoxCards.setStyle("-fx-border-color:black");
		VBoxCards.setMaxSize(500, 300);
		VBoxCards.setSpacing(20);

		// alle hBoxen zusammengefasst
		VBox vBoxAll = new VBox();
		vBoxAll.setAlignment(Pos.CENTER);
		vBoxAll.getChildren().addAll(lError,hBoxPlayers, hBoxPlay, VBoxCards);
		vBoxAll.setSpacing(20);

		Label labelHeader = new Label("Spielen");
		labelHeader.setStyle("-fx-font-size:2em");
		// labelHeader.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);
		borderPane.setCenter(vBoxAll);

		Scene scene = new Scene(borderPane, 700, 400);
		prime.setTitle("SKAT");
		prime.setScene(scene);

		sem.release();
	}

	private void btnCardsClick(int i) {
		Player temp = player[id];
		Karte k = temp.getHand().remove(i);
		try {
			
			
			System.out.println(k);
			centerClient.legKarte(k);
			btnCards[i].setVisible(false);
			lError.setVisible(false);
		} catch (WrongCardException e) {
			lError.setVisible(true);
			System.out.println("wrongCard ");
			
		}
		catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotYourTurnException e) {
			System.out.println("not your turn");
			player[id].getHand().getHandkarten().add(i, k);
		}
		catch(NullPointerException e) {}

		return;
	}

	public void Card0Text(String s) {
		lPlayedCard1.setText(s);
	}

	public void Card1Text(String s) {
		lPlayedCard2.setText(s);
	}

	public void Card2Text(String s) {
		lPlayedCard3.setText(s);
	}

	public void CardChanges(String s1, String s2, String s3) {

//		try {
//			sem.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (s1 != null)
					lPlayedCard1.setText(s1);
				if (s2 != null)
					lPlayedCard2.setText(s2);
				if (s3 != null)
					lPlayedCard3.setText(s3);
//				sem.release();
			}
		});

	}

}
