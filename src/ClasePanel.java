import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClasePanel extends JPanel {
	public ClasePanel() {
		 
		  add(getLabelPanelClase());
		  inicializador();
		 }
		 
		 private void inicializador() {
		 setBounds(100, 770, 475, 250);
		  setBackground(Color.ORANGE);
		  setVisible(true);
		 }
		 
		 /* Jlabel del panel */
		 private JLabel getLabelPanelClase() {
		 
		  JLabel label = new JLabel();
		  label.setText("Este es el panel desde una Clase");
		  return label;
		 }


}
