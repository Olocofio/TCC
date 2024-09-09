package gui;
public class QuizData {
    private static QuizData instance;
    private String materiaSelecionada;
    private int quantidadePerguntas;

    private QuizData() {
    }

    public static QuizData getInstance() {
        if (instance == null) {
            instance = new QuizData();
        }
        return instance;
    }

    public String getMateriaSelecionada() {
        return materiaSelecionada;
    }

    public void setMateriaSelecionada(String materiaSelecionada) {
        this.materiaSelecionada = materiaSelecionada;
    }

    public int getQuantidadePerguntas() {
        return quantidadePerguntas;
    }

    public void setQuantidadePerguntas(int quantidadePerguntas) {
        this.quantidadePerguntas = quantidadePerguntas;
    }
}
