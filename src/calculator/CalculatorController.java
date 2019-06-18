package calculator;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculatorController {
	
	private String value;
	private String history = "";
	private double result = 0;
	private ArrayList<Double> input = new ArrayList<Double>();

	
    @FXML
    private TextField displayField;
    
    @FXML
    private Text historyField;

    @FXML
    private void handleButtonInput(ActionEvent event) {
    	value = ((Button)event.getSource()).getText();			// get text of button input
    	
    	switch(value) {
    	case "C": // clear display and reset result
    		clear(event);
    		result = 0;
    		break;
    	case "+/-":
    
    	case "+":
    	case "-":
    	case "*":
    	case "/":
    		history += (displayField.getText() + value);
    		historyField.setText(history); // display input history
    		displayField.clear();
    		break;
    	case "=":
    		calculate();
    		
    		// clear display and show result
    		clear(event);
    		displayField.setText("" + result);
    		break;
    	default: 
    		displayField.setText(displayField.getText() + value); // display value entered
    		storeInput();
    		break;
    	} 
    }
    
    private double calculate() {
    	for (Double i : input) {
    		result += i;
    	}
    	return result;
    }
 
    @FXML
    private void clear(ActionEvent event) {
    	displayField.clear();
    	historyField.setText("");
    	history = "";
    	input = new ArrayList<Double>();
    }
    
    private void storeInput() {  	
    	if (isNumeric(value)) {
    		input.add(Double.parseDouble(displayField.getText()));
    	}	
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
