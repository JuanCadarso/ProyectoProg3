package prpal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.l2fprod.util.WindowUtils;

import datos.CuentaBD;
import datos.OperacionTransferenciaBD;
import datos.UsuarioBD;
import info.Cuenta;
import info.OperacionTransferencia;
import info.Usuario;
import seguridad.MD5;

public class AltaTransferencia extends JFrame implements ActionListener {

	private final static Logger LOGGER = Logger.getLogger("AppBanco.AltaTransferencia");
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIban;
	private JTextField textConcepto;
	private JTextField textImporte;
	
	private Usuario usuario;
	private Cuenta cuenta;
	private OperacionTransferencia transferencia;
	
	/**
	 * Create the frame.
	 */
	public AltaTransferencia(Usuario us, Cuenta cuen) {
		this.usuario = us;
		this.cuenta = cuen;
		setFont(new Font("Dialog", Font.BOLD, 12));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 681, 250);
		this.setTitle("Alta de Transferencia");
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(8, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel1.add(panel);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblIBANorigen = new JLabel("IBAN origen:  ");
		lblIBANorigen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIBANorigen.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_9.add(lblIBANorigen);
		
		JLabel lblIBANOrigen = new JLabel("");
		lblIBANOrigen.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_9.add(lblIBANOrigen);
		lblIBANOrigen.setText(this.cuenta.getIBAN());
		
		JPanel panel_7 = new JPanel();
		panel1.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("");
		panel_8.add(label);
		
		JLabel label_1 = new JLabel("");
		panel_8.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel1.add(panel_1);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblIBANdestino = new JLabel("IBAN destino:  ");
		panel_10.add(lblIBANdestino);
		lblIBANdestino.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIBANdestino.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textIban = new JTextField();
		textIban.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_10.add(textIban);
		textIban.setColumns(30);
		
		JPanel panel_2 = new JPanel();
		panel1.add(panel_2);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_11.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_11.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		panel1.add(panel_3);
		
		JPanel panel_12 = new JPanel();
		panel_3.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblConcepto = new JLabel("Concepto: ");
		panel_12.add(lblConcepto);
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConcepto.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textConcepto = new JTextField();
		textConcepto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_12.add(textConcepto);
		textConcepto.setColumns(30);
		
		JPanel panel_4 = new JPanel();
		panel1.add(panel_4);
		
		JPanel panel_13 = new JPanel();
		panel_4.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel_13.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel_13.add(lblNewLabel_5);
		
		JPanel panel_5 = new JPanel();
		panel1.add(panel_5);
		
		JPanel panel_14 = new JPanel();
		panel_5.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblImporte = new JLabel("Importe:  ");
		lblImporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_14.add(lblImporte);
		
		textImporte = new JTextField();
		textImporte.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textImporte.setColumns(15);
		panel_14.add(textImporte);
		
		JPanel panel_6 = new JPanel();
		panel1.add(panel_6);
		
		JPanel panel_16 = new JPanel();
		panel_6.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_25 = new JLabel("");
		panel_16.add(label_25);
		
		JLabel label_24 = new JLabel("");
		panel_16.add(label_24);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setLayout(new GridLayout(1, 4, 0, 0));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volverAppBanco();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel2.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("");
		panel2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel2.add(lblNewLabel_1);
		
		JButton btnCrear = new JButton("Realizar transferencia");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validar()) {
					if (insertarTransferencia()) {
						volverAppBanco();
					}
				}
			}
		});
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel2.add(btnCrear);
		
		WindowUtils.centerOnScreen(this);
		this.setVisible(true);
		
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	System.exit(0);
            }
        });

	}
	
    public boolean validar() {
    	textIban.setText(textIban.getText().trim());
    	String iban = textIban.getText();
    	if (iban.equals("")) {
    		textIban.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el IBAN destino de la transferencia", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isIBAN(iban)) {
    		textIban.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El IBAN destino tecleado es erroneo. El formato debe ser ESnn nnnn nnnn nn nnnnnnnnnn", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	textConcepto.setText(textConcepto.getText().trim());
    	String concepto = textConcepto.getText();
    	if (concepto.equals("")) {
    		textConcepto.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el concepto", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	textImporte.setText(textImporte.getText().trim());
    	String importe = textImporte.getText();
    	if (importe.equals("")) {
    		textImporte.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el importe", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isImporte(importe)) {
    		textImporte.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El importe tecleado es erroneo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}
	
		if (this.cuenta.getIBAN().equalsIgnoreCase(iban)) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El IBAN de destino no puede ser el mismo que el de origen.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
		
	   	this.transferencia = new OperacionTransferencia(new java.util.Date(), this.cuenta.getIBAN(), concepto, Float.parseFloat(importe) * -1, iban); 
	   	
    	return true;
    	
    }

    public boolean insertarTransferencia() {
		// Se da de alta el nuevo usuario
    	OperacionTransferenciaBD tr = new OperacionTransferenciaBD();

    	String msg = tr.insertarTransferencis(this.transferencia);
    	if (msg != null) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}
    	
    	// Se vuelven a cargar las cuentas del usuario para que añada la transferencia insertada
       	CuentaBD cuenbd = new CuentaBD();
       	this.usuario.setCuentas(cuenbd.cargarCuentasUsuario(this.usuario));
        	

        JOptionPane mensaje = new JOptionPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
        mensaje.showMessageDialog(null, "Transferencia dada de alta correctamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
        
        // Registro en el log el alta de transferencia
        Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        String fecha = formatter.format(this.transferencia.getFecha());

        LOGGER.log(Level.INFO, "Transferencia del usuario "+this.usuario.getDni()+" y cuenta "+this.cuenta.getIBAN()+", dada de alta en fecha: "+fecha);
        
        return true;
    }
    
    // Metodo para validar un IBAN    
    public boolean isIBAN(String dni) {
    	Pattern pat = null;
    	Matcher mat = null;
    	// ESnn nnnn nnnn nn nnnnnnnnnn
    	pat = Pattern.compile("^(ES)[0-9]{2}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{2}[ ][0-9]{10}$");
    	mat = pat.matcher(dni);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Metodo para validar un importe    
    public boolean isImporte(String importe) {
    	Pattern pat = null;
    	Matcher mat = null;
    	pat = Pattern.compile("^[0-9]+([\\.,][0-9]{1,2})?$");
    	mat = pat.matcher(importe);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

	private void volverAppBanco() {
		this.setVisible(false);
		AppBanco frame = new AppBanco(this.usuario);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

