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

import model.Estoque;

public class EstoqueDAO{

public void salvar(Estoque e) {

    String sql =

    "INSERT INTO estoque " +

    "(nome_item, quantidade, unidade_medida, quantidade_minima, atualizado_por) " +

    "VALUES (?, ?, ?, ?, ?)";

    try {

        Connection conn =
        Conexao.conectar();

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        stmt.setString(1, e.getNomeItem());

        stmt.setInt(2, e.getQuantidade());

        stmt.setString(3, e.getUnidadeMedida());

        stmt.setInt(4, e.getQuantidadeMinima());

        stmt.setInt(5, 1);

        stmt.execute();

        System.out.println(
        "Item salvo!");

    } catch (Exception ex) {

        System.out.println(
        "Erro salvar estoque: "
        + ex.getMessage());

    }
    }
    
    public ResultSet listar() {

    String sql =
    "SELECT * FROM estoque";

    try {

        Connection conn =
        Conexao.conectar();

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        return stmt.executeQuery();

    } catch (Exception e) {

        System.out.println(
        "Erro listar estoque: "
        + e.getMessage());

        return null;
    }
    }
    
    public ResultSet pesquisar(String nome) {

    String sql =

    "SELECT * FROM estoque " +
    "WHERE nome_item LIKE ?";

    try {

        Connection conn =
        Conexao.conectar();

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        stmt.setString(
        1,
        "%" + nome + "%");

        return stmt.executeQuery();

    } catch (Exception e) {

        System.out.println(
        "Erro pesquisar estoque: "
        + e.getMessage());

        return null;
    }
    }
    
    public void excluir(int id) {

    String sql =
    "DELETE FROM estoque WHERE item_id=?";

    try {

        Connection conn =
        Conexao.conectar();

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();

        System.out.println(
        "Item excluído!");

    } catch (Exception e) {

        System.out.println(
        "Erro excluir estoque: "
        + e.getMessage());

    }
    }
    
    public void atualizarQuantidade(
        int id,
        int quantidade) {

            String sql =

            "UPDATE estoque " +

            "SET quantidade=? " +

            "WHERE item_id=?";

            try {

                Connection conn =
                Conexao.conectar();

                PreparedStatement stmt =
                conn.prepareStatement(sql);

                stmt.setInt(1, quantidade);

                stmt.setInt(2, id);

                stmt.execute();

                System.out.println(
                "Quantidade atualizada!");

            } catch (Exception e) {

                System.out.println(
                "Erro atualizar: "
                + e.getMessage());

        }
    }
}    
    








