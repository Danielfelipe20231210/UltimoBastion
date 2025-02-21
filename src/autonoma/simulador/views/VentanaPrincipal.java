/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package autonoma.simulador.views;

import autonoma.simulador.main.JuegoGUI;
import autonoma.simulador.models.Juego;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class VentanaPrincipal extends javax.swing.JDialog {

    private Juego juego;
    
    FondoPanel fondo = new FondoPanel();
    
    
    
    public VentanaPrincipal(Juego juego) {

        this.setContentPane(fondo);
        initComponents();
        
        this.juego = juego;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFacil = new javax.swing.JButton();
        btnNorman = new javax.swing.JButton();
        btnDificil = new javax.swing.JButton();
        bttnIntroduccion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnFacil.setBackground(new java.awt.Color(0, 51, 0));
        btnFacil.setForeground(new java.awt.Color(255, 255, 255));
        btnFacil.setText("Facil");
        btnFacil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFacilMouseClicked(evt);
            }
        });
        btnFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacilActionPerformed(evt);
            }
        });

        btnNorman.setBackground(new java.awt.Color(0, 0, 51));
        btnNorman.setForeground(new java.awt.Color(255, 255, 255));
        btnNorman.setText("Normal");
        btnNorman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNormanMouseClicked(evt);
            }
        });

        btnDificil.setBackground(new java.awt.Color(153, 0, 0));
        btnDificil.setForeground(new java.awt.Color(255, 255, 255));
        btnDificil.setText("Dificil");
        btnDificil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDificilMouseClicked(evt);
            }
        });

        bttnIntroduccion.setText("Induccion");
        bttnIntroduccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttnIntroduccionMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel1.setText("Elije la dificultad del juego");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnFacil)
                .addGap(45, 45, 45)
                .addComponent(btnNorman)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnDificil)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttnIntroduccion)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFacil)
                    .addComponent(btnNorman)
                    .addComponent(btnDificil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(bttnIntroduccion)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacilActionPerformed

    private void btnFacilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacilMouseClicked

        Juego juego = new Juego();
        JuegoGUI gui = new JuegoGUI(juego);
        gui.iniciar();
        
    }//GEN-LAST:event_btnFacilMouseClicked

    private void btnNormanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNormanMouseClicked

        Juego juego = new Juego();
        JuegoGUI gui = new JuegoGUI(juego);
        gui.iniciar();
        
    }//GEN-LAST:event_btnNormanMouseClicked

    private void btnDificilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDificilMouseClicked
        Juego juego = new Juego();
        JuegoGUI gui = new JuegoGUI(juego);
        gui.iniciar();
    }//GEN-LAST:event_btnDificilMouseClicked

    private void bttnIntroduccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttnIntroduccionMouseClicked
        JOptionPane.showMessageDialog(null, "El juego se basa en proteger tu aldea de enemigos que vienen. Buena suerte");
    }//GEN-LAST:event_bttnIntroduccionMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDificil;
    private javax.swing.JButton btnFacil;
    private javax.swing.JButton btnNorman;
    private javax.swing.JButton bttnIntroduccion;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

     class FondoPanel extends JPanel
     {
         private Image imagen;
         
         @Override
         public void paint(Graphics g)
         {
             imagen = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/fondo.png")).getImage(); 
             
             g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
             
             setOpaque(false);
             super.paint(g);
             
         }     
     }
}
