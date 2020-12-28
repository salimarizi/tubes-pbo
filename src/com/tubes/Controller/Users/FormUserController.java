package com.tubes.Controller.Users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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

    }

    public void cancelProcess(ActionEvent actionEvent) {
    }
}
