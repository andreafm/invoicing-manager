package modelo;


public class FactoriaNuevaTarifa implements FactoriaTarifas {

	public FactoriaNuevaTarifa(){
		super();
	}
	
	@Override
	public Tarifa nuevaTarifaBase(double precio) {
		return new TarifaBase(precio);
	}

	@Override
	public Tarifa nuevaTarifaDomingosGratis(Tarifa tarifa, double precio) {
		return new TarifaDomingosGratis(tarifa, precio);
	}

	@Override
	public Tarifa nuevaTarifaTardesReducidas(Tarifa tarifa, double precio) {
		return new TarifaTardes(tarifa, precio);
	}

}
