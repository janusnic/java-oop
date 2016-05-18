import javax.swing.*;
import java.awt.*;
public class SimpleWindow2  extends JFrame {
  SimpleWindow2(){
    super("Пробное окно");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    panel.add(new JButton("Кнопка"));
    panel.add(new JButton("+"));
    panel.add(new JButton("-"));
    panel.add(new JButton("Кнопка с длинной надписью"));
    setContentPane(panel);
    setSize(250, 100);
    }

}
