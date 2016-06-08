# java Unit 16

01 add car
=============

class CarDialog
---------------

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


class Car
---------
        package vehicle;
        import java.text.DateFormat;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.text.Collator;
        import java.util.Date;
        import java.util.Locale;

        public class Car {
                private int carId;
                private String model;
                private int brandId;
                private Double price;
                private Date builtDate;
                private int geer;
                private int seats;
                private int wheels;
                private int miles;
                private int capacity;
                private char sold;
                private Date soldOn;

                public Car()
                {
                    
                }
                public Car(ResultSet rs) throws SQLException {
                    setCarId(rs.getInt(1));
                    setModel(rs.getString(2));
                    setBrandId(rs.getInt(3));
                    setPrice(rs.getDouble(4));
                    setBuiltDate(rs.getDate(5));
                    setGeer(rs.getInt(6));
                    setSeats(rs.getInt(7));
                    setWheels(rs.getInt(8));
                    setMiles(rs.getInt(9));
                    setCapacity(rs.getInt(10));
                    setSold(rs.getString(11).charAt(0));
                    setDateSoldOn(rs.getDate(12));
                    
                }

                public Date getBuiltDate() {
                    return builtDate;
                }

                public void setBuiltDate(Date builtDate) {
                    this.builtDate = builtDate;
                }
                public Date getDateSoldOn() {
                    return soldOn;
                }

                public void setDateSoldOn(Date soldOn) {
                    this.soldOn = soldOn;
                }
                
                public int getGeer() {
                    return geer;
                }

                public void setGeer(int geer) {
                    this.geer = geer;
                }
                
                public int getWheels() {
                    return wheels;
                }

                public void setWheels(int wheels) {
                    this.wheels = wheels;
                }
                
                public int getMiles() {
                    return miles;
                }

                public void setMiles(int miles) {
                    this.miles = miles;
                }
                public int getSeats() {
                    return seats;
                }

                public void setSeats(int seats) {
                    this.seats = seats;
                }
               
                public int getCapacity() {
                    return capacity;
                }

                public void setCapacity(int capacity) {
                    this.capacity = capacity;
                }

                public int getBrandId() {
                    return brandId;
                }

                public void setBrandId(int brandId) {
                    this.brandId = brandId;
                }

                public int getCarId() {
                    return carId;
                }

                public void setCarId(int carId) {
                    this.carId = carId;
                }

                public String getModel() {
                    return model;
                }

                public void setModel(String model) {
                    this.model = model;
                }

                public Double getPrice() {
                    return price;
                }

                public void setPrice(Double price) {
                    this.price = price;
                }

                public char getSold() {
                    return sold;
                }

                public void setSold(char sold) {
                    this.sold = sold;
                }


        }

class Db
--------

        package vehicle;

        import java.sql.Connection;
        import java.sql.Date;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.List;

        public class Db {
            private static Connection con;
            private static Db instance;

            private Db() throws Exception {
                try {
                    Class.forName("org.sqlite.JDBC");
                    String url = "jdbc:sqlite:vehicle.db";
                    con = DriverManager.getConnection(url);
                } catch (ClassNotFoundException e) {
                    throw new Exception(e);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }

            public static synchronized Db getInstance() throws Exception {
                if (instance == null) {
                    instance = new Db();
                }
                return instance;
            }

            public List<Brand> getBrands() throws SQLException {
                List<Brand> brands = new ArrayList<Brand>();

                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT brand_id, brandName FROM brands");
                    while (rs.next()) {
                        Brand brand = new Brand(rs);
                        brand.setBrandId(rs.getInt(1));
                        brand.setNameBrand(rs.getString(2));
                        

                        brands.add(brand);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                return brands;
            }
            public Collection<Car> getAllCars() throws SQLException {
                Collection<Car> cars = new ArrayList<Car>();

                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT * FROM cars " +
                            "ORDER BY model");
                    while (rs.next()) {
                        Car car = new Car(rs);
                        cars.add(car);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }

                return cars;
            }
            
            public void insertBrand(Brand brand) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement("INSERT INTO brands (brandName) VALUES (?)");
                    stmt.setString(1, brand.getNameBrand());
                    
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
            
            public void insertCar(Car car) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement(
                            "INSERT INTO cars " +
                            "(model, brandId, price, builtDate, gear, seats, wheels, miles, capacity, sold, soldOn ) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                    
                    stmt.setString(1, car.getModel());
                    stmt.setInt(2, car.getBrandId());
                    stmt.setDouble(3, car.getPrice());
                    stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
                    stmt.setInt(5, car.getGeer());
                    stmt.setInt(6, car.getSeats());
                    stmt.setInt(7, car.getWheels());
                    stmt.setInt(8, car.getMiles());
                    stmt.setInt(9, car.getCapacity());
                    stmt.setString(10, new String(new char[]{car.getSold()}));
                    stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                                
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }

        }

class View 
----------
            package vehicle;

            import java.sql.SQLException;
            import java.util.Vector;
            import java.awt.Dimension;
            import java.awt.FlowLayout;
            import java.awt.BorderLayout;
            import java.awt.Component;
            import java.awt.GridLayout;

            import java.awt.event.ActionEvent;
            import java.awt.event.ActionListener;

            import javax.swing.JButton;
            import javax.swing.JFrame;
            import javax.swing.JLabel;

            import javax.swing.JMenu;
            import javax.swing.JMenuBar;
            import javax.swing.JMenuItem;
            import javax.swing.JOptionPane;
            import javax.swing.JPanel;
            import javax.swing.JScrollPane;
            import javax.swing.JTable;

            import javax.swing.JList;

            import javax.swing.JSpinner;
            import javax.swing.SpinnerModel;
            import javax.swing.SpinnerNumberModel;
            import javax.swing.SwingUtilities;
            import javax.swing.border.BevelBorder;

            import javax.swing.event.ChangeEvent;
            import javax.swing.event.ChangeListener;
            import javax.swing.event.ListSelectionEvent;
            import javax.swing.event.ListSelectionListener;

            public class View  extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
                // Введем сразу имена для кнопок - потом будем их использовать в обработчиках
                
                private static final String MANAGE_BRAND = "manageBrand";
                private static final String CLEAR_BRAND = "clearBrand";

                private static final String INSERT_CAR = "insertCar";
                private static final String UPDATE_CAR = "updateCar";
                private static final String DELETE_CAR = "deleteCar";

                private static final String ALL_CARS = "allCars";
                private static final String ALL_SOLD_CARS = "allSoldCars";
                private static final String NOT_SOLD_CARS = "notSoldCars";
                private static final String ABOUT_US = "aboutUs";
                
                

                private JList brandList;
                private JTable carList;
                private Db db = null;
              
                private JSpinner spYear;
                private AboutDialog dialog;
                private BrandPanel left;
                 public View() throws Exception {
                        // Устанавливаем layout для всей клиентской части формы
                        getContentPane().setLayout(new BorderLayout());
                    
                        // Создаем строку меню
                        JMenuBar menuBar = new JMenuBar();

                        // Создаем выпадающее меню
                        JMenu menu = new JMenu("Отчеты");

                       // Создаем пункт в выпадающем меню
                        JMenuItem menuItem = new JMenuItem("Все автомобили");
                        menuItem.setName(ALL_CARS);
                        
                        // Добавляем листенер
                        menuItem.addActionListener(this);
                        
                        // Вставляем пункт меню в выпадающее меню
                        menu.add(menuItem);
                        
                        JMenuItem menuItem1 = new JMenuItem("Проданые автомобили");
                        menuItem1.setName(ALL_SOLD_CARS);
                        
                        // Добавляем листенер
                        menuItem1.addActionListener(this);
                        
                        // Вставляем пункт меню в выпадающее меню
                        menu.add(menuItem1);
                        
                        JMenuItem menuItem2 = new JMenuItem("Непроданые автомобили");
                        menuItem2.setName(NOT_SOLD_CARS);
                        
                        // Добавляем листенер
                        menuItem2.addActionListener(this);
                        
                        // Вставляем пункт меню в выпадающее меню
                        menu.add(menuItem2);
                        
                        // Создаем выпадающее меню
                        JMenu menua = new JMenu("About");

                        // Создаем пункт в выпадающем меню
                        JMenuItem menuItema1 = new JMenuItem("About Us");
                        menuItema1.setName(ABOUT_US);
                      
                       // Добавляем листенер
                       menuItema1.addActionListener(this);
                       
                       
                       // Вставляем пункт меню в выпадающее меню
                        menua.add(menuItema1);
                    
                        // Вставляем выпадающее меню в строку меню
                        menuBar.add(menu);
                        menuBar.add(menua);
                        // Устанавливаем меню для формы
                        setJMenuBar(menuBar);
                        

                        // Создаем верхнюю панель, где будет поле для ввода года
                        JPanel top = new JPanel();
                        // Устанавливаем для нее layout
                        top.setLayout(new FlowLayout(FlowLayout.LEFT));

                        // Вставляем пояснительную надпись
                        top.add(new JLabel("Год выпуска:"));
                        // Делаем спин-поле
                        // 1. Задаем модель поведения - только цифры
                        // 2. Вставляем в панель
                        SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
                        spYear = new JSpinner(sm);
                        // Добавляем листенер
                        spYear.addChangeListener(this);
                        top.add(spYear);

                        
                        // Создаем нижнюю панель и задаем ей layout
                        JPanel bot = new JPanel();
                        bot.setLayout(new BorderLayout());

                        // Создаем левую панель для вывода списка производителей
                        // Она у нас
                        left= new BrandPanel();
                        // Задаем layout и задаем "бордюр" вокруг панели
                        left.setLayout(new BorderLayout());
                        left.setBorder(new BevelBorder(BevelBorder.LOWERED));

                        // Получаем коннект к базе и создаем объект ManagementSystem
                        db = Db.getInstance();
                        // Получаем список групп
                        Vector<Brand> brand = new Vector<Brand>(db.getBrands());
                
                        // Создаем надпись
                        left.add(new JLabel("Производитель:"), BorderLayout.NORTH);
                        
                        // Создаем визуальный список и вставляем его в скроллируемую
                        // панель, которую в свою очередь уже кладем на панель left
                        brandList = new JList(brand);
                        // Добавляем листенер
                        brandList.addListSelectionListener(this);
                        // Сразу выделяем первого из производителей
                        brandList.setSelectedIndex(0);
                        left.add(new JScrollPane(brandList), BorderLayout.CENTER);
                        
                        // Создаем кнопки для производителей
                        JButton btnMvGr = new JButton("Редактировать");
                        btnMvGr.setName(MANAGE_BRAND);
                        JButton btnClGr = new JButton("Очистить");
                        btnClGr.setName(CLEAR_BRAND);
                        
                        // Добавляем листенер
                        btnMvGr.addActionListener(this);
                        btnClGr.addActionListener(this);
                        
                        // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                        JPanel pnlBtnGr = new JPanel();
                        pnlBtnGr.setLayout(new GridLayout(1, 2));
                        pnlBtnGr.add(btnMvGr);
                        pnlBtnGr.add(btnClGr);
                        left.add(pnlBtnGr, BorderLayout.SOUTH);

                
                        // Создаем правую панель для вывода списка cars
                        JPanel right = new JPanel();
                        
                        // Задаем layout и задаем "бордюр" вокруг панели
                        right.setLayout(new BorderLayout());
                        right.setBorder(new BevelBorder(BevelBorder.LOWERED));

                        // Создаем надпись
                        right.add(new JLabel("Автомобили:"), BorderLayout.NORTH);
                        
                        // Создаем таблицу и вставляем ее в скроллируемую
                        // панель, которую в свою очередь уже кладем на панель right
                        // Наша таблица пока ничего не умеет - просто положим ее как заготовку
                        // Сделаем в ней 4 колонки 
                        carList = new JTable(1, 4);
                        right.add(new JScrollPane(carList), BorderLayout.CENTER);
                        
                        // Создаем кнопки для автомобилей
                        JButton btnAddSt = new JButton("Добавить");
                        btnAddSt.setName(INSERT_CAR);
                        btnAddSt.addActionListener(this);
                        JButton btnUpdSt = new JButton("Исправить");
                        btnUpdSt.setName(UPDATE_CAR);
                        btnUpdSt.addActionListener(this);
                        JButton btnDelSt = new JButton("Удалить");
                        btnDelSt.setName(DELETE_CAR);
                        btnDelSt.addActionListener(this);
                        
                        // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                        JPanel pnlBtnSt = new JPanel();
                        pnlBtnSt.setLayout(new GridLayout(1, 3));
                        pnlBtnSt.add(btnAddSt);
                        pnlBtnSt.add(btnUpdSt);
                        pnlBtnSt.add(btnDelSt);
                        right.add(pnlBtnSt, BorderLayout.SOUTH);

                        // Вставляем панели со списками производителей и автомобилей в нижнюю панель
                        bot.add(left, BorderLayout.WEST);
                        bot.add(right, BorderLayout.CENTER);

                        // Вставляем верхнюю и нижнюю панели в форму
                        getContentPane().add(top, BorderLayout.NORTH);
                        getContentPane().add(bot, BorderLayout.CENTER);

                        // Задаем границы формы
                        setBounds(200, 200, 700, 500);
                    }
                    
                    // Метод для обеспечения интерфейса ChangeListener
                    public void stateChanged(ChangeEvent e) {

                    }
                    
                    // Метод для обеспечения интерфейса ActionListener
                    
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() instanceof Component) {
                            Component c = (Component) e.getSource();

                            if (c.getName().equals(ALL_SOLD_CARS)) {
                                showSoldCars();
                            }
                            if (c.getName().equals(NOT_SOLD_CARS)) {
                                showNotSoldCars();
                            }
                            if (c.getName().equals(ALL_CARS)) {
                                showAllCars();
                            }
                            if (c.getName().equals(ABOUT_US)) {
                                showAbouUs();
                                
                            }
                            if (c.getName().equals(MANAGE_BRAND)) {
                                manageBrand();
                            }
                            if (c.getName().equals(CLEAR_BRAND)) {
                                clearBrand();
                            }
                            if (c.getName().equals(INSERT_CAR)) {
                                insertCar();
                            }
                            if (c.getName().equals(UPDATE_CAR)) {
                                updateCar();
                            }
                            if (c.getName().equals(DELETE_CAR)) {
                                deleteCar();
                            }
                        }
                    }
                 
                 
                    // Метод для обеспечения интерфейса ListSelectionListener
                    public void valueChanged(ListSelectionEvent e) {
                    }
                    
                 // метод для обновления списка 
                    public void reloadBrands() {
                        // Создаем анонимный класс для потока
                        Thread t = new Thread() {
                            // Переопределяем в нем метод run
                            public void run() {
                                if (brandList != null) {
                                    // Получаем визуальный список
                                    try {
                                        Vector<Brand> brand = new Vector<Brand>(db.getBrands());
                                        // Обновить список
                                        // 
                                        brandList.setListData(brand);
                                    } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(View.this, e.getMessage());
                                    }
                                }
                            }
                            // Окончание нашего метода run
                        };
                        // Окончание определения анонимного класса

                        // И теперь мы запускаем наш поток
                        t.start();
                    }

                    // метод для показа всех автомобилей
                    private void showAllCars() {
                        JOptionPane.showMessageDialog(this, "showAllCars");
                    }
                    
                    private void showSoldCars() {
                        JOptionPane.showMessageDialog(this, "show All Sold Cars");
                    }

                    private void showNotSoldCars() {
                        JOptionPane.showMessageDialog(this, "show Not Sold Cars");
                    }
                    
                    private void showAbouUs() {
                        //
                        if(dialog == null) // в первый раз
                            dialog = new AboutDialog(View.this);
                        dialog.setVisible(true); // отобразить диалог
                    }
                    
                   // метод для производителей
                    private void manageBrand() {

                        Thread t = new Thread() {

                        public void run() {
                         try {
                        
                        BrandDialog brand = new BrandDialog(db.getBrands(),View.this);
                        // Задаем ему режим модальности - нельзя ничего кроме него выделить
                        brand.setModal(true);
                        // Показываем диалог
                        brand.setVisible(true);
                        if (brand.getResult()) {
                            Brand s = brand.getBrand();
                            db.insertBrand(s);
                            reloadBrands();
                        }
                         
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(View.this, e.getMessage());
                    }
                        
                            }
                        };
                        t.start();
                    }

                    // метод для очистки производителей
                    private void clearBrand() {
                        JOptionPane.showMessageDialog(this, "Clear Brand");
                    }
                    
                 // метод для добавления автомобиля
                    private void insertCar() {
                        Thread t = new Thread() {

                            public void run() {
                                try {
                                    // Добавляем 
                                    
                                    CarDialog car = new CarDialog(db.getBrands(), true, View.this);
                                    car.setModal(true);
                                    car.setVisible(true);
                                    if (car.getResult()) {
                                        Car c = car.getCar();
                                        db.insertCar(c);

                                    }
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                                }
                            }
                        };
                        t.start();
                    }

                    // метод для редактирования автомобиля
                    private void updateCar() {
                        JOptionPane.showMessageDialog(this, "Update Car");
                    }

                    // метод для удаления автомобиля
                    private void deleteCar() {
                        JOptionPane.showMessageDialog(this, "Delete Car");
                    }


                
                    public static void main(String args[]) {
                    
                        SwingUtilities.invokeLater(new Runnable() {

                            public void run() {
                                try {
                                    View sf = new View();
                                    sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                    sf.setVisible(true);
                                    
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }
                        });
                    }
            }

            //Наш внутренний класс - переопределенная панель.
            class BrandPanel extends JPanel {

             public Dimension getPreferredSize() {
                 return new Dimension(250, 0);
             }
            }

02 Edit
=======

