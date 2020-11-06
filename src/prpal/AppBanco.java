package prpal;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
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

import datos.UsuarioBD;
import info.Cuenta;
import info.Usuario;
import seguridad.MD5;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.GridLayout;

public class AppBanco extends JFrame implements ActionListener {
	
	//Logger para la clase AppBAnco
	private final static Logger LOGGER = Logger.getLogger("AppBanco.AppBanco");

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel dcm;
	
	private Usuario usuario;
	private JComboBox comboBox;
	private JTextField textSaldo;
	private Cuenta cuenta;

	/**
	 * Creación de la pantalla de AppBAnco
	 */
	public AppBanco(Usuario user) {
		this.usuario = user;
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 527, 375);
		this.setTitle("Usuario: "+user.getNombre()+" "+user.getApellido1()+" "+user.getApellido2());
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Seleccionar cuenta: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(lblNewLabel);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Saldo actual: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel2.add(lblNewLabel_1);
		
		textSaldo = new JTextField();
		textSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		textSaldo.setEditable(false);
		panel2.add(textSaldo);
		textSaldo.setColumns(10);
		
		JPanel panel3 = new JPanel();
		contentPane.add(panel3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Cerrar sesion");
		panel3.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volverLogin();
			}
		});
		
		// Cargo el combo con todas las cuentas
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
		List<Cuenta> lcuentas = this.usuario.getCuentas();
		for (int i = 0;i < lcuentas.size(); i++) {
			Cuenta cuen = lcuentas.get(i);
			dcm.addElement(cuen);
		}
		
		comboBox = new JComboBox(dcm);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambioCuenta();
			}
		});
		comboBox.setMaximumRowCount(15);
		panel1.add(comboBox);
		// Se selecciona la primera cuenta del combobox
		this.cuenta = (Cuenta) comboBox.getSelectedItem();
		textSaldo.setText(cuenta.getSaldo()+"");
		
        // Registro en el log la consulta de la cuenta
        LOGGER.log(Level.INFO, "El usuario "+this.usuario.getDni()+" consulta la cuenta "+cuenta.getIBAN());

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
	 * Regula la cuenta que se muestra en cada momento, según la cuenta
	 * seleccionada en el comboBox de cuentas de la parte superior de la ventana.
	 */
	private void cambioCuenta() {
		Cuenta cuen = (Cuenta) comboBox.getSelectedItem();
		
		// Si ha seleccionado la misma cuenta no se hace nada
		if (!cuen.getIBAN().equals(this.cuenta.getIBAN())) {
			this.cuenta = cuen;
			textSaldo.setText(cuenta.getSaldo()+"");
	        // Registro en el log la consulta de la cuenta
	        LOGGER.log(Level.INFO, "El usuario "+this.usuario.getDni()+" consulta la cuenta "+cuenta.getIBAN());
		}
	}

	/**
	 * Método ppara retorna a la pantalla de login
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
		System.out.println(e.getActionCommand());
	}
}
