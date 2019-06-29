package Activities;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Server_Client.*;
import GameClasses.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReizenActivity {
	Label lNR, lGereizt, lZustand, lEmpty;
	Button btnNext, btnWeg;
	TextArea field;
	CenterClient centerClient;
	ArrayList<Karte> karten;
	Semaphore sem = new Semaphore(0);
	int pos = 0;

	public ReizenActivity(Stage prime, CenterClient centerClient, int pos, ArrayList<Karte> karten) {
		this.pos = pos;
		this.centerClient = centerClient;
		this.karten = karten;
		// Platform.setImplicitExit(false);

		System.out.println(pos);
		lNR = new Label(); // SpielerNR
		lGereizt = new Label();// n�chster ReizWert oder JA
		lZustand = new Label();// aktueller Zustand/Aktivit�t des Spielers
		lEmpty = new Label();
		btnNext = new Button();// Aktueller Reizwert
		btnNext.setPrefSize(80, 20);
		btnWeg = new Button("Weg");// Weg
		btnWeg.setPrefSize(80, 20);

		btnWeg.setOnAction(e -> btnWegClick());
		btnNext.setOnAction(e -> btnNextClick());

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
		// field.setText("Florian mag FX");
		// field.setText(field.getText()+"\n"+"lol");

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
		prime.setTitle("SKAT");
		prime.setScene(scene);
//		try {
//			centerClient.getSem().release();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		sem.release();

//		changeLabelstart();

	}

	public void changeLabelstart() {
//		try {
//			centerClient.getSem().acquire();
//		} catch (RemoteException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lNR.setText("" + (pos + 1));
				switch (pos) {
				case 0:
					lZustand.setText("gegeben warten");
					break;
				case 1:
					lZustand.setText("h�ren");
					btnNext.setText("JA");
					break;
				case 2:
					lZustand.setText("sagen");
					btnNext.setText("18");
					break;
				}
				lGereizt.setText("---");
				for(int i=0; i<10 ; i++) {
					field.setText(field.getText()+karten.get(i)+"\n");
				}
				sem.release();
//		try {
//			centerClient.getSem().release();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		sem.release();
			}
		});

	}

	public void btnWegClick() {
		centerClient.btnWegClick();
	}

	public void btnNextClick() {
		System.out.println("Next ReizenAcc");
		centerClient.btnNextClick();
	}

	public void changeLGereizt(String value) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lGereizt.setText(value);
				sem.release();
			}
		});
	}

	public void changeBtnNext(String value) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				btnNext.setText(value);
				sem.release();
			}
		});
	}
	
	public void changeLEmpty(String value) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lEmpty.setText(value);
				sem.release();
			}
		});
	}
	
	

}
