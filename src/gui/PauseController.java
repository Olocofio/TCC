package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseController {
	@FXML
    private Button btnSair;
	
	public void btnSairMouseClicked() {
		Main.changeScreen("home");
	}
	public void btnRetomarMouseClicked() {
		Main.changeScreen("quiz");
	}
}
