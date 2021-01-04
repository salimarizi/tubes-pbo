package com.tubes.Controller.Spareparts;

import com.jfoenix.controls.JFXButton;
import com.tubes.Controller.Spareparts.FormSparepartController;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.DAO.SparepartsDAO;
import com.tubes.DAO.UsersDAO;
import com.tubes.Model.SparepartsEntity;
import com.tubes.Model.UsersEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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

    public void initialize(){
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
}
