import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Db {
    
        private Connection con = null;

        public Db(String driver, String url)
        {
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url);

            } catch (ClassNotFoundException ex)
            {
                System.err.println("Db.Cannot find this db driver classes.");
                    ex.printStackTrace();
            } catch (SQLException e)
            {
                System.err.println("Db.Cannot connect to this db.");
                    e.printStackTrace();        
              }         
        }
        

}
