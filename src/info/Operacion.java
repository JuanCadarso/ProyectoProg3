package info;

import java.util.Date;

//Clase general de operacion
public class Operacion {

	Date fecha;
	String IBAN;
	String concepto;
	float importe;
	
	public Operacion(Date fecha, String iBAN, String concepto, float importe) {
		super();
		this.fecha = fecha;
		IBAN = iBAN;
		this.concepto = concepto;
		this.importe = importe;
	}

	//Getters y setters de la clase Operacion
	
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
