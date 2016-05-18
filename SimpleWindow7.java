import javax.swing.*;
    import java.awt.*;
    public class SimpleWindow7  extends JFrame {
      SimpleWindow7(){
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box box = Box.createVerticalBox();
        box.add(new JButton("Кнопка"));
        box.add(Box.createVerticalStrut(10));
        box.add(new JButton("+"));
        box.add(Box.createVerticalGlue());
        // изменим пример, выровняв третью кнопку по правому краю. 
        //box.add(new JButton("-"));
        JButton rightButton = new JButton("-");
        rightButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        
        box.add(rightButton);

        box.add(Box.createVerticalStrut(10));
        box.add(new JButton("Кнопка с длинной надписью"));
        setLocation(140, 120);
        setContentPane(box);
        setSize(250, 300);
        }

    }
