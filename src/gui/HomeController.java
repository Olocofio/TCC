package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import session.UserSession;

public class HomeController {
	
	@FXML
    private Button btnJogar;
    @FXML
    private Button btnOpcoes;
    
    @FXML
    public void onbtnJogarAction() {
    	UserSession us = UserSession.getInstance();
    	if(us == null) {
    		Main.changeScreen("login");
    	}else {
    		Main.changeScreen("jogar");
    	}
    }
    @FXML
    public void onbtnOpcoesAction() {
    	Main.changeScreen("opcoes");
    }
    
}
