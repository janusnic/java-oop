import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow13  extends JFrame {
  
  SimpleWindow13(){
    super("Окно с кнопкой");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JButton button = new JButton("Кнопка", new ImageIcon("1.gif"));
    button.setMargin(new Insets(0, 10, 20, 30));
    button.setVerticalTextPosition(SwingConstants.TOP);
    button.setHorizontalTextPosition(SwingConstants.LEFT);
    getContentPane().add(button);
    pack();
}

}
