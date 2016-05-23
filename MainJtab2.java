import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel{
    @Override
    public int getRowCount() {
        return 12;
    }
    @Override
    public int getColumnCount() {
        return 12;
    }
    @Override
    public Object getValueAt(int r, int c) {
        return r * c;
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
public class MainJtab2 {

    /**
     * @param args
     */
    //Массив содержащий заголоки таблицы
    Object[] headers = { "id","model","brand","price","built_date","gear","seat","wheels","miles","capacity","sold","sold_on" };
     
    //Массив содержащий информацию для таблицы
    Object[][] data = {
        { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
        { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
        { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
        { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
        
    };
 
    //Объект таблицы
    JTable jTabCars;
 
    MainJtab2() {
        //Создаем новый контейнер JFrame
        JFrame jfrm = new JFrame("JTableCars");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(1000, 200);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        // jTabCars = new JTable(data, headers);
        jTabCars = new JTable(new MyTableModel());
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
                new MainJtab2();
            }
        });
    }
}
