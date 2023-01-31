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
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rick
 */
public class InterGestor extends javax.swing.JFrame {

    /**
     * Creates new form InterGestor
     */
    
    public void MinMax1(){
        SpinnerNumberModel nm = new SpinnerNumberModel();
        nm.setMaximum(20);
        nm.setMinimum(0);
        nm.setStepSize(1);
        jSpinner1.setModel(nm);
        
    }
    public void MinMax2(){
        SpinnerNumberModel nm = new SpinnerNumberModel();
        nm.setMaximum(20);
        nm.setMinimum(0);
        nm.setStepSize(1);
        jSpinner2.setModel(nm);
    }
    public void transpareciaButton() {
        jAumentar.setOpaque(false);
        jAumentar.setContentAreaFilled(false);
        jAumentar.setBorderPainted(false);
        jDisminuir.setOpaque(false);
        jDisminuir.setContentAreaFilled(false);
        jDisminuir.setBorderPainted(false);
        jSalida.setOpaque(false);
        jSalida.setContentAreaFilled(false);
        jSalida.setBorderPainted(false);
    }
public void TablaLimpia(){
        visor.getColumnModel().getColumn(0).setResizable(false);
        visor.getColumnModel().getColumn(1).setResizable(false);
        visor.getColumnModel().getColumn(2).setResizable(false);
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

    RellenarCombos re = new RellenarCombos();    
    
    public InterGestor() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("TubyPedidos");
        setIconImage(getIconImage());
        re.RellenarComboBox2("Producto", "Nombre", jProductos);
        MinMax1();
        MinMax2();
        mostrar("alminter");
        transpareciaButton();
        TablaLimpia();
        transpareciaTable2();
    }
    
    
    @Override
    public Image getIconImage (){
        
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imag/TubyMini.png"));
        return retValue;
    }
    
   public void Aumentar(JComboBox Nombre,JSpinner StockAct, JSpinner StokAct2)
{
  try{
   ConexionBD con = new ConexionBD();
   Connection ConexionBD = con.getConnection(); 
   CallableStatement proc = ConexionBD.prepareCall("CALL SP_STOCKAUMENTO(?,?,?)");      
   proc.setString(1, Nombre.getSelectedItem().toString());
   proc.setString(2, StockAct.getValue().toString());
   proc.setString(3, StokAct2.getValue().toString());
   proc.execute();
   JOptionPane.showMessageDialog(null, "Se a aumentado correctamente", "Mensaje", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
   mostrar("alminter");
   TablaLimpia();
}catch(Exception e){

System.out.println(e);

}


}
  public void Disminuir(JComboBox Nombre,JSpinner StockAct, JSpinner StokAct2)
{
  try{
   ConexionBD con = new ConexionBD();
   Connection ConexionBD = con.getConnection(); 
   CallableStatement proc = ConexionBD.prepareCall("CALL SP_STOCKDISMINUCION(?,?,?)");      
   proc.setString(1, Nombre.getSelectedItem().toString());
   proc.setString(2, StockAct.getValue().toString());
   proc.setString(3, StokAct2.getValue().toString());
   proc.execute();
   JOptionPane.showMessageDialog(null, "Se a disminuido correctamente", "Mensaje", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/TubyMicro.png"));
   mostrar("alminter");
   TablaLimpia();
}catch(Exception e){

System.out.println(e);

}


}   
public void mostrar(String tabla){
        String sql = "select Nombre, StockAct, StokAct2 from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Stock");
        model.addColumn("StockUnit");
        visor.setModel(model);
        
        String [] datos = new String[3];
        try{
            st = DBConexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
               {
                   datos[0]=rs.getString(1);
                   datos[1]=rs.getString(2);
                   datos[2]=rs.getString(3);
                   model.addRow(datos);
               } 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
            
        }
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new ImageFondo();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jProductos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jAumentar = new javax.swing.JButton();
        jDisminuir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSalida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Stocks:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 133, 53, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Stock Unitario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 187, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 567, 50, 1));
        jPanel1.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 128, 53, -1));
        jPanel1.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 182, 53, -1));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("REGULADOR DE INVENTARIO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 84, -1, -1));

        jProductos.setToolTipText("");
        jPanel1.add(jProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 79, 112, -1));

        visor = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        visor.setModel(new javax.swing.table.DefaultTableModel(
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
        visor.getTableHeader().setReorderingAllowed(false);
        visor.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(visor);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 300, -1, 220));

        jAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/Bittorrent_Plus_22936.png"))); // NOI18N
        jAumentar.setBorder(null);
        jAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAumentarActionPerformed(evt);
            }
        });
        jPanel1.add(jAumentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, 60));

        jDisminuir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/minus_14757.png"))); // NOI18N
        jDisminuir.setBorder(null);
        jDisminuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDisminuirActionPerformed(evt);
            }
        });
        jPanel1.add(jDisminuir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, 56));

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("GESTOR DE STOCKS:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 266, -1, -1));

        jSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/restart_back_left_arrow_6022.png"))); // NOI18N
        jSalida.setBorder(null);
        jSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(jSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 70, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAumentarActionPerformed
        Aumentar(jProductos,jSpinner1,jSpinner2);
    }//GEN-LAST:event_jAumentarActionPerformed

    private void jDisminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDisminuirActionPerformed
        Disminuir(jProductos,jSpinner1,jSpinner2);
    }//GEN-LAST:event_jDisminuirActionPerformed

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
            java.util.logging.Logger.getLogger(InterGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterGestor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAumentar;
    private javax.swing.JButton jDisminuir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jProductos;
    private javax.swing.JButton jSalida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable visor;
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

