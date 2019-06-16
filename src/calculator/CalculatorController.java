package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	private String value;
	
    @FXML
    private TextField displayField;

    @FXML
    void handleButtonInput(ActionEvent event) {
    	value = ((Button)event.getSource()).getText();			// get text of button input
        displayField.setText(displayField.getText() + value);	// display value entered
    }
    
    @FXML
    void clear(ActionEvent event) {
    	displayField.clear();
    }
	
}
