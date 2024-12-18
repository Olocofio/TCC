const loader = document.querySelector('#div-loader');
const buttons = document.querySelectorAll('.btn');
const audioPlayer = document.getElementById('audio-player');

let timer = 0;

if (loader) {
    if (timer) {
        clearTimeout(timer);
    }
    timer = setTimeout(() => {
        loader.style.display = "none";
    }, 800);
}

buttons.forEach(button => {
    button.onclick = (event) => {
        const audio = new Audio('sound/btn.mp3');

        audioPlayer.play().then(() => {
            setTimeout(() => {
                switch (button.id) {
                    case 'play':
                        window.location.href = 'login.php';
                        break;

                    default:
                        break;
                }
            }, 500);
        });
    }
});

function exibirToast(mensagem) {
    const toast = document.getElementById('toast');
    toast.textContent = mensagem;

    toast.className = "show";

    setTimeout(function () {
        toast.className = toast.className.replace("show", "");
    }, 3000);
}

function exibirAlerta() {
    const params = new URLSearchParams(window.location.search);
    const mensagem = params.get('mensagem');
    if (mensagem) {
        exibirToast(`Atenção: ${mensagem}`);
    }
}

function alternarVisibilidadeSenha(senha, olho) {
    var campoSenha = document.getElementById(senha);
    var imagemOlho = document.getElementById(olho);

    if (campoSenha.type === "password") {
        // Troca para texto e muda imagem para "olho aberto"
        campoSenha.type = "text";
        imagemOlho.src = "img/olhoaberto.png";
        imagemOlho.alt = "Ocultar senha";
    } else {
        // Troca para password e muda imagem para "olho fechado"
        campoSenha.type = "password";
        imagemOlho.src = "img/olhofechado.png";
        imagemOlho.alt = "Mostrar senha";
    }
}

exibirAlerta();