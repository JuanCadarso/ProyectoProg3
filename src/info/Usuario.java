package info;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	String dni;
	String nombre;
	String apellido1;
	String apellido2;
	String email;
	String password;
	List<Cuenta> cuentas;
	
	/**
	 * Constrcutor vacio de la clase Usuario
	 */
	public Usuario() {
		super();
		this.dni = null;
		this.nombre = "";
		this.apellido1 = "";
		this.apellido2 = "";
		this.email = "";
		this.password = "";
		this.cuentas = null;
	}
	
	/**
	 * Constructor de la clase Usuario
	 * @param dni
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param email
	 * @param password
	 */
	public Usuario(String dni, String nombre, String apellido1, String apellido2, String email, String password) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.password = password;
		this.cuentas = new ArrayList<Cuenta>();
	}
	
	//Getters y setters clase Usuario
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	} 
	
}
