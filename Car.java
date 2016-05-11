package carsale;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Car {
    private int id; // id
    private String brand; // The make of the car as a string
    private String model; // The model of the car as a string.
    private Date built_date; // The integral year the car was built.
    private int gear; // передача
    private int wheels; // колесо - An integer representing the number of wheels the car has.
    private int numberOfSeat;
    
    private double price;
    
    private int miles; // The integral number of miles driven on the car.
    
    protected Date sold_on; // Дата - транспортное средство было продано
    protected boolean sold = false;

    public Car(int id, String model, String brand, double price) { //конструктор) 
        this.id = id;
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
    
    public double salePrice(){
        // Return the sale price for this vehicle as a float amount.
                
        if (sold != false){
            return 0.0; // Already sold
        }
        else {
        return this.getWheel()*5000.0;
        }
      }
    public double salePrice(double amt){
        // Return the sale price for this vehicle as a float amount.
        
        if (sold != false){
            return 0.0; // Already sold
        }
        else {
        return this.getWheel()*amt;
        }
      }
    public void setSold(Date d) {
        this.sold_on = d;
        this.sold = true;
    } 
    public void show(){
            System.out.println("название: " + this.model + " " + this.brand+ ", цена: "+ this.price);
        }

    public int getId() {
        return id;
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
    private int wheels;

    public Truck(int id, String model, String brand, double price,int copasity) {
        super(id, model,brand, price); //вызов конструктора суперкласса
        loadCapacity = copasity;
    }

    public int getWheel() {
        return wheels;
    }

    public void setWheel(int wheels) {
        this.wheels = wheels;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
    public void show() {
         /* вывод copasity: переопределенный метод show() из Car */
        super.show(); // вывод значений из Car
             System.out.println("copasity: " + loadCapacity);
        }
}

class Sedan extends Car {
    private int gearType;
    
    public Sedan(int id, String model, String brand, double price,int geartype) {
        super(id, model,brand, price); //вызов конструктора суперкласса
        gearType = geartype;
    }

    public int getGearType() {
        return gearType;
    }

    public void setGearType(int gearType) {
        this.gearType = gearType;
    }
    
    public double salePrice(double amt){
        if (sold != false){
              return 0.0; // Already sold
          }
        else {
         switch (this.getGearType()) {
             case 1:
             return this.getWheel()*amt*1.2;
             //break;
             case 2:
             return this.getWheel()*amt*1.5;
             //break;
             default:
             return this.getWheel()*amt;
             }
         
         }
       }
    public void show() {
     /* вывод copasity: переопределенный метод show() из Car */
    super.show(); // вывод значений из Car
         System.out.println("Gear Type: " + gearType);
    }
    public void display()
    {
        System.out.println("The id of car:" + this.getId());
        System.out.println("The brand of car:" + this.getBrand());
        System.out.println("The model of car:" + this.getModel());
        System.out.println("The price of car:" + this.getPrice());
        System.out.println("The gear Type of car:" + gearType);
    }
}


