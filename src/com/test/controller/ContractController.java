package com.test.controller;

import com.test.model.Customer;
import com.test.model.SalesProcessDAO;
import com.test.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import static com.test.controller.DashBoardController.customerList;
import static com.test.controller.DashBoardController.index;

import java.time.LocalDate;


public class ContractController extends BaseController implements Initializable {
    Customer customer = customerList.get(index);

    public ContractController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private TextField dateOfSale;

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    @FXML
    private TextField invoiceNo;

    @FXML
    private TextField buyerNameV;

    @FXML
    private TextField mailingAddressV;

    @FXML
    private TextField CityStateZipV;

    @FXML
    private TextField NumberV;

    @FXML
    private TextField VINv;

    @FXML
    private TextField MakeV;

    @FXML
    private TextField ModelV;

    @FXML
    private TextField MileageV;

    @FXML
    private TextField ColorV;

    @FXML
    private TextField YearV;

    @FXML
    private TextField PriceV;

    @FXML
    private Label deposit;

    @FXML
    private TextField TotalV;

    @FXML
    private TextField LoanBalanceV;

    private String bankNote = "CHECK #";
    @FXML
    void CashCheckBox(MouseEvent event) {
        bankNote = "CASH";
    }

    @FXML
    private TextField CheckNumberV;

    private String vinOfPurchase;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateOfSale.setText(String.valueOf(getLocalDate()));
        invoiceNo.setText("4032"); // I had set this DB column to auto-increment, however last minute constraints leaves me to hard code a high number to simulate a working business

        try {
            ResultSet resultSet = SalesProcessDAO.contractSection1();
            while (resultSet.next()) {
                buyerNameV.setText(resultSet.getString("full_name"));
                mailingAddressV.setText(resultSet.getString("address1"));
                CityStateZipV.setText(resultSet.getString("city") + ", " +
                        resultSet.getString("state") + ", " +
                        resultSet.getString("zip"));
                NumberV.setText(resultSet.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILED ->   SalesProcessDAO.contractSection1() ");
        }

        try {
            ResultSet resultSet = SalesProcessDAO.contractSection2();
            while (resultSet.next()) {
                VINv.setText(resultSet.getString("vin"));
                MakeV.setText(resultSet.getString("make"));
                ModelV.setText(resultSet.getString("model"));
                MileageV.setText(resultSet.getString("mileage") + " miles");
                ColorV.setText(resultSet.getString("color"));
                YearV.setText(resultSet.getString("year"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILED ->   SalesProcessDAO.contractSection2() ");
        }

        PriceV.setText("$" + customer.formatCarPrice());
        TotalV.setText("$" + customer.formatCarPrice());
        deposit.setText("$" + customer.formatDownPayment());

        if (customer.getLoan() != 0.00)
            LoanBalanceV.setText("$" + customer.formatloan());
        else
            LoanBalanceV.setText("REFUSE");

        vinOfPurchase = VINv.getText();
    }

    @FXML
    void finalize(ActionEvent event) {
        // (invoice, date, total, loan, deposit, banknote, vin, ssn, employee_id)
        String finalSale = invoiceNo.getText().trim() + "', '" + dateOfSale.getText().trim() + "', '" + TotalV.getText().trim()
                + "', '" + LoanBalanceV.getText().trim() + "', '" + deposit.getText().trim() + "', '" + bankNote + CheckNumberV.getText().trim()
                + "', '" + vinOfPurchase + "', '" + customer.getSsn() + "', '";

        try {
            SalesProcessDAO.finalization(finalSale);
// TODO: At this point I can, if I want, close the windows but I need to demonstrate the CSS via options window

            Stage stage = (Stage) dateOfSale.getScene().getWindow();
            viewFactory.closeStage(stage);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("finalize function failed in ContractController.java");
        }
    }
}