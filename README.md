# java Unit 14

Обработка событий в Swing
-------------------------
Графический интерфейс пользователя включает в себя не только расположение в окне необходимых элементов управления, но и назначение реакции на пользовательские события. Большая честь действий в оконных программах выполняется в ответ на выбор пользователем команд меню, нажатие кнопок, а иногда даже просто в ответ на ввод нового символа в текстовое поле.

Таким образом, при разработке программы необходимо:

Выявить события, в ответ на которые потребуется реакция программы.
Написать код, реализующий эту реакцию (так называемый обработчик событий).
Связать обработчик события с соответствующим событием.

Обработчик события — это, как правило, обычный метод и ничего особенного в его написании нет.

Паттерн проектирования «наблюдатель»

В библиотеке Swing для привязки обработчика события к вызывающему его компоненту используется паттерн проектирования «наблюдатель».

Паттерны проектирования — это стандартные приемы объектно-ориентированного программирования, позволяющие оптимальным образом справиться с нетривиальными, но часто возникающими в программировании ситуации. Паттерн проектирования описывает классы, которые необходимо ввести для разрешения такой ситуации и взаимодействие между классами. 

Паттерн проектирования «наблюдатель» применяется, когда один объект должен оповещать другие о произошедших с ним изменениях или внешних воздействиях. Такой объект называется наблюдаемым, а объекты, которые следует оповестить — наблюдателями.

Для того, чтобы подобное взаимодействие было возможным, объект-наблюдатель должен иметь метод (или несколько методов) с заранее определенной сигнатурой (именем и параметрами). Когда с наблюдаемым объектом произойдет ожидаемое событие, он вызовет соответствующий метод у своего наблюдателя. В этом методе и будет запрограммирована реакция на событие.

Для того, чтобы наблюдаемый объект мог вызвать метод наблюдателя, он должен знать о том, что такой наблюдатель существует. Поэтому у наблюдаемого предварительно должен быть вызван специальный метод, регистрирующий его наблюдателя.

в данной схеме один наблюдатель может быть зарегистрирован у нескольких объектов (т.е. одинаково реагировать на изменения в каждом из них), а у одного объекта может быть несколько наблюдателей (т.е. при возникновении события выполняется несколько независимых методов-обработчиков). Это весьма увеличивает гибкость программирования.

Механизм обработки событий библиотеки Swing
В контексте графического интерфейса пользователя наблюдаемыми объектами являются элементы управления: кнопки, флажки, меню и т.д. Они могут сообщить своим наблюдателям об определенных событиях, как элементарных (наведение мышкой, нажатие клавиши на клавиатуре), так и о высокоуровневых (изменение текста в текстовом поле, выбор нового элемента в выпадающем списке и т.д.).

Наблюдателями должны являться объекты классов, поддерживающих специальные интерфейсы (вспомним, что в классе наблюдателя должны быть определенные методы, о которых «знает» наблюдаемый и вызывает их при наступлении события). Такие классы в терминологии Swing называются слушателями.


Универсальный слушатель ActionListener

Среди многочисленных событий, на которые реагирует каждый элемент управления (и о которых он оповещает соответствующих слушателей, если они к нему присоединены), есть одно основное, вытекающее из самой сути компонента и обрабатываемое значительно чаще, чем другие. Например, для кнопки это щелчок на ней, а для выпадающего списка — выбор нового элемента.

Для отслеживания и обработки такого события может быть использован особый слушатель ActionListener, имеющий один метод:

    public void actionPerformed(ActionEvent event).

У использования ActionListener есть небольшое преимущество в эффективности (так, при обработке нажатия на кнопку не надо реагировать на четыре лишних события — ведь даже если методы-обработчики пустые, на вызов этих методов все равно тратятся ресурсы). А кроме того очень удобно запомнить и постоянно использовать один класс с одним методам и обращаться к остальным лишь в тех относительно редких случаях, когда возникнет такая необходимость.


Слушатель изменения состояния ChangeListener

