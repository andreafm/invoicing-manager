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

 class VentanaLlamada {
	private Controlador controlador;
	private ActionListener escuchador;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private JDialog ventanaPrincipal=new JDialog();
	
	//Labels y cajas de texto
	private JLabel textoNIF=new JLabel("NIF");
	private JTextField NIF=new JTextField(9);
	private JLabel textoNumDest=new JLabel("Numero de destino");
	private JTextField numDest=new JTextField(12);
	private JLabel textoDuracion=new JLabel("Duracion");
	private JTextField duracion=new JTextField(6);
	private JLabel textoDia=new JLabel("Dia");
	private JTextField dia=new JTextField(2);
	private JLabel textoMes=new JLabel("Mes");
	private JTextField mes=new JTextField(2);
	private JLabel textoAnyo=new JLabel("Año");
	private JTextField anyo=new JTextField(4);
	private JLabel textoHora=new JLabel("Hora");
	private JTextField hora=new JTextField(2);
	private JLabel textoMinuto=new JLabel("Minuto");
	private JTextField minuto=new JTextField(2);
	
	//botones
	private JButton botonAceptar;
	private JButton botonCancelar;
	
	public VentanaLlamada(Controlador controlador){
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
		ventanaPrincipal.setTitle("Dar de alta una llamada");
		ventanaPrincipal.setSize(screenSize.width/2, screenSize.height/2);
		ventanaPrincipal.setLocationRelativeTo(null);
		Container panelPrincipal=ventanaPrincipal.getContentPane();
		
		//Panel datos
		JPanel panelDatos=new JPanel();
		panelDatos.setLayout(new GridLayout(9, 2) );
		panelDatos.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelPrincipal.add(panelDatos);
		
		panelDatos.add(textoNIF);
		panelDatos.add(NIF);
		panelDatos.add(textoNumDest);
		panelDatos.add(numDest);
		panelDatos.add(textoDuracion);
		panelDatos.add(duracion);
		panelDatos.add(textoDia);
		panelDatos.add(dia);
		panelDatos.add(textoMes);
		panelDatos.add(mes);
		panelDatos.add(textoAnyo);
		panelDatos.add(anyo);
		panelDatos.add(textoHora);
		panelDatos.add(hora);
		panelDatos.add(textoMinuto);
		panelDatos.add(minuto);
		
		
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
					
					controlador.altaLlamada();
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
	
	
	
	
	public JTextField getNIF() {
		return NIF;
	}

	public JTextField getNumDest() {
		return numDest;
	}

	public JTextField getDuracion() {
		return duracion;
	}

	public JTextField getDia() {
		return dia;
	}

	public JTextField getMes() {
		return mes;
	}

	public JTextField getAnyo() {
		return anyo;
	}

	public JTextField getHora() {
		return hora;
	}

	public JTextField getMinuto() {
		return minuto;
	}

	public void borrarCampos(){
		NIF.setText(null);
		numDest.setText(null);
		duracion.setText(null);
		dia.setText(null);
		mes.setText(null);
		anyo.setText(null);
		hora.setText(null);
		minuto.setText(null);
	}
}
