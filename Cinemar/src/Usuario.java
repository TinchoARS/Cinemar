import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

	public class Usuario {
	private  String Nombre;
	private  String Apellido;
	private  int Edad;
	private  String Correo;
	private  String Contrase�a;
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "pasword";
	private Connection conn=null;
	
	public Usuario(String nombre, String apellido, int edad, String correo, String contrase�a) {
		Nombre = nombre;
		Apellido = apellido;
		Correo = correo;
		Contrase�a = contrase�a;
		Edad = edad;
	}
	
	public Usuario(String correo) {
		Nombre = "";
		Apellido = "";
		Correo = correo;
		Contrase�a = "";
		Edad = 0;
	}
	

	public String getNombre() {
		return Nombre;
	}

	public String getApellido() {
		return Apellido;
	}
	public String getCorreo() {
		return Correo;
	}
	
	public int getEdad() {
		return Edad;
	}
	public String getContrase�a() {
		return Contrase�a;
	}
	public void generarUsuario() {
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query="insert into Usuario (correo,nombre,apellido,edad,contrase�a,admi) (?,?,?,?,?,?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, this.Correo);
			stmt.setString(2, this.Nombre);
			stmt.setString(3, this.Apellido);
			stmt.setInt(4, this.Edad);
			stmt.setString(5,this.Contrase�a);
			stmt.setBoolean(6, false);
	        stmt.executeUpdate();
			
			stmt.close();
			conn.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2) {
			}
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public void consultarBD() {
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="Select nombre,apellido,edad,contrase�a from Pelicula where id="+this.Correo;
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
			
			this.Nombre = rs.getString("nombre");
			this.Apellido = rs.getString("apellido");
			this.Edad = rs.getInt("edad");
			this.Contrase�a= rs.getString("contrase�a");
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2) {
			}
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	@Override
	public String toString() {
		return "Nombre = " + Nombre + "\nApellido = " + Apellido +"\nEdad = "+Edad+"\nCorreo = " + Correo;
	}
	
}