package modelo;

import java.io.Serializable;

public class Direccion implements Serializable{
	private String provincia;
	private String poblacion;
	private String codpostal;

	public Direccion() {
		super();

	}

	public Direccion(String provincia, String poblacion, String codpostal) {
		super();
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.codpostal = codpostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCodpostal() {
		return codpostal;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}

	@Override
	public String toString() {
		return " Provincia: " + provincia + " Poblacion: " + poblacion
				+ " Codpostal: " + codpostal;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		if (codpostal == null) {
			if (other.codpostal != null)
				return false;
		} else if (!codpostal.equals(other.codpostal))
			return false;
		if (poblacion == null) {
			if (other.poblacion != null)
				return false;
		} else if (!poblacion.equals(other.poblacion))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	
}
