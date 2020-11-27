package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import info.Cuenta;
import info.OperacionCajero;
import info.OperacionPagoConTarjeta;
import info.OperacionTransferencia;
import info.OperacionVentanilla;
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
            	Cuenta cuen = new Cuenta(rs.getString("IBAN"), rs.getString("dni"));
            	
            	OperacionTransferenciaBD ot = new OperacionTransferenciaBD();
            	List<OperacionTransferencia> loperst = ot.cargarOperacionesTransferencia(cuen);
            	for (int i = 0;i < loperst.size();i++) {
            		OperacionTransferencia op = loperst.get(i);
            		cuen.setMovimiento(op);
            	}
            	OperacionCajeroBD oc = new OperacionCajeroBD();
            	List<OperacionCajero> lopersc = oc.cargarOperacionesCajero(cuen);
            	for (int i = 0;i < lopersc.size();i++) {
            		OperacionCajero op = lopersc.get(i);
            		cuen.setMovimiento(op);
            	}
            	OperacionPagoConTarjetaBD opct = new OperacionPagoConTarjetaBD();
            	List<OperacionPagoConTarjeta> lopersopct = opct.cargarOperacionesPagoConTarjeta(cuen);
            	for (int i = 0;i < lopersopct.size();i++) {
            		OperacionPagoConTarjeta op = lopersopct.get(i);
            		cuen.setMovimiento(op);
            	}
            	OperacionVentanillaBD ov = new OperacionVentanillaBD();
            	List<OperacionVentanilla> lopersv = ov.cargarOperacionesVentanilla(cuen);
            	for (int i = 0;i < lopersv.size();i++) {
            		OperacionVentanilla op = lopersv.get(i);
            		cuen.setMovimiento(op);
            	}
            	
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
