import java.sql.*;

public class SQLiteJDBC2
{
    public static void main( String args[] )
    {
      Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "CREATE TABLE cars " +
                     "(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
                     " model          CHAR(50)    NOT NULL, " + 
                     " brand          CHAR(50)    NOT NULL, " +
                     " price          REAL        NOT NULL, " + 
                     " built_date     DATEYIME    NOT NULL, " + 
                     " gear           INTEGER     NOT NULL, " + 
                     " seat           INTEGER     NOT NULL, " + 
                     " wheels         INTEGER     NOT NULL, " + 
                     " miles          INTEGER     NOT NULL, " + 
                     " capacity       INTEGER     NOT NULL, " + 
                     " sold           BOOLEAN             , " + 
                     " sold_on        DATEYIME    NOT NULL)"; 
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Table created successfully");
    }
}
