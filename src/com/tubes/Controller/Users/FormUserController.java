package com.tubes.Controller.Users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tubes.Model.UsersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class FormUserController {
    public JFXTextField txtNama;
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXTextField txtTelepon;
    public JFXTextArea txtAlamat;
    public JFXButton btnSubmit;
    public JFXButton btnCancel;
    public JFXComboBox cbRole;
    UserController main;

    public static ObservableList<String> roles = FXCollections.observableArrayList();

    public void setController(UserController main){
        this.main = main;

        roles.clear();
        roles.add("admin");
        roles.add("technician");
        roles.add("member");

        cbRole.setItems(roles);
        if (main.modalType.equals("edit")){
            txtNama.setText(main.getSelectedUser().getName());
            txtUsername.setText(main.getSelectedUser().getUsername());
            txtPassword.setText(main.getSelectedUser().getPassword());
            txtTelepon.setText(main.getSelectedUser().getPhone());
            txtAlamat.setText(main.getSelectedUser().getAddress());
        }
    }

    public void submitData(ActionEvent actionEvent) {
        if (main.modalType.equals("add")){
            UsersEntity user = new UsersEntity(txtNama.getText(), txtUsername.getText(), txtPassword.getText(), txtTelepon.getText(), txtAlamat.getText(), cbRole.getValue().toString());
            System.out.println(user);
            main.usersDAO.insertData(user);
        }else {
            UsersEntity user = new UsersEntity(this.main.getSelectedUser().getId(), txtNama.getText(), txtUsername.getText(), txtPassword.getText(), txtTelepon.getText(), txtAlamat.getText(), "member");
            main.usersDAO.editData(user);
        }
        this.main.refreshData();
        closeForm(actionEvent);
    }

    public void cancelProcess(ActionEvent actionEvent) {
        closeForm(actionEvent);
    }

    public void closeForm(ActionEvent actionEvent){
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
