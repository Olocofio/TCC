package gui;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import session.UserSession;

public class LoginController {
   
	@FXML
    private Button btnLogin;
    @FXML
    private Button btnCriarConta;
    
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private ImageView imgHome;
    
    @FXML
    public void onimgHomeMouseClicked() {
    	Main.changeScreen("home");
    }
    @FXML
    public void onbtnLoginAction() {
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        UsuarioDTO usuario = usuarioDAO.getUsuarioByEmailAndSenha(email, senha);
        
        if (usuario != null) {
            // Armazena o usuário na sessão
            UserSession.getInstance(usuario);
            Alerts.showAlert("Mensagem", "Login", "Login realizado com sucesso!", AlertType.INFORMATION);
            Main.changeScreen("jogar");
        } else {
            Alerts.showAlert("Erro", "Login", "Email ou senha incorretos!", AlertType.ERROR);
        }
        
        txtEmail.clear();
        txtSenha.clear();
        
        //UsuarioDTO usuarioLogado = UserSession.getInstance().getUsuario();
        //System.out.println("Usuário logado: " + usuarioLogado.getNome());
        //Logout
        //UserSession.getInstance().cleanUserSession();
        //Main.changeScreen("login");
    }

    @FXML
    public void onbtnCriarContaAction() {
    	Main.changeScreen("cadastro");
    }
}