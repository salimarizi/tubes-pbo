package com.tubes.Controller.Services;

import com.jfoenix.controls.JFXButton;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.DAO.SparepartsDAO;
import com.tubes.Model.ServicesEntity;
import com.tubes.Model.SparepartsEntity;
import com.tubes.Utility.UserSession;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.internal.SessionImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.tubes.Utility.HibernateUtil.getSession;

public class ServiceDetailController {
    public Label lbTanggal;
    public Label lbTeknisi;
    public Label lbKendaraan;
    public Label lbKeluhan;
    public Label lbTindakan;
    public TableView tbData;
    public TableColumn<SparepartsEntity, String> clSparepartName;
    public TableColumn<SparepartsEntity, String> clSparepartPrice;
    public JFXButton btnVehicle;
    public JFXButton btnService;
    public JFXButton btnSparepart;
    public JFXButton btnUser;
    public JFXButton btnReports;
    public Label username;
    UserSession user = UserSession.getInstace();

    ServiceController main;
    ServicesEntity selectedService;
    public static SparepartsDAO sparepartsDAO = new SparepartsDAO();
    public static ObservableList<SparepartsEntity> spareparts;

    public void refreshData(){
        spareparts = FXCollections.observableArrayList();
        spareparts.addAll(sparepartsDAO.fetchServiceSparepart(selectedService.getId()));

        tbData.setItems(spareparts);
        clSparepartName.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getName()));
        clSparepartPrice.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPrice()));
    }
    
    public void setController(ServiceController main) {
        this.main = main;
        selectedService = main.getSelectedService();

        main.getSelectedService();
        lbTanggal.setText(main.getSelectedService().getDate().toString());
        lbTeknisi.setText(main.getSelectedService().getUsersByTechnicianId().getName());
        lbKendaraan.setText(main.getSelectedService().getVehiclesByVehicleId().getName());
        lbKeluhan.setText(main.getSelectedService().getProblem());
        lbTindakan.setText(main.getSelectedService().getAction());

        this.refreshData();

        username.setText(user.getName());
        if (user.getRole().equals("member")){
            btnService.setManaged(false);
            btnService.setVisible(false);
            btnSparepart.setManaged(false);
            btnSparepart.setVisible(false);
            btnUser.setManaged(false);
            btnUser.setVisible(false);
            btnVehicle.setManaged(false);
            btnVehicle.setVisible(false);
            btnVehicle.setManaged(false);
            btnVehicle.setVisible(false);
            btnReports.setManaged(false);
            btnReports.setVisible(false);
        }else if (user.getRole().equals("technician")){
            btnUser.setManaged(false);
            btnUser.setVisible(false);
            btnSparepart.setManaged(false);
            btnSparepart.setVisible(false);
        }
    }

    public ServicesEntity getSelectedService(){
        return selectedService;
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

    public void addServiceDetail(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(SparepartController.class.getResource("../../View/Services/Details/ServiceDetailForm.fxml"));
            Parent root = fxml.load();
            FormServiceDetailController modal_match = fxml.getController();
            modal_match.setController(this);

            stage.setTitle("Tambah Sparepart");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteServiceDetail(ActionEvent actionEvent) {
        ServicesEntity service = this.getSelectedService();

        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Data");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure want to delete this data?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            this.refreshData();
            alert.close();
        } else {
            alert.close();
        }
    }

    public void showReports(ActionEvent actionEvent) {
        JasperPrint jp;
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/ReportBengkel.jasper", param, ((SessionImpl)getSession()).connection());
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Laporan Marnat Bengkel");
            viewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
