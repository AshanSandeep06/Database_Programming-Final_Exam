/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/ManageStudentForm.fxml"))));
        primaryStage.setTitle("Manage Students Form");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("assets/images/Student.png"));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
