package com.tubes.Controller.Spareparts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.tubes.Controller.Spareparts.SparepartController;
import com.tubes.Model.SparepartsEntity;
import com.tubes.Model.UsersEntity;
import javafx.event.ActionEvent;

import java.math.BigDecimal;

public class FormSparepartController {
    public JFXTextField txtNama;
    public JFXTextField txtQuantity;
    public JFXTextField txtPrice;
    public JFXButton btnSubmit;
    public JFXButton btnCancel;
    SparepartController main;

    public void setController(SparepartController main) {
        this.main = main;
        if (main.modalType.equals("edit")){
            txtNama.setText(main.getSelectedSparepart().getName());
            txtQuantity.setText(String.valueOf(main.getSelectedSparepart().getQuantity()));
            txtPrice.setText(String.valueOf(main.getSelectedSparepart().getPrice()));
        }
    }

    public void submitData(ActionEvent actionEvent) {
        if (main.modalType.equals("add")){
            SparepartsEntity sparepart = new SparepartsEntity(txtNama.getText(), Integer.valueOf(txtQuantity.getText()), BigDecimal.valueOf(Long.parseLong(txtPrice.getText())));
            System.out.println(sparepart);
            main.sparepartsDAO.insertData(sparepart);
        }else {
            SparepartsEntity sparepart = new SparepartsEntity(this.main.getSelectedSparepart().getId(), txtNama.getText(), Integer.valueOf(txtQuantity.getText()), BigDecimal.valueOf(Long.parseLong(txtPrice.getText())));
            main.sparepartsDAO.editData(sparepart);
        }
        this.main.refreshData();
    }

    public void cancelProcess(ActionEvent actionEvent) {
    }

}
