package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("CalculatorView.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("calculator/CalculatorStyle.css");
				
		primaryStage.setTitle("Calculator");
		primaryStage.setMinHeight(436);
		primaryStage.setMinWidth(314);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}