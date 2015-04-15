package main;

import modelo.ImplementacionModelo;
import vista.ImplementacionVista;
import controlador.ImplementacionControlador;

public class Main {
	private ImplementacionControlador controlador = new ImplementacionControlador();
	private ImplementacionVista vista = new ImplementacionVista();
	private ImplementacionModelo modelo = new ImplementacionModelo();

	public Main(){
		super();
	}
	
	public void ejecuta() {
		modelo.iniciarSistema();
		 modelo.setVista(vista);
		 controlador.setVista(vista);
		 controlador.setModelo(modelo);
		 vista.setModelo(modelo);
		vista.setControlador(controlador);
		vista.creaGUI();
		modelo.guardarDatos();
	}
	
	public static void main(String[] args) {
		new Main().ejecuta();
		
	}
	
}
