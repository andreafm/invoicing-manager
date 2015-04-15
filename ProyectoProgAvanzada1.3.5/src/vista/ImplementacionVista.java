package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import modelo.Modelo;
import controlador.Controlador;

public class ImplementacionVista implements InterrogaVista,InformaVista{

	private Controlador controlador;
	private Modelo modelo;
	private JFrame ventana=new JFrame("Gestor de Telecomunicaciones");
	private ActionListener escuchador;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private VentanaClientes ventanaClientes=new VentanaClientes(this.controlador);
	private VentanaLlamada ventanaLlamada=new VentanaLlamada(this.controlador);
	private VentanaFactura ventanaEmitirFactura=new VentanaFactura(controlador);
	JLabel mostradorMensajes=new JLabel("Informacion");
	private boolean aceptado;
	
	
	//ventana pedirNIF
	private JDialog ventanaDNI=new JDialog(ventana,true);
	private JLabel textoNIF;
	private JTextField NIF;
	
	//Ventana Cambiar Tarifa
	private JDialog ventanaTarifa=new JDialog(ventana,true);
	private JTextField NIFTarifa;
	private JTextField tarifaBase;
	
	//Ventana mostrarCliente
	private JDialog ventanaMostrarCliente=new JDialog(ventana,true);
	
	//Ventana mostrar todos los clientes
	private JDialog ventanaTodosClientes=new JDialog(ventana,true);
	
	//Ventana mostrar Llamadas
	private JDialog ventanaMostrarLlamadas=new JDialog(ventana,true);
	
	//Mostrar Factura
	private JDialog ventanaFactura=new JDialog(ventana,true);
	private JDialog ventanaCodFactura=new JDialog(ventana,true);
	JTextField codigoFactura=new JTextField(15);
	
	//Mostrar facturas de un cliente
	private JDialog ventanaFacturasCli=new JDialog();
	
	//Mostrar clientes entre fechas
	private VentanaClientesEntreFechas ventanaCliEntreFechas=new VentanaClientesEntreFechas(modelo);
	
	//Mostrar facturas entre fechas
	private VentanaFacturasEntreFechas ventanaFacEntreFechas=new VentanaFacturasEntreFechas(modelo);
	
	//Mostrar Llamadas entre fechas
	private VentanaLlamadasEntreFechas ventanaLlamEntreFechas=new VentanaLlamadasEntreFechas(modelo);
	
