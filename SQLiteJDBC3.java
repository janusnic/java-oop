import java.sql.*;

public class SQLiteJDBC3
{
      public static void main( String args[] )
    {
      Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
        "VALUES ( 'Accord LX', 'Hyundai',12000,'2007-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00' );"; 
        stmt.executeUpdate(sql);

        sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
        "VALUES ( 'Honda', 'Hyundai',10000,'2010-01-01 10:00:00',1,4,4,12000,2,0,'0000-00-00 00:00:00' );"; 
        stmt.executeUpdate(sql);

        sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
        "VALUES ( 'Honda', 'Hyundai',11000,'2012-01-01 10:00:00',1,4,4,1000,2,0,'0000-00-00 00:00:00' );"; 
        
        stmt.executeUpdate(sql);

        sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
        "VALUES ( 'Honda Sed', 'Hyundai',13000,'2013-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00' );"; 
        stmt.executeUpdate(sql);

        stmt.close();
        c.commit();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Records created successfully");
    }
}
