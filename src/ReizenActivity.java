
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReizenActivity extends Application {
	Label lNR, lGereizt;
	Button btnNext, btnWeg;
	CenterClient centerClient;

	public ReizenActivity(Stage prime, CenterClient centerClient) {
		this.centerClient=centerClient;
		try {
			start(prime);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setResizable(false);

		lNR = new Label("Spieler NR:");
		lGereizt = new Label("Gereizt bis: ");
		btnNext = new Button("'nächster Wert'");
		btnNext.setPrefSize(80, 20);
		btnWeg = new Button("Weg");
		btnWeg.setPrefSize(80, 20);

		VBox rechts = new VBox();
		rechts.getChildren().add(lGereizt);
		rechts.getChildren().add(btnNext);
		rechts.setAlignment(Pos.CENTER);
		
		VBox links = new VBox();
		links.getChildren().add(lNR);
		links.getChildren().add(btnWeg);
		links.setAlignment(Pos.CENTER);
		
		TextField field = new TextField();
		field.setText("Florian mag FX");
		
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.getChildren().addAll(links,rechts,field);
		hbox.setAlignment(Pos.CENTER);
		
		Label labelHeader = new Label("SKAT");
		labelHeader.setStyle("-fx-font-size:2em");

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);
		borderPane.setCenter(hbox);
		
		Scene scene = new Scene(borderPane, 700,430);
		primaryStage.setTitle("SKAT");
		primaryStage.setScene(scene);
		primaryStage.show();

		field.setMinHeight(primaryStage.getHeight()/2);

	}

}
