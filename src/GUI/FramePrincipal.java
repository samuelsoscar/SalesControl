package GUI;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class FramePrincipal extends javax.swing.JFrame {
   FrameIngresoOrden frameIngresoOrden = new FrameIngresoOrden();
   FrameNombreClientes frameNombreClientes = new FrameNombreClientes();
   
    public FramePrincipal() {
       initComponents();
       desktop.add(frameIngresoOrden);
       desktop.add(frameNombreClientes);
       
       int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 500, 500);
       this.setExtendedState(MAXIMIZED_BOTH);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shadowFactory1 = new org.edisoncor.gui.util.ShadowFactory();
        btnIngresarOrden = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnVerOrden = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIngresarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ver.png"))); // NOI18N
        btnIngresarOrden.setMaximumSize(new java.awt.Dimension(161, 130));
        btnIngresarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarOrdenActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("Ingresar Orden");

        btnVerOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Editar.png"))); // NOI18N
        btnVerOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerOrdenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("Ver y Modificar ");

        desktop.setForeground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1085, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIngresarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnVerOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktop))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnIngresarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(btnVerOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel3))
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnVerOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerOrdenActionPerformed
       
        frameNombreClientes.setVisible(true);
   
    }//GEN-LAST:event_btnVerOrdenActionPerformed

    private void btnIngresarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarOrdenActionPerformed
        // TODO add your handling code here:
       frameIngresoOrden.setVisible(true);
    }//GEN-LAST:event_btnIngresarOrdenActionPerformed
   
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
   try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
       if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
           break;
        }
    }
    //UIManager.setLookAndFeel(
      //      UIManager.getSystemLookAndFeelClassName());
   //   UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
      //UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
      
    //UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
 //  UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel()); //good
    // UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
     //UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
     
     
     
      
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresarOrden;
    private javax.swing.JButton btnVerOrden;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private org.edisoncor.gui.util.ShadowFactory shadowFactory1;
    // End of variables declaration//GEN-END:variables
}
