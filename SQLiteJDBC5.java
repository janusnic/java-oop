import java.sql.*;

public class SQLiteJDBC5
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
      String sql = "UPDATE cars set price = 25000.00 where id=1;";
      stmt.executeUpdate(sql);
      c.commit();

      ResultSet rs = stmt.executeQuery( "SELECT * FROM cars;" );
      while ( rs.next() ) {
                       int id = rs.getInt("id");
                       String  model = rs.getString("model");
                       String built_date  = rs.getString("built_date");
                       String  brand = rs.getString("brand");
                       float price = rs.getFloat("price");
                       System.out.println( "ID = " + id );
                       System.out.println( "model = " + model );
                       System.out.println( "built_date = " + built_date );
                       System.out.println( "brand = " + brand );
                       System.out.println( "price = " + price );
                       System.out.println();
                    }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}
