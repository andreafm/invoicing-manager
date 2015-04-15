package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;






public class Cliente implements Serializable,InterfaceFecha {

	private String nombre;
	private String NIF;
	private Direccion direccion;
	private String email;
	private Calendar fecha;
	private Tarifa tarifa;
	private ArrayList<Factura> listaFacturas;  //FALTA POR IMPLEMENTARLO EN EL CONSTRUCTOR O SIMPLEMENTE INICIALIZARLO
	private LinkedList<Llamada> listaLlamadas;
	
	public Cliente() {
		super();

	}

	public Cliente(String nombre, String nIF, Direccion direccion,
			String email, Calendar fecha, Tarifa tarifa) {
	
		this.nombre = nombre;
		NIF = nIF;
		this.direccion = direccion;
		this.email = email;
		this.fecha = fecha;
		this.tarifa = tarifa;
		listaFacturas=new ArrayList<Factura>();
		listaLlamadas=new LinkedList<Llamada>();
	}

	public LinkedList<Llamada> getListaLlamadas() {
		return listaLlamadas;
	}

	public void setListaLlamadas(LinkedList<Llamada> listaLlamadas) {
		this.listaLlamadas = listaLlamadas;
	}

	public void addLlamada(Llamada llamada){
		listaLlamadas.add(llamada);
	}
	
	public void addFactura(Factura factura){
		listaFacturas.add(factura);
	}
	
	public void setCodigosFacturas(ArrayList<Factura> codigosFacturas) {
		this.listaFacturas = codigosFacturas;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fechaDeAlta) {
		this.fecha = fechaDeAlta;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public ArrayList<Factura> getListaFacturas() {
		return listaFacturas;
	}

	@Override
	public String toString() {
		return " Nombre:" + nombre + " NIF: " + NIF + " Direccion: "
				+ direccion + " email: " + email + ", fechaDeAlta: "
				+ fecha.getTime() + " tarifa: " + tarifa;
	}

	public void altaLlamada(Llamada llamada){
		listaLlamadas.add(llamada);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (listaFacturas == null) {
			if (other.listaFacturas != null)
				return false;
		} else if (!listaFacturas.equals(other.listaFacturas))
			return false;
		if (listaLlamadas == null) {
			if (other.listaLlamadas != null)
				return false;
		} else if (!listaLlamadas.equals(other.listaLlamadas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tarifa == null) {
			if (other.tarifa != null)
				return false;
		} else if (!tarifa.equals(other.tarifa))
			return false;
		return true;
	}

	



	
}
