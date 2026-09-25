import javax.swing.*;
import java.awt.*;

public class Estilos {

    public static final Color AZUL_OSCURO =
        new Color(20, 35, 70);

    public static final Color AZUL =
        new Color(52, 105, 200);

    public static final Color FONDO =
        new Color(245, 247, 250);

    public static final Color BLANCO =
        Color.WHITE;

    public static final Font TITULO =
        new Font("Arial", Font.BOLD, 24);

    public static final Font SUBTITULO =
        new Font("Arial", Font.BOLD, 18);

    public static final Font TEXTO =
        new Font("Arial", Font.PLAIN, 14);

    public static void estilizarBoton(JButton boton) {

        boton.setBackground(AZUL);
        boton.setForeground(BLANCO);
        boton.setFocusPainted(false);
        boton.setFont(TEXTO);
    }
}