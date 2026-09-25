import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import javax.swing.*;
 
public class RegistroView extends JPanel {
    public RegistroView(UsuarioController controlador, Consumer<Usuario> alRegistrar, Runnable volver) {
        setLayout(new GridBagLayout());
        setBackground(Estilos.FONDO);
        JPanel formulario = new JPanel(new GridLayout(0, 1, 6, 6));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        formulario.setPreferredSize(new Dimension(430, 560));
 
        JLabel titulo = new JLabel("Crear cuenta", SwingConstants.CENTER);
        titulo.setFont(Estilos.TITULO);
        titulo.setForeground(Estilos.AZUL_OSCURO);
        formulario.add(titulo);
 
        formulario.add(new JLabel("Nombre completo"));
        JTextField nombre = new JTextField();
        formulario.add(nombre);
 
        formulario.add(new JLabel("Correo institucional"));
        JTextField correo = new JTextField();
        formulario.add(correo);
 
        formulario.add(new JLabel("Carrera"));
        JTextField carrera = new JTextField();
        formulario.add(carrera);
 
        formulario.add(new JLabel("Contraseña (mínimo 8 caracteres)"));
        JPasswordField contrasena = new JPasswordField();
        formulario.add(contrasena);
 
        formulario.add(new JLabel("Confirmar contraseña"));
        JPasswordField confirmar = new JPasswordField();
        formulario.add(confirmar);
 
        JButton crear = new JButton("Crear cuenta");
        Estilos.estilizarBoton(crear);
        formulario.add(crear);
 
        JButton regresar = new JButton("Volver al inicio de sesión");
        formulario.add(regresar);
 
        JLabel estado = new JLabel(" ");
        estado.setForeground(Color.RED);
        formulario.add(estado);
 
        crear.addActionListener(e -> {
            char[] clave = contrasena.getPassword();
            char[] clave2 = confirmar.getPassword();
            // La confirmación solo existe en la pantalla, por eso se revisa aquí
            if (!Arrays.equals(clave, clave2)) {
                estado.setText("Las contraseñas no coinciden.");
                Arrays.fill(clave, '\0');
                Arrays.fill(clave2, '\0');
                return;
            }
            try {
                Estudiante nuevo = controlador.registrarEstudiante(
                        nombre.getText(), correo.getText(), carrera.getText(), new String(clave));
                alRegistrar.accept(nuevo);
            } catch (IllegalArgumentException ex) {
                estado.setText(ex.getMessage());
            } finally {
                Arrays.fill(clave, '\0');
                Arrays.fill(clave2, '\0');
                contrasena.setText("");
                confirmar.setText("");
            }
        });
        regresar.addActionListener(e -> volver.run());
 
        add(formulario);
    }
}