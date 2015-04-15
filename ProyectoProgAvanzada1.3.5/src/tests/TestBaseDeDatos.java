package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import modelo.BaseDeDatos;
import modelo.Cliente;
import modelo.Direccion;
import modelo.ExcepcionCliente;
import modelo.ExcepcionFactura;
import modelo.ExcepcionFecha;
import modelo.FactoriaClientes;
import modelo.FactoriaNuevosClientes;
import modelo.Factura;
import modelo.Llamada;
import modelo.Particular;
import modelo.Tarifa;
import modelo.TarifaBase;

import org.junit.Before;
import org.junit.Test;



import es.uji.www.GeneradorDatosINE;

public class TestBaseDeDatos {
	
	private BaseDeDatos baseDeDatos;
	private Cliente cliente;
	private GeneradorDatosINE gen=new GeneradorDatosINE();
	private Direccion direccion;
	private Factura factura;
	private LinkedList<Llamada> listaLlamadas=new LinkedList<Llamada>();
	private ArrayList<Factura> listaFacturas=new ArrayList<Factura>();
	Factura facturaErronea;
	FactoriaClientes factoriaClientes=new FactoriaNuevosClientes();
	
	
	@Before
	public void init(){
		baseDeDatos=new BaseDeDatos();
		String provincia=gen.getProvincia();
		String codpostal="12540";
		direccion=new Direccion(provincia,gen.getPoblacion(provincia),codpostal);
		String email="ferran@gmail.com";
		Calendar fechaDeAlta=GregorianCalendar.getInstance();
		Tarifa tarifa=new TarifaBase(0.1);
		cliente=factoriaClientes.nuevoParticular(gen.getNombre(), gen.getNIF(), direccion,email , fechaDeAlta, tarifa, gen.getApellido(),gen.getApellido());
		Calendar emision=GregorianCalendar.getInstance();
		Calendar periodo1=GregorianCalendar.getInstance();
		Calendar periodo2=GregorianCalendar.getInstance();
		periodo1.set(2014, 1, 1);
		periodo2.set(2014, 2, 1);
		Llamada llamada=new Llamada("123456789", periodo1, 100);
		listaLlamadas.add(llamada);
		factura=new Factura(tarifa, emision, periodo1, periodo2, listaLlamadas);
		cliente.getListaFacturas().add(factura);
		listaFacturas.add(factura);
		facturaErronea=new Factura(tarifa, emision, periodo2, periodo1, listaLlamadas);
	}
	
	

	

	@Test
	public void testBorrarCliente() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("Excepcion alta cliente,ya existe el cliente");
		}
		try {
			baseDeDatos.borrarCliente(cliente.getNIF());
			assertEquals(baseDeDatos.getMapaClientes().size(),0);
		} catch (ExcepcionCliente e) {
			fail("Excepcion el cliente no existe");
		}
	}
	
	@Test
	public void testBorrarClienteNoExistente() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("Excepcion alta cliente,ya existe el cliente");
		}
		try {
			String dni=")(*&^%%";
			baseDeDatos.borrarCliente(dni);
			fail("Deberia saltar la excepcion de cliente no existente");
		} catch (ExcepcionCliente e) {
			
		}
	}

	

	@Test
	public void testRecuperarClienteNoExistente() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("Excepcion alta cliente,ya existe el cliente");
		}
		try {
			String dni="765fr)(*";
			Cliente clienteAux=baseDeDatos.recuperarCliente(dni);
			fail("Deberia saltar la excepcion");
		} catch (ExcepcionCliente e) {
			
		}
	}

	@Test
	public void testRecuperarCliente() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("Excepcion alta cliente,ya existe el cliente");
		}
		try {
			Cliente clienteAux=baseDeDatos.recuperarCliente(cliente.getNIF());
			assertEquals(cliente, clienteAux);
		} catch (ExcepcionCliente e) {
			fail("Excepcion no se encuentra el cliente");
		}
		
		
		
	}

	@Test
	public void testAltaCliente() {
		try {
			baseDeDatos.altaCliente(cliente);
			assertEquals(cliente, baseDeDatos.getMapaClientes().get(cliente.getNIF()));
		} catch (ExcepcionCliente e) {
			fail("Salta la excepcion si ya existe un cliente asi");
		}
		
	}
	
	@Test
	public void testAltaClienteRepetido() {
		try {
			baseDeDatos.altaCliente(cliente);
			baseDeDatos.altaCliente(cliente);
			fail("No ha saltado la excepcion");
		} catch (ExcepcionCliente e) {
			
		}
		
	}

	@Test
	public void testSetTarifaCliente() {
		try {
			baseDeDatos.altaCliente(cliente);
			baseDeDatos.setTarifaCliente(cliente.getNIF(), 0.25);
			Calendar fecha=GregorianCalendar.getInstance();
			Llamada llamada=new Llamada("5678", fecha, 60);
			assertEquals(0.25, cliente.getTarifa().getPrecioLlamada(llamada), 0);
		} catch (ExcepcionCliente e) {
			fail("No deberia saltar la excepcion");
		}
		
		
		
	}

	@Test
	public void testRecuperarFactura() {
		try {
			baseDeDatos.addFactura(factura);
		} catch (ExcepcionFecha e1) {
			
			e1.printStackTrace();
			fail("No deberia");
		}
		try {
			String datosFactura=baseDeDatos.recuperarFactura(factura.getCodigo());
			assertEquals(factura.toString(),datosFactura);
		} catch (ExcepcionFactura e) {
			fail("No deberia saltar la excepcion");
		}
	}
	
	@Test
	public void testRecuperarFacturaNoExistente() {
		try {
			baseDeDatos.addFactura(factura);
		} catch (ExcepcionFecha e1) {
			
			e1.printStackTrace();
			fail("No deberia");
		}
		try {
			int codigo=0;
			String datosFactura=baseDeDatos.recuperarFactura(codigo);
			fail("Deberia saltar la excepcion");
		} catch (ExcepcionFactura e) {
			
		}
	}

	

	@Test
	public void testRecuperarFacturaCliente() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("no deberia saltar");
		}
		assertEquals(listaFacturas, baseDeDatos.recuperarFacturas(cliente));
		
	}

	@Test
	public void testAddFactura() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("No deberia salir la excepcion");
		}
		
		try {
			baseDeDatos.addFactura(factura);
		} catch (ExcepcionFecha e) {
			e.printStackTrace();
			fail("No deberia");
		}
		assertEquals(listaFacturas,baseDeDatos.recuperarFacturas(cliente));
	}
	
	@Test
	public void testAddFacturaFechaMal() {
		try {
			baseDeDatos.altaCliente(cliente);
		} catch (ExcepcionCliente e) {
			fail("No deberia salir la excepcion");
		}
		
		try {
			baseDeDatos.addFactura(facturaErronea);
			fail("Deberia saltar la excepcion");
		} catch (ExcepcionFecha e) {
			
		}
		
	}
	

}
