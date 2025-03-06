package aplicacion;

/*
 * Main de la aplicación
 * Miembros:
 * Cesar Andres Ramirez Roa
 * Nicolas Gabriel Rodriguez Gonzalez
 * Santiago Galeano Osorio
 * 
 */

import interfaz.VentanaPrincipal;

public class Aplicacion {
	private VentanaPrincipal miVentanaPrincipal;
    public static void main(String[] args) {
        VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
        
        miVentanaPrincipal.setLocationRelativeTo(null);
        miVentanaPrincipal.setVisible(true);
    }
} 