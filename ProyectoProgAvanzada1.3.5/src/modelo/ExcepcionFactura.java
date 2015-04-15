package modelo;

public class ExcepcionFactura extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3418699286462559359L;

	public ExcepcionFactura(){
		super("ERROR.La factura no existe");
	}
	
	public ExcepcionFactura(String mensaje){
		super(mensaje);
	}
	
}
