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

    public LoanController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Label errorLabel;

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
        //TODO: Store all the data into the database
    }

    @FXML
    void clearAction(ActionEvent event) {
        numberOfYears.setText("");
        loanAmount.setText("");
        monthlyPayment.setText("");
        totalPayment.setText("");
        CarTotalCost.setText("");
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

            double loan;
            if(loanAmount.getText().charAt(0) == '†') {
                loan = Double.parseDouble(String.valueOf(customer.getCarPrice()));
            } else loan = Double.parseDouble(loanAmount.getText());

            loan -= requiredDownPayment;

            /** Achieving access to the model's functions */
            loanModel = new LoanModel(interest, year, loan);
            monthlyPayment.setText(String.format("$%.2f", loanModel.getMonthlyPayment()));
            totalPayment.setText(String.format("$%.2f", loanModel.getTotalPayment()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Depending on the grossly, the interest rate will be displayed
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

    public void calcInterest() {
        if (customer.getCarType() != null) {
            if ((customer.getGrossPay() * 1.5) > customer.getCarPrice()) {
                annualInterestRate.setText("4.4%");
                requiredDownPayment = customer.getCarPrice() * 0.07;
                downPayment.setText("Required: $" + requiredDownPayment);

            } else {
                annualInterestRate.setText("5.8%");
                requiredDownPayment = (customer.getCarPrice() * 0.05);
                downPayment.setText("Required: $" + requiredDownPayment );
            }
            numberOfYears.setText("5");
            loanAmount.setText("† FULL COVERAGE APPROVED");
        }
    }

    private boolean fieldsAreValid() {
        // We will check the contents of our fields
        if (annualInterestRate.getText().isEmpty()
                || numberOfYears.getText().isEmpty() || downPayment.getText().isEmpty()) {
            errorLabel.setText("Please fill the required fields");
            return false;
        }
        return true;
    }
}
