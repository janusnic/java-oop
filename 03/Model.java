import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {
    
    public Object [] table_header;

    public static final Object[][] DATA =  {
        { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
        { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
        { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
        { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        
    };
    public Model() {
            
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:vehicle.db";                
            String query = "select *  from cars"; 
            Connection con = DriverManager.getConnection(url);
           
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            
            int columnCount = rs.getMetaData().getColumnCount();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            
            table_header = new String[columnCount];
            
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
               String h = resultSetMetaData.getColumnName(i);
              table_header[i-1]= h;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
