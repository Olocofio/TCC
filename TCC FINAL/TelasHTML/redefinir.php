<?php
session_start();

include "conexao.php";

if (isset($_GET['token'])) {
    $token = $_GET['token'];

    // Verifica se o token é válido
    $conn = (new conexao())->getConnection();
    $sql = "SELECT email FROM usuario WHERE token = :token AND token_expiry > NOW()";
    $stmt = $conn->prepare($sql);
    $stmt->bindParam(':token', $token);
    $stmt->execute();
    $usuario = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($usuario) {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $senha1 = $_POST['senha1'] ?? '';
            $senha2 = $_POST['senha2'] ?? '';

            if ($senha1 === $senha2) {
                // Atualiza a senha com segurança
                $hashSenha = password_hash($senha1, PASSWORD_DEFAULT);
                $email = $usuario['email'];

                $sql = "UPDATE usuario SET senha = :senha, token = NULL, token_expiry = NULL WHERE email = :email";
                $stmt = $conn->prepare($sql);
                $stmt->bindParam(':senha', $hashSenha);
                $stmt->bindParam(':email', $email);
                $stmt->execute();

                echo "Senha alterada com sucesso!";
            } else {
                echo "As senhas não correspondem!";
            }
        }
    } else {
        echo "Token inválido ou expirado!";
    }
}
?>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/redefinir.css"> <!-- Arquivo de CSS -->
</head>

<body>
    <div class="icons">
        <a href="home.html"> <img src="img/back.png" class="icon" alt="Voltar"> </a>
    </div>

    <div class="logo">
        <img src="img/logo-sf.png" alt="Logo FunLearn">
    </div>

    <form action="" method="post">
        <h1>Redefinir a senha</h1>
        <input type="password" name="senha1" placeholder="Digite a senha nova" required><br>
        <input type="password" name="senha2" placeholder="Redigite a senha nova" required><br>
        <img src="img/olhofechado.png" alt="Mostrar/Ocultar Senha" id="olho" onclick="alternarVisibilidadeSenha()" style="cursor: pointer;">
        <input type="submit" class="btn" value="Alterar">
    </form>
    <!-- O action agora aponta para login.php -->
</body>
<script src="js/main.js"></script>
</html>