package Datos;
import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Operaciones extends Conexion{
    public void insertarDetalle(String nombre,String total,String fecha,ArrayList arrayList, int filas){
      int id_factura;
      
      String bidimensional[][] = new String[filas][8];
        try{ 
            int j=0;
            for (int i = 0; i < filas; i++) { //
            for (int k = 0; k < 7; k++) {
               bidimensional[i][k] = (String) arrayList.get(j);
                 j++;  
            } 
        }
            
            int id_x = insercionIdCliente(nombre); //inserta nombre cliente 
          
            insercionFactura(id_x,total,fecha);//inserta factura, buscando donde esta el correcto id de acuerdo al nombre 
            
            id_factura=consultaIdFactura(id_x);//verficar si es necesario
            
           String insercionEnItem = "INSERT INTO ITEM (ID_FACTURA,CANTIDAD) VALUES(?,?)";
           conexion();
           PreparedStatement statement = conexion.prepareStatement(insercionEnItem);
            for (int i = 0; i < filas; i++) {
                statement.setInt(1, id_factura);
                statement.setInt(2, Integer.parseInt(bidimensional[i][2]));
                statement.executeUpdate(); 
            }
            statement.close();
            conexion.close();
            
            String insercionEnProducto ="INSERT INTO PRODUCTO(CODIGO,DESCRIPCION,PRECIO,CATALOGO,NUMERO_PAGINA,NUMERO_CAMPANIA)"
                    + "VALUES (?,?,?,?,?,?)"; //falta fecha
            conexion(); 
            PreparedStatement st = conexion.prepareStatement(insercionEnProducto);
            
            for (int i = 0; i < filas; i++) { 
            st.setString(1, bidimensional[i][0]);
            st.setString(2, bidimensional[i][1]);//descripcion
            st.setDouble(3, Double.parseDouble(bidimensional[i][3]));//precio
            st.setString(4, bidimensional[i][4]);//catalogo
            st.setInt   (5, Integer.parseInt(bidimensional[i][5]));//num pag
            st.setInt   (6, Integer.parseInt(bidimensional[i][6])); //num camp
            st.executeUpdate();     
        }  
            conexion.close();
        }   catch(SQLException | NumberFormatException exception){
            System.err.println(exception.getMessage()); 
            exception.printStackTrace();
        }
    }
    public void insercionFactura (int id,String total,String fecha){
        try {
        String insercionFactura ="INSERT INTO FACTURA (ID_CLIENTE,FECHA,TOTAL) VALUES (?,?,?)";
        conexion();
        PreparedStatement  st = conexion.prepareStatement(insercionFactura);
        st.setInt(1, id);
        st.setString(2,fecha);
        st.setDouble(3, Double.parseDouble(total));
        st.executeUpdate();
        st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public int  insercionIdCliente(String nombre){
        int resultado = 0; //ALMACENA ID SI EXISTE CLIENTE
        try {
        String consultaExistencia = "SELECT EXISTS (SELECT * FROM CLIENTES WHERE nombre_cliente ="+"'"+nombre+"'"+")";
        conexion();
        PreparedStatement consulta = conexion.prepareStatement(consultaExistencia);
        ResultSet resultSet = null;
        resultSet = consulta.executeQuery();
        
        while(resultSet.next()){
           resultado = resultSet.getInt(1);
          
        }
        consulta.close();
        if(resultado ==0){
        String insercionCliente = "INSERT INTO CLIENTES(NOMBRE_CLIENTE) VALUES (?)"; // inserta nombre de cliente en clientes
        conexion();
        PreparedStatement st = conexion.prepareStatement(insercionCliente);
        st.setString(1, nombre);
        st.executeUpdate();
        st.close();
            }
           
            String seleccionId = "SELECT ID_CLIENTE FROM CLIENTES WHERE nombre_cliente LIKE"+"'"+nombre+"'";
            conexion();
            PreparedStatement consultaId =conexion.prepareStatement(seleccionId);
            resultSet = consultaId.executeQuery();
            while(resultSet.next()){
            resultado = resultSet.getInt(1);
        }
         consultaId.close();
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resultado;
    }
    public int  consultaIdFactura(int id_c){
        int id =0;
        try {
        String consulta = "SELECT ID_FACTURA FROM FACTURA WHERE ID_CLIENTE="+"'"+id_c+"'";
        conexion();
        PreparedStatement st = conexion.prepareStatement(consulta);
        ResultSet resultSet = null;
        resultSet = st.executeQuery();
        
        while(resultSet.next()){
           id = resultSet.getInt(1);
          
        }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return  id;
    }
    public void consultaNombres (DefaultTableModel defaultTableModel){
       ArrayList <Object> nombres = new ArrayList<>();
        try{
        String consulta = "SELECT * FROM CLIENTES";
        conexion();
        
        ResultSet result = null;
    
       PreparedStatement st = conexion.prepareStatement(consulta);
       result = st.executeQuery();
       if(result!= null){
        int numeroClumna = result.getMetaData().getColumnCount(); //obtiene el numero de columnas 
        defaultTableModel.setRowCount(0);
        
        while (result.next()) {            
           Object [] objetos = new Object[numeroClumna];
           for(int i = 1; i<=numeroClumna;i++){
               objetos[i-1] = result.getObject(i);
           }
            defaultTableModel.addRow(objetos);    
        } 
    }    
         st.close();
        // conexion.close();
        }
    catch(SQLException e){
     
    } 
       
    }
    public void busquedaCliente(DefaultTableModel tableModel ,String texto){
         try{
        String filtro = "%"+texto+"%";
        String SQL = "SELECT * FROM CLIENTES WHERE NOMBRE_CLIENTE LIKE"+'"'+filtro+'"';
              
    conexion();
    ResultSet result = null;
    
    PreparedStatement select = conexion.prepareStatement(SQL);
    result = select.executeQuery();
    if(result!= null){
        int numeroClumna = result.getMetaData().getColumnCount(); //obtiene el numero de columnas 
        tableModel.setRowCount(0);
        
        while (result.next()) {            
           Object [] objetos = new Object[numeroClumna];
           for(int i = 1; i<=numeroClumna;i++){
               objetos[i-1] = result.getObject(i);
           }
            tableModel.addRow(objetos);    
        }  }     select.close(); }
    catch(SQLException e){
     
    }
    
}
    public void consultaFacturas(DefaultTableModel tableModel, String texto){
        try{
        String filtro = ""+texto+"";
    String SQL = "SELECT DISTINCT FACTURA.ID_FACTURA,FACTURA.FECHA,FACTURA.TOTAL,FACTURA.ESTADO FROM FACTURA WHERE FACTURA.ID_CLIENTE="+'"'+filtro+'"';
          
    conexion();
    ResultSet result = null;
    PreparedStatement select = conexion.prepareStatement(SQL);
    result = select.executeQuery();
    
    if(result!= null){
        while (result.next()) {            
           Object [] objetos = new Object[4];
           for(int i = 1; i<=4;i++){
               objetos[i-1] = result.getObject(i);
           }
          
            tableModel.addRow(objetos);    
        }}
    select.close();
        }
    catch(SQLException e){
        e.printStackTrace();
    }}
    public void muestraDetalleOrdenes(DefaultTableModel defaultTableModel,int id,JDateChooser jDateChooser){
        try{
    String fecha = null;
    String consultaFecha ="SELECT FECHA FROM FACTURA WHERE ID_FACTURA ="+"'"+id+"'";
    conexion();
    PreparedStatement ps = conexion.prepareStatement(consultaFecha);
    ResultSet resultSet = null;
    
    resultSet = ps.executeQuery();
     while(resultSet.next()){
           fecha = resultSet.getString(1); 
          
        }
     ps.close();
     
     java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
      System.out.println("fecha "+fecha);
     jDateChooser.setDate(date);
          
    String CONSULTA = "SELECT ITEM.ID_PRODUCTO,PRODUCTO.CODIGO,PRODUCTO.DESCRIPCION,ITEM.CANTIDAD,PRODUCTO.PRECIO,PRODUCTO.CATALOGO,PRODUCTO.NUMERO_PAGINA,PRODUCTO.NUMERO_CAMPANIA,FACTURA.ESTADO"+
                           " FROM PRODUCTO" + // se deja espacio en frente para no pegar las palabras
                           " INNER JOIN FACTURA ON" +
                           " FACTURA.ID_FACTURA = ITEM.ID_FACTURA" +
                           " INNER JOIN ITEM ON " +
                           " ITEM.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO" +
                           " WHERE FACTURA.ID_FACTURA ="+"'"+id+"'";
    
    ResultSet result = null;
    PreparedStatement select = conexion.prepareStatement(CONSULTA);
    result = select.executeQuery();
    
    if(result!= null){
        int numeroClumna = result.getMetaData().getColumnCount(); //obtiene el numero de columnas 
        defaultTableModel.setRowCount(0);
        
        while (result.next()) {            
           Object [] objetos = new Object[numeroClumna];
           
           for(int i = 1; i<=numeroClumna;i++){
               objetos[i-1] = result.getObject(i);
           }
           
            defaultTableModel.addRow(objetos);    
        }  
}      select.close();
}
    catch(SQLException e){
     e.getMessage();
    }   catch (ParseException ex) {     
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    public int  verificarExistenciaCliente(String nombre){
        int resultado = 0;
        try {
        String consultaExistencia = "SELECT EXISTS (SELECT * FROM CLIENTES WHERE nombre_cliente LIKE"+"'"+nombre+"'"+")";
        conexion();
        PreparedStatement consulta = conexion.prepareStatement(consultaExistencia);
        ResultSet resultSet = null;
        resultSet = consulta.executeQuery();
        
        while(resultSet.next()){
           resultado = resultSet.getInt(1);
          
        }
        } catch (Exception e) {
        }
        return resultado;
    }
    public void almacenarModificacion(int id_factura,int id_cliente,int idProds[],int cantProds[],ArrayList arrayList,int filas,String nombre,String fecha){
       try {
        String [][] DATA = new String[filas][8]; 
        int j=0;
            for (int i = 0; i < filas; i++) { //
            for (int k = 0; k < 7; k++) {
               DATA[i][k] = (String) arrayList.get(j);
                 j++;     
            }      
        }
            
          for (int i = 0; i < filas; i++) {  
               for (int q = 0; q < 8; q++) {  
                   System.out.print(DATA[i][q]+" ");
               }
               System.out.println("");
          }   
      
           for (int i = 0; i < filas; i++) {  
            conexion();
            String modificacion ="UPDATE PRODUCTO SET CODIGO = ?,DESCRIPCION = ?,PRECIO = ?,CATALOGO = ?,NUMERO_PAGINA = ?,NUMERO_CAMPANIA = ?"
                + " WHERE ID_PRODUCTO="+"'"+idProds[i]+"'";
            PreparedStatement  st = conexion.prepareStatement(modificacion);
            st.setString(1, DATA[i][1]);//CODIGO
            st.setString(2, DATA[i][2]);// DESCRIPCION
            st.setDouble(3, Double.parseDouble(DATA[i][3]));//precio
            st.setString(4, DATA[i][4]);//catalogo
            st.setInt   (5, Integer.parseInt(DATA[i][5]));//num pag
            st.setInt   (6, Integer.parseInt(DATA[i][6])); //num camp
            st.executeUpdate();    
            st.close();
        }  
           
        String cambioNombre = "UPDATE CLIENTES SET NOMBRE_CLIENTE ="+"'"+nombre+"'"+"WHERE ID_CLIENTE ="+"'"+id_cliente+"'";
        conexion();
        PreparedStatement statement = conexion.prepareStatement(cambioNombre);
        statement.executeUpdate();
        statement.close();
        
           insercionIdCliente(nombre);///crear metodo similar
        for (int i = 0; i < filas; i++) {  
        conexion();
        String actualizacionCantidades =" UPDATE ITEM SET CANTIDAD = ? WHERE ID_PRODUCTO ="+"'"+idProds[i]+"'";
        PreparedStatement actualizacionCant = conexion.prepareStatement(actualizacionCantidades);
        actualizacionCant.setInt(1, cantProds[i]);
        actualizacionCant.executeUpdate();
         }
        } 
       catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void almacenarTotal(double tot,int id,String fecha){
    String modificacion = "UPDATE FACTURA SET FECHA = ? , TOTAL = ? WHERE ID_FACTURA ="+"'"+id+"'";
        try {
    conexion();
    PreparedStatement statement = conexion.prepareStatement(modificacion);
    statement.setString(1, fecha);
    statement.setDouble(2, tot);
    statement.executeUpdate();
    statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    
    }
    public void actualizarEstado (String estado,int id){
      String modificacion = "UPDATE FACTURA SET ESTADO = ? WHERE ID_FACTURA ="+"'"+id+"'";
        try {
    conexion();
    PreparedStatement statement = conexion.prepareStatement(modificacion);
    statement.setString(1, estado); 
    statement.executeUpdate();
    statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eliminarFactura(String id){
        try { 
        String eliminacion = "DELETE FROM FACTURA WHERE ID_FACTURA = "+"'"+id+"'"; 
        String eliminacion2 ="DELETE FROM ITEM WHERE ID_FACTURA = "+"'"+id+"'"; 
        conexion();
        PreparedStatement st = conexion.prepareStatement(eliminacion);
        st.executeUpdate();
        st.close();
        
        PreparedStatement st2 = conexion.prepareStatement(eliminacion2);
        st2.executeUpdate();
        st2.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void eliminarCliente(String id){
        try { 
      ArrayList <Integer> idsFact = new ArrayList<>();      
      ArrayList <Integer> idProds = new ArrayList<>();
      
      int cantidadP =0;
      conexion();
      ResultSet result = null;
      
      String idsFactura = "SELECT ID_FACTURA FROM FACTURA WHERE ID_CLIENTE = "+"'"+id+"'"; //selecciona los ids de factura de acuerdo a cliente
      PreparedStatement consultaIDSFactura = conexion.prepareStatement(idsFactura);
      result = consultaIDSFactura.executeQuery();
      
         while (result.next()) {
        idsFact.add(result.getInt(1));
        }
        consultaIDSFactura.close();
        
       int q = 0;
       
      for(int i =0;i<idsFact.size();i++){
          try {     
      String cantidadProductos = "SELECT COUNT (ID_PRODUCTO) FROM ITEM WHERE ID_FACTURA="+"'"+ idsFact.get(i)+"'"; 
      PreparedStatement consultaCantidadProductos = conexion.prepareStatement(cantidadProductos);
      result = consultaCantidadProductos.executeQuery();
      cantidadP = cantidadP+result.getInt(1);
      
       
        String idsProd = "SELECT ID_PRODUCTO FROM ITEM WHERE ID_FACTURA ="+"'"+idsFact.get(q)+"'";
        PreparedStatement consultaIdsProd = conexion.prepareStatement(idsProd);
        result = consultaIdsProd.executeQuery();
         while (result.next()) {
       idProds.add(result.getInt(1));
        }
        consultaIdsProd.close();
        q++;
              
      
      consultaCantidadProductos.close();
      
          } catch (IndexOutOfBoundsException e) {
             
          }
      
      }
            for (int i = 0; i < idProds.size(); i++) {
                System.out.println("prods "+idProds.get(i));
            }
       
         for (int j = 0; j < idProds.size(); j++) {
         String eliminaProd = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO ="+"'"+idProds.get(j)+"'";
         PreparedStatement eliminacionProducto = conexion.prepareStatement(eliminaProd);
         eliminacionProducto.executeUpdate();  
         eliminacionProducto.close();
            }
         
         for (int j = 0; j < idsFact.size(); j++) {
         String DELETE = "DELETE FROM ITEM WHERE ID_FACTURA ="+"'"+idsFact.get(j)+"'";
         PreparedStatement st3 = conexion.prepareStatement(DELETE);
         st3.executeUpdate();   
         st3.close();
            }
       
        
        String eliminacion = "DELETE FROM CLIENTES WHERE ID_CLIENTE = "+"'"+id+"'"; 
        String eliminacion2 ="DELETE FROM FACTURA WHERE ID_CLIENTE = "+"'"+id+"'";  
        PreparedStatement st = conexion.prepareStatement(eliminacion);
        st.executeUpdate();
        st.close();
        
        PreparedStatement st2 = conexion.prepareStatement(eliminacion2);
        st2.executeUpdate();
        st2.close();
        
        result.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void updateDeNombre (String nombre,int id_factura,int id_cliente){
    int resultado = 0;
    int id_temporal = 0;
    String consulta ="SELECT EXISTS (SELECT * FROM CLIENTES WHERE nombre_cliente = "+"'"+nombre+"'"+")";  
        try {
    conexion();
    PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
    ResultSet resultSet = null;
    resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
           resultado = resultSet.getInt(1); 
        }
        preparedStatement.close();   
        
        if (resultado == 0) {// si el cliente NO existe 
        String modificacion = "UPDATE CLIENTES SET NOMBRE_CLIENTE ="+"'"+nombre+"'"+"WHERE ID_CLIENTE ="+"'"+id_cliente+"'";
        PreparedStatement ST = conexion.prepareStatement(modificacion);
        ST.executeUpdate();
        ST.close();
        }
        
        if (resultado == 1){
    // si el cliente existe        
    String consulta2 = "SELECT ID_CLIENTE FROM CLIENTES WHERE NOMBRE_CLIENTE ="+"'"+nombre+"'";
    conexion();
    PreparedStatement CONS2 = conexion.prepareStatement(consulta2);
    resultSet = null;
    resultSet = CONS2.executeQuery();
        
        while(resultSet.next()){
           id_temporal = resultSet.getInt(1);
          
        } 
        CONS2.close();  
        
        String eliminaCliente = "DELETE FROM CLIENTES WHERE ID_CLIENTE="+"'"+id_cliente+"'";
        String updateFactura  = "UPDATE FACTURA SET ID_CLIENTE ="+"'"+id_temporal+"'"+"WHERE ID_CLIENTE ="+"'"+id_cliente+"'";
        
        conexion();
        PreparedStatement CONS3 = conexion.prepareStatement(eliminaCliente);
        CONS3.executeUpdate();
        CONS3.close();
        
        conexion();
        PreparedStatement CONS4 = conexion.prepareStatement(updateFactura);
        CONS4.executeUpdate();
        CONS4.close();
        resultSet.close();
        
        }
        resultSet.close();
        }
    catch (Exception e) {
        e.printStackTrace();
        }
    }
}
