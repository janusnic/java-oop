mport javax.swing.*;
import java.awt.*;
public class SimpleWindow3  extends JFrame {
  SimpleWindow3(){
    super("Пробное окно");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().add(new JButton("Кнопка"), BorderLayout.NORTH);
    getContentPane().add(new JButton("+"), BorderLayout.EAST);
    getContentPane().add(new JButton("-"), BorderLayout.WEST);
    getContentPane().add(new JButton("Кнопка с длинной надписью"), BorderLayout.SOUTH);
    getContentPane().add(new JButton("В ЦЕНТР!"));
    setSize(250, 100);
    }

}