Слушатель ChangeListener реагирует на изменение состояния объекта. Каждый элемент управления по своему определяет понятие «изменение состояния». Например, для панели со вкладками JTabbedPane это переход на другую вкладку, для ползунка JSlider — изменение его положения, кнопка JButton рассматривает как смену состояния щелчок на ней. Таким образом, хотя событие это достаточно общее, необходимо уточнять его специфику для каждого конкретного компонента. В интерфейсе определен всего один метод:

        public void stateChanged(ChangeEvent event).


        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import javax.swing.event.ChangeEvent;
        import javax.swing.event.ChangeListener;

        public class View  extends JFrame implements ActionListener, ChangeListener {

                // Метод для обеспечения интерфейса ActionListener
                public void actionPerformed(ActionEvent e) {
                 
                }

                
                // Метод для обеспечения интерфейса ChangeListener
                public void stateChanged(ChangeEvent e) {

                }
            }


Работа с меню в библиотеке Swing
--------------------------------
Неотъемлемой частью современных оконных программ является меню, представляющее собой удобно сгруппированный набор команд. Меню бывает двух типов: главное и контекстное. Главное меню располагается вдоль верхней границы окна и содержит команды, относящиеся ко всему приложению (точнее, все команды, которые можно выполнить, работая с данным окном). Контекстное меню вызывается нажатием правой кнопки мышки на конкретном объекте и содержит команды, которые могут быть применены именно к этому объекту.

Создание главного меню
Главное меню окна представлено в Swing классом JMenuBar. По сути своей этот класс представляет собой панель с менеджером расположения BoxLayout (по горизонтали), в которую можно добавлять не только элементы меню, но и что угодно: хоть выпадающие списки, хоть панели с закладками. Однако для удобства пользования программой предпочтительнее использовать «традиционные» возможности меню.

Главное меню должно быть присоединено к окну методом setJMenuBar(JMenuBar menuBar).

Элементами главного меню являются обычные меню — выпадающие прямоугольные блоки команд — объекты классаJMenu. Конструктор JMenu(String title) принимает один параметр: название меню, которое будет отображаться в строке главного меню.*

Меню, в свою очередь, состоит из пунктов меню, представленных классом JMenuItem. По логике работы пункты меню аналогичны кнопке JButton, то есть, при нажатии на него пользователем выполняется какое-то действие.

об особых разновидностях пунктов меню
У класса JMenuItem есть наследники. Класс JCheckBoxMenuItem по логике работы аналогичен флажку JCheckBox — то есть, прямо в меню можно установить или сбросить какой-либо параметр логического типа. Класс JRadioButtonMenuItemэмулирует переключатель, позволяя выбрать только одну из нескольких альтернатив.

Создать элемент меню можно пустым конструктором JMenuItem либо (что более востребовано) одним из конструкторов, в которые передается текст и/или значок элемента меню: JMenuItem(String text), JMenuItem(Icon icon),JMenuItem(String text, Icon icon). Конечно, в любой момент текст и значок можно сменить методамиsetText(String text) и setIcon(Icon icon) соответственно.

Элемент добавляется в меню методом add(JMenuItem item) класса JMenu. Чтобы отделить группы взаимосвязанных элементов меню, можно добавить между ними разделитель методом addSeparator() класса JMenu.

Кроме того, в меню можно добавить и другое меню. В этом случае образуется последовательность вложенных друг в друга подменю, что довольно часто встречается в современных программах. Но не следует увлекаться: глубина вложенности более трех уровней скорее всего приведет к неудобствам пользования программой.

Создадим главное меню окна, состоящее из двух подменю: «Отчеты» и «About»

            package vehicle;

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

            import javax.swing.JSpinner;
            import javax.swing.SpinnerModel;
            import javax.swing.SpinnerNumberModel;
            import javax.swing.SwingUtilities;
            import javax.swing.border.BevelBorder;

            import javax.swing.event.ChangeEvent;
            import javax.swing.event.ChangeListener;

            public class View  extends JFrame implements ActionListener, ChangeListener {

                private JSpinner spYear;
                
                 public View() throws Exception {
                        // Устанавливаем layout для всей клиентской части формы
                        getContentPane().setLayout(new BorderLayout());
                    
                        // Создаем строку меню
                        JMenuBar menuBar = new JMenuBar();

                        // Создаем выпадающее меню
                        JMenu menu = new JMenu("Отчеты");

                       // Создаем пункт в выпадающем меню
                        JMenuItem menuItem = new JMenuItem("Все автомобили");
                       
                        // Вставляем пункт меню в выпадающее меню
                        menu.add(menuItem);
                        
                        JMenuItem menuItem1 = new JMenuItem("Проданые автомобили");
                        
                        // Вставляем пункт меню в выпадающее меню
                        menu.add(menuItem1);
                        
                        JMenuItem menuItem2 = new JMenuItem("Непроданые автомобили");
                        
                        // Вставляем пункт меню в выпадающее меню
                        menu.add(menuItem2);
                        
                        // Создаем выпадающее меню
                        JMenu menua = new JMenu("About");

                        // Создаем пункт в выпадающем меню
                        JMenuItem menuItema1 = new JMenuItem("About Us");
                        //menuItem.setName(ALL_CARS);
                        
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

                        // Вставляем верхнюю и нижнюю панели в форму
                        getContentPane().add(top, BorderLayout.NORTH);

                        // Задаем границы формы
                        setBounds(100, 100, 700, 500);
                    }
                    // Метод для обеспечения интерфейса ActionListener
                    public void actionPerformed(ActionEvent e) {
                    }

                    // Метод для обеспечения интерфейса ChangeListener
                    public void stateChanged(ChangeEvent e) {

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


Обработка команд меню

Каждый элемент меню по сути представляет собой кнопку, поэтому реагирует на те же события и позволяет присоединять к себе тех же слушателей, что и кнопка. Поэтому мы можем назначить реакцию на команды меню. 

Введем сразу имена для кнопок - потом будем их использовать в обработчиках

    private static final String ALL_CARS = "allCars";
    private static final String ALL_SOLD_CARS = "allSoldCars";
    private static final String NOT_SOLD_CARS = "notSoldCars";
    private static final String ABOUT_US = "aboutUs";


Метод для обеспечения интерфейса ActionListener
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof Component) {
                Component c = (Component) e.getSource();

                if (c.getName().equals(ALL_SOLD_CARS)) {

                }
                if (c.getName().equals(NOT_SOLD_CARS)) {

                }
                if (c.getName().equals(ALL_CARS)) {

                }
                if (c.getName().equals(ABOUT_US)) {

                }
            }
        }


Добавляем листенер

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


Метод для обеспечения интерфейса ActionListener
        
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
            }
        }


Вызываемые методы

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
            JOptionPane.showMessageDialog(this, "About Us");
        }


Создаем правую панель для вывода списка cars


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

Создаем нижнюю панель и задаем ей layout
  
            JPanel bot = new JPanel();
            bot.setLayout(new BorderLayout());

Создаем кнопки для автомобилей

            JButton btnAddSt = new JButton("Добавить");
            btnAddSt.setName(INSERT_CAR);
            btnAddSt.addActionListener(this);
            JButton btnUpdSt = new JButton("Исправить");
            btnUpdSt.setName(UPDATE_CAR);
            btnUpdSt.addActionListener(this);
            JButton btnDelSt = new JButton("Удалить");
            btnDelSt.setName(DELETE_CAR);
            btnDelSt.addActionListener(this);

Создаем панель, на которую положим наши кнопки и кладем ее вниз

            JPanel pnlBtnSt = new JPanel();
            pnlBtnSt.setLayout(new GridLayout(1, 3));
            pnlBtnSt.add(btnAddSt);
            pnlBtnSt.add(btnUpdSt);
            pnlBtnSt.add(btnDelSt);
            right.add(pnlBtnSt, BorderLayout.SOUTH);

Вставляем панели со списками автомобилей в нижнюю панель

            bot.add(right, BorderLayout.CENTER);

Вставляем верхнюю и нижнюю панели в форму

            getContentPane().add(top, BorderLayout.NORTH);
            getContentPane().add(bot, BorderLayout.CENTER);

            // Задаем границы формы
            setBounds(100, 100, 700, 500);
        

Наш внутренний класс - переопределенная панель.

        class BrandPanel extends JPanel {

         public Dimension getPreferredSize() {
             return new Dimension(250, 0);
         }
        }

Создаем левую панель для вывода списка производителей

            // Она у нас
            BrandPanel left = new BrandPanel();
            // Задаем layout и задаем "бордюр" вокруг панели
            left.setLayout(new BorderLayout());
            left.setBorder(new BevelBorder(BevelBorder.LOWERED));

    
            // Создаем надпись
            left.add(new JLabel("Производитель:"), BorderLayout.NORTH);
            
            
