package modelo;

import java.io.Serializable;
import java.util.Calendar;


public class Llamada implements Serializable,InterfaceFecha {
	private String numeroDestino;
	private Calendar fecha;

	private int duracion; // en segundos

	public Llamada() {
		super();
		
	}

	public Llamada(String numeroDestino, Calendar fecha, int duracion) {
		super();
		this.numeroDestino = numeroDestino;
		this.fecha = fecha;

		this.duracion = duracion;
	}

	public String getNumeroDestino() {
		return numeroDestino;
	}

	public void setNumeroDestino(String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Llamada NumeroDestino: " + numeroDestino + " Fecha: "
				+ fecha.getTime() + " Duracion:" + duracion;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Llamada other = (Llamada) obj;
		if (duracion != other.duracion)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (numeroDestino == null) {
			if (other.numeroDestino != null)
				return false;
		} else if (!numeroDestino.equals(other.numeroDestino))
			return false;
		return true;
	}

	
}
