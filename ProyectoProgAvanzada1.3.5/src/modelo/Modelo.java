package modelo;

public interface Modelo {
	
	public void guardarDatos();
	public void crearParticular(String nombre,String apellido1,String apellido2,String NIF,String provincia,String poblacion,String codpostal,String mail,int dia,int mes,int anyo);
	public void crearEmpresa(String nombre,String NIF,String provincia,String poblacion,String codpostal,String mail,int dia,int mes,int anyo);
	public void eliminarCliente(String nif);
	public String mostrarCliente(String NIF);
	public String mostrarTodosClientes();
	public void crearLlamada(String nif,String numeroDestino,int duracion,int dia,int mes,int anyo,int hora,int minuto);
	public String mostrarLlamadas(String nif);
	public void crearFactura(String nif,int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin);
	public String mostrarFactura(int codFactura);
	public String mostrarTodasFacturas(String nif);
	public String clientesEntreFechas(int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin);
	public String facturasEntreFechas(String nif,int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin);
	public String llamadasEntreFechas(String nif,int diaIni,int mesIni,int anyoIni,int diaFin,int mesFin,int anyoFin);
	public void cambioDomingos(String nif);
	public void cambioTardes(String nif);
	public void cambioTarifaBase(String nif,double precio);
	
	public String getResultado();

}
