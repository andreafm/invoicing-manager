package modelo;

import java.util.Calendar;


public class TarifaDomingosGratis extends MejoraTarifa {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6561244810070871035L;
	
	
	public TarifaDomingosGratis(Tarifa tarifa,double precio){
		super(tarifa,precio);
	
	}
	
	@Override
	public double getPrecioLlamada(Llamada llamada){
		
		if(llamada.getFecha().get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			if(super.precio<getTarifa().getPrecioLlamada(llamada)){
			return super.precio * (llamada.getDuracion()/60);
			
			}
		}
		return super.getTarifa().getPrecioLlamada(llamada);
		
		
	}
	
}
