package com.test.controller;

        import com.test.view.ViewFactory;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.input.MouseEvent;

        import java.net.URL;
        import java.util.ResourceBundle;

public class InvoiceController extends BaseController implements Initializable {

    @FXML
    private ChoiceBox<String> creditDebit;

    public InvoiceController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void payment(MouseEvent event) {
        creditDebit.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
