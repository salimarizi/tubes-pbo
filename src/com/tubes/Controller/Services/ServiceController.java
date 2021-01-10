package com.tubes.Controller.Services;


import com.jfoenix.controls.JFXButton;
import com.tubes.Controller.Spareparts.FormSparepartController;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.DAO.ServicesDAO;
import com.tubes.Model.ServicesEntity;
import com.tubes.Model.VehiclesEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


public class ServiceController {
    public JFXButton btnService;
    public JFXButton btnSparepart;
    public JFXButton btnUser;
    public JFXButton btnLogout;
    public JFXButton btnAddData;
    public JFXButton btnEditData;
    public JFXButton btnDeleteData;
    public TableView tbData;
    public TableColumn<ServicesEntity, Timestamp> clDate;
    public TableColumn<ServicesEntity, VehiclesEntity> clVehicle;
    public TableColumn<ServicesEntity, String> clTechnician;
    public String modalType;
    public static ObservableList<ServicesEntity> services;
    public static ServicesDAO servicesDAO = new ServicesDAO();

    public void initialize(){ this.refreshData();}

    public void refreshData() {
        services = FXCollections.observableArrayList();
        services.addAll(servicesDAO.fetchAll());

        tbData.setItems(services);
        clDate.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getDate()));
        clVehicle.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getVehiclesByVehicleId().getName()));
        clTechnician.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUsersByTechnicianId().getName()));
    }

    public void addService(ActionEvent actionEvent) {
        this.modalType = "add";
        showFormService();
    }

    public void showFormService() {
        try {
            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(SparepartController.class.getResource("../../View/Services/ServiceForm.fxml"));
            Parent root = fxml.load();
            FormServiceController modal_match = fxml.getController();
            modal_match.setController(this);

            stage.setTitle(this.modalType == "add" ? "Add New Service" : "Update Service");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editService(ActionEvent actionEvent) {
        this.modalType = "edit";
        showFormService();
    }

    public void deleteService(ActionEvent actionEvent) {
        ServicesEntity service = this.getSelectedService();

        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Data");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure want to delete this data?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            servicesDAO.deleteData(service);
            this.refreshData();
            alert.close();
        } else {
            alert.close();
        }

    }

    public ServicesEntity getSelectedService() {
        return  (ServicesEntity) tbData.getSelectionModel().getSelectedItem();
    }

    public void showServicePage(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../View/Services/ServiceLayout.fxml"));
            stage.setTitle("Services Data");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSparepartPage(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../View/Spareparts/SparepartLayout.fxml"));
            stage.setTitle("Spareparts Data");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUserPage(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../View/Users/UserLayout.fxml"));
            stage.setTitle("Users Data");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLogOut(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../View/LoginLayout.fxml"));
            stage.setTitle("Login Tubes");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showVehiclePage(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../View/Vehicles/VehicleLayout.fxml"));
            stage.setTitle("Vehicles Data");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
