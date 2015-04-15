package modelo;



public class TarifaBase extends Tarifa {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014587015127242302L;

	

	public TarifaBase(double precio) {
		super(precio);
		
	}
	
	@Override
	public double getPrecioLlamada(Llamada llamada){
		return precio *(llamada.getDuracion()/60);
	}
	
}
