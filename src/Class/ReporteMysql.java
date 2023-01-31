package Class;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionPool;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteMysql {
    public ReporteMysql(){
        try {
            JasperReport report;
            JasperPrint jprint;
            report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reports/Tubynet.jasper"));
            jprint = JasperFillManager.fillReport(report, null, ConnectionPool.getInstance().getConnection());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setTitle("Vista Previa");
            view.setIconImage(getIconImage());
            view.setVisible(true);
        }catch (HeadlessException | NumberFormatException | JRException ex){
            System.out.print(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ReporteMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Image getIconImage(){
        
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imag/TubyMini.png"));
        return retValue;
    }
    
}
