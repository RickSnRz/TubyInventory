package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {
    
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "root";
    private static String PASSWORD = "989974718@";
    private static String URL = "jdbc:mysql://localhost:3306/TubyInventory";
    
    static {
    try {
    Class.forName(DRIVER);
    
} catch (ClassNotFoundException e){
    JOptionPane.showMessageDialog(null, "ERROR EN EL DRIVER:  "+e);
}
    }
    
public Connection getConnection() {
    Connection con = null;
    
      try {
   con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
   
} catch (SQLException e){
    JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXION:  "+e);
} 
        return con;
    
    
    
}  
    
    
}