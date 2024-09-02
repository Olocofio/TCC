package gui;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class CadastroController {
	// variaveis fxml
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnVoltar;

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtSenha;
	@FXML
	private TextField txtTelefone;

	@FXML
	private ImageView imgHome;
	@FXML
	private ImageView imgVoltar;

	@FXML
	public void onimgVoltarClicked() {
		// Fechar a janela de cadastro
		Main.changeScreen("login");
	}

	@FXML
	public void onbtnCadastrarAction() {

		try {
	        UsuarioDTO objusuarioDTO = new UsuarioDTO();
	        UsuarioDAO usuarioDAO = new UsuarioDAO();

	        // Preenchendo o objeto com os dados dos campos de texto
	        String nome = txtNome.getText();
	        String email = txtEmail.getText();
	        String senha = txtSenha.getText();
	        String telefone = txtTelefone.getText();

	        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || telefone.isEmpty()) {
	            Alerts.showAlert("Mensagem", "ERRO", "Não é possível cadastrar com campos vazios.", AlertType.INFORMATION);
	            return;
	        }

	        // Verifica se o email já está cadastrado
	        if (usuarioDAO.isEmailCadastrado(email)) {
	            Alerts.showAlert("Mensagem", "ERRO", "Este email já está cadastrado.", AlertType.INFORMATION);
	            return;
	        }

	        // Configura os dados do usuário
	        objusuarioDTO.setNome(nome);
	        objusuarioDTO.setEmail(email);
	        objusuarioDTO.setSenha(senha);
	        objusuarioDTO.setTelefone(telefone);

	        // Tentativa de cadastrar o usuário
	        usuarioDAO.cadastrarUsuario(objusuarioDTO);

	        // Exibindo a mensagem de sucesso
	        Alerts.showAlert("Mensagem", "Cadastro", "Cadastro realizado com sucesso!", AlertType.INFORMATION);

	        Main.changeScreen("login");
	    } catch (Exception e) {
	        // Exibindo a mensagem de erro
	        Alerts.showAlert("Erro", "Erro no cadastro",
	                "Ocorreu um erro ao tentar realizar o cadastro: " + e.getMessage(), AlertType.ERROR);
	    } finally {
	        txtNome.clear();
	        txtEmail.clear();
	        txtSenha.clear();
	        txtTelefone.clear();
	    }
	}

	@FXML
	public void onimgHomeMouseClicked() {
		Main.changeScreen("home");
	}
}