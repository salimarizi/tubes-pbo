package com.tubes.Controller.Vehicles;

import com.jfoenix.controls.JFXButton;
import com.tubes.Controller.Spareparts.FormSparepartController;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.Controller.Vehicles.FormVehicleController;
import com.tubes.Controller.Vehicles.VehicleController;
import com.tubes.DAO.VehiclesDAO;
import com.tubes.DAO.UsersDAO;
import com.tubes.Model.VehiclesEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class VehicleController {
    public JFXButton btnAddData;
    public JFXButton btnEditData;
    public JFXButton btnDeleteData;
    public TableView tbData;
    public TableColumn<VehiclesEntity, String> clName;
    public TableColumn<VehiclesEntity, String> clPlatNomor;
    public TableColumn<VehiclesEntity, String> clPemilik;
    public String modalType;
    public static ObservableList<VehiclesEntity> vehicle;
    public static VehiclesDAO vehiclesDAO = new VehiclesDAO();
    public static UsersDAO userDAO = new UsersDAO();

    public void initialize(){
        this.refreshData();
    }

    public void refreshData() {
        vehicle = FXCollections.observableArrayList();
        vehicle.addAll(vehiclesDAO.fetchAll());
        tbData.setItems(vehicle);
        clName.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getName()));
        clPlatNomor.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPoliceNumber()));
        clPemilik.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUsersByUserId()));
    }


    public void addVehicle(ActionEvent actionEvent) {
        this.modalType = "add";
        showFormVehicle();
    }

    public void showFormVehicle() {
        try {
            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(VehicleController.class.getResource("../../View/Vehicles/VehicleForm.fxml"));
            Parent root = fxml.load();
            FormVehicleController modal_match = fxml.getController();
            modal_match.setController(this);

            stage.setTitle(this.modalType == "add" ? "Add New Vehicle" : "Update Vehicle");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editVehicle(ActionEvent actionEvent) {
        this.modalType = "edit";
        showFormVehicle();
    }

    public void deleteVehicle(ActionEvent actionEvent) {
        VehiclesEntity vehicles = this.getSelectedVehicle();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Data");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure want to delete this data?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            vehiclesDAO.deleteData(vehicles);
            this.refreshData();
            alert.close();
        }else{
            alert.close();
        }
    }

    public VehiclesEntity getSelectedVehicle() { return (VehiclesEntity) tbData.getSelectionModel().getSelectedItem();
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

    public void showDashboard(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../View/DashboardLayout.fxml"));
            stage.setTitle("Dashboard");
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
