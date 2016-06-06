package vehicle;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Db {
    private static Connection con;
    private static Db instance;

    private Db() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:vehicle.db";
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public static synchronized Db getInstance() throws Exception {
        if (instance == null) {
            instance = new Db();
        }
        return instance;
    }

    public List<Brand> getBrands() throws SQLException {
        List<Brand> brands = new ArrayList<Brand>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT brand_id, brandName FROM brands");
            while (rs.next()) {
                Brand brand = new Brand(rs);
                brand.setBrandId(rs.getInt(1));
                brand.setNameBrand(rs.getString(2));
                

                brands.add(brand);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return brands;
    }
    
    public void insertBrand(Brand brand) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO brands (brandName) VALUES (?)");
            stmt.setString(1, brand.getNameBrand());
            
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
