package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		 
        try {
            alert.getDialogPane().getStylesheets().add(Alerts.class.getResource("/gui/util/application.css").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("CSS file not found!");
        }

        alert.show();
	}
}