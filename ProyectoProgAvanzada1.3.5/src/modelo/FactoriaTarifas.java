package modelo;


public interface FactoriaTarifas {

	Tarifa nuevaTarifaBase(double precio);
	Tarifa nuevaTarifaDomingosGratis(Tarifa tarifa,double precio);
	Tarifa nuevaTarifaTardesReducidas(Tarifa tarifa,double precio);
}
