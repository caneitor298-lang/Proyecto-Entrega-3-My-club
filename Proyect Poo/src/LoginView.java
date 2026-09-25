import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import javax.swing.*;

public class LoginView extends JPanel {
    public LoginView(UsuarioController controlador, Consumer<Usuario> alIngresar, Runnable irARegistro) {
        setLayout(new GridBagLayout());
        setBackground(Estilos.FONDO);
        JPanel formulario = new JPanel(new GridLayout(0, 1, 8, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        formulario.setPreferredSize(new Dimension(430, 440));
        JLabel titulo = new JLabel("MY CLUB", SwingConstants.CENTER);
        titulo.setFont(Estilos.TITULO);
        titulo.setForeground(Estilos.AZUL_OSCURO);
        formulario.add(titulo);
        formulario.add(new JLabel("Encuentra tu club y participa."));
        formulario.add(new JLabel("Correo institucional"));
        JTextField correo = new JTextField();
        correo.setName("correo");
        formulario.add(correo);
        formulario.add(new JLabel("Contraseña"));
        JPasswordField contrasena = new JPasswordField();
        contrasena.setName("contrasena");
        formulario.add(contrasena);
        JButton ingresar = new JButton("Iniciar sesión");
        Estilos.estilizarBoton(ingresar);
        formulario.add(ingresar);
        JButton registrar = new JButton("Crear cuenta");
        formulario.add(registrar);
        JLabel estado = new JLabel(" ");
        formulario.add(estado);
        ingresar.addActionListener(e -> {
            char[] clave = contrasena.getPassword();
            if (correo.getText().trim().isEmpty() || clave.length == 0) {
                Arrays.fill(clave, '\0');
                estado.setText("Completa el correo y la contraseña.");
                return;
            }
            Usuario usuario = controlador.iniciarSesion(correo.getText(), new String(clave));
            Arrays.fill(clave, '\0');
            contrasena.setText("");
            if (usuario == null) estado.setText("Correo o contraseña incorrectos.");
            else alIngresar.accept(usuario);
        });
        registrar.addActionListener(e -> irARegistro.run());
        contrasena.addActionListener(e -> ingresar.doClick());
        add(formulario);
    }
}
