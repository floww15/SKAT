package Activities;

import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrueckenActivity {

	Button btnAufnehmen, btnCommit;

	CheckBox[] checkBox;
	ToggleGroup gModus;
	RadioButton gModusRadioButton[] = new RadioButton[6];

	CheckBox cbOuvert, cbHand, cbSchneider, cbSchwarz;
	Label label;
	ArrayList<Karte> skat, neuerSkat = new ArrayList<Karte>();
	Semaphore sem = new Semaphore(0);
	Stage prime;
	CenterClient centerClient;
	Hand hand;
	boolean skatAufgenommen = false;
	
	String spielModus = "";
	boolean addOns[] = new boolean[4];

	public DrueckenActivity(Stage prime, CenterClient centerClient, Hand hand) {
		this.prime=prime;
		this.hand = hand;
		this.centerClient = centerClient;
		centerClient.getClient().setFirst();
		for (int i = 0; i < 10; i++) {
			System.out.println(hand.get(i));
		}
		prime.setResizable(false);
		btnAufnehmen = new Button("Skat aufnehmen");
		btnAufnehmen.setOnAction(e -> btnAufnehmenClick());
		btnAufnehmen.setAlignment(Pos.CENTER);

		label = new Label();
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
		gModusRadioButton[0] = new RadioButton("Kreuz");
		gModusRadioButton[0].setToggleGroup(gModus);
		gModusRadioButton[1] = new RadioButton("Pik");
		gModusRadioButton[1].setToggleGroup(gModus);
		gModusRadioButton[2] = new RadioButton("Herz");
		gModusRadioButton[2].setToggleGroup(gModus);
		gModusRadioButton[3] = new RadioButton("Karo");
		gModusRadioButton[3].setToggleGroup(gModus);
		gModusRadioButton[4] = new RadioButton("Grand");
		gModusRadioButton[4].setToggleGroup(gModus);
		gModusRadioButton[5] = new RadioButton("Null");
		gModusRadioButton[5].setToggleGroup(gModus);
		Label lAuswahl = new Label("Spielmodus wählen");

		VBox vBoxModus = new VBox();
		vBoxModus.getChildren().add(lAuswahl);
		vBoxModus.getChildren().add(gModusRadioButton[0]);
		vBoxModus.getChildren().add(gModusRadioButton[1]);
		vBoxModus.getChildren().add(gModusRadioButton[2]);
		vBoxModus.getChildren().add(gModusRadioButton[3]);
		vBoxModus.getChildren().add(gModusRadioButton[4]);
		vBoxModus.getChildren().add(gModusRadioButton[5]);

		Label lgereiztBis = new Label("Gereizt bis:");
		cbOuvert = new CheckBox("Ouvert");
		cbOuvert.setVisible(false);
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
		if(skatAufgenommen)
			return;
		skatAufgenommen = true;
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
				Collections.sort(hand.getHandkarten(), new Hand.HandComparator());
				for (int i = 0; i < 12; i++) {
					checkBox[i].setText("" + hand.get(i));
				}

				checkBox[10].setVisible(true);
				checkBox[11].setVisible(true);
				label.setVisible(true);
//				System.out.println(hand.getSize());
				sem.release();

			}

		});

	}

	private void btnCommitClick() {
		System.out.println();
		label.setVisible(false);
//		System.out.println("btnCommit");
		int countDruecken = 0;
		for (int i = 0; i < 12; i++) {
			if (checkBox[i].isSelected())
				countDruecken++;
		}

		if (skatAufgenommen && countDruecken != 2) {
			System.out.println("mit Skat");
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					label.setText("zu drücken Ankreuzen:  Bitte genau zwei Karten drücken!");
					label.setTextFill(Color.RED);
					label.setVisible(true);
					sem.release();
				}

			});
			return;
		}
		if (!skatAufgenommen && countDruecken != 0) {
			System.out.println("ohne Skat");
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					label.setText(label.getText() + "  Bitte keine Karte drücken. Skat nicht aufgenommen");
					label.setTextFill(Color.RED);
					label.setVisible(true);
					sem.release();
				}

			});
			return;
			// return;
		}
		if ((skatAufgenommen && countDruecken == 2) || (!skatAufgenommen && countDruecken == 0)) {
			System.out.println("skat korrekt");
			System.out.println("Hand groeße " + hand.getSize());
			if (skatAufgenommen) {
				for (int i = 0; i < 12; i++) {
					if (checkBox[i].isSelected()) {
//						System.out.println(hand.get(i));
//						hand.remove(i);
						neuerSkat.add(hand.get(i));
					}
				}
				System.out.println(neuerSkat.get(0));
				System.out.println(neuerSkat.get(1));
				hand.removeD(neuerSkat.get(0));
				hand.removeD(neuerSkat.get(1));

			}
			System.out.println("Hand groeße " + hand.getSize());
			boolean toggled = false;
			for (int i = 0; i < 6; i++) {
				if (gModusRadioButton[i].isSelected()) {
					toggled = true;
					spielModus = gModusRadioButton[i].getText();
				}
			}
			if (!toggled) {
				label.setText("Bitte SpielModus Auswählen!");
				label.setVisible(true);
				return;
			}
		}
		if(!skatAufgenommen) {
			neuerSkat=centerClient.getSkat();
		}
		// cbOuvert, cbHand, cbSchneider, cbSchwarz
		if (cbOuvert.isSelected())
			addOns[0] = true;
		if (cbHand.isSelected() && !skatAufgenommen)
			addOns[1] = true;
		if (cbSchneider.isSelected())
			addOns[2] = true;
		if (cbSchwarz.isSelected())
			addOns[3] = true;
		System.out.println("Punkt5");
		// Übergabe der eingelesenen Variablen
		centerClient.setChangesAfterDruecken(spielModus, hand, neuerSkat, addOns);
		// Start GameMode
		centerClient.startGameActivites();
	}
	
	public GameActivity startGamefromDruecken() {
		return new GameActivity(prime,centerClient.getPlayers(),centerClient.getPos(),centerClient.getPlayingAlone(),centerClient.getTrumpf(),centerClient);
	}


}
