package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage stage;
	
	private static Scene homeScene;
	private static Scene loginScene;
	private static Scene cadastroScene;
	private static Scene pauseScene;
	private static Scene jogarScene;
	private static Scene quizScene;
	
	
	@Override
    public void start(Stage primaryStage) {
        try {
        	stage = primaryStage;
            // Carregar o arquivo FXML
        	Parent fxmlHome = FXMLLoader.load(getClass().getResource("/gui/home.fxml"));
        	homeScene = new Scene(fxmlHome, 375, 645);
        	
        	Parent fxmlPause = FXMLLoader.load(getClass().getResource("/gui/pause.fxml"));
        	pauseScene = new Scene(fxmlPause, 375, 645);
        	
        	Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        	loginScene = new Scene(fxmlLogin, 375, 645);
        	
        	Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("/gui/Cadastro.fxml"));
        	cadastroScene = new Scene(fxmlCadastro, 375, 645);
        	
        	Parent fxmlJogar = FXMLLoader.load(getClass().getResource("/gui/jogar.fxml"));
        	jogarScene = new Scene(fxmlJogar, 375, 645);
        	
        	Parent fxmlQuiz = FXMLLoader.load(getClass().getResource("/gui/quiz.fxml"));
        	quizScene = new Scene(fxmlQuiz, 375, 645);
            // FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
            //Parent root = loader.load();
            
            // Configurar a cena
            //Scene scene = new Scene(root, 375, 637);
            
            // Configurar o palco
            primaryStage.setTitle("Tela Inicial");
            primaryStage.setScene(homeScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void changeScreen(String scr, String materiaSelecionada, Integer quantidadePerguntas) {
		
	}
	
	 public static void changeScreen(String scr) {
		switch(scr) {
		case "login":
			stage.setScene(loginScene);
			break;
		case "cadastro":
			stage.setScene(cadastroScene);
			break;
		case "home":
			stage.setScene(homeScene);
			break;
		case "pause":
			stage.setScene(pauseScene);
			break;
		case "jogar":
			stage.setScene(jogarScene);
			break;
		case "quiz":
			stage.setScene(quizScene);
			break;
		
		}

	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
