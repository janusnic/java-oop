
class Point {
    public double x; // абсцисса точки
    public double y; // ордината точки

    // конструктор по умолчанию, создающий точку в начале координат
    public Point() {
        x = 0.0;
        y = 0.0;
    }
    // конструктор, создающий точку с указанными координатами
    public Point(double a, double b) {
        x = a;
        y = b;
    }  

}

public class MainPoint {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Point p1 = new Point();
        Point p2 = new Point(1,1);
        System.out.println(p2);
        System.out.println(p2.getClass().getName() 
                + "@" 
                + Integer.toHexString(p2.hashCode()));
        String s = p2 + " testing";
        System.out.println(s);
        p1 = null;
        s = p1 + " testing";
        System.out.println(s);

    }

}
