package com.tubes.Controller.Spareparts;

import com.jfoenix.controls.JFXButton;
import com.tubes.Controller.Spareparts.FormSparepartController;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.DAO.SparepartsDAO;
import com.tubes.DAO.UsersDAO;
import com.tubes.Model.SparepartsEntity;
import com.tubes.Model.UsersEntity;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class SparepartController {
    public JFXButton btnService;
    public JFXButton btnSparepart;
    public JFXButton btnUser;
    public JFXButton btnLogout;
    public JFXButton btnAddData;
    public JFXButton btnEditData;
    public JFXButton btnDeleteData;
    public TableView tbData;
    public TableColumn<SparepartsEntity, String> clName;
    public TableColumn<SparepartsEntity, String> clQuantity;
    public TableColumn<SparepartsEntity, String> clPrice;
    public String modalType;
    public static ObservableList<SparepartsEntity> spareparts;
    public static SparepartsDAO sparepartsDAO = new SparepartsDAO();
    public JFXButton btnVehicle;
    public JFXButton btnReports;
    public Label username;
    public ImageView imageView;
    UserSession user = UserSession.getInstace();

    public void initialize(){
        Image image = new Image("file:/../assets/Avatar.jpg");
        imageView.setImage(image);

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
        
        this.refreshData();
    }

    public void showFormSparepart() {
        try {
            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(SparepartController.class.getResource("../../View/Spareparts/SparepartForm.fxml"));
            Parent root = fxml.load();
            FormSparepartController modal_match = fxml.getController();
            modal_match.setController(this);

            stage.setTitle(this.modalType == "add" ? "Add New Sparepart" : "Update Sparepart");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshData() {
        spareparts = FXCollections.observableArrayList();

        spareparts.addAll(sparepartsDAO.fetchAll());

        tbData.setItems(spareparts);
        clName.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getName()));
        clQuantity.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getQuantity()));
        clPrice.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPrice()));
    }
    public SparepartsEntity getSelectedSparepart(){  return (SparepartsEntity) tbData.getSelectionModel().getSelectedItem(); }


    public void addSparepart(ActionEvent actionEvent) {
        this.modalType = "add";
        showFormSparepart();
    }

    public void editSparepart(ActionEvent actionEvent) {
        this.modalType = "edit";
        showFormSparepart();
    }

    public void deleteSparepart(ActionEvent actionEvent) {
        SparepartsEntity sparepart = this.getSelectedSparepart();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Data");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure want to delete this data?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            sparepartsDAO.deleteData(sparepart);
            this.refreshData();
            alert.close();
        } else {
            alert.close();
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
            user.cleanUserSession();
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
