
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartActivity extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setResizable(false);
//		Button btnConnect = new Button("Connect");
//		btnConnect.setPrefSize(80,20);
		Button btnStart = new Button("Connect");
		btnStart.setPrefSize(80,20);
		TextField tfIP = new TextField();
		TextField tfName = new TextField();
		Label label1= new Label("Server IP:");
		Label label2= new Label("Spielername:");
		
		Label labelHeader= new Label("SKAT");
		labelHeader.setStyle("-fx-font-size:2em");
//		labelHeader.setAlignment(Pos.CENTER);
		
		BorderPane borderPane=new BorderPane();
		borderPane.setTop(labelHeader);
		BorderPane.setAlignment(labelHeader, Pos.CENTER);

		GridPane pane= new GridPane();
		pane.setHgap(2);
		pane.setVgap(2);
//		pane.add(btnConnect, 2,1);
		pane.add(btnStart, 2,2);
		pane.add(tfIP, 1,1);
		pane.add(tfName, 1,2);
		pane.add(label1, 0, 1);
		pane.add(label2, 0, 2);
//		pane.add(labelHeader, 1, 0);
		pane.setAlignment(Pos.CENTER);
		borderPane.setCenter(pane);
		Scene scene = new Scene(borderPane, 700,430);
		primaryStage.setTitle("SKAT");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	

}