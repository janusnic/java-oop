import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel{
    
    ArrayList<Car> cars;
        MyTableModel(ArrayList<Car> cars) {
            super();
            this.cars = cars;
        }
    
    @Override
    public int getRowCount() {
        // return 12;
        return cars.size();
    }
    @Override
    public int getColumnCount() {
        return 12;
    }
    /*@Override
    public Object getValueAt(int r, int c) {
        return r * c;
    }*/
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return cars.get(r).getId();
            case 1:
                return cars.get(r).getModel();
            case 2:
                return cars.get(r).getBrand();
            case 3:
                return cars.get(r).getPrice();
            case 4:
                return cars.get(r).getDateWasBuilt();
            case 5:
                return cars.get(r).getGear();
            case 6:
                return cars.get(r).getSeat();
            case 7:
                return cars.get(r).getWheels();
            case 8:
                return cars.get(r).getMiles();
            case 9:
                return cars.get(r).getCapacity();
            case 10:
                return cars.get(r).getSold();
            case 11:
                return cars.get(r).getSoldOn();

            default:
                return "";
        }
    }
    
    @Override
    public String getColumnName(int c) {
        switch (c) {
            case 0:
                return "id";
            case 1:
                return "model";
            case 2:
                return "brand";
            case 3:
                return "price";
            case 4:
                return "built_date";
            case 5:
                return "gear";
            case 6:
                return "seat";
            case 7:
                return "wheels";
            case 8:
                return "miles";
            case 9:
                return "capacity";
            case 10:
                return "sold";
            case 11:
                return "sold_on";
            default:
                return "Other Column";
        }
    }
    
  
}
public class MainJtab3 {

    /**
     * @param args
     */

 
    //Объект таблицы
    JTable jTabCars;
 
    MainJtab3() {
       //Создаем новый контейнер JFrame
        JFrame jfrm = new JFrame("JTableCars");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(1000, 200);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<Car> cars = new ArrayList<>();
    
        cars.add(new Car(1,"Accord LX","Hyundai",25000,new Date(20080101),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(2,"Honda","Hyundai",25000,new Date(100801010),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(3,"Honda Sed","Hyundai",21000,new Date(2009010110),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(4,"Accord LX1","Hyundai",21100,new Date(2005010110),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(5,"Accord LX2","Hyundai",21500,new Date(2007010110),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(6,"Accord LX3","Hyundai",22200,new Date(2010010110),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(7,"Accord LX4","Hyundai",24000,new Date(2012010110),1,4,4,10000,2,false,new Date(0000)));
        cars.add(new Car(8,"Accord LX5","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
        
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        // jTabCars = new JTable(data, headers);
        jTabCars = new JTable(new MyTableModel(cars));
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(jTabCars);
        //Устаналиваем размеры прокручиваемой области
        jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
        jfrm.getContentPane().add(jscrlp);
        //Отображаем контейнер
        jfrm.setVisible(true);
    }
 
    //Функция main, запускающаяся при старте приложения
    public static void main(String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJtab3();
            }
        });
    }
}
