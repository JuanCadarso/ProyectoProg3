package prpal;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.util.OS;
import com.l2fprod.util.WindowUtils;

import datos.CuentaBD;
import datos.UsuarioBD;
import info.Cuenta;
import info.Operacion;
import info.OperacionCajero;
import info.OperacionPagoConTarjeta;
import info.OperacionTransferencia;
import info.OperacionVentanilla;
import info.Usuario;
import seguridad.MD5;

import javax.swing.SwingConstants;

public class Login extends JFrame implements ActionListener {

	// El log de nivel superior
    private final static Logger LOG_RAIZ = Logger.getLogger("AppBanco");

	// El log para ESTA clase en particular
	private final static Logger LOGGER = Logger.getLogger("AppBanco.Login");
	// El log para AltaUsuario
	private final static Logger LOG_ALTAUSUARIO = Logger.getLogger("AppBanco.AltaUsuario");
	// El log para AppBanco
	private final static Logger LOG_APPBANCO = Logger.getLogger("AppBanco.AppBanco");
	// El log para AltaTransferencia
	private final static Logger LOG_ALTATRANSFERENCIA = Logger.getLogger("AppBanco.AltaTransferencia");
	// El log para ModificacionUsuario
	private final static Logger LOG_MODIFICACIONUSUARIO = Logger.getLogger("AppBanco.ModificacionUsuario");

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public Login() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 177);
		this.setTitle("Seguridad appBanco");
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumbreDeUsuario = new JLabel("DNI: ");
		lblNumbreDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumbreDeUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		lblNumbreDeUsuario.setBounds(89, 30, 117, 14);
		contentPane.add(lblNumbreDeUsuario);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(208, 27, 149, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
		lblPassword.setBounds(90, 68, 112, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordField.setBounds(208, 66, 190, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Crear usuario");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaUsuario();
			}
		});
		btnNewButton.setBounds(21, 114, 124, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Aceptar");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acceder();			
			}
		});
		button.setBounds(436, 114, 94, 23);
		contentPane.add(button);
		
		JButton btnModificarUsuario = new JButton("Modificar usuario");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarUsuario();
			}
		});
		btnModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificarUsuario.setBounds(208, 114, 149, 23);
		contentPane.add(btnModificarUsuario);
		
		// Si la base de datos no existe se crea
		UsuarioBD app = new UsuarioBD();
		app.crearBDSqlite();
	}
	
	//Llamada a la ventana del alta de usuario
    private void altaUsuario() {
    	this.setVisible(false);
    	
    	AltaUsuario m = new AltaUsuario();
    }
    
    //Acceso de la ventana principal de la aplicación
    private void acceder() {
        String dni = this.textField.getText().toUpperCase().trim();
        this.textField.setText(dni);
        String pass = String.copyValueOf(this.passwordField.getPassword()).trim();
        
        if (dni.equals("") || pass.equals("")) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear DNI y contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
        	UsuarioBD us = new UsuarioBD();

        	try {
	            String clave = "";
	            
                MD5 md = MD5.getInstance();
                clave = md.hashData(pass.getBytes());

				Usuario user = us.validarUsuario(dni, clave);
				if (user.getDni() == null) {
		            JOptionPane mensaje = new JOptionPane();
		            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
		            mensaje.showMessageDialog(null, user.getNombre(), "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					user = cargarCuentasUsuario(user);
					
					if (user.getCuentas().size() <= 0) {
			            JOptionPane mensaje = new JOptionPane();
			            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
			            mensaje.showMessageDialog(null, "El usuario no tiene cuentas para gestionar.", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
						System.out.println("Usuario "+user.getNombre()+" "+user.getApellido1()+" logueado.");
						List<Cuenta> lcuentas = user.getCuentas();
						for (int i = 0;i < lcuentas.size(); i++) {
							Cuenta cuen = lcuentas.get(i);
							System.out.println("\tCuenta: "+cuen.getIBAN());
							for(Entry<Date, Operacion> entry : cuen.getMovimientos().entrySet()) {
							  Date key = entry.getKey();
							  Operacion value = entry.getValue();
							  String tipo = "";
							  if (value instanceof OperacionCajero) {
								  OperacionCajero op = (OperacionCajero) value;
								  tipo = "OperacionCajero - "+op.getConcepto();
							  }
							  if (value instanceof OperacionPagoConTarjeta) {
								  OperacionPagoConTarjeta op = (OperacionPagoConTarjeta) value;
								  tipo = "OperacionPagoConTarjeta - "+op.getConcepto();
							  }
							  if (value instanceof OperacionTransferencia) {
								  OperacionTransferencia op = (OperacionTransferencia) value;
								  tipo = "OperacionTransferencia - "+op.getConcepto();
							  }
							  if (value instanceof OperacionVentanilla) {
								  OperacionVentanilla op = (OperacionVentanilla) value;
								  tipo = "OperacionVentanilla - "+op.getConcepto();
							  }
							  System.out.println("\t\tOperacion: "+key+" - "+tipo);
							}

						}
		            	this.setVisible(false);
		            	
		            	// Registro en el logger la entrada del usuario
		            	LOGGER.log(Level.INFO, "Entrada del usuario "+user.getDni()+" en el sistema");
		            	
		            	AppBanco m = new AppBanco(user);
					}
				}
			} catch (Exception e) {
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			} 
        }

    }

    //Metodo para la carga de las cuentas de usuario
    private Usuario cargarCuentasUsuario(Usuario user) {
    	Usuario usuar = user;
    	
    	CuentaBD cuenbd = new CuentaBD();
    	usuar.setCuentas(cuenbd.cargarCuentasUsuario(user));
    	
    	return usuar;
    }
    
    //Metodo que llama a la modificacion del usuario
    private void modificarUsuario() {
        String dni = this.textField.getText().toUpperCase().trim();
        this.textField.setText(dni);
        String pass = String.copyValueOf(this.passwordField.getPassword()).trim();
        
        if (dni.equals("") || pass.equals("")) {
            JOptionPane mensaje = new JOptionPane();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
            mensaje.showMessageDialog(null, "Debe teclear DNI y contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
        	UsuarioBD us = new UsuarioBD();

        	try {
	            String clave = "";
	            
                MD5 md = MD5.getInstance();
                clave = md.hashData(pass.getBytes());

				Usuario user = us.validarUsuario(dni, clave);
				if (user.getDni() == null) {
		            JOptionPane mensaje = new JOptionPane();
		            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
		            mensaje.showMessageDialog(null, user.getNombre(), "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					user = cargarCuentasUsuario(user);
					
	            	this.setVisible(false);
	            	
	            	// Registro en el logger la entrada del usuario en modificacion
	            	LOGGER.log(Level.INFO, "Acceso a modificacion del usuario "+user.getDni());
	            	
	            	ModificacionUsuario m = new ModificacionUsuario(user);
				}
			} catch (Exception e) {
	            JOptionPane mensaje = new JOptionPane();
	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	            mensaje.setLocation(screenSize.width / 2 - mensaje.getSize().width / 2, screenSize.height / 2 - mensaje.getSize().height / 2);
	            mensaje.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			} 
        }

    	
    }
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Logger
		// Fuente https://medium.com/el-acordeon-del-programador/logs-en-java-con-java-util-logging-d344ae2ba7bc
		Handler consoleHandler = new ConsoleHandler();
        Handler fileHandler = null;
        
		try {
			fileHandler = new FileHandler("./AppBanco.xml", true);
	        // Para poner el logger en formato texto, si no se especifica sale en formato XML
	        //SimpleFormatter simpleFormatter = new SimpleFormatter();
	        //fileHandler.setFormatter(simpleFormatter);
			XMLFormatter formatter = new XMLFormatter();
			fileHandler.setFormatter(formatter);
			
	        LOG_RAIZ.addHandler(consoleHandler);
	        LOG_RAIZ.addHandler(fileHandler);

	        consoleHandler.setLevel(Level.ALL);
	        fileHandler.setLevel(Level.ALL);

	        LOGGER.log(Level.INFO, "AppBanco arrancada. Log inicializado");


		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.log(Level.SEVERE, "Error de Seguridad: "+e1.getMessage());
		} catch (IOException e1) {
			LOGGER.log(Level.SEVERE, "Error de IO: "+e1.getMessage());
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cargarLookAndFeel(null);
					
					Login frame = new Login();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			        frame.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	System.out.println("Salida de la aplicacion");
			                System.exit(0);
			            }
			        });
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	//Carga del look and feel
    public static void cargarLookAndFeel(String nombreTema) {
        // if we are running with JDK1.4 decorates the frames and dialogs
        if (OS.isOneDotFourOrMore()) {
            try {
                java.lang.reflect.Method method = JFrame.class.getMethod("setDefaultLookAndFeelDecorated", new Class[]{boolean.class});
                method.invoke(null, new Object[]{Boolean.TRUE});
                method = JDialog.class.getMethod("setDefaultLookAndFeelDecorated", new Class[]{boolean.class});
                method.invoke(null, new Object[]{Boolean.TRUE});
            } catch (SecurityException ex) {
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
        try {
            nombreTema = "temas/crystal2themepack.zip";
            // first tell SkinLF which theme to use
            Skin theSkinToUse = SkinLookAndFeel.loadThemePack(nombreTema);
            SkinLookAndFeel.setSkin(theSkinToUse);
            // finally set the Skin Look And Feel
            UIManager.setLookAndFeel(new SkinLookAndFeel());

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
