import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import conexiones.conexion;
import javax.swing.JTable;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class interfaz extends JFrame implements ActionListener {
	
	private JPanel panel_campos, panel_logo, panel_vista, panel_crear, panel_log, panel_base, panel_new_base, panel_eliminar;
	private JLabel logo_bd, lb_usuario, lb_pass, titulo_tabla, nombre_table, lb_nombre_campo, lb_tipo_dato, lb_longitud, lb_atributo,
	logo_elimina, logo_crea, logo_prin,logo_base, tx_bd, tx_drop;
    private JMenuBar menuBar;
    private JMenu inicio, menu, data;
    private JMenuItem n_conexion, inicio_item, crear, crear_base, borra_bd;
    private JTextField t_usu, t_pass, text_nomTable, txt_bd, txt_idtabla, tx_query;
    private JButton ingresar, new_tabla, agregar_column, btn_guardar, b_crea, b_actualiza, b_nbase, b_edita, b_eliminar, bt_drop, d_select,
    b_agregar, b_ejecutar, b_aceptar, b_cancela, b_insert, b_drop, b_select;
    private JComboBox<String> bd, tablas, campos;
    private JTable j_tabla, j_tabla_query;
    private JTextArea consola;
    
    /// Variables para las tablas
    private JTextField tn1,tn2,tn3,tn4,tn5,tn6,tn7,tn8,tn9,tn10;
    private JComboBox<String> cbt1,cbt2,cbt3,cbt4,cbt5,cbt6,cbt7,cbt8,cbt9,cbt10;
    private JTextField l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    private JComboBox<String> long1,long2,long3,long4,long5,long6,long7,long8,long9,long10;
    //////////////////////////////////////////
    
    int fila = 1;
    String bd_s;
    conexion cc = new conexion();
    Connection con = cc.conexion("root", "2121");  
    DefaultTableModel dtm = new DefaultTableModel();
    List<String> d = new ArrayList<String>();
    List<String> d_aux = new ArrayList<String>();
	String tabla; 
    ArrayList<ArrayList<String>> editar = new ArrayList<ArrayList<String>>();
    int fil =0;
    
    public interfaz() {
        componentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 1000, 600);
        setLocationRelativeTo(null);	
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setTitle("*JDBC*");  
    }

    public void componentes() {
    	//------------------SECCION PANELES -----------------------------//////.
    	
    	//PANEL LOG
    	panel_log = new JPanel();
        panel_log.setBounds(0, 0, 1000, 600);
        panel_log.setBackground(Color.lightGray);
        panel_log.setLayout(null);
        add(panel_log);
        
        lb_usuario = new JLabel("Usuario: ");
        lb_usuario.setBounds(330, 200, 200, 20);
        panel_log.add(lb_usuario);
        
        t_usu = new JTextField("");
        t_usu.setBounds(400, 200, 200, 20);
        panel_log.add(t_usu);       
        
        lb_pass = new JLabel("Password: ");
        lb_pass.setBounds(330, 250, 200, 20);
        panel_log.add(lb_pass);
        
        t_pass = new JTextField("");
        t_pass.setBounds(400, 250, 200, 20);       
        panel_log.add(t_pass);
        
        ingresar = new JButton("INGRESAR");
        ingresar.setBounds(450, 300, 100, 30);
        ingresar.addActionListener(this);
        panel_log.add(ingresar);
        
      //PANEL LOGO
        panel_logo = new JPanel();
        panel_logo.setBounds(0, 0, 200, 600);
        panel_logo.setBackground(Color.darkGray);
        panel_logo.setLayout(null);
        panel_logo.setVisible(false);
        bd = new JComboBox<String>();
        bd.setBounds(30,230,150,20);
        panel_logo.add(bd);
       
        bd.addActionListener(this);
        tablas = new JComboBox<String>();
        tablas.setBounds(30,280,150,20);
        tablas.addItem("Selecciona");
        tablas.addActionListener(this);
        panel_logo.add(tablas);
        campos = new JComboBox<String>();
        campos.setBounds(30,330,150,20);
        panel_logo.add(campos);
        add(panel_logo);
        new_tabla = new JButton("Nueva Tabla");
        new_tabla.setBounds(30, 450, 150, 30);
        new_tabla.addActionListener(this);
        panel_logo.add(new_tabla);
        bd.addItem("Selecciona");
    	
    	//PANEL CREAR TABLAS       
    	panel_campos = new JPanel();
        panel_campos.setBounds(200, 0, 1000, 600);
        panel_campos.setBackground(Color.lightGray);    
        panel_campos.setLayout(null);
        panel_campos.setVisible(false);
        titulo_tabla = new JLabel("Estructura ");
        titulo_tabla.setBounds(390, 20, 200, 20);
        panel_campos.add(titulo_tabla);
        nombre_table = new JLabel("Nombre: ");
        nombre_table.setBounds(150, 50, 200, 30);
        panel_campos.add(nombre_table);
        text_nomTable = new JTextField("");
        text_nomTable.setBounds(210, 55, 200, 20);
        panel_campos.add(text_nomTable);
        
        lb_nombre_campo = new JLabel("Nombre");
        lb_nombre_campo.setBounds(70, 110, 200, 20);
        panel_campos.add(lb_nombre_campo);
        tn1 = new JTextField("");
        tn1.setBounds(20, 150, 150, 20);
        panel_campos.add(tn1);
        tn2 = new JTextField("");
        tn2.setBounds(20, 180, 150, 20);
        tn2.setVisible(false);
        panel_campos.add(tn2);
        tn3 = new JTextField("");
        tn3.setBounds(20, 210, 150, 20);
        tn3.setVisible(false);
        panel_campos.add(tn3);
        tn4 = new JTextField("");
        tn4.setBounds(20, 240, 150, 20);
        tn4.setVisible(false);
        panel_campos.add(tn4);
        tn5 = new JTextField("");
        tn5.setBounds(20, 270, 150, 20);
        tn5.setVisible(false);
        panel_campos.add(tn5);
        tn6 = new JTextField("");
        tn6.setBounds(20, 300, 150, 20);
        tn6.setVisible(false);
        panel_campos.add(tn6);
        tn7 = new JTextField("");
        tn7.setBounds(20, 330, 150, 20);
        tn7.setVisible(false);
        panel_campos.add(tn7);
        tn8 = new JTextField("");
        tn8.setBounds(20, 360, 150, 20);
        tn8.setVisible(false);
        panel_campos.add(tn8);
        tn9 = new JTextField("");
        tn9.setBounds(20, 390, 150, 20);
        tn9.setVisible(false);
        panel_campos.add(tn9);
        tn10 = new JTextField("");
        tn10.setBounds(20, 420, 150, 20);
        tn10.setVisible(false);
        panel_campos.add(tn10);
        
        lb_tipo_dato = new JLabel("Tipo Dato");
        lb_tipo_dato.setBounds(250, 110, 200, 20);
        panel_campos.add(lb_tipo_dato);
        cbt1=new JComboBox<String>();
        cbt1.setBounds(210, 150, 130, 20);
        cbt1.addItem("varchar");
        cbt1.addItem("int");
        cbt1.addItem("double");
        cbt1.addItem("date");
        panel_campos.add(cbt1);
        cbt2=new JComboBox<String>();
        cbt2.setBounds(210, 180, 130, 20);
        cbt2.addItem("varchar");
        cbt2.addItem("int");
        cbt2.addItem("double");
        cbt2.addItem("date");
        cbt2.setVisible(false);
        panel_campos.add(cbt2);
        cbt3=new JComboBox<String>();
        cbt3.setBounds(210, 210, 130, 20);
        cbt3.addItem("varchar");
        cbt3.addItem("int");
        cbt3.addItem("double");
        cbt3.addItem("date");
        cbt3.setVisible(false);
        panel_campos.add(cbt3);
        cbt4=new JComboBox<String>();
        cbt4.setBounds(210, 240, 130, 20);
        cbt4.addItem("varchar");
        cbt4.addItem("int");
        cbt4.addItem("double");
        cbt4.addItem("date");
        cbt4.setVisible(false);
        panel_campos.add(cbt4);
        cbt5=new JComboBox<String>();
        cbt5.setBounds(210, 270, 130, 20);
        cbt5.addItem("varchar");
        cbt5.addItem("int");
        cbt5.addItem("double");
        cbt5.addItem("date");
        cbt5.setVisible(false);
        panel_campos.add(cbt5);
        cbt6=new JComboBox<String>();
        cbt6.setBounds(210, 300, 130, 20);
        cbt6.addItem("varchar");
        cbt6.addItem("int");
        cbt6.addItem("double");
        cbt6.addItem("date");
        cbt6.setVisible(false);
        panel_campos.add(cbt6);
        cbt7=new JComboBox<String>();
        cbt7.setBounds(210, 330, 130, 20);
        cbt7.addItem("varchar");
        cbt7.addItem("int");
        cbt7.addItem("double");
        cbt7.addItem("date");
        cbt7.setVisible(false);
        panel_campos.add(cbt7);
        cbt8=new JComboBox<String>();
        cbt8.setBounds(210, 360, 130, 20);
        cbt8.addItem("varchar");
        cbt8.addItem("int");
        cbt8.addItem("double");
        cbt8.addItem("date");
        cbt8.setVisible(false);
        panel_campos.add(cbt8);
        cbt9=new JComboBox<String>();
        cbt9.setBounds(210, 390, 130, 20);
        cbt9.addItem("varchar");
        cbt9.addItem("int");
        cbt9.addItem("double");
        cbt9.addItem("date");
        cbt9.setVisible(false);
        panel_campos.add(cbt9);
        cbt10=new JComboBox<String>();
        cbt10.setBounds(210, 420, 130, 20);
        cbt10.addItem("varchar");
        cbt10.addItem("int");
        cbt10.addItem("double");
        cbt10.addItem("date");
        cbt10.setVisible(false);
        panel_campos.add(cbt10);
                
        lb_longitud = new JLabel("Longitud");
        lb_longitud.setBounds(430, 110, 200, 20);
        panel_campos.add(lb_longitud);
        l1 = new JTextField("");
        l1.setBounds(390, 150, 150, 20);
        panel_campos.add(l1);
        l2 = new JTextField("");
        l2.setBounds(390, 180, 150, 20);
        l2.setVisible(false);
        panel_campos.add(l2);
        l3 = new JTextField("");
        l3.setBounds(390, 210, 150, 20);
        l3.setVisible(false);
        panel_campos.add(l3);
        l4 = new JTextField("");
        l4.setBounds(390, 240, 150, 20);
        l4.setVisible(false);
        panel_campos.add(l4);
        l5 = new JTextField("");
        l5.setBounds(390, 270, 150, 20);
        l5.setVisible(false);
        panel_campos.add(l5);
        l6 = new JTextField("");
        l6.setBounds(390, 300, 150, 20);
        l6.setVisible(false);
        panel_campos.add(l6);
        l7 = new JTextField("");
        l7.setBounds(390, 330, 150, 20);
        l7.setVisible(false);
        panel_campos.add(l7);
        l8 = new JTextField("");
        l8.setBounds(390, 360, 150, 20);
        l8.setVisible(false);
        panel_campos.add(l8);
        l9 = new JTextField("");
        l9.setBounds(390, 390, 150, 20);
        l9.setVisible(false);
        panel_campos.add(l9);
        l10 = new JTextField("");
        l10.setBounds(390, 420, 150, 20);
        l10.setVisible(false);
        panel_campos.add(l10);
               
        lb_atributo = new JLabel("Atributo");
        lb_atributo.setBounds(640, 110, 200, 20);
        panel_campos.add(lb_atributo);        
        add(panel_campos);
        long1=new JComboBox<String>();
        long1.setBounds(610, 150, 130, 20);
        long1.addItem("");
        long1.addItem("pk");
        long1.addItem("");
        panel_campos.add(long1);
        long2=new JComboBox<String>();
        long2.setBounds(610, 180, 130, 20);
        long2.addItem("null");
        long2.addItem("0");
        long2.addItem("undefined");
        long2.setVisible(false);
        panel_campos.add(long2);
        long3=new JComboBox<String>();
        long3.setBounds(610, 210, 130, 20);
        long3.addItem("null");
        long3.addItem("0");
        long3.addItem("undefined");
        long3.setVisible(false);
        panel_campos.add(long3);
        long4=new JComboBox<String>();
        long4.setBounds(610, 240, 130, 20);
        long4.addItem("null");
        long4.addItem("0");
        long4.addItem("undefined");
        long4.setVisible(false);
        panel_campos.add(long4);
        long5=new JComboBox<String>();
        long5.setBounds(610, 270, 130, 20);
        long5.addItem("null");
        long5.addItem("0");
        long5.addItem("undefined");
        long5.setVisible(false);
        panel_campos.add(long5);
        long6=new JComboBox<String>();
        long6.setBounds(610, 300, 130, 20);
        long6.addItem("null");
        long6.addItem("0");
        long6.addItem("undefined");
        long6.setVisible(false);
        panel_campos.add(long6);
        long7=new JComboBox<String>();
        long7.setBounds(610, 330, 130, 20);
        long7.addItem("null");
        long7.addItem("0");
        long7.addItem("undefined");
        long7.setVisible(false);
        panel_campos.add(long7);
        long8=new JComboBox<String>();
        long8.setBounds(610, 360, 130, 20);
        long8.addItem("null");
        long8.addItem("0");
        long8.addItem("undefined");
        long8.setVisible(false);
        panel_campos.add(long8);
        long9=new JComboBox<String>();
        long9.setBounds(610, 390, 130, 20);
        long9.addItem("null");
        long9.addItem("0");
        long9.addItem("undefined");
        long9.setVisible(false);
        panel_campos.add(long9);
        long10=new JComboBox<String>();
        long10.setBounds(610, 420, 130, 20);
        long10.addItem("null");
        long10.addItem("0");
        long10.addItem("undefined");
        long10.setVisible(false);
        panel_campos.add(long10);
        
        agregar_column = new JButton("Agregar");
        agregar_column.setBounds(150, 480, 150, 30);
        agregar_column.addActionListener(this);
        panel_campos.add(agregar_column);
        
        btn_guardar = new JButton("Guardar");
        btn_guardar.setBounds(450, 480, 150, 30);
        btn_guardar.addActionListener(this);
        panel_campos.add(btn_guardar);
        
        //PANEL VISTA
        panel_vista = new JPanel();
        panel_vista.setBounds(200, 0, 1000, 600);
        panel_vista.setBackground(Color.lightGray);    
        panel_vista.setLayout(null);
        panel_vista.setVisible(false);
        add(panel_vista);
                  
    	//PANEL BASE
    	panel_base = new JPanel();
    	panel_base.setBounds(200, 0, 1000, 600);
    	panel_base.setBackground(Color.lightGray);
    	panel_base.setLayout(null);
    	panel_base.setVisible(false);
    	
    	j_tabla = new JTable();
        j_tabla.setBounds(20, 50, 580, 200);
        j_tabla.setVisible(true);
        panel_base.add(j_tabla);
        
    	b_actualiza = new JButton("Atualizar");
        b_actualiza.setBounds(640, 140, 100, 30);
        b_actualiza.addActionListener(this);
        b_actualiza.setVisible(false);
        panel_base.add(b_actualiza);
        
        b_edita = new JButton("Insertar");
        b_edita.setBounds(640, 100, 100, 30);
        b_edita.addActionListener(this);
        panel_base.add(b_edita);
        
        d_select = new JButton("Seleccion");
        d_select.setBounds(640, 60, 100, 30);
        d_select.addActionListener(this);       
        panel_base.add(d_select);
                
        b_eliminar = new JButton("Borrar");
        b_eliminar.setBounds(640, 180, 100, 30);
        b_eliminar.addActionListener(this);
        panel_base.add(b_eliminar);
        b_eliminar.setVisible(false);
    	add(panel_base);
    	
    	b_aceptar = new JButton("Aceptar");
    	b_aceptar.setBounds(550, 270, 100, 30);
    	b_aceptar.addActionListener(this);
        panel_base.add(b_aceptar);
        b_aceptar.setVisible(false);
    	add(panel_base);
    	
    	b_cancela = new JButton("Cancelar");
    	b_cancela.setBounds(670, 270, 100, 30);
    	b_cancela.addActionListener(this);
        panel_base.add(b_cancela);
        b_cancela.setVisible(false);
    	add(panel_base);
    	
    	txt_idtabla = new JTextField();
    	txt_idtabla.setBounds(130, 17, 100, 20);
    	txt_idtabla.addActionListener(this);
        panel_base.add(txt_idtabla);
        
        tx_query = new JTextField();
    	tx_query.setBounds(50, 270, 200, 20);
    	tx_query.addActionListener(this);
        panel_base.add(tx_query);
        
        consola = new JTextArea();
        consola.setBounds(20, 320, 580, 200);
        consola.setVisible(true);
        panel_base.add(consola);
        
        b_agregar = new JButton("Agregar");
    	b_agregar.setBounds(270, 270, 100, 30);
    	b_agregar.addActionListener(this);
        panel_base.add(b_agregar);	
    	
        b_ejecutar = new JButton("Ejecutar");
        b_ejecutar.setBounds(640, 350, 100, 30);
        b_ejecutar.setBackground(Color.yellow);
        b_ejecutar.addActionListener(this);
        panel_base.add(b_ejecutar);
        
        b_insert = new JButton("Insert");
        b_insert.setBounds(640, 400, 100, 30);
        b_insert.setBackground(Color.yellow);
        b_insert.addActionListener(this);
        panel_base.add(b_insert);
        
        b_drop = new JButton("Delete");
        b_drop.setBounds(640, 450, 100, 30);
    	b_drop.setBackground(Color.yellow);
        b_drop.addActionListener(this);
        panel_base.add(b_drop);
        
        b_select = new JButton("Select");
        b_select.setBounds(640, 500, 100, 30);
        b_select.setBackground(Color.yellow);
        b_select.addActionListener(this);
        panel_base.add(b_select);
        
        	
    	//PANEL NEW_BASE
    	panel_new_base = new JPanel();
    	panel_new_base.setBounds(200, 0, 1000, 600);
    	panel_new_base.setBackground(Color.lightGray);
    	panel_new_base.setLayout(null);
    	panel_new_base.setVisible(false);
    	tx_bd = new JLabel("Nombre de la base");
    	tx_bd.setBounds(300, 150, 150, 20);
    	tx_bd.setVisible(true);
    	panel_new_base.add(tx_bd);	
    	
    	txt_bd = new JTextField("");
    	txt_bd.setBounds(300, 200, 150, 20);
    	txt_bd.setVisible(true);
    	panel_new_base.add(txt_bd);	
        add(panel_new_base);
        
        b_nbase = new JButton("Crear");
        b_nbase.setBounds(300, 290, 150, 30);
        b_nbase.addActionListener(this);
        panel_new_base.add(b_nbase);
    	   	
    	//PANEL BORRAR
    	panel_eliminar = new JPanel();   
    	panel_eliminar.setBounds(200, 0, 1000, 600);
    	panel_eliminar.setBackground(Color.lightGray);
    	panel_eliminar.setLayout(null);
    	panel_eliminar.setVisible(false);
    	
    	tx_drop = new JLabel("BASE");
    	tx_drop.setBounds(300, 200, 150, 20);
    	tx_drop.setVisible(true);
    	panel_eliminar.add(tx_drop);	
        add(panel_eliminar);
        
        bt_drop = new JButton("Borrar");
        bt_drop.setBounds(300, 250, 150, 30);
        bt_drop.addActionListener(this);
        panel_eliminar.add(bt_drop);
      
    	add(panel_eliminar);
    	
        //------------------------- SECCION DE LABELS  -----------------------------/////  
        logo_bd = new JLabel("");
        logo_bd.setIcon(new ImageIcon("src/imagen/base-de-datos.png"));
        logo_bd.setBounds(40, 20, 150, 150);
    	panel_logo.add(logo_bd);
    	
    	logo_prin = new JLabel("");
    	logo_prin.setIcon(new ImageIcon("src/imagen/seguridad-del-usuario.png"));
    	logo_prin.setBounds(450, 30, 200, 200);
    	panel_log.add(logo_prin);
    	    	
    	logo_crea = new JLabel("CREAR BASE");
    	logo_crea.setIcon(new ImageIcon("src/imagen/agregar-base-de-datos.png"));
    	logo_crea.setBounds(30, 30, 200, 80);
    	panel_new_base.add(logo_crea);
    	
    	logo_elimina = new JLabel("ELIMINAR BASE");
    	logo_elimina.setIcon(new ImageIcon("src/imagen/elimina-base.png"));
    	logo_elimina.setBounds(30, 30, 200, 80);
    	panel_eliminar.add(logo_elimina);
    	
//    	logo_base = new JLabel("");
//    	logo_base.setIcon(new ImageIcon("src/imagen/datos.png"));
//    	logo_base.setBounds(640, 200, 100, 100);
//    	panel_base.add(logo_base);
    	
    	logo_base = new JLabel("");
    	logo_base.setIcon(new ImageIcon("src/imagen/servicio-al-cliente.png"));
    	logo_base.setBounds(650, 250, 100, 100);
    	panel_base.add(logo_base);
    	
    	  	
    	//-------------------------  SECCION DE MENU BAR  -------------------------/////  
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setVisible(false);
        inicio = new JMenu("Inicio");
        inicio_item = new JMenuItem("inicio");
        inicio.add(inicio_item);
        menuBar.add(inicio);
        
        menu = new JMenu("Menu");
        menuBar.add(menu);
        n_conexion = new JMenuItem("Nueva Conexion");
        menu.add(n_conexion);
        
        data = new JMenu("Base");
        menuBar.add(data);
        crear_base = new JMenuItem("Nueva Base");
        crear_base.addActionListener(this);
        data.add(crear_base);
   
        borra_bd = new JMenuItem("Eliminar base");
        borra_bd.addActionListener(this);
        data.add(borra_bd);
        
        String sql = "show databases";
	    try {    	 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				bd.addItem(rs.getString("DATABASE"));							
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
    @Override
    public void actionPerformed(ActionEvent ae) {  	
    	if (ae.getSource() == ingresar) {
  		if (t_usu.getText().length() != 0 && t_pass.getText().length()!= 0) {
  			boolean conect = false;
  			conexion tes = new conexion();
  			conect =   tes.test(t_usu.getText(), t_pass.getText());			
  			if (conect != false) {
				panel_log.setVisible(false);
				panel_logo.setVisible(true);
				//panel_campos.setVisible(true);
				panel_vista.setVisible(true);
				menuBar.setVisible(true);
  			}				
			}else {
				JOptionPane.showMessageDialog(null, "Usuario o pasword es incorrecto");
			}             
        } 	
    	if (ae.getSource() == agregar_column) {
			switch (fila) {
			case 1:
				tn2.setVisible(true);
				cbt2.setVisible(true);
				l2.setVisible(true);
				long2.setVisible(true);
				fila++;
				break;
				
			case 2:
				tn3.setVisible(true);
				cbt3.setVisible(true);
				l3.setVisible(true);
				long3.setVisible(true);
				fila++;
				break;
				
			case 3:
				tn4.setVisible(true);
				cbt4.setVisible(true);
				l4.setVisible(true);
				long4.setVisible(true);
				fila++;
				break;
				
			case 4:
				tn5.setVisible(true);
				cbt5.setVisible(true);
				l5.setVisible(true);
				long5.setVisible(true);
				fila++;
				break;
				
			case 5:
				tn6.setVisible(true);
				cbt6.setVisible(true);
				l6.setVisible(true);
				long6.setVisible(true);
				fila++;
				break;
				
			case 6:
				tn7.setVisible(true);
				cbt7.setVisible(true);
				l7.setVisible(true);
				long7.setVisible(true);
				fila++;
				break;
			
			case 7:
				tn8.setVisible(true);
				cbt8.setVisible(true);
				l8.setVisible(true);
				long8.setVisible(true);
				fila++;
				break;
			case 8:
				tn9.setVisible(true);
				cbt9.setVisible(true);
				l9.setVisible(true);
				long9.setVisible(true);
				fila++;
				break;
				
			case 9:
				tn10.setVisible(true);
				cbt10.setVisible(true);
				l10.setVisible(true);
				long10.setVisible(true);
				fila++;
				fila++;
				break;
			
			default:
				break;
			}			
		}  
    	
    	if (ae.getSource() == btn_guardar) {
    		System.out.print("Guardar");
    		String cp;
    		  ArrayList<String> crear_tabla = new ArrayList<>();
    		
    		for (int i = 1; i <= fila; i++) {
    			switch (i) {
    		    case 1:
    		        cp = tn1.getText() + " " + cbt1.getSelectedItem() + "(" + l1.getText() + ")";
    		        crear_tabla.add(cp);
    		        break;

    		    case 2:
    		    	cp = tn2.getText() + " " +cbt2.getSelectedItem() + "(" + l2.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
    		    case 3:
    		    	cp = tn3.getText() + " " +cbt3.getSelectedItem() + "(" + l3.getText() + ")";
    		    	 crear_tabla.add(cp);
    		    	 break;
                case 4:
    		    	cp = tn4.getText() + " " +cbt4.getSelectedItem() + "(" + l4.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
    		        
    		    case 5:
    		    	cp = tn5.getText() + " " +cbt5.getSelectedItem() + "(" + l5.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
    		    case 6:
    		    	cp = tn6.getText() + " " +cbt6.getSelectedItem() + " " + l6.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
                case 7:
    		    	cp = tn7.getText() + " " +cbt7.getSelectedItem() + " " + l7.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
    		    
    		    case 8:
    		    	cp = tn8.getText() + " " +cbt8.getSelectedItem() + " " + l8.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
    		    case 9:
    		    	cp = tn9.getText() + " " +cbt9.getSelectedItem() + " " + l9.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
                case 10:
    		    	cp = tn10.getText() + " " +cbt10.getSelectedItem() + " " + l10.getText() + ")";
    		    	crear_tabla.add(cp);
    		        break;
    		    default:
    		        break;
    		    }   			
    			System.out.print("");
			}

    		String query = cc.crear( text_nomTable.getText(), crear_tabla);
    		System.out.println("////// " + query);
    		cc.agregar_cliente(query);  		
        }
    	
    	if (ae.getSource() == new_tabla) {
    		panel_campos.setVisible(true);                    
        } 
    	
	    if (ae.getSource() == b_actualiza) { 
	    	System.out.println("-------- " + j_tabla.getColumnCount() + "___________" + j_tabla.getRowCount());
	    	for (int i = 0; i < j_tabla.getRowCount(); i++) {
	    		ArrayList<String> datos = new ArrayList<String>();
	    		String id = (String)j_tabla.getValueAt(i, 0);
	    		if(txt_idtabla.getText().equals(id)) {
	    			System.out.println("id encontrado: " +j_tabla.getValueAt(i, 1));	
		    		for (int j = 0; j < j_tabla.getColumnCount(); j++) {
		    			datos.add((String)j_tabla.getValueAt(i, j));		    			
					}	
		    		System.out.print("Datos: " + datos.toString());
		    		actualizar(tabla, datos, d);	
	    		}
			}  
	    	dtm.getDataVector().clear();
    	    consultar_datos(tabla);
        }
	    
	    if (ae.getSource() == b_insert) {
         consola.setText("");
         consola.setText(boton_insert(tabla, d));			
		}
	    
	    if (ae.getSource() == b_drop) {
	    	consola.setText("");
	         consola.setText("DELETE FROM "+ tabla + " WHERE nombre_campo = valor");	
	  		}
	    
	    
	    
	    if (ae.getSource() == b_edita) { 	    	
	    	System.out.print("Insertar");
	    	String[] registros = new String[d.size()];
	    	dtm.addRow(registros);
	    	b_aceptar.setVisible(true);
        }
	    
	    if (ae.getSource() == b_eliminar) { 
	    	System.out.print("Eliminar");
	    	eliminar(txt_idtabla.getText(), tabla, d);	
	    	dtm.getDataVector().clear();
    	    consultar_datos(tabla);
        }
	    
	    if (ae.getSource() == b_agregar) { 
	    	System.out.print("Agregar"); 
				int columna = j_tabla.getSelectedColumn();
				System.out.println("Column: " + columna);	
				System.out.println("Column: " + d.get(columna));
				if(fil==0){
				tx_query.setText(d.get(columna));
				d_aux.add(d.get(columna));
				fil =1;
				}else{
					String query =  tx_query.getText() + "," + d.get(columna);
					tx_query.setText(query);
					d_aux.add(d.get(columna));
					fil++;
					}
				
        }
	    
	    
	    if (ae.getSource() == b_ejecutar) {
//	    	System.out.println(fil);  
//	    	System.out.print("Ejecutar");  
//	    	System.out.println(Query_selct(tabla,  tx_query.getText()));
//	    	consulta_query(Query_selct(tabla,  tx_query.getText()), fil);
	    	String consulta = consola.getText();
	    	if(consulta.equals("")) {
	    		System.out.print("contiene valor");
	    	}else {
	    		ejecutar(consulta);
	    	}
        }
	    
	    if (ae.getSource() == bt_drop) { 
	    	System.out.print("Eliminar");
		panel_eliminar.setVisible(true);		
	    
        }
	    
        if (ae.getSource() == b_select) { 
		System.out.println(fil);    
    	System.out.println(Query_selct(tabla,  tx_query.getText()));
    	consola.setText(Query_selct(tabla,  tx_query.getText()));
    	consulta_query(Query_selct(tabla,  tx_query.getText()), fil);
			
        }
	    
	    if (ae.getSource() == b_aceptar) { 
	    	for (int i = 0; i < j_tabla.getRowCount(); i++) {
	    		ArrayList<String> datos = new ArrayList<String>();
	    		String id = (String)j_tabla.getValueAt(i, 0);
	    		if(id == null) {
	    			System.out.println("id encontrado: " +j_tabla.getValueAt(i, 1));	
		    		for (int j = 0; j < j_tabla.getColumnCount(); j++) {
		    			datos.add((String)j_tabla.getValueAt(i, j));		    			
					}	
		    		System.out.println("Agregar: " + datos.toString());
		    		System.out.println("query: " + ejecuta_insertar(tabla, datos, d));
		    		ejecutar(ejecuta_insertar(tabla, datos, d));
		    		dtm.getDataVector().clear();
		    	    consultar_datos(tabla);
	    		}
			}

        }
	    
        if (ae.getSource() == b_cancela) { 
			
        }
	    
	    
	    if (ae.getSource() == d_select) { 
	    
        if (j_tabla.getSelectedRow() != -1) { 
        	b_edita.setVisible(false);
            b_actualiza.setVisible(true);	
            b_eliminar.setVisible(true);
            b_aceptar.setVisible(true);
            b_cancela.setVisible(true);
	    	System.out.print("Selecciona *******" + j_tabla.getSelectedRow());
	    	txt_idtabla.setText(String.valueOf(j_tabla.getValueAt(j_tabla.getSelectedRow(), 0)));		
	    	
	    }else {
	    	System.out.println("Seleciona una fila");
	    }
        }
	    
	    	    
    	if (ae.getSource() == crear_base) {
    		panel_new_base.setVisible(true); 
    		panel_base.setVisible(false);
    		panel_campos.setVisible(false);
    		panel_vista.setVisible(false);   		
        }  
    	
    	if (ae.getSource() == borra_bd) {
    		panel_eliminar.setVisible(true);
    		panel_new_base.setVisible(false); 
    		panel_base.setVisible(false);
    		panel_campos.setVisible(false);
    		panel_vista.setVisible(false);   		
        }

//    	if (ae.getSource() == tablas) {
//    		String table;
//    		table = (String)tablas.getSelectedItem();		
//    	}
    	
    	if (ae.getSource() == bd) {
    		String base;
    		base = (String)bd.getSelectedItem();
    		if(base.equals("Selecciona")) {
    			
    		}else{
    			System.out.println("la base que escogio: " +base);  
    			consultar_tablas(base);
    			bd_s = base;
    		}	
        } 
    	
    	if (ae.getSource() == tablas) {
    		
    		tabla = (String)tablas.getSelectedItem();
    		if(tabla.equals("Selecciona")) {	
    		}else{
    			base_datos(bd_s);			
    			System.out.println("la tabla que escogio: " +tabla);
//    			List<String> d = new ArrayList<String>();
//    			d = consultar_estructura(tabla);
    			panel_base.setVisible(true);
    			panel_vista.setVisible(false);
    			consultar_datos(tabla);
    		}	
        } 
    }
    
   public List<String> consultar_estructura(String tabla) {
    	List<String> campos = new ArrayList<String>();
    	  String sql = "describe " + tabla;
  	    try {    	 
  			Statement st = con.createStatement();
  			ResultSet rs = st.executeQuery(sql);
  			while (rs.next()) {
  				campos.add(rs.getString("Field"));						
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	    return campos;
    }
    
  public void consultar_tablas(String base_d) {
	  String sql = "show tables from " + base_d;
	  String field = "Tables_in_" + base_d;
	    try {    	 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
			tablas.addItem(rs.getString(field));						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    }
  
  //crea
  public void crear_tablas(String base_d) {
	  String sql = "create database" + base_d;
	    try {    	 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
			tablas.addItem(rs.getString(sql));						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}  	
    }
  
  //eliminar
  public void eliminar_tablas(String base_d) {
	  String sql = "drop database " + base_d; 
	    try {    	 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
			tablas.addItem(rs.getString(sql));						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}   	
    }
   
  public void base_datos(String base_datos) {
	  String sql = "use " + base_datos;
	    try {    	 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
}

  public void consultar_datos(String bt) {
	  String[] registros = new String[num_campos(bt)];
	  String[] datos = new String[num_campos(bt)];
	  System.out.print(registros.length);  	
		d = consultar_estructura(bt);
	int compos  = registros.length;	
	  for (int i = 0; i < registros.length; i++) {
		  registros[i] = d.get(i);	
	}
		System.out.println(registros.toString());
		dtm.setColumnIdentifiers(registros);
		  String sql = "select * from  " + bt;
	  	    try {    	 
	  			Statement st = con.createStatement();
	  			ResultSet rs = st.executeQuery(sql);
	  			while (rs.next()) {	  				
	  				 for (int i = 0; i < datos.length; i++) {
	  					  datos[i] = rs.getString(d.get(i));		
	  				}	
	  				 dtm.addRow(datos);
	  				 j_tabla.setModel(dtm);
	  				 j_tabla.repaint();
	  			System.out.print("");					
	  			}
	   int[] anchos = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,100, 100, 100};
       for (int x = 0; x < registros.length; x++) {
           j_tabla.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
       }
} catch (Exception e) {

}      
  }
  
  public Integer num_campos(String tabla) {
  	int msql  = 0;
  	  String sql = "SELECT COUNT(*) as num FROM information_schema.columns WHERE table_name = '" + tabla + "'";
  	System.out.println(sql);
	    try {    	 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				msql = Integer.parseInt(rs.getString("num"));						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return msql;
  }
  
  public void actualizar(String tabla, ArrayList<String> datos , List<String> campo) {	
	  System.out.print(tabla + "--- " + datos.toString() + "**** " + campo.toString());
	  for (int i = 1; i < datos.size(); i++) {	
		  String sql = "UPDATE " + tabla + " SET " + campo.get(i) + " = '" + datos.get(i) + "' WHERE " + campo.get(0) + " = " + datos.get(0);
		  System.out.println(sql);
		  try {    	 
			  PreparedStatement pstm = con.prepareStatement(sql);
			  pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  dtm.fireTableDataChanged();
			}	  
	}
  	//  String sql = "SELECT COUNT(*) as num FROM information_schema.columns WHERE table_name = '" + tabla + "'";	 
	  }

private void eliminar(String id, String tabla, List<String> campo) {
	  String sql = "DELETE FROM " + tabla + " WHERE " + campo.get(0) + " = " + id ;
	System.out.println(sql);
	    try {    	
	    	 PreparedStatement pstm = con.prepareStatement(sql);
			  pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dtm.fireTableDataChanged();
		}	
	// TODO Auto-generated method stub
}

public String ejecuta_insertar(String tabla, ArrayList<String> datos , List<String> campo){
	  StringBuilder insert = new StringBuilder("INSERT INTO ");
      insert.append(tabla).append("(");
      for (int i = 1; i < campo.size(); i++) {
          insert.append(campo.get(i));
          if (i < campo.size() - 1) {
              insert.append(",");
          }
      }
      insert.append(") VALUES (");
      for (int i = 1; i < datos.size(); i++) {
    	  insert.append("'");
    	    insert.append(datos.get(i));
    	    insert.append("'");
    	    if (i < datos.size() - 1) {
                insert.append(",");              
            }
      }
      insert.append(");");   
      return insert.toString();        
}

public String Query_selct(String tabla, String  campos){
	  StringBuilder insert = new StringBuilder("SELECT ");
    insert.append(campos).append(" FROM ");
    insert.append(tabla); 
    insert.append(";");
    return insert.toString();        
}

public boolean ejecutar(String query) {
    boolean insertado = false;
    System.out.println(query);
    try {
        PreparedStatement ps = null;
        ps = con.prepareStatement(query);
        ps.execute();
        insertado = true;
    	dtm.getDataVector().clear();
	    consultar_datos(tabla);
    } catch (SQLException ex) {
        System.out.println("error" + ex.getMessage());
    }
    return insertado;
}


/// Nuevo metodo 
public void consulta_query(String bt, int columna) {
	  String[] registros = new String[columna];
	  String[] datos = new String[columna];
		System.out.println(registros.toString());
		dtm.setColumnIdentifiers(registros);
	  	    try {    	 
	  			Statement st = con.createStatement();
	  			ResultSet rs = st.executeQuery(bt);
	  			while (rs.next()) {	  				
	  				 for (int i = 0; i < datos.length; i++) {
	  					  datos[i] = rs.getString(d_aux.get(i));	  
	  				}	
	  				 dtm.addRow(datos);
	  				 j_tabla.setModel(dtm);
	  				 j_tabla.repaint();
	  			System.out.print("");					
	  			}
	   int[] anchos = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,100, 100, 100};
     for (int x = 0; x < registros.length; x++) {
         j_tabla.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
     }
} catch (Exception e) {

}      
}

public String boton_insert(String tabla, List<String> campo){
	  StringBuilder insert = new StringBuilder("INSERT INTO ");
    insert.append(tabla).append("(");
    for (int i = 1; i < campo.size(); i++) {
        insert.append(campo.get(i));
        if (i < campo.size() - 1) {
            insert.append(",");
        }
    }
    insert.append(") VALUES (");
    for (int i = 1; i < campo.size(); i++) {
  	  insert.append("'");
  	    insert.append("'");
  	    if (i < campo.size() - 1) {
              insert.append(",");              
          }
    }
    insert.append(");");   
    return insert.toString();        
}

}




  

    
    
    
    
    
    
    
    

