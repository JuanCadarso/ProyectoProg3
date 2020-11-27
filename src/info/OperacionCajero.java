package info;

import java.util.Date;

//Clase de operacion realizada en un cajero, solo añade el establecimiento en el que se encuentra el cajero
public class OperacionCajero extends Operacion {

	String establecimiento;

	/**
	 * Constructor clase OperacionCAjero
	 * @param fecha
	 * @param iBAN
	 * @param concepto
	 * @param importe
	 * @param establecimiento
	 */
	public OperacionCajero(Date fecha, String iBAN, String concepto, float importe, String establecimiento) {
		super(fecha, iBAN, concepto, importe);
		this.establecimiento = establecimiento;
	}

	//Getters y setters de la clase OperacionCajero
	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	
}	