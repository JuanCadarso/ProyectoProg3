package info;

import java.util.Date;

public class OperacionVentanilla extends Operacion{

	String sucursal;

	/**
	 * Constructor de la clase OperacionVentanilla
	 * @param fecha
	 * @param iBAN
	 * @param concepto
	 * @param importe
	 * @param sucursal
	 */
	public OperacionVentanilla(Date fecha, String iBAN, String concepto, float importe, String sucursal) {
		super(fecha, iBAN, concepto, importe);
		this.sucursal = sucursal;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
}
