package tests.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import vuecontroller.LoginController;

public class LoginTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../vuecontroller/login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();

        // screen dimensions
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        double width = bounds.getWidth() - 100, height = bounds.getHeight() - 100;

        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setTitle("Gestion scolaire");
        primaryStage.show();
    }
}
