package info;

import java.util.Date;

//Operacion de pago con tarjeta, simplemente añade un beneficiario
public class OperacionPagoConTarjeta extends Operacion {

	String benificiario;

	public OperacionPagoConTarjeta(String idOperacion, float importe, String concepto, Date fecha,
			String benificiario) {
		super(idOperacion, importe, "Pago con tarjeta", fecha);
		this.benificiario = benificiario;
	}

	public String getBenificiario() {
		return benificiario;
	}

	public void setBenificiario(String benificiario) {
		this.benificiario = benificiario;
	}
	
}
