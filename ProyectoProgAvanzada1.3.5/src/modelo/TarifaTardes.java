package modelo;

import java.util.Calendar;


public class TarifaTardes extends MejoraTarifa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1018080653059203959L;

	
	public TarifaTardes(Tarifa tarifa,double precio){
		super(tarifa,precio);
	}
	
	
	@Override
	public double getPrecioLlamada(Llamada llamada){
		
		if(llamada.getFecha().get(Calendar.HOUR_OF_DAY)>=16 && llamada.getFecha().get(Calendar.HOUR_OF_DAY)<= 20 ){
			if(super.precio<getTarifa().getPrecioLlamada(llamada)){
			return super.precio *(llamada.getDuracion()/60);
			
			}
		}	
			return getTarifa().getPrecioLlamada(llamada);
	}
}
