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
    private boolean sold = false;

    public Car(String model, String brand, double price) { //конструктор) 
        this.model = model;
        this.brand = brand;
        this.price = price;
        
    }
    
    public Car() {
        this(" "," ", 0);
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

    public Truck(String model, String brand, double price,int copasity) {
        super(model,brand, price); //вызов конструктора суперкласса
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
    
    public Sedan() {
        
    }

    public Sedan(String model, String brand, double price,int geartype) {
        super(model,brand, price); //вызов конструктора суперкласса
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
            
            case 2:
            return this.getWheel()*amt*1.5;
            
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
}



public class MainCar10 {

    /**
     * @param args
     */
    static void show1(Car obj1, Sedan obj2) {

        System.out.println("первый метод show(Car, Sedan)");
        }

    static void show(Sedan obj1, Car obj2){

        System.out.println("второй метод show(Sedan, Car)");
        }

    static void show(Object obj1, Object obj2){
        System.out.println("третий метод show(Object, Object)");
    }

    public static void main(String[] args) {

        Date date = new Date();
        Calendar cal1 = new GregorianCalendar(2013, 11, 25);//календарь на 25.11.2013

        System.out.println(date.toString());
        
        System.out.println(cal1.getTime());// 26.11.2013
        
        Car car1 = new Car("Honda", "Hyundai", 12000);
        car1.show(); // вызов show() класса Car
        car1.setWheel(4);
        System.out.println(car1.getModel()+" has "+ car1.getWheel()+" wheels");
        
        car1.setSold(date);
        
        System.out.println("Sale Price for "+car1.getModel()+" is "+car1.salePrice()+" $");
        
        Truck car2 = new Truck("Honda Ridgeline", "Hyundai", 20000, 10);
       
        car2.setWheel(6);
        
        car2.show(); // вызов show() класса Truck
        System.out.println(car2.getModel()+" has "+car2.getWheel()+" wheels");
        //System.out.println(car2.sold_on);// 
        System.out.println("Sale Price for "+car2.getModel()+" is "+car2.salePrice()+" $");
        Sedan car3 = new Sedan("Accord LX", "Hyundai", 25000, 1);
        
        car3.setWheel(4);
        System.out.println("Sale Price for "+car3.getModel()+" is "+car3.salePrice(6000.0)+" $");
        car3.show(); // вызов show() класса Truck
        System.out.println(car3.getModel()+" has "+car3.getWheel()+" wheels");


    }

}
