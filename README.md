# TCC CTI

# FunLearn Quiz

## Descrição

**FunLearn Quiz** é um jogo interativo de perguntas e respostas projetado para crianças de 8 a 14 anos. O jogo cobre diversas matérias escolares, 
incluindo História, Geografia, Ciência e Tecnologia, Língua Portuguesa e Matemática, proporcionando uma experiência divertida e educativa.

## Funcionalidades

- **Login de Usuário**: Permite que os usuários façam login com seu e-mail e senha. Oferece também a opção de redefinição de senha via token.
- **Seleção de Matérias**: Usuários podem escolher a matéria desejada para o quiz.
- **Jogos de Perguntas e Respostas**: Perguntas são apresentadas aleatoriamente, permitindo que os usuários respondam interativamente.
- **Resultados e Pontuação**: Após completar o quiz, os resultados são exibidos, mostrando a quantidade de respostas corretas e a pontuação total.

## Tecnologias Utilizadas

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: PHP, PDO para interação com o banco de dados
- **Banco de Dados**: PostgreSQL

## Estrutura do Projeto

O projeto é organizado nas seguintes partes principais:

- **Banco de Dados**:
    - **tabela `usuario`**: Armazena dados dos usuários, como e-mail e senha.
    - **tabela `pergunta`**: Armazena perguntas, respostas e matérias.
    - **tabela `partida`**: Armazena informações das partidas jogadas, incluindo pontuação e quantidade de perguntas.
- **Arquivos PHP**:
    - `conexao.php`: Estabelece a conexão com o banco de dados.
    - `cadastro.php`: Realizar o cadastro dos usuários.
    - `EnviaEmail.php`: Enviar e-mail de esqueci a senha ao usuário.
    - `esqueci.php`: Identifica o usuário e gera token para troca da senha.
    - `login.php`: Faz a validação do usuário para acesso ao jogo.
    - `PerguntaService.php`: Gerencia as perguntas, incluindo busca de matérias e perguntas por matéria, além da inserção de pontuação.
    - `QuestionDTO.php`: Representa uma pergunta e suas respostas.
    - `QuizData.php`: Página para seleção de matérias e perguntas.
        - `jogo.php`: Lógica principal do jogo, onde as perguntas são apresentadas e as respostas são verificadas.
    - `resultado.php`: Exibe os resultados do Quiz após a finalização.
    - `redefinir.php`: Valida o token digitado, e realiza a criação da nova senha do usuário.
- **Frontend**:
    - Páginas HTML para a interface do usuário, incluindo formulários de login, seleção de matérias, apresentação de perguntas e resultados.
    - Arquivos CSS para estilização das páginas.

## Como Instalar

1. **Acessar o Site**: Visite o site onde o jogo está hospedado.
2. **Baixar o APK**: No site, você encontrará a opção para baixar o APK do FunLearn Quiz.
3. **Descompactar o Arquivo**: O APK vem em um arquivo ZIP. Você precisará descompactar este arquivo para acessar o APK.
4. **Instalar o APK**: Após descompactar, você verá o arquivo APK.
    - No seu dispositivo Android, acesse as configurações e permita a instalação de aplicativos de fontes desconhecidas.
    - Clique no arquivo APK e siga as instruções na tela para completar a instalação.

## Como Usar

- Após instalar o aplicativo, abra o FunLearn Quiz.
- Faça login ou crie uma nova conta.
- Escolha uma matéria e comece a responder as perguntas.
- Ao final do jogo, você verá sua pontuação e poderá tentar novamente.
