package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;

 class VentanaLlamadasEntreFechas extends VentanaEntreFechas{
	 private ActionListener escuchador;
		
		
		public VentanaLlamadasEntreFechas(Modelo modelo){
			super(modelo);
		}
		
		@Override
		public void muestraVentana(){
			super.muestraVentana();
			ventanaPrincipal.setTitle("Llamadas entre fechas");
			
			
			botonAceptar.addActionListener(escuchador=new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					String operacion=e.getActionCommand();
					switch(operacion){
					case "aceptar":
						String datos=modelo.llamadasEntreFechas(getNIF(),getDiaIni(), getMesIni(), getAnyoIni(), getDiaFin(), getMesFin(), getAnyoFin());
						ventanaInfo.setTitle("Llamadas entre fechas");
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
