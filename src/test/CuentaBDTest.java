package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datos.CuentaBD;
import datos.UsuarioBD;
import info.Cuenta;
import info.Operacion;

public class CuentaBDTest {

	private List<Cuenta> cuentas;
	
	@Before
	public void cargarCuentas() {
    	CuentaBD cuenbd = new CuentaBD();
 		this.cuentas = cuenbd.cargarCuentas();
	}
	
	// Se comprueba que todas las cuentas tengan saldo positivo
	@Test
	public void test() {
		if (this.cuentas.size() == 0) {
			fail("La aplicacion no tiene cuentas");
		}
		
		for (int i = 0;i < this.cuentas.size();i++) {
			Cuenta cu = this.cuentas.get(i);
			float saldo = cargarMovimientos(cu);
//			if (saldo < 0) {
//				continue;
//			}
			assertFalse("Cuenta "+cu.getIBAN()+" del usuario "+cu.getDni()+" en descubierto.", saldo < 0);
		}
	}

	private float cargarMovimientos(Cuenta cue) {
		float saldo = 0;
		for(Entry<Date, Operacion> entry : cue.getMovimientos().entrySet()) {
		  Operacion value = entry.getValue();
		  saldo += value.getImporte();
		}
		
		return saldo;
	}

}
