<?php
session_start();
session_unset(); // Remove todas as variáveis de sessão
session_destroy(); // Destroi a sessão

// header("Location: home.html?mensagem=Logout realizado com sucesso", true, 302
header("Location: home.html", true, 302);
exit;
