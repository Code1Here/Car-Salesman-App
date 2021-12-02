package com.test.controller;

import com.test.model.LoanModel;
import com.test.view.ViewFactory;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoanController extends BaseController implements Initializable {
    public LoanController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    @FXML
    private Label errorLabel;

    @FXML
    private TextField downPayment;

    @FXML
    private Label preApprovedLoan;

    @FXML
    private TextField annualInterestRate;

    @FXML
    private TextField numberOfYears;

    @FXML
    private TextField loanAmount;

    @FXML
    private TextField monthlyPayment;

    @FXML
    private TextField totalPayment;

    @FXML
    void openContract(ActionEvent event) {

    }
    @FXML
    void clearAction(ActionEvent event) {
        annualInterestRate.setText("");
        numberOfYears.setText("");
        loanAmount.setText("");
        monthlyPayment.setText("");
        totalPayment.setText("");
        preApprovedLoan.setText("");
    }

    @FXML
    void closeAction(ActionEvent event) {
        Stage stage = (Stage) preApprovedLoan.getScene().getWindow();
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

            LoanModel loanModel = new LoanModel(interest, year, loan);

            // Display monthly payment and total payment
            monthlyPayment.setText(String.format("$%.2f", loanModel.getMonthlyPayment()));
            totalPayment.setText(String.format("$%.2f", loanModel.getTotalPayment()));
//        } else {
//            if(Thread.sleep(2000))
//            errorLabel.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Depending on the grossly, the interest rate will be displayed
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
