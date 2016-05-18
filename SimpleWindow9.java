import javax.swing.*;
import java.awt.*;
public class SimpleWindow9  extends JFrame {
  SimpleWindow9(){
    super("Пробное окно");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(null);
    JButton button = new JButton("Кнопка");
    button.setSize(80, 30);
    button.setLocation(20,20);
    panel.add(button);
    button = new JButton("Кнопка с длинной надписью");
    button.setSize(120, 40);
    button.setLocation(70,50);
    panel.add(button);
    setContentPane(panel);
    //setSize(250, 150);
    pack();
    }

}
