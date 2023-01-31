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
import java.sql.PreparedStatement;
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
import javax.swing.table.TableColumn;

/**
 *
 * @author Rick
 */
public class InterPedido extends javax.swing.JFrame {

    /**
     * Creates new form InterPedido
     */
    public void TablaLimpia(){
        visor.getColumnModel().getColumn(0).setResizable(false);
        visor.getColumnModel().getColumn(1).setResizable(false);
        visor.getColumnModel().getColumn(2).setResizable(false);
        visor.getColumnModel().getColumn(3).setResizable(false);
        visor.getColumnModel().getColumn(4).setResizable(false);
        visor.getColumnModel().getColumn(5).setResizable(false);
        visor.getColumnModel().getColumn(5).setPreferredWidth(120);
        visor.getColumnModel().getColumn(6).setPreferredWidth(70);
        visor2.getColumnModel().getColumn(0).setResizable(false);
        visor2.getColumnModel().getColumn(1).setResizable(false);
        visor2.getColumnModel().getColumn(2).setResizable(false);
        visor2.getColumnModel().getColumn(3).setResizable(false);
        visor2.getColumnModel().getColumn(4).setResizable(false);
        visor2.getColumnModel().getColumn(1).setPreferredWidth(35);
        visor2.getColumnModel().getColumn(2).setPreferredWidth(65);
        visor2.getColumnModel().getColumn(3).setPreferredWidth(60);
        visor2.getColumnModel().getColumn(4).setPreferredWidth(120);
    }
    public void transpareciaButton() {
        jIngresar.setOpaque(false);
        jIngresar.setContentAreaFilled(true);
        jIngresar.setBorderPainted(false);
        jEliminar.setOpaque(false);
        jEliminar.setContentAreaFilled(true);
        jEliminar.setBorderPainted(false);
        jModificar.setOpaque(false);
        jModificar.setContentAreaFilled(true);
        jModificar.setBorderPainted(false);
        jBuscar.setOpaque(false);
        jBuscar.setContentAreaFilled(false);
        jBuscar.setBorderPainted(false);
        jSalida.setOpaque(false);
        jSalida.setContentAreaFilled(false);
        jSalida.setBorderPainted(false);
        jActualizar.setOpaque(false);
        jActualizar.setContentAreaFilled(false);
        jActualizar.setBorderPainted(false);
    }
    public void StringsTrans(){
        jCantidadOrd.setBackground(new Color(0,0,0,1));
        jCantidadUnit.setBackground(new Color(0,0,0,1));
    }
    
    RellenarCombos re = new RellenarCombos();
    
