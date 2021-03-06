package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;

 class VentanaClientesEntreFechas extends VentanaEntreFechas {
	private ActionListener escuchador;
	
	
	public VentanaClientesEntreFechas(Modelo modelo){
		super(modelo);
	}
	
	@Override
	public void muestraVentana(){
		super.muestraVentana();
		textNIF.setVisible(false);
		NIF.setVisible(false);
		ventanaPrincipal.setTitle("Clientes entre fechas");
		
		
		botonAceptar.addActionListener(escuchador=new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				String operacion=e.getActionCommand();
				switch(operacion){
				case "aceptar":
					String datos=modelo.clientesEntreFechas(getDiaIni(), getMesIni(), getAnyoIni(), getDiaFin(), getMesFin(), getAnyoFin());
					ventanaInfo.setTitle("Clientes entre fechas");
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
