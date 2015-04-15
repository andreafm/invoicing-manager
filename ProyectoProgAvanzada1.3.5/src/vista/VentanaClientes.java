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

class VentanaClientes {
	private Controlador controlador;
	private ActionListener escuchador;
	//Ventana Particular
		private JDialog ventanaParticular;
		private JLabel textoApellido1=new JLabel("Apellido 1");
		private JLabel textoApellido2=new JLabel("Apellido 2");
		private JTextField apellido1=new JTextField(15);
		private JTextField apellido2=new JTextField(15);
		private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//botones y textos
		private JLabel textoNombre=new JLabel("Nombre");
		private JTextField nombre=new JTextField(15);
		private JLabel textoNIF=new JLabel("NIF");
		private JTextField NIF=new JTextField(9);
		private JLabel textoProvincia=new JLabel("Provincia");
		private JTextField pronvincia=new JTextField(15);
		private JLabel textoPoblacion=new JLabel("Poblacion");
		private JTextField poblacion=new JTextField(20);
		private JLabel textoCodPostal=new JLabel("Codigo postal");
		private JTextField codpostal=new JTextField(6);
		private JLabel textoMail=new JLabel("E-mail");
		private JTextField mail=new JTextField(20);
		private JLabel textoDia=new JLabel("Dia");
		private JTextField dia=new JTextField(2);
		private JLabel textoMes=new JLabel("Mes");
		private JTextField mes=new JTextField(2);
		private JLabel textoAnyo=new JLabel("Año");
		private JTextField anyo=new JTextField(4);
		//private JLabel textoTarifa=new JLabel("Tarifa Base");
		//private JTextField tarifa=new JTextField(4);
		
		
		//botones aceptar y cancelar
		private JButton botonAceptar;
		private JButton botonCancelar;
		
	public VentanaClientes(Controlador controlador){
		super();
		this.controlador=controlador;
	}
	
	public void setControlador(Controlador controlador){
		this.controlador=controlador;
	}
	
	public void mostarVentanaParticular(){
		//Ventana Particular
				ventanaParticular=new JDialog();
				ventanaParticular.setSize(screenSize.width/2, screenSize.height/2);
				ventanaParticular.setLocationRelativeTo(null);
				ventanaParticular.setTitle("Anyadir particular");
				ventanaParticular.setVisible(true);
				Container panelPrincipal=ventanaParticular.getContentPane();
				
				//Panel datos
				JPanel panelDatos=new JPanel();
				panelDatos.setLayout(new GridLayout(12, 2) );
				panelDatos.setBorder(new EmptyBorder(15, 15, 15, 15));
				panelPrincipal.add(panelDatos);
				
				//Nombre
				campoNombre(panelDatos);
				
				//Apellido 1
				campoApellido1(panelDatos);
				
				//Apellido 2
				campoApellido2(panelDatos);
				
				
				//NIF
				campoNIF(panelDatos);
				
				//Provincia
				campoProvincia(panelDatos);
				
				//Poblacion
				campoPoblacion(panelDatos);
				
				
				//Codpostal
				campoCodPostal(panelDatos);
				
				//E-mail
				campoMail(panelDatos);
				
				//Dia
				campoDia(panelDatos);
				
				//Mes
				campoMes(panelDatos);
				
				//Año
				campoAnyo(panelDatos);
				
				
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
							
							controlador.nuevoParticular();
							ventanaParticular.setVisible(false);
							borrarCampos();
							break;
						case "cancelar":
							
							ventanaParticular.setVisible(false);
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

	
	private void campoAnyo(JPanel panelDatos) {
		
		panelDatos.add(textoAnyo);
		panelDatos.add(anyo);
	}

	private void campoMes(JPanel panelDatos) {
		
		panelDatos.add(textoMes);
		panelDatos.add(mes);
	}

	private void campoDia(JPanel panelDatos) {
		
		panelDatos.add(textoDia);
		panelDatos.add(dia);
	}

	private void campoMail(JPanel panelDatos) {
		
		panelDatos.add(textoMail);
		panelDatos.add(mail);
	}

	private void campoCodPostal(JPanel panelDatos) {
		
		panelDatos.add(textoCodPostal);
		panelDatos.add(codpostal);
	}

	private void campoPoblacion(JPanel panelDatos) {
		
		panelDatos.add(textoPoblacion);
		panelDatos.add(poblacion);
	}

	private void campoProvincia(JPanel panelDatos) {
		
		panelDatos.add(textoProvincia);
		panelDatos.add(pronvincia);
	}

	private void campoNIF(JPanel panelDatos) {
		
		panelDatos.add(textoNIF);
		panelDatos.add(NIF);
	}

	private void campoApellido2(JPanel panelDatos) {
		
		panelDatos.add(textoApellido2);
		panelDatos.add(apellido2);
	}

	private void campoApellido1(JPanel panelDatos) {
		
		panelDatos.add(textoApellido1);
		panelDatos.add(apellido1);
	}

	private void campoNombre(JPanel panelDatos) {
		
		panelDatos.add(textoNombre);
		panelDatos.add(nombre);
	}
	
	public void mostarVentanaEmpresa(){
		//Ventana Particular
				ventanaParticular=new JDialog();
				ventanaParticular.setSize(screenSize.width/2, screenSize.height/2);
				ventanaParticular.setLocationRelativeTo(null);
				ventanaParticular.setTitle("Anyadir empresa");
				ventanaParticular.setVisible(true);
				Container panelPrincipal=ventanaParticular.getContentPane();
				
				//Panel datos
				JPanel panelDatos=new JPanel();
				panelDatos.setLayout(new GridLayout(10, 2) );
				panelDatos.setBorder(new EmptyBorder(15, 15, 15, 15));
				panelPrincipal.add(panelDatos);
				
				campoNombre(panelDatos);
				campoNIF(panelDatos);
				campoProvincia(panelDatos);
				campoPoblacion(panelDatos);
				campoCodPostal(panelDatos);
				campoMail(panelDatos);
				campoDia(panelDatos);
				campoMes(panelDatos);
				campoAnyo(panelDatos);
				
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
							controlador.nuevoEmpresa();
							ventanaParticular.setVisible(false);
							borrarCampos();
							break;
						case "cancelar":
							
							ventanaParticular.setVisible(false);
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

	public JTextField getApellido1() {
		return apellido1;
	}

	public JTextField getApellido2() {
		return apellido2;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public JTextField getNIF() {
		return NIF;
	}

	public JTextField getPronvincia() {
		return pronvincia;
	}

	public JTextField getPoblacion() {
		return poblacion;
	}

	public JTextField getCodpostal() {
		return codpostal;
	}

	public JTextField getMail() {
		return mail;
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

	
	
	public void borrarCampos(){
		nombre.setText(null);
		apellido1.setText(null);
		apellido2.setText(null);
		NIF.setText(null);
		pronvincia.setText(null);
		poblacion.setText(null);
		codpostal.setText(null);
		mail.setText(null);
		dia.setText(null);
		mes.setText(null);
		anyo.setText(null);
		
	}
	
	
}
