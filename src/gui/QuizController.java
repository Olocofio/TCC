package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import DTO.QuestionDTO;
import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    @FXML
    private Text txtPergunta;

    private Button[] buttons;
    
    int acertos;
    
    // Variáveis de instância para armazenar o estado do quiz
    private List<QuestionDTO> questions;
    private AtomicInteger currentQuestionIndex;

    public void onimgConfigMouseClicked() {
        Main.changeScreen("pause");
    }

    public void initialize() {
    	acertos = 0;
        String materiaSelecionada = QuizData.getInstance().getMateriaSelecionada();
        int quantidadePerguntas = QuizData.getInstance().getQuantidadePerguntas();
        
        // Agora você pode usar materiaSelecionada e quantidadePerguntas
        initializeQuiz(materiaSelecionada, quantidadePerguntas);
    }

    public void initializeQuiz(String materiaSelecionada, int quantidadePerguntas) {
        PerguntaService perguntaService = new PerguntaService();
        questions = perguntaService.findQuestionsByMateria(materiaSelecionada, quantidadePerguntas);

        if (questions.size() > 0) {
            currentQuestionIndex = new AtomicInteger(0); // Inicializa o índice da pergunta atual

            // Exibe a primeira pergunta
            configureQuestion(questions.get(currentQuestionIndex.get()));

            // Atribui ações aos botões
            buttons = new Button[]{btn1, btn2, btn3, btn4};
        }
    }

    // Método para configurar uma pergunta e associá-la aos botões
    private void configureQuestion(QuestionDTO question) {
        buttons = new Button[]{btn1, btn2, btn3, btn4};
        List<String> respostas = new ArrayList<>(List.of(
            question.getResposta1(),
            question.getResposta2(),
            question.getResposta3(),
            question.getResposta4()
        ));

        // Embaralha as respostas
        Collections.shuffle(respostas);

        // Atribui as respostas aos botões
        for (int i = 0; i < buttons.length; i++) {
            String respostaAtual = respostas.get(i);
            buttons[i].setText(respostaAtual);

            // Verifica se a resposta é a correta
            final boolean isCorreta = respostaAtual.equals(question.getRespostaCorreta());

            // Configura a ação de clique para avaliar a resposta
            buttons[i].setOnAction(event -> botaoClicado(isCorreta));
        }

        // Exibe a pergunta
        txtPergunta.setText(question.getPergunta());
    }

    // Método chamado ao clicar no botão
    private void botaoClicado(boolean isCorreta) {
        // Exibe mensagem ou altera estilo com base na resposta correta/incorreta
        if (isCorreta) {
        	acertos++;
            System.out.println("Resposta correta!");
            exibirMensagem("Correto!", true);
            avancarProximaPergunta();
        } else {
            System.out.println("Resposta incorreta!");
            exibirMensagem("Incorreto!", false);
            avancarProximaPergunta();
        }
    }

    // Método para avançar para a próxima pergunta ou finalizar o quiz
    private void avancarProximaPergunta() {
        // Verifica se há mais perguntas
        if (currentQuestionIndex.incrementAndGet() < questions.size()) {
            // Carrega a próxima pergunta
            configureQuestion(questions.get(currentQuestionIndex.get()));
        } else {
            // Se não houver mais perguntas, finaliza o quiz
            finalizarQuiz();
        }
    }

    // Método para exibir mensagens de feedback
    private void exibirMensagem(String mensagem, boolean isCorreta) {
        txtPergunta.setText(mensagem);
        if (isCorreta) {
            txtPergunta.setStyle("-fx-fill: green;");
        } else {
            txtPergunta.setStyle("-fx-fill: red;");
        }
    }

    // Método para finalizar o quiz
    private void finalizarQuiz() {
        System.out.println("Quiz finalizado!");
        Alerts.showAlert("Quiz Finalizado!", "Quiz", "Parabéns! Você completou o quiz. \n Você acertou: "+ acertos + "!", AlertType.ERROR);
        Main.changeScreen("home");
        // Adicione lógica para redirecionar ou exibir uma tela final de pontuação
    }
}