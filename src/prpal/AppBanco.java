package prpal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.l2fprod.util.WindowUtils;

import info.Cuenta;
import info.Operacion;
import info.OperacionCajero;
import info.OperacionPagoConTarjeta;
import info.OperacionTransferencia;
import info.OperacionVentanilla;
import info.Usuario;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class AppBanco extends JFrame implements ActionListener {

	private final static Logger LOGGER = Logger.getLogger("AppBanco.AppBanco");
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel dcm;
	
	private Usuario usuario;
	private JComboBox comboBox;
	private JTextField textSaldo;
	private Cuenta cuenta;
	
	private List<Operacion> l_movimientos;
	private int num_paginas;
	private int pag_actual;
	private final int MOV_PAGINA = 5;
	private JLabel lblTexto;
	private JLabel lblPagina;
	private JButton btnAtras;
	private JButton btnAdelante;
	private JLabel lbl01_1_Fecha;
	private JLabel lbl01_1_Descripcion;
	private JLabel lbl01_1_Importe;
	private JLabel lbl01_2_Operacion;
	private JPanel panel01_1;
	private JPanel panel01_2;
	private JPanel panel01;
	private JPanel panel02;
	private JPanel panel02_1;
	private JLabel lbl02_1_Fecha;
	private JLabel lbl02_1_Descripcion;
	private JLabel lbl02_1_Importe;
	private JPanel panel02_2;
	private JLabel lbl02_2_Operacion;
	private JLabel lbl03_1_Fecha;
	private JLabel lbl03_1_Descripcion;
	private JLabel lbl03_1_Importe;
	private JPanel panel03_1;
	private JPanel panel03;
	private JPanel panel03_2;
	private JLabel lbl03_2_Operacion;
	private JPanel panel04;
	private JPanel panel04_1;
	private JLabel lbl04_1_Fecha;
	private JLabel lbl04_1_Importe;
	private JLabel lbl04_1_Descripcion;
	private JLabel lbl04_2_Operacion;
	private JPanel panel05;
	private JPanel panel05_1;
	private JPanel panel05_2;
	private JLabel lbl05_1_Fecha;
	private JLabel lbl05_1_Descripcion;
	private JLabel lbl05_1_Importe;
	private JLabel lbl05_2_Operacion;
	private JPanel panel_Centro;
	private JPanel panel04_2;
	
	/**
	 * Create the frame.
	 */
	public AppBanco(Usuario user) {
		setFont(new Font("Dialog", Font.BOLD, 12));
		this.usuario = user;
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 742, 429);
		this.setTitle("Usuario: "+user.getNombre()+" "+user.getApellido1()+" "+user.getApellido2());
        WindowUtils.centerOnScreen(this);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		
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
		panel1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel1.add(panel);
		
		JLabel lblNewLabel = new JLabel("Seleccionar cuenta: ");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBox = new JComboBox(dcm);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambioCuenta();
			}
		});
		comboBox.setMaximumRowCount(15);
		// Se selecciona la primera cuenta del combobox
		this.cuenta = (Cuenta) comboBox.getSelectedItem();
		float saldo = cargarMovimientos();
		this.num_paginas = this.l_movimientos.size() / MOV_PAGINA;
		if ((this.l_movimientos.size() % MOV_PAGINA) > 0) {
			this.num_paginas++;
		}
		this.pag_actual = 1;
		
		JPanel panel2 = new JPanel();
		panel1.add(panel2);
		
		JLabel lblNewLabel_1 = new JLabel("Saldo actual: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel2.add(lblNewLabel_1);
		
		textSaldo = new JTextField();
		textSaldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		textSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		textSaldo.setEditable(false);
		textSaldo.setText(saldo+"");
		panel2.add(textSaldo);
		textSaldo.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel1.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2);
		
		lblTexto = new JLabel("Ultimos movimientos");
		lblTexto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTexto.setText("Ultimos movimientos");
		panel_2.add(lblTexto);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3);
		
		Icon iconAtras = new ImageIcon("./imagenes/flecha_arriba.gif");
		btnAtras = new JButton(iconAtras);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginarMenos();
			}
		});
		panel_3.add(btnAtras);
		
		Icon iconAdelante = new ImageIcon("./imagenes/flecha_abajo.gif");
		btnAdelante = new JButton(iconAdelante);
		btnAdelante.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginarMas();
			}
		});
		panel_3.add(btnAdelante);
		
		JLabel lblNewLabel_2 = new JLabel("           ");
		panel_3.add(lblNewLabel_2);
		
		lblPagina = new JLabel("Pagina: 0 / 0");
		lblPagina.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPagina.setText("Pagina: "+this.pag_actual+" / "+this.num_paginas);
		panel_3.add(lblPagina);
		
		iniciarPanelCentral();
		presentarPagina();
		
		if (this.pag_actual >= this.num_paginas) {
			btnAdelante.setEnabled(false);
		} else {
			btnAdelante.setEnabled(true);
		}
		if (this.pag_actual <= 1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}

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
	
	private void iniciarPanelCentral() {
		panel_Centro = new JPanel();
		panel_Centro.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_Centro, BorderLayout.CENTER);
		panel_Centro.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel01 = new JPanel();
		panel_Centro.add(panel01);
		panel01.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel01_1 = new JPanel();
		panel01.add(panel01_1);
		panel01_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		lbl01_1_Fecha = new JLabel("");
		lbl01_1_Fecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl01_1_Fecha.setHorizontalAlignment(SwingConstants.LEFT);
		panel01_1.add(lbl01_1_Fecha);
		
		lbl01_1_Descripcion = new JLabel("");
		lbl01_1_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl01_1_Descripcion.setHorizontalAlignment(SwingConstants.LEFT);
		panel01_1.add(lbl01_1_Descripcion);
		
		lbl01_1_Importe = new JLabel("");
		lbl01_1_Importe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl01_1_Importe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel01_1.add(lbl01_1_Importe);
		
		panel01_2 = new JPanel();
		panel01.add(panel01_2);
		panel01_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbl01_2_Operacion = new JLabel("");
		lbl01_2_Operacion.setVerticalAlignment(SwingConstants.TOP);
		lbl01_2_Operacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl01_2_Operacion.setHorizontalAlignment(SwingConstants.LEFT);
		panel01_2.add(lbl01_2_Operacion);
		
		panel02 = new JPanel();
		panel_Centro.add(panel02);
		panel02.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel02_1 = new JPanel();
		panel02.add(panel02_1);
		panel02_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		lbl02_1_Fecha = new JLabel("");
		lbl02_1_Fecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl02_1_Fecha.setHorizontalAlignment(SwingConstants.LEFT);
		panel02_1.add(lbl02_1_Fecha);
		
		lbl02_1_Descripcion = new JLabel("");
		lbl02_1_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl02_1_Descripcion.setHorizontalAlignment(SwingConstants.LEFT);
		panel02_1.add(lbl02_1_Descripcion);
		
		lbl02_1_Importe = new JLabel("");
		lbl02_1_Importe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl02_1_Importe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel02_1.add(lbl02_1_Importe);
		
		panel02_2 = new JPanel();
		panel02.add(panel02_2);
		panel02_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbl02_2_Operacion = new JLabel("");
		lbl02_2_Operacion.setVerticalAlignment(SwingConstants.TOP);
		lbl02_2_Operacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl02_2_Operacion.setHorizontalAlignment(SwingConstants.LEFT);
		panel02_2.add(lbl02_2_Operacion);
		
		panel03 = new JPanel();
		panel_Centro.add(panel03);
		panel03.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel03_1 = new JPanel();
		panel03.add(panel03_1);
		panel03_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		lbl03_1_Fecha = new JLabel("");
		lbl03_1_Fecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl03_1_Fecha.setHorizontalAlignment(SwingConstants.LEFT);
		panel03_1.add(lbl03_1_Fecha);
		
		lbl03_1_Descripcion = new JLabel("");
		lbl03_1_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl03_1_Descripcion.setHorizontalAlignment(SwingConstants.LEFT);
		panel03_1.add(lbl03_1_Descripcion);
		
		lbl03_1_Importe = new JLabel("");
		lbl03_1_Importe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl03_1_Importe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel03_1.add(lbl03_1_Importe);
		
		panel03_2 = new JPanel();
		panel03.add(panel03_2);
		panel03_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbl03_2_Operacion = new JLabel("");
		lbl03_2_Operacion.setVerticalAlignment(SwingConstants.TOP);
		lbl03_2_Operacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl03_2_Operacion.setHorizontalAlignment(SwingConstants.LEFT);
		panel03_2.add(lbl03_2_Operacion);
		
		panel04 = new JPanel();
		panel_Centro.add(panel04);
		panel04.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel04_1 = new JPanel();
		panel04.add(panel04_1);
		panel04_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		lbl04_1_Fecha = new JLabel("");
		lbl04_1_Fecha.setHorizontalAlignment(SwingConstants.LEFT);
		lbl04_1_Fecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel04_1.add(lbl04_1_Fecha);
		
		lbl04_1_Descripcion = new JLabel("");
		lbl04_1_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl04_1_Descripcion.setHorizontalAlignment(SwingConstants.LEFT);
		panel04_1.add(lbl04_1_Descripcion);
		
		lbl04_1_Importe = new JLabel("");
		lbl04_1_Importe.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl04_1_Importe.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel04_1.add(lbl04_1_Importe);
		
		panel04_2 = new JPanel();
		panel04.add(panel04_2);
		panel04_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbl04_2_Operacion = new JLabel("");
		lbl04_2_Operacion.setVerticalAlignment(SwingConstants.TOP);
		lbl04_2_Operacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl04_2_Operacion.setHorizontalAlignment(SwingConstants.LEFT);
		panel04_2.add(lbl04_2_Operacion);
		
		panel05 = new JPanel();
		panel_Centro.add(panel05);
		panel05.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel05_1 = new JPanel();
		panel05.add(panel05_1);
		panel05_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		lbl05_1_Fecha = new JLabel("");
		lbl05_1_Fecha.setHorizontalAlignment(SwingConstants.LEFT);
		lbl05_1_Fecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel05_1.add(lbl05_1_Fecha);
		
		lbl05_1_Descripcion = new JLabel("");
		lbl05_1_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl05_1_Descripcion.setHorizontalAlignment(SwingConstants.LEFT);
		panel05_1.add(lbl05_1_Descripcion);
		
		lbl05_1_Importe = new JLabel("");
		lbl05_1_Importe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl05_1_Importe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel05_1.add(lbl05_1_Importe);
		
		panel05_2 = new JPanel();
		panel05.add(panel05_2);
		panel05_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbl05_2_Operacion = new JLabel("");
		lbl05_2_Operacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl05_2_Operacion.setVerticalAlignment(SwingConstants.TOP);
		lbl05_2_Operacion.setHorizontalAlignment(SwingConstants.LEFT);
		panel05_2.add(lbl05_2_Operacion);
				
	}
	
	private void limpiarPanelCentral() {
		lbl01_1_Fecha.setText("");
		lbl01_1_Descripcion.setText("");
		lbl01_1_Importe.setText("");
		lbl01_2_Operacion.setText("");
		
		lbl02_1_Fecha.setText("");
		lbl02_1_Descripcion.setText("");
		lbl02_1_Importe.setText("");
		lbl02_2_Operacion.setText("");
		
		lbl03_1_Fecha.setText("");
		lbl03_1_Descripcion.setText("");
		lbl03_1_Importe.setText("");
		lbl03_2_Operacion.setText("");
		
		lbl04_1_Fecha.setText("");
		lbl04_1_Descripcion.setText("");
		lbl04_1_Importe.setText("");
		lbl04_2_Operacion.setText("");
		
		lbl05_1_Fecha.setText("");
		lbl05_1_Descripcion.setText("");
		lbl05_1_Importe.setText("");
		lbl05_2_Operacion.setText("");
		
	}
	
	private void cambioCuenta() {
		Cuenta cuen = (Cuenta) comboBox.getSelectedItem();
		
		// Si ha seleccionado la misma cuenta no se hace nada
		if (!cuen.getIBAN().equals(this.cuenta.getIBAN())) {
			this.cuenta = cuen;
			float saldo = cargarMovimientos();
			textSaldo.setText(saldo+"");
			lblTexto.setText("Ultimos movimientos");
			this.num_paginas = this.l_movimientos.size() / MOV_PAGINA;
			if ((this.l_movimientos.size() % MOV_PAGINA) > 0) {
				this.num_paginas++;
			}
			this.pag_actual = 1;
			lblPagina.setText("Pagina: "+this.pag_actual+" / "+this.num_paginas);
			
			limpiarPanelCentral();
			presentarPagina();
			
			if (this.pag_actual >= this.num_paginas) {
				btnAdelante.setEnabled(false);
			} else {
				btnAdelante.setEnabled(true);
			}
			if (this.pag_actual <= 1) {
				btnAtras.setEnabled(false);
			} else {
				btnAtras.setEnabled(true);
			}

	        // Registro en el log la consulta de la cuenta
	        LOGGER.log(Level.INFO, "El usuario "+this.usuario.getDni()+" consulta la cuenta "+cuenta.getIBAN());
		}
	}

	
	//Se obtiene el saldo de esta cuenta y carga los movimiento de la cuenta ya ordenados.
	private float cargarMovimientos() {
		float saldo = 0;
		this.l_movimientos = new ArrayList<Operacion>();
		for(Entry<Date, Operacion> entry : this.cuenta.getMovimientos().entrySet()) {
		  Operacion value = entry.getValue();
		  this.l_movimientos.add(value);
		  saldo += value.getImporte();
		}
		
		return saldo;
	}
	
	private void paginarMas() {
		if (this.pag_actual < this.num_paginas) {
			this.pag_actual++;
			limpiarPanelCentral();
			presentarPagina();
		}
		if (this.pag_actual >= this.num_paginas) {
			btnAdelante.setEnabled(false);
		} else {
			btnAdelante.setEnabled(true);
		}
		if (this.pag_actual <= 1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}
		lblPagina.setText("Pagina: "+this.pag_actual+" / "+this.num_paginas);
	}
	
	private void paginarMenos() {
		if (this.pag_actual > 1) {
			this.pag_actual--;
			limpiarPanelCentral();
			presentarPagina();
		}
		if (this.pag_actual >= this.num_paginas) {
			btnAdelante.setEnabled(false);
		} else {
			btnAdelante.setEnabled(true);
		}
		if (this.pag_actual <= 1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}
		lblPagina.setText("Pagina: "+this.pag_actual+" / "+this.num_paginas);
	}
	
	private void presentarPagina() {
		if (this.pag_actual == 1) {
			lblTexto.setText("Ultimos movimientos");
		} else {
			if (this.pag_actual == this.num_paginas) {
				lblTexto.setText("Primeros movimientos");
			} else {
				lblTexto.setText("");
			}
		}
		int desde = (this.pag_actual - 1) * MOV_PAGINA;
		int hasta = desde + MOV_PAGINA - 1;
		if (this.pag_actual == this.num_paginas) {
			hasta = this.l_movimientos.size() - 1;
		}
		int cont = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		DecimalFormat decFormat = new DecimalFormat("################0.00 ");
		
		panel01_1.setBackground(new Color(245,245,245));
		panel01_2.setBackground(new Color(245,245,245));
		panel02_1.setBackground(new Color(245,245,245));
		panel02_2.setBackground(new Color(245,245,245));
		panel03_1.setBackground(new Color(245,245,245));
		panel03_2.setBackground(new Color(245,245,245));
		panel04_1.setBackground(new Color(245,245,245));
		panel04_2.setBackground(new Color(245,245,245));
		panel05_1.setBackground(new Color(245,245,245));
		panel05_2.setBackground(new Color(245,245,245));
		
		for (int i = desde;i <= hasta;i++) {
			cont++;
			Operacion op = this.l_movimientos.get(i);
			if (cont == 1) {
				//System.out.println(panel01_1.getBackground());
				panel01_1.setBackground(Color.LIGHT_GRAY);
				panel01_2.setBackground(Color.LIGHT_GRAY);
				lbl01_1_Fecha.setText(" "+format.format(op.getFecha()));
				lbl01_1_Descripcion.setText(op.getConcepto());
				lbl01_1_Importe.setText(decFormat.format(op.getImporte()));
				String tipo = "";
				if (op instanceof OperacionCajero) {
					OperacionCajero opt = (OperacionCajero) op;
					tipo = "Cajero - "+opt.getEstablecimiento();
				}
				if (op instanceof OperacionPagoConTarjeta) {
					OperacionPagoConTarjeta opt = (OperacionPagoConTarjeta) op;
					tipo = "Pago Con Tarjeta - "+opt.getBenificiario();
				}
				if (op instanceof OperacionTransferencia) {
					OperacionTransferencia opt = (OperacionTransferencia) op;
					if (op.getImporte() >= 0) {
						tipo = "Transferencia de "+opt.getIBANReceptor();
					} else {
						tipo = "Transferencia a "+opt.getIBANReceptor();
					}
				}
				if (op instanceof OperacionVentanilla) {
					OperacionVentanilla opt = (OperacionVentanilla) op;
					tipo = "Ventanilla - "+opt.getSucursal();
				}
				lbl01_2_Operacion.setText(" "+tipo);
			}
			if (cont == 2) {
				lbl02_1_Fecha.setText(" "+format.format(op.getFecha()));
				lbl02_1_Descripcion.setText(op.getConcepto());
				lbl02_1_Importe.setText(decFormat.format(op.getImporte()));
				String tipo = "";
				if (op instanceof OperacionCajero) {
					OperacionCajero opt = (OperacionCajero) op;
					tipo = "Cajero - "+opt.getEstablecimiento();
				}
				if (op instanceof OperacionPagoConTarjeta) {
					OperacionPagoConTarjeta opt = (OperacionPagoConTarjeta) op;
					tipo = "Pago Con Tarjeta - "+opt.getBenificiario();
				}
				if (op instanceof OperacionTransferencia) {
					OperacionTransferencia opt = (OperacionTransferencia) op;
					if (op.getImporte() >= 0) {
						tipo = "Transferencia de "+opt.getIBANReceptor();
					} else {
						tipo = "Transferencia a "+opt.getIBANReceptor();
					}
				}
				if (op instanceof OperacionVentanilla) {
					OperacionVentanilla opt = (OperacionVentanilla) op;
					tipo = "Ventanilla - "+opt.getSucursal();
				}
				lbl02_2_Operacion.setText(" "+tipo);
			}
			if (cont == 3) {
				panel03_1.setBackground(Color.LIGHT_GRAY);
				panel03_2.setBackground(Color.LIGHT_GRAY);
				lbl03_1_Fecha.setText(" "+format.format(op.getFecha()));
				lbl03_1_Descripcion.setText(op.getConcepto());
				lbl03_1_Importe.setText(decFormat.format(op.getImporte()));
				String tipo = "";
				if (op instanceof OperacionCajero) {
					OperacionCajero opt = (OperacionCajero) op;
					tipo = "Cajero - "+opt.getEstablecimiento();
				}
				if (op instanceof OperacionPagoConTarjeta) {
					OperacionPagoConTarjeta opt = (OperacionPagoConTarjeta) op;
					tipo = "Pago Con Tarjeta - "+opt.getBenificiario();
				}
				if (op instanceof OperacionTransferencia) {
					OperacionTransferencia opt = (OperacionTransferencia) op;
					if (op.getImporte() >= 0) {
						tipo = "Transferencia de "+opt.getIBANReceptor();
					} else {
						tipo = "Transferencia a "+opt.getIBANReceptor();
					}
				}
				if (op instanceof OperacionVentanilla) {
					OperacionVentanilla opt = (OperacionVentanilla) op;
					tipo = "Ventanilla - "+opt.getSucursal();
				}
				lbl03_2_Operacion.setText(" "+tipo);
			}
			if (cont == 4) {
				lbl04_1_Fecha.setText(" "+format.format(op.getFecha()));
				lbl04_1_Descripcion.setText(op.getConcepto());
				lbl04_1_Importe.setText(decFormat.format(op.getImporte()));
				String tipo = "";
				if (op instanceof OperacionCajero) {
					OperacionCajero opt = (OperacionCajero) op;
					tipo = "Cajero - "+opt.getEstablecimiento();
				}
				if (op instanceof OperacionPagoConTarjeta) {
					OperacionPagoConTarjeta opt = (OperacionPagoConTarjeta) op;
					tipo = "Pago Con Tarjeta - "+opt.getBenificiario();
				}
				if (op instanceof OperacionTransferencia) {
					OperacionTransferencia opt = (OperacionTransferencia) op;
					if (op.getImporte() >= 0) {
						tipo = "Transferencia de "+opt.getIBANReceptor();
					} else {
						tipo = "Transferencia a "+opt.getIBANReceptor();
					}
				}
				if (op instanceof OperacionVentanilla) {
					OperacionVentanilla opt = (OperacionVentanilla) op;
					tipo = "Ventanilla - "+opt.getSucursal();
				}
				lbl04_2_Operacion.setText(" "+tipo);
			}
			if (cont == 5) {
				panel05_1.setBackground(Color.LIGHT_GRAY);
				panel05_2.setBackground(Color.LIGHT_GRAY);
				lbl05_1_Fecha.setText(" "+format.format(op.getFecha()));
				lbl05_1_Descripcion.setText(op.getConcepto());
				lbl05_1_Importe.setText(decFormat.format(op.getImporte()));
				String tipo = "";
				if (op instanceof OperacionCajero) {
					OperacionCajero opt = (OperacionCajero) op;
					tipo = "Cajero - "+opt.getEstablecimiento();
				}
				if (op instanceof OperacionPagoConTarjeta) {
					OperacionPagoConTarjeta opt = (OperacionPagoConTarjeta) op;
					tipo = "Pago Con Tarjeta - "+opt.getBenificiario();
				}
				if (op instanceof OperacionTransferencia) {
					OperacionTransferencia opt = (OperacionTransferencia) op;
					if (op.getImporte() >= 0) {
						tipo = "Transferencia de "+opt.getIBANReceptor();
					} else {
						tipo = "Transferencia a "+opt.getIBANReceptor();
					}
				}
				if (op instanceof OperacionVentanilla) {
					OperacionVentanilla opt = (OperacionVentanilla) op;
					tipo = "Ventanilla - "+opt.getSucursal();
				}
				lbl05_2_Operacion.setText(" "+tipo);
			}

		}
	}
	
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
