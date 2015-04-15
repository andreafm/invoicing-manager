package modelo;

import java.util.Calendar;
import java.util.Date;





public class Particular extends Cliente {
	private String apellido1;
	private String apellido2;

	public Particular() {
		super();

	}

	public Particular(String nombre, String nIF, Direccion direccion,
			String email, Calendar fechaDeAlta, Tarifa tarifa, String apellido1,
			String apellido2) {

		super(nombre, nIF, direccion, email, fechaDeAlta, tarifa);
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	@Override
	public String toString() {
		return "Particular Apellido1: " + apellido1 + " Apellido2: "
				+ apellido2 +  super.toString();
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particular other = (Particular) obj;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		return true;
	}
	
	

}
