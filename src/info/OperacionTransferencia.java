package info;

import java.util.Date;

//Operacion transmisor en la que se añade un emisor y un receptor
public class OperacionTransferencia extends Operacion {

	String IBANEmisor;
	String IBANReceptor;

	/**
	 *  Constructor de la clase OperacionTransferencia
	 * @param idOperacion
	 * @param importe
	 * @param concepto
	 * @param fecha
	 * @param iBANEmisor
	 * @param iBANReceptor
	 */
	public OperacionTransferencia(String idOperacion, float importe, String concepto, Date fecha, String iBANEmisor,
			String iBANReceptor) {
		super(idOperacion, importe, concepto, fecha);
		IBANEmisor = iBANEmisor;
		IBANReceptor = iBANReceptor;
	}

	//Getters y setters de la clase OperacionTransferencia
	public String getIBANEmisor() {
		return IBANEmisor;
	}

	public void setIBANEmisor(String iBANEmisor) {
		IBANEmisor = iBANEmisor;
	}

	public String getIBANReceptor() {
		return IBANReceptor;
	}

	public void setIBANReceptor(String iBANReceptor) {
		IBANReceptor = iBANReceptor;
	}
	


}
