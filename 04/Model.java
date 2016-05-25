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
    public Object[][] rows ;
    
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
        
            int j =0;
            rows = new Object[10][columnCount];
            while (rs.next()) {
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    values[i - 1] = rs.getObject(i);
                    rows[j][i-1]=values[i - 1];
                }
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

}
