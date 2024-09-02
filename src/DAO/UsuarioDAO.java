package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.UsuarioDTO;

public class UsuarioDAO {
	
	ArrayList<UsuarioDTO> lista = new ArrayList<>();
    public void cadastrarUsuario(UsuarioDTO usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefone());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<UsuarioDTO> selecionarUsuario() throws SQLException {
        String sql = "SELECT * FROM usuario";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            try{
                while (rs.next()) {
                    UsuarioDTO objusuarioDTO = new UsuarioDTO();
                    objusuarioDTO.setId(rs.getInt("id"));
                    objusuarioDTO.setNome(rs.getString("nome"));
                    objusuarioDTO.setEmail(rs.getString("email"));
                    objusuarioDTO.setSenha(rs.getString("senha"));
                    objusuarioDTO.setTelefone(rs.getString("telefone"));
                    
                    lista.add(objusuarioDTO);
                }
            }
        catch (SQLException e) {
        	e.printStackTrace();
            }
            return lista;
        }
    }
    public void removerUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    public void alterarUsuario(UsuarioDTO usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id = ?";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefone());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    public boolean loginUsuario(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;  // Se houver correspondência, o login é válido
            } else {
                return false; // Caso contrário, o login é inválido
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public UsuarioDTO getUsuarioByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        UsuarioDTO usuario = null;
        
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                usuario = new UsuarioDTO();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuario;
    }
    public boolean isEmailCadastrado(String email) throws Exception {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (Connection conn = new ConexaoDAO().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Se o COUNT(*) > 0, o email já está cadastrado
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao verificar email no banco de dados: " + e.getMessage());
        }
        return false;
    }
}
