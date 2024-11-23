<?php
require_once 'EnviaEmail.php';
session_start(); // Iniciar sessão para armazenar o token

include "conexao.php";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $_POST['email'] ?? '';
    
    // Verifica se o e-mail existe no banco
    $conn = (new conexao())->getConnection();
    $sql = "SELECT nome, senha FROM usuario WHERE email = :email";
    $stmt = $conn->prepare($sql);
    $stmt->bindParam(':email', $email);
    $stmt->execute();
    $usuario = $stmt->fetch(PDO::FETCH_ASSOC);
    
    if ($usuario) {
        // Gera um token único e seguro
        $token = bin2hex(random_bytes(32));
        $sql = "UPDATE usuario SET token = :token, token_expiry = NOW() + INTERVAL '1 hour' WHERE email = :email";
        $stmt = $conn->prepare($sql);
        $stmt->bindParam(':token', $token);
        $stmt->bindParam(':email', $email);
        $stmt->execute();
        
        // Envia o e-mail com o link de redefinição
        $nome = $usuario['nome'];
        $link = "https://projetoscti.com.br/projetoscti29/TelasHTML/redefinir.php?token=$token";
        $html = "<h4>Redefinir sua senha</h4><br>
                <b>$nome</b>, <br>
                Clique no link para redefinir sua senha:<br><a href='$link'>$link</a>";
        
        if (EnviaEmail($email, '* Recupere a sua senha do usuario FunLearn! *', $html)) {
            echo "<b>Email enviado com sucesso</b> (verifique sua caixa de spam se não encontrar)";
        }
    } else {
        echo "E-mail não encontrado.";
    }
}
?>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Esqueci a Senha</title>
    <link rel="stylesheet" href="css/esqueci.css"> <!-- Arquivo de CSS -->
</head>

<body>
    <div class="icons">
        <a href="home.html"> <img src="img/back.png" class="icon" alt="Voltar"> </a>
    </div>

    <div class="logo">
        <img src="img/logo-sf.png" alt="Logo FunLearn">
    </div>

    <h3>Recuperar Senha</h3>

    <form action="esqueci.php" method="post">
        <label for="email">Digite seu email cadastrado:</label><br>
        <input type="email" name="email" id="email" required><br><br>
        <input type="submit" class="btn" value="Enviar">
    </form>
    <!-- O action agora aponta para login.php -->
</body>

</html>