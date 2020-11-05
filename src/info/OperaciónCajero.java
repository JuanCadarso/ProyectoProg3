package info;

import java.util.Date;

public class Operaci�nCajero extends Operacion {

	String establecimiento;

	public Operaci�nCajero(String idOperacion, float importe, String concepto, Date fecha, String establecimiento) {
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
