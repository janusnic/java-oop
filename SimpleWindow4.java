import javax.swing.*;
import java.awt.*;
public class SimpleWindow4  extends JFrame {
  SimpleWindow4(){
    super("Пробное окно");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2,3,5,10));
    panel.add(new JButton("Кнопка"));
    panel.add(new JButton("+"));
    panel.add(new JButton("-"));
    panel.add(new JButton("Кнопка с длинной надписью"));
    panel.add(new JButton("еще кнопка"));
    setContentPane(panel);
    setSize(250, 100);
    }

}