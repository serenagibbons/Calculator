package calculator;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class CalculatorController {
	
	private String value;
	private String operator = "";
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
    	case "CE":
    		clearEntry();
        	break;
    	case "+/-":
    		negate();
    		break;
    	case "+":
    	case "-":
    	case "*":
    	case "/":
    		storeInput();
    		operator = ((Button)event.getSource()).getText();
    		
    		history += (displayField.getText() + value);
    		historyField.setText(history); // display input history
    		displayField.clear();
    		break;
    	case "=":
    		storeInput();

    		calculate();
    		
    		// clear display and show result
    		clear(event);
    		displayField.setText("" + result);
    		break;
    	default: 
    		displayField.setText(displayField.getText() + value); // display value entered
    		break;
    	} 
    }
    
    @FXML
    private void handleKeyboardInput(ActionEvent event) {
    		value = ((TextField)event.getSource()).getText();		// get text of keyboard input
        	displayField.setText(displayField.getText() + value);	// display value entered
    }
    
    private double calculate() {
    	result = input.get(0);
    	
    	for (int i = 0; i < input.size() - 1; i++) {
    	switch(operator) {
    	case "+":
        	result += input.get(i+1);
    		break;
    	case "-":
    		result -= input.get(i+1);
    		break;
    	case "*":
    		result *= input.get(i+1);
    		break;
    	case "/":
    		if (input.get(i+1) != 0)
    			result /= input.get(i+1);
    		break;
    	}
    	}
    	return result;
    }
    
    private void negate() {    	
    	double temp = Double.parseDouble(displayField.getText());

    	if (temp >= 0) {
    		displayField.setText("-" + displayField.getText()); // if input is positive, make negative
    	}
    	else {
    		displayField.setText(displayField.getText().substring(1)); // if input is negative, make positive
    	}
    	
    	input.set(input.indexOf(temp), 0-temp);    		
    }
    
    @FXML
    private void clear(ActionEvent event) {
    	displayField.clear();
    	historyField.setText("");
    	history = "";
    	input = new ArrayList<Double>();
    }
    
    private void clearEntry() { 
    	if (!input.isEmpty()) {
    		double lastEntry = Double.parseDouble(displayField.getText());

    		displayField.clear();
    		input.remove(input.indexOf(lastEntry));
    	}
    	else
    		displayField.clear();

    }

    private void storeInput() {  	
    	if (isNumeric(displayField.getText())) {
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
