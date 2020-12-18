package test;

import static org.junit.Assert.*;

import org.junit.Test;

import info.Usuario;


//Test para comprobar la creación de usuarios
public class UsuarioTest {

	//La lista de cuentas está bien creada
	@Test(expected = NullPointerException.class)
	public void testUsuario1() {
		Usuario user = new Usuario();
		System.out.println(user.getCuentas().size());
	}

	@Test(expected = NullPointerException.class)
	public void testUsuario2() {
		Usuario user = new Usuario("16027536K", "Pedro", "Ruiz", "Garcia", "pedroRuiz@hotmail.com",
				 "87FDA93AB58D7443A9355F298CE7C696");
		System.out.println(user.getCuentas().size());
	}

	//Se comprueba que la lista de cuentas nada más creada esté vacia
	@Test
	public void testUsuario3() {
		Usuario user = new Usuario("16027536K", "Pedro", "Ruiz", "Garcia", "pedroRuiz@hotmail.com",
				 "87FDA93AB58D7443A9355F298CE7C696");
		assertEquals( user.getCuentas().size(), 0 );
	}
	
}
