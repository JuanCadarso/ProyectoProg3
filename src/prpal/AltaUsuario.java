package prpal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.l2fprod.util.WindowUtils;

import datos.CuentaBD;
import datos.UsuarioBD;
import info.Usuario;
import seguridad.MD5;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;

public class AltaUsuario extends JFrame implements ActionListener {

	//Logger para la clase alta usuario
	private final static Logger LOGGER = Logger.getLogger("AppBanco.AltaUsuario");
	
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
	
	/**
	 * Creacion de la pantalla de AltaUsuario
	 */
	public AltaUsuario() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 483, 311);
		this.setTitle("Alta de Usuario");
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1);
		panel1.setLayout(new GridLayout(9, 1, 0, 0));
		
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
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validar()) {
					if (insertarUsuario()) {
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
	
	/**
	 * Méetpdp validar() para la validacion de los atributos de un nuevo usuario (si alguno de los
	 * atributos no se puede validar lo avisa mediante un dialogo, para la validación como tal
	 * se apoya en métodos implementados al final de esta misma clase). También
	 * comprueba que el nuevo usuario no exista ya previamente. Si el nuevo usuario ya existe
	 * se deniega. Si no existe previamente se crea uno nuevo
	 * @return boolean
	 */
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
    	
    	// Validamos que no exista el usuario
    	UsuarioBD us = new UsuarioBD();
    	Usuario user = us.verUsuario(dni);
    	
		if (user.getDni() != null) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "El usuario ya existe. Alta cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
		
    	// Creamos una instancia con el nuevo usuario
    	this.usuario = new Usuario(dni, nombre, apell1, apell2, email, "");
        MD5 md;
		try {
			md = MD5.getInstance();
	        this.usuario.setPassword(md.hashData(pass1.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return true;
    	
    }

    /**
     * Insercion de un nuevo usuario
     * @return
     */
    public boolean insertarUsuario() {
		// Se da de alta el nuevo usuario
    	UsuarioBD us = new UsuarioBD();

    	String msg = us.insertarUsuario(this.usuario);
    	if (msg != null) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
    	}
    	
        JOptionPane mensaje = new JOptionPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
        mensaje.showMessageDialog(null, "Usuario dado de alta correctamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
        
        // Registro en el log el alta de usuario
        LOGGER.log(Level.INFO, "Usuario "+this.usuario.getDni()+" dado de alta");
        
        return true;
    }
    
    /**
     * Metodo para validar el dni a través de expresiones regulares  
     * @param dni
     * @return
     */
    public boolean isDNI(String dni) {
    	Pattern pat = null;
    	Matcher mat = null;
    	pat = Pattern.compile("^[0-9]{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E]$");
    	mat = pat.matcher(dni);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /** 
     * Método para validar los nombres y apellidos a través de expresiones regulares
     * @param nom
     * @return
     */
    public boolean isNomApell(String nom) {
    	Pattern pat = null;
    	Matcher mat = null;
    	pat = Pattern.compile("^([A-Z]{1}[a-z]+[ ]?){1,2}$");
    	mat = pat.matcher(nom);

    	if (mat.find()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /** 
     * Método para validar el mail a través de expresiones regulares
     * @param correo
     * @return
     */
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

    /**   
     * Método para validar la contrseña, recorriendo los caracteres de la misma
     * @param pass
     * @return
     */
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
    	
    	//Utilizamos ASCII para verficar
    	for (int i = 0;i < pass.length(); i++) {
    		//Extraemos caracter a caracter para la comprobacion
    		char car  = pass.charAt(i);
    		//Que haya al menos un numero
    		if (car >= 48 && car <= 57) {
    			numero = true;
    		}
    		//Que haya al menos una mayuscula
    		if (car >= 65 && car <= 90) {
    			mayuscula = true;
    		}
    		//Que haya al menos un simbolo
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

    /**
     * Vuelve a la pantalla de login
     */
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