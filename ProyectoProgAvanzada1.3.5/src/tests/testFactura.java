package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import modelo.FactoriaNuevaTarifa;
import modelo.FactoriaTarifas;
import modelo.Factura;
import modelo.Llamada;
import modelo.Tarifa;
import modelo.TarifaBase;
import modelo.TarifaDomingosGratis;
import modelo.TarifaTardes;

import org.junit.Before;
import org.junit.Test;


import es.uji.www.GeneradorDatosINE;

public class testFactura {

	private Factura factura;
	private LinkedList<Llamada> listaLlamadas;
	private Calendar emision;
	private Calendar fechaInicio;
	private Calendar fechaFinal;
	Llamada llamada1;
	Llamada llamada2;
	Llamada llamadaDomingo;
	Llamada llamadaDomingoTarde;
	Llamada llamadaTarde;
	Calendar fecha1;
	Calendar fecha2;
	Calendar domingo;
	Calendar domingoTarde;
	Calendar tarde;
	FactoriaTarifas factoriaTarifas=new FactoriaNuevaTarifa();
	
	@Before
	public void init(){
		
		
		fecha1=GregorianCalendar.getInstance();
		fecha1.set(2014, 3, 18, 10, 10);
		fecha2=GregorianCalendar.getInstance();
		fecha2.set(2014, 3, 18, 11, 20);
		fechaInicio=GregorianCalendar.getInstance();
		fechaInicio.set(2014, 1, 1);
		fechaFinal=GregorianCalendar.getInstance();
		fechaFinal.set(2014, 6, 1);
		llamada1=new Llamada("12345", fecha1, 60);
		llamada2=new Llamada("12345678", fecha2,120);
		listaLlamadas=new LinkedList<Llamada>();
		listaLlamadas.add(llamada1);
		listaLlamadas.add(llamada2);
		domingo=GregorianCalendar.getInstance();
		domingo.set(2014,3,20,11,30);
		llamadaDomingo=new Llamada("5432", domingo, 60);
		domingoTarde=GregorianCalendar.getInstance();
		domingoTarde.set(2014,3,20,17,30);
		llamadaDomingoTarde=new Llamada("5432", domingoTarde, 60);
		tarde=GregorianCalendar.getInstance();
		tarde.set(2014, 3, 18, 19, 20);
		llamadaTarde=new Llamada("45678",tarde, 60);
		listaLlamadas.add(llamadaDomingoTarde);
		listaLlamadas.add(llamadaTarde);
		listaLlamadas.add(llamadaDomingo);
		
		
		
	
	}
	
	
	@Test
	public void testCalcularImporteTarifaBase() {
		Tarifa tarifaBase=factoriaTarifas.nuevaTarifaBase(0.5);
		factura=new Factura(tarifaBase, emision, fechaInicio, fechaFinal, listaLlamadas);
		double importe=factura.calcularImporteNuevo(listaLlamadas);
		assertEquals(3,importe,0);
	}

	@Test
	public void testCalcularImporteDomingos(){
		Tarifa tarifa=factoriaTarifas.nuevaTarifaBase(0.5);
		tarifa=factoriaTarifas.nuevaTarifaDomingosGratis(tarifa, 0);
		factura=new Factura(tarifa, emision, fechaInicio, fechaFinal, listaLlamadas);
		
		double importe=factura.calcularImporteNuevo(listaLlamadas);
		assertEquals(2,importe,0);
	}
	
	@Test
	public void testCalcularImporteTardes(){
		Tarifa tarifa=factoriaTarifas.nuevaTarifaBase(0.5);
		tarifa=factoriaTarifas.nuevaTarifaTardesReducidas(tarifa, 0.1);
		factura=new Factura(tarifa, emision, fechaInicio, fechaFinal, listaLlamadas);
		double importe=factura.calcularImporteNuevo(listaLlamadas);
		assertEquals(2.2, importe,0);
	}

	
	@Test
	public void testCalcularImporteTodasLasTarifas(){
		Tarifa tarifa=factoriaTarifas.nuevaTarifaBase(0.5);
		tarifa=factoriaTarifas.nuevaTarifaDomingosGratis(tarifa, 0);
		tarifa=factoriaTarifas.nuevaTarifaTardesReducidas(tarifa, 0.1);
		factura=new Factura(tarifa, emision, fechaInicio, fechaFinal, listaLlamadas);
		double importe=factura.calcularImporteNuevo(listaLlamadas);
		assertEquals(1.6, importe,0);
		
		
	}
	
	@Test
	public void testCalcularImporteLlamadaFueraDePeriodo(){
		Tarifa tarifaBase=factoriaTarifas.nuevaTarifaBase(0.5);
		Calendar fechaFueraPeriodo=GregorianCalendar.getInstance();
		fechaFueraPeriodo.set(2010, 2, 1);
		Llamada llamadaFueraPeriodo=new Llamada("8765434", fechaFueraPeriodo, 60);
		listaLlamadas.add(llamadaFueraPeriodo);
		factura=new Factura(tarifaBase, emision, fechaInicio, fechaFinal, listaLlamadas);
		double importe=factura.calcularImporteNuevo(listaLlamadas);
		assertEquals(3,importe,0);
	}
	
	
}