	//botones aceptar y cancelar
	private JButton botonAceptar;
	private JButton botonCancelar;
	
	
	
	
	public ImplementacionVista() {
		super();
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		ventanaClientes.setControlador(controlador);
		ventanaLlamada.setControlador(controlador);
		ventanaEmitirFactura.setControlador(controlador);
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
		ventanaCliEntreFechas.setModelo(modelo);
		ventanaFacEntreFechas.setModelo(modelo);
		ventanaLlamEntreFechas.setModelo(modelo);
	}
	
	
	
	
	public void GUI(){
		//VENTANA PRINCIPAL
		ventana.setSize(screenSize.width/2, screenSize.height/2);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ventana.pack();
		ventana.setVisible(true);
		Container panelPrincipal=ventana.getContentPane();
		panelPrincipal.setLayout(new BorderLayout());
		
		//PANEL DE OPCIONES
		JPanel opciones=new JPanel();
		opciones.setBorder(new EmptyBorder(15, 15, 15, 15));
		opciones.setLayout(new GridLayout(5,3));
		panelPrincipal.add(opciones,BorderLayout.CENTER);
		
		//Ventanas ocultas
		pedirNIF();
		altaLlamada();
		pedirCodFactura();
		emitirFactura();
		clientesEntreFechas();
		facturasEntreFechas();
		llamadasEntreFechas();
		cambiarTarifaGUI();
		
		//Opcion anyadir cliente
		JButton botonParticular=new JButton("Anadir Particular");
		botonParticular.setActionCommand("particular");
		opciones.add(botonParticular);
		botonParticular.addActionListener(escuchador=new ActionListener() {
			
			//ESCUCHADOR
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
				case "particular":
					particularGUI();
					break;
				case "empresa":
					empresaGUI();
					break;
				case "borrarCliente":
					borrarCliente();
					break;
				case "cambiarTarifa":
					ventanaTarifa.setVisible(true);
					break;
				case "recuperarCliente":
					recuperaCliente();
					break;
				case "recuperarTodosClientes":
					recuperarTodosClientes();
					break;
				case "altaLlamada":
					ventanaLlamada.setVisible(true);
					break;
				case "mostrarLlamadas":
					mostrarLlamadas();
					break;
				case "emitirFactura":
					ventanaEmitirFactura.setVisible(true);
					break;
				case "mostrarFactura":
					mostrarFactura();
					break;
				case "recuperarFacturas":
					mostrarFacturasCliente();
					break;
				case"clientesEntreFechas":
					ventanaCliEntreFechas.setVisible(true);
					break;
				case"facturasEntreFechas":
					ventanaFacEntreFechas.setVisible(true);
					break;
				case "llamadasEntreFechas":
					ventanaLlamEntreFechas.setVisible(true);
					break;
				case "guardar":
					controlador.guardar();
					break;
				}
				
			}
		});
		//
		
	//Opcion anadir empresa
		JButton botonEmpresa=new JButton("Anadir empresa");
		botonEmpresa.setActionCommand("empresa");
		opciones.add(botonEmpresa);
		botonEmpresa.addActionListener(escuchador);
		
	//Opcion borrar cliente
		JButton botonBorrar=new JButton("Borrar cliente");
		botonBorrar.setActionCommand("borrarCliente");
		opciones.add(botonBorrar);
		botonBorrar.addActionListener(escuchador);
	
	//Opcion cambiar tarifa
		JButton botonTarifa=new JButton("Cambiar tarifa");
		botonTarifa.setActionCommand("cambiarTarifa");
		opciones.add(botonTarifa);
		botonTarifa.addActionListener(escuchador);
		
	//Opcion recuperar datos de un cliente
		JButton botonDatosCliente=new JButton("Mostrar datos de un cliente");
		botonDatosCliente.setActionCommand("recuperarCliente");
		opciones.add(botonDatosCliente);
		botonDatosCliente.addActionListener(escuchador);
		
	//Opcion recuperar todos los clientes
		JButton botonDatosTodosClientes=new JButton("Mostrar listado de clientes");
		botonDatosTodosClientes.setActionCommand("recuperarTodosClientes");
		opciones.add(botonDatosTodosClientes);
		botonDatosTodosClientes.addActionListener(escuchador);
		
	//Opcion dar de alta una llamada
		JButton botonAltaLlamada=new JButton("Dar de alta una llamada");
		botonAltaLlamada.setActionCommand("altaLlamada");
		opciones.add(botonAltaLlamada);
		botonAltaLlamada.addActionListener(escuchador);
		
	//Opcion recuperar llamadas de un cliente	
		JButton botonMostrarLlamadas=new JButton("Mostrar llamadas de un cliente");
		botonMostrarLlamadas.setActionCommand("mostrarLlamadas");
		opciones.add(botonMostrarLlamadas);
		botonMostrarLlamadas.addActionListener(escuchador);
		
	//Opcion emitir factura
		JButton botonEmitirFactura=new JButton("Emitir factura");
		botonEmitirFactura.setActionCommand("emitirFactura");
		opciones.add(botonEmitirFactura);
		botonEmitirFactura.addActionListener(escuchador);
		
	//Opcion recuperarFactura
		JButton botonMostrarFactura=new JButton("Mostrar Factura");
		botonMostrarFactura.setActionCommand("mostrarFactura");
		opciones.add(botonMostrarFactura);
		botonMostrarFactura.addActionListener(escuchador);
		
	//Opcion mostrar facturas de un cliente
		JButton botonRecuperarFacturas=new JButton("Mostrar facturas de un cliente");
		botonRecuperarFacturas.setActionCommand("recuperarFacturas");
		opciones.add(botonRecuperarFacturas);
		botonRecuperarFacturas.addActionListener(escuchador);
		
	//Opcion mostrar clientes entre dos fechas
		JButton botonClientesEntreFechas=new JButton("Clientes entre dos fechas");
		botonClientesEntreFechas.setActionCommand("clientesEntreFechas");
		opciones.add(botonClientesEntreFechas);
		botonClientesEntreFechas.addActionListener(escuchador);
		
	//Opcion mostrar facturas entre dos fechas
		JButton botonFacturasEntreFechas=new JButton("Facturas entre dos fechas");
		botonFacturasEntreFechas.setActionCommand("facturasEntreFechas");
		opciones.add(botonFacturasEntreFechas);
		botonFacturasEntreFechas.addActionListener(escuchador);
		
	//Opcion mostrar llamadas entre dos fechas
		JButton botonLlamadasEntreFechas=new JButton("Llamadas entre dos fechas");
		botonLlamadasEntreFechas.setActionCommand("llamadasEntreFechas");
		opciones.add(botonLlamadasEntreFechas);
		botonLlamadasEntreFechas.addActionListener(escuchador);
		
	//Opcion Guardar
		JButton botonGuardar=new JButton("Guardar");
		botonGuardar.setActionCommand("guardar");
		opciones.add(botonGuardar);
		botonGuardar.addActionListener(escuchador);
		
	//Panel superior
		JPanel panelInferior=new JPanel();
		panelInferior.setLayout(new BorderLayout());
		panelInferior.setSize(screenSize.width/2, screenSize.height);
		panelPrincipal.add(panelInferior,BorderLayout.NORTH);
		
		//mostradorMensajes.setSize(screenSize.width, 200);
		
		panelInferior.add(mostradorMensajes,BorderLayout.CENTER);
		mostradorMensajes.setText(null);
	}
	
	//Ventana anyadir Particular
	public void particularGUI(){
		ventanaClientes.mostarVentanaParticular();
	}
	
	
	//Ventana anyadir Empresa

	public void empresaGUI(){
		ventanaClientes.mostarVentanaEmpresa();
		
	}
	
	//VENTANA DE PEDIR DNI
	public void pedirNIF(){
		botonAceptar=new JButton("Aceptar");
		botonCancelar=new JButton("Cancelar");
		textoNIF=new JLabel("NIF: ");
		NIF=new JTextField(9);
		botonAceptar.setActionCommand("aceptar");
		botonCancelar.setActionCommand("cancelar");
		ventanaDNI.setSize(200, 200);
		ventanaDNI.setTitle("Introduzca el NIF");
		ventanaDNI.setLayout(new GridLayout(4, 2));
		
		
		Container contenedorNIF=ventanaDNI.getContentPane();
		
		
		
		contenedorNIF.add(new JLabel(""));
		contenedorNIF.add(new JLabel(""));
		contenedorNIF.add(textoNIF);
		contenedorNIF.add(NIF);
		contenedorNIF.add(new JLabel(""));
		contenedorNIF.add(new JLabel(""));
		contenedorNIF.add(botonAceptar);
		contenedorNIF.add(botonCancelar);
		
		botonAceptar.addActionListener(escuchador=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
				case "aceptar":
					aceptado=true;
					ventanaDNI.setVisible(false);
					break;
				case "cancelar":
					borrarCamposNIF();
					aceptado=false;
					ventanaDNI.setVisible(false);
					break;
				}
			}
		});
		botonCancelar.addActionListener(escuchador);
		
		ventanaDNI.setLocationRelativeTo(null);
		ventanaDNI.pack();
	}
	
	public void cambiarTarifaGUI(){
		//Configuracion Ventana
		ventanaTarifa.setSize(400, 400);
		ventanaTarifa.setTitle("Cambia la tarifa del cliente");
		
		//Configuracion Paneles
		Container contenedorTarifa=ventanaTarifa.getContentPane();
		contenedorTarifa.setLayout(new BorderLayout()); 
		JPanel opcionesTarifa=new JPanel();
		opcionesTarifa.setBorder(new EmptyBorder(15, 15, 15, 15));
		opcionesTarifa.setLayout(new GridLayout(5, 2));
		contenedorTarifa.add(opcionesTarifa,BorderLayout.CENTER);
		
		//Introduccion de NIF
		JLabel textNIF=new JLabel("NIF");
		NIFTarifa=new JTextField(9);
		opcionesTarifa.add(textNIF);
		opcionesTarifa.add(NIFTarifa);
		
		//Tarifa base
		JLabel textTarifaBase=new JLabel("Cambiar tarifa base ");
		opcionesTarifa.add(textTarifaBase);
		tarifaBase=new JTextField(4);
		opcionesTarifa.add(tarifaBase);
				
		//Domingos gratis
		JButton botonDomingos=new JButton("Domingos gratis");
		botonDomingos.setActionCommand("domingos");
		opcionesTarifa.add(botonDomingos);
		botonDomingos.addActionListener(escuchador=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
				case"domingos":
					controlador.cambiarDomingos();
					ventanaTarifa.setVisible(false);
					NIFTarifa.setText(null);
					break;
				case "tardes":
					controlador.cambiarTardes();
					ventanaTarifa.setVisible(false);
					NIFTarifa.setText(null);
					break;
				case "aceptar":
					controlador.cambiarTarifaBase();
					ventanaTarifa.setVisible(false);
					NIFTarifa.setText(null);
					break;
				case "cancelar":
					ventanaTarifa.setVisible(false);
					NIFTarifa.setText(null);
				}
				}
			
		});
		
		
		//Tarde reducidas
		JButton botonTardes=new JButton("Tardes reducidas");
		botonTardes.setActionCommand("tardes");
		opcionesTarifa.add(botonTardes);
		botonTardes.addActionListener(escuchador);
		
		//espacio vacio
		JLabel vacio=new JLabel("");
		JLabel vacio2=new JLabel("");
		
		opcionesTarifa.add(vacio);
		opcionesTarifa.add(vacio2);
		
		//Boton aceptar
		botonAceptar=new JButton("Aceptar");
		botonAceptar.setActionCommand("aceptar");
		opcionesTarifa.add(botonAceptar);
		botonAceptar.addActionListener(escuchador);
		
		//Boton cancelar
		botonCancelar=new JButton("Cancelar");
		botonCancelar.setActionCommand("cancelar");
		opcionesTarifa.add(botonCancelar);
		botonCancelar.addActionListener(escuchador);
		
		ventanaTarifa.pack();
		ventanaTarifa.setLocationRelativeTo(null);
		

		
	}
	
	
	
	public void borrarCliente(){
		ventanaDNI.setVisible(true);
		if(aceptado==true){
			controlador.borrarCliente();
			borrarCamposNIF();
		}
	}
	
	public void recuperaCliente(){
		ventanaMostrarCliente.setTitle("Datos del cliente");
		ventanaMostrarCliente.setSize(screenSize.width/2,screenSize.height/2);
		Container contenedor=ventanaMostrarCliente.getContentPane();
		JTextArea datosCliente=new JTextArea();
		datosCliente.setSize(screenSize.width/2,screenSize.height/2);
		final JScrollPane scroll=new JScrollPane(datosCliente);
		
		ventanaDNI.setVisible(true);
		
		if(aceptado==true){
			String nif=NIF.getText();
			ventanaDNI.setVisible(false);
			String datos=modelo.mostrarCliente(nif);
			if(datos!=""){
				datosCliente.append("\n");
				datosCliente.append(datos);
				contenedor.add(scroll);
				ventanaMostrarCliente.setLocationRelativeTo(null);
				ventanaMostrarCliente.setVisible(true);
			}
			NIF.setText(null);
		}else{
			NIF.setText(null);
		}
		
	}
	
	
	public void recuperarTodosClientes(){
		ventanaTodosClientes.setTitle("Todos los clientes de la base de datos");
		ventanaTodosClientes.setSize(screenSize.width/2,screenSize.height/2);
		Container contenedor=ventanaTodosClientes.getContentPane();
		JTextArea datosClientes=new JTextArea();
		datosClientes.setSize(screenSize.width/2,screenSize.height/2);
		final JScrollPane scroll=new JScrollPane(datosClientes);
		
		String clientes=modelo.mostrarTodosClientes();
		datosClientes.append(clientes);
		contenedor.add(scroll);
		
		
		botonCancelar = new JButton("Cerrar");
		botonCancelar.setActionCommand("Cerrar");
		botonCancelar.addActionListener(escuchador = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
					case "Cerrar":
						ventanaTodosClientes.setVisible(false);
						ventanaTodosClientes.remove(scroll);
						ventanaTodosClientes.remove(botonCancelar);
						break;
				}
				
			}
		});
		
		ventanaTodosClientes.add(botonCancelar, BorderLayout.SOUTH);
		ventanaTodosClientes.setLocationRelativeTo(null);
		ventanaTodosClientes.setVisible(true);
	}
	
	public void mostrarLlamadas(){
		ventanaMostrarLlamadas.setTitle("Todas las llamadas del cliente");
		ventanaMostrarLlamadas.setSize(screenSize.width/2,screenSize.height/2);
		Container contenedor=ventanaMostrarLlamadas.getContentPane();
		JTextArea datosLlamadas=new JTextArea();
		datosLlamadas.setSize(screenSize.width/2,screenSize.height/2);
		final JScrollPane scroll=new JScrollPane(datosLlamadas);
		ventanaDNI.setVisible(true);
		
		botonCancelar = new JButton("Cerrar");
		botonCancelar.setActionCommand("Cerrar");
		botonCancelar.addActionListener(escuchador = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
					case "Cerrar":
						ventanaMostrarLlamadas.setVisible(false);
						ventanaMostrarLlamadas.remove(scroll);
						ventanaMostrarLlamadas.remove(botonCancelar);
						break;
				}
				
			}
		});
		
		ventanaMostrarLlamadas.add(botonCancelar, BorderLayout.SOUTH);
		
		if(aceptado==true){
			String nif=NIF.getText();
			ventanaDNI.setVisible(false);
			String datos=modelo.mostrarLlamadas(nif);
			if(datos!=null){
			datosLlamadas.append("\n");
			datosLlamadas.append(datos);
			contenedor.add(scroll);
			ventanaMostrarLlamadas.setLocationRelativeTo(null);
			ventanaMostrarLlamadas.setVisible(true);
			}
			
			NIF.setText(null);
		}else{
			NIF.setText(null);
		}
		
		
	}
	
	
	public void emitirFactura(){
		ventanaEmitirFactura.mostrarVentana();
	}
	
	
	
	
	
	
	public void pedirCodFactura(){
		//Ventana codigo de factura
		ventanaCodFactura.setTitle("Introduce el codigo de la factura");
		ventanaCodFactura.setSize(400, 70);
		Container contenedorCodigo=ventanaCodFactura.getContentPane();
		JPanel panelCodigo=new JPanel();
		panelCodigo.setLayout(new BoxLayout(panelCodigo, BoxLayout.X_AXIS));
		panelCodigo.setBorder(new EmptyBorder(15, 15, 15, 15));
		contenedorCodigo.add(panelCodigo);
		JLabel textCodFac=new JLabel("Codigo de factura    ");
		panelCodigo.add(textCodFac);
		panelCodigo.add(codigoFactura);
		
		//boton aceptar y cancelar
		botonAceptar=new JButton("Aceptar");
		botonAceptar.setActionCommand("aceptar");
		panelCodigo.add(botonAceptar);
		botonAceptar.addActionListener(escuchador=new ActionListener(
				) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
				case "aceptar":
					aceptado=true;
					ventanaCodFactura.setVisible(false);
					break;
				case "cancelar":
					aceptado=false;
					ventanaCodFactura.setVisible(false);
					break;
				}
			}
		});
		
		botonCancelar=new JButton("Cancelar");
		botonCancelar.setActionCommand("cancelar");
		panelCodigo.add(botonCancelar);
		botonCancelar.addActionListener(escuchador);
		
		ventanaCodFactura.pack();
		ventanaCodFactura.setLocationRelativeTo(null);
		
	}
	
	public void mostrarFactura(){
		
		ventanaFactura.setTitle("Datos del cliente");
		ventanaFactura.setSize(screenSize.width/2,screenSize.height/2);
		Container contenedor=ventanaFactura.getContentPane();
		JTextArea datosFactura=new JTextArea();
		datosFactura.setSize(screenSize.width/2,screenSize.height/2);
		final JScrollPane scroll=new JScrollPane(datosFactura);
		
		ventanaCodFactura.setVisible(true);	
		
		if(aceptado==true){
			int codFac=Integer.parseInt(codigoFactura.getText());
			String datos=modelo.mostrarFactura(codFac);
			if(datos!=""){
				datosFactura.append("\n");
				datosFactura.append(datos);
				contenedor.add(scroll);
				ventanaFactura.setLocationRelativeTo(null);
				ventanaFactura.setVisible(true);
				
			}
			codigoFactura.setText(null);
		}else{
			codigoFactura.setText(null);
		}
		
	}
	
	public void mostrarFacturasCliente(){
		ventanaFacturasCli.setTitle("Facturas del cliente");
		ventanaFacturasCli.setSize(screenSize.width/2,screenSize.height/2);
		Container contenedor=ventanaFacturasCli.getContentPane();
		JTextArea datosFacturas=new JTextArea();
		datosFacturas.setSize(screenSize.width/2,screenSize.height/2);
		final JScrollPane scroll=new JScrollPane(datosFacturas);
		
		ventanaDNI.setVisible(true);
		
		botonCancelar = new JButton("Cerrar");
		botonCancelar.setActionCommand("Cerrar");
		botonCancelar.addActionListener(escuchador = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
					case "Cerrar":
						ventanaFacturasCli.setVisible(false);
						ventanaFacturasCli.remove(scroll);
						ventanaFacturasCli.remove(botonCancelar);
						break;
				}
				
			}
		});
		ventanaFacturasCli.add(botonCancelar, BorderLayout.SOUTH);

		
		if(aceptado==true){
			String nif=NIF.getText();
			ventanaDNI.setVisible(false);
			String datos=modelo.mostrarTodasFacturas(nif);
			if(datos!=""){
				datosFacturas.append(datos);
			}else{
				datosFacturas.append("No tiene facturas");
			}
			contenedor.add(scroll);
			ventanaFacturasCli.setLocationRelativeTo(null);
			ventanaFacturasCli.setVisible(true);
			NIF.setText(null);
		}else{
			NIF.setText(null);
		}
	}
	
	
	public void clientesEntreFechas(){
		ventanaCliEntreFechas.muestraVentana();
	}
	
	public void facturasEntreFechas(){
		ventanaFacEntreFechas.muestraVentana();
	}
	
	public void llamadasEntreFechas(){
		ventanaLlamEntreFechas.muestraVentana();
	}
	
	public void altaLlamada(){
		ventanaLlamada.mostrarVentana();
	}
	
	
	public void creaGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI();
			}
		});
	}
	
	//Metodos de borrado
	
	public void borrarCamposNIF(){
		NIF.setText(null);
	}
	
	
	
	
	
	//Metodos InformaVista
	
	@Override
	public void resultado() {
		mostradorMensajes.setText(modelo.getResultado());
	}
	
	
	
	
	
	//Metodos InterrogaVista

	

	@Override
	public String getNIF() {
		
		return ventanaClientes.getNIF().getText();
	}
	
	@Override
	public String getNIFEmergente(){
		return NIF.getText();
	}

	@Override
	public String getNombre() {
		return ventanaClientes.getNombre().getText();
	}

	@Override
	public String getApellido1() {
		return ventanaClientes.getApellido1().getText();
	}

	@Override
	public String getApellido2() {
		return ventanaClientes.getApellido2().getText();
	}

	@Override
	public String getMail() {
		return ventanaClientes.getMail().getText();
	}

	@Override
	public String getDia() {
		return ventanaClientes.getDia().getText();
	}

	@Override
	public String getMes() {
		return ventanaClientes.getMes().getText();
	}

	@Override
	public String getAño() {
		return ventanaClientes.getAnyo().getText();
	}

	@Override
	public String getCP() {
		return ventanaClientes.getCodpostal().getText();
	}

	@Override
	public String getProvincia() {
		return ventanaClientes.getPronvincia().getText();
	}

	@Override
	public String getPoblacion() {
		return ventanaClientes.getPoblacion().getText();
		
	}
	
	@Override
	public String getNIFTarifa(){
		return NIFTarifa.getText();
	}
	
	@Override
	public String getPrecioTarifaBase(){
		return tarifaBase.getText();
	}
	
	@Override
	public String getNIFLlamada(){
		return ventanaLlamada.getNIF().getText();
	}
	
	@Override
	public String getNumDest(){
		return ventanaLlamada.getNumDest().getText();

	}
	
	@Override
	public String getDuracion(){
		return ventanaLlamada.getDuracion().getText();

	}
	
	@Override
	public String getDiaLlam(){
		return ventanaLlamada.getDia().getText();

	}
	
	@Override
	public String getMesLlam(){
		return ventanaLlamada.getMes().getText();

	}
	
	@Override
	public String getAnyoLlam(){
		return ventanaLlamada.getAnyo().getText();

	}
	
	@Override
	public String getHoraLlam(){
		return ventanaLlamada.getHora().getText();

	}
	
	@Override
	public String getMinutoLlam(){
		return ventanaLlamada.getMinuto().getText();

	}
	
	
	
	
	
	
	
	
	@Override
	public String getNIFFactura() {
		return ventanaEmitirFactura.getNIF().getText();
	}

	@Override
	public String getDiaIniFac() {
		return ventanaEmitirFactura.getDiaIni().getText();
	}

	@Override
	public String getMesIniFac() {
		return ventanaEmitirFactura.getMesIni().getText();

	}

	@Override
	public String getAnyoIniFac() {
		return ventanaEmitirFactura.getAnyoIni().getText();

	}

	@Override
	public String getDiaFinFac() {
		return ventanaEmitirFactura.getDiaFin().getText();

	}

	@Override
	public String getMesFinFac() {
		return ventanaEmitirFactura.getMesFin().getText();

	}

	@Override
	public String getAnyoFinFac() {
		return ventanaEmitirFactura.getAnyoFin().getText();

	}

	public static void main(String[] args) {
		//ImplementacionVista vista=new ImplementacionVista();
		//vista.pedirNIF();
		
	}
}
