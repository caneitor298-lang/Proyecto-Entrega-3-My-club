import java.awt.*;
import javax.swing.*;

public class InformacionClubView extends JPanel {
    public InformacionClubView(Usuario usuario, Club club, ClubController controlador, Runnable volver) {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(20, 24, 24, 24));

        JLabel titulo = new JLabel("Información de " + club.getNombre());
        titulo.setFont(Estilos.SUBTITULO);
        add(titulo, BorderLayout.NORTH);

        JTextField ubicacion = new JTextField(club.getUbicacion());
        JTextArea descripcion = new JTextArea(club.getDescripcion(), 8, 30);
        descripcion.setFont(Estilos.TEXTO);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);

        JPanel campos = new JPanel(new BorderLayout(8, 8));
        JPanel arriba = new JPanel(new GridLayout(0, 1, 4, 4));
        arriba.add(new JLabel("Ubicación (máximo 150 caracteres)"));
        arriba.add(ubicacion);
        arriba.add(new JLabel("Descripción (máximo 2000 caracteres)"));
        campos.add(arriba, BorderLayout.NORTH);
        campos.add(new JScrollPane(descripcion), BorderLayout.CENTER);
        add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton guardar = new JButton("Guardar cambios");
        JButton cancelar = new JButton("Volver al club");
        Estilos.estilizarBoton(guardar);
        botones.add(cancelar);
        botones.add(guardar);
        add(botones, BorderLayout.SOUTH);

        guardar.addActionListener(e -> {
            try {
                controlador.actualizarInformacionClub(usuario, club, descripcion.getText(), ubicacion.getText());
                JOptionPane.showMessageDialog(this, "Información del club actualizada.");
                volver.run();
            } catch (RuntimeException ex) {
                VentanaPrincipal.mostrarError(this, ex);
            }
        });
        cancelar.addActionListener(e -> volver.run());
    }
}
