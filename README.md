# java Unit 12

Java MVC
========
Model-view-controller (MVC, «модель-представление-контроллер», «модель-вид-контроллер») — схема использования нескольких шаблонов проектирования, с помощью которых модель приложения, пользовательский интерфейс и взаимодействие с пользователем разделены на три отдельных компонента таким образом, чтобы модификация одного из компонентов оказывала минимальное воздействие на остальные. Данная схема проектирования часто используется для построения архитектурного каркаса, когда переходят от теории к реализации в конкретной предметной области.

Концепция MVC позволяет разделить данные, представление и обработку действий пользователя на три отдельных компонента:

Модель (Model). Модель предоставляет знания: данные и методы работы с этими данными, реагирует на запросы, изменяя своё состояние. Не содержит информации, как эти знания можно визуализировать.

Представление, вид (View). Отвечает за отображение информации (визуализацию). Часто в качестве представления выступает форма (окно) с графическими элементами.

Контроллер (Controller). Обеспечивает связь между пользователем и системой: контролирует ввод данных пользователем и использует модель и представление для реализации необходимой реакции.

01 Main.java
=============
        import javax.swing.SwingUtilities;

        public class Main {

            /**
             * @param args
             */
            public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        createAndShowGUI();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public static void createAndShowGUI() throws Exception {
            new View();
        }

        }
Компонент JPanel
================
При построении интерфейсов нужны компоненты-контерйнеры, которые будут содержать другие компоненты пользовательского интерфейса. В Swing одним из таких компонентов-контейнеров является JPanel. По умолчанию JPanel сама по себе ничего не отрисовывает за исключением фона. При работе с контейнерами, разработчику надо решить, как правило, две основные проблемы. Первая – задать расположение дочерних компонентов и вторая – осуществить добавление компонентов на контейнер.

Компонент JSplitPane 
--------------------
Он представляет собой панель, разделенную на две области, границу между которыми пользователь может перемещать. 

View.java
----------

        import java.awt.Dimension;

        import javax.swing.BorderFactory;

        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JSplitPane;
        import javax.swing.JTable;

        import javax.swing.border.TitledBorder;


        public class View {

            public View() {
                // Create views swing UI components 
                JTable table = new JTable();

                // Set the view layout
                JPanel ctrlPane = new JPanel();

                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setPreferredSize(new Dimension(1000, 400));
                tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                        TitledBorder.CENTER, TitledBorder.TOP));

                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
                splitPane.setDividerLocation(35);
                splitPane.setEnabled(false);

                // Display it all in a scrolling window and make the window appear
                JFrame frame = new JFrame("Swing MVC Market");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(splitPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }

02 Model.java
=============

        import javax.swing.table.DefaultTableModel;

        @SuppressWarnings("serial")
        public class Model extends DefaultTableModel {

            public Model() {
                super(Db.DATA, Db.TABLE_HEADER);
            }

        }

