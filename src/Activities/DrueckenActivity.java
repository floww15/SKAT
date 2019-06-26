package Activities;

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

	public DrueckenActivity(Stage prime) {
		start(prime);
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub

		primaryStage.setResizable(false);
		Button btnAufnehmen = new Button("Skat aufnehmen");
		btnAufnehmen.setAlignment(Pos.CENTER);

		Label label = new Label("zu drücken Ankreuzen:");

		HBox hBox1 = new HBox();

		CheckBox[] checkBox = new CheckBox[12];
		for (int i = 0; i < 12; i++) {
			checkBox[i] = new CheckBox("" + (i + 1) + "   ");
			hBox1.getChildren().add(checkBox[i]);
		}
		hBox1.setAlignment(Pos.CENTER);
		//
		// HBox hBoxCardsGes= new HBox();
		// HBox hBoxCards1= new HBox();
		// HBox hBoxCards2= new HBox();
		// HBox hBoxCards3= new HBox();
		// CheckBox[] checkBox= new CheckBox[12];
		// for(int i=0; i<4; i++) {
		// checkBox[i]=new CheckBox(i+" ");
		// hBoxCards1.getChildren().add(checkBox[i]);
		// }
		// for(int i=4; i<8; i++) {
		// checkBox[i]=new CheckBox(i+" ");
		// hBoxCards2.getChildren().add(checkBox[i]);
		// }
		// for(int i=8; i<12; i++) {
		// checkBox[i]=new CheckBox(i+" ");
		// hBoxCards1.getChildren().add(checkBox[i]);
		// }
		//
		// hBoxCardsGes.getChildren().add(hBoxCards1);
		// hBoxCardsGes.getChildren().add(hBoxCards2);
		// hBoxCardsGes.getChildren().add(hBoxCards3);
		//

		ToggleGroup gModus = new ToggleGroup();
		RadioButton rbtnclub = new RadioButton("Kreuz");
		rbtnclub.setToggleGroup(gModus);
		RadioButton rbtnspade = new RadioButton("Pik");
		rbtnspade.setToggleGroup(gModus);
		RadioButton rbtnheart = new RadioButton("Herz");
		rbtnheart.setToggleGroup(gModus);
		RadioButton rbtndiamond = new RadioButton("Karo");
		rbtndiamond.setToggleGroup(gModus);
		RadioButton rbtngrand = new RadioButton("Grand");
		rbtngrand.setToggleGroup(gModus);
		RadioButton rbtnnull = new RadioButton("Null");
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
		CheckBox cbOuvert = new CheckBox("Ouvert");
		CheckBox cbHand = new CheckBox("Hand");
		CheckBox cbSchneider = new CheckBox("Schneider");
		CheckBox cbSchwarz = new CheckBox("Schwarz");

		VBox vBoxAddOn = new VBox();
		// vBoxAddOn.setVisible(false);
		vBoxAddOn.getChildren().add(lgereiztBis);
		vBoxAddOn.getChildren().add(cbOuvert);
		vBoxAddOn.getChildren().add(cbHand);
		vBoxAddOn.getChildren().add(cbSchneider);
		vBoxAddOn.getChildren().add(cbSchwarz);

		Button btnCommit = new Button("Commit");

		HBox hBox2 = new HBox();
		hBox2.setSpacing(20);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(vBoxModus, vBoxAddOn);

		VBox vBoxGes = new VBox();
		vBoxGes.getChildren().addAll(btnAufnehmen, label, hBox1, hBox2, btnCommit);
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
		primaryStage.setTitle("SKAT");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