    public InterPedido() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("TubyPedidos");
        setIconImage(getIconImage());
        re.RellenarComboBox1("Pago","TipoPago",jTipoPago);
        re.RellenarComboBox2("Producto","Nombre",jNombre);
        mostrar("Pedido");
        mostrar1("Historial");
        TablaLimpia();
        StringsTrans();
        visor.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent Mouse_evt){
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if(Mouse_evt.getClickCount() == 2){
                    jId_Pedido.setText(visor.getValueAt(visor.getSelectedRow(), 0).toString());
                    jNombre.setSelectedItem(visor.getValueAt(visor.getSelectedRow(), 1));
                    jCantidadOrd.setText(visor.getValueAt(visor.getSelectedRow(), 2).toString());
                    jCantidadUnit.setText(visor.getValueAt(visor.getSelectedRow(), 3).toString());
                    jTipoPago.setSelectedItem(visor.getValueAt(visor.getSelectedRow(), 4));
                }
            }
        });
        transpareciaTable();
        transpareciaTable2();
        transpareciaButton();
    }

    @Override
    public Image getIconImage (){
        
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imag/TubyMini.png"));
        return retValue;
    }
    public void transpareciaTable() {
        visor2.setBackground(new Color(0,0,0,0));
        ((DefaultTableCellRenderer)visor2.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        visor2.setGridColor(Color.WHITE);
        visor2.setForeground(Color.WHITE);
        jScrollPane2.setBackground(new Color(0,0,0,0));
        jScrollPane2.setOpaque(false);
        visor.setOpaque(false);
                 ((DefaultTableCellRenderer)visor2.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        jScrollPane2.getViewport().setOpaque(false);
        visor2.setShowGrid(true);
    }
    public void transpareciaTable2() {
        visor.setBackground(new Color(0,0,0,0));
        ((DefaultTableCellRenderer)visor.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        visor.setGridColor(Color.WHITE);
        visor.setForeground(Color.WHITE);
        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.setOpaque(false);
        visor.setOpaque(false);
                 ((DefaultTableCellRenderer)visor.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        jScrollPane1.getViewport().setOpaque(false);
        visor.setShowGrid(true);
    }
    public void IngresarRegristro(JComboBox Nombre, JTextField CantidadOrd,JTextField CantidadUnit,JComboBox TipoPago)
{
  try{
   ConexionBD con = new ConexionBD();
   Connection ConexionBD = con.getConnection(); 
   CallableStatement proc = ConexionBD.prepareCall("CALL SP_INSERTARPEDIDO(?,?,?,?)");      
   proc.setString(1, Nombre.getSelectedItem().toString());
   proc.setString(2, CantidadOrd.getText());
   proc.setString(3, CantidadUnit.getText());
   proc.setString(4, TipoPago.getSelectedItem().toString());
   proc.execute();
   JOptionPane.showMessageDialog(null, "Se registro correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
   mostrar("Pedido");
}catch(Exception e){

System.out.println(e);

}


}
public void ModificarRegistro(JTextField Id_Pedido, JComboBox Nombre, JTextField CantidadOrd,JTextField CantidadUnit,JComboBox TipoPago){
    try {
        ConexionBD con = new ConexionBD();
        Connection ConexionBD = con.getConnection();
        CallableStatement proc = ConexionBD.prepareCall("CALL SP_MODIFICARPEDIDO(?,?,?,?,?)");
        proc.setString(1, Id_Pedido.getText());
        proc.setString(2, Nombre.getSelectedItem().toString());
        proc.setString(3, CantidadOrd.getText());
        proc.setString(4, CantidadUnit.getText());
        proc.setString(5, TipoPago.getSelectedItem().toString());
        proc.execute();
        JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
        mostrar("Pedido");
        TablaLimpia();
    } catch (Exception e) {

        System.out.println(e);

    }
}

public boolean EliminarRegistro(int Id_Pedido)
{
   String sql = "delete from Pedido where Id_Pedido = ?";
   Connection cn;
   PreparedStatement pst;
  try{
   ConexionBD con = new ConexionBD();
   cn = con.getConnection();
   pst = cn.prepareStatement(sql);
   pst.setInt(1, Id_Pedido);
   int i = pst.executeUpdate();
       return i != 0;
  }catch(SQLException e){

   System.out.println("Error al eliminar registro "+e.getMessage());
   
   return false;
  }


}

public void Buscar1(String Fecha){
        String sql = "select Nombre, CantOrd, CantUnit, TipoPago, Fecha from Historial where Fecha like " + "'%" + Fecha + "%';";
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Stock");
        model.addColumn("StockUnit");
        model.addColumn("TipoPago");
        model.addColumn("Fecha");
        visor2.setModel(model);
        
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
public void mostrar(String tabla){
        String sql = "select Id_Pedido, Nombre, CantOrd, CantUnit, TipoPago, fechaPedido from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("COD.Pedido");
        model.addColumn("Nombre");
        model.addColumn("Stock");
        model.addColumn("StockUnit");
        model.addColumn("TipoPago");
        model.addColumn("Fecha");
        model.addColumn("Seleccionado");
        visor.setModel(model);
        
        String [] datos = new String[7];
        addCheckBox(6,visor);
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
                   datos[5]=rs.getString(6); 
                   model.addRow(datos);
               } 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
            
        }
    }
public void mostrar1(String tabla){
        String sql = "select Nombre, CantOrd, CantUnit,TipoPago, Fecha from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Stock");
        model.addColumn("StockUnit");
        model.addColumn("TipoPago");
        model.addColumn("Fecha");
        visor2.setModel(model);
        
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
public void addCheckBox(int column, JTable table){
    TableColumn tc = table.getColumnModel().getColumn(column);
    tc.setCellEditor(table.getDefaultEditor(Boolean.class));
    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    
}
public boolean IsSelected(int row, int column, JTable table){
    return table.getValueAt(row, column) != null;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new ImageFondo();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jIngresar = new javax.swing.JButton();
        jEliminar = new javax.swing.JButton();
        jModificar = new javax.swing.JButton();
        jBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCantidadOrd = new javax.swing.JTextField();
        jCantidadUnit = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        visor2 = new javax.swing.JTable();
        jCalendar = new com.toedter.calendar.JDateChooser();
        jTipoPago = new javax.swing.JComboBox<>();
        jNombre = new javax.swing.JComboBox<>();
        jId_Pedido = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSalida = new javax.swing.JButton();
        jActualizar = new javax.swing.JButton();

        jButton5.setText("jButton5");

        jTextField6.setText("jTextField6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        visor = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                if (col <= 5 ){
                    return false;
                }else{
                    return true;
                }

            }

        };
        visor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "COD.Pedio", "Nombre", "Stock", "StockUnit", "TipoPago", "Fecha", "Seleccionado"
            }
        ));
        visor.getTableHeader().setReorderingAllowed(false);
        visor.setFocusable(false);
        visor.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(visor);
        if (visor.getColumnModel().getColumnCount() > 0) {
            visor.getColumnModel().getColumn(0).setResizable(false);
            visor.getColumnModel().getColumn(1).setResizable(false);
            visor.getColumnModel().getColumn(2).setResizable(false);
            visor.getColumnModel().getColumn(3).setResizable(false);
            visor.getColumnModel().getColumn(4).setResizable(false);
            visor.getColumnModel().getColumn(5).setResizable(false);
            visor.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 840, 156));

        jIngresar.setBackground(new java.awt.Color(0, 0, 0));
        jIngresar.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jIngresar.setForeground(new java.awt.Color(255, 255, 255));
        jIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/business_ordering_pencil_table_2333.png"))); // NOI18N
        jIngresar.setText("Registrar");
        jIngresar.setFocusable(false);
        jIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(jIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 250, 160, -1));

        jEliminar.setBackground(new java.awt.Color(0, 0, 0));
        jEliminar.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/check-form_116472.png"))); // NOI18N
        jEliminar.setText("Realizado");
        jEliminar.setFocusable(false);
        jEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        jModificar.setBackground(new java.awt.Color(0, 0, 0));
        jModificar.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jModificar.setForeground(new java.awt.Color(255, 255, 255));
        jModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/business_table_order_report_history_2332.png"))); // NOI18N
        jModificar.setText("Modificar");
        jModificar.setFocusable(false);
        jModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModificarActionPerformed(evt);
            }
        });
        jPanel1.add(jModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 170, -1));

        jBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/xmag_search_find_export_locate_5984.png"))); // NOI18N
        jBuscar.setBorder(null);
        jBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 60, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Stock:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stock Unitario:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo de Pago:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jCantidadOrd.setBackground(new java.awt.Color(0, 0, 0));
        jCantidadOrd.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jCantidadOrd.setForeground(new java.awt.Color(255, 255, 255));
        jCantidadOrd.setBorder(null);
        jCantidadOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCantidadOrdActionPerformed(evt);
            }
        });
        jPanel1.add(jCantidadOrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 70, 30));

        jCantidadUnit.setBackground(new java.awt.Color(0, 0, 0));
        jCantidadUnit.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jCantidadUnit.setForeground(new java.awt.Color(255, 255, 255));
        jCantidadUnit.setBorder(null);
        jPanel1.add(jCantidadUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 70, 30));

        visor2 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        visor2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Stock", "StockUnit", "TipoPago", "Fecha"
            }
        ));
        visor.getTableHeader().setReorderingAllowed(false);
        visor2.setFocusable(false);
        visor2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(visor2);
        if (visor2.getColumnModel().getColumnCount() > 0) {
            visor2.getColumnModel().getColumn(0).setResizable(false);
            visor2.getColumnModel().getColumn(1).setResizable(false);
            visor2.getColumnModel().getColumn(2).setResizable(false);
            visor2.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 40, 410, 330));

        jCalendar.setBackground(new java.awt.Color(0, 0, 0));
        jCalendar.setForeground(new java.awt.Color(255, 255, 255));
        jCalendar.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(jCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 169, -1));

        jTipoPago.setBackground(new java.awt.Color(0, 102, 102));
        jTipoPago.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jTipoPago.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 141, -1));

        jNombre.setBackground(new java.awt.Color(0, 102, 102));
        jNombre.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jNombre.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 140, -1));

        jId_Pedido.setEditable(false);
        jId_Pedido.setBackground(new java.awt.Color(0, 0, 0));
        jId_Pedido.setForeground(new java.awt.Color(0, 0, 0));
        jId_Pedido.setBorder(null);
        jPanel1.add(jId_Pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 0, 26, -1));

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 70, 60));

        jSeparator5.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 70, 50));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PENDIENTES/PEDIDOS:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 250, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("HISTORIAL:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        jSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/restart_back_left_arrow_6022.png"))); // NOI18N
        jSalida.setBorder(null);
        jSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(jSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 70, 40));

        jActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/arrow_refresh_15732 (1).png"))); // NOI18N
        jActualizar.setBorder(null);
        jActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 50, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIngresarActionPerformed
        IngresarRegristro(jNombre, jCantidadOrd, jCantidadUnit, jTipoPago);

        jCantidadOrd.setText(null);
        jCantidadUnit.setText(null);

        TablaLimpia();
        
    }//GEN-LAST:event_jIngresarActionPerformed

    private void jCantidadOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCantidadOrdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCantidadOrdActionPerformed

    private void jEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEliminarActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < visor.getRowCount(); i++){
            if ( IsSelected(i, 6, visor)){
                EliminarRegistro(Integer.parseInt(visor.getValueAt(i, 0).toString()));
                JOptionPane.showMessageDialog(null, "Se realizo el pedido correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
                mostrar("Pedido");
                mostrar1("Historial");
                TablaLimpia();
            }
        
    }
    }//GEN-LAST:event_jEliminarActionPerformed

    private void jBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBuscarActionPerformed
        Buscar1(((JTextField)jCalendar.getDateEditor().getUiComponent()).getText());
        TablaLimpia();
    }//GEN-LAST:event_jBuscarActionPerformed

    private void jModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jModificarActionPerformed

        ModificarRegistro(jId_Pedido, jNombre, jCantidadOrd, jCantidadUnit, jTipoPago);
    }//GEN-LAST:event_jModificarActionPerformed

    private void jSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSalidaActionPerformed
        Menu a = new Menu();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSalidaActionPerformed

    private void jActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActualizarActionPerformed
        mostrar1("Historial");
    }//GEN-LAST:event_jActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(InterPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jActualizar;
    private javax.swing.JButton jBuscar;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jCalendar;
    private javax.swing.JTextField jCantidadOrd;
    private javax.swing.JTextField jCantidadUnit;
    private javax.swing.JButton jEliminar;
    private javax.swing.JTextField jId_Pedido;
    private javax.swing.JButton jIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jModificar;
    private javax.swing.JComboBox<String> jNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jSalida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JComboBox<String> jTipoPago;
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
