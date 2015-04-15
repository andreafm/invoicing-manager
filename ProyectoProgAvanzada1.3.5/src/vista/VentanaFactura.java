package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

 class VentanaFactura {

	private Controlador controlador;
	private ActionListener escuchador;
	private JDialog ventanaPrincipal=new JDialog();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	//Labels y cajas de texto
		private JLabel textoNIF=new JLabel("NIF");
		private JTextField NIF=new JTextField(9);
		private JLabel textoDia=new JLabel("Dia");
		private JTextField diaIni=new JTextField(2);
		private JLabel textoMes=new JLabel("Mes");
		private JTextField mesIni=new JTextField(2);
		private JLabel textoAnyo=new JLabel("Año");
		private JTextField anyoIni=new JTextField(4);
		private JTextField diaFin=new JTextField(2);
		private JTextField mesFin=new JTextField(2);
		private JTextField anyoFin=new JTextField(4);
		
	
		private JLabel textoDia2=new JLabel("Dia");
		private JLabel textoMes2=new JLabel("Mes");
		private JLabel textoAnyo2=new JLabel("Año");
		
	//botones
		private JButton botonAceptar;
		private JButton botonCancelar;
		
		
	public VentanaFactura(Controlador controlador){
		super();
		this.controlador=controlador;
	}
	
	public void setControlador(Controlador controlador){
		this.controlador=controlador;
	}
	
	
	public void setVisible(boolean opcion){
		if(opcion==true){
			ventanaPrincipal.setVisible(true);
		}else{
			ventanaPrincipal.setVisible(false);
		}
	}
	
	public void mostrarVentana(){
		ventanaPrincipal.setTitle("Emitir una nueva factura");
		ventanaPrincipal.setSize(screenSize.width/2, screenSize.height/2);
		ventanaPrincipal.setLocationRelativeTo(null);
		Container panelPrincipal=ventanaPrincipal.getContentPane();	
	
		JPanel panelDatos=new JPanel();
		panelDatos.setLayout(new GridLayout(10, 2) );
		panelDatos.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelPrincipal.add(panelDatos);
		
		
		panelDatos.add(textoNIF);
		panelDatos.add(NIF);
		panelDatos.add(new JLabel("Fecha de inicio"));
		panelDatos.add(new JLabel(""));
		panelDatos.add(textoDia);
		panelDatos.add(diaIni);
		panelDatos.add(textoMes);
		panelDatos.add(mesIni);
		panelDatos.add(textoAnyo);
		panelDatos.add(anyoIni);
		panelDatos.add(new JLabel("Fecha de fin"));
		panelDatos.add(new JLabel(""));
		panelDatos.add(textoDia2);
		panelDatos.add(diaFin);
		panelDatos.add(textoMes2);
		panelDatos.add(mesFin);
		panelDatos.add(textoAnyo2);
		panelDatos.add(anyoFin);
		
		
		
		
		//Aceptar
				botonAceptar=new JButton("Aceptar");
				botonAceptar.setActionCommand("aceptar");
				panelDatos.add(botonAceptar);
				botonAceptar.addActionListener(escuchador=new ActionListener() {
						
					@Override
					public void actionPerformed(ActionEvent e) {
						String operacion=e.getActionCommand();
						switch(operacion){
						case "aceptar":
							
							controlador.emitirFactura();
							ventanaPrincipal.setVisible(false);
							borrarCampos();
							break;
						case "cancelar":
							
							ventanaPrincipal.setVisible(false);
							borrarCampos();
						}	
					}
				});
				
				//Cancelar
						botonCancelar=new JButton("Cancelar");
						botonCancelar.setActionCommand("cancelar");
						panelDatos.add(botonCancelar);
						botonCancelar.addActionListener(escuchador);
	
			
		
	}
	
	public void borrarCampos(){
		NIF.setText(null);
		diaIni.setText(null);
		mesIni.setText(null);
		anyoIni.setText(null);
		diaFin.setText(null);
		mesFin.setText(null);
		anyoFin.setText(null);
	}

	protected JTextField getNIF() {
		return NIF;
	}

	protected JTextField getDiaIni() {
		return diaIni;
	}

	protected JTextField getMesIni() {
		return mesIni;
	}

	protected JTextField getAnyoIni() {
		return anyoIni;
	}

	protected JTextField getDiaFin() {
		return diaFin;
	}

	protected JTextField getMesFin() {
		return mesFin;
	}

	protected JTextField getAnyoFin() {
		return anyoFin;
	}

	
	
	
}
