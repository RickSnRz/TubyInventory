/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Class.ConexionBD;
import Class.ReporteMysql;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import movimiento.MoverComp;


/**
 *
 * @author Rick
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public void TablaLimpia(){
        visor.getColumnModel().getColumn(0).setResizable(false);
        visor.getColumnModel().getColumn(1).setResizable(false);
        visor.getColumnModel().getColumn(2).setResizable(false);
        visor.getColumnModel().getColumn(3).setResizable(false);
        visor.getColumnModel().getColumn(4).setResizable(false);
 }
    public void BotonTrans(){
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b3.setOpaque(false);
        b3.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        b5.setOpaque(false);
        b5.setContentAreaFilled(false);
        b5.setBorderPainted(false);
    }
    
    public void ocultarBoton(){
       
        if (b3.getX() == 780){
         b3.setVisible(false);
         
         
        }else{
         b3.setVisible(true);
         
         
        }
        
        if (b2.getX() == 780){
            b2.setVisible(false);
        }else{
            b2.setVisible(true);
        }
        
        if (btnProductos.getY() == -65){
            
            b5.setVisible(true);
        }else{
            
            b5.setVisible(false);
        }
        if (btnGestor.getY() == -65){
           
            
        }else{
          
            
        }
       
        
    }
    
    ImageFondo fondo = new ImageFondo();
    
 
    
    
    public Menu() {
        this.setContentPane(fondo);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("TubyMenu");
        setIconImage(getIconImage());
        transpareciaButton();
        transpareciaTable();
        mostrar("Inventario");
        
        
        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        BotonTrans();
        
    }

    
    
    @Override
    public Image getIconImage (){
        
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imag/TubyMini.png"));
        return retValue;
    }
    
    public void mostrar(String tabla){
        String sql = "select * from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection DBConexion = con.getConnection();  
        System.out.println(sql);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Categoria");
        model.addColumn("Precio");
        model.addColumn("Stock");
        model.addColumn("StockUnit");
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
    
    
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    

    public void transpareciaButton() {
        btnCerrar.setOpaque(false);
        btnCerrar.setContentAreaFilled(true);
        btnCerrar.setBorderPainted(false);
        btnGestor.setOpaque(false);
        btnGestor.setContentAreaFilled(true);
        btnGestor.setBorderPainted(false);
        btnPedidos.setOpaque(false);
        btnPedidos.setContentAreaFilled(true);
        btnPedidos.setBorderPainted(false);
        btnProductos.setOpaque(false);
        btnProductos.setContentAreaFilled(true);
        btnProductos.setBorderPainted(false);
    }
    
    public void transpareciaTable() {
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
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnProductos = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnGestor = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 840, 350));

        jLabel1.setFont(new java.awt.Font("David", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INVENTARIO:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnProductos.setBackground(new java.awt.Color(0, 0, 0));
        btnProductos.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(204, 204, 255));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/49364_box_crate_delivery_entrega_inventory_icon.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProductos.setFocusable(false);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -65, 160, 70));

        btnPedidos.setBackground(new java.awt.Color(0, 0, 0));
        btnPedidos.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnPedidos.setForeground(new java.awt.Color(204, 204, 255));
        btnPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/ecommerce_cart_shop_online_shopping_device_icon_211034.png"))); // NOI18N
        btnPedidos.setText("Pedidos/Pendientes");
        btnPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPedidos.setFocusable(false);
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });
        jPanel1.add(btnPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, -65, 240, 70));

        btnGestor.setBackground(new java.awt.Color(0, 0, 0));
        btnGestor.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnGestor.setForeground(new java.awt.Color(204, 204, 255));
        btnGestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/bars_chart_settings_cogwheel_icon_211043.png"))); // NOI18N
        btnGestor.setText("Gestor Inventario");
        btnGestor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGestor.setFocusable(false);
        btnGestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestorActionPerformed(evt);
            }
        });
        jPanel1.add(btnGestor, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, -65, 260, 70));

        btnCerrar.setBackground(new java.awt.Color(0, 0, 0));
        btnCerrar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(204, 204, 255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/shutdown_off_close_exit_15253.png"))); // NOI18N
        btnCerrar.setText("Cerrar Sesion");
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrar.setFocusable(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, -65, 230, 70));

        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/Iziquierda.png"))); // NOI18N
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b1MouseClicked(evt);
            }
        });
        jPanel1.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 70, 60));

        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/abajo.png"))); // NOI18N
        b5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b5MouseClicked(evt);
            }
        });
        jPanel1.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/addUser.png"))); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel1.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, -1, -1));

        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imag/Oficina_PDF_35460.png"))); // NOI18N
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel1.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 70, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 190, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 380, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 200));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 850, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestorActionPerformed
        InterGestor ges = new InterGestor();
        ges.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnGestorActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        Login a = new Login();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        InterProductos proc = new InterProductos();
        proc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        InterPedido ped = new InterPedido();
        ped.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnPedidosActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        ReporteMysql report = new ReporteMysql();
        
    }//GEN-LAST:event_b3ActionPerformed

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked
         if (b3.getX() == 850) {
            MoverComp.Izquierda(b3, 2, 4, 655);
            b1.setIcon(new ImageIcon(getClass().getResource("/Imag/Derecha.png")));
        } else {
            MoverComp.Derecha(b3, 2, 4, 850);
            b1.setIcon(new ImageIcon(getClass().getResource("/Imag/Iziquierda.png")));
        }
        if (b2.getX() == 850) {
            MoverComp.Izquierda(b2, 2, 4, 715);
            
           
        } else {
            MoverComp.Derecha(b2, 2, 4, 850);
            
        }
    }//GEN-LAST:event_b1MouseClicked

    private void b5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b5MouseClicked
        if (btnProductos.getY() == -65) {
            MoverComp.Abajo(btnProductos, 2, 3, -8);
            
            
        } else {
            MoverComp.Arriba(btnProductos, 2, 3, -65);
            
        }
        if (btnPedidos.getY() == -65) {
            MoverComp.Abajo(btnPedidos, 2, 3, -8);
            
        } else {
            MoverComp.Arriba(btnPedidos, 2, 3, -65);
            
        }
        if (btnGestor.getY() == -65) {
            MoverComp.Abajo(btnGestor, 2, 3,  -8);
            
        } else {
            MoverComp.Arriba(btnGestor, 2, 3, -65);
           
        }
        if (btnCerrar.getY() == -65) {
            MoverComp.Abajo(btnCerrar, 2, 3,  -8);
            
        } else {
            MoverComp.Arriba(btnCerrar, 2, 3, -65);
            
        }
        if (b5.getY() == 0) {
            MoverComp.Abajo(b5, 2, 3,  60);
            b5.setIcon(new ImageIcon(getClass().getResource("/Imag/arriba.png")));
        } else {
            MoverComp.Arriba(b5, 2, 3, 0);
            b5.setIcon(new ImageIcon(getClass().getResource("/Imag/abajo.png")));
        }
    }//GEN-LAST:event_b5MouseClicked

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        InterUser b = new InterUser();
        b.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_b2ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b5;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGestor;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProductos;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
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
