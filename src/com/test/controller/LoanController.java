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
            double interest = Double.parseDouble(annualInterestRate.getText());
            int year = Integer.parseInt(numberOfYears.getText());
            double loan = Double.parseDouble(loanAmount.getText());

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
            downPayment.setText("$"+customer.formatCarPrice());
        } catch (SQLException e) {
            e.printStackTrace();
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
