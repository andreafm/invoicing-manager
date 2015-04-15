package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import vista.InformaVista;

public class ImplementacionModelo implements Modelo {

	private InformaVista vista;
	private BaseDeDatos baseDatos;
	private FactoriaClientes factoriaClientes=new FactoriaNuevosClientes();
	private FactoriaTarifas factoriaTarifas=new FactoriaNuevaTarifa();
	private String resultado;
	
	public ImplementacionModelo() {
		super();
		baseDatos=new BaseDeDatos();
	}

	public InformaVista getVista() {
		return vista;
	}

	public void setVista(InformaVista vista) {
		this.vista = vista;
	}

	public void iniciarSistema() {
		ObjectInputStream ois = null;
		try {
			try {
				FileInputStream fis = new FileInputStream("baseDeDatos.bin");
				ois = new ObjectInputStream(fis);
				this.baseDatos = (BaseDeDatos) ois.readObject();
			} finally {
				if (ois != null)
					ois.close();
			}
		} catch (FileNotFoundException exc) {
			System.err
					.println("Fichero de datos no existe. Se crea una nueva base de datos.");
		} catch (IOException exc) {
			exc.printStackTrace();
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
	}
	
	public void guardarDatos() {
		ObjectOutputStream oos = null;
		try {
			try {
				FileOutputStream fos = new FileOutputStream("baseDeDatos.bin");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(baseDatos);
			} finally {
				oos.close();
			}
		} catch (FileNotFoundException exc) {
			System.out.println("El fichero no existe.");
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		resultado="DATOS GUARDADOS CON EXITO";
		vista.resultado();
	}

	
	
	
	
	
	
	
	public void crearParticular(String nombre,String apellido1,String apellido2,String NIF,String provincia,String poblacion,String codpostal,String mail,int dia,int mes,int anyo){
		Direccion direccion = new Direccion(provincia, poblacion, codpostal);
		Calendar fecha=GregorianCalendar.getInstance();
		fecha.set(anyo, mes, dia);
		Tarifa tarifaBase = factoriaTarifas.nuevaTarifaBase(0.25);
		Cliente cliente = factoriaClientes.nuevoParticular(nombre, NIF, direccion, mail,
				fecha, tarifaBase, apellido1, apellido2);
		try {
			baseDatos.altaCliente(cliente);
			resultado="Cliente "+NIF+" añadido";
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR  ---El cliente "+NIF+" ya existe---";
			vista.resultado();
		}
		
	}
	
	public void crearEmpresa(String nombre,String NIF,String provincia,String poblacion,String codpostal,String mail,int dia,int mes,int anyo){
		Direccion direccion = new Direccion(provincia, poblacion, codpostal);
		Calendar fecha=GregorianCalendar.getInstance();
		fecha.set(anyo, mes, dia);
		Tarifa tarifaBase = factoriaTarifas.nuevaTarifaBase(0.25);
		Cliente cliente = factoriaClientes.nuevaEmpresa(nombre, NIF, direccion, mail, fecha,
				tarifaBase);
		try {
			baseDatos.altaCliente(cliente);
			resultado="Cliente "+NIF+" añadido";
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR  ---El cliente "+NIF+" ya existe---";
			vista.resultado();
		}
		
	}
	
	public void eliminarCliente(String nif){
		try {
			baseDatos.borrarCliente(nif);
			resultado="Cliente "+nif+" borrado";
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR ---El cliente "+nif+" no existe---";
			vista.resultado();
		}
		
	
	}
	
	public void cambioDomingos(String nif){
		try {
			Cliente cliente=baseDatos.recuperarCliente(nif);
			Tarifa tarifa=factoriaTarifas.nuevaTarifaDomingosGratis(cliente.getTarifa(), 0);
			cliente.setTarifa(tarifa);
			resultado="Tarifa Domingos gratis anyadida al cliente";
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR  ---El cliente no existe";
			vista.resultado();
		}
		
	}
	
	public void cambioTardes(String nif){
		try {
			Cliente cliente=baseDatos.recuperarCliente(nif);
			Tarifa tarifa=factoriaTarifas.nuevaTarifaTardesReducidas(cliente.getTarifa(), 0.1);
			cliente.setTarifa(tarifa);
			resultado="Tarifa Tardes Reducidas anyadida al cliente";
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR  ---El cliente no existe";
			vista.resultado();
		}
		
	}
	
	
	public void cambioTarifaBase(String nif,double precio){
		try {
			Cliente cliente=baseDatos.recuperarCliente(nif);
			Tarifa tarifa=factoriaTarifas.nuevaTarifaBase(precio);
			cliente.setTarifa(tarifa);
			resultado="Tarifa Base del cliente ha cambiado, ahora es "+precio;
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR  ---El cliente no existe";
			vista.resultado();
		}
		
	}
	
	
	
	
	
	public String mostrarCliente(String NIF){   
		String datos="";
		try {
			datos = baseDatos.datosCliente(NIF);
			
		} catch (ExcepcionCliente e) {
			resultado="ERROR --- El cliente "+NIF+" no existe---";
			vista.resultado();
		}
		return datos;
	}
	
	public String mostrarTodosClientes(){
		String datosClientes="\n";
		Collection<Cliente> todosClientes = baseDatos.recuperarClientes();
		for (Cliente cliente : todosClientes) {
			datosClientes+=cliente.toString()+"\n";
		}
		return datosClientes;
	}
	
	public void crearLlamada(String nif,String numeroDestino,int duracion,int dia,int mes,int anyo,int hora,int minuto){
		Calendar fecha = GregorianCalendar.getInstance();
		fecha.set(anyo, mes, dia, hora, minuto);
		Llamada llamada = new Llamada(numeroDestino, fecha, duracion);
		Cliente cliente;
		try {
			cliente = baseDatos.recuperarCliente(nif);
			cliente.addLlamada(llamada);
			resultado="Llamada registrada con exito";
			vista.resultado();
		} catch (ExcepcionCliente e) {
			resultado="ERROR ---El cliente "+nif+" no existe";
			vista.resultado();
		}
		
	}
	
	
	public String mostrarLlamadas(String nif){
		Cliente cliente;
		String infoLlamadas="";
		try {
			cliente = baseDatos.recuperarCliente(nif);
			
			for (Llamada aux : cliente.getListaLlamadas()) {
				infoLlamadas+=aux.toString()+"\n";
				}
			} catch (ExcepcionCliente e) {
				infoLlamadas=null;
				resultado="ERROR ---El cliente "+nif+" no existe";
				vista.resultado();
		}
		return infoLlamadas;

	}
	
	public void crearFactura(String nif,int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin){
		Calendar fechaEmision = GregorianCalendar.getInstance();
		Calendar fechaIni = new GregorianCalendar();
		fechaIni.set(anyoIni, mesIni, diaIni);
		Calendar fechaFin = new GregorianCalendar();
		fechaFin.set(anyoFin, mesFin, diaFin);
		Cliente cliente;
		
		double importe=0;
		int codigo=0;
		
		try {
			cliente = baseDatos.recuperarCliente(nif);
		
		Factura factura = new Factura(cliente.getTarifa(), fechaEmision, fechaIni,
				fechaFin, cliente.getListaLlamadas());
		importe=factura.calcularImporteNuevo(cliente.getListaLlamadas());
		codigo=factura.getCodigo();
		try {
			baseDatos.addFactura(factura);
			baseDatos.addFacturaCliente(nif, factura);
			resultado="Factura emitida correctamente -- Importe: "+importe+"  ---Codigo: "+codigo;
			vista.resultado();
		} catch (ExcepcionFecha e) {
			resultado="ERROR ---La fecha final no puede ser mas pequena que la fecha de inicio---";
			vista.resultado();
		}
		}catch (ExcepcionCliente e) {
			resultado="ERROR ---El cliente "+nif+" no existe";
			vista.resultado();
		}
		
	}
	
	
	
	
	public String mostrarFactura(int codFactura){
		String infoFactura="";
		try {
			infoFactura = baseDatos.recuperarFactura(codFactura);
		} catch (ExcepcionFactura e) {
			resultado="ERROR ---La factura "+codFactura+" no existe---";
			vista.resultado();
		}
		return infoFactura;
	}
	
	public String mostrarTodasFacturas(String nif){
		String infoFacturas="";
		ArrayList<Factura> listaAux;
		try {
			listaAux = baseDatos.recuperarCliente(nif)
					.getListaFacturas();
			for (Factura factura : listaAux) {
				infoFacturas+=factura.toString()+"\n";
		}
		} catch (ExcepcionCliente e) {
			resultado="ERROR ---El cliente "+nif+" no existe";
			vista.resultado();
		}
		return infoFacturas;
	
	}		
	
	//METODO EXTRAER ENTRE FECHAS
	public <E extends InterfaceFecha> List<E> extraerEntreFechas(
			Collection<E> coleccion, Calendar fechaDesde, Calendar fechaHasta)
			throws ExcepcionFecha {

		if (fechaDesde.compareTo(fechaHasta) <= 0) {
			List<E> listaElementos = new LinkedList<E>();
			for (E elemento : coleccion) {
				if (elemento.getFecha().compareTo(fechaDesde) >= 0
						&& elemento.getFecha().compareTo(fechaHasta) <= 0) {
					listaElementos.add(elemento);
				}
			}
			return listaElementos;
		} else {
			throw new ExcepcionFecha();
		}
	}
	
	
	public String clientesEntreFechas(int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin){
		Calendar fechaIni = new GregorianCalendar();
		fechaIni.set(anyoIni, mesIni, diaIni);
		Calendar fechaFin = new GregorianCalendar();
		fechaFin.set(anyoFin, mesFin, diaFin);

		Collection<Cliente> coleccion = baseDatos.getMapaClientes().values();
		List<Cliente> listaClientesEntreFecha;
		String infoClientes="";
		try {
			listaClientesEntreFecha = extraerEntreFechas(coleccion, fechaIni,
					fechaFin);

			for (Cliente cliente : listaClientesEntreFecha) {
				infoClientes+=cliente.toString()+"\n";
			}
		} catch (ExcepcionFecha e) {
			infoClientes=null;
			resultado="ERROR La fecha final es mayor que la inicial";
			vista.resultado();
		}
		return infoClientes;
	}
	
	
	public String facturasEntreFechas(String nif,int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin){
		Calendar fechaIni = new GregorianCalendar();
		fechaIni.set(anyoIni, mesIni, diaIni);
		Calendar fechaFin = new GregorianCalendar();
		fechaFin.set(anyoFin, mesFin, diaFin);
		String infoFacturas="";
		
		Collection<Factura> coleccion;
		try {
			coleccion = baseDatos.recuperarCliente(nif)
					.getListaFacturas();
		
		List<Factura> listaFacturasEntreFecha;
		try {
			listaFacturasEntreFecha = extraerEntreFechas(coleccion, fechaIni,
					fechaFin);
			for (Factura factura : listaFacturasEntreFecha) {
				infoFacturas+=factura.toString()+"\n";
			}
		} catch (ExcepcionFecha e) {
			infoFacturas=null;
			resultado="ERROR La fecha final es mayor que la inicial";
			vista.resultado();
		}
		} catch (ExcepcionCliente e1) {
			infoFacturas=null;
			resultado="ERROR El cliente no existe";
			vista.resultado();
		}
		return infoFacturas;
	}
	
	
	public String llamadasEntreFechas(String nif,int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin){
		Calendar fechaIni = new GregorianCalendar();
		fechaIni.set(anyoIni, mesIni, diaIni);
		Calendar fechaFin = new GregorianCalendar();
		fechaFin.set(anyoFin, mesFin, diaFin);
		String infoLlamadas="";
		
		
		Collection<Llamada> coleccion;
		try {
			coleccion = baseDatos.recuperarCliente(nif)
					.getListaLlamadas();
		
		List<Llamada> listaClientesEntreFecha;
		try {
			listaClientesEntreFecha = extraerEntreFechas(coleccion, fechaIni,
					fechaFin);
			for (Llamada llamada : listaClientesEntreFecha) {
				infoLlamadas+=llamada.toString()+"\n";
			}
		} catch (ExcepcionFecha e) {
			infoLlamadas=null;
			resultado="ERROR La fecha final es mayor que la inicial";
			vista.resultado();
		}
		} catch (ExcepcionCliente e1) {
			infoLlamadas=null;
			resultado="ERROR El cliente no existe";
			vista.resultado();
		}
		return infoLlamadas;
		
	}
	
	
	
	
	
	
	
	public String getResultado(){
		return resultado;
	}
	
}
