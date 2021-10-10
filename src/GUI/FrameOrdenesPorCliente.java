package GUI;

import Datos.ConsultaParaAutoCompletado;
import Datos.Operaciones;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class FrameOrdenesPorCliente extends javax.swing.JFrame {
  String []datosOrdenes ={"ID_FACTURA","FECHA","TOTAL","ESTADO"};
  static String []datosDetalleOrden ={"ID_PRODUCTO","CODIGO","DESCRIPCION","CANTIDAD","PRECIO","CATALOGO","NUMERO DE PAGINA","NUMERO DE CAMPANIA"};
  Operaciones operaciones = new Operaciones();
  JComboBox cantidad = new JComboBox();
  JComboBox numPag   = new JComboBox();
  JComboBox numCamp  = new JComboBox();   
  JComboBox catalogo  = new JComboBox();
  JComboBox estado  = new JComboBox();
  JTextField txtF = new JTextField();
  
  JDateChooser chooser = new JDateChooser();
  
  DefaultTableModel modeloTablaOrdenes = new DefaultTableModel(datosOrdenes, 0);
  public static DefaultTableModel modeloTablaDetalle = new DefaultTableModel(datosDetalleOrden, 0);
   
    public FrameOrdenesPorCliente() {
        initComponents();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) fechaDetalle.getDateEditor();//hace el calendario no editable
        editor.setEnabled(false);
        txtModNombre.setEnabled(false);
        chooser.setDateFormatString("dd-MMMM-yyyy");
        btnGuardaMod.setEnabled(false);
        this.setLocationRelativeTo(null);
      
        
        tablaOrdenes.setDefaultEditor(Object.class, null); // evita la edicion
        tablaOrdenes.setAutoCreateRowSorter(true); //habilita el orden de tabla
        tablaOrdenes.setModel(modeloTablaOrdenes);
        tablaDetalle.setModel(modeloTablaDetalle);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0;i<4;i++){
            tablaOrdenes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        llenarCantidad();
        llenarNumCamp();
        llenarNumPag();
        llenarTipoRev();
        llenarEstado();
         modeloTablaDetalle = new DefaultTableModel(datosDetalleOrden,0){
        @Override
        public boolean isCellEditable(int row, int column) {
        return column!=0;  
    }
    };
        tablaDetalle.setModel(modeloTablaDetalle);
        TableColumn columna = tablaDetalle.getColumnModel().getColumn(3);//agrega los combo box a la tabla
        columna.setCellEditor(new DefaultCellEditor(cantidad));
        
        columna = tablaDetalle.getColumnModel().getColumn(5);//agrega los combo box a la tabla
        columna.setCellEditor(new DefaultCellEditor(catalogo));
        
        columna = tablaDetalle.getColumnModel().getColumn(6);//agrega los combo box a la tabla
        columna.setCellEditor(new DefaultCellEditor(numPag));
        
        columna = tablaDetalle.getColumnModel().getColumn(7);//agrega los combo box a la tabla
        columna.setCellEditor(new DefaultCellEditor(numCamp));
        
        columna = tablaOrdenes.getColumnModel().getColumn(3);//agrega los combo box a la tabla
        columna.setCellEditor(new DefaultCellEditor(estado));
        
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem = new JMenuItem("Eliminar Orden");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int fila = tablaDetalle.getSelectedRow()+1;
                System.out.println("fila "+fila);
                int opcion = JOptionPane.showConfirmDialog(null,"Deseas realmente eliminar la orden "+tablaOrdenes.getValueAt(fila, 0)+" de "+txtModNombre.getText());
                if(opcion == JOptionPane.YES_OPTION){
                    String id_f = String.valueOf(tablaOrdenes.getValueAt(fila, 0));
                    operaciones.eliminarFactura(id_f);
                }
                
            }
        });
        jPopupMenu.add(jMenuItem);
        tablaOrdenes.setComponentPopupMenu(jPopupMenu); 
        tablaOrdenes.getTableHeader().setReorderingAllowed(false);
        tablaDetalle.getTableHeader().setReorderingAllowed(false);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaOrdenes = new javax.swing.JTable();
        btnGuardarEstado = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnGuardaMod = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        etiquetaRecibeId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtModNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fechaDetalle = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtIdFactura = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordenes");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("Ordenes de: ");

        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });

        tablaOrdenes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaOrdenes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaOrdenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaOrdenes);

        btnGuardarEstado.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        btnGuardarEstado.setText("Guardar");
        btnGuardarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Ordenes", jPanel2);

        btnGuardaMod.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        btnGuardaMod.setText("Guardar Modificaciones");
        btnGuardaMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardaModActionPerformed(evt);
            }
        });

        tablaDetalle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalle.setCellSelectionEnabled(true);
        tablaDetalle.setGridColor(new java.awt.Color(255, 255, 255));
        tablaDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaDetalleKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaDetalle);
        tablaDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardaMod)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardaMod, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Detalle", jPanel1);

        etiquetaRecibeId.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setText("ID Cliente :");

        txtModNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("FECHA:");

        fechaDetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setText("ID Factura:");

        txtIdFactura.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(3, 3, 3)
                                .addComponent(txtModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(15, 15, 15)
                                .addComponent(etiquetaRecibeId, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10)
                                .addComponent(fechaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)
                                .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(tab))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(fechaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(etiquetaRecibeId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         //modeloTablaDetalle.setRowCount(0);
         modeloTablaOrdenes.setRowCount(0);
         //tab.setSelectedIndex(0);
         operaciones.consultaFacturas((DefaultTableModel)tablaOrdenes.getModel(),etiquetaRecibeId.getText());
         TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtModNombre);
        ConsultaParaAutoCompletado consultaParaAutoCompletado = new ConsultaParaAutoCompletado();
        consultaParaAutoCompletado.nombres(textAutoCompleter);
         
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
         modeloTablaDetalle.setRowCount(0);
         tab.setSelectedIndex(0); 
         if (tablaDetalle.isEditing()) {
        tablaDetalle.getCellEditor().stopCellEditing();
             }
        
    }//GEN-LAST:event_formWindowClosed

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
        try{
            if(tab.getSelectedIndex() ==0){
                modeloTablaOrdenes.setRowCount(0);
                modeloTablaDetalle.setRowCount(0);
                operaciones.consultaFacturas((DefaultTableModel)tablaOrdenes.getModel(),etiquetaRecibeId.getText());
                txtModNombre.setEnabled(false);
            }
        } catch(NullPointerException a){
                a.printStackTrace();
        }
    }//GEN-LAST:event_tabMouseClicked

    private void btnGuardaModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaModActionPerformed
        // TODO add your handling code here:
        String nombreCliente = txtModNombre.getText().toUpperCase();
        int id_factura = Integer.parseInt(txtIdFactura.getText());
        int id_cliente=Integer.parseInt(etiquetaRecibeId.getText());
         int tamanio = tablaDetalle.getRowCount();
            int cantidad =0;
            double total =0;
            double precio =0;
          
            String prc="";
            String cant ="";
            
        //operaciones.updateDeNombre(nombreCliente,id_factura,id_cliente);
        
        try{
            if (tablaDetalle.isEditing()) {
                tablaDetalle.getCellEditor().stopCellEditing();
            }
           
            
            int idProds[] = new int[tamanio];
            int cants[]= new int[tamanio];
            id_cliente = Integer.parseInt(etiquetaRecibeId.getText());
            
            id_factura = Integer.parseInt(txtIdFactura.getText());//obtiene el id de la factura para alamcenamiento
            ArrayList<Object>datosTabla = new ArrayList<>();// obtiene los datos de la tabla
            
            for (int i = 0; i < tablaDetalle.getRowCount(); i++) {
                for (int j = 0; j < tablaDetalle.getColumnCount(); j++)
                {
                    if(j!=3){
                        datosTabla.add(tablaDetalle.getValueAt(i, j).toString());
                    }
                    if(j==3){
                        cant = String.valueOf(tablaDetalle.getValueAt(i, 3));
                        cants[i] = Integer.parseInt(cant);
                    }
                }}
            
            for(int i = 0;i<tablaDetalle.getRowCount();i++){//usado para calcular total
                idProds[i]=(int) tablaDetalle.getValueAt(i, 0);
               
                cant=String.valueOf(tablaDetalle.getValueAt(i, 3));
                cantidad = Integer.parseInt(cant);
                prc = String.valueOf(tablaDetalle.getValueAt(i, 4));
                precio  = Double.parseDouble(prc);
                total   += cantidad*precio;
            }

            Date date = fechaDetalle.getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String fecha=formatter.format(date); // obtiene fecha

            operaciones.almacenarModificacion(id_factura,id_cliente,idProds,cants,datosTabla,tablaDetalle.getRowCount(),txtModNombre.getText().toUpperCase(),fecha);
            /////cambiar
            modeloTablaDetalle.setRowCount(0);
            txtModNombre.setText("");
            operaciones.almacenarTotal(total,id_factura,fecha);
            JOptionPane.showMessageDialog(null,"ACTUALIZACIÓN EXITOSA ! ! !","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El campo de precio debe ser numérico ! ! ! ".toUpperCase(),"ERROR",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardaModActionPerformed

    private void tablaDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDetalleKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            modeloTablaDetalle.addRow(new Object[]{null,null,null});

        }
    }//GEN-LAST:event_tablaDetalleKeyTyped

    private void tablaOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaOrdenesMouseClicked
   // TODO add your handling code here:
   if(evt.getClickCount()==2){ 
    int filaSeleccionada = tablaOrdenes.getSelectedRow();
            String id_factura = String.valueOf(tablaOrdenes.getValueAt(filaSeleccionada,0));
            operaciones.muestraDetalleOrdenes((DefaultTableModel)tablaDetalle.getModel(),Integer.parseInt(id_factura),fechaDetalle);
            tab.setSelectedIndex(1);
            btnGuardaMod.setEnabled(true);   
            txtIdFactura.setText(id_factura); 
           
   }
    }//GEN-LAST:event_tablaOrdenesMouseClicked

    private void btnGuardarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEstadoActionPerformed
        // TODO add your handling code here:
        int fila = tablaOrdenes.getSelectedRow();
        String estado = (String) tablaOrdenes.getValueAt(fila, 3);
        int id_f = (int) tablaOrdenes.getValueAt(fila, 0);
        operaciones.actualizarEstado(estado,id_f);
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null,"ACTUALIZACIÓN EXITOSA ! ! !","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);  
    }//GEN-LAST:event_btnGuardarEstadoActionPerformed
    public final void llenarCantidad (){
     String numero = null;
         for(int i = 1 ; i<=30;i++){
             numero = String.valueOf(i);
            cantidad.addItem(numero);
         }
 }
    public final void llenarNumPag(){
     String numero = null;
         for(int i = 1 ; i<=200;i++){
             numero = String.valueOf(i);
            numPag.addItem(numero);
         }
 }
    public final void llenarNumCamp(){
     String numero = null;
         for(int i = 1 ; i<=18;i++){
             numero = String.valueOf(i);
            numCamp.addItem(numero);
         }
 }
    public final void llenarTipoRev(){
        catalogo.addItem("ESIKA");
        catalogo.addItem("CYZONE");
        catalogo.addItem("L'BEL");
        catalogo.addItem("OFERTA");
 }
    public final void llenarEstado(){
    estado.addItem("DEBE");
    estado.addItem("CANCELADO");
    }
    class  JDateChooserEditor extends DefaultCellEditor // agrega jdate ala tabla
{
  public JDateChooserEditor(JCheckBox checkBox)
  {
    super(checkBox);

  }

