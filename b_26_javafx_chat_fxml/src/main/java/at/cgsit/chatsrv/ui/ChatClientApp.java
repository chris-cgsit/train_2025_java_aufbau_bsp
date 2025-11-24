package at.cgsit.chatsrv.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                ChatClientApp.class.getResource("/fxml/chat_main_view.fxml")
        );

        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setTitle("CGS Chat Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
