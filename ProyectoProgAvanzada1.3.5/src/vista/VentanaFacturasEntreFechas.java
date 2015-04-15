package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;

class VentanaFacturasEntreFechas extends VentanaEntreFechas {
private ActionListener escuchador;
	
	
	public VentanaFacturasEntreFechas(Modelo modelo){
		super(modelo);
	}
	
	@Override
	public void muestraVentana(){
		super.muestraVentana();
		ventanaPrincipal.setTitle("Facturas entre fechas");
		
		
		botonAceptar.addActionListener(escuchador=new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				String operacion=e.getActionCommand();
				switch(operacion){
				case "aceptar":
					String datos=modelo.facturasEntreFechas(getNIF(),getDiaIni(), getMesIni(), getAnyoIni(), getDiaFin(), getMesFin(), getAnyoFin());
					ventanaInfo.setTitle("Facturas entre fechas");
					mostrarInformacion(datos);
					ventanaPrincipal.setVisible(false);
					borrarCampos();
					break;
				case "cancelar":
					
					ventanaPrincipal.setVisible(false);
					borrarCampos();
				}	
			}
		});
		
		
		botonCancelar.addActionListener(escuchador);
	}
}