  JDateChooser date;
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) 
  {

         date = new JDateChooser();
         date.setDateFormatString("dd-MM-yyyy");
         return date;
  }

  public Object getCellEditorValue() 
  {
    return new String(((JTextField)date.getDateEditor().getUiComponent()).getText());
  }

  public boolean stopCellEditing()
  {
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
    private DecimalFormatSymbols simbolos;
    private DecimalFormat formateador; 
    
    public DecimalFormatRenderer() {
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        formateador = new DecimalFormat("###0.00", simbolos);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // 1.- value = valor formateado utilizando el formateador */
        // 2.- llamada al método getTableCellRendererComponent de la clase Padre (recuerda utilizar 'super')
        return this;
    }
       }
    
   
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
            java.util.logging.Logger.getLogger(FrameOrdenesPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameOrdenesPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameOrdenesPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameOrdenesPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameOrdenesPorCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardaMod;
    private javax.swing.JButton btnGuardarEstado;
    public static javax.swing.JLabel etiquetaRecibeId;
    private com.toedter.calendar.JDateChooser fechaDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tab;
    public javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaOrdenes;
    private javax.swing.JLabel txtIdFactura;
    public static javax.swing.JTextField txtModNombre;
    // End of variables declaration//GEN-END:variables
}
