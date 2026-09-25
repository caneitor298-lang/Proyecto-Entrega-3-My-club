import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class InicioView extends JPanel {
    private final ClubController controlador;
    private final JTextField buscar = new JTextField(20);
    private final JComboBox<String> categoria = new JComboBox<>(new String[] {
            "Todas", "Deportes", "Tecnología", "Arte", "Académicos", "Entretenimiento" });
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[] {"Club", "Categoría", "Horario", "Ubicación"}, 0) {
        public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private final JTable tabla = new JTable(modelo);
    private final JLabel estado = new JLabel();
    private final JButton ver = new JButton("Ver club seleccionado");
    private ArrayList<Club> resultados = new ArrayList<>();

    public InicioView(ClubController controlador, Consumer<Club> abrirClub) {
        this.controlador = controlador;
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(20, 24, 24, 24));
        JLabel titulo = new JLabel("Explorar clubes");
        titulo.setFont(Estilos.TITULO);
        JPanel cabecera = new JPanel(new BorderLayout(10, 14));
        cabecera.add(titulo, BorderLayout.NORTH);
        JPanel filtros = new JPanel(new BorderLayout(10, 5));
        JPanel busqueda = new JPanel(new BorderLayout(4, 4));
        busqueda.add(new JLabel("Buscar por nombre"), BorderLayout.NORTH);
        buscar.setName("buscar");
        busqueda.add(buscar, BorderLayout.CENTER);
        filtros.add(busqueda, BorderLayout.CENTER);
        JPanel categorias = new JPanel(new BorderLayout(4, 4));
        categorias.add(new JLabel("Categoría"), BorderLayout.NORTH);
        categoria.setName("categoria");
        categorias.add(categoria, BorderLayout.CENTER);
        filtros.add(categorias, BorderLayout.EAST);
        cabecera.add(filtros, BorderLayout.CENTER);
        add(cabecera, BorderLayout.NORTH);
        tabla.setName("clubes");
        tabla.setRowHeight(34);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabla.setFillsViewportHeight(true);
        JScrollPane desplazamiento = new JScrollPane(tabla);
        desplazamiento.setColumnHeaderView(tabla.getTableHeader());
        add(desplazamiento, BorderLayout.CENTER);
        JPanel pie = new JPanel(new BorderLayout());
        pie.add(estado, BorderLayout.CENTER);
        Estilos.estilizarBoton(ver);
        pie.add(ver, BorderLayout.EAST);
        add(pie, BorderLayout.SOUTH);
        buscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { refrescar(); }
            public void removeUpdate(DocumentEvent e) { refrescar(); }
            public void changedUpdate(DocumentEvent e) { refrescar(); }
        });
        categoria.addActionListener(e -> refrescar());
        tabla.getSelectionModel().addListSelectionListener(e -> ver.setEnabled(tabla.getSelectedRow() >= 0));
        ver.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) abrirClub.accept(resultados.get(fila));
        });
        refrescar();
    }
    public void refrescar() {
        resultados = controlador.buscarClubes(buscar.getText(), (String) categoria.getSelectedItem());
        modelo.setRowCount(0);
        for (Club club : resultados)
            modelo.addRow(new Object[] {club.getNombre(), club.getCategoria(), club.getHorario(), club.getUbicacion()});
        estado.setText(resultados.isEmpty() ? "No se encontraron clubes con esos filtros." : resultados.size() + " clubes encontrados.");
        ver.setEnabled(false);
        if (!resultados.isEmpty()) tabla.setRowSelectionInterval(0, 0);
    }
}
