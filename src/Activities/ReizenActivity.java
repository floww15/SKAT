package Activities;

import java.util.Collections;
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
	Hand karten;
	Semaphore sem = new Semaphore(0);
	int pos = 0;
	Stage prime;

	public ReizenActivity(Stage prime, CenterClient centerClient, int pos, Hand karten) {
		this.prime = prime;
		this.pos = pos;
		this.centerClient = centerClient;
		this.karten = karten;
		// Platform.setImplicitExit(false);

		System.out.println(pos);
		lNR = new Label(); // SpielerNR
		lGereizt = new Label();// nächster ReizWert oder JA
		lZustand = new Label();// aktueller Zustand/Aktivität des Spielers
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
					lZustand.setText("hören");
					btnNext.setText("JA");
					break;
				case 2:
					lZustand.setText("sagen");
					btnNext.setText("18");
					break;
				}
				lGereizt.setText("---");
//				for(int i=0; i<10 ; i++) {
				Collections.sort(karten.getHandkarten(), new Hand.HandComparator());
				field.setText(karten.toString());
//				}
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

//	public void changeLGereizt(String value) {
//		try {
//			sem.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				lGereizt.setText(value);
//				sem.release();
//			}
//		});
//	}
//
//	public void changeBtnNext(String value) {
//		try {
//			sem.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				btnNext.setText(value);
//				sem.release();
//			}
//		});
//	}
//	
//	public void changeLEmpty(String value) {
//		try {
//			sem.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				lEmpty.setText(value);
//				sem.release();
//			}
//		});
//	}

	public void changes(String Nr, String Weg, String Empty, String Gereizt, String Next, String Zustand) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (Nr != null)
					lNR.setText(Nr);
				if (Weg != null)
					btnWeg.setText(Weg);
				if (Empty != null)
					lEmpty.setText(Empty);
				if (Gereizt != null)
					lGereizt.setText(Gereizt);
				if (Next != null)
					btnNext.setText(Next);
				if (Zustand != null)
					lZustand.setText(Zustand);
				sem.release();
			}

		});
	}

	public DrueckenActivity startDruecken(Hand hand) {
		return new DrueckenActivity(prime, centerClient, hand);
	}
	
	public GameActivity startGamefromReizen() {
		return new GameActivity(prime);
	}

}
