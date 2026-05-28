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
import java.sql.ResultSet;

import model.HistoricoMedico;

public class HistoricoDAO {

    public void salvar(HistoricoMedico h) {

        String sql =
        "INSERT INTO historicos_medicos(paciente_id, medico_id, descricao, data_registro) VALUES (?, ?, ?, ?)";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(1, h.getPacienteId());
            stmt.setInt(2, h.getMedicoId());
            stmt.setString(3, h.getDescricao());
            stmt.setString(4, h.getData());

            stmt.execute();

            conn.close();

            System.out.println("Histórico salvo!");

        } catch (Exception e) {

            System.out.println(
            "Erro salvar histórico: "
            + e.getMessage());

        }

    }

    public ResultSet listarHistoricos() {

        String sql =
       
        "SELECT " +

        "h.historico_id, " +

        "CONCAT(p.paciente_id, ' - ', p.nome) AS paciente, " +

        "CONCAT(u.usuario_id, ' - ', u.nome) AS medico, " +

        "h.descricao, " +

        "h.data_registro " +

        "FROM historicos_medicos h " +

        "INNER JOIN pacientes p " +
        "ON h.paciente_id = p.paciente_id " +

        "INNER JOIN usuarios u " +
        "ON h.medico_id = u.usuario_id";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            return stmt.executeQuery();

        } catch (Exception e) {

            System.out.println(
            "Erro listar históricos: "
            + e.getMessage());

            return null;
        }

    }

    public void excluirHistorico(int id) {

        String sql =
        "DELETE FROM historicos_medicos WHERE historico_id=?";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();

            conn.close();

            System.out.println("Histórico excluído!");

        } catch (Exception e) {

            System.out.println(
            "Erro excluir histórico: "
            + e.getMessage());

        }

    }

}
