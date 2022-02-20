package view;

//import java.awt.*;

import controller.DataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class HomeBoundary implements Initializable {
    @FXML
    public TextField temp;
    @FXML
    public TextField humi;
    @FXML
    public TextField time;
    DataController dataController = new DataController();
    Thread t1 = new Thread(dataController);
    public HomeBoundary() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void startButtonClicked(ActionEvent event) throws IOException {

        dataController.temp = temp.getText();
        dataController.humi = humi.getText();
        dataController.time = Long.parseLong(time.getText());

        if(t1.isAlive()) t1.resume();
        else t1.start();

        ButtonType loginButtonType = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Alert Dialog");
        dialog.setContentText("Start send data !");
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
        dialog.showAndWait();
    }
    public void stopButtonClicked(ActionEvent event) throws IOException {
        t1.suspend();
        ButtonType loginButtonType = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Alert Dialog");
        dialog.setContentText("Stop send data !");
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
        dialog.showAndWait();
    }
}