Создаем кнопки для производителей

            JButton btnMvGr = new JButton("Переместить");
            btnMvGr.setName(MOVE_BRAND);
            JButton btnClGr = new JButton("Очистить");
            btnClGr.setName(CLEAR_BRAND);
            
            // Добавляем листенер
            btnMvGr.addActionListener(this);
            btnClGr.addActionListener(this);
            
Создаем панель, на которую положим наши кнопки и кладем ее вниз

            JPanel pnlBtnGr = new JPanel();
            pnlBtnGr.setLayout(new GridLayout(1, 2));
            pnlBtnGr.add(btnMvGr);
            pnlBtnGr.add(btnClGr);
            left.add(pnlBtnGr, BorderLayout.SOUTH);


Вставляем панели со списками производителей и автомобилей в нижнюю панель

            bot.add(left, BorderLayout.WEST);
            bot.add(right, BorderLayout.CENTER);

            // Вставляем верхнюю и нижнюю панели в форму
            getContentPane().add(top, BorderLayout.NORTH);
            getContentPane().add(bot, BorderLayout.CENTER);


Метод для обеспечения интерфейса ActionListener

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
                        if (c.getName().equals(MOVE_BRAND)) {
                            moveBrand();
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


имена для кнопок
    
    private static final String MOVE_BRAND = "moveBrand";
    private static final String CLEAR_BRAND = "clearBrand";

    private static final String INSERT_CAR = "insertCar";
    private static final String UPDATE_CAR = "updateCar";
    private static final String DELETE_CAR = "deleteCar";

    private static final String ALL_CARS = "allCars";
    private static final String ALL_SOLD_CARS = "allSoldCars";
    private static final String NOT_SOLD_CARS = "notSoldCars";
    private static final String ABOUT_US = "aboutUs";


     // метод для переноса производителей
        private void moveBrand() {
            JOptionPane.showMessageDialog(this, "Move Brand");
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


Диалоговые окна

Основные отличия диалогового окна (класс JDialog):

диалоговые окна могут быть модальными. Это означает что диалоговое окно переключает на себя всю работу пользователя в приложении и до тех пор пока оно активно, доступ к оставшимся частям приложения заблокирован. Это свойство можно и отключить, но, как правило, этого не делают, т.к. весь смысл диалогового окна – это «заставить» пользователя сделать какую-то работу, какой-то выбор

при создании и вызове диалогового окна можно указывать «родительское» окно, т.е. окно из которого это окно было сформировано и вызвано. За редкими исключениями диалоговые окна являются вспомогательными по отношению к тому окну из которого они были вызваны. Часто достаточно указать пустую ссылку (null – т.е. «ничего», «пусто», «не имеет значения»), но можно передать и действительную ссылку на родительское окно. 

Кроме того, как правило у диалоговых окон отключают возможность изменять свои размеры (этого можно и не делать, но никакого смысла в этом нет) и, кроме того, их нельзя свернуть – диалоговые окна «вынуждают» пользователя работать с ними, что, в общем, и правильно.


        package vehicle;

        import java.awt.BorderLayout;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
         
        import javax.swing.JButton;
        import javax.swing.JDialog;
        import javax.swing.JFrame;
        import javax.swing.JLabel;

        import javax.swing.JPanel;

        class AboutDialog extends JDialog
        {
            public AboutDialog(JFrame owner)
            {
                super(owner, "About Dialog", true);

                // Метка с HTML-форматированием выравнивается по центру.

                add(new JLabel(
                        "<html><h1><i>Java Cars Market</i></h1><hr>"
                        + "All About Cars</html>"),
                        BorderLayout.CENTER);

                // При активизации кнопки ОК диалогове окно закрывается.

                        JButton ok = new JButton("ok");
                        ok.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent event) {
                                setVisible(false);
                            }
                        });

                // Кнопка ОК помещается в нижнюю часть окна.

                        JPanel panel = new JPanel();
                        panel.add(ok);
                        add(panel, BorderLayout.SOUTH);
                        setBounds(250, 250, 300, 200);

            }
        }
