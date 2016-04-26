class Circle {
    public double x; // абсцисса центра
    public double y; // ордината центра
    public double r; // радиус

    public void printCircle() {
        System.out.println("Окружность с центром ("+x+";"+y+") и радиусом "+r);
    }    
    public void moveCircle(double a, double b) {
        x = x + a;
        y = y + b;
    }
    public void zoomCircle(double k) {
        r = r * k;
    }
    // конструктор по умолчанию, теперь сразу после создания объекта будем
    // получать окружность единичного радиуса с центром в начале координат
    public Circle() {
        x = 0.0;
        y = 0.0;
        r = 1.0;
    }
}

public class MainCircle {
    public static void main(String[] args) {
        Circle o1 = new Circle();
        o1.printCircle(); // Окружность с центром (0.0;0.0) и радиусом 1.0
    }
}