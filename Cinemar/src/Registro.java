import java.sql.*;
import java.util.Scanner;

public class Registro {
	private Usuario us;
	private Cliente cli;
	
	public Registro(String nombre, String apellido, int edad, String correo, String contrase�a) {
		this.us= new Usuario(nombre,apellido,edad,correo,contrase�a);
		this.us.generarUsuario();
		
		this.cargarCliente();
		this.cli.generarCliente();
	}
	
	private void cargarCliente() {
		Scanner ingreso=new Scanner(System.in);
		
		System.out.println("Ingresse un nick: ");
		String n=ingreso.nextLine();
		
		this.cli=new Cliente(this.us,n);
	}
	
	public Cliente getCliente() {
		return this.cli;
	}
}