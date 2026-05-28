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
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/medsinc",
                    "root",
                    "1234"
            );

            System.out.println("Conectado com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());

        }

        return conn;
    }
}
