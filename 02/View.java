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
