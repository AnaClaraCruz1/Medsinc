/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author annac
 * 
 */
import dao.ConsultaDAO;

import model.Consulta;

import dao.PacienteDAO;

import dao.Conexao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JTextField;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;

public class ConsultaView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ConsultaView.class.getName());
    
    private java.util.ArrayList<String> listaPacientes =
    new java.util.ArrayList<>();
    /**
     * Creates new form ConsultaView
     */
    public ConsultaView() {
        initComponents();
        
        cbPaciente.setEditable(true);
        
        setSize(1200, 700);

        setLocationRelativeTo(null);

        setResizable(false);
        carregarTabela();
        carregarMedicos();
        carregarPacientesCombo();
        configurarAutoComplete();
        
    }    
    public void carregarTabela() {

    try {

        ConsultaDAO dao =
        new ConsultaDAO();

        ResultSet rs =
        dao.listarConsultas();

        DefaultTableModel modelo =
        (DefaultTableModel)
        tblConsultas.getModel();

        modelo.setRowCount(0);

        while(rs.next()) {

            modelo.addRow(new Object[] {

                rs.getInt("consulta_id"),
                rs.getString("paciente"),
                rs.getString("medico"),
                rs.getString("data_consulta"),
                rs.getString("hora_consulta"),
                rs.getString("status")

            });

        }

    } catch (Exception e) {

        System.out.println(
        "Erro tabela consultas: "
        + e.getMessage());

    }
    
    }
    public void carregarPacientesCombo() {

        try {

            Connection conn =
            Conexao.conectar();

            String sql =

            "SELECT paciente_id, nome " +
            "FROM pacientes " +
            "ORDER BY nome";

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            ResultSet rs =
            stmt.executeQuery();

            cbPaciente.removeAllItems();

            listaPacientes.clear();

            while(rs.next()) {

                String paciente =

                rs.getInt("paciente_id")
                + " - " +
                rs.getString("nome");

                listaPacientes.add(paciente);

                cbPaciente.addItem(paciente);

            }
            cbPaciente.setSelectedIndex(-1);

        } catch (Exception e) {

            System.out.println(
            "Erro combo pacientes: "
            + e.getMessage());

        }

    }
    public void carregarMedicos() {

    try {

        Connection conn =
        Conexao.conectar();

        String sql =
        "SELECT usuario_id, nome FROM usuarios WHERE perfil='medico'";

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        ResultSet rs =
        stmt.executeQuery();

        cbMedico.removeAllItems();

        while(rs.next()) {

            cbMedico.addItem(

            rs.getInt("usuario_id")
            + " - " +
            rs.getString("nome")

            );

        }
        cbMedico.setSelectedIndex(-1);

    } catch (Exception e) {

        System.out.println(
        "Erro carregar médicos: "
        + e.getMessage());

    }


    }
    
    public void configurarAutoComplete() {

        cbPaciente.setEditable(true);

        JTextField campo =

        (JTextField)
        cbPaciente.getEditor().getEditorComponent();

        campo.addKeyListener(
        new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(
            java.awt.event.KeyEvent evt) {

                String texto =
                campo.getText().toLowerCase();

                DefaultComboBoxModel<String> modelo =
                new DefaultComboBoxModel<>();

                for(String paciente : listaPacientes) {

                    if(paciente.toLowerCase()
                    .contains(texto)) {

                        modelo.addElement(paciente);

                    }

                }

                cbPaciente.setModel(modelo);

                campo.setText(texto);

                cbPaciente.showPopup();

            }

        });

    }
    public void limparCampos() {

    cbPaciente.setSelectedIndex(-1);

    txtData.setText("");

    txtHora.setText("");

    cbStatus.setSelectedIndex(0);

    cbMedico.setSelectedIndex(0);


}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bntPaciente = new javax.swing.JButton();
        bntConsulta = new javax.swing.JButton();
        bntHistorico = new javax.swing.JButton();
        btnEstoque = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        cbMedico = new javax.swing.JComboBox<>();
        txtData = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        cbPaciente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bntPaciente.setText("Pacientes");
        bntPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPacienteActionPerformed(evt);
            }
        });

        bntConsulta.setBackground(new java.awt.Color(45, 156, 219));
        bntConsulta.setForeground(new java.awt.Color(255, 255, 255));
        bntConsulta.setText("Consultas");
        bntConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntConsultaActionPerformed(evt);
            }
        });

        bntHistorico.setText("Histórico");
        bntHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntHistoricoActionPerformed(evt);
            }
        });

        btnEstoque.setText("Estoque");
        btnEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstoqueActionPerformed(evt);
            }
        });

        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(45, 156, 219));
        jLabel8.setText("Medsinc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntConsulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntHistorico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEstoque)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Sistema de Gestão Médica");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Selecionar Paciente");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Selecionar Médico");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Data");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Horário");

        btnSalvar.setBackground(new java.awt.Color(45, 156, 219));
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Agendar Consulta");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STATUS DA CONSULTA:", "Agendada", "Realizada", "Cancelada", " ", " ", " " }));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Paciente", "Médico", "Data", "Hora", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblConsultas);

        btnExcluir.setBackground(new java.awt.Color(102, 102, 102));
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        cbMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                                .addGap(265, 265, 265))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSalvar)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(cbMedico, 0, 704, Short.MAX_VALUE)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(203, 203, 203))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Agendamento de Consulta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPacienteActionPerformed
        new PacienteView().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_bntPacienteActionPerformed

    private void bntConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntConsultaActionPerformed
        new ConsultaView().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_bntConsultaActionPerformed

    private void bntHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntHistoricoActionPerformed
        new HistoricoView().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_bntHistoricoActionPerformed

    private void btnEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstoqueActionPerformed
        new EstoqueView().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnEstoqueActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new MenuView().setVisible(true);
        this. dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Consulta c = new Consulta();

        String pacienteSelecionado =

        cbPaciente.getSelectedItem()
        .toString();

        int pacienteId =

        Integer.parseInt(
        pacienteSelecionado.split(" - ")[0]);

        c.setPacienteId(pacienteId);

        String medicoSelecionado =
        cbMedico.getSelectedItem()
        .toString();

        int medicoId =
        Integer.parseInt(
        medicoSelecionado.split(" - ")[0]);

        c.setMedicoId(medicoId);
        
        String dataBr =
        txtData.getText();

        if(dataBr.length() < 10) {

            JOptionPane.showMessageDialog(
            null,
            "Digite a data completa!");

            return;
        }

        String[] partes =
        dataBr.split("/");

        String dataMysql =

        partes[2] + "-" +
        partes[1] + "-" +
        partes[0];
        
        c.setData(dataMysql);

        c.setHora(
        txtHora.getText());

        c.setStatus(
        cbStatus.getSelectedItem()
        .toString());

        ConsultaDAO dao =
        new ConsultaDAO();

        dao.salvar(c);

        carregarTabela();

        limparCampos();

        JOptionPane.showMessageDialog(
        null,
        "Consulta salva!");// TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linha =
        tblConsultas.getSelectedRow();

        if(linha == -1) {

            JOptionPane.showMessageDialog(
            null,
            "Selecione uma consulta");

            return;
        }

        int id = Integer.parseInt(

        tblConsultas.getValueAt(
        linha,
        0).toString());

        ConsultaDAO dao =
        new ConsultaDAO();

        dao.excluirConsulta(id);

        carregarTabela();

        JOptionPane.showMessageDialog(
        null,
        "Consulta excluída!");// TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void cbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPacienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ConsultaView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntConsulta;
    private javax.swing.JButton bntHistorico;
    private javax.swing.JButton bntPaciente;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEstoque;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbMedico;
    private javax.swing.JComboBox<String> cbPaciente;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConsultas;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
