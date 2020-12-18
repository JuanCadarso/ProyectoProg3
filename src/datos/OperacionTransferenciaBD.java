package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.Cuenta;
import info.OperacionTransferencia;

public class OperacionTransferenciaBD {

	public static final String CLASS_SQLITE = "org.sqlite.JDBC";
	public static final String STRING_CONN_SQLITE = "jdbc:sqlite:AppBancoBD.db";

    public List<OperacionTransferencia> cargarOperacionesTransferencia(Cuenta cuen) {
        
    	List<OperacionTransferencia> lopers = new ArrayList<OperacionTransferencia>();
    	
        ResultSet rs = null;
        StringBuffer sSql = null;
        PreparedStatement  stm   = null;  
        Connection con = null;
        
        try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
 
        	sSql = new StringBuffer();
            sSql.append("Select * from operacion_transferencia where IBAN_emisor = ? or IBAN_receptor = ?");
            stm = con.prepareStatement(sSql.toString());
            
            stm.setString(1, cuen.getIBAN());
            stm.setString(2, cuen.getIBAN());
           
            rs = stm.executeQuery();
            
            while (rs.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
            	Date fecha = formatter.parse(rs.getString("fecha"));
            	OperacionTransferencia ope = null;
            	
            	if (rs.getFloat("importe") > 0) {
	            	ope = new OperacionTransferencia(fecha, rs.getString("IBAN_receptor"), 
													 rs.getString("concepto"), 
													 rs.getFloat("importe"), rs.getString("IBAN_emisor"));
            	} else {
	            	ope = new OperacionTransferencia(fecha, rs.getString("IBAN_emisor"), 
													 rs.getString("concepto"), 
													 rs.getFloat("importe"), rs.getString("IBAN_receptor"));
            	}
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
    
    public String insertarTransferencis(OperacionTransferencia trans) {	    
    	ResultSet rs = null;
	    StringBuffer sSql = null;
	    PreparedStatement  st   = null;   
	    
	    Connection con = null;
	
	    String mensaje = null;
	    
	    try {
			Class.forName(CLASS_SQLITE);
			con = DriverManager.getConnection(STRING_CONN_SQLITE);
		    
	        sSql = new StringBuffer();
	        sSql.append("INSERT INTO operacion_transferencia VALUES (null, ?, ?, ?, ?, ?)");
	        st = con.prepareStatement(sSql.toString());
	
	        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	        String fecha = formatter.format(trans.getFecha());
	        
	        st.setString(1, fecha);
	        st.setString(2, trans.getConcepto());
	        st.setFloat(3, trans.getImporte());
	        st.setString(4, trans.getIBAN());
	        st.setString(5, trans.getIBANReceptor());
	        
	        st.executeUpdate();
	
	    } catch (Exception e) {
	        mensaje =  e.getMessage();
	    }  finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (Exception e) {
			        mensaje =  e.getMessage();
				}
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



