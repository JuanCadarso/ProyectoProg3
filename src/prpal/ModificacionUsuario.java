package prpal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import datos.UsuarioBD;
import info.Cuenta;
import info.Usuario;
import seguridad.MD5;

public class ModificacionUsuario extends JFrame implements ActionListener {

	private final static Logger LOGGER = Logger.getLogger("AppBanco.ModificacionUsuario");
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApell1;
	private JTextField textApell2;
	private JTextField textDni;
	private JTextField textEmail;
	private JPasswordField password_2;
	private JPasswordField password_1;
	
	private Usuario usuario;
	private String dni_origen;
	private Cuenta cuenta;
	private JTextField textIban;
	
	/**
	 * Create the frame.
	 */
	public ModificacionUsuario(Usuario user) {
		this.usuario = user;
		this.cuenta = null;
		this.dni_origen = user.getDni();
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 618, 430);
		this.setTitle("Modificacion de Usuario");
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(11, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel1.add(panel);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_6 = new JLabel("");
		panel_9.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel_9.add(label_7);
		
		JPanel panel_1 = new JPanel();
		panel1.add(panel_1);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNombre = new JLabel("Nombre:  ");
		panel_10.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_10.add(textNombre);
		textNombre.setColumns(20);
		textNombre.setText(this.usuario.getNombre());
		
		JPanel panel_2 = new JPanel();
		panel1.add(panel_2);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPrimerApellido = new JLabel("Primer Apellido:  ");
		panel_11.add(lblPrimerApellido);
		lblPrimerApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrimerApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textApell1 = new JTextField();
		textApell1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_11.add(textApell1);
		textApell1.setColumns(20);
		textApell1.setText(this.usuario.getApellido1());
		
		JPanel panel_3 = new JPanel();
		panel1.add(panel_3);
		
		JPanel panel_12 = new JPanel();
		panel_3.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:  ");
		panel_12.add(lblSegundoApellido);
		lblSegundoApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSegundoApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textApell2 = new JTextField();
		textApell2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_12.add(textApell2);
		textApell2.setColumns(20);
		textApell2.setText(this.usuario.getApellido2());
		
		JPanel panel_4 = new JPanel();
		panel1.add(panel_4);
		
		JPanel panel_13 = new JPanel();
		panel_4.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDni = new JLabel("DNI:  ");
		panel_13.add(lblDni);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textDni = new JTextField();
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_13.add(textDni);
		textDni.setColumns(10);
		textDni.setText(this.usuario.getDni());
		
		JPanel panel_5 = new JPanel();
		panel1.add(panel_5);
		
		JPanel panel_14 = new JPanel();
		panel_5.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmail = new JLabel("Email:  ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_14.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textEmail.setColumns(20);
		panel_14.add(textEmail);
		textEmail.setText(this.usuario.getEmail());
		
		JPanel panel_6 = new JPanel();
		panel1.add(panel_6);
		
		JPanel panel_15 = new JPanel();
		panel_6.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblContrasea = new JLabel("            Contrase\u00F1a:  ");
		panel_15.add(lblContrasea);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		
		password_1 = new JPasswordField();
		password_1.setColumns(10);
		panel_15.add(password_1);
		
		JPanel panel_7 = new JPanel();
		panel1.add(panel_7);
		
		JPanel panel_17 = new JPanel();
		panel_7.add(panel_17);
		panel_17.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblRepetirVontrasea = new JLabel("Repetir Contrase\u00F1a:  ");
		panel_17.add(lblRepetirVontrasea);
		lblRepetirVontrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRepetirVontrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		
		password_2 = new JPasswordField();
		password_2.setColumns(10);
		panel_17.add(password_2);
		
		JPanel panel_18 = new JPanel();
		panel1.add(panel_18);
		panel_18.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_19 = new JPanel();
		panel_18.add(panel_19);
		panel_19.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCuenta = new JLabel("Cuentas:  ");
		panel_19.add(lblCuenta);
		lblCuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		// Cargo el combo con todas las cuentas
		DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
		List<Cuenta> lcuentas = this.usuario.getCuentas();
		for (int i = 0;i < lcuentas.size(); i++) {
			Cuenta cuen = lcuentas.get(i);
			dcm.addElement(cuen);
		}
		JComboBox comboBox = new JComboBox(dcm);
		comboBox.setMaximumRowCount(15);
		panel_19.add(comboBox);
		
		JPanel panel_20 = new JPanel();
		panel1.add(panel_20);
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21);
		panel_21.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblAadirCuenta = new JLabel("A\u00F1adir cuenta:  ");
		lblAadirCuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAadirCuenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_21.add(lblAadirCuenta);
		
		textIban = new JTextField();
		textIban.setText((String) null);
		textIban.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textIban.setColumns(30);
		panel_21.add(textIban);
		
		JPanel panel_8 = new JPanel();
		panel1.add(panel_8);
		
		JPanel panel_16 = new JPanel();
		panel_8.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_25 = new JLabel("");
		panel_16.add(label_25);
		
		JLabel label_24 = new JLabel("");
		panel_16.add(label_24);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setLayout(new GridLayout(1, 4, 0, 0));
		
		//Boton de volver al menú inicial
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volverLogin();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel2.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("");
		panel2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel2.add(lblNewLabel_1);
		
		JButton btnCrear = new JButton("Modificar");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validar()) {
					if (modificarUsuario()) {
						volverLogin();
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
	
	
	//Metodo para la validacion de cada uno de los campos
    public boolean validar() {
    	textNombre.setText(textNombre.getText().trim());
    	String nombre = textNombre.getText();
    	if (nombre.equals("")) {
    		textNombre.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isNomApell(nombre)) {
    		textNombre.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El nombre tecleado es erroneo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	textApell1.setText(textApell1.getText().trim());
    	String apell1 = textApell1.getText();
    	if (apell1.equals("")) {
    		textApell1.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el primer apellido", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isNomApell(apell1)) {
    		textApell1.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El primer apellido tecleado es erroneo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	textApell2.setText(textApell2.getText().trim());
    	String apell2 = textApell2.getText();
    	if (apell2.equals("")) {
    		textApell2.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el segundo apellido", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isNomApell(apell2)) {
    		textApell2.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El segundo apellido tecleado es erroneo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	textDni.setText(textDni.getText().trim());
    	String dni = textDni.getText();
    	if (dni.equals("")) {
    		textDni.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el DNI", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isDNI(dni)) {
    		textDni.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El DNI tecleado es erroneo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	textEmail.setText(textEmail.getText().trim());
    	String email = textEmail.getText();
    	if (email.equals("")) {
    		textEmail.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear el Email", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	if (!isEmail(email)) {
    		textEmail.requestFocus();
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El e-mail tecleado es erroneo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}

    	String pass1 = String.copyValueOf(this.password_1.getPassword()).trim();
    	String pass2 = String.copyValueOf(this.password_2.getPassword()).trim();

    	if (!pass1.equals("") || !pass2.equals("")) {
	        if (pass1.equals("")) {
	    		password_1.requestFocus();
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "Debe teclear la contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	}
	
	    	if (!isPassword(pass1)) {
	    		password_1.requestFocus();
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "La contraseña tecleada es erronea", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	}
	
	    	if (pass2.equals("")) {
	    		password_2.requestFocus();
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "Debe repetir la contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	}
	
	    	if (!pass1.equals(pass2)) {
	    		password_2.requestFocus();
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "Las contraseñas tecleadas no coinciden", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	} 
    	}
    	
      	textIban.setText(textIban.getText().trim());
    	String iban = textIban.getText();
    	if (!iban.equals("")) {
	    	if (!isIBAN(iban)) {
	    		textIban.requestFocus();
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "El IBAN tecleado es erroneo. El formato debe ser ESnn nnnn nnnn nn nnnnnnnnnn", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	}
	    	// Validamos que no exista la cuenta
	    	CuentaBD cu = new CuentaBD();
	    	Cuenta cuen = cu.verCuenta(iban);
	    	
			if (cuen.getIBAN() != null) {
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "La cuenta ya existe. Modificacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	    	// Creamos una instancia para la nueva cuenta del usuario
	    	this.cuenta = new Cuenta(iban, dni);
    	}
    	
    	// Validamos que no exista el usuario si se cambia el DNI
    	if (!dni_origen.equalsIgnoreCase(dni)) {
	    	UsuarioBD us = new UsuarioBD();
	    	Usuario user = us.verUsuario(dni);
	    	
			if (user.getDni() != null) {
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, "Ya existe un usuario con el nuevo DNI. Modificacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
    	}
    			
    	// Creamos una instancia con el nuevo usuario
    	this.usuario = new Usuario(dni, nombre, apell1, apell2, email, "");
	    if (!pass1.equals("")) {
	        MD5 md;
			try {
				md = MD5.getInstance();
		        this.usuario.setPassword(md.hashData(pass1.getBytes()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    	return true;
    	
    }

    //Método para modificar un usuario
    public boolean modificarUsuario() {
		// Se modifica el usuario
    	UsuarioBD us = new UsuarioBD();

    	String msg = us.modificarUsuario(this.dni_origen, this.usuario);
    	if (msg != null) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Error modificando usuario: "+msg, "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}
    	
    	// Si se cambia el DNI del usuario se cambia dicho dni en todas sus cuentas
    	if (!dni_origen.equalsIgnoreCase(this.usuario.getDni())) {
	    	CuentaBD cu = new CuentaBD();
	    	
	    	msg = cu.cambiarDniCuentas(this.usuario.getDni(), dni_origen);
	    	if (msg != null) {
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null,"Error modificando cuentas de usuario: "+ msg, "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	}
    	}
    	
    	if (this.cuenta != null) {
			// Se da de alta la nueva cuenta del usuario
	    	CuentaBD cu = new CuentaBD();
	
	    	msg = cu.insertarCuenta(this.cuenta);
	    	if (msg != null) {
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null,"Error insertando cuenta de usuario: "+ msg, "Aviso", JOptionPane.WARNING_MESSAGE);
	            return false;
	    	}
    	}
    	
        JOptionPane mensaje = new JOptionPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
        mensaje.showMessageDialog(null, "Usuario modificado correctamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
        
        // Registro en el log la modificacion de usuario
        if (!dni_origen.equalsIgnoreCase(this.usuario.getDni())) {
        	LOGGER.log(Level.INFO, "Usuario "+ this.dni_origen +" ha cambiado al DNI " + this.usuario.getDni());
        }
        LOGGER.log(Level.INFO, "Usuario "+this.usuario.getDni()+" modificado");
        
        return true;
    }
    
    // Metodo para validar un DNI    
    public boolean isDNI(String dni) {
    	Pattern pat = null;
    	Matcher mat = null;
    	// https://www.discoduroderoer.es/ejercicios-propuestos-y-resueltos-expresiones-regulares-en-java/
    	pat = Pattern.compile("^[0-9]{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E]$");
    	mat = pat.matcher(dni);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Metodo para validar nombres y apellidos    
    public boolean isNomApell(String nom) {
    	Pattern pat = null;
    	Matcher mat = null;
    	// https://www.discoduroderoer.es/ejercicios-propuestos-y-resueltos-expresiones-regulares-en-java/
    	pat = Pattern.compile("^([A-Z]{1}[a-z]+[ ]?){1,2}$");
    	mat = pat.matcher(nom);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Metodo para validar correo electronio    
    public boolean isEmail(String correo) {
    	Pattern pat = null;
    	Matcher mat = null;
//    	pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
    	pat = Pattern.compile("^([\\w-]+\\.)*?[\\w-]+@[\\w-]+\\.([\\w-]+\\.)*?[\\w]+$");
    	mat = pat.matcher(correo);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Metodo para validar password    
    public boolean isPassword(String pass) {
    	if (pass == null) {
    		return false;
    	}

    	if (pass.length() < 8) {
    		return false;
    	}

    	boolean numero = false;
    	boolean simbolo = false;
    	boolean mayuscula = false;
    	
    	for (int i = 0;i < pass.length(); i++) {
    		char car  = pass.charAt(i);
    		if (car >= 48 && car <= 57) {
    			numero = true;
    		}
    		if (car >= 65 && car <= 90) {
    			mayuscula = true;
    		}
    		if (car >= 32 && (car < 48 || car > 57) && (car < 65 || car > 90) && (car < 97 || car > 122)) {
    			simbolo = true;
    		}
    	}

    	if (numero && mayuscula && simbolo) {
    		return true;
    	} else {
    		return false;
    	}
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

    //Función para volver al menu de login
    private void volverLogin() {
		this.setVisible(false);
		Login frame = new Login();
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


