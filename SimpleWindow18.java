import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow18  extends JFrame {
  
  SimpleWindow18(){
    super("Пример использования JComboBox");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    String[] elements = new String[] {"Вася", "Петя",
    "<html><font size = +1 color = yellow>Иван</font>"};
    JComboBox combo = new JComboBox(elements);
    combo.setSelectedIndex(1);
    JPanel panel = new JPanel();
    panel.add(combo);
    setContentPane(panel);
    setSize(200,200);
  }

}