package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import info.Usuario;

public class UsuarioBD {

	public static final String CLASS_SQLITE = "org.sqlite.JDBC";
	public static final String STRING_CONN_SQLITE = "jdbc:sqlite:AppBancoBD.db"; //Cadena de conexion

	public void crearBDSqlite() {
		
		Connection conn = null;
		
		try {
			
			Statement stat;
			
			File fichero = null;
			BufferedReader objReader = null;
			
			fichero = new File("AppBancoBD.db");
			if (!fichero.exists()) {
				Class.forName(CLASS_SQLITE);
				conn = DriverManager.getConnection(STRING_CONN_SQLITE);
				stat = conn.createStatement();
				try {
					String cadena = "";
					String strCurrentLine = "";
					objReader = new BufferedReader(new FileReader("Create_Usuario.txt"));
					while ((strCurrentLine = objReader.readLine()) != null) {
						cadena += strCurrentLine;
					}
					// Creacion tabla Usuarios
					stat.executeUpdate(cadena);
					
					objReader.close();
					
					cadena = "";
					strCurrentLine = "";
					objReader = new BufferedReader(new FileReader("Insert_Usuarios.txt"));
					while ((strCurrentLine = objReader.readLine()) != null) {
						cadena += strCurrentLine;
					}
					String [] usuarios = cadena.split(";");
					// Insercion de usuarios de pruebas
					for (int i = 0;i < usuarios.length; i++) {
						stat.executeUpdate(usuarios[i]);
					}
					
					objReader.close();
					
					cadena = "";
					strCurrentLine = "";
					objReader = new BufferedReader(new FileReader("Create_Cuenta.txt"));
					while ((strCurrentLine = objReader.readLine()) != null) {
						cadena += strCurrentLine;
					}
					// Creacion tabla Cuentas
					stat.executeUpdate(cadena);
					
					objReader.close();
					cadena = "";
					strCurrentLine = "";
					objReader = new BufferedReader(new FileReader("Insert_Cuentas.txt"));
					while ((strCurrentLine = objReader.readLine()) != null) {
						cadena += strCurrentLine;
					}
					String [] cuentas = cadena.split(";");
					// Insercion de cuentas de pruebas
					for (int i = 0;i < cuentas.length; i++) {
						stat.executeUpdate(cuentas[i]);
					}
					
					objReader.close();
					
					System.out.println("Creada Base de Datos AppBancoBD.db");
					
					
				} catch (Exception e0) {
					e0.printStackTrace();
				} finally {
					if (objReader != null)
						try {
							objReader.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			} else {
				Class.forName(CLASS_SQLITE);
				conn = DriverManager.getConnection(STRING_CONN_SQLITE);
				stat = conn.createStatement();
			}
			
			ResultSet rs = stat.executeQuery("select count(*) as NUM_USUARIOS from usuario;");
			if (rs.next()) {
				System.out.println("Numero de usuarios = " + rs.getInt("NUM_USUARIOS"));
			}
			
			rs.close();
			conn.close();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
    public Usuario validarUsuario(String dni, String password) {
        
    	Usuario user = new Usuario();
    	
        ResultSet rs = null;
        StringBuffer sSql = null;
        PreparedStatement  stm   = null;  
        Connection con = null;
        
        try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
 
        	sSql = new StringBuffer();
            sSql.append("Select * from usuario where dni = ?");
            stm = con.prepareStatement(sSql.toString());
            
            stm.setString(1, dni);
           
            rs = stm.executeQuery();
            
            if (rs.next()) {
            	if (rs.getString("password").equals(password)) {
            		user = new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"),
            						   rs.getString("apellido2"), rs.getString("email"), rs.getString("password"));
            	} else {
            		user.setNombre("La contraseña es erronea. Intentelo de nuevo.");
            	}
            } else {
            	user.setNombre("No existe un usuario con ese DNI.");
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }  finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (Exception e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
			if (stm!=null)
				try {
					stm.close();
				} catch (Exception e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
			
			if (con!=null)
				try {
					con.close();
				} catch (Exception e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
			
		}

        return user;
    }

public Usuario verUsuario(String dni) {
        
    	Usuario user = new Usuario();
    	
        ResultSet rs = null;
        StringBuffer sSql = null;
        PreparedStatement  stm   = null;  
        Connection con = null;
        
        try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
 
        	sSql = new StringBuffer();
            sSql.append("Select * from usuario where dni = ?");
            stm = con.prepareStatement(sSql.toString());
            
            stm.setString(1, dni);
           
            rs = stm.executeQuery();
            
            if (rs.next()) {
           		user = new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"),
           						   rs.getString("apellido2"), rs.getString("email"), rs.getString("password"));
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }  finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (Exception e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
			if (stm!=null)
				try {
					stm.close();
				} catch (Exception e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
			
			if (con!=null)
				try {
					con.close();
				} catch (Exception e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
			
		}

        return user;
    }

    public String insertarUsuario(Usuario user) {	    
    	//ResultSet rs = null;
	    StringBuffer sSql = null;
	    PreparedStatement  st   = null;   
	    
	    Connection con = null;
	
	    String mensaje = null;
	    
	    try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
		    
	        sSql = new StringBuffer();
	        sSql.append("INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?)");
	        st = con.prepareStatement(sSql.toString());
	
	        st.setString(1, user.getDni());
	        st.setString(2, user.getNombre());
	        st.setString(3, user.getApellido1());
	        st.setString(4, user.getApellido2());
	        st.setString(5, user.getEmail());
	        st.setString(6, user.getPassword());
	        
	        st.executeUpdate();
	
	    } catch (Exception e) {
	        mensaje =  e.getMessage();
	    }  finally {
			if (st!=null)
				try {
					st.close();
				} catch (Exception e) {
			        mensaje =  e.getMessage();
				}
			if (con!=null)
				try {
					con.close();
				} catch (Exception e) {
			        mensaje =  e.getMessage();
				}
		}
	    
	    return mensaje;
	}



}
