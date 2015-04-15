package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;





public class Empresa extends Cliente {

	public Empresa() {
		super();

	}

	public Empresa(String nombre, String nIF, Direccion direccion,
			String email, Calendar fechaDeAlta, Tarifa tarifa) {
		super(nombre, nIF, direccion, email, fechaDeAlta, tarifa);

	}
	

}
