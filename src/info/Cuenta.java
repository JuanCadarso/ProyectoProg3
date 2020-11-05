package info;

import java.util.Date;
import java.util.TreeMap;

public class Cuenta {

	String IBAN;
	String dni;
	String nombre;
	String apellido1;
	String apellido2;
	float saldo;
	TreeMap<Date, Operacion> movimientos;
	
	public Cuenta(String iBAN, String dni, String nombre, String apellido1, String apellido2, float saldo) {
		super();
		IBAN = iBAN;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.saldo = saldo;
		movimientos = new TreeMap<Date, Operacion>();
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

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

	public TreeMap<Date, Operacion> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(TreeMap<Date, Operacion> movimientos) {
		this.movimientos = movimientos;
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

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
}
