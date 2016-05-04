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
            
            private int miles; // The integral number of miles driven on the car.
            private Date sold_on; // Дата - транспортное средство было продано

            public Car() {
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

        public class MainCar2 {

            public static void main(String[] args) {

                Date date = new Date();
                Calendar cal1 = new GregorianCalendar(2013, 11, 25);//календарь на 25.11.2013

                System.out.println(date.toString());
                
                System.out.println(cal1.getTime());// 26.11.2013

                Car car = new Car(); 
                car.setBrand("Honda");
                car.setDateWasBuilt(cal1.getTime());
                
                System.out.println("Brand = " + car.getBrand());
                System.out.println("Brand = " + car.getDateWasBuilt().toString());

            }

        }

