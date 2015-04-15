package modelo;

public class ExcepcionFecha extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -835517802577711907L;

	public ExcepcionFecha(){
		super("ERROR.Las fechas son erroneas,la fecha inicial no puede ser mayor que la final");
	}
}
