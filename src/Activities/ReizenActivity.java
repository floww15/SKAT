package Activities;

import java.util.ArrayList;

import Server_Client.*;
import GameClasses.*;


import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReizenActivity extends Application {
	Label lNR, lGereizt, lZustand, lEmpty;
	Button btnNext, btnWeg;
	TextArea field;
	CenterClient centerClient;

	public ReizenActivity(Stage prime, CenterClient centerClient,int pos, ArrayList<Karte> karten) {
		this.centerClient = centerClient;
		try {
			start(prime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lNR.setText(""+pos);
		switch(pos) {
		case 0:
			lZustand.setText("gegeben warten");
			break;
		case 1:
			lZustand.setText("hören");
			break;
		case 2:
			lZustand.setText("sagen");
			btnNext.setText("18");
			break;
		}
		lGereizt.setText("---");
		

	}
	
//	public static void main(String[] args) {
//		Application.launch(args);
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setResizable(false);

		lNR = new Label(); //SpielerNR
		lGereizt = new Label();//nächster ReizWert oder JA
		lZustand= new Label();//aktueller Zustand/Aktivität des Spielers
		lEmpty= new Label();
		btnNext = new Button();//Aktueller Reizwert
		btnNext.setPrefSize(80, 20);
		btnWeg = new Button("Weg");//Weg
		btnWeg.setPrefSize(80, 20);

		VBox rechts = new VBox();
		rechts.getChildren().add(lGereizt);
		rechts.getChildren().add(btnNext);
		rechts.getChildren().add(lZustand);
		rechts.setAlignment(Pos.CENTER);

		VBox links = new VBox();
		links.getChildren().add(lNR);
		links.getChildren().add(btnWeg);
		links.getChildren().add(lEmpty);
		links.setAlignment(Pos.CENTER);

		field = new TextArea();
		field.setMaxSize(100, 200);
//		field.setText("Florian mag FX");
//		field.setText(field.getText()+"\n"+"lol");

		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.getChildren().addAll(links, rechts, field);
		hbox.setAlignment(Pos.CENTER);

		Label labelHeader = new Label("SKAT");
		labelHeader.setStyle("-fx-font-size:2em");

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);
		borderPane.setCenter(hbox);

		Scene scene = new Scene(borderPane, 700, 430);
		primaryStage.setTitle("SKAT");
		primaryStage.setScene(scene);
		field.setMinHeight(primaryStage.getHeight() / 2);

	}

}
