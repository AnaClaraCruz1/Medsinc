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

public class UsuarioDAO {

    public boolean autenticar(String email, String senha) {

        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {

                return true;

            }

        } catch (Exception e) {

            System.out.println("Erro login: " + e.getMessage());

        }

        return false;
    }
}
