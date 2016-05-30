import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.util.Vector;

public class View {
    
    private Db dbase;
    private JTable table;
    private Model model;

    public View() {
        
        //Не забудьте указать свой путь к базе, вместо моего: jdbc:sqlite:vehicle.db;
                      
        dbase = new Db("org.sqlite.JDBC", "jdbc:sqlite:vehicle.db");
        
        // Create views swing UI components 
        JTextField searchTermTextField = new JTextField(26);
        JButton filterButton = new JButton("Filter");
        
        // Create table model
        model = new Model();
        model.setTableData(dbase.getNomen("SELECT * FROM cars"));
        
        table = new JTable();
        table.setModel(model);
        
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        // Create controller
        Controller controller = new Controller(searchTermTextField, model);
        filterButton.addActionListener(controller);

        // Set the view layout
        JPanel ctrlPane = new JPanel();
        ctrlPane.add(searchTermTextField);
        ctrlPane.add(filterButton);

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
