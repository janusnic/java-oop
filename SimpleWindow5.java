import javax.swing.*;
    import java.awt.*;
    public class SimpleWindow5  extends JFrame {
      SimpleWindow5(){
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box box = Box.createVerticalBox();
        box.add(new JButton("Кнопка"));
        box.add(Box.createVerticalStrut(10));
        box.add(new JButton("+"));
        box.add(Box.createVerticalGlue());
        box.add(new JButton("-"));
        box.add(Box.createVerticalStrut(10));
        box.add(new JButton("Кнопка с длинной надписью"));
        setContentPane(box);
        setSize(250, 100);
        }

    }