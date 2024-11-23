<?php
session_start();
include 'conexao.php'; // Inclua seu arquivo de conexão com o banco de dados

if (!isset($_SESSION['usuario_id'])) {
    header("Location: home.html?mensagem=Acesso negado", true, 302);
    exit;
}

// Cria uma nova conexão
$conexao = new conexao();
$pdo = $conexao->getConnection(); // Obtenha o PDO a partir da classe `conexao`

// Obtém o ID do usuário da sessão
$usuario_id = $_SESSION['usuario_id'];

// Prepara o SELECT para buscar as informações do usuário
$sqlUsuario = "SELECT id, nome, email, telefone FROM usuario WHERE id = :usuario_id";
$stmtUsuario = $pdo->prepare($sqlUsuario);
$stmtUsuario->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
$stmtUsuario->execute();

// Verifica se o usuário foi encontrado
if ($stmtUsuario->rowCount() > 0) {
    $usuario = $stmtUsuario->fetch(PDO::FETCH_ASSOC);
} else {
    echo "Usuário não encontrado.";
    exit;
}

// Prepara o SELECT para buscar todas as partidas do usuário
$sqlPartidas = "SELECT materia, dt_game, hr_game, pontuacao, qtd_perguntas, resultado FROM partida WHERE id_usu = :usuario_id ORDER BY dt_game DESC, hr_game DESC";
$stmtPartidas = $pdo->prepare($sqlPartidas);
$stmtPartidas->bindParam(':usuario_id', $usuario_id, PDO::PARAM_INT);
$stmtPartidas->execute();
$partidas = $stmtPartidas->fetchAll(PDO::FETCH_ASSOC);

?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil do Usuário</title>

    <!-- links -->
    <link rel="stylesheet" href="css/perfil.css">
</head>

<body>
    <div class="icons">
        <div class="right-icons">
            <a href="QuizData.php"><img src="img/casaBranca.png" class="icon"></a>
        </div>
    </div>
    <div class="container">
        <div class="logo">
            <img src="img/logoSF.png" alt="Logo FunLearn">
        </div>

        <div class="infos">
            <p><strong>Nome:</strong> <?php echo htmlspecialchars($usuario['nome']); ?></p>
        </div>

        <h2 id="titulo-partida">Partidas Jogadas</h2>
        <?php if (count($partidas) > 0): ?>
            <table class="table-info">
                <tr>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Matéria</th>
                    <th>Resultado (%)</th>
                </tr>
                <?php foreach ($partidas as $partida): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($partida['dt_game']); ?></td>
                        <td><?php echo date('H:i', strtotime($partida['hr_game'])); ?></td>
                        <td><?php echo htmlspecialchars($partida['materia']); ?></td>
                        <td><?php echo htmlspecialchars($partida['resultado']); ?></td>
                    </tr>
                <?php endforeach; ?>
            </table>
        <?php else: ?>
            <p>Este usuário ainda não jogou nenhuma partida.</p>
        <?php endif; ?>

        <!-- <p>Obs: Para visualizar melhor as informações, arraste a tabela para o lado.</p> -->
    </div>
</body>

</html>