class CarTableModel
-------------------
        package vehicle;
        import java.text.DateFormat;
        import java.util.Vector;

        import javax.swing.table.AbstractTableModel;

        public class CarTableModel extends AbstractTableModel  {
            // Сделаем хранилище для нашего списка автомобилей

            private Vector cars;

            // Модель при создании получает список автомобилей
            public CarTableModel(Vector cars) {
                this.cars = cars;
            }

            // Количество строк равно числу записей
            public int getRowCount() {
                if (cars != null) {
                    return cars.size();
                }
                return 0;
            }

            // Количество столбцов - 4
            public int getColumnCount() {
                return 4;
            }

            // Вернем наименование колонки
            public String getColumnName(int column) {
                String[] colNames = {"Модель", "Цена", "Пробег", "Дата выпуска"};
                return colNames[column];
            }

            // Возвращаем данные для определенной строки и столбца
            public Object getValueAt(int rowIndex, int columnIndex) {
                if (cars != null) {
                    // Получаем из вектора автомобиля
                    Car car = (Car) cars.get(rowIndex);
                    // В зависимости от колонки возвращаем модель, цена и т.д.
                    switch (columnIndex) {
                        case 0:
                            return car.getModel();
                        case 1:
                            return car.getPrice();
                        case 2:
                            return car.getMiles();
                        case 3:
                            return DateFormat.getDateInstance(DateFormat.SHORT).format(
                                    car.getBuiltDate());
                    }
                }
                return null;
            }

            // Добавим метод, который возвращает автомобиль по номеру строки
           
            public Car getCar(int rowIndex) {
                if (cars != null) {
                    if (rowIndex < cars.size() && rowIndex >= 0) {
                        return (Car) cars.get(rowIndex);
                    }
                }
                return null;
            }
        }

class CarDialog
----------------
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
                        owner.reloadCars();
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

