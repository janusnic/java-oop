import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.sql.*;

import java.util.Date;

class MyTableModel extends AbstractTableModel{
    
    ArrayList<Car> cars;
        MyTableModel(ArrayList<Car> cars) {
            super();
            this.cars = cars;
        }
    
    @Override
    public int getRowCount() {
        
        return cars.size();
    }
    @Override
    public int getColumnCount() {
        return 12;
    }
 
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

public class MainJtab5 {

    /**
     * @param args
     */
    //Объект таблицы
    JTable jTabCars;
    ArrayList<Car> cars = new ArrayList<>();
    //ArrayList<Car> results = new ArrayList<Car>();
    MyTableModel tModel;
    
    MainJtab5() {
        //Создаем новый контейнер JFrame
        JFrame jfrm = new JFrame("JTableCars");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(1000, 200);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Connection c = null;
        Statement stmt = null;

        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "UPDATE cars set price = 25000.00 where id=1;";
          stmt.executeUpdate(sql);
          c.commit();

          ResultSet resultset = stmt.executeQuery( "SELECT * FROM cars;" );
     
          while (resultset.next()) {
  
               cars.add(new Car(resultset.getInt("id"),
                           resultset.getString("model"),
                           resultset.getString("brand"),
                           resultset.getFloat("price"),
                           new Date(2005010110),
                           resultset.getInt("gear"),
                           resultset.getInt("seat"),
                           resultset.getInt("wheels"),
                           resultset.getInt("miles"),
                           resultset.getInt("capacity"),
                           resultset.getBoolean("sold"),
                           new Date(0)
                   ));
           }
          resultset.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        tModel = new MyTableModel(cars);
        jTabCars = new JTable(tModel);
        
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(jTabCars);
        //Устаналиваем размеры прокручиваемой области
        jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
        jfrm.getContentPane().add(jscrlp);
        //Отображаем контейнер
        jfrm.setVisible(true);
        
        JButton btnPress = new JButton("Click!");
        btnPress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cars.add(new Car(9,"Accord LX6","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
                tModel.fireTableDataChanged();
            }
        });
        jfrm.add(btnPress);
        
    }
    
    //Функция main, запускающаяся при старте приложения
    public static void main(String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJtab5();
            }
        });
    }
}
