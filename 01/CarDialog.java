package vehicle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;


public class CarDialog extends JDialog implements ActionListener {
    
    private static final int D_HEIGHT = 300;   // высота окна
    private final static int D_WIDTH = 450;   // ширина окна
    private final static int L_X = 10;      // левая граница метки для поля
    private final static int L_W = 100;     // ширина метки для поля
    private final static int C_W = 150;     // ширина поля
    
    // Владелец нашего окна - вводим для вызова нужного нам метода
    private View owner;
    // Результат нажатия кнопок
    private boolean result = false;

    // Параметры
    
    private int carId = 0;
    
    private JTextField model = new JTextField();
    
    private JSpinner builtDate = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
    private JSpinner soldOn = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
    
    private ButtonGroup sold = new ButtonGroup();

    private JSpinner price = new JSpinner(new SpinnerNumberModel(1000, 500, 100000, 1));
    private JSpinner geer = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
    private JSpinner seats = new JSpinner(new SpinnerNumberModel(4, 2, 8, 1));
    private JSpinner wheels = new JSpinner(new SpinnerNumberModel(4, 2, 12, 1));
    
    private JSpinner miles = new JSpinner(new SpinnerNumberModel(10, 1, 1000000, 1));
    private JSpinner capacity = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
    
    private JComboBox brandList;

    // Второй параметр содержит знак - добавление автомобиля или исправление
    public CarDialog(List<Brand> brands, boolean newCar, View owner) {
        // После вставки автомобиля без закрытия окна нам потребуется перегрузка списка
        // А для этого нам надо иметь доступ к этому методу в главной форме
        this.owner = owner;
        
        // Установить заголовок
        setTitle("Редактирование данных автомобиля");
        getContentPane().setLayout(new FlowLayout());

        brandList = new JComboBox(new Vector<Brand>(brands));

        JRadioButton yes = new JRadioButton("Да");
        JRadioButton no = new JRadioButton("Нет");
        // Сделаем имя такое же, как требуется в баще данных - Д/Н
        
        yes.setActionCommand("Д");
        no.setActionCommand("Н");
        
        // Добавим радио-кнопки в группу
        sold.add(yes);
        sold.add(no);

        // Не будем использовать layout совсем
        getContentPane().setLayout(null);

        // Разместим компоненты по абсолютным координатам
       
        
        // модель
        JLabel l = new JLabel("Модель:", JLabel.RIGHT);
        l.setBounds(L_X, 10, L_W, 20);
        getContentPane().add(l);
        model.setBounds(L_X + L_W + 10, 10, C_W, 20);
        getContentPane().add(model);

        // Бренд
        l = new JLabel("Бренд:", JLabel.RIGHT);
        l.setBounds(L_X, 30, L_W, 25);
        getContentPane().add(l);
        brandList.setBounds(L_X + L_W + 10, 30, C_W, 25);
        getContentPane().add(brandList);
        
        // Продан
        l = new JLabel("Продан:", JLabel.RIGHT);
        l.setBounds(L_X, 70, L_W, 20);
        getContentPane().add(l);
        yes.setBounds(L_X + L_W + 10, 70, C_W / 2, 20);
        getContentPane().add(yes);

        // Сделаем по умолчанию нет
        no.setBounds(L_X + L_W + 10 + C_W / 2, 70, C_W / 2, 20);
        no.setSelected(true);
        getContentPane().add(no);

        // Дата выпуска
        l = new JLabel("Дата выпуска:", JLabel.RIGHT);
        l.setBounds(L_X, 90, L_W, 20);
        getContentPane().add(l);
        builtDate.setBounds(L_X + L_W + 10, 90, C_W, 20);
        getContentPane().add(builtDate);
        
        // Пробег
        l = new JLabel("Пробег:", JLabel.RIGHT);
        l.setBounds(L_X, 115, L_W, 20);
        getContentPane().add(l);
        miles.setBounds(L_X + L_W + 10, 115, C_W, 20);
        getContentPane().add(miles);
        
     // Число мест
        l = new JLabel("Число мест:", JLabel.RIGHT);
        l.setBounds(L_X, 135, L_W, 20);
        getContentPane().add(l);
        seats.setBounds(L_X + L_W + 10, 135, C_W, 20);
        getContentPane().add(seats);

        // Цена
        l = new JLabel("Цена:", JLabel.RIGHT);
        l.setBounds(L_X, 155, L_W, 20);
        getContentPane().add(l);
        price.setBounds(L_X + L_W + 10, 155, C_W, 20);
        getContentPane().add(price);

        // Число колес
        l = new JLabel("Число колес:", JLabel.RIGHT);
        l.setBounds(L_X, 175, L_W, 20);
        getContentPane().add(l);
        wheels.setBounds(L_X + L_W + 10, 175, C_W, 20);
        getContentPane().add(wheels);

        // Грузоподъемность
        l = new JLabel("Грузоподъемность:", JLabel.RIGHT);
        l.setBounds(L_X, 195, L_W, 20);
        getContentPane().add(l);
        capacity.setBounds(L_X + L_W + 10, 195, C_W, 20);
        getContentPane().add(capacity);

        // Передача
        l = new JLabel("Тип передачи:", JLabel.RIGHT);
        l.setBounds(L_X, 215, L_W, 20);
        getContentPane().add(l);
        geer.setBounds(L_X + L_W + 10, 215, C_W, 20);
        getContentPane().add(geer);

        // Дата продажи
        l = new JLabel("Дата продажи:", JLabel.RIGHT);
        l.setBounds(L_X, 235, L_W, 20);
        getContentPane().add(l);
        soldOn.setBounds(L_X + L_W + 10, 235, C_W, 20);
        getContentPane().add(soldOn);

        JButton btnOk = new JButton("OK");
        btnOk.setName("OK");
        btnOk.addActionListener(this);
        btnOk.setBounds(L_X + L_W + C_W + 10 + 50, 10, 100, 25);
        getContentPane().add(btnOk);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setName("Cancel");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(L_X + L_W + C_W + 10 + 50, 40, 100, 25);
        getContentPane().add(btnCancel);

        if (newCar) {
            JButton btnNew = new JButton("New");
            btnNew.setName("New");
            btnNew.addActionListener(this);
            btnNew.setBounds(L_X + L_W + C_W + 10 + 50, 70, 100, 25);
            getContentPane().add(btnNew);
        }

        // Устанавливаем поведение формы при закрытии - не закрывать совсем.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Получаем размеры экрана
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // А теперь просто помещаем его по центру, вычисляя координаты на основе полученной информации
        setBounds(((int) d.getWidth() - CarDialog.D_WIDTH) / 2, ((int) d.getHeight() - CarDialog.D_HEIGHT) / 2,
                CarDialog.D_WIDTH, CarDialog.D_HEIGHT);
    }

