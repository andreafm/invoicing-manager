package modelo;

public class ExcepcionCliente extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 574760932185545790L;

	public ExcepcionCliente(){
		super("El cliente no existe");
	}
	
	public ExcepcionCliente(String mensaje){
		super(mensaje);
	}
}
