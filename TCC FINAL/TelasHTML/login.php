<?php

session_start(); // Inicie a sessão no início do script

if (isset($_SESSION['usuario_id'])) {
    header("Location: QuizData.php", true, 302);
    exit;
}

include "conexao.php";
?>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/cadastro.css"> <!-- Arquivo de CSS -->
</head>

<body>
    <div class="icons">
        <a href="home.html"> <img src="img/back.png" class="icon" alt="Voltar"> </a>
    </div>

    <div class="logo">
        <img src="img/logo-sf.png" alt="Logo FunLearn">
    </div>

    <!-- O action agora aponta para login.php -->
    <form action="login.php" method="post"> <!-- Verifique se "login.php" está no mesmo diretório -->
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="senha" id="senha" placeholder="Senha" required>
        <img src="img/olhofechado.png" alt="Mostrar/Ocultar Senha" id="olho" onclick="alternarVisibilidadeSenha('senha', 'olho')" style="cursor: pointer;">
        <button type="submit" class="btn" style="margin-top:.5rem">Login</button>
    </form>

    <div class="create-account">
        <a href="cadastro.html">Criar Conta</a> <br>
        <a href="esqueci.php">Esqueci a senha</a>
    </div>
</body>
<script src="js/main.js"></script>
<html>

<?php
class UsuarioDAO
{
    private $conn;

    public function __construct()
    {
        $this->conn = (new conexao())->getConnection();
    }

    public function loginUsuario($email, $senha)
    {
        $sql = "SELECT * FROM usuario WHERE email = :email";

        try {
            $stmt = $this->conn->prepare($sql);
            $stmt->bindParam(':email', $email);
            $stmt->execute();

            $usuario = $stmt->fetch(PDO::FETCH_ASSOC);

            if ($usuario && password_verify($senha, $usuario['senha'])) {
                // Armazene o ID ou outros dados do usuário na sessão
                $_SESSION['usuario_id'] = $usuario['id'];
                $_SESSION['usuario_email'] = $usuario['email'];

                header("Location: QuizData.php", true, 302);
                exit;
            } else {
                header("Location: home.html?mensagem=Senha inválida", true, 302);
                exit;
            }
        } catch (PDOException $e) {
            error_log($e->getMessage());
            return false;
        }
    }
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $_POST['email'] ?? '';
    $senha = $_POST['senha'] ?? '';

    $usuarioDAO = new UsuarioDAO();
    $usuarioDAO->loginUsuario($email, $senha);
}
