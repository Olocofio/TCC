package gui;

import java.util.Collections;
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
    private Button[] buttons;

    

    
    public void onimgConfigMouseClicked() {
        Main.changeScreen("pause");
    }
    
    public void initialize() {
    	String materiaSelecionada = QuizData.getInstance().getMateriaSelecionada();
        int quantidadePerguntas = QuizData.getInstance().getQuantidadePerguntas();

        // Agora você pode usar materiaSelecionada e quantidadePerguntas
        initializeQuiz(materiaSelecionada, quantidadePerguntas);
    }
    public void initializeQuiz(String materiaSelecionada, int quantidadePerguntas) {
        PerguntaService perguntaService = new PerguntaService();
        List<QuestionDTO> questions = perguntaService.findQuestionsByMateria(materiaSelecionada, quantidadePerguntas);

        if (questions.size() > 0) {
            buttons = new Button[]{btn1, btn2, btn3, btn4};
            
            for (QuestionDTO question : questions.subList(0, quantidadePerguntas)) {
                // Configurar o quiz com perguntas e respostas
                configureQuestion(question);
            }
        }
    }
    
    // Método inicializador do quiz
    /*private void initializeQuiz() {
        PerguntaService perguntaService = new PerguntaService();
        List<QuestionDTO> questions = perguntaService.findQuestionsByMateria(materiaSelecionada);

        if (questions.size() > 0) {
            buttons = new Button[]{btn1, btn2, btn3, btn4};
            Random random = new Random();
            
            for (QuestionDTO question : questions.subList(0, quantidadePerguntas)) {
                // Configurar o quiz com perguntas e respostas
                configureQuestion(question, random);
            }
        } 
    }*/
    
    // Método para configurar uma pergunta e associá-la aos botões
    private void configureQuestion(QuestionDTO question) {
        List<Integer> availableButtons = List.of(0, 1, 2, 3);
        AtomicInteger resposta = new AtomicInteger(0);

        // Adicionar as respostas nos botões de forma aleatória
        for (int i = 0; i < buttons.length; i++) {
            int indexBtn = availableButtons.get(i);
            buttons[indexBtn].setText(question.getRespostas().get(i)); // Supondo que `getRespostas` retorne uma lista de respostas

            // Configurar ação ao clicar em cada botão
            buttons[indexBtn].setOnAction(event -> botaoClicado(resposta.get(), indexBtn));
        }
    }

    // Método chamado ao clicar no botão
    private void botaoClicado(int resposta, int btnIndex) {
        // Implementar lógica de avaliação da resposta
        buttons[btnIndex].setText("Clicou na resposta " + resposta);
    }
}