Db.java
-------


        public class Db {

            public static final Object[] TABLE_HEADER ={ "id","model","brand","price","built_date","gear","seat","wheels","miles","capacity","sold","sold_on" };

            public static final Object[][] DATA =  {
                { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
                { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                
            };
        }

Views.java
----------

        import java.awt.Dimension;

        import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JSplitPane;
        import javax.swing.JTable;
        import javax.swing.JTextField;
        import javax.swing.border.TitledBorder;


        public class View {

            public View() {
                // Create views swing UI components 
                JTable table = new JTable();

                // Create table model
                Model model = new Model();
                table.setModel(model);

                // Set the view layout
                JPanel ctrlPane = new JPanel();

                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setPreferredSize(new Dimension(1000, 400));
                tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                        TitledBorder.CENTER, TitledBorder.TOP));

                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
                splitPane.setDividerLocation(35);
                splitPane.setEnabled(false);

                // Display it all in a scrolling window and make the window appear
                JFrame frame = new JFrame("Swing MVC Market");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(splitPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }

03 Model.java
=============

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
        import java.sql.Statement;

        import javax.swing.table.DefaultTableModel;

        @SuppressWarnings("serial")
        public class Model extends DefaultTableModel {
            
            public Object [] table_header;

            public static final Object[][] DATA =  {
                { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
                { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                
            };
            public Model() {
                    
                try {
                    Class.forName("org.sqlite.JDBC");
                    String url = "jdbc:sqlite:vehicle.db";                
                    String query = "select *  from cars"; 
                    Connection con = DriverManager.getConnection(url);
                   
                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery(query);
                    
                    int columnCount = rs.getMetaData().getColumnCount();
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
                    
                    table_header = new String[columnCount];
                    
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                       String h = resultSetMetaData.getColumnName(i);
                      table_header[i-1]= h;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

View.java
---------

        import java.awt.Dimension;

        import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JSplitPane;
        import javax.swing.JTable;
        import javax.swing.JTextField;
        import javax.swing.border.TitledBorder;


        public class View {

            public View() {
                // Create views swing UI components 

                // Create table model
                Model model = new Model();
                JTable table = new JTable(model.DATA,model.table_header);

                // Set the view layout
                JPanel ctrlPane = new JPanel();

                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setPreferredSize(new Dimension(1000, 400));
                tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                        TitledBorder.CENTER, TitledBorder.TOP));

                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
                splitPane.setDividerLocation(35);
                splitPane.setEnabled(false);

                // Display it all in a scrolling window and make the window appear
                JFrame frame = new JFrame("Swing MVC Market");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(splitPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }


04 Model.java
--------------

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
        import java.sql.Statement;

        import javax.swing.table.DefaultTableModel;

        @SuppressWarnings("serial")
        public class Model extends DefaultTableModel {
            
            public Object [] table_header;
            public Object[][] rows ;
            
            public Model() {
                    
                try {
                    Class.forName("org.sqlite.JDBC");
                    String url = "jdbc:sqlite:vehicle.db";                
                                  
                    String query = "select *  from cars"; 
                    Connection con = DriverManager.getConnection(url);
                   
                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery(query);

                    int columnCount = rs.getMetaData().getColumnCount();
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
                    
                    table_header = new String[columnCount];
                    
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                       String h = resultSetMetaData.getColumnName(i);
                      table_header[i-1]= h;
                    }
                
                    int j =0;
                    rows = new Object[10][columnCount];
                    while (rs.next()) {
                        Object[] values = new Object[columnCount];
                        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                            values[i - 1] = rs.getObject(i);
                            rows[j][i-1]=values[i - 1];
                        }
                        j++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
            }

        }

View.java
---------

        import java.awt.Dimension;

        import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JSplitPane;
        import javax.swing.JTable;
        import javax.swing.JTextField;
        import javax.swing.border.TitledBorder;


        public class View {

            public View() {
                // Create table model
                Model model = new Model();
                //table.setModel(model.DATA,model.table_header);
                JTable table = new JTable(model.rows,model.table_header);

                // Set the view layout
                JPanel ctrlPane = new JPanel();

                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setPreferredSize(new Dimension(1000, 400));
                tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                        TitledBorder.CENTER, TitledBorder.TOP));

                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
                splitPane.setDividerLocation(35);
                splitPane.setEnabled(false);

                // Display it all in a scrolling window and make the window appear
                JFrame frame = new JFrame("Swing MVC Market");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(splitPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }


05 Model.java
--------------
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
        import java.sql.Statement;

        import javax.swing.table.DefaultTableModel;

        @SuppressWarnings("serial")
        public class Model extends DefaultTableModel {
            
            public Object [] table_header;
            public Object[][] rows ;
            
            public Model() {
                    
                try {
                    Class.forName("org.sqlite.JDBC");
                    String url = "jdbc:sqlite:vehicle.db";                
                                  
                    String query = "select *  from cars"; 
                    Connection con = DriverManager.getConnection(url);
                    
                    String q = "SELECT COUNT(*) as counts FROM cars";
                    
                    Statement st = con.createStatement();
                    
                    ResultSet rsc = st.executeQuery(q);

                    int size = rsc.getInt("counts");

                    ResultSet rs = st.executeQuery(query);
                    
                    
                    int columnCount = rs.getMetaData().getColumnCount();
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
                    
                    table_header = new String[columnCount];
                    
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                       String h = resultSetMetaData.getColumnName(i);
                      table_header[i-1]= h;
                    }
                
                    int j =0;
                    rows = new Object[size][columnCount];
                    while (rs.next()) {
                        Object[] values = new Object[columnCount];
                        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                            values[i - 1] = rs.getObject(i);
                            rows[j][i-1]=values[i - 1];
                        }
                        j++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
            }

        }


06 Controller.java
==================
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JOptionPane;
        import javax.swing.JTextField;
        import javax.swing.table.DefaultTableModel;


        public class Controller implements ActionListener {
            
            private JTextField searchTermTextField = new JTextField(26);
            private Model model;

            public Controller(JTextField searchTermTextField, Model model) {
                super();
                this.searchTermTextField = searchTermTextField;
                this.model = model;
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                String searchTerm = searchTermTextField.getText();
                if (searchTerm != null && !"".equals(searchTerm)) {
                    Object[][] newData = new Object[model.rows.length][];
                    
                    int idx = 0;
                    for (Object[] o: model.rows) {
                        if ("*".equals(searchTerm.trim())) {
                            newData[idx++] = o;
                        } else {
                            if(String.valueOf(o[1]).startsWith(searchTerm.toUpperCase().trim())){
                                newData[idx++] = o;
                            }   
                        }   
                    } 
                    
                    model.setDataVector(newData, model.table_header);
                    
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Search term is empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }

View.java
----------

        import java.awt.Dimension;

        import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JSplitPane;
        import javax.swing.JTable;
        import javax.swing.JTextField;
        import javax.swing.border.TitledBorder;


        public class View {

            public View() {
                // Create views swing UI components 
                JTextField searchTermTextField = new JTextField(26);
                JButton filterButton = new JButton("Filter");
                
                JTable table = new JTable();
                // Create table model

                Model model = new Model();
                model.setDataVector(model.rows,model.table_header);

                table.setModel(model);

                // Create controller
                Controller controller = new Controller(searchTermTextField, model);
                filterButton.addActionListener(controller);

                // Set the view layout
                JPanel ctrlPane = new JPanel();
                ctrlPane.add(searchTermTextField);
                ctrlPane.add(filterButton);

                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setPreferredSize(new Dimension(1000, 400));
                tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                        TitledBorder.CENTER, TitledBorder.TOP));

                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
                splitPane.setDividerLocation(35);
                splitPane.setEnabled(false);

                // Display it all in a scrolling window and make the window appear
                JFrame frame = new JFrame("Swing MVC Market");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(splitPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }
