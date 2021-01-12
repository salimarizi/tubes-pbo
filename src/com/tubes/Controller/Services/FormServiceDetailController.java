package com.tubes.Controller.Services;

import com.jfoenix.controls.JFXComboBox;
import com.tubes.DAO.ServiceSparepartRelationsDAO;
import com.tubes.DAO.SparepartsDAO;
import com.tubes.Model.ServiceSparepartRelationsEntity;
import com.tubes.Model.ServicesEntity;
import com.tubes.Model.SparepartsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class FormServiceDetailController {
    public JFXComboBox cbSparepart;
    public static ObservableList<SparepartsEntity> spareparts = FXCollections.observableArrayList();
    public SparepartsDAO sparepartsDAO = new SparepartsDAO();

    ServiceDetailController main;
    ServicesEntity service;

    public void setController(ServiceDetailController main) {
        service = main.getSelectedService();
        spareparts.clear();
        spareparts.addAll(sparepartsDAO.fetchAll());
        cbSparepart.setItems(spareparts);
    }

    public void submitData(ActionEvent actionEvent) {
        SparepartsEntity sparepart = (SparepartsEntity)cbSparepart.getValue();
        sparepart.setQuantity(sparepart.getQuantity() - 1);
        sparepartsDAO.editData(sparepart);

        ServiceSparepartRelationsEntity sparepartRelationsEntity = new ServiceSparepartRelationsEntity();
        sparepartRelationsEntity.setSparepartsBySparepartId(sparepart);
        sparepartRelationsEntity.setServicesByServiceId(service);

        ServiceSparepartRelationsDAO relationsDAO = new ServiceSparepartRelationsDAO();
        relationsDAO.insertData(sparepartRelationsEntity);

        main.refreshData();
        this.closeForm(actionEvent);
    }

    public void cancelProcess(ActionEvent actionEvent) {
        this.closeForm(actionEvent);
    }

    public void closeForm(ActionEvent actionEvent){
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
