package com.tubes.Controller.Vehicles;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.Controller.Vehicles.VehicleController;
import com.tubes.DAO.UsersDAO;
import com.tubes.Model.SparepartsEntity;
import com.tubes.Model.UsersEntity;
import com.tubes.Model.VehiclesEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.math.BigDecimal;

public class FormVehicleController {
    public JFXTextField txtNama;
    public JFXTextField txtPlatNomor;
    public JFXTextField txtWarna;
    public JFXButton btnSubmit;
    public JFXButton btnCancel;
    public JFXComboBox cbJenisKendaraan;
    public JFXComboBox cbPemilik;
    VehicleController main;
    public static ObservableList<String> jenis = FXCollections.observableArrayList();
    public static ObservableList<UsersEntity> pemilik = FXCollections.observableArrayList();
    public static UsersDAO userDAO = new UsersDAO();


    public void setController(VehicleController main) {
        this.main = main;

        jenis.clear();
        jenis.add("Car");
        jenis.add("Motorcycle");
        cbJenisKendaraan.setItems(jenis);

        pemilik.clear();
        pemilik.addAll(userDAO.fetchAll());
        cbPemilik.setItems(pemilik);

        if (main.modalType.equals("edit")){
            txtNama.setText(main.getSelectedVehicle().getName());
            txtPlatNomor.setText(main.getSelectedVehicle().getPoliceNumber());
            txtWarna.setText(main.getSelectedVehicle().getColor());
        }
    }
    public void submitData(ActionEvent actionEvent) {
        if (main.modalType.equals("add")){
            VehiclesEntity vehicle = new VehiclesEntity(txtNama.getText(),txtPlatNomor.getText(), txtWarna.getText(),cbJenisKendaraan.getValue().toString(), (UsersEntity)cbPemilik.getValue());
            System.out.println(vehicle);
            main.vehiclesDAO.insertData(vehicle);
        }else {
            VehiclesEntity vehicle = new VehiclesEntity(main.getSelectedVehicle().getId(), txtNama.getText(),txtPlatNomor.getText(), txtWarna.getText(),cbJenisKendaraan.getValue().toString(), (UsersEntity)cbPemilik.getValue());
            main.vehiclesDAO.editData(vehicle);
        }
        this.main.refreshData();
    }

    public void cancelProcess(ActionEvent actionEvent) {
    }
}
