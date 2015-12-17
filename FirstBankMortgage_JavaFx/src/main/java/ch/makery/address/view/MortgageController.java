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

import java.text.DecimalFormat;
import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {

	
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
		
		// Get payment term length
		int pmtTerm;
		if (cmbTerm.getValue() == "15 Year") {
			// 15 years times 12 monthly payments
			pmtTerm = 15*12;
		} 
		else if (cmbTerm.getValue() == "30 Year") {
			// 30 years times 12 monthly payments 
			pmtTerm = 30*12;
		} 
		else {
			pmtTerm = 0;
			System.out.println("ERROR");
		}
		
		
		String strPayment = setPayment(income, expenses, credit, house, pmtTerm);
		System.out.println(strPayment);
		MortgagePaymentLbl.setText(strPayment);
	}
	
	public static String setPayment(double income, double expenses, int credit, double house, int pmtTerm) {
		String pmtString;
		double payment;
		//Calculate rate r based on Credit Score
		Rate r;
		r = new Rate(credit, house, pmtTerm);

		// Make sure the input credit score is high enough
		if (r.getInterestRate() == 0){
			// Loan was not approved
			pmtString = "Credit score is too low";
			return pmtString;
		}

		// Calculate PMT
		payment = r.getPayment();

		if (payment<= 0.36*(income/12) && payment <= 0.28*(income/12 - expenses)) {
			DecimalFormat formatter = new DecimalFormat("#,###.00");
			pmtString = "Your monthly payment will be $" + formatter.format(payment);
		}
		else {
			// User cannot afford mortgage payments

			pmtString = "House cost too high";
		}
		return pmtString;
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