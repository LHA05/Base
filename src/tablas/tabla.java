
package tablas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class tabla  {
	 public String crear(String tabla, ArrayList<String> campos) {
	        StringBuilder insert = new StringBuilder("CREATE TABLE ");
	        insert.append(tabla).append("(");
	        for (int i = 0; i < campos.size(); i++) {
	            insert.append(campos.get(i));
	            if (i < campos.size() - 1) {
	                insert.append(",");
	            }
	        }
	        insert.append(");");
	        return insert.toString();
	    }
	 
	
}
