package session;

import DTO.UsuarioDTO;

public class UserSession {

    private static UserSession instance;

    private UsuarioDTO usuario;

    private UserSession(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public static UserSession getInstance(UsuarioDTO usuario) {
        if (instance == null) {
            instance = new UserSession(usuario);
        }
        return instance;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void cleanUserSession() {
        usuario = null;
        instance = null;
    }
}
