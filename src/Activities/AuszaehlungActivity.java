package Activities;

import Server_Client.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AuszaehlungActivity extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setResizable(false);

		HBox hBoxSpielArt = new HBox();
		Label lSpielArt = new Label("Spieler X hat ... gespielt");
		lSpielArt.setStyle("-fx-border-width:2px");
		lSpielArt.setStyle("-fx-border-color:black");
		hBoxSpielArt.getChildren().add(lSpielArt);
		hBoxSpielArt.setAlignment(Pos.CENTER);
		
		HBox hBoxPunkte= new HBox();
		Label lPunkte = new Label("Punkte geholt: ");
		lPunkte.setStyle("-fx-border-width:2px");
		lPunkte.setStyle("-fx-border-color:black");
		hBoxPunkte.getChildren().add(lPunkte);
		hBoxPunkte.setAlignment(Pos.CENTER);
		
		HBox hBoxSieger= new HBox();
		Label lSieger = new Label("Gewonnen haben:");
		Label lAufschreiben= new Label("Addierte Punkte:");
		lSieger.setStyle("-fx-border-width:2px");
		lSieger.setStyle("-fx-border-color:black");
		lAufschreiben.setStyle("-fx-border-width:2px");
		lAufschreiben.setStyle("-fx-border-color:black");
		hBoxSieger.getChildren().addAll(lSieger,lAufschreiben);
		hBoxSieger.setAlignment(Pos.CENTER);
		hBoxSieger.setSpacing(40);
		
		HBox hBoxButtons= new HBox();
		Button btnRestart= new Button("Restart");
		Button btnClose= new Button("Close");
		btnRestart.setPrefSize(80, 20);
		btnClose.setPrefSize(80, 20);	
		hBoxButtons.getChildren().addAll(btnRestart, btnClose);
		hBoxButtons.setSpacing(20);
		hBoxButtons.setAlignment(Pos.CENTER);
		

		VBox vBoxAll = new VBox();
		vBoxAll.setAlignment(Pos.CENTER);
		vBoxAll.getChildren().addAll(hBoxSpielArt,hBoxPunkte, hBoxSieger, hBoxButtons);
		vBoxAll.setSpacing(20);

		Label labelHeader = new Label("Auszählung");
		labelHeader.setStyle("-fx-font-size:2em");
//		labelHeader.setAlignment(Pos.CENTER);

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
