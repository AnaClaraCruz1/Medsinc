/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author annac
 */

import model.Paciente;
import model.Usuario;
import model.Consulta;
import dao.Conexao;
import view.LoginView;

public class Main {

    public static void main(String[] args) {
        
        Conexao.conectar();

        new LoginView().setVisible(true);

    }
}
