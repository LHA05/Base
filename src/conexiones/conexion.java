package conexiones;
import com.mysql.jdbc.Connection;

import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class conexion  {
	Connection conectar = null;
	//public Connection conexion;
	// public Statement sentencia;
	
	 public ResultSet resultado;

    public Connection conexion(String usuario, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // CONEXON DE BASE DE DATOS LOCAL
//            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_mysql", "root", "2121");
            
            //conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base_datos, usuario, password);
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/",usuario, password);
            System.out.println("conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Conexion " + e.getMessage());
        }
        return conectar;
    }
    
    
    public  Connection probar_conexion() {
    	boolean conec = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // CONEXON DE BASE DE DATOS LOCAL
//            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/",usuario, password);
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_mysql", "root", "2121");
            JOptionPane.showMessageDialog(null, "Conexion Exitosa");
            conec = true;         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Conexion " + e.getMessage());
        }
        return conectar;
    }
    
    public  boolean test(String usuario, String password) {
    	boolean conec = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // CONEXON DE BASE DE DATOS LOCAL
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/",usuario, password);
           // conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_mysql", "root", "2121");
            JOptionPane.showMessageDialog(null, "Conexion Exitosa");
            conec = true;         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Conexion " + e.getMessage());
        }
        return conec;
    }
    
    public Connection conect(String usuario, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // CONEXON DE BASE DE DATOS LOCAL
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/",usuario, password);
            JOptionPane.showMessageDialog(null, "Conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Conexion " + e.getMessage());
        }
        return conectar;
    }    
    
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
    
    public String insertar(String tabla, ArrayList<String> datos , List<String> campo) {
        StringBuilder insert = new StringBuilder("INSERT INTO ");
        insert.append(tabla).append("(");
        for (int i = 0; i < campo.size(); i++) {
            insert.append(campo.get(i));
            if (i < campo.size() - 1) {
                insert.append(",");
            }
        }
        insert.append(");");
        return insert.toString();
    }
    
    
    
    
    public boolean agregar_cliente(String query) {
        boolean insertado = false;
        try {
        	 Connection con = probar_conexion();
            PreparedStatement ps = null;
            ps = con.prepareStatement(query);
            ps.execute();
            insertado = true;
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        return insertado;
    }
    
    
    public String columnas(String tabla){
    	boolean cc = false;
      String SQL = "describe  ";
      try {
          PreparedStatement ps = null;
          ResultSet rs = null;
          rs = ps.executeQuery();
          ResultSetMetaData data = rs.getMetaData();
          while (rs.next()) {
              //determina que la base de datos tenga un registro para la base de datos
              if (cc == false) {
                  for (int i = 1; i <= data.getColumnCount(); i++) {
                  }
                  cc = true;
              }
          }
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Error al mostrar datos" + e.getMessage());
      }
      return tabla;
  }    
}
