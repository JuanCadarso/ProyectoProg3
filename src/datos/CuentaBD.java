package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import info.Cuenta;
import info.Usuario;

public class CuentaBD {

	public static final String CLASS_SQLITE = "org.sqlite.JDBC";
	public static final String STRING_CONN_SQLITE = "jdbc:sqlite:AppBancoBD.db";

    public List<Cuenta> cargarCuentasUsuario(Usuario user) {
        
    	List<Cuenta> lcuentas = new ArrayList<Cuenta>();
    	
        ResultSet rs = null;
        StringBuffer sSql = null;
        PreparedStatement  stm   = null;  
        Connection con = null;
        
        try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
 
        	sSql = new StringBuffer();
            sSql.append("Select * from cuenta where dni = ?");
            stm = con.prepareStatement(sSql.toString());
            
            stm.setString(1, user.getDni());
           
            rs = stm.executeQuery();
            
            while (rs.next()) {
            	Cuenta cuen = new Cuenta(rs.getString("IBAN"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"),
            						     rs.getString("apellido2"), rs.getFloat("saldo"));
            	lcuentas.add(cuen);
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

        return lcuentas;
    }

}
