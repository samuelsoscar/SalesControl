package GUI;
import Datos.Operaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

public class FrameNombreClientes extends javax.swing.JInternalFrame {
String colNames[] ={"ID_Cliente","Nombre"};

    DefaultTableModel modelo = new DefaultTableModel(colNames,0);
    FrameOrdenesPorCliente frameOrdenesPorCliente = new FrameOrdenesPorCliente();
    Operaciones operaciones = new Operaciones();
 
    public FrameNombreClientes () {
        initComponents();
        tablaNombres.setModel(modelo);
        tablaNombres.setDefaultEditor(Object.class, null); // evita la edicion
        
        tablaNombres.getColumnModel().getColumn(0).setMaxWidth(120);
      
        
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem = new JMenuItem("Eliminar Cliente");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int fila = tablaNombres.getSelectedRow();
                int opcion = JOptionPane.showConfirmDialog(null,"Deseas realmente eliminar el cliente: "+tablaNombres.getValueAt(fila, 1)+"\nTen en cuenta que se eliminarán todas las facturas del cliente ");
                if(opcion == JOptionPane.YES_OPTION){
                    String id_C = String.valueOf(tablaNombres.getValueAt(fila, 0));
                    operaciones.eliminarCliente(id_C);
                    operaciones.consultaNombres(modelo);
                }  
            }
        });
        jPopupMenu.add(jMenuItem);
        tablaNombres.setComponentPopupMenu(jPopupMenu);
        btnBusqueda.setEnabled(false);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnBusqueda = new javax.swing.JButton();
        txtBusquedaCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaNombres = new javax.swing.JTable();
        bntActualizar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ver Ordenes");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setText("Buscar Cliente");

        btnBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search32.png"))); // NOI18N
        btnBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaActionPerformed(evt);
            }
        });

        txtBusquedaCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBusquedaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaClienteKeyTyped(evt);
            }
        });

        tablaNombres.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaNombres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaNombres.setToolTipText("Presiona doble click para Ver Ordenes y click derecho para Eliminar Cliente");
        tablaNombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaNombresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaNombres);

        bntActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntActualizar.setText("Actualizar");
        bntActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntActualizarActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Eliminar Orden");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bntActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusqueda)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtBusquedaCliente)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel4)))
                    .addComponent(bntActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaNombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaNombresMouseClicked
        // TODO add your handling code here:
        String nombreBusqueda;
        String id;
        if(evt.getClickCount() == 2 && !evt.isConsumed()){
            int fila = tablaNombres.getSelectedRow();
            nombreBusqueda = String.valueOf(tablaNombres.getValueAt(fila, 1));
            id = String.valueOf(tablaNombres.getValueAt(fila, 0));
            frameOrdenesPorCliente.setVisible(true);
            FrameOrdenesPorCliente.txtModNombre.setText(nombreBusqueda);
            FrameOrdenesPorCliente.etiquetaRecibeId.setText(id);
            FrameOrdenesPorCliente.modeloTablaDetalle.setRowCount(0);
        }
        evt.consume();
    }//GEN-LAST:event_tablaNombresMouseClicked

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
        // TODO add your handling code here:
        operaciones.busquedaCliente((DefaultTableModel)tablaNombres.getModel(), txtBusquedaCliente.getText());
    }//GEN-LAST:event_btnBusquedaActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        operaciones.consultaNombres(modelo);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtBusquedaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaClienteKeyTyped
        // TODO add your handling code here:
        btnBusqueda.setEnabled(true);
       operaciones.busquedaCliente((DefaultTableModel)tablaNombres.getModel(), txtBusquedaCliente.getText());
    }//GEN-LAST:event_txtBusquedaClienteKeyTyped

    private void bntActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntActualizarActionPerformed
        // TODO add your handling code here:
        operaciones.consultaNombres(modelo);
    }//GEN-LAST:event_bntActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntActualizar;
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaNombres;
    private javax.swing.JTextField txtBusquedaCliente;
    // End of variables declaration//GEN-END:variables
}
