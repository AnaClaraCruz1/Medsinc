/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author annac
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    public void salvar(Paciente p) {

        String sql = "INSERT INTO pacientes(nome, idade, endereco, contato, usuario_cadastro_id) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdade());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getContato());
            stmt.setInt(5, 1);

            stmt.execute();

            System.out.println("Paciente salvo!");

            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao salvar: " + e.getMessage());

        }

    }
    
    public ResultSet listarPacientes() {

        String sql = "SELECT * FROM pacientes";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement(sql);

            return stmt.executeQuery();

        } catch (Exception e) {

            System.out.println("Erro ao listar: " + e.getMessage());

            return null;
        }
    }
    
    public ResultSet pesquisarPaciente(String nome) {

        String sql =
        "SELECT * FROM pacientes WHERE nome LIKE ?";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(1, "%" + nome + "%");

            return stmt.executeQuery();

        } catch (Exception e) {

            System.out.println(
            "Erro pesquisar: " + e.getMessage());

            return null;
        }
    }
    
    public void excluirPaciente(int id) {

    String sql =
    "DELETE FROM pacientes WHERE paciente_id=?";

    try {

        Connection conn = Conexao.conectar();

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        stmt.setInt(1, id);

        int linhas = stmt.executeUpdate();

        System.out.println(
        "Linhas afetadas: " + linhas);
        
        conn.close();

    } catch (SQLException e) {

        System.out.println(
        "Erro excluir: " + e.getMessage());
    }
    
}
}
