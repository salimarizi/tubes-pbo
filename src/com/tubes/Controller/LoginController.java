package com.tubes.Controller;

import com.tubes.DAO.JdbcDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.sql.SQLException;

public class LoginController {
    public ImageView imageView;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());

        if(usernameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(username, password);

        if(!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        }else {
            infoBox("Login Successful!", null, "Success");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void initialize(){
        Image image = new Image("file:/../assets/bengkel.png");
        imageView.setImage(image);
    }
}