    // Установить поля соответственно переданным данным об автомобиле
    public void setCar(Car car) {
        carId = car.getCarId();
        model.setText(car.getModel());
        builtDate.getModel().setValue(car.getBuiltDate());
        for (Enumeration e = sold.getElements(); e.hasMoreElements();) {
            AbstractButton ab = (AbstractButton) e.nextElement();
            ab.setSelected(ab.getActionCommand().equals(new String("" + car.getSold())));
        }
        price.getModel().setValue(new Double(car.getPrice()));
        geer.getModel().setValue(new Integer(car.getGeer()));
        wheels.getModel().setValue(new Integer(car.getWheels()));
        miles.getModel().setValue(new Integer(car.getMiles()));
        capacity.getModel().setValue(new Integer(car.getCapacity()));
        seats.getModel().setValue(new Integer(car.getSeats()));
        soldOn.getModel().setValue(car.getDateSoldOn());
        for (int i = 0; i < brandList.getModel().getSize(); i++) {
            Brand g = (Brand) brandList.getModel().getElementAt(i);
            if (g.getBrandId() == car.getBrandId()) {
                brandList.setSelectedIndex(i);
                break;
            }
        }
    }

    // Вернуть данные в виде нового авто с соотвтествующими полями
    public Car getCar() {
        Car car = new Car();
        
        car.setCarId(carId);

        car.setModel(model.getText());
    
        Date d = ((SpinnerDateModel) builtDate.getModel()).getDate();
        car.setBuiltDate(d);
        Date ds = ((SpinnerDateModel) soldOn.getModel()).getDate();
        car.setDateSoldOn(ds);
        
        for (Enumeration e = sold.getElements(); e.hasMoreElements();) {
            AbstractButton ab = (AbstractButton) e.nextElement();
            if (ab.isSelected()) {
                car.setSold(ab.getActionCommand().charAt(0));
            }
        }
        double prc = ((SpinnerNumberModel) price.getModel()).getNumber().intValue();
        car.setPrice(prc);
                        
        int g = ((SpinnerNumberModel) geer.getModel()).getNumber().intValue();
        car.setGeer(g);
        int w = ((SpinnerNumberModel) wheels.getModel()).getNumber().intValue();
        car.setGeer(w);
        int m = ((SpinnerNumberModel) miles.getModel()).getNumber().intValue();
        car.setMiles(m);
        int cp = ((SpinnerNumberModel) capacity.getModel()).getNumber().intValue();
        car.setCapacity(cp);
        
        int s = ((SpinnerNumberModel) seats.getModel()).getNumber().intValue();
        car.setSeats(s);
        
        car.setBrandId(((Brand) brandList.getSelectedItem()).getBrandId());
        
        
        return car;
    }

    // Получить результат, true - кнопка ОК, false - кнопка Cancel
    public boolean getResult() {
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        // Добавляем авто, но не закрываем окно
        if (src.getName().equals("New")) {
            result = true;
            try {
                Db.getInstance().insertCar(getCar());
                //owner.reloadStudents();
                model.setText("");
                
            } catch (Exception sql_e) {
                JOptionPane.showMessageDialog(this, sql_e.getMessage());
            }
            return;
        }
        if (src.getName().equals("OK")) {
            result = true;
        }
        if (src.getName().equals("Cancel")) {
            result = false;
        }
        setVisible(false);
    }
}
