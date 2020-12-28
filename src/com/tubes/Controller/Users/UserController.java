package com.tubes.Controller.Users;

import com.jfoenix.controls.JFXButton;
import com.tubes.DAO.UsersDAO;
import com.tubes.Model.UsersEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UserController {
    public JFXButton btnService;
    public JFXButton btnSparepart;
    public JFXButton btnUser;
    public JFXButton btnLogout;
    public JFXButton btnAddData;
    public JFXButton btnEditData;
    public JFXButton btnDeleteData;
    public TableView tbData;
    public TableColumn<UsersEntity, String> clName;
    public TableColumn<UsersEntity, String> clUsername;
    public TableColumn<UsersEntity, String> clTelepon;
    public TableColumn<UsersEntity, String> clAlamat;

    String modalType;
    public static ObservableList<UsersEntity> users;
    public static UsersDAO usersDAO = new UsersDAO();

    public void initialize(){
        this.refreshData();
    }

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

    public void refreshData() {
        users = FXCollections.observableArrayList();

        users.addAll(usersDAO.fetchAll());

        tbData.setItems(users);
        clName.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getName()));
        clUsername.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUsername()));
        clTelepon.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPhone()));
        clAlamat.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getAddress()));
    }

    public UsersEntity getSelectedUser(){ return (UsersEntity) tbData.getSelectionModel().getSelectedItem(); }

    public void addUser(ActionEvent actionEvent) {
        this.modalType = "add";
        showFormUser();
    }

    public void editUser(ActionEvent actionEvent) {
        this.modalType = "edit";
        showFormUser();

    }

    public void deleteUser(ActionEvent actionEvent) {
        UsersEntity user = this.getSelectedUser();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Data");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure want to delete this data?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            usersDAO.deleteData(user);
            this.refreshData();
            alert.close();
        } else {
            alert.close();
        }
    }
}
