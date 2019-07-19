package calculator;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class CalculatorController {
	
	private String value;
	private String operator = "";
	private String history = "";
	private double result = 0;
	private final String[] symbols = {"*", "/", "+", "-", "="};
	private ArrayList<Double> input = new ArrayList<Double>();
	
	
    @FXML
    private TextField displayField;	// display field for current entry
    
    @FXML
    private Text historyField;	// display previous number and operator inputs
        
    @FXML
    // handle button inputs
    private void handleButtonInput(ActionEvent event) {
    	value = ((Button)event.getSource()).getText();			// get text of button input
    	
    	switch(value) {
    	case "C": // clear display and reset result
    		clear();
    		result = 0;
    		break;
    	case "CE":
    		clearEntry();
        	break;
    	case "+/-":
    		storeInput();
    		negate();
    		break;
    	case "=":
    		if (!displayField.getText().equals("")) 
    			showResult();
    		break;
    	case ".":
    		if (!displayField.getText().contains(".")) // only allow 1 decimal point input
    			displayField.setText(displayField.getText() + value);
    		break;
    	default: 
    		if (Arrays.asList(symbols).contains(value)) {
    			storeInput();
        		operator = value;
        		history += (displayField.getText() + value);
        		historyField.setText(history); // display input history
        		displayField.clear();
    		}
    		else
    			displayField.setText(displayField.getText() + value); // display value entered
    		break;
    	} 
    }
    
    @FXML
    // handle key typed when application selected
    private void handleKeyTyped(KeyEvent event) {
    	value = event.getCharacter();
    	
    	// if the value of the key pressed is numeric, display input in text field
    	if (isNumeric(value))
    		displayField.setText(displayField.getText() + value);
    	
       	// else if value is a decimal point display input
    	else if (value.equals(".")) {
    		if (!displayField.getText().contains(".")) // only allow 1 decimal point input
    			displayField.setText(displayField.getText() + value);
    	}
    			
    	// else if the value of the key pressed is not a letter 
    	else if (!isLetter(value)) {

    		if (value.equals("=")) {
    			if (!displayField.getText().equals(""))
    				showResult();
    		}
    		else if (Arrays.asList(symbols).contains(value)) {
    			// allow implementation of minus sign as negative sign or for subtraction
    			if (displayField.getText().isEmpty() && value.equals("-")) {
    				displayField.setText(displayField.getText() + value);
    			}
    			else {
    				storeInput();
    				operator = value;
    				history += (displayField.getText() + operator);
    				historyField.setText(history); // display input history
    				displayField.clear();
    			}
    		}

    	}
    }
    
    @FXML
    // handle key pressed for non-character keys
    private void handleKeyPressed(KeyEvent event) {
    	
    	if (event.getCode() == KeyCode.BACK_SPACE)
    		backspace();
    	else if (event.getCode() == KeyCode.ENTER & !input.isEmpty()) {
    		showResult();
    	}
    	
    }
    
    // perform calculations and return result
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
    
    // negate the current entry
    private void negate() {  
    	if (displayField.getText().equals("")) {
    		displayField.setText("-");	// if no input yet, insert negative sign
    	}
    	else if (displayField.getText().equals("-")) {
    		displayField.clear(); // if sole input is negative sign, clear display
    	}
    	else {
    		double temp = Double.parseDouble(displayField.getText());

    		if (temp >= 0) {
    			displayField.setText("-" + displayField.getText()); // if input is positive, make negative
    		}
    		else {
    			displayField.setText(displayField.getText().substring(1)); // if input is negative, make positive
    		}

    		input.remove(input.indexOf(temp));
    	}
    }
    
    // clear the current entry, history, and all stored input
    private void clear() {
    	displayField.clear();
    	historyField.setText("");
    	history = "";
    	input.clear();
    }
    
    // clear the current entry only
    private void clearEntry() { 
    	if (!input.isEmpty()) {
    		double lastEntry = Double.parseDouble(displayField.getText());

    		displayField.clear();
    		input.remove(input.indexOf(lastEntry));
    	}
    	else
    		displayField.clear();
    }

    // delete the last digit entered
    private void backspace() {
    	if (!displayField.getText().equals("")) {
    		String entry = displayField.getText();
    		displayField.setText(entry.substring(0, entry.length()-1));
    	}
    }

    // store numerical input in ArrayList input
    private void storeInput() {  	
    	if (isNumeric(displayField.getText())) {
    		input.add(Double.parseDouble(displayField.getText()));
    	}	
    }
    
    private void showResult() {
    	// store input, calculate, clear current display and show result
		storeInput();
		calculate();
		clear();
		displayField.setText("" + result);
    }
    
    // evaluate whether a string can be parsed into a Double
    private boolean isNumeric(String s) {
    	try {
    		Double.parseDouble(s);
    		return true;
    	}
    	catch (NumberFormatException e) {
    		return false;
    	}
    }
    
    // evaluate whether a string contains only letters
    private boolean isLetter(String s) {
    	boolean flag = false;
    	
    	for (int i = 0; i < s.length(); i++)
    	    if (java.lang.Character.isLetter(s.charAt(i)))
    	    	flag = true;
 
    	return flag;
    }
    
}
