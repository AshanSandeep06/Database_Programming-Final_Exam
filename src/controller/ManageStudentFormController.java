package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Student;
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

    public void initialize() {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllStudents();
        generateNewStudentId();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, student) -> {
            if (student != null) {
                if (!tblStudent.getItems().isEmpty()) {
                    setStudentData(new Student(student.getStudent_id(), student.getStudent_name(), student.getEmail(), student.getContact(),student.getAddress(),student.getNic()));
                }
            }
        });
    }

    private void generateNewStudentId() {
        try {
            ResultSet result = CrudUtil.execute("SELECT student_id FROM Student ORDER BY student_id DESC LIMIT 1");
            String studentId = "S001";
            if (result.next()) {
                studentId = result.getString(1);
            }

            String[] splitString = studentId.split("S");
            int id = Integer.parseInt(splitString[1]);
            id++;

            if (id < 10) {
                studentId = "S00" + id;
            } else if (id < 100) {
                studentId = "S0" + id;
            } else {
                studentId = "S" + id;
            }
            txtStudentID.setText(studentId);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Student");
            while (result.next()) {
                tblStudent.getItems().add(new StudentTM(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchStudent() {
        try {
            if (!txtSearchStudent.getText().isEmpty()) {
                if (txtSearchStudent.getText().matches("^([S]([0-9]{3,4}))$")) {
                    String studentID = txtSearchStudent.getText();
                    ResultSet result = CrudUtil.execute("SELECT * FROM Student WHERE student_id=?", studentID);
                    if (result.next()) {
                        Student student = new Student(
                                result.getString(1),
                                result.getString(2),
                                result.getString(3),
                                result.getString(4),
                                result.getString(5),
                                result.getString(6)
                        );
                        setStudentData(student);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "No students exists for this student ID..!", ButtonType.OK).show();
                        clearAll();
                    }
                } else {
                    clearAll();
                    new Alert(Alert.AlertType.WARNING, "Invalid student ID ..!").show();
                }
            } else {
                clearAll();
                new Alert(Alert.AlertType.WARNING, "Empty field,try again..!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearAll() {
        txtStudentID.clear();
        txtStudentName.clear();
        txtMobileNumber.clear();
        txtAddress.clear();
        txtSearchStudent.clear();
        txtEmail.clear();
        txtNic.clear();

        txtStudentID.requestFocus();
        tblStudent.getSelectionModel().clearSelection();
        generateNewStudentId();
    }

    private void setStudentData(Student student) {
        txtStudentID.setText(student.getStudent_id());
        txtStudentName.setText(student.getStudent_name());
        txtNic.setText(student.getNic());
        txtAddress.setText(student.getAddress());
        txtEmail.setText(student.getEmail());
        txtMobileNumber.setText(student.getContact());
    }

    public void searchStudentOnAction(ActionEvent event) {
        searchStudent();
    }

    public void txtSearchStudentOnAction(ActionEvent event) {
        searchStudent();
    }

    public void addStudentOnAction(ActionEvent event) {
        try {
            Student student = new Student(
                    txtStudentID.getText(),
                    txtStudentName.getText(),
                    txtEmail.getText(),
                    txtMobileNumber.getText(),
                    txtAddress.getText(),
                    txtNic.getText()
            );
            if (CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",
                    student.getStudent_id(),
                    student.getStudent_name(),
                    student.getEmail(),
                    student.getContact(),
                    student.getAddress(),
                    student.getNic()
            )) {
                tblStudent.getItems().add(new StudentTM(student.getStudent_id(), student.getStudent_name(), student.getEmail(), student.getContact(), student.getAddress(), student.getNic()));
                new Alert(Alert.AlertType.CONFIRMATION, "Student has been saved successfully..!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, try again.!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        clearAll();
    }

    public void updateStudentOnAction(ActionEvent event) {
        try {
            Student student = new Student(
                    txtStudentID.getText(),
                    txtStudentName.getText(),
                    txtEmail.getText(),
                    txtMobileNumber.getText(),
                    txtAddress.getText(),
                    txtNic.getText()
            );
            if (CrudUtil.execute("UPDATE Student SET student_name=?,email=?,contact=?,address=?,nic=? WHERE student_id=?",
                    student.getStudent_name(),
                    student.getEmail(),
                    student.getContact(),
                    student.getAddress(),
                    student.getNic(),
                    student.getStudent_id()
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student has been updated successfully..!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, try again.!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        clearAll();
        loadAllStudents();
        tblStudent.refresh();
    }

    public void removeStudentOnAction(ActionEvent event) {
        try {
            if (!tblStudent.getItems().isEmpty()) {
                if (tblStudent.getSelectionModel().getSelectedItem() != null || !txtSearchStudent.getText().isEmpty()) {
                    StudentTM tm = tblStudent.getSelectionModel().getSelectedItem();
                    String studentID = txtSearchStudent.getText();
                    if (CrudUtil.execute("DELETE FROM Student WHERE student_id=?", tm != null ? tm.getStudent_id() : studentID)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student has been deleted successfully..!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong, try again.!").show();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        clearAll();
        tblStudent.refresh();
        loadAllStudents();
    }

    public void resetOnAction(ActionEvent event) {
        clearAll();
    }

    public void btnAddNewStudentOnAction(ActionEvent event) {
        clearAll();
    }
}
