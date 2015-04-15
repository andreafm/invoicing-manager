package modelo;

import java.util.Calendar;



public class FactoriaNuevosClientes implements FactoriaClientes {

	public FactoriaNuevosClientes(){
		super();
	}
	
	@Override
	public Cliente nuevoParticular(String nombre, String nIF,
			Direccion direccion, String email, Calendar fechaDeAlta,
			Tarifa tarifa, String apellido1, String apellido2) {
		return new Particular(nombre, nIF, direccion, email, fechaDeAlta, tarifa, apellido1, apellido2);
	}

	@Override
	public Cliente nuevaEmpresa(String nombre, String nIF, Direccion direccion,
			String email, Calendar fechaDeAlta, Tarifa tarifa) {
		return new Empresa(nombre, nIF, direccion, email, fechaDeAlta, tarifa);
	}

}
