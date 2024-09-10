package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.ConexaoDAO;
import DTO.QuestionDTO;

public class PerguntaService {
	private ConexaoDAO conexaoDAO;

    public PerguntaService() {
    	this.conexaoDAO = new ConexaoDAO();
    }

    public List<String> findAllMaterias() {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = conexaoDAO.getConnection();
            st = conn.prepareStatement("SELECT DISTINCT materia FROM pergunta");
            rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("materia"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar matérias: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return list;
    }
    
    public List<QuestionDTO> findQuestionsByMateria(String materia, int questoes) {
        List<QuestionDTO> questions = new ArrayList<>();
        
        String sql = "SELECT * FROM pergunta WHERE materia = ? ORDER BY RANDOM()";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, materia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QuestionDTO question = new QuestionDTO();
                question.setId(rs.getInt("id_perg"));
                question.setMateria(rs.getString("materia"));
                question.setPergunta(rs.getString("pergunta"));
                question.setResposta1(rs.getString("resposta1"));
                question.setResposta2(rs.getString("resposta2"));
                question.setResposta3(rs.getString("resposta3"));
                question.setResposta4(rs.getString("resposta4"));
                question.setRespostaCorreta(rs.getString("resposta_correta"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

}
