package com.tubes.Controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tubes.Controller.Services.ServiceController;
import com.tubes.Model.ServicesEntity;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;


public class FormServiceController {/*
    public JFXButton btnSubmit;
    public JFXButton btnCancel;
    public JFXComboBox cbKendaraan;
    public JFXComboBox cbTeknisi;
    public DatePicker dpTanggal;
    public JFXTextArea txtKeluhan;
    public JFXTextArea txtTindakan;
    ServiceController main;


    public void setController(ServiceController serviceController) {
        this.main = main;
        if (main.modalType.equals("edit")){
            dpTanggal.setValue(main.getSelectedService().getDate());
            *//*cbKendaraan.setItems(main.getSelectedService().get());*//*
            *//*cbTeknisi.setItems(main.getSelectedService().get());*//*

        }
    }

    public void submitData(ActionEvent actionEvent) {
        if (main.modalType.equals("add")) {
            ServicesEntity service = new ServicesEntity(dpTanggal.getValue(),);
            System.out.println(service);
            main.servicesDAO.insertData(service);
        }else{
            ServicesEntity service = new ServicesEntity(this.main.getSelectedService().getId(),)
            main.servicesDAO.editData(service);
        }
        this.main.refreshData();
    }

    public void cancelProcess(ActionEvent actionEvent) {
    }*/
}
