package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.Cuenta;
import info.OperacionCajero;

public class OperacionCajeroBD {

	public static final String CLASS_SQLITE = "org.sqlite.JDBC";
	public static final String STRING_CONN_SQLITE = "jdbc:sqlite:AppBancoBD.db";

    public List<OperacionCajero> cargarOperacionesCajero(Cuenta cuen) {
        
    	List<OperacionCajero> lopers = new ArrayList<OperacionCajero>();
    	
        ResultSet rs = null;
        StringBuffer sSql = null;
        PreparedStatement  stm   = null;  
        Connection con = null;
        
        try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
 
        	sSql = new StringBuffer();
            sSql.append("Select * from operacion_cajero where IBAN = ?");
            stm = con.prepareStatement(sSql.toString());
            
            stm.setString(1, cuen.getIBAN());
           
            rs = stm.executeQuery();
            
            while (rs.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
            	Date fecha = formatter.parse(rs.getString("fecha"));
            	OperacionCajero ope = new OperacionCajero(fecha, rs.getString("IBAN"), rs.getString("concepto"), 
            											  rs.getFloat("importe"), rs.getString("establecimiento"));
            	lopers.add(ope);
             }

        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (stm!=null)
				try {
					stm.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			if (con!=null)
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}

        return lopers;
    }

}
