
class Point {
    
    private double x; // абсцисса точки
    private double y; // ордината точки

    // конструктор по умолчанию, создающий точку в начале координат
    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }
    // конструктор, создающий точку с указанными координатами
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }  
}

public class MainPoint0 {

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
