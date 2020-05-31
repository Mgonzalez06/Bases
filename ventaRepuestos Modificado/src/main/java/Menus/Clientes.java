package Menus;
import Personas.*;
import Partes.*;
import Organizaciones.*;
import Conexion.*;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Liseth
 */
public class Clientes extends javax.swing.JFrame {

    /**
     * Creates new form Clientes
     */
    Menu ventanaM;
    
    CrearVentanasClientes ventanas;
    public Clientes() {
        ventanas = new CrearVentanasClientes(); 
        initComponents();
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
        r_per = new javax.swing.JCheckBox();
        r_org = new javax.swing.JCheckBox();
        r_ins = new javax.swing.JRadioButton();
        r_mod = new javax.swing.JRadioButton();
        r_sus = new javax.swing.JRadioButton();
        continuar = new javax.swing.JButton();
        atras = new javax.swing.JButton();
        listar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Clientes");

        r_per.setText("Persona");
        r_per.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_perMouseClicked(evt);
            }
        });

        r_org.setText("Organización");
        r_org.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_orgMouseClicked(evt);
            }
        });

        r_ins.setText("Insertar");
        r_ins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_insMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                r_insMouseEntered(evt);
            }
        });

        r_mod.setText("Modificar");
        r_mod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_modMouseClicked(evt);
            }
        });

        r_sus.setText("Cambiar estado");
        r_sus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_susMouseClicked(evt);
            }
        });

        continuar.setText("Continuar");
        continuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continuarMouseClicked(evt);
            }
        });
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });

        atras.setText("Atrás");
        atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atrasMouseClicked(evt);
            }
        });

        listar.setText("Listar");
        listar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(atras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continuar)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(listar)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(r_org)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(r_sus)
                        .addComponent(r_mod)
                        .addComponent(r_ins))
                    .addComponent(r_per, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(281, 281, 281))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(listar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r_per)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r_org)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(r_ins)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r_mod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r_sus)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continuar)
                    .addComponent(atras))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_continuarActionPerformed

    private void atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasMouseClicked
        // TODO add your handling code here:
       ventanaM.setVisible(true);
       this.dispose();
       r_per.setSelected(false);
       r_org.setSelected(false);
       r_ins.setSelected(false);
       r_mod.setSelected(false);
       r_sus.setSelected(false);
      
    }//GEN-LAST:event_atrasMouseClicked

    private void r_perMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_perMouseClicked
        // TODO add your handling code here:
        r_org.setSelected(false);
        r_org.setHideActionText(false);
    }//GEN-LAST:event_r_perMouseClicked

    private void r_orgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_orgMouseClicked
        // TODO add your handling code here:
        r_per.setSelected(false);
        r_per.setHideActionText(false);
    }//GEN-LAST:event_r_orgMouseClicked

    private void r_insMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_insMouseClicked
        // TODO add your handling code here:
        r_mod.setSelected(false);
        r_sus.setSelected(false);
       
    }//GEN-LAST:event_r_insMouseClicked

    private void r_modMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_modMouseClicked
        // TODO add your handling code here:
        r_ins.setSelected(false);
        r_sus.setSelected(false);
       
    }//GEN-LAST:event_r_modMouseClicked

    private void r_susMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_susMouseClicked
        // TODO add your handling code here:
        r_ins.setSelected(false);
        r_mod.setSelected(false);
       
    }//GEN-LAST:event_r_susMouseClicked
    public void instruccionesCliente(){
        if (r_per.isSelected()==true){
            if(r_ins.isSelected()==true){
                ventanas.getVentanaIP().setVisible(true);
                ventanas.getVentanaIP().ventanaC=this;
                this.dispose();
            }
            else if(r_mod.isSelected()==true){
                ventanas.getVentanaMP().setVisible(true);
                ventanas.getVentanaMP().ventanaC=this;
                this.dispose();
            }
            else if(r_sus.isSelected()==true){
                ventanas.getVentanaSP().setVisible(true);
                ventanas.getVentanaSP().ventanaC=this;
                this.dispose();
            }   
            
                
            
        }
        else if(r_org.isSelected()==true){
            if(r_ins.isSelected()==true){
                ventanas.getVentanaIO().setVisible(true);
                ventanas.getVentanaIO().ventanaC = this;
                this.dispose();
            }
            else if(r_mod.isSelected()==true){
                ventanas.getVentanaMO().setVisible(true);
                ventanas.getVentanaMO().ventanaC=this;
                this.dispose();
            }
            else if(r_sus.isSelected()==true){
                ventanas.getVentanaSO().setVisible(true);
                ventanas.getVentanaSO().ventanaC=this;
                this.dispose();
            }
        }
    }
    private void continuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continuarMouseClicked
        instruccionesCliente();
    }//GEN-LAST:event_continuarMouseClicked

    private void listarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listarMouseClicked
       ventanas.getVentanaLP().setVisible(true);
       ventanas.getVentanaLP().ventanaC=this;
       this.dispose();
    }//GEN-LAST:event_listarMouseClicked

    private void r_insMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_insMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_r_insMouseEntered

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JButton continuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton listar;
    private javax.swing.JRadioButton r_ins;
    private javax.swing.JRadioButton r_mod;
    private javax.swing.JCheckBox r_org;
    private javax.swing.JCheckBox r_per;
    private javax.swing.JRadioButton r_sus;
    // End of variables declaration//GEN-END:variables
}