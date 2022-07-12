package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import util.CrudUtil;
import view.tdm.StudentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllStudents();
    }

    private void loadAllStudents(){
        tblStudent.getItems().clear();
        try{
            ResultSet result = CrudUtil.execute("SELECT * FROM Student");
            while(result.next()){
                tblStudent.getItems().add(new StudentTM(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                ));
            }

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void searchStudentOnAction(ActionEvent event) {
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
}
