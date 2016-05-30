import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;	
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.Enumeration; 

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
			Vector<Vector<Object>> newData = new Vector<Vector<Object>>();	
	
				if ("*".equals(searchTerm.trim())) {

					newData = model.getTableData();
				} else {
					for (Enumeration num = model.getTableData().elements(); num.hasMoreElements();) 
					{ 
						Vector<Object> newRow = new Vector<Object>(); 
						
						newRow = (Vector) num.nextElement();

						if (newRow.contains(searchTerm.trim()))
						{
							System.out.println("Found element : "+newRow);
							JOptionPane.showMessageDialog(null, "Found element: "+newRow);

							newData.add(newRow);
						}
					}
					}	

				model.setTableData(newData);
			
		} else {
			JOptionPane.showMessageDialog(null,
					"Search term is empty", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
