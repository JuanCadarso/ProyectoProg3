package info;

import java.util.Date;

//Operacion de pago con tarjeta, simplemente añade un beneficiario
public class OperacionPagoConTarjeta extends Operacion {

	String benificiario;

	/**
	 * Constructor de la clase OperacinPagoConTarjeta
	 * @param fecha
	 * @param iBAN
	 * @param concepto
	 * @param importe
	 * @param benificiario
	 */
	public OperacionPagoConTarjeta(Date fecha, String iBAN, String concepto, float importe, String benificiario) {
		super(fecha, iBAN, concepto, importe);
		this.benificiario = benificiario;
	}

	public String getBenificiario() {
		return benificiario;
	}

	public void setBenificiario(String benificiario) {
		this.benificiario = benificiario;
	}
	
}
