package modelo;

import java.io.Serializable;


public abstract class Tarifa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -747023451682294825L;
	protected double precio;
	
	
	
	public Tarifa(double precio){
		this.precio=precio;
	}
	
	

	
	
	public abstract double getPrecioLlamada(Llamada llamada);
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarifa other = (Tarifa) obj;
		if (Double.doubleToLongBits(precio) != Double
				.doubleToLongBits(other.precio))
			return false;
		return true;
	}
	
	
}
