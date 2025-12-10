package at.cgsit.jfxintro.grid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class HelloGridApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        List<String> raw = this.getParameters().getRaw();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/hello-grid.fxml")
        );

        Scene scene = new Scene(loader.load(), 350, 200);
        stage.setTitle("Hello GridPane");
        stage.setScene(scene);
        stage.show();
    }

}
