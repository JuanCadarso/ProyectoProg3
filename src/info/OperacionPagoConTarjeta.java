package info;

import java.util.Date;

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
