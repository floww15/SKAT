package Activities;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import GameClasses.Hand;
import GameClasses.Karte;
import Server_Client.CenterClient;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DrueckenActivity {

	Button btnAufnehmen, btnCommit;

	CheckBox[] checkBox;
	ToggleGroup gModus;
	RadioButton rbtnclub, rbtnspade, rbtnheart, rbtndiamond, rbtngrand, rbtnnull;

	CheckBox cbOuvert, cbHand, cbSchneider, cbSchwarz;
	Label label;
	ArrayList<Karte> skat;
	Semaphore sem = new Semaphore(0);
	CenterClient centerClient;
	Hand hand;

	public DrueckenActivity(Stage prime, CenterClient centerClient, Hand hand) {
		this.hand = hand;
		this.centerClient = centerClient;
		for (int i = 0; i < 10; i++) {
			System.out.println(hand.get(i));
		}
		prime.setResizable(false);
		btnAufnehmen = new Button("Skat aufnehmen");
		btnAufnehmen.setOnAction(e -> btnAufnehmenClick());
		btnAufnehmen.setAlignment(Pos.CENTER);

		label = new Label("zu drücken Ankreuzen:");
		label.setVisible(false);

		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
		HBox hBox11 = new HBox();
		hBox11.setSpacing(10);
		checkBox = new CheckBox[12];
		for (int i = 0; i < 6; i++) {
			checkBox[i] = new CheckBox("" + hand.get(i) + "  ");
			hBox1.getChildren().add(checkBox[i]);
		}
		hBox1.setAlignment(Pos.CENTER);

		for (int i = 6; i < 10; i++) {
			checkBox[i] = new CheckBox("" + hand.get(i) + "  ");
			hBox11.getChildren().add(checkBox[i]);
		}
		for (int i = 10; i < 12; i++) {
			checkBox[i] = new CheckBox();
			hBox11.getChildren().add(checkBox[i]);
		}
		hBox11.setAlignment(Pos.CENTER);
		checkBox[10].setVisible(false);
		checkBox[11].setVisible(false);
		VBox vBox1 = new VBox();
		vBox1.getChildren().addAll(hBox1, hBox11);
		vBox1.setSpacing(20);

		gModus = new ToggleGroup();
		rbtnclub = new RadioButton("Kreuz");
		rbtnclub.setToggleGroup(gModus);
		rbtnspade = new RadioButton("Pik");
		rbtnspade.setToggleGroup(gModus);
		rbtnheart = new RadioButton("Herz");
		rbtnheart.setToggleGroup(gModus);
		rbtndiamond = new RadioButton("Karo");
		rbtndiamond.setToggleGroup(gModus);
		rbtngrand = new RadioButton("Grand");
		rbtngrand.setToggleGroup(gModus);
		rbtnnull = new RadioButton("Null");
		rbtnnull.setToggleGroup(gModus);
		Label lAuswahl = new Label("Spielmodus wählen");

		VBox vBoxModus = new VBox();
		vBoxModus.getChildren().add(lAuswahl);
		vBoxModus.getChildren().add(rbtnclub);
		vBoxModus.getChildren().add(rbtnspade);
		vBoxModus.getChildren().add(rbtnheart);
		vBoxModus.getChildren().add(rbtndiamond);
		vBoxModus.getChildren().add(rbtngrand);
		vBoxModus.getChildren().add(rbtnnull);

		Label lgereiztBis = new Label("Gereizt bis:");
		cbOuvert = new CheckBox("Ouvert");
		cbHand = new CheckBox("Hand");
		cbSchneider = new CheckBox("Schneider");
		cbSchwarz = new CheckBox("Schwarz");

		VBox vBoxAddOn = new VBox();
		// vBoxAddOn.setVisible(false);
		vBoxAddOn.getChildren().add(lgereiztBis);
		vBoxAddOn.getChildren().add(cbOuvert);
		vBoxAddOn.getChildren().add(cbHand);
		vBoxAddOn.getChildren().add(cbSchneider);
		vBoxAddOn.getChildren().add(cbSchwarz);

		btnCommit = new Button("Commit");

		btnCommit.setOnAction(e -> btnCommitClick());

		HBox hBox2 = new HBox();
		hBox2.setSpacing(20);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(vBoxModus, vBoxAddOn);

		VBox vBoxGes = new VBox();
		vBoxGes.getChildren().addAll(btnAufnehmen, label, vBox1, hBox2, btnCommit);
		vBoxGes.setAlignment(Pos.CENTER);
		vBoxGes.setSpacing(20);

		Label labelHeader = new Label("Drücken");
		labelHeader.setStyle("-fx-font-size:2em");
		// labelHeader.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);
		borderPane.setCenter(vBoxGes);

		Scene scene = new Scene(borderPane, 700, 430);
		prime.setTitle("SKAT");
		prime.setScene(scene);

		sem.release();
	}

	private void btnAufnehmenClick() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		skat =centerClient.getSkat();
//		System.out.println(skat.get(0));
//		System.out.println(skat.get(1));
		Platform.runLater(new Runnable() {
		
			@Override
			public void run() {
				
				hand.add(centerClient.getSkat().get(0));
				hand.add(centerClient.getSkat().get(1));
				checkBox[10].setText("" + hand.get(10) + "     ");
				checkBox[10].setVisible(true);
				checkBox[11].setText("" + hand.get(11) + "     ");
				checkBox[11].setVisible(true);
				label.setVisible(true);
				System.out.println(hand.getSize());
				sem.release();
				
			}

		});

	}

	private void btnCommitClick() {

	}

}
