import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {

    public Model() {
        super(Db.DATA, Db.TABLE_HEADER);
    }

}
