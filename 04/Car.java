package vehicle;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Date;
import java.util.Locale;

public class Car {
	    private int carId;
	    private String model;
	    private int brandId;
	    private Double price;
	    private Date builtDate;
	    private int geer;
	    private int seats;
	    private int wheels;
	    private int miles;
	    private int capacity;
	    private char sold;
	    private Date soldOn;

	    public Car(ResultSet rs) throws SQLException {
	        setCarId(rs.getInt(1));
	        setModel(rs.getString(2));
	        setBrandId(rs.getInt(3));
	        setPrice(rs.getDouble(4));
	        setBuiltDate(rs.getDate(5));
	        setGeer(rs.getInt(6));
	        setSeats(rs.getInt(7));
	        setWheels(rs.getInt(8));
	        setMiles(rs.getInt(9));
	        setCapacity(rs.getInt(10));
	        setSold(rs.getString(11).charAt(0));
	        setDateSoldOn(rs.getDate(12));
	        
	    }

	    public Date getBuiltDate() {
	        return builtDate;
	    }

	    public void setBuiltDate(Date builtDate) {
	        this.builtDate = builtDate;
	    }
	    public Date getDateSoldOn() {
	        return soldOn;
	    }

	    public void setDateSoldOn(Date soldOn) {
	        this.soldOn = soldOn;
	    }
	    
	    public int getGeer() {
	        return geer;
	    }

	    public void setGeer(int geer) {
	        this.geer = geer;
	    }
	    
	    public int getWheels() {
	        return wheels;
	    }

	    public void setWheels(int wheels) {
	        this.wheels = wheels;
	    }
	    
	    public int getMiles() {
	        return miles;
	    }

	    public void setMiles(int miles) {
	        this.miles = miles;
	    }
	    public int getSeats() {
	        return seats;
	    }

	    public void setSeats(int seats) {
	        this.seats = seats;
	    }
	   
	    public int getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public int getBrandId() {
	        return brandId;
	    }

	    public void setBrandId(int brandId) {
	        this.brandId = brandId;
	    }

	    public int getCarId() {
	        return carId;
	    }

	    public void setCarId(int carId) {
	        this.carId = carId;
	    }

	    public String getModel() {
	        return model;
	    }

	    public void setModel(String model) {
	        this.model = model;
	    }

	    public Double getPrice() {
	        return price;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public char getSold() {
	        return sold;
	    }

	    public void setSold(char sold) {
	        this.sold = sold;
	    }


}
