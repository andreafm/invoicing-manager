package modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;





public class Factura implements Serializable,InterfaceFecha {

	private Tarifa tarifa;
	private Calendar emision;
	private Calendar[] periodo=new Calendar[2];
	private double importe;
	private int codigo;
	
	public Factura() {
		super();
			}

	public Factura(Tarifa tarifa, Calendar emision, Calendar periodo1,
			Calendar periodo2,LinkedList<Llamada> listaLlamadas)  {
		super();
		this.tarifa = tarifa;
		this.emision = emision;
		this.periodo[0] = periodo1;
		this.periodo[1] = periodo2;
		this.importe =calcularImporteNuevo(listaLlamadas);
		this.codigo=this.hashCode();
	}

	
	
	
	public double calcularImporteNuevo(LinkedList<Llamada> listaLlamadas){
		double importe=0;
		
		for(Llamada llamada : listaLlamadas){
			
			if(llamada.getFecha().compareTo(periodo[0])>=0 && llamada.getFecha().compareTo(periodo[1])<=0){
			importe+=tarifa.getPrecioLlamada(llamada);
			}else if(llamada.getFecha().compareTo(periodo[1])>0){
				break;
			}
				
		}
		
		return importe;
	}
	
	
	
	
	
	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Calendar getFecha() {
		return emision;
	}

	public void setEmision(Calendar emision) {
		this.emision = emision;
	}

	public Calendar[] getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Calendar[] periodo) {
		this.periodo = periodo;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Factura Tarifa: " + tarifa +"Codigo: "+codigo+ ", Emision: " + emision.getTime()
				+ " fecha de Inicio: "+periodo[0].getTime() +" Fecha final: "+periodo[1].getTime()+ " Importe:"
				+ importe;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((emision == null) ? 0 : emision.hashCode());
		long temp;
		temp = Double.doubleToLongBits(importe);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(periodo);
		result = prime * result + ((tarifa == null) ? 0 : tarifa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (codigo != other.codigo)
			return false;
		if (emision == null) {
			if (other.emision != null)
				return false;
		} else if (!emision.equals(other.emision))
			return false;
		if (Double.doubleToLongBits(importe) != Double
				.doubleToLongBits(other.importe))
			return false;
		if (!Arrays.equals(periodo, other.periodo))
			return false;
		if (tarifa == null) {
			if (other.tarifa != null)
				return false;
		} else if (!tarifa.equals(other.tarifa))
			return false;
		return true;
	}

	public int getCodigo() {
		return codigo;
	}

	

	
	
}
