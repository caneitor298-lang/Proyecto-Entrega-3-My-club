import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatosAplicacion datos = DatosAplicacion.cargar();
        UsuarioController usuarios = new UsuarioController(datos);
        ClubController clubes = new ClubController(datos);
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(usuarios, clubes, datos);
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    datos.guardar();
                }
            });
            ventana.setVisible(true);
        });
    }
}
