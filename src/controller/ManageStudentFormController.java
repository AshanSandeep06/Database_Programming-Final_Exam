package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import view.tdm.StudentTM;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class ManageStudentFormController {

    public JFXTextField txtStudentID;
    public JFXButton btnSearch;
    public JFXTextField txtStudentName;
    public JFXTextField txtNic;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public TableView<StudentTM> tblStudent;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colMobileNumber;
    public JFXTextField txtAddress;
    public JFXButton btnAdd;
    public TextField txtSearchStudent;
    public JFXTextField txtEmail;
    public JFXTextField txtMobileNumber;

    public void initialize(){

    }

    public void searchStudentOnAction(ActionEvent event) {
    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
    }

    public void updateStudentOnAction(ActionEvent event) {
    }

    public void removeStudentOnAction(ActionEvent event) {
    }

    public void resetOnAction(ActionEvent event) {
    }

    public void addStudentOnAction(ActionEvent event) {
    }

    public void txtSearchStudentOnAction(ActionEvent event) {
    }

    public void btnAddNewStudentOnAction(ActionEvent event) {
    }

    public void searchStudentKeyReleasedOnAction(KeyEvent keyEvent) {
    }
}
