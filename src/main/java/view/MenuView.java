/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author annac
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;

import dao.Conexao;

public class MenuView extends javax.swing.JFrame {
    Color[] cores = {

        new Color(45,156,219),   // azul
        new Color(39,174,96),    // verde
        new Color(241,196,15),   // amarelo
        new Color(231,76,60),    // vermelho
        new Color(155,89,182),   // roxo
        new Color(26,188,156),   // turquesa
        new Color(230,126,34)    // laranja

    };
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuView.class.getName());

    /**
     * Creates new form MenuView
     */
    public MenuView() {
        initComponents();
        
        setSize(1300, 700);

        setLocationRelativeTo(null);

        setResizable(false);
        
        carregarIndicadores();
        
        carregarGraficoPizza();

        carregarGraficoEstoque();
        
        configurarCards();
    }
    
    public void carregarIndicadores() {

        try {

            Connection conn =
            Conexao.conectar();

            // TOTAL PACIENTES

            String sqlPacientes =
            "SELECT COUNT(*) AS total FROM pacientes";

            PreparedStatement stmtPacientes =
            conn.prepareStatement(sqlPacientes);

            ResultSet rsPacientes =
            stmtPacientes.executeQuery();

            if(rsPacientes.next()) {

                lblPacientes.setText(
                rsPacientes.getString("total"));

            }

            // TOTAL CONSULTAS

            String sqlConsultas =
            "SELECT COUNT(*) AS total FROM consultas";

            PreparedStatement stmtConsultas =
            conn.prepareStatement(sqlConsultas);

            ResultSet rsConsultas =
            stmtConsultas.executeQuery();

            if(rsConsultas.next()) {

                lblConsultas.setText(
                rsConsultas.getString("total"));

            }

            // ESTOQUE BAIXO

            String sqlEstoque =
            "SELECT COUNT(*) AS total FROM estoque " +
            "WHERE quantidade <= quantidade_minima";

            PreparedStatement stmtEstoque =
            conn.prepareStatement(sqlEstoque);

            ResultSet rsEstoque =
            stmtEstoque.executeQuery();

            if(rsEstoque.next()) {

                lblEstoqueBaixo.setText(
                rsEstoque.getString("total"));

            }
            
            String sqlHoje =

            "SELECT COUNT(*) AS total " +
            "FROM consultas " +
            "WHERE data_consulta = CURDATE()";

            PreparedStatement stmtHoje =
            conn.prepareStatement(sqlHoje);

            ResultSet rsHoje =
            stmtHoje.executeQuery();

            if(rsHoje.next()) {

                lblHoje.setText(

                rsHoje.getString("total")

                );

            }

        } catch (Exception e) {

            System.out.println(
            "Erro dashboard: "
            + e.getMessage());

        }

    }
    
    public void carregarGraficoPizza() {

        try {

            Connection conn
                    = Conexao.conectar();

            String sql
                    = "SELECT status, COUNT(*) AS total "
                    + "FROM consultas GROUP BY status";

            PreparedStatement stmt
                    = conn.prepareStatement(sql);

            ResultSet rs
                    = stmt.executeQuery();

            DefaultPieDataset dados
                    = new DefaultPieDataset();

            while (rs.next()) {

                dados.setValue(
                        rs.getString("status"),
                        rs.getInt("total")
                );

            }

            JFreeChart grafico =

            ChartFactory.createPieChart(

                "Consultas por Status",
                dados,
                true,
                true,
                false

            );

            PiePlot plot =
            (PiePlot) grafico.getPlot();

            int i = 0;

            for(Object key : dados.getKeys()) {

                plot.setSectionPaint(

                    (Comparable) key,
                    cores[i % cores.length]

                );

                i++;

            }

            ChartPanel painel =
            new ChartPanel(grafico);

            painelPizza.removeAll();

            painelPizza.setLayout(
            new BorderLayout());

            painelPizza.add(painel);

            painelPizza.validate();

        } catch (Exception e) {

            System.out.println(

            "Erro gráfico pizza: "
            + e.getMessage()

            );

        }

    }

    public void carregarGraficoEstoque() {
        
        try {

            Connection conn =
            Conexao.conectar();

            String sql =

            "SELECT nome_item, quantidade " +
            "FROM estoque LIMIT 5";

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            ResultSet rs =
            stmt.executeQuery();

            DefaultCategoryDataset dados =
            new DefaultCategoryDataset();

            while(rs.next()) {

                dados.addValue(

                    rs.getInt("quantidade"),
                    "Quantidade",
                    rs.getString("nome_item")

                );

            }

            JFreeChart grafico =

            ChartFactory.createBarChart(

                "Itens em Estoque",
                "Medicamento",
                "Quantidade",
                dados,
                PlotOrientation.VERTICAL,
                false,
                true,
                false

            );

            BarRenderer renderer =
            (BarRenderer)grafico.getCategoryPlot().getRenderer();
            
            renderer.setMaximumBarWidth(0.05);

            new BarRenderer() {

                @Override
                public Paint getItemPaint(
                int row,
                int column) {

                    return cores[
                    column % cores.length];

                }

            };

            grafico.getCategoryPlot()
            .setRenderer(renderer);

            ChartPanel painel =
            new ChartPanel(grafico);

            painelBarras.removeAll();

            painelBarras.setLayout(
            new BorderLayout());

            painelBarras.add(painel);

            painelBarras.validate();

        } catch (Exception e) {

            System.out.println(

            "Erro gráfico barras: "
            + e.getMessage()

            );

        }

    }

    public void configurarCards() {

        lblPacientes.setOpaque(true);
        lblConsultas.setOpaque(true);
        lblEstoqueBaixo.setOpaque(true);
        lblHoje.setOpaque(true);

        lblPacientes.setBackground(cores[0]);
        lblConsultas.setBackground(cores[1]);
        lblEstoqueBaixo.setBackground(cores[2]);
        lblHoje.setBackground(cores[3]);

        lblPacientes.setForeground(Color.WHITE);
        lblConsultas.setForeground(Color.WHITE);
        lblEstoqueBaixo.setForeground(Color.WHITE);
        lblHoje.setForeground(Color.WHITE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bntPaciente = new javax.swing.JButton();
        bntConsulta = new javax.swing.JButton();
        bntHistorico = new javax.swing.JButton();
        btnEstoque = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPacientes = new javax.swing.JLabel();
        lblConsultas = new javax.swing.JLabel();
        lblEstoqueBaixo = new javax.swing.JLabel();
        lblHoje = new javax.swing.JLabel();
        painelPizza = new javax.swing.JPanel();
        painelBarras = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bntPaciente.setText("Pacientes");
        bntPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPacienteActionPerformed(evt);
            }
        });

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

        btnDashboard.setBackground(new java.awt.Color(45, 156, 219));
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(btnDashboard)
                .addGap(18, 18, 18)
                .addComponent(bntPaciente)
                .addGap(18, 18, 18)
                .addComponent(bntConsulta)
                .addGap(18, 18, 18)
                .addComponent(bntHistorico)
                .addGap(18, 18, 18)
                .addComponent(btnEstoque)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Sistema de Gestão Médica");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Dashboard");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Consultas Recentes ");

        lblPacientes.setBackground(new java.awt.Color(153, 204, 255));
        lblPacientes.setFont(new java.awt.Font("Segoe UI Light", 1, 32)); // NOI18N
        lblPacientes.setText("jLabel9");
        lblPacientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblPacientes.setPreferredSize(new java.awt.Dimension(220, 100));

        lblConsultas.setBackground(new java.awt.Color(153, 204, 255));
        lblConsultas.setFont(new java.awt.Font("Segoe UI Light", 1, 32)); // NOI18N
        lblConsultas.setText("jLabel9");
        lblConsultas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblConsultas.setPreferredSize(new java.awt.Dimension(220, 100));

        lblEstoqueBaixo.setBackground(new java.awt.Color(153, 204, 255));
        lblEstoqueBaixo.setFont(new java.awt.Font("Segoe UI Light", 1, 32)); // NOI18N
        lblEstoqueBaixo.setText("jLabel9");
        lblEstoqueBaixo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblEstoqueBaixo.setPreferredSize(new java.awt.Dimension(220, 100));

        lblHoje.setBackground(new java.awt.Color(153, 204, 255));
        lblHoje.setFont(new java.awt.Font("Segoe UI Light", 1, 32)); // NOI18N
        lblHoje.setText("jLabel9");
        lblHoje.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHoje.setPreferredSize(new java.awt.Dimension(220, 100));

        painelPizza.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelPizza.setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout painelPizzaLayout = new javax.swing.GroupLayout(painelPizza);
        painelPizza.setLayout(painelPizzaLayout);
        painelPizzaLayout.setHorizontalGroup(
            painelPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        painelPizzaLayout.setVerticalGroup(
            painelPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        painelBarras.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelBarras.setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout painelBarrasLayout = new javax.swing.GroupLayout(painelBarras);
        painelBarras.setLayout(painelBarrasLayout);
        painelBarrasLayout.setHorizontalGroup(
            painelBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        painelBarrasLayout.setVerticalGroup(
            painelBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Consultas do Dia");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Itens Críticos");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Pacientes Ativos");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Hoje");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEstoqueBaixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblHoje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(389, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(painelPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstoqueBaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(painelPizza, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void bntPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPacienteActionPerformed
        new PacienteView().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_bntPacienteActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new MenuView().setVisible(true);
        this. dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnDashboardActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new MenuView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntConsulta;
    private javax.swing.JButton bntHistorico;
    private javax.swing.JButton bntPaciente;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEstoque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConsultas;
    private javax.swing.JLabel lblEstoqueBaixo;
    private javax.swing.JLabel lblHoje;
    private javax.swing.JLabel lblPacientes;
    private javax.swing.JPanel painelBarras;
    private javax.swing.JPanel painelPizza;
    // End of variables declaration//GEN-END:variables
}
