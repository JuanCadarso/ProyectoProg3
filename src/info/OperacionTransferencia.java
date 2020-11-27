package info;

import java.util.Date;

//Operacion transmisor en la que se añade un emisor y un receptor
public class OperacionTransferencia extends Operacion {

	//Nuestra cuenta es IBANReceptor cuando el importe es positivo.
	String IBANReceptor;

	/**
	 * Constructor clase OperacionTransferencia
	 * @param fecha
	 * @param iBAN
	 * @param concepto
	 * @param importe
	 * @param iBANReceptor
	 */
	public OperacionTransferencia(Date fecha, String iBAN, String concepto, float importe, String iBANReceptor) {
		super(fecha, iBAN, concepto, importe);
		IBANReceptor = iBANReceptor;
	}

	//Getters y setters de la clase OperacionTransferencia
	public String getIBANReceptor() {
		return IBANReceptor;
	}

	public void setIBANReceptor(String iBANReceptor) {
		IBANReceptor = iBANReceptor;
	}
	


}
