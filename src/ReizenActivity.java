import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReizenActivity extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setResizable(false);

		Label lNR = new Label("Spieler NR:");
		Label lGereizt = new Label("Gereizt bis: ");
		Button btnWeg = new Button("Weg");
		btnWeg.setPrefSize(80, 20);
		Button btnNext = new Button("'nächster Wert'");
		btnNext.setPrefSize(80, 20);

		GridPane pane2 = new GridPane();
		pane2.setVgap(1);
		pane2.setHgap(1);
		pane2.add(lNR, 0, 0);
		pane2.add(lGereizt, 1, 0);
		pane2.add(btnWeg, 0, 1);
		pane2.add(btnNext, 1, 1);

		GridPane pane1 = new GridPane();
		pane1.setVgap(1);
		pane1.setHgap(0);
		pane1.add(pane2, 0, 0);
		

		Label labelHeader = new Label("SKAT");
		labelHeader.setStyle("-fx-font-size:2em");

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);
		borderPane.setCenter(pane1);
		BorderPane.setAlignment(pane1, Pos.CENTER);
		
		
		Scene scene = new Scene(borderPane, 600,400);
		primaryStage.setTitle("Skat");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
