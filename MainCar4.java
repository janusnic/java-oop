import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class Car {
    private String brand; // The make of the car as a string
    private String model; // The model of the car as a string.
    private Date built_date; // The integral year the car was built.
    private int gear; // передача
    private int wheels; // колесо - An integer representing the number of wheels the car has.
    private int numberOfSeat;
    
    private double price;
    
    private int miles; // The integral number of miles driven on the car.
    private Date sold_on; // Дата - транспортное средство было продано

    public Car(String model, String brand, double price) { //конструктор) 
        this.model = model;
        this.brand = brand;
        this.price = price;
        
    }
    
    public double getPrice(){
        return price;
        }
    
    public void setPrice(double price){
        this.price = price;
        }
    
     public void show(){
            System.out.println("название: " + this.model + " " + this.brand+ ", цена: "+ this.price);
        }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateWasBuilt() {
        return built_date;
    }

    public void setDateWasBuilt(Date built_date) {
        this.built_date = built_date;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public int getWheel() {
        return wheels;
    }

    public void setWheel(int wheels) {
        this.wheels = wheels;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }
}

class Truck extends Car {
    private int loadCapacity; // вместимость

    public Truck(String model, String brand, double price,int copasity) {
        super(model,brand, price); //вызов конструктора суперкласса
        loadCapacity = copasity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}


public class MainCar4 {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Car car1 = new Car("Honda", "Hyundai", 12000);
        car1.show(); // вызов show() класса Car
        Truck car2 = new Truck("Honda Ridgeline", "Hyundai", 20000, 10);
        
        car2.show(); // вызов show() класса Truck

    }

}
