package at.cgsit.a_jfxintro.person;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloFxPersonApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Person Editor – JavaFX + MVVM");

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/person-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
