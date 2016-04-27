
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

    public String toString() {
       return x + " " + y;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
       return y;
    } 
}

public class MainPoint3 {

    /**
     * @param args
     */
    public static void main(String[] args) {
       Point p1 = new Point(1, 1);
    
            // использовать Object.toString() по умолчанию
    
            System.out.println(p1);
    
            // аналогично предыдущему, показывая
            // функцию toString() по умолчанию
    
            System.out.println(p1.getClass().getName() 
                + "@" 
                + Integer.toHexString(p1.hashCode()));
    
            // неявный вызов toString() объекта
            // как часть соединения строк
    
            String s = p1 + " testing";
            System.out.println(s);
    
            // аналогично предыдущему, за исключением того,
            // что ссылка на объект равна null
    
            p1 = null;
            s = p1 + " testing";
            System.out.println(s);

    }

}
