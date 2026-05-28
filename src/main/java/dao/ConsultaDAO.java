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

import model.Consulta;

public class ConsultaDAO {

    // SALVAR
    public void salvar(Consulta c) {

        String sql =
        "INSERT INTO consultas(paciente_id, medico_id, data_consulta, hora_consulta, status) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(1, c.getPacienteId());

            stmt.setInt(2, c.getMedicoId());

            stmt.setString(3, c.getData());

            stmt.setString(4, c.getHora());

            stmt.setString(5,
            c.getStatus().toLowerCase());

            stmt.executeUpdate();

            System.out.println(
            "Consulta salva!");

            conn.close();

        } catch (Exception e) {

            System.out.println(
            "Erro salvar consulta: "
            + e.getMessage());

        }

    }

    // LISTAR
    public ResultSet listarConsultas() {

        String sql =
        

        "SELECT " +

        "c.consulta_id, " +

        "CONCAT(p.paciente_id, ' - ', p.nome) AS paciente, " +

        "CONCAT(u.usuario_id, ' - ', u.nome) AS medico, " +

        "c.data_consulta, " +

        "c.hora_consulta, " +

        "c.status " +

        "FROM consultas c " +

        "INNER JOIN pacientes p " +
        "ON c.paciente_id = p.paciente_id " +

        "INNER JOIN usuarios u " +
        "ON c.medico_id = u.usuario_id";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            return stmt.executeQuery();

        } catch (Exception e) {

            System.out.println(
            "Erro listar consultas: "
            + e.getMessage());

            return null;
        }

    }

    // EXCLUIR
    public void excluirConsulta(int id) {

        String sql =
        "DELETE FROM consultas WHERE consulta_id=?";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(
            "Erro excluir consulta: "
            + e.getMessage());

        }

    }

}
