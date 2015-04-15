package controlador;

import vista.ImplementacionVista;
import vista.InterrogaVista;
import modelo.Modelo;

public class ImplementacionControlador implements Controlador {

	private Modelo modelo;
	private InterrogaVista vista;
	
	public ImplementacionControlador() {
		super();
		
	}


	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	

	public void setVista(InterrogaVista vista) {
		this.vista = vista;	
	}
	
	
	public void nuevoParticular(){
		String nombre=vista.getNombre();
		String apellido1=vista.getApellido1();
		String apellido2=vista.getApellido2();
		String NIF=vista.getNIF();
		String provincia=vista.getProvincia();
		String poblacion=vista.getPoblacion();
		String codpostal=vista.getCP();
		String mail=vista.getMail();
		int dia=Integer.parseInt(vista.getDia());
		int mes=Integer.parseInt(vista.getMes());
		int anyo=Integer.parseInt(vista.getAño());
		
		modelo.crearParticular(nombre,apellido1,apellido2,NIF,provincia,poblacion,codpostal,mail,dia,mes,anyo);
		
	}
	
	public void nuevoEmpresa(){
		String nombre=vista.getNombre();
		String NIF=vista.getNIF();
		String provincia=vista.getProvincia();
		String poblacion=vista.getPoblacion();
		String codpostal=vista.getCP();
		String mail=vista.getMail();
		int dia=Integer.parseInt(vista.getDia());
		int mes=Integer.parseInt(vista.getMes());
		int anyo=Integer.parseInt(vista.getAño());
		
		modelo.crearEmpresa(nombre, NIF, provincia, poblacion, codpostal, mail, dia, mes, anyo);
	}
	
	public void borrarCliente(){
		String NIF=vista.getNIFEmergente();
		
		modelo.eliminarCliente(NIF);
	}
	
	public void cambiarDomingos(){
		String nif=vista.getNIFTarifa();
		modelo.cambioDomingos(nif);
		
	}
	
	public void cambiarTardes(){
		String nif=vista.getNIFTarifa();
		modelo.cambioTardes(nif);
	}
	
	public void cambiarTarifaBase(){
		String nif=vista.getNIFTarifa();
		double precio=Double.parseDouble(vista.getPrecioTarifaBase());
		modelo.cambioTarifaBase(nif,precio);
	}
	
	
	public void altaLlamada(){
		String nif=vista.getNIFLlamada();
		String numeroDestino=vista.getNumDest();
		int duracion=Integer.parseInt(vista.getDuracion());
		int dia=Integer.parseInt(vista.getDiaLlam());
		int mes=Integer.parseInt(vista.getMesLlam());
		int anyo=Integer.parseInt(vista.getAnyoLlam());
		int hora=Integer.parseInt(vista.getHoraLlam());
		int minuto=Integer.parseInt(vista.getMinutoLlam());
		
		modelo.crearLlamada(nif, numeroDestino, duracion, dia, mes, anyo, hora, minuto);
	}
	
	
	
	
	public void emitirFactura(){
		String nif=vista.getNIFFactura();
		int diaIni=Integer.parseInt(vista.getDiaIniFac());
		int mesIni=Integer.parseInt(vista.getMesIniFac());;
		int anyoIni=Integer.parseInt(vista.getAnyoIniFac());;
		int diaFin=Integer.parseInt(vista.getDiaFinFac());
		int mesFin=Integer.parseInt(vista.getMesFinFac());
		int anyoFin=Integer.parseInt(vista.getAnyoFinFac());
		modelo.crearFactura(nif, diaIni, mesIni, anyoIni, diaFin, mesFin, anyoFin);
	}
	
	
	
	public void recuperarFacturaCliente(){
		//String NIF=vista.getDNI();
		//modelo.mostrarFacturaCliente(NIF);
	}
	
	public void clientesEntreFechas(){
		
	}
	
	public void facturasEntreFechas(){
		
	}
	
	public void llamadasEntreFechas(){
		
	}
	
	public void guardar(){
		modelo.guardarDatos();
	}
	
	
	
	
	
	
	
}
