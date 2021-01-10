package com.tubes.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.tubes.DAO.JdbcDao;
import com.tubes.DAO.UsersDAO;
import com.tubes.Model.UsersEntity;
import com.tubes.Utility.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {
    public ImageView imageView;
    public JFXTextField usernameField;
    public JFXPasswordField passwordField;
    public JFXButton submitButton;
    String username;

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

        UsersDAO user = new UsersDAO();
        List<UsersEntity> users = user.login(username, password);

        if(users.size() == 0) {
            infoBox("Please enter correct Username and Password", null, "Failed");
        }else {
            UsersEntity currentUser = users.get(0);
            UserSession.getInstace(currentUser.getId(), currentUser.getName(), currentUser.getUsername(), currentUser.getRole());

            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../View/DashboardLayout.fxml"));
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
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
