package carsale;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class MainCar {

    /**
     * @param args
     */
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

    }

}
