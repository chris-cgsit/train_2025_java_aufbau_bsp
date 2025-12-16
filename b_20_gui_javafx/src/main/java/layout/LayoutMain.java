/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package layout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LayoutMain extends Application {

	public void start(javafx.stage.Stage primaryStage) throws Exception {
		
		
		try { 
			// den Root des Scene Graph aus dem FXML-File laden  
			Parent root = (Parent) 
					FXMLLoader.load(getClass().getResource("/views/layout/LayoutView.fxml"));
			Scene scene = new Scene(root, 400, 300);
			// das CSS-Stylesheet laden und setzen
			scene.getStylesheets().add(getClass().getResource("/views/layout/layout.css").toExternalForm());
			// das ganze anzeigen
			primaryStage.setScene(scene);
			primaryStage.setTitle("Layout demo");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
