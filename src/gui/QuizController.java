package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import DTO.QuestionDTO;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import service.PerguntaService;

public class QuizController {
    @FXML
    private ImageView imgConfig;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    private Button[] buttons = {btn1,btn2,btn3,btn4};
    
    private String materiaSelecionada;
    private int quantidadePerguntas;
    
    public void onimgConfigMouseClicked() {
		Main.changeScreen("pause");
	}
    
    
    public void initialize() {
    	//PerguntaService perguntaService = new PerguntaService();
    	//List<QuestionDTO> questions = perguntaService.findQuestionsByMateria(materia);
    	
    	/*List<Integer> buffBotoes = new ArrayList<>();
    	buffBotoes.add(0);
    	buffBotoes.add(1);
    	buffBotoes.add(2);
    	buffBotoes.add(3);

        Random random = new Random();

        int resposta = 0;
        // Enquanto a lista não estiver vazia
        while (buffBotoes.size() > 0) {
            // Gera um índice aleatório entre 0 e o tamanho atual da lista - 1
            int indexEscolha = random.nextInt(buffBotoes.size());

            // Pega o botão correspondente
            int indexBtnEscolhido = buffBotoes.remove(indexEscolha);
            
            AtomicInteger r = new AtomicInteger(resposta);
            AtomicInteger b = new AtomicInteger(indexBtnEscolhido);
            buttons[indexBtnEscolhido].setOnAction(event -> {
                botaoClicado(r.get(),b.get());
            });
            //buttons[indexBtnEscolhido].setText(respostasTexto[resposta]);
            
            resposta++;*/
    	String materiaSelecionada = JogarController.;
    	btn1.setText(materiaSelecionada);
        }
    }
    
    /*public void botaoClicado(int resposta,int b) {
    	buttons[b].setText(String.valueOf(resposta));
    }*/