class Db
--------
        package vehicle;

        import java.sql.Connection;
        import java.sql.Date;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.List;

        public class Db {
            private static Connection con;
            private static Db instance;

            private Db() throws Exception {
                try {
                    Class.forName("org.sqlite.JDBC");
                    String url = "jdbc:sqlite:vehicle.db";
                    con = DriverManager.getConnection(url);
                } catch (ClassNotFoundException e) {
                    throw new Exception(e);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }

            public static synchronized Db getInstance() throws Exception {
                if (instance == null) {
                    instance = new Db();
                }
                return instance;
            }

            public List<Brand> getBrands() throws SQLException {
                List<Brand> brands = new ArrayList<Brand>();

                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT brand_id, brandName FROM brands");
                    while (rs.next()) {
                        Brand brand = new Brand(rs);
                        brand.setBrandId(rs.getInt(1));
                        brand.setNameBrand(rs.getString(2));
                        

                        brands.add(brand);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                return brands;
            }
            public Collection<Car> getAllCars() throws SQLException {
                Collection<Car> cars = new ArrayList<Car>();

                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT * FROM cars " +
                            "ORDER BY model");
                    while (rs.next()) {
                        Car car = new Car(rs);
                        cars.add(car);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }

                return cars;
            }
            
            public void insertBrand(Brand brand) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement("INSERT INTO brands (brandName) VALUES (?)");
                    stmt.setString(1, brand.getNameBrand());
                    
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
            
            public void insertCar(Car car) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement(
                            "INSERT INTO cars " +
                            "(model, brandId, price, builtDate, gear, seats, wheels, miles, capacity, sold, soldOn ) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                    
                    stmt.setString(1, car.getModel());
                    stmt.setInt(2, car.getBrandId());
                    stmt.setDouble(3, car.getPrice());
                    stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
                    stmt.setInt(5, car.getGeer());
                    stmt.setInt(6, car.getSeats());
                    stmt.setInt(7, car.getWheels());
                    stmt.setInt(8, car.getMiles());
                    stmt.setInt(9, car.getCapacity());
                    stmt.setString(10, new String(new char[]{car.getSold()}));
                    stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                                
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
            
            public Collection<Car> getCarsFromBrand(Brand brand, int year) throws SQLException {
                Collection<Car> cars = new ArrayList<Car>();

                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.prepareStatement(
                            "SELECT * FROM cars " +
                            "WHERE brandId=? " +
                            "ORDER BY model");
                    stmt.setInt(1, brand.getBrandId());
                    //stmt.setInt(2, year);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        Car car = new Car(rs);

                        cars.add(car);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }

                return cars;
            }

        }

class View
----------
        package vehicle;

        import java.sql.SQLException;
        import java.util.Vector;
        import java.awt.Dimension;
        import java.awt.FlowLayout;
        import java.awt.BorderLayout;
        import java.awt.Component;
        import java.awt.GridLayout;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.Collection;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JLabel;

        import javax.swing.JMenu;
        import javax.swing.JMenuBar;
        import javax.swing.JMenuItem;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;

        import javax.swing.JList;

        import javax.swing.JSpinner;
        import javax.swing.SpinnerModel;
        import javax.swing.SpinnerNumberModel;
        import javax.swing.SwingUtilities;
        import javax.swing.border.BevelBorder;

        import javax.swing.event.ChangeEvent;
        import javax.swing.event.ChangeListener;
        import javax.swing.event.ListSelectionEvent;
        import javax.swing.event.ListSelectionListener;

        public class View  extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
            // Введем сразу имена для кнопок - потом будем их использовать в обработчиках
            
            private static final String MANAGE_BRAND = "manageBrand";
            private static final String CLEAR_BRAND = "clearBrand";

            private static final String INSERT_CAR = "insertCar";
            private static final String UPDATE_CAR = "updateCar";
            private static final String DELETE_CAR = "deleteCar";

            private static final String ALL_CARS = "allCars";
            private static final String ALL_SOLD_CARS = "allSoldCars";
            private static final String NOT_SOLD_CARS = "notSoldCars";
            private static final String ABOUT_US = "aboutUs";
            
            

            private JList brandList;
            private JTable carList;
            private Db db = null;
          
            private JSpinner spYear;
            private AboutDialog dialog;
            private BrandPanel left;
             public View() throws Exception {
                    // Устанавливаем layout для всей клиентской части формы
                    getContentPane().setLayout(new BorderLayout());
                
                    // Создаем строку меню
                    JMenuBar menuBar = new JMenuBar();

                    // Создаем выпадающее меню
                    JMenu menu = new JMenu("Отчеты");

                   // Создаем пункт в выпадающем меню
                    JMenuItem menuItem = new JMenuItem("Все автомобили");
                    menuItem.setName(ALL_CARS);
                    
                    // Добавляем листенер
                    menuItem.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem);
                    
                    JMenuItem menuItem1 = new JMenuItem("Проданые автомобили");
                    menuItem1.setName(ALL_SOLD_CARS);
                    
                    // Добавляем листенер
                    menuItem1.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem1);
                    
                    JMenuItem menuItem2 = new JMenuItem("Непроданые автомобили");
                    menuItem2.setName(NOT_SOLD_CARS);
                    
                    // Добавляем листенер
                    menuItem2.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem2);
                    
                    // Создаем выпадающее меню
                    JMenu menua = new JMenu("About");

                    // Создаем пункт в выпадающем меню
                    JMenuItem menuItema1 = new JMenuItem("About Us");
                    menuItema1.setName(ABOUT_US);
                  
                   // Добавляем листенер
                   menuItema1.addActionListener(this);
                   
                   
                   // Вставляем пункт меню в выпадающее меню
                    menua.add(menuItema1);
                
                    // Вставляем выпадающее меню в строку меню
                    menuBar.add(menu);
                    menuBar.add(menua);
                    // Устанавливаем меню для формы
                    setJMenuBar(menuBar);
                    

                    // Создаем верхнюю панель, где будет поле для ввода года
                    JPanel top = new JPanel();
                    // Устанавливаем для нее layout
                    top.setLayout(new FlowLayout(FlowLayout.LEFT));

                    // Вставляем пояснительную надпись
                    top.add(new JLabel("Год выпуска:"));
                    // Делаем спин-поле
                    // 1. Задаем модель поведения - только цифры
                    // 2. Вставляем в панель
                    SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
                    spYear = new JSpinner(sm);
                    // Добавляем листенер
                    spYear.addChangeListener(this);
                    top.add(spYear);

                    
                    // Создаем нижнюю панель и задаем ей layout
                    JPanel bot = new JPanel();
                    bot.setLayout(new BorderLayout());

                    // Создаем левую панель для вывода списка производителей
                    // Она у нас
                    left= new BrandPanel();
                    // Задаем layout и задаем "бордюр" вокруг панели
                    left.setLayout(new BorderLayout());
                    left.setBorder(new BevelBorder(BevelBorder.LOWERED));

                    // Получаем коннект к базе и создаем объект ManagementSystem
                    db = Db.getInstance();
                    // Получаем список групп
                    Vector<Brand> brand = new Vector<Brand>(db.getBrands());
            
                    // Создаем надпись
                    left.add(new JLabel("Производитель:"), BorderLayout.NORTH);
                    
                    // Создаем визуальный список и вставляем его в скроллируемую
                    // панель, которую в свою очередь уже кладем на панель left
                    brandList = new JList(brand);
                    // Добавляем листенер
                    brandList.addListSelectionListener(this);
                    // Сразу выделяем первого из производителей
                    brandList.setSelectedIndex(0);
                    left.add(new JScrollPane(brandList), BorderLayout.CENTER);
                    
                    // Создаем кнопки для производителей
                    JButton btnMvGr = new JButton("Редактировать");
                    btnMvGr.setName(MANAGE_BRAND);
                    JButton btnClGr = new JButton("Очистить");
                    btnClGr.setName(CLEAR_BRAND);
                    
                    // Добавляем листенер
                    btnMvGr.addActionListener(this);
                    btnClGr.addActionListener(this);
                    
                    // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                    JPanel pnlBtnGr = new JPanel();
                    pnlBtnGr.setLayout(new GridLayout(1, 2));
                    pnlBtnGr.add(btnMvGr);
                    pnlBtnGr.add(btnClGr);
                    left.add(pnlBtnGr, BorderLayout.SOUTH);

            
                    // Создаем правую панель для вывода списка cars
                    JPanel right = new JPanel();
                    
                    // Задаем layout и задаем "бордюр" вокруг панели
                    right.setLayout(new BorderLayout());
                    right.setBorder(new BevelBorder(BevelBorder.LOWERED));

                    // Создаем надпись
                    right.add(new JLabel("Автомобили:"), BorderLayout.NORTH);
                    
                    // Создаем таблицу и вставляем ее в скроллируемую
                    // панель, которую в свою очередь уже кладем на панель right
                    // Наша таблица пока ничего не умеет - просто положим ее как заготовку
                    // Сделаем в ней 4 колонки 
                    carList = new JTable(1, 4);
                    right.add(new JScrollPane(carList), BorderLayout.CENTER);
                    
                    // Создаем кнопки для автомобилей
                    JButton btnAddSt = new JButton("Добавить");
                    btnAddSt.setName(INSERT_CAR);
                    btnAddSt.addActionListener(this);
                    JButton btnUpdSt = new JButton("Исправить");
                    btnUpdSt.setName(UPDATE_CAR);
                    btnUpdSt.addActionListener(this);
                    JButton btnDelSt = new JButton("Удалить");
                    btnDelSt.setName(DELETE_CAR);
                    btnDelSt.addActionListener(this);
                    
                    // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                    JPanel pnlBtnSt = new JPanel();
                    pnlBtnSt.setLayout(new GridLayout(1, 3));
                    pnlBtnSt.add(btnAddSt);
                    pnlBtnSt.add(btnUpdSt);
                    pnlBtnSt.add(btnDelSt);
                    right.add(pnlBtnSt, BorderLayout.SOUTH);

                    // Вставляем панели со списками производителей и автомобилей в нижнюю панель
                    bot.add(left, BorderLayout.WEST);
                    bot.add(right, BorderLayout.CENTER);

                    // Вставляем верхнюю и нижнюю панели в форму
                    getContentPane().add(top, BorderLayout.NORTH);
                    getContentPane().add(bot, BorderLayout.CENTER);

                    // Задаем границы формы
                    setBounds(200, 200, 700, 500);
                }
                
                // Метод для обеспечения интерфейса ChangeListener
                public void stateChanged(ChangeEvent e) {
                    reloadCars();
                }
                
                // Метод для обеспечения интерфейса ActionListener
                
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof Component) {
                        Component c = (Component) e.getSource();

                        if (c.getName().equals(ALL_SOLD_CARS)) {
                            showSoldCars();
                        }
                        if (c.getName().equals(NOT_SOLD_CARS)) {
                            showNotSoldCars();
                        }
                        if (c.getName().equals(ALL_CARS)) {
                            showAllCars();
                        }
                        if (c.getName().equals(ABOUT_US)) {
                            showAbouUs();
                            
                        }
                        if (c.getName().equals(MANAGE_BRAND)) {
                            manageBrand();
                        }
                        if (c.getName().equals(CLEAR_BRAND)) {
                            clearBrand();
                        }
                        if (c.getName().equals(INSERT_CAR)) {
                            insertCar();
                        }
                        if (c.getName().equals(UPDATE_CAR)) {
                            updateCar();
                        }
                        if (c.getName().equals(DELETE_CAR)) {
                            deleteCar();
                        }
                    }
                }
             
             
                // Метод для обеспечения интерфейса ListSelectionListener
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        reloadCars();
                    }
                }
                   
            
                // метод для обновления списка автомобилей для определенного производителя
                public void reloadCars() {
            
                // Создаем анонимный класс для потока
                Thread t = new Thread() {
                    // Переопределяем в нем метод run

                    public void run() {
                        if (carList != null) {
                            // Получаем визуальный список
                            Brand g = (Brand) brandList.getSelectedValue();
                            // Получаем число из спинера
                            int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
                            try {
                                // Получаем список автомобилей
                                Collection<Car> s = db.getCarsFromBrand(g, y);
                                // И устанавливаем модель для таблицы с новыми данными
                                carList.setModel(new CarTableModel(new Vector<Car>(s)));
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(View.this, e.getMessage());
                            }
                        }
                    }
                    // Окончание нашего метода run
                };
                // Окончание определения анонимного класса

                // И теперь мы запускаем наш поток
                t.start();
            }


                
             // метод для обновления списка 
                public void reloadBrands() {
                    // Создаем анонимный класс для потока
                    Thread t = new Thread() {
                        // Переопределяем в нем метод run
                        public void run() {
                            if (brandList != null) {
                                // Получаем визуальный список
                                try {
                                    Vector<Brand> brand = new Vector<Brand>(db.getBrands());
                                    // Обновить список
                                    // 
                                    brandList.setListData(brand);
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                                }
                            }
                        }
                        // Окончание нашего метода run
                    };
                    // Окончание определения анонимного класса

                    // И теперь мы запускаем наш поток
                    t.start();
                }

                // метод для показа всех автомобилей
                private void showAllCars() {
                    JOptionPane.showMessageDialog(this, "showAllCars");
                }
                
                private void showSoldCars() {
                    JOptionPane.showMessageDialog(this, "show All Sold Cars");
                }

                private void showNotSoldCars() {
                    JOptionPane.showMessageDialog(this, "show Not Sold Cars");
                }
                
                private void showAbouUs() {
                    //
                    if(dialog == null) // в первый раз
                        dialog = new AboutDialog(View.this);
                    dialog.setVisible(true); // отобразить диалог
                }
                
               // метод для  производителей
                private void manageBrand() {

                    Thread t = new Thread() {

                    public void run() {
                     try {
                    
                    BrandDialog brand = new BrandDialog(db.getBrands(),View.this);
                    // Задаем ему режим модальности - нельзя ничего кроме него выделить
                    brand.setModal(true);
                    // Показываем диалог
                    brand.setVisible(true);
                    if (brand.getResult()) {
                        Brand s = brand.getBrand();
                        db.insertBrand(s);
                        reloadBrands();
                    }
                     
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                }
                    
                        }
                    };
                    t.start();
                }

                // метод для очистки производителей
                private void clearBrand() {
                    JOptionPane.showMessageDialog(this, "Clear Brand");
                }
                
             // метод для добавления автомобиля
                private void insertCar() {
                    Thread t = new Thread() {

                        public void run() {
                            try {
                                // Добавляем 
                                
                                CarDialog car = new CarDialog(db.getBrands(), true, View.this);
                                car.setModal(true);
                                car.setVisible(true);
                                if (car.getResult()) {
                                    Car c = car.getCar();
                                    db.insertCar(c);
                                    reloadCars();
                                }
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(View.this, e.getMessage());
                            }
                        }
                    };
                    t.start();
                }

                // метод для редактирования автомобиля
                private void updateCar() {
                    JOptionPane.showMessageDialog(this, "Update Car");
                }

                // метод для удаления автомобиля
                private void deleteCar() {
                    JOptionPane.showMessageDialog(this, "Delete Car");
                }


            
                public static void main(String args[]) {
                
                    SwingUtilities.invokeLater(new Runnable() {

                        public void run() {
                            try {
                                View sf = new View();
                                sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                sf.setVisible(true);
                                
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    });
                }
        }

        //Наш внутренний класс - переопределенная панель.
        class BrandPanel extends JPanel {

         public Dimension getPreferredSize() {
             return new Dimension(250, 0);
         }
        }



03 Edit
========
class Db
--------
        package vehicle;

        import java.sql.Connection;
        import java.sql.Date;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.List;

        public class Db {
            private static Connection con;
            private static Db instance;

            private Db() throws Exception {
                try {
                    Class.forName("org.sqlite.JDBC");
                    String url = "jdbc:sqlite:vehicle.db";
                    con = DriverManager.getConnection(url);
                } catch (ClassNotFoundException e) {
                    throw new Exception(e);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            }

            public static synchronized Db getInstance() throws Exception {
                if (instance == null) {
                    instance = new Db();
                }
                return instance;
            }

            public List<Brand> getBrands() throws SQLException {
                List<Brand> brands = new ArrayList<Brand>();

                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT brand_id, brandName FROM brands");
                    while (rs.next()) {
                        Brand brand = new Brand(rs);
                        brand.setBrandId(rs.getInt(1));
                        brand.setNameBrand(rs.getString(2));
                        

                        brands.add(brand);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                return brands;
            }
            public Collection<Car> getAllCars() throws SQLException {
                Collection<Car> cars = new ArrayList<Car>();

                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT * FROM cars " +
                            "ORDER BY model");
                    while (rs.next()) {
                        Car car = new Car(rs);
                        cars.add(car);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }

                return cars;
            }
            
            public void insertBrand(Brand brand) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement("INSERT INTO brands (brandName) VALUES (?)");
                    stmt.setString(1, brand.getNameBrand());
                    
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
            
            public void insertCar(Car car) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement(
                            "INSERT INTO cars " +
                            "(model, brandId, price, builtDate, gear, seats, wheels, miles, capacity, sold, soldOn ) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                    
                    stmt.setString(1, car.getModel());
                    stmt.setInt(2, car.getBrandId());
                    stmt.setDouble(3, car.getPrice());
                    stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
                    stmt.setInt(5, car.getGeer());
                    stmt.setInt(6, car.getSeats());
                    stmt.setInt(7, car.getWheels());
                    stmt.setInt(8, car.getMiles());
                    stmt.setInt(9, car.getCapacity());
                    stmt.setString(10, new String(new char[]{car.getSold()}));
                    stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                                
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
            
            public Collection<Car> getCarsFromBrand(Brand brand, int year) throws SQLException {
                Collection<Car> cars = new ArrayList<Car>();

                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = con.prepareStatement(
                            "SELECT * FROM cars " +
                            "WHERE brandId=? " +
                            "ORDER BY model");
                    stmt.setInt(1, brand.getBrandId());
                    //stmt.setInt(2, year);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        Car car = new Car(rs);

                        cars.add(car);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }

                return cars;
            }
            
            public void updateCar(Car car) throws SQLException {
                PreparedStatement stmt = null;
                try {
                    stmt = con.prepareStatement(
                            "UPDATE cars SET " +
                            "model=?, brandId=?, price=?, builtDate=?, gear=?, seats=?, wheels=?, miles=?, capacity=?, sold=?, soldOn=? " +
                            "WHERE carId=?");
                    
                    stmt.setString(1, car.getModel());
                    stmt.setInt(2, car.getBrandId());
                    stmt.setDouble(3, car.getPrice());
                    stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
                    stmt.setInt(5, car.getGeer());
                    stmt.setInt(6, car.getSeats());
                    stmt.setInt(7, car.getWheels());
                    stmt.setInt(8, car.getMiles());
                    stmt.setInt(9, car.getCapacity());
                    stmt.setString(10, new String(new char[]{car.getSold()}));
                    stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                    stmt.setInt(12, car.getCarId());
                    
                    stmt.execute();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
        }

class View
----------
        package vehicle;

        import java.sql.SQLException;
        import java.util.Vector;
        import java.awt.Dimension;
        import java.awt.FlowLayout;
        import java.awt.BorderLayout;
        import java.awt.Component;
        import java.awt.GridLayout;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.Collection;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JLabel;

        import javax.swing.JMenu;
        import javax.swing.JMenuBar;
        import javax.swing.JMenuItem;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;

        import javax.swing.JList;

        import javax.swing.JSpinner;
        import javax.swing.SpinnerModel;
        import javax.swing.SpinnerNumberModel;
        import javax.swing.SwingUtilities;
        import javax.swing.border.BevelBorder;

        import javax.swing.event.ChangeEvent;
        import javax.swing.event.ChangeListener;
        import javax.swing.event.ListSelectionEvent;
        import javax.swing.event.ListSelectionListener;

        public class View  extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
            // Введем сразу имена для кнопок - потом будем их использовать в обработчиках
            
            private static final String MANAGE_BRAND = "manageBrand";
            private static final String CLEAR_BRAND = "clearBrand";

            private static final String INSERT_CAR = "insertCar";
            private static final String UPDATE_CAR = "updateCar";
            private static final String DELETE_CAR = "deleteCar";

            private static final String ALL_CARS = "allCars";
            private static final String ALL_SOLD_CARS = "allSoldCars";
            private static final String NOT_SOLD_CARS = "notSoldCars";
            private static final String ABOUT_US = "aboutUs";
            
            

            private JList brandList;
            private JTable carList;
            private Db db = null;
          
            private JSpinner spYear;
            private AboutDialog dialog;
            private BrandPanel left;
             public View() throws Exception {
                    // Устанавливаем layout для всей клиентской части формы
                    getContentPane().setLayout(new BorderLayout());
                
                    // Создаем строку меню
                    JMenuBar menuBar = new JMenuBar();

                    // Создаем выпадающее меню
                    JMenu menu = new JMenu("Отчеты");

                   // Создаем пункт в выпадающем меню
                    JMenuItem menuItem = new JMenuItem("Все автомобили");
                    menuItem.setName(ALL_CARS);
                    
                    // Добавляем листенер
                    menuItem.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem);
                    
                    JMenuItem menuItem1 = new JMenuItem("Проданые автомобили");
                    menuItem1.setName(ALL_SOLD_CARS);
                    
                    // Добавляем листенер
                    menuItem1.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem1);
                    
                    JMenuItem menuItem2 = new JMenuItem("Непроданые автомобили");
                    menuItem2.setName(NOT_SOLD_CARS);
                    
                    // Добавляем листенер
                    menuItem2.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem2);
                    
                    // Создаем выпадающее меню
                    JMenu menua = new JMenu("About");

                    // Создаем пункт в выпадающем меню
                    JMenuItem menuItema1 = new JMenuItem("About Us");
                    menuItema1.setName(ABOUT_US);
                  
                   // Добавляем листенер
                   menuItema1.addActionListener(this);
                   
                   
                   // Вставляем пункт меню в выпадающее меню
                    menua.add(menuItema1);
                
                    // Вставляем выпадающее меню в строку меню
                    menuBar.add(menu);
                    menuBar.add(menua);
                    // Устанавливаем меню для формы
                    setJMenuBar(menuBar);
                    

                    // Создаем верхнюю панель, где будет поле для ввода года
                    JPanel top = new JPanel();
                    // Устанавливаем для нее layout
                    top.setLayout(new FlowLayout(FlowLayout.LEFT));

                    // Вставляем пояснительную надпись
                    top.add(new JLabel("Год выпуска:"));
                    // Делаем спин-поле
                    // 1. Задаем модель поведения - только цифры
                    // 2. Вставляем в панель
                    SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
                    spYear = new JSpinner(sm);
                    // Добавляем листенер
                    spYear.addChangeListener(this);
                    top.add(spYear);

                    
                    // Создаем нижнюю панель и задаем ей layout
                    JPanel bot = new JPanel();
                    bot.setLayout(new BorderLayout());

                    // Создаем левую панель для вывода списка производителей
                    // Она у нас
                    left= new BrandPanel();
                    // Задаем layout и задаем "бордюр" вокруг панели
                    left.setLayout(new BorderLayout());
                    left.setBorder(new BevelBorder(BevelBorder.LOWERED));

                    // Получаем коннект к базе и создаем объект ManagementSystem
                    db = Db.getInstance();
                    // Получаем список групп
                    Vector<Brand> brand = new Vector<Brand>(db.getBrands());
            
                    // Создаем надпись
                    left.add(new JLabel("Производитель:"), BorderLayout.NORTH);
                    
                    // Создаем визуальный список и вставляем его в скроллируемую
                    // панель, которую в свою очередь уже кладем на панель left
                    brandList = new JList(brand);
                    // Добавляем листенер
                    brandList.addListSelectionListener(this);
                    // Сразу выделяем первого из производителей
                    brandList.setSelectedIndex(0);
                    left.add(new JScrollPane(brandList), BorderLayout.CENTER);
                    
                    // Создаем кнопки для производителей
                    JButton btnMvGr = new JButton("Редактировать");
                    btnMvGr.setName(MANAGE_BRAND);
                    JButton btnClGr = new JButton("Очистить");
                    btnClGr.setName(CLEAR_BRAND);
                    
                    // Добавляем листенер
                    btnMvGr.addActionListener(this);
                    btnClGr.addActionListener(this);
                    
                    // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                    JPanel pnlBtnGr = new JPanel();
                    pnlBtnGr.setLayout(new GridLayout(1, 2));
                    pnlBtnGr.add(btnMvGr);
                    pnlBtnGr.add(btnClGr);
                    left.add(pnlBtnGr, BorderLayout.SOUTH);

            
                    // Создаем правую панель для вывода списка cars
                    JPanel right = new JPanel();
                    
                    // Задаем layout и задаем "бордюр" вокруг панели
                    right.setLayout(new BorderLayout());
                    right.setBorder(new BevelBorder(BevelBorder.LOWERED));

                    // Создаем надпись
                    right.add(new JLabel("Автомобили:"), BorderLayout.NORTH);
                    
                    // Создаем таблицу и вставляем ее в скроллируемую
                    // панель, которую в свою очередь уже кладем на панель right
                    // Наша таблица пока ничего не умеет - просто положим ее как заготовку
                    // Сделаем в ней 4 колонки 
                    carList = new JTable(1, 4);
                    right.add(new JScrollPane(carList), BorderLayout.CENTER);
                    
                    // Создаем кнопки для автомобилей
                    JButton btnAddSt = new JButton("Добавить");
                    btnAddSt.setName(INSERT_CAR);
                    btnAddSt.addActionListener(this);
                    JButton btnUpdSt = new JButton("Исправить");
                    btnUpdSt.setName(UPDATE_CAR);
                    btnUpdSt.addActionListener(this);
                    JButton btnDelSt = new JButton("Удалить");
                    btnDelSt.setName(DELETE_CAR);
                    btnDelSt.addActionListener(this);
                    
                    // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                    JPanel pnlBtnSt = new JPanel();
                    pnlBtnSt.setLayout(new GridLayout(1, 3));
                    pnlBtnSt.add(btnAddSt);
                    pnlBtnSt.add(btnUpdSt);
                    pnlBtnSt.add(btnDelSt);
                    right.add(pnlBtnSt, BorderLayout.SOUTH);

                    // Вставляем панели со списками производителей и автомобилей в нижнюю панель
                    bot.add(left, BorderLayout.WEST);
                    bot.add(right, BorderLayout.CENTER);

                    // Вставляем верхнюю и нижнюю панели в форму
                    getContentPane().add(top, BorderLayout.NORTH);
                    getContentPane().add(bot, BorderLayout.CENTER);

                    // Задаем границы формы
                    setBounds(200, 200, 700, 500);
                }
                
                // Метод для обеспечения интерфейса ChangeListener
                public void stateChanged(ChangeEvent e) {
                    reloadCars();
                }
                
                // Метод для обеспечения интерфейса ActionListener
                
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof Component) {
                        Component c = (Component) e.getSource();

                        if (c.getName().equals(ALL_SOLD_CARS)) {
                            showSoldCars();
                        }
                        if (c.getName().equals(NOT_SOLD_CARS)) {
                            showNotSoldCars();
                        }
                        if (c.getName().equals(ALL_CARS)) {
                            showAllCars();
                        }
                        if (c.getName().equals(ABOUT_US)) {
                            showAbouUs();
                            
                        }
                        if (c.getName().equals(MANAGE_BRAND)) {
                            manageBrand();
                        }
                        if (c.getName().equals(CLEAR_BRAND)) {
                            clearBrand();
                        }
                        if (c.getName().equals(INSERT_CAR)) {
                            insertCar();
                        }
                        if (c.getName().equals(UPDATE_CAR)) {
                            updateCar();
                        }
                        if (c.getName().equals(DELETE_CAR)) {
                            deleteCar();
                        }
                    }
                }
             
             
                // Метод для обеспечения интерфейса ListSelectionListener
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        reloadCars();
                    }
                }
                   
            
                // метод для обновления списка автомобилей для определенного производителя
                public void reloadCars() {
            
                // Создаем анонимный класс для потока
                Thread t = new Thread() {
                    // Переопределяем в нем метод run

                    public void run() {
                        if (carList != null) {
                            // Получаем визуальный список
                            Brand g = (Brand) brandList.getSelectedValue();
                            // Получаем число из спинера
                            int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
                            try {
                                // Получаем список автомобилей
                                Collection<Car> s = db.getCarsFromBrand(g, y);
                                // И устанавливаем модель для таблицы с новыми данными
                                carList.setModel(new CarTableModel(new Vector<Car>(s)));
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(View.this, e.getMessage());
                            }
                        }
                    }
                    // Окончание нашего метода run
                };
                // Окончание определения анонимного класса

                // И теперь мы запускаем наш поток
                t.start();
            }


                
             // метод для обновления списка 
                public void reloadBrands() {
                    // Создаем анонимный класс для потока
                    Thread t = new Thread() {
                        // Переопределяем в нем метод run
                        public void run() {
                            if (brandList != null) {
                                // Получаем визуальный список
                                try {
                                    Vector<Brand> brand = new Vector<Brand>(db.getBrands());
                                    // Обновить список
                                    // 
                                    brandList.setListData(brand);
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                                }
                            }
                        }
                        // Окончание нашего метода run
                    };
                    // Окончание определения анонимного класса

                    // И теперь мы запускаем наш поток
                    t.start();
                }

                // метод для показа всех автомобилей
                private void showAllCars() {
                    JOptionPane.showMessageDialog(this, "showAllCars");
                }
                
                private void showSoldCars() {
                    JOptionPane.showMessageDialog(this, "show All Sold Cars");
                }

                private void showNotSoldCars() {
                    JOptionPane.showMessageDialog(this, "show Not Sold Cars");
                }
                
                private void showAbouUs() {
                    //
                    if(dialog == null) // в первый раз
                        dialog = new AboutDialog(View.this);
                    dialog.setVisible(true); // отобразить диалог
                }
                
               // метод для переноса производителей
                private void manageBrand() {

                    Thread t = new Thread() {

                    public void run() {
                     try {
                    
                    BrandDialog brand = new BrandDialog(db.getBrands(),View.this);
                    // Задаем ему режим модальности - нельзя ничего кроме него выделить
                    brand.setModal(true);
                    // Показываем диалог
                    brand.setVisible(true);
                    if (brand.getResult()) {
                        Brand s = brand.getBrand();
                        db.insertBrand(s);
                        reloadBrands();
                    }
                     
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                }
                    
                        }
                    };
                    t.start();
                }

                // метод для очистки производителей
                private void clearBrand() {
                    JOptionPane.showMessageDialog(this, "Clear Brand");
                }
                
             // метод для добавления автомобиля
                private void insertCar() {
                    Thread t = new Thread() {

                        public void run() {
                            try {
                                // Добавляем 
                                
                                CarDialog car = new CarDialog(db.getBrands(), true, View.this);
                                car.setModal(true);
                                car.setVisible(true);
                                if (car.getResult()) {
                                    Car c = car.getCar();
                                    db.insertCar(c);
                                    reloadCars();
                                }
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(View.this, e.getMessage());
                            }
                        }
                    };
                    t.start();
                }

                // метод для редактирования автомобиля
                private void updateCar() {
                    
                        Thread t = new Thread() {

                            public void run() {
                                if (carList != null) {
                                    CarTableModel stm = (CarTableModel) carList.getModel();
                                    // Проверяем - выделен ли хоть какой-нибудь автомобил
                                    if (carList.getSelectedRow() >= 0) {
                                        // Вот где нам пригодился метод getCar(int rowIndex)
                                        Car s = stm.getCar(carList.getSelectedRow());
                                        try {
                                            // Исправляем данные на автомобиля - поэтому false
                                            // Также заметим, что необходимо указать не просто this, а View.this
                                            // Иначе класс не будет воспринят - он же другой - анонимный
                                            CarDialog cd = new CarDialog(db.getBrands(), false, View.this);
                                            cd.setCar(s);
                                            cd.setModal(true);
                                            cd.setVisible(true);
                                            if (cd.getResult()) {
                                                Car us = cd.getCar();
                                                db.updateCar(us);
                                                reloadCars();
                                            }
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(View.this, e.getMessage());
                                        }
                                    } // Если автомобиль не выделен - сообщаем пользователю, что это необходимо
                                    else {
                                        JOptionPane.showMessageDialog(View.this,
                                                "Необходимо выделить автомобиль в списке");
                                    }
                                }
                            }
                        };
                        t.start();
                    }


                // метод для удаления автомобиля
                private void deleteCar() {
                    JOptionPane.showMessageDialog(this, "Delete Car");
                }

        }

            
                public static void main(String args[]) {
                
                    SwingUtilities.invokeLater(new Runnable() {

                        public void run() {
                            try {
                                View sf = new View();
                                sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                sf.setVisible(true);
                                
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    });
                }
        }

        //Наш внутренний класс - переопределенная панель.
        class BrandPanel extends JPanel {

         public Dimension getPreferredSize() {
             return new Dimension(250, 0);
         }


04 метод для удаления автомобиля
--------------------------------

class Db
--------
            package vehicle;

            import java.sql.Connection;
            import java.sql.Date;
            import java.sql.DriverManager;
            import java.sql.PreparedStatement;
            import java.sql.ResultSet;
            import java.sql.SQLException;
            import java.sql.Statement;
            import java.util.ArrayList;
            import java.util.Collection;
            import java.util.List;

            public class Db {
                private static Connection con;
                private static Db instance;

                private Db() throws Exception {
                    try {
                        Class.forName("org.sqlite.JDBC");
                        String url = "jdbc:sqlite:vehicle.db";
                        con = DriverManager.getConnection(url);
                    } catch (ClassNotFoundException e) {
                        throw new Exception(e);
                    } catch (SQLException e) {
                        throw new Exception(e);
                    }
                }

                public static synchronized Db getInstance() throws Exception {
                    if (instance == null) {
                        instance = new Db();
                    }
                    return instance;
                }

                public List<Brand> getBrands() throws SQLException {
                    List<Brand> brands = new ArrayList<Brand>();

                    Statement stmt = null;
                    ResultSet rs = null;
                    try {
                        stmt = con.createStatement();
                        rs = stmt.executeQuery("SELECT brand_id, brandName FROM brands");
                        while (rs.next()) {
                            Brand brand = new Brand(rs);
                            brand.setBrandId(rs.getInt(1));
                            brand.setNameBrand(rs.getString(2));
                            

                            brands.add(brand);
                        }
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                    }
                    return brands;
                }
                public Collection<Car> getAllCars() throws SQLException {
                    Collection<Car> cars = new ArrayList<Car>();

                    Statement stmt = null;
                    ResultSet rs = null;
                    try {
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(
                                "SELECT * FROM cars " +
                                "ORDER BY model");
                        while (rs.next()) {
                            Car car = new Car(rs);
                            cars.add(car);
                        }
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                    }

                    return cars;
                }
                
                public void insertBrand(Brand brand) throws SQLException {
                    PreparedStatement stmt = null;
                    try {
                        stmt = con.prepareStatement("INSERT INTO brands (brandName) VALUES (?)");
                        stmt.setString(1, brand.getNameBrand());
                        
                        stmt.execute();
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                    }
                }
                
                public void insertCar(Car car) throws SQLException {
                    PreparedStatement stmt = null;
                    try {
                        stmt = con.prepareStatement(
                                "INSERT INTO cars " +
                                "(model, brandId, price, builtDate, gear, seats, wheels, miles, capacity, sold, soldOn ) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                        
                        stmt.setString(1, car.getModel());
                        stmt.setInt(2, car.getBrandId());
                        stmt.setDouble(3, car.getPrice());
                        stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
                        stmt.setInt(5, car.getGeer());
                        stmt.setInt(6, car.getSeats());
                        stmt.setInt(7, car.getWheels());
                        stmt.setInt(8, car.getMiles());
                        stmt.setInt(9, car.getCapacity());
                        stmt.setString(10, new String(new char[]{car.getSold()}));
                        stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                                    
                        stmt.execute();
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                    }
                }
                
                public Collection<Car> getCarsFromBrand(Brand brand, int year) throws SQLException {
                    Collection<Car> cars = new ArrayList<Car>();

                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        stmt = con.prepareStatement(
                                "SELECT * FROM cars " +
                                "WHERE brandId=? " +
                                "ORDER BY model");
                        stmt.setInt(1, brand.getBrandId());
                        //stmt.setInt(2, year);
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                            Car car = new Car(rs);

                            cars.add(car);
                        }
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                    }

                    return cars;
                }
                
                public void updateCar(Car car) throws SQLException {
                    PreparedStatement stmt = null;
                    try {
                        stmt = con.prepareStatement(
                                "UPDATE cars SET " +
                                "model=?, brandId=?, price=?, builtDate=?, gear=?, seats=?, wheels=?, miles=?, capacity=?, sold=?, soldOn=? " +
                                "WHERE carId=?");
                        
                        stmt.setString(1, car.getModel());
                        stmt.setInt(2, car.getBrandId());
                        stmt.setDouble(3, car.getPrice());
                        stmt.setDate(4, new Date(car.getBuiltDate().getTime()));
                        stmt.setInt(5, car.getGeer());
                        stmt.setInt(6, car.getSeats());
                        stmt.setInt(7, car.getWheels());
                        stmt.setInt(8, car.getMiles());
                        stmt.setInt(9, car.getCapacity());
                        stmt.setString(10, new String(new char[]{car.getSold()}));
                        stmt.setDate(11, new Date(car.getDateSoldOn().getTime()));
                        stmt.setInt(12, car.getCarId());
                        
                        stmt.execute();
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                    }
                }
                
                public void deleteCar(Car car) throws SQLException {
                    PreparedStatement stmt = null;
                    try {
                        stmt = con.prepareStatement(
                                "DELETE FROM cars WHERE carId=?");
                        stmt.setInt(1, car.getCarId());
                        stmt.execute();
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                    }
                }
            }

class View
----------

        package vehicle;

        import java.sql.SQLException;
        import java.util.Vector;
        import java.awt.Dimension;
        import java.awt.FlowLayout;
        import java.awt.BorderLayout;
        import java.awt.Component;
        import java.awt.GridLayout;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.Collection;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JLabel;

        import javax.swing.JMenu;
        import javax.swing.JMenuBar;
        import javax.swing.JMenuItem;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;

        import javax.swing.JList;

        import javax.swing.JSpinner;
        import javax.swing.SpinnerModel;
        import javax.swing.SpinnerNumberModel;
        import javax.swing.SwingUtilities;
        import javax.swing.border.BevelBorder;

        import javax.swing.event.ChangeEvent;
        import javax.swing.event.ChangeListener;
        import javax.swing.event.ListSelectionEvent;
        import javax.swing.event.ListSelectionListener;

        public class View  extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
            // Введем сразу имена для кнопок - потом будем их использовать в обработчиках
            
            private static final String MANAGE_BRAND = "manageBrand";
            private static final String CLEAR_BRAND = "clearBrand";

            private static final String INSERT_CAR = "insertCar";
            private static final String UPDATE_CAR = "updateCar";
            private static final String DELETE_CAR = "deleteCar";

            private static final String ALL_CARS = "allCars";
            private static final String ALL_SOLD_CARS = "allSoldCars";
            private static final String NOT_SOLD_CARS = "notSoldCars";
            private static final String ABOUT_US = "aboutUs";
            
            

            private JList brandList;
            private JTable carList;
            private Db db = null;
          
            private JSpinner spYear;
            private AboutDialog dialog;
            private BrandPanel left;
             public View() throws Exception {
                    // Устанавливаем layout для всей клиентской части формы
                    getContentPane().setLayout(new BorderLayout());
                
                    // Создаем строку меню
                    JMenuBar menuBar = new JMenuBar();

                    // Создаем выпадающее меню
                    JMenu menu = new JMenu("Отчеты");

                   // Создаем пункт в выпадающем меню
                    JMenuItem menuItem = new JMenuItem("Все автомобили");
                    menuItem.setName(ALL_CARS);
                    
                    // Добавляем листенер
                    menuItem.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem);
                    
                    JMenuItem menuItem1 = new JMenuItem("Проданые автомобили");
                    menuItem1.setName(ALL_SOLD_CARS);
                    
                    // Добавляем листенер
                    menuItem1.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem1);
                    
                    JMenuItem menuItem2 = new JMenuItem("Непроданые автомобили");
                    menuItem2.setName(NOT_SOLD_CARS);
                    
                    // Добавляем листенер
                    menuItem2.addActionListener(this);
                    
                    // Вставляем пункт меню в выпадающее меню
                    menu.add(menuItem2);
                    
                    // Создаем выпадающее меню
                    JMenu menua = new JMenu("About");

                    // Создаем пункт в выпадающем меню
                    JMenuItem menuItema1 = new JMenuItem("About Us");
                    menuItema1.setName(ABOUT_US);
                  
                   // Добавляем листенер
                   menuItema1.addActionListener(this);
                   
                   
                   // Вставляем пункт меню в выпадающее меню
                    menua.add(menuItema1);
                
                    // Вставляем выпадающее меню в строку меню
                    menuBar.add(menu);
                    menuBar.add(menua);
                    // Устанавливаем меню для формы
                    setJMenuBar(menuBar);
                    

                    // Создаем верхнюю панель, где будет поле для ввода года
                    JPanel top = new JPanel();
                    // Устанавливаем для нее layout
                    top.setLayout(new FlowLayout(FlowLayout.LEFT));

                    // Вставляем пояснительную надпись
                    top.add(new JLabel("Год выпуска:"));
                    // Делаем спин-поле
                    // 1. Задаем модель поведения - только цифры
                    // 2. Вставляем в панель
                    SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
                    spYear = new JSpinner(sm);
                    // Добавляем листенер
                    spYear.addChangeListener(this);
                    top.add(spYear);

                    
                    // Создаем нижнюю панель и задаем ей layout
                    JPanel bot = new JPanel();
                    bot.setLayout(new BorderLayout());

                    // Создаем левую панель для вывода списка производителей
                    // Она у нас
                    left= new BrandPanel();
                    // Задаем layout и задаем "бордюр" вокруг панели
                    left.setLayout(new BorderLayout());
                    left.setBorder(new BevelBorder(BevelBorder.LOWERED));

                    // Получаем коннект к базе и создаем объект ManagementSystem
                    db = Db.getInstance();
                    // Получаем список групп
                    Vector<Brand> brand = new Vector<Brand>(db.getBrands());
            
                    // Создаем надпись
                    left.add(new JLabel("Производитель:"), BorderLayout.NORTH);
                    
                    // Создаем визуальный список и вставляем его в скроллируемую
                    // панель, которую в свою очередь уже кладем на панель left
                    brandList = new JList(brand);
                    // Добавляем листенер
                    brandList.addListSelectionListener(this);
                    // Сразу выделяем первого из производителей
                    brandList.setSelectedIndex(0);
                    left.add(new JScrollPane(brandList), BorderLayout.CENTER);
                    
                    // Создаем кнопки для производителей
                    JButton btnMvGr = new JButton("Редактировать");
                    btnMvGr.setName(MANAGE_BRAND);
                    JButton btnClGr = new JButton("Очистить");
                    btnClGr.setName(CLEAR_BRAND);
                    
                    // Добавляем листенер
                    btnMvGr.addActionListener(this);
                    btnClGr.addActionListener(this);
                    
                    // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                    JPanel pnlBtnGr = new JPanel();
                    pnlBtnGr.setLayout(new GridLayout(1, 2));
                    pnlBtnGr.add(btnMvGr);
                    pnlBtnGr.add(btnClGr);
                    left.add(pnlBtnGr, BorderLayout.SOUTH);

            
                    // Создаем правую панель для вывода списка cars
                    JPanel right = new JPanel();
                    
                    // Задаем layout и задаем "бордюр" вокруг панели
                    right.setLayout(new BorderLayout());
                    right.setBorder(new BevelBorder(BevelBorder.LOWERED));

                    // Создаем надпись
                    right.add(new JLabel("Автомобили:"), BorderLayout.NORTH);
                    
                    // Создаем таблицу и вставляем ее в скроллируемую
                    // панель, которую в свою очередь уже кладем на панель right
                    // Наша таблица пока ничего не умеет - просто положим ее как заготовку
                    // Сделаем в ней 4 колонки 
                    carList = new JTable(1, 4);
                    right.add(new JScrollPane(carList), BorderLayout.CENTER);
                    
                    // Создаем кнопки для автомобилей
                    JButton btnAddSt = new JButton("Добавить");
                    btnAddSt.setName(INSERT_CAR);
                    btnAddSt.addActionListener(this);
                    JButton btnUpdSt = new JButton("Исправить");
                    btnUpdSt.setName(UPDATE_CAR);
                    btnUpdSt.addActionListener(this);
                    JButton btnDelSt = new JButton("Удалить");
                    btnDelSt.setName(DELETE_CAR);
                    btnDelSt.addActionListener(this);
                    
                    // Создаем панель, на которую положим наши кнопки и кладем ее вниз
                    JPanel pnlBtnSt = new JPanel();
                    pnlBtnSt.setLayout(new GridLayout(1, 3));
                    pnlBtnSt.add(btnAddSt);
                    pnlBtnSt.add(btnUpdSt);
                    pnlBtnSt.add(btnDelSt);
                    right.add(pnlBtnSt, BorderLayout.SOUTH);

                    // Вставляем панели со списками производителей и автомобилей в нижнюю панель
                    bot.add(left, BorderLayout.WEST);
                    bot.add(right, BorderLayout.CENTER);

                    // Вставляем верхнюю и нижнюю панели в форму
                    getContentPane().add(top, BorderLayout.NORTH);
                    getContentPane().add(bot, BorderLayout.CENTER);

                    // Задаем границы формы
                    setBounds(200, 200, 700, 500);
                }
                
                // Метод для обеспечения интерфейса ChangeListener
                public void stateChanged(ChangeEvent e) {
                    reloadCars();
                }
                
                // Метод для обеспечения интерфейса ActionListener
                
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof Component) {
                        Component c = (Component) e.getSource();

                        if (c.getName().equals(ALL_SOLD_CARS)) {
                            showSoldCars();
                        }
                        if (c.getName().equals(NOT_SOLD_CARS)) {
                            showNotSoldCars();
                        }
                        if (c.getName().equals(ALL_CARS)) {
                            showAllCars();
                        }
                        if (c.getName().equals(ABOUT_US)) {
                            showAbouUs();
                            
                        }
                        if (c.getName().equals(MANAGE_BRAND)) {
                            manageBrand();
                        }
                        if (c.getName().equals(CLEAR_BRAND)) {
                            clearBrand();
                        }
                        if (c.getName().equals(INSERT_CAR)) {
                            insertCar();
                        }
                        if (c.getName().equals(UPDATE_CAR)) {
                            updateCar();
                        }
                        if (c.getName().equals(DELETE_CAR)) {
                            deleteCar();
                        }
                    }
                }
             
             
                // Метод для обеспечения интерфейса ListSelectionListener
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        reloadCars();
                    }
                }
                   
            
                // метод для обновления списка автомобилей для определенного производителя
                public void reloadCars() {
            
                // Создаем анонимный класс для потока
                Thread t = new Thread() {
                    // Переопределяем в нем метод run

                    public void run() {
                        if (carList != null) {
                            // Получаем визуальный список
                            Brand g = (Brand) brandList.getSelectedValue();
                            // Получаем число из спинера
                            int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
                            try {
                                // Получаем список автомобилей
                                Collection<Car> s = db.getCarsFromBrand(g, y);
                                // И устанавливаем модель для таблицы с новыми данными
                                carList.setModel(new CarTableModel(new Vector<Car>(s)));
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(View.this, e.getMessage());
                            }
                        }
                    }
                    // Окончание нашего метода run
                };
                // Окончание определения анонимного класса

                // И теперь мы запускаем наш поток
                t.start();
            }


                
             // метод для обновления списка 
                public void reloadBrands() {
                    // Создаем анонимный класс для потока
                    Thread t = new Thread() {
                        // Переопределяем в нем метод run
                        public void run() {
                            if (brandList != null) {
                                // Получаем визуальный список
                                try {
                                    Vector<Brand> brand = new Vector<Brand>(db.getBrands());
                                    // Обновить список
                                    // 
                                    brandList.setListData(brand);
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                                }
                            }
                        }
                        // Окончание нашего метода run
                    };
                    // Окончание определения анонимного класса

                    // И теперь мы запускаем наш поток
                    t.start();
                }

                // метод для показа всех автомобилей
                private void showAllCars() {
                    JOptionPane.showMessageDialog(this, "showAllCars");
                }
                
                private void showSoldCars() {
                    JOptionPane.showMessageDialog(this, "show All Sold Cars");
                }

                private void showNotSoldCars() {
                    JOptionPane.showMessageDialog(this, "show Not Sold Cars");
                }
                
                private void showAbouUs() {
                    //
                    if(dialog == null) // в первый раз
                        dialog = new AboutDialog(View.this);
                    dialog.setVisible(true); // отобразить диалог
                }
                
               // метод для переноса производителей
                private void manageBrand() {

                    Thread t = new Thread() {

                    public void run() {
                     try {
                    
                    BrandDialog brand = new BrandDialog(db.getBrands(),View.this);
                    // Задаем ему режим модальности - нельзя ничего кроме него выделить
                    brand.setModal(true);
                    // Показываем диалог
                    brand.setVisible(true);
                    if (brand.getResult()) {
                        Brand s = brand.getBrand();
                        db.insertBrand(s);
                        reloadBrands();
                    }
                     
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                }
                    
                        }
                    };
                    t.start();
                }

                // метод для очистки производителей
                private void clearBrand() {
                    JOptionPane.showMessageDialog(this, "Clear Brand");
                }
                
             // метод для добавления автомобиля
                private void insertCar() {
                    Thread t = new Thread() {

                        public void run() {
                            try {
                                // Добавляем 
                                
                                CarDialog car = new CarDialog(db.getBrands(), true, View.this);
                                car.setModal(true);
                                car.setVisible(true);
                                if (car.getResult()) {
                                    Car c = car.getCar();
                                    db.insertCar(c);
                                    reloadCars();
                                }
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(View.this, e.getMessage());
                            }
                        }
                    };
                    t.start();
                }

                // метод для редактирования автомобиля
                private void updateCar() {
                    
                        Thread t = new Thread() {

                            public void run() {
                                if (carList != null) {
                                    CarTableModel stm = (CarTableModel) carList.getModel();
                                    // Проверяем - выделен ли хоть какой-нибудь автомобил
                                    if (carList.getSelectedRow() >= 0) {
                                        // Вот где нам пригодился метод getCar(int rowIndex)
                                        Car s = stm.getCar(carList.getSelectedRow());
                                        try {
                                            // Исправляем данные на автомобиля - поэтому false
                                            // Также заметим, что необходимо указать не просто this, а View.this
                                            // Иначе класс не будет воспринят - он же другой - анонимный
                                            CarDialog cd = new CarDialog(db.getBrands(), false, View.this);
                                            cd.setCar(s);
                                            cd.setModal(true);
                                            cd.setVisible(true);
                                            if (cd.getResult()) {
                                                Car us = cd.getCar();
                                                db.updateCar(us);
                                                reloadCars();
                                            }
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(View.this, e.getMessage());
                                        }
                                    } // Если автомобиль не выделен - сообщаем пользователю, что это необходимо
                                    else {
                                        JOptionPane.showMessageDialog(View.this,
                                                "Необходимо выделить автомобиль в списке");
                                    }
                                }
                            }
                        };
                        t.start();
                    }


                // метод для удаления автомобиля
                private void deleteCar() {
                    
                            Thread t = new Thread() {

                                public void run() {
                                    if (carList != null) {
                                        CarTableModel stm = (CarTableModel) carList.getModel();
                                        // Проверяем - выделен ли хоть какой-нибудь автомобиль
                                        if (carList.getSelectedRow() >= 0) {
                                            if (JOptionPane.showConfirmDialog(View.this,
                                                    "Вы хотите удалить автомобиль?", "Удаление автомобиля",
                                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                                // Вот где нам пригодился метод getCar(int rowIndex)
                                                Car s = stm.getCar(carList.getSelectedRow());
                                                try {
                                                    db.deleteCar(s);
                                                    reloadCars();
                                                } catch (SQLException e) {
                                                    JOptionPane.showMessageDialog(View.this, e.getMessage());
                                                }
                                            }
                                        } // Если автомобиль не выделен - сообщаем пользователю, что это необходимо
                                        else {
                                            JOptionPane.showMessageDialog(View.this, "Необходимо выделить автомобиль в списке");
                                        }
                                    }
                                }
                            };
                            t.start();
                        }


            
                public static void main(String args[]) {
                
                    SwingUtilities.invokeLater(new Runnable() {

                        public void run() {
                            try {
                                View sf = new View();
                                sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                sf.setVisible(true);
                                
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    });
                }
        }

        //Наш внутренний класс - переопределенная панель.
        class BrandPanel extends JPanel {

         public Dimension getPreferredSize() {
             return new Dimension(250, 0);
         }
        }