package calculator;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	private String value;
	
    @FXML
    private TextField displayField;

    @FXML
    private void handleButtonInput(ActionEvent event) {
    	value = ((Button)event.getSource()).getText();			// get text of button input
    	
    	switch(value) {
    	case "C":
    		clear(event);
    		break;
    	case "+":
    	default: 
    		displayField.setText(displayField.getText() + value); // display value entered
    		break;
    	}
       
    }
    
    @FXML
    private void clear(ActionEvent event) {
    	displayField.clear();
    }
    
    private boolean isNumeric(String s) {
    	try {
    		Double.parseDouble(s);
    		return true;
    	}
    	catch (NumberFormatException e) {
    		return false;
    	}
    }
	
}
