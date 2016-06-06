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
            JOptionPane.showMessageDialog(this, "Add Car");
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