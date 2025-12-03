package at.cgsit.a_jfxintro.table;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/tableview.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("JavaFX TableView (FXML)");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
