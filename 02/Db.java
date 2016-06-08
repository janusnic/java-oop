package vehicle;

import java.sql.Connection;
import java.sql.Date;
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
    public Collection<Car> getAllCars() throws SQLException {
        Collection<Car> cars = new ArrayList<Car>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT * FROM cars " +
                    "ORDER BY model");
            while (rs.next()) {
                Car car = new Car(rs);
                cars.add(car);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return cars;
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
    
    public void insertCar(Car car) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO cars " +
                    "(model, brandId, price, builtDate, gear, seats, wheels, miles, capacity, sold, soldOn ) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            
            stmt.setString(1, car.getModel());
            stmt.setInt(2, car.getBrandId());
            stmt.setDouble(3, car.getPrice());
            stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
            stmt.setInt(5, car.getGeer());
            stmt.setInt(6, car.getSeats());
            stmt.setInt(7, car.getWheels());
            stmt.setInt(8, car.getMiles());
            stmt.setInt(9, car.getCapacity());
            stmt.setString(10, new String(new char[]{car.getSold()}));
            stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                        
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public Collection<Car> getCarsFromBrand(Brand brand, int year) throws SQLException {
        Collection<Car> cars = new ArrayList<Car>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "SELECT * FROM cars " +
                    "WHERE brandId=? " +
                    "ORDER BY model");
            stmt.setInt(1, brand.getBrandId());
            //stmt.setInt(2, year);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(rs);

                cars.add(car);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return cars;
    }

}
