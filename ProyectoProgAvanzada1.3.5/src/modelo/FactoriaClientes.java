package modelo;

import java.util.Calendar;



public interface FactoriaClientes {

	Cliente nuevoParticular(String nombre, String nIF, Direccion direccion,
			String email, Calendar fechaDeAlta, Tarifa tarifa, String apellido1,
			String apellido2);

	Cliente nuevaEmpresa(String nombre, String nIF, Direccion direccion,
			String email, Calendar fechaDeAlta, Tarifa tarifa);
}
