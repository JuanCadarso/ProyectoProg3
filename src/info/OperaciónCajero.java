package info;

import java.util.Date;

//Clase de operacion realizada en un cajero, solo a�ade el establecimiento en el que se encuentra el cajero
public class Operaci�nCajero extends Operacion {

	String establecimiento;

	/**
	 *  Constructor de la clase OperacionCajero
	 * @param idOperacion
	 * @param importe
	 * @param concepto
	 * @param fecha
	 * @param establecimiento
	 */
	public Operaci�nCajero(String idOperacion, float importe, String concepto, Date fecha, String establecimiento) {
		super(idOperacion, importe, concepto, fecha);
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
