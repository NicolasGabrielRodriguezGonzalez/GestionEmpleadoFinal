package aplicacion;

import	 javax.swing.SwingUtilities;

import interfaz.VentanaPrincipal;

public class Aplicacion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal.IsInstance().setVisible(true);
        });
    }
}
