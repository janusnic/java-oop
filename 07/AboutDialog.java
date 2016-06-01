package vehicle;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

class AboutDialog extends JDialog
{
    public AboutDialog(JFrame owner)
    {
        super(owner, "About Dialog", true);

        // Метка с HTML-форматированием выравнивается по центру.

        add(new JLabel(
                "<html><h1><i>Java Cars Market</i></h1><hr>"
                + "All About Cars</html>"),
                BorderLayout.CENTER);

        // При активизации кнопки ОК диалогове окно закрывается.

                JButton ok = new JButton("ok");
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        setVisible(false);
                    }
                });

        // Кнопка ОК помещается в нижнюю часть окна.

                JPanel panel = new JPanel();
                panel.add(ok);
                add(panel, BorderLayout.SOUTH);
                setBounds(250, 250, 300, 200);
                //setSize(260, 160);
    }
}
