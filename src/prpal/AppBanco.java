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
import info.Usuario;
import seguridad.MD5;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.GridLayout;

public class AppBanco extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Usuario usuario;

	/**
	 * Create the frame.
	 */
	public AppBanco(Usuario user) {
		this.usuario = user;
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 527, 375);
		this.setTitle("Usuario: "+user.getNombre()+" "+user.getApellido1()+" "+user.getApellido2());
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Seleccionar cuenta: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel1.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		panel1.add(comboBox);
		
		JLabel label1 = new JLabel("");
		panel1.add(label1);
		
		JLabel label2 = new JLabel("");
		panel1.add(label2);
		
		JButton btnNewButton = new JButton("Cerrar sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volverLogin();
			}
		});
		panel1.add(btnNewButton);
		
		WindowUtils.centerOnScreen(this);
		this.setVisible(true);
		
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	System.exit(0);
            }
        });

	}

	//Método para cerra sesion
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
