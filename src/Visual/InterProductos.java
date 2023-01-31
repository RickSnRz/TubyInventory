/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Class.ConexionBD;
import Class.RellenarCombos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rick
 */
public class InterProductos extends javax.swing.JFrame {

    /**
     * Creates new form InterProductos
     */
public void TablaLimpia(){
        visor2.getColumnModel().getColumn(0).setPreferredWidth(200);
        visor2.getColumnModel().getColumn(0).setResizable(false);
        visor2.getColumnModel().getColumn(1).setResizable(false);
        
        visor.getColumnModel().getColumn(0).setResizable(false);
        visor.getColumnModel().getColumn(1).setResizable(false);
        visor.getColumnModel().getColumn(2).setResizable(false);
        visor.getColumnModel().getColumn(3).setResizable(false);
        visor.getColumnModel().getColumn(4).setResizable(false);
}    
public void StringsLimpio(){
        jId_Producto.setText(null);
        jNombre.setText(null);
        jCantidad.setText(null);
        jPrecio.setText(null);
}    
public void otros(){
        jNombre.setBackground(new Color(0,0,0,1));
        jPrecio.setBackground(new Color(0,0,0,1));
        jCantidad.setBackground(new Color(0,0,0,1));
        jId_Producto.setBackground(new Color(0,0,0,1));
        
        jSeparator1.setBackground(new Color(0,0,0,0));
        jSeparator1.setOpaque(false);
}    
    
    
    
    ImageFondo fondo = new ImageFondo();
    RellenarCombos re = new RellenarCombos(); 
    
    public InterProductos() {
        this.setContentPane(fondo);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("TubyProductos");
        setIconImage(getIconImage());
        re.RellenarComboBox("Categoria1","Categoria",jCategorias);
        mostrar("Producto");
        mostrar1("accion");
        TablaLimpia();
        otros();

        
        
        visor.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent Mouse_evt){
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if(Mouse_evt.getClickCount() == 2){
                    jId_Producto.setText(visor.getValueAt(visor.getSelectedRow(), 0).toString());
                    jNombre.setText(visor.getValueAt(visor.getSelectedRow(), 1).toString());
                    jCategorias.setSelectedItem(visor.getValueAt(visor.getSelectedRow(), 2));
                    jPrecio.setText(visor.getValueAt(visor.getSelectedRow(), 3).toString());
                    jCantidad.setText(visor.getValueAt(visor.getSelectedRow(), 4).toString());
                }
            }
        });
        transpareciaTable2();
        transpareciaTable();
        transpareciaButton();
    }
    
    
    @Override
    public Image getIconImage (){
        
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imag/TubyMini.png"));
        return retValue;
    }
    
    public void IngresarRegristro(JTextField Nombre, JComboBox Categorias,JTextField Precio,JTextField Cantidad)
{
  try{
   ConexionBD con = new ConexionBD();
   Connection ConexionBD = con.getConnection(); 
   CallableStatement proc = ConexionBD.prepareCall("CALL SP_INSERTARPRODUCTO(?,?,?,?)");      
   proc.setString(1, Nombre.getText());
   proc.setString(2, Categorias.getSelectedItem().toString());
   proc.setString(3, Precio.getText());
   proc.setString(4, Cantidad.getText());
   proc.execute();
   JOptionPane.showMessageDialog(null, "Se registro correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
   TablaLimpia();
   mostrar("Producto");
   mostrar1("accion");
   StringsLimpio();
}catch(Exception e){

System.out.println(e);

}


}
    
public void EliminarRegistro(JTextField Id_Producto)
{
  try{
   ConexionBD con = new ConexionBD();
   Connection ConexionBD = con.getConnection(); 
   CallableStatement proc = ConexionBD.prepareCall("CALL SP_ELEMINARPRODUCTO(?)");      
   proc.setString(1, Id_Producto.getText());
   proc.execute();
   JOptionPane.showMessageDialog(null, "Se elimino correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
   TablaLimpia();
   mostrar("Producto");
   mostrar1("accion");
   StringsLimpio();
}catch(Exception e){

System.out.println(e);

}


}    
    
public void ActualizarRegistro(JTextField Id_Producto,JTextField Nombre, JComboBox Categorias,JTextField Precio,JTextField Cantidad)
{
  try{
   ConexionBD con = new ConexionBD();
   Connection ConexionBD = con.getConnection(); 
   CallableStatement proc = ConexionBD.prepareCall("CALL SP_MODIFICARPRODUCTO(?,?,?,?,?)");      
   proc.setString(1, Id_Producto.getText());
   proc.setString(2, Nombre.getText());
   proc.setString(3, Categorias.getSelectedItem().toString());
   proc.setString(4, Precio.getText());
   proc.setString(5, Cantidad.getText());
   proc.execute();
   JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
   TablaLimpia();
   mostrar("Producto");
   mostrar1("accion");
   StringsLimpio();
}catch(Exception e){

System.out.println(e);

}


}    
    
public void mostrar(String tabla){
        String sql = "select Id_Producto, Nombre, Categoria, Precio, Cantidad from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CODProduc");
        model.addColumn("Nombre");
        model.addColumn("Categoria");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        visor.setModel(model);
        
        String [] datos = new String[5];
        try{
            st = DBConexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
               {
                   datos[0]=rs.getString(1);
                   datos[1]=rs.getString(2);
                   datos[2]=rs.getString(3);
                   datos[3]=rs.getString(4);
                   datos[4]=rs.getString(5);
                   model.addRow(datos);
               } 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
            
        }
    }
public void mostrar1(String tabla){
        String sql = "select accion, fecha from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Registro");
        model.addColumn("Fecha");       
        visor2.setModel(model);
        
        String [] datos = new String[2];
        try{
            st = DBConexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
               {
                   datos[0]=rs.getString(1);
                   datos[1]=rs.getString(2);
                   model.addRow(datos);
               } 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
            
        }
    }
 public void transpareciaTable() {
        visor2.setBackground(new Color(0,0,0,0));
        ((DefaultTableCellRenderer)visor2.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        visor2.setGridColor(Color.WHITE);
        visor2.setForeground(Color.WHITE);
        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.setOpaque(false);
        visor.setOpaque(false);
                 ((DefaultTableCellRenderer)visor2.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        jScrollPane1.getViewport().setOpaque(false);
        visor2.setShowGrid(true);
    }
public void transpareciaTable2() {
        visor.setBackground(new Color(0,0,0,0));
        ((DefaultTableCellRenderer)visor.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        visor.setGridColor(Color.WHITE);
        visor.setForeground(Color.WHITE);
        jScrollPane2.setBackground(new Color(0,0,0,0));
        jScrollPane2.setOpaque(false);
        visor.setOpaque(false);
                 ((DefaultTableCellRenderer)visor.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        jScrollPane2.getViewport().setOpaque(false);
        visor.setShowGrid(true);
    }
    public void transpareciaButton() {
        Regristar.setOpaque(false);
        Regristar.setContentAreaFilled(true);
        Regristar.setBorderPainted(false);
        Eliminar.setOpaque(false);
        Eliminar.setContentAreaFilled(true);
        Eliminar.setBorderPainted(false);
        Modificar.setOpaque(false);
        Modificar.setContentAreaFilled(true);
        Modificar.setBorderPainted(false);
        Confirmado.setOpaque(false);
        Confirmado.setContentAreaFilled(true);
        Confirmado.setBorderPainted(false);
        Eliminado.setOpaque(false);
        Eliminado.setContentAreaFilled(true);
        Eliminado.setBorderPainted(false);
        jSalida.setOpaque(false);
        jSalida.setContentAreaFilled(false);
        jSalida.setBorderPainted(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jId_Producto = new javax.swing.JTextField();
        jNombre = new javax.swing.JTextField();
        jPrecio = new javax.swing.JTextField();
        jCantidad = new javax.swing.JTextField();
        Regristar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jCategorias = new javax.swing.JComboBox<>();
        Confirmado = new javax.swing.JButton();
        Eliminado = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSalida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Categoria:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cantidad:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jId_Producto.setEditable(false);
        jId_Producto.setBackground(new java.awt.Color(0, 0, 0));
        jId_Producto.setForeground(new java.awt.Color(0, 0, 0));
        jId_Producto.setBorder(null);
        jId_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jId_ProductoActionPerformed(evt);
            }
        });
        getContentPane().add(jId_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 30, -1));

        jNombre.setBackground(new java.awt.Color(0, 0, 0));
        jNombre.setForeground(new java.awt.Color(255, 255, 255));
        jNombre.setBorder(null);
        getContentPane().add(jNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 160, 20));

        jPrecio.setBackground(new java.awt.Color(0, 0, 0));
        jPrecio.setForeground(new java.awt.Color(255, 255, 255));
        jPrecio.setBorder(null);
        getContentPane().add(jPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 90, 20));

        jCantidad.setBackground(new java.awt.Color(0, 0, 0));
        jCantidad.setForeground(new java.awt.Color(255, 255, 255));
        jCantidad.setBorder(null);
        getContentPane().add(jCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 78, 20));
        jCantidad.getAccessibleContext().setAccessibleName("");

        Regristar.setBackground(new java.awt.Color(0, 0, 0));
        Regristar.setForeground(new java.awt.Color(255, 255, 255));
        Regristar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/businesspackage_additionalpackage_box_add_insert_negoci_2335.png"))); // NOI18N
        Regristar.setText("Registrar");
        Regristar.setFocusable(false);
        Regristar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegristarActionPerformed(evt);
            }
        });
        getContentPane().add(Regristar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 234, 140, 70));

        Eliminar.setBackground(new java.awt.Color(0, 0, 0));
        Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/businesspackagewarningbox_negocio_paquete_advertencia_2337.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setFocusable(false);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        getContentPane().add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 294, 140, 70));

        Modificar.setBackground(new java.awt.Color(0, 0, 0));
        Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/businesssettings_thebox_theproduct_negocio_2327.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.setFocusable(false);
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        getContentPane().add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 354, 140, 70));

        visor2 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        visor2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Registro", "Fecha"
            }
        ));
        visor2.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(visor2);
        if (visor2.getColumnModel().getColumnCount() > 0) {
            visor2.getColumnModel().getColumn(0).setResizable(false);
            visor2.getColumnModel().getColumn(0).setPreferredWidth(300);
            visor2.getColumnModel().getColumn(1).setResizable(false);
            visor2.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 450, 120));

        visor = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        visor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CODPorudc", "Nombre", "Categoria", "Precio", "Cantidad"
            }
        ));
        visor.setFocusable(false);
        visor.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(visor);
        if (visor.getColumnModel().getColumnCount() > 0) {
            visor.getColumnModel().getColumn(0).setResizable(false);
            visor.getColumnModel().getColumn(1).setResizable(false);
            visor.getColumnModel().getColumn(2).setResizable(false);
            visor.getColumnModel().getColumn(3).setResizable(false);
            visor.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 6, 450, 350));

        jCategorias.setBackground(new java.awt.Color(0, 102, 102));
        jCategorias.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jCategorias.setForeground(new java.awt.Color(255, 255, 255));
        jCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCategoriasActionPerformed(evt);
            }
        });
        getContentPane().add(jCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 154, -1));

        Confirmado.setBackground(new java.awt.Color(0, 0, 0));
        Confirmado.setForeground(new java.awt.Color(255, 255, 255));
        Confirmado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/gui_check_yes_icon_157194.png"))); // NOI18N
        Confirmado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmadoActionPerformed(evt);
            }
        });
        getContentPane().add(Confirmado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 40, -1));

        Eliminado.setBackground(new java.awt.Color(0, 0, 0));
        Eliminado.setForeground(new java.awt.Color(255, 255, 255));
        Eliminado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/gui_check_no_icon_157196.png"))); // NOI18N
        Eliminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminadoActionPerformed(evt);
            }
        });
        getContentPane().add(Eliminado, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 40, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 790, 10));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("REPORTE:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 120, 30));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 90, 20));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 80, 20));

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 160, 50));

        jSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/restart_back_left_arrow_6022.png"))); // NOI18N
        jSalida.setBorder(null);
        jSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(jSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 60, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jId_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jId_ProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jId_ProductoActionPerformed

    private void RegristarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegristarActionPerformed
        // TODO add your handling code here:
        IngresarRegristro(jNombre, jCategorias, jPrecio, jCantidad);
    }//GEN-LAST:event_RegristarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        EliminarRegistro(jId_Producto);
    }//GEN-LAST:event_EliminarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        ActualizarRegistro(jId_Producto, jNombre, jCategorias, jPrecio, jCantidad);
    }//GEN-LAST:event_ModificarActionPerformed

    private void ConfirmadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmadoActionPerformed
        // TODO add your handling code here:
        mostrar1("accion");
        TablaLimpia();
        transpareciaTable();
    }//GEN-LAST:event_ConfirmadoActionPerformed

    private void EliminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminadoActionPerformed
        // TODO add your handling code here:
        mostrar1("accion1");
        TablaLimpia();
        transpareciaTable();
    }//GEN-LAST:event_EliminadoActionPerformed

    private void jCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCategoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCategoriasActionPerformed

    private void jSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSalidaActionPerformed
        Menu a = new Menu();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSalidaActionPerformed

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
            java.util.logging.Logger.getLogger(InterProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirmado;
    private javax.swing.JButton Eliminado;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Regristar;
    private javax.swing.JTextField jCantidad;
    private javax.swing.JComboBox<String> jCategorias;
    private javax.swing.JTextField jId_Producto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jNombre;
    private javax.swing.JTextField jPrecio;
    private javax.swing.JButton jSalida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable visor;
    private javax.swing.JTable visor2;
    // End of variables declaration//GEN-END:variables

class ImageFondo extends JPanel{
        
        private Image imagen;
        
        
        public void paint (Graphics g){
            imagen = new ImageIcon(getClass().getResource("/Imag/t.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
        
        
    }








}
