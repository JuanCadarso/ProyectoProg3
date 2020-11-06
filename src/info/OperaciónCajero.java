package info;

import java.util.Date;

//Clase de operacion realizada en un cajero, solo añade el establecimiento en el que se encuentra el cajero
public class OperaciónCajero extends Operacion {

	String establecimiento;

	public OperaciónCajero(String idOperacion, float importe, String concepto, Date fecha, String establecimiento) {
		super(idOperacion, importe, concepto, fecha);
		this.establecimiento = establecimiento;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	
}
