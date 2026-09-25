import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MisClubsView extends JPanel {
    public MisClubsView(Estudiante estudiante, ClubController controlador, Consumer<Club> abrirClub, Runnable refrescar) {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(20, 24, 24, 24));
        JLabel titulo = new JLabel("Mis clubes");
        titulo.setFont(Estilos.TITULO);
        add(titulo, BorderLayout.NORTH);
        DefaultTableModel modelo = new DefaultTableModel(new String[] {"Club", "Horario"}, 0) {
            public boolean isCellEditable(int fila, int columna) { return false; }
        };
        for (Club club : estudiante.getClubes()) modelo.addRow(new Object[] {club.getNombre(), club.getHorario()});
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(34);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);
        JScrollPane desplazamiento = new JScrollPane(tabla);
        desplazamiento.setColumnHeaderView(tabla.getTableHeader());
        add(desplazamiento, BorderLayout.CENTER);
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pie.add(new JLabel(estudiante.getClubes().isEmpty() ? "Todavía no perteneces a ningún club." : "Selecciona un club."));
        JButton ver = new JButton("Ver club");
        JButton salir = new JButton("Salir del club");
        ver.setEnabled(false);
        salir.setEnabled(false);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            ver.setEnabled(tabla.getSelectedRow() >= 0);
            salir.setEnabled(tabla.getSelectedRow() >= 0);
        });
        ver.addActionListener(e -> abrirClub.accept(estudiante.getClubes().get(tabla.getSelectedRow())));
        salir.addActionListener(e -> {
            Club club = estudiante.getClubes().get(tabla.getSelectedRow());
            if (JOptionPane.showConfirmDialog(this, "¿Quieres salir de " + club.getNombre() + "?",
                    "Salir del club", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
            try {
                controlador.eliminarEstudianteDeClub(estudiante, club);
                refrescar.run();
            } catch (Exception ex) { VentanaPrincipal.mostrarError(this, ex); }
        });
        pie.add(ver);
        pie.add(salir);
        add(pie, BorderLayout.SOUTH);
        if (!estudiante.getClubes().isEmpty()) tabla.setRowSelectionInterval(0, 0);
    }
}