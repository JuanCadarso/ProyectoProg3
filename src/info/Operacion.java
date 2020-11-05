package info;

import java.util.Date;

public class Operacion {

	String idOperacion;
	float importe;
	String concepto;
	Date fecha;
	
	public Operacion(String idOperacion, float importe, String concepto, Date fecha) {
		super();
		this.idOperacion = idOperacion;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
	}

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
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
