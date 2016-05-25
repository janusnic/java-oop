import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Controller implements ActionListener {
    
    private JTextField searchTermTextField = new JTextField(26);
    private Model model;

    public Controller(JTextField searchTermTextField, Model model) {
        super();
        this.searchTermTextField = searchTermTextField;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String searchTerm = searchTermTextField.getText();
        if (searchTerm != null && !"".equals(searchTerm)) {
            Object[][] newData = new Object[model.rows.length][];
            
            int idx = 0;
            for (Object[] o: model.rows) {
                if ("*".equals(searchTerm.trim())) {
                    newData[idx++] = o;
                } else {
                    if(String.valueOf(o[1]).startsWith(searchTerm.toUpperCase().trim())){
                        newData[idx++] = o;
                    }   
                }   
            } 
            
            model.setDataVector(newData, model.table_header);
            
        } else {
            JOptionPane.showMessageDialog(null,
                    "Search term is empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
