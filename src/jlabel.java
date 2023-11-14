import javax.swing.*;
import java.awt.*;

public class jlabel extends interfaz{
//	public jlabel() {
//		super();
//		this.setDefaultCloseOperation(interfaz.EXIT_ON_CLOSE);
//		this.setSize(750, 400);
//		this.setLocationRelativeTo(null);
//		this.getContentPane().setLayout(new GridLayout(1,3,5,5));
//	    JLabel l1 = new JLabel();
//	    l1.setSize(100, 100);
//	    ImageIcon imagen = new ImageIcon("imagen/database.jpg");
//	    Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_DEFAULT));	        
//	}
	public void jlabel() {
		this.setDefaultCloseOperation(interfaz.EXIT_ON_CLOSE);
		this.setSize(750, 400);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new GridLayout(1,3,5,5));
	    JLabel l1 = new JLabel();
	    l1.setSize(100, 100);
	    ImageIcon imagen = new ImageIcon("imagen/database.jpg");
	    Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(l1.getWidth(), l1.getHeight(), Image.SCALE_DEFAULT));	
		
		
	}	
}
