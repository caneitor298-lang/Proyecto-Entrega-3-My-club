import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Los datos iniciales se crean otra vez cada vez que se abre el programa.
        DatosAplicacion datos = DatosAplicacion.crearDemostracion();
        UsuarioController usuarios = new UsuarioController(datos);
        ClubController clubes = new ClubController(datos);
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(usuarios, clubes).setVisible(true);
        });
    }
}
