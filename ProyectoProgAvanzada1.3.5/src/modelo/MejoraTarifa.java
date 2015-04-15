package modelo;

public abstract class MejoraTarifa extends Tarifa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1325174350351663777L;
	private Tarifa tarifa;
	
	
	
	public MejoraTarifa(Tarifa tarifa,double precio){
		super(precio);
		this.tarifa=tarifa;
	}
	
	public Tarifa getTarifa(){
		return tarifa;
	}
	
	public abstract double getPrecioLlamada(Llamada llamada);
}
