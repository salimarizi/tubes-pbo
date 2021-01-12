package com.tubes.Controller;

import com.jfoenix.controls.JFXButton;
import com.tubes.DAO.ServicesDAO;
import com.tubes.DAO.UsersDAO;
import com.tubes.DAO.VehiclesDAO;
import com.tubes.Model.ServicesEntity;
import com.tubes.Model.UsersEntity;
import com.tubes.Model.VehiclesEntity;
import com.tubes.Utility.UserSession;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.internal.SessionImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static com.tubes.Utility.HibernateUtil.getSession;

public class DashboardController {
    public JFXButton btnService;
    public JFXButton btnSparepart;
    public JFXButton btnUser;
    public JFXButton btnReports;
    public JFXButton btnLogout;
    public Label username;
    public JFXButton btnVehicle;
    public TableView tbData;
    public TableColumn<ServicesEntity, Timestamp> clTanggal;
    public TableColumn<ServicesEntity, UsersEntity> clPelanggan;
    public TableColumn<ServicesEntity, VehiclesEntity> clKendaraan;
    public TableColumn<ServicesEntity, String> clKeluhan;

    public JFXButton bxJumlahService;
    public JFXButton bxJumlahPelanggan;
    public JFXButton bxJumlahKendaraan;

    public static ObservableList<ServicesEntity> services;

    public static ServicesDAO servicesDAO = new ServicesDAO();
    public static VehiclesDAO vehiclesDAO = new VehiclesDAO();
    public static UsersDAO usersDAO = new UsersDAO();

    UserSession user = UserSession.getInstace();

    public void initialize(){
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

            bxJumlahService.setVisible(false);
            bxJumlahKendaraan.setVisible(false);
            bxJumlahPelanggan.setVisible(false);
        }else if (user.getRole().equals("technician")){
            btnUser.setManaged(false);
            btnUser.setVisible(false);
            btnSparepart.setManaged(false);
            btnSparepart.setVisible(false);
        }

        bxJumlahService.setText("Jumlah Service \n " + servicesDAO.fetchAll().size());
        bxJumlahKendaraan.setText("Jumlah Kendaraan \n " + vehiclesDAO.fetchAll().size());
        bxJumlahPelanggan.setText("Jumlah User \n " + usersDAO.fetchAll().size());



        if (user.getRole().equals("member")){
            services = FXCollections.observableArrayList();
            services.addAll(servicesDAO.fetchUserService(user.getId()));

            tbData.setItems(services);
            clTanggal.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getDate()));
            clKendaraan.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getVehiclesByVehicleId().getName()));
            clPelanggan.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUsersByTechnicianId().getName()));
            clKeluhan.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getProblem()));
        }else {
            services = FXCollections.observableArrayList();
            services.addAll(servicesDAO.fetchAll());

            tbData.setItems(services);
            clTanggal.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getDate()));
            clKendaraan.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getVehiclesByVehicleId().getName()));
            clPelanggan.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUsersByTechnicianId().getName()));
            clKeluhan.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getProblem()));
        }
    }

    public void showUserPage(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../View/Users/UserLayout.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../View/Spareparts/SparepartLayout.fxml"));
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
            user.cleanUserSession();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../View/LoginLayout.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../View/Services/ServiceLayout.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../View/DashboardLayout.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../View/Vehicles/VehicleLayout.fxml"));
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
