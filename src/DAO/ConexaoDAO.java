package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO {
	static String stringconexao = "jdbc:postgresql://pgsql.projetoscti.com.br:5432/projetoscti28";
    static String usuario = "projetoscti28";
    static String senha = "eq13556";
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(stringconexao, usuario, senha);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
