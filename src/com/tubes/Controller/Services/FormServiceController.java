package com.tubes.Controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.tubes.Controller.Services.ServiceController;
import com.tubes.DAO.UsersDAO;
import com.tubes.DAO.VehiclesDAO;
import com.tubes.Model.ServicesEntity;
import com.tubes.Model.UsersEntity;
import com.tubes.Model.VehiclesEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.sql.Timestamp;


public class FormServiceController {
    public JFXComboBox cbKendaraan;
    public JFXComboBox cbTeknisi;
    public JFXTextArea txtKeluhan;
    public JFXTextArea txtTindakan;
    public JFXButton btnSubmit;
    public JFXButton btnCancel;
    ServiceController main;
    public static ObservableList<VehiclesEntity> kendaraan = FXCollections.observableArrayList();
    public static ObservableList<UsersEntity> teknisi = FXCollections.observableArrayList();
    public static VehiclesDAO vehiclesDAO = new VehiclesDAO();
    public static UsersDAO usersDAO = new UsersDAO();


    public void setController(ServiceController main) {
        this.main = main;

        kendaraan.clear();
        kendaraan.addAll(vehiclesDAO.fetchAll());
        cbKendaraan.setItems(kendaraan);

        teknisi.clear();
        teknisi.addAll(usersDAO.fetchAll());
        cbTeknisi.setItems(teknisi);

        if (main.modalType.equals("edit")){
            txtKeluhan.setText(this.main.getSelectedService().getProblem());
            txtTindakan.setText(this.main.getSelectedService().getAction());
        }
    }

    public void submitData(ActionEvent actionEvent) {
        VehiclesEntity vehicle = (VehiclesEntity)cbKendaraan.getValue();
        UsersEntity technician = (UsersEntity)cbTeknisi.getValue();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        String problem = txtKeluhan.getText();
        String action = txtTindakan.getText();

        if (main.modalType.equals("add")) {
            ServicesEntity service = new ServicesEntity(vehicle, technician, date, problem, action);
            System.out.println(service);
            main.servicesDAO.insertData(service);
        }else{
            ServicesEntity service = new ServicesEntity(this.main.getSelectedService().getId(), vehicle, technician, date, problem, action);
            main.servicesDAO.editData(service);
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
