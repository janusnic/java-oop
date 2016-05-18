Simport javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow20  extends JFrame {
  
  SimpleWindow20(){
    super("Пример использования JTabbedPane");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
    for (int i = 1; i <= 10; i++) {
    JPanel panel = new JPanel();
    panel.add(new JButton("Кнопка № " + i));
    tabbedPane.add("Панель " + i, panel);
    }
    getContentPane().add(tabbedPane);
    setSize(300,200);
  }

}