package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {


//	@FXML
//	Label IncomeLbl;
//
//	@FXML
//	Label ExpensesLbl;
//
//	@FXML
//	Label CreditScoreLbl;
//
//	@FXML
//	Label HouseCostLbl;
//	
	
	@FXML
	TextField txtIncome;
	
	@FXML
	TextField txtExpenses;
	
	@FXML
	TextField txtCreditScore;
	
	@FXML
	TextField txtHouseCost;
	
	@FXML
	ComboBox cmbTerm;
	
	@FXML
	Label MortgagePaymentLbl;
	
	
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
		ObservableList<String> comboBox = FXCollections.observableArrayList("15 Year", "30 Year");
		cmbTerm.setItems(comboBox);
	}
	
	@FXML
	public void handleCalculate() {
		// When this button is pressed, the interest rate is calculated
		
		// Placeholder
		MortgagePaymentLbl.setText("");
		
		double income = Double.parseDouble(txtIncome.getText());
		double expenses = Double.parseDouble(txtExpenses.getText());
		int credit = (int) Double.parseDouble(txtCreditScore.getText());
		double house = Double.parseDouble(txtHouseCost.getText());
		
		//Calculate rate R based on Credit Score
		Rate r = new Rate(credit);
		
		// Calculate PMT
		
	}


    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
   
}