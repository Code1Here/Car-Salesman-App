package com.test.controller;

import com.test.model.Customer;
import com.test.model.LoanModel;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.test.controller.DashBoardController.customerList;
import static com.test.controller.DashBoardController.index;

public class LoanController extends BaseController implements Initializable {

    Customer customer;
    LoanModel loanModel;
    double requiredDownPayment;
    double promo;

    public LoanController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Label CarTotalCost;

    @FXML
    private TextField annualInterestRate;

    @FXML
    private TextField numberOfYears;

    @FXML
    private TextField loanAmount;

    @FXML
    private TextField downPayment;

    @FXML
    private TextField monthlyPayment;

    @FXML
    private TextField totalPayment;

    @FXML
    void openContract(ActionEvent event) {
        viewFactory.showContractWindow();
        //TODO: Initialize all the data onto the contract
    }

    @FXML
    void clearAction(ActionEvent event) {
        numberOfYears.setText("");
        downPayment.setText("");
        monthlyPayment.setText("");
        totalPayment.setText("");
        promoLabel.setText("");
    }
    @FXML
    private Label promoLabel;
    @FXML
    void promoAction(ActionEvent event) {
        if(promo > 0) { // Precondition: MUST press 're-calculate' once after initialization
            promoLabel.setText("Christmas promo. w/ min. $" + String.valueOf(promo) + " deposit!");
            promoLabel.setStyle("-fx-background-color: black;");
        }
    }
    @FXML
    void closeAction(ActionEvent event) {
        Stage stage = (Stage) annualInterestRate.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void optionsAction(ActionEvent event) {
        viewFactory.showOptionsWindow();
    }

    @FXML
    void calculate(ActionEvent event) throws InterruptedException {
        if (fieldsAreValid()) { // Get values from text fields
            /** Data to instantiate loanModel */
            double interest = Double.parseDouble(annualInterestRate.getText().substring(0, annualInterestRate.getText().length() - 1));
            int year = Integer.parseInt(numberOfYears.getText());
            double loan = Double.parseDouble(String.valueOf(customer.getCarPrice()));

            double markup  = 0;
            if (downPayment.getText().charAt(0) != 'R') {
                markup  = Double.parseDouble(downPayment.getText());
            }

            promo = requiredDownPayment + 3000.00;

            if (markup  >= promo) { // If the customer is willing to pay over $3k over the requirement
                interest -= 0.7; // We will lower their interest rate of the loan
                annualInterestRate.setText(String.valueOf(interest + "%"));
                loan -= markup ;
                customer.setDownPayment((float) markup);
            } else {
                loan -= requiredDownPayment;
                customer.setDownPayment((float) requiredDownPayment);
            }
            customer.setLoan((float) loan);


            /** Achieving access to the model's functions */
            loanModel = new LoanModel(interest, year, loan);
            monthlyPayment.setText(String.format("$%.2f", loanModel.getMonthlyPayment()));
            totalPayment.setText(String.format("$%.2f", loanModel.getTotalPayment()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Depending on the gross income, the interest rate will be displayed
        try {
            customer = customerList.get(index);
            customer.setCarPrice(SalesProcessDAO.calculatorDefault());
            // Text in the bottom
            CarTotalCost.setText(" Total cost of inquired product: $" + customer.formatCarPrice());
            // Calculate interest rate based on annual income
            // Also Generate a pre-approval
            calcInterest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calcInterest() { // Upon initialization, but not the special
        if (customer.getCarType() != null) {
            if ((customer.getGrossPay() * 0.8) > customer.getCarPrice()) {
                annualInterestRate.setText("4.4%");
                requiredDownPayment = customer.getCarPrice() * 0.07;
            } else {
                annualInterestRate.setText("5.8%");
                requiredDownPayment = (customer.getCarPrice() * 0.05);
            }

            downPayment.setText("Required: $" + requiredDownPayment);
            numberOfYears.setText("5");
            loanAmount.setText("† FULLY PRE-APPROVED");
        }
    }

    private boolean fieldsAreValid() {
        // We will check the contents of our fields
        if (annualInterestRate.getText().isEmpty()
                || numberOfYears.getText().isEmpty() || downPayment.getText().isEmpty()) {
            promoLabel.setText("Please fill the required fields");
            promoLabel.setStyle("-fx-background-color: black;");
            return false;
        }
        return true;
    }
}
