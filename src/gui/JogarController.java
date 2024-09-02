package gui;

import java.util.List;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import service.PerguntaService;
import session.UserSession;

public class JogarController {
	@FXML
    private ImageView imgHome;
    @FXML
    private ImageView imgLogout;
    @FXML
    private ComboBox<String> cmbMateria;
    @FXML
    private Spinner<Integer> spnQuantidade;
    @FXML
    private Button btnComecar;
    
    public void initialize() {
        loadComboBoxMateria();
        configureSpinnerQuantidade();
    }
    
    private void loadComboBoxMateria() {
    	PerguntaService perguntaService = new PerguntaService();
        List<String> materias = perguntaService.findAllMaterias();
        cmbMateria.getItems().clear();	
        cmbMateria.getItems().addAll(materias);
    }
    private void configureSpinnerQuantidade() {
        int min = 5;  // valor mínimo
        int max = 15; // valor máximo
        int initialValue = 5; // valor inicial
        spnQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue));
    }
    
    public void onimgLogoutClicked() {
        // Fechar a janela de cadastro
    	UserSession.getInstance().cleanUserSession();
    	Alerts.showAlert("Mensagem", "Logout", "Logout realizado com sucesso!", AlertType.INFORMATION);
    	Main.changeScreen("home");
    }
    public void onimgHomeMouseClicked() {
    	Main.changeScreen("home");
    }
    public String getMateriaSelecionada() {
    	String materiaSelecionada = cmbMateria.getValue();
        return materiaSelecionada;
    }

    public int getQuantidadePerguntas() {
    	Integer quantidadePerguntas = spnQuantidade.getValue();
        return quantidadePerguntas;
    } 
    public void btnComecarClicked() {
    	String materiaSelecionada = cmbMateria.getValue();
        Integer quantidadePerguntas = spnQuantidade.getValue();
        
        if (materiaSelecionada != null && quantidadePerguntas != null) {
            Main.changeScreen("quiz"); // Muda a tela depois de definir os valores
        } else {
            Alerts.showAlert("Aviso", "Dados incompletos", "Por favor, selecione uma matéria e a quantidade de perguntas.", AlertType.WARNING);
        }
    }
}
