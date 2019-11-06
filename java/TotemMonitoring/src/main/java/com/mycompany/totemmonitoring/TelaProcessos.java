package com.mycompany.totemmonitoring;

import monitoramento.Refresh;
import monitoramento.Totem;

public class TelaProcessos extends javax.swing.JFrame {

    Totem totem;
    Refresh tempo;

    public TelaProcessos() {
        totem = new Totem();
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbProcessos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textProcessos = new javax.swing.JTextArea();
        lblPid = new javax.swing.JLabel();
        lblPid1 = new javax.swing.JLabel();
        lblPid2 = new javax.swing.JLabel();
        lblPid4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        lbProcessos.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 48)); // NOI18N
        lbProcessos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProcessos.setText("Processos");
        lbProcessos.setToolTipText("");

        textProcessos.setColumns(20);
        textProcessos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        textProcessos.setRows(5);
        textProcessos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textProcessos.setEnabled(false);
        jScrollPane1.setViewportView(textProcessos);

        lblPid.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        lblPid.setText("PID");

        lblPid1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        lblPid1.setText("CPU(%)");

        lblPid2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        lblPid2.setText("MEM(%)");

        lblPid4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        lblPid4.setText("Name");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lblPid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPid4)
                .addGap(118, 118, 118)
                .addComponent(lblPid1)
                .addGap(88, 88, 88)
                .addComponent(lblPid2)
                .addGap(81, 81, 81))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbProcessos)
                .addGap(335, 335, 335))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(342, 342, 342)
                        .addComponent(jButton1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPid4)
                    .addComponent(lblPid)
                    .addComponent(lblPid1)
                    .addComponent(lblPid2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Refresh(0);
        textProcessos.setText(totem.getProcessos());
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProcessos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProcessos;
    private javax.swing.JLabel lblPid;
    private javax.swing.JLabel lblPid1;
    private javax.swing.JLabel lblPid2;
    private javax.swing.JLabel lblPid4;
    private javax.swing.JTextArea textProcessos;
    // End of variables declaration//GEN-END:variables
}
