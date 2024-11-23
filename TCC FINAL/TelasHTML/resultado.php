<?php
session_start();
include 'conexao.php'; // Inclua o arquivo de conexão com o banco de dados

// Verifica se o usuário está logado e se as variáveis de pontuação estão definidas e válidas
if (!isset($_SESSION['usuario_id']) || !isset($_SESSION['pontuacao']) || !isset($_SESSION['totalPerguntas']) || $_SESSION['totalPerguntas'] <= 0) {
    header('Location: QuizData.php');
    exit();
}

$usuario_id = $_SESSION['usuario_id'];
$pontuacao = $_SESSION['pontuacao'];
$totalPerguntas = $_SESSION['totalPerguntas'];
$materia = $_SESSION['materia'];

// Calcula o resultado como porcentagem de acertos
// $resultado = ($pontuacao / $totalPerguntas) * 100;

// Conecta ao banco de dados
$conexao = new conexao();
$pdo = $conexao->getConnection();

try {
    // Prepara o INSERT na tabela de pontuação
    $sql = "INSERT INTO partida(id_usu, dt_game, hr_game, pontuacao, qtd_perguntas, materia)
            VALUES (:id_usu, CURRENT_DATE, CURRENT_TIME, :pontuacao, :qtd_perguntas, :materia)";
    $stmt = $pdo->prepare($sql);
    $stmt->bindParam(':id_usu', $usuario_id, PDO::PARAM_INT);
    $stmt->bindParam(':pontuacao', $pontuacao, PDO::PARAM_INT);
    $stmt->bindParam(':qtd_perguntas', $totalPerguntas, PDO::PARAM_INT);
    $stmt->bindParam(':materia', $materia, PDO::PARAM_INT);
    //$stmt->bindParam(':resultado', $resultado, PDO::PARAM_STR);
    $stmt->execute();
} catch (PDOException $e) {
    echo "Erro ao salvar o resultado: " . $e->getMessage();
    exit();
}

// Limpa as sessões para um novo jogo
unset($_SESSION['pontuacao']);
unset($_SESSION['totalPerguntas']);
unset($_SESSION['materia']);
//$pontuacao = "";
//$totalPerguntas = "";
//$materia = "";
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado do Quiz</title>
    <link rel="stylesheet" href="css/resultado.css">
</head>

<body>
    <div>
        <div class="logo">
            <img src="img/logoSF.png" alt="Logo FunLearn">
        </div>

        <h1>Resultado</h1>
        <p class="resultado">Você acertou <span class="acertos"><?= htmlspecialchars($pontuacao) ?></span> de <span class="total"><?= htmlspecialchars($totalPerguntas) ?></span> perguntas.</p>

        <a href="QuizData.php" class="btn">Tentar Novamente</a>
    </div>
</body>

</html>
