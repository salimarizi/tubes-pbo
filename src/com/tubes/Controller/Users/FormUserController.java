package com.tubes.Controller.Users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tubes.Model.UsersEntity;
import javafx.event.ActionEvent;

public class FormUserController {
    public JFXTextField txtNama;
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXTextField txtTelepon;
    public JFXTextArea txtAlamat;
    public JFXButton btnSubmit;
    public JFXButton btnCancel;
    UserController main;

    public void setController(UserController main){
        this.main = main;
    }

    public void submitData(ActionEvent actionEvent) {
        if (main.modalType.equals("add")){
            UsersEntity user = new UsersEntity(txtNama.getText(), txtUsername.getText(), txtPassword.getText(), txtTelepon.getText(), txtAlamat.getText(), "member");
            main.usersDAO.insertData(user);
        }else {
            UsersEntity user = new UsersEntity(this.main.getSelectedUser().getId(), txtNama.getText(), txtUsername.getText(), txtPassword.getText(), txtTelepon.getText(), txtAlamat.getText(), "member");
            main.usersDAO.editData(user);
        }
        this.main.refreshData();
    }

    public void cancelProcess(ActionEvent actionEvent) {
    }
}
