package info;

import java.util.Date;
import java.util.TreeMap;

public class Cuenta {

	String IBAN;
	String dni;
	TreeMap<Date, Operacion> movimientos;
	
	/**
	 * Constructor de la clase cuenta
	 * @param iBAN
	 * @param dni
	 */
	public Cuenta(String iBAN, String dni) {
		super();
		IBAN = iBAN;
		this.dni = dni;
		movimientos = new TreeMap<Date, Operacion>();
	}

	//Getters y setters de la clase cuenta
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


	public TreeMap<Date, Operacion> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(TreeMap<Date, Operacion> movimientos) {
		this.movimientos = movimientos;
	}
	
	public void setMovimiento(Operacion oper) {
		this.movimientos.put(oper.getFecha(), oper);
	}

	public String toString() {
		return this.IBAN;
	}
}
