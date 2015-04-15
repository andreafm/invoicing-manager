package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;




public class BaseDeDatos implements Serializable {

	
	
	private HashMap<String, Cliente> mapaClientes;
	private HashMap<Integer, Factura> mapaFacturas;
	
	public BaseDeDatos(){
		mapaClientes = new HashMap<String, Cliente>();
		mapaFacturas=new HashMap<Integer,Factura>();
	}

	//// GETTERS
	public HashMap<String, Cliente> getMapaClientes() {
		return mapaClientes;
	}

	public HashMap<Integer, Factura> getMapaFacturas() {
		return mapaFacturas;
	}
	/////////////
	
	//METODOS LISTACLIENTES
	public void borrarCliente(String nif) throws ExcepcionCliente {
		if (mapaClientes.containsKey(nif)) {
			mapaClientes.remove(nif);
		}else{
			throw new ExcepcionCliente("ERROR.No se ha podido borrar el cliente,el cliente no existe");
		}
	}

	public String datosCliente(String nif) throws ExcepcionCliente {
		if(mapaClientes.containsKey(nif)){
		Cliente cliente = mapaClientes.get(nif);
		return cliente.toString();
		}else{
			throw new ExcepcionCliente();
		}
	}
	
	public Cliente recuperarCliente(String nif) throws ExcepcionCliente{
		if(mapaClientes.containsKey(nif)){
		return mapaClientes.get(nif);
		}else{
			throw new ExcepcionCliente();
		}
	}
	
	public Collection<Cliente> recuperarClientes() {		
		return  mapaClientes.values();
	}

	public void altaCliente(Cliente cliente)throws ExcepcionCliente {
		if(!mapaClientes.containsKey(cliente.getNIF())){ 
		mapaClientes.put(cliente.getNIF(), cliente);
		}else{
			throw new ExcepcionCliente("ERROR.No se ha podido dar de alta,el cliente ya existe");
		}
		
	}
	
	public void setTarifaCliente(String nif,double precio){
		Tarifa tarifa=new TarifaBase(precio);
		mapaClientes.get(nif).setTarifa(tarifa);
	}
	/////
	
	//METODOS MAPAFACTURAS
	public String recuperarFactura(int codigo) throws ExcepcionFactura{
		if(mapaFacturas.containsKey(codigo)){
		return mapaFacturas.get(codigo).toString();
		}else{
			throw new ExcepcionFactura();
		}
	}

	public List<Factura> recuperarFacturas() {
		Collection<Factura> lista = mapaFacturas.values();
		List<Factura> resultado = new LinkedList<Factura>(lista);
		return resultado;
	}
	
	public List<Factura> recuperarFacturas(Cliente cliente){
		return cliente.getListaFacturas();
	}
	
	public void addFactura(Factura factura) throws ExcepcionFecha  {		//Este metodo no necesitara excepcion pues como no pueden generarse
		Calendar[] periodo=factura.getPeriodo();
		if(periodo[0].compareTo(periodo[1])<=0){																	//dos facturas al instante el hascode siempre se generara distinto
		mapaFacturas.put(factura.getCodigo(), factura);
		
		}else{
			throw new ExcepcionFecha();
		}
	}
	
	public void addFacturaCliente(String nif,Factura factura) throws ExcepcionCliente{
		if(mapaClientes.containsKey(nif)){
		mapaClientes.get(nif).addFactura(factura);
		}else{
			throw new ExcepcionCliente("El cliente no existe");
		}
		}
		
	//////////////////////
	
}
