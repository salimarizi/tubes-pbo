package com.tubes.Controller.Users;

import com.jfoenix.controls.JFXButton;
import com.tubes.DAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    public JFXButton btnService;
    public JFXButton btnSparepart;
    public JFXButton btnUser;
    public JFXButton btnLogout;
    public JFXButton btnAddData;
    public JFXButton btnEditData;
    public JFXButton btnDeleteData;
    String modalType;
    public static UsersDAO usersDAO = new UsersDAO();

    public void showFormUser() {
        try {
            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(UserController.class.getResource("../../View/Users/UserForm.fxml"));
            Parent root = fxml.load();
            FormUserController modal_match = fxml.getController();
            modal_match.setController(this);

            stage.setTitle(this.modalType == "add" ? "Add New User" : "Update User");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(ActionEvent actionEvent) {
        this.modalType = "add";
        showFormUser();
    }

    public void editUser(ActionEvent actionEvent) {
        this.modalType = "edit";
        showFormUser();
    }
}
