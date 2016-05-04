import java.util.Date;
    class Car {
            private String brand; // The make of the car as a string
            private String model; // The model of the car as a string.
            private int year; // The integral year the car was built.
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

            public int getYearWasBuilt() {
                return year;
            }

            public void setYearWasBuilt(int year) {
                this.year = year;
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
        public class MainCar1 {

            public static void main(String[] args) {
                Date date = new Date();
                System.out.println(date.toString());
                long millis = date.getTime();
                System.out.println(String.valueOf(millis));
                Car car = new Car(); // Создаём объект car класса Car с помощью конструктора по умолчанию
                car.setBrand("Honda");
               
                System.out.println("Brand = " + car.getBrand());
            }

        }

