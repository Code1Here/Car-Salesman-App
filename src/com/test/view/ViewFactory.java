package com.test.view;

import com.test.controller.BaseController;
import com.test.controller.ClientDetailsController;
import com.test.controller.InvoiceController;
import com.test.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class ViewFactory {

    private ArrayList<Stage> activeStages;

    public ViewFactory() {
        activeStages = new ArrayList<>();
    }

    // View Client Details handling:
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public void showLoginWindow() { // all of these constructors had emailManager objects in constructor
        BaseController controller = new LoginController(this, "login.fxml");
        initializeStage(controller);
    }

    public void showClientDetailsWindow() {
        BaseController controller = new InvoiceController(this, "ClientDetails.fxml");
        initializeStage(controller);
    }

    public void showInvoiceWindow() {
        BaseController controller = new ClientDetailsController(this, "Invoice.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();

  // ?? -->>    The reason for this: I want to have a list of active stages when we will work with the overall CSS of all active windows

        activeStages.remove(stageToClose); // This way we always have a list of active stages. Now we can iterate through it in the view factory
    }

    public void updateStyles() { // In javaFX CSS styles are applied to the scenes which need a list of scenes ( meaning all the active scenes running in the program. This is easy to do because we have the initialize-stage and closeStage methods
        for (Stage stage: activeStages){
            Scene scene = stage.getScene();
            // Now we can handle the css classes. When we go for each scene and update its styles, we first have to start by clearing the current stylesheets
            scene.getStylesheets().clear();    // Now we can the new stylesheet that we get from our enums: ColorTheme and FontSize
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm()); // The enum will tell us what the colortheme is but we must make a path between the enum and the CSS file it represents; and write it within getResource(). We create a static method to handle this within the enum class.
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }

}
/*
This view factory will pass the view events to the sales manager, which will do all the work of the request
 */