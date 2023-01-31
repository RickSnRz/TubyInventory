package Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class RellenarCombos {
    public void RellenarComboBox(String tabla, String valor, JComboBox combo){
        String sql = "Select * from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection conexion = con.getConnection();
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                combo.addItem(rs.getString(valor));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
        }
    }
    
    public void RellenarComboBox1(String tabla, String valor, JComboBox combo){
        String sql = "Select TipoPago from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection conexion = con.getConnection();
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                combo.addItem(rs.getString(valor));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
        }
    }
    public void RellenarComboBox2(String tabla, String valor, JComboBox combo){
        String sql = "Select Nombre from " + tabla;
        Statement st;
        ConexionBD con = new ConexionBD();
        Connection conexion = con.getConnection();
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                combo.addItem(rs.getString(valor));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
        }
    }
}

