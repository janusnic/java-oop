import java.awt.Dimension;

import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import javax.swing.border.TitledBorder;


public class View {

    public View() {
        // Create views swing UI components 
        JTable table = new JTable();

        // Set the view layout
        JPanel ctrlPane = new JPanel();

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1000, 400));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                TitledBorder.CENTER, TitledBorder.TOP));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
        splitPane.setDividerLocation(35);
        splitPane.setEnabled(false);

        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("Swing MVC Market");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
