package Class;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class consultas {
    public void guardarUsuario(String usuario, String password, String email){
        ConexionBD db = new ConexionBD();
        String sql = "insert into Usuarios(Usuario, Contraseña, Email) values ('" + usuario +"', '" + password +"', '" + email +"');";
        Statement st;
        Connection conexion = db.getConnection();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Guardado correctamente", "Information", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Imag/like.png"));
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
  }   
