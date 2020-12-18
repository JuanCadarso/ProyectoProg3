package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import datos.CuentaBD;
import datos.UsuarioBD;
import info.Usuario;

public class UsuarioBDTest {

	private List<Usuario> users;
	
	@Before
	public void cargarUsuarios() {
    	UsuarioBD userbd = new UsuarioBD();
 		this.users = userbd.cargarUsuarios();
	}
	
	// Se comprueba que todos los usuarios tengan a menos una cuenta
	@Test
	public void test() {
		if (this.users.size() == 0) {
			fail("La aplicacion no tiene usuarios");
		}
		
		for (int i = 0;i < this.users.size();i++) {
			Usuario us = this.users.get(i);
//			if (us.getCuentas().size() == 0) {
//				continue;
//			}
			assertFalse("Usuario "+us.getDni()+" sin cuentas.", us.getCuentas().size() == 0);
		}
	}

}
