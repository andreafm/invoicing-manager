package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Modelo;

import controlador.Controlador;

class VentanaEntreFechas {
	private ActionListener escuchador;
	protected Modelo modelo;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected JDialog ventanaPrincipal=new JDialog();
	private JPanel panelDatos;
	
	//Labels y cajas de texto
			protected JLabel textNIF=new JLabel("NIF");
			protected JTextField NIF=new JTextField(9);
			protected JTextField diaIni=new JTextField(2);
			private JLabel textoMes=new JLabel("Mes");
			protected JTextField mesIni=new JTextField(2);
			private JLabel textoAnyo=new JLabel("Año");
			protected JTextField anyoIni=new JTextField(4);
			protected JTextField diaFin=new JTextField(2);
			protected JTextField mesFin=new JTextField(2);
			protected JTextField anyoFin=new JTextField(4);
			
		
			private JLabel textoDia2=new JLabel("Dia");
			private JLabel textoMes2=new JLabel("Mes");
			private JLabel textoAnyo2=new JLabel("Año");
			
			//botones aceptar y cancelar
			protected JButton botonAceptar;
			protected JButton botonCancelar;
			
	//Ventana informacion
	protected JDialog ventanaInfo=new JDialog();
			
			
	public VentanaEntreFechas(Modelo modelo){
		this.modelo=modelo;
	}
	
	public void setModelo(Modelo modelo){
		this.modelo=modelo;
	}
	
	
	public void setVisible(boolean opcion){
		if(opcion==true){
			ventanaPrincipal.setVisible(true);
		}else{
			ventanaPrincipal.setVisible(false);
		}
	}
	
	
	
	
	public void muestraVentana(){
		ventanaPrincipal.setSize(screenSize.width/2, screenSize.height/2);
		ventanaPrincipal.setLocationRelativeTo(null);
		Container panelPrincipal=ventanaPrincipal.getContentPane();	
	
		panelDatos=new JPanel();
		panelDatos.setLayout(new GridLayout(10, 2) );
		panelDatos.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelPrincipal.add(panelDatos);
		
		JLabel textoDia=new JLabel("Dia");
		
		panelDatos.add(textNIF);
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
		
		//Cancelar
		botonCancelar=new JButton("Cancelar");
		botonCancelar.setActionCommand("cancelar");
		panelDatos.add(botonCancelar);
		
	}

	protected void mostrarInformacion(String datos){
		ventanaInfo.setSize(screenSize.width/2,screenSize.height/2);
		Container contenedor=ventanaInfo.getContentPane();
		JTextArea areaDatos=new JTextArea();
		areaDatos.setSize(screenSize.width/2,screenSize.height/2);
		final JScrollPane scroll=new JScrollPane(areaDatos);
		
		botonCancelar = new JButton("Cerrar");
		botonCancelar.setActionCommand("Cerrar");
		botonCancelar.addActionListener(escuchador = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcion=e.getActionCommand();
				switch(opcion){
					case "Cerrar":
						ventanaInfo.setVisible(false);
						ventanaInfo.remove(scroll);
						ventanaInfo.remove(botonCancelar);
						break;
				}
				
			}
		});
		ventanaInfo.add(botonCancelar, BorderLayout.SOUTH);
		
		if(datos==""){
			areaDatos.append("No existen entre esas fechas");
			contenedor.add(scroll);
			ventanaInfo.setLocationRelativeTo(null);
			ventanaInfo.setVisible(true);
		}else if(datos!=null){
			areaDatos.append(datos);
			contenedor.add(scroll);
			ventanaInfo.setLocationRelativeTo(null);
			ventanaInfo.setVisible(true);
		}
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


	protected String getNIF(){
		return NIF.getText();
	}
	
	protected int getDiaIni() {
		return Integer.parseInt(diaIni.getText());
	}

	protected int getMesIni() {
		return Integer.parseInt(mesIni.getText());
	}

	protected int getAnyoIni() {
		return Integer.parseInt(anyoIni.getText());
	}

	protected int getDiaFin() {
		return Integer.parseInt(diaFin.getText());
	}

	protected int getMesFin() {
		return Integer.parseInt(mesFin.getText());
	}

	protected int getAnyoFin() {
		return Integer.parseInt(anyoFin.getText());
	}

	


}
