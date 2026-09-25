import java.awt.*;
import javax.swing.*;
 

public class VentanaPrincipal extends JFrame {
    private final UsuarioController usuarios;
    private final ClubController clubes;
    private final DatosAplicacion datos;
    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenido = new JPanel(tarjetas);
    private final JPanel menu = new JPanel(new BorderLayout(8, 8));
    private Usuario usuario;
    private InicioView inicio;
    private JPanel detalle;
    private JPanel misClubes;
 
    public VentanaPrincipal(UsuarioController usuarios, ClubController clubes, DatosAplicacion datos) {
        this.usuarios = usuarios;
        this.clubes = clubes;
        this.datos = datos;
        setTitle("My Club");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(940, 680);
        setMinimumSize(new Dimension(740, 560));
        setLocationRelativeTo(null);
        add(menu, BorderLayout.NORTH);
        add(contenido, BorderLayout.CENTER);
        mostrarLogin();
    }
    public void mostrarLogin() {
        usuario = null;
        inicio = null;
        detalle = null;
        misClubes = null;
        menu.removeAll();
        menu.setVisible(false);
        contenido.removeAll();
        contenido.add(new LoginView(usuarios, this::abrirSesion, this::mostrarRegistro), "login");
        tarjetas.show(contenido, "login");
        contenido.revalidate();
        contenido.repaint();
    }
    public void mostrarRegistro() {
        contenido.add(new RegistroView(usuarios, this::abrirSesion, this::mostrarLogin), "registro");
        tarjetas.show(contenido, "registro");
        contenido.revalidate();
        contenido.repaint();
    }
    private void abrirSesion(Usuario usuario) {
        this.usuario = usuario;
        contenido.removeAll();
        menu.removeAll();
        JLabel marca = new JLabel("MY CLUB  |  " + usuario.getNombre() + "  ·  "
                + (usuario instanceof Lider ? "Líder" : "Estudiante"));
        marca.setBorder(BorderFactory.createEmptyBorder(10, 14, 0, 14));
        menu.add(marca, BorderLayout.NORTH);
        JPanel navegacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        menu.add(navegacion, BorderLayout.CENTER);
        JButton explorar = new JButton("Explorar clubes");
        explorar.addActionListener(e -> mostrarInicio());
        navegacion.add(explorar);
        if (usuario instanceof Estudiante) {
            JButton mis = new JButton("Mis clubes");
            mis.addActionListener(e -> mostrarMisClubes());
            navegacion.add(mis);
        } else {
            JButton administrar = new JButton("Administrar mi club");
            administrar.addActionListener(e -> mostrarClub(((Lider) usuario).getClubAdministrado(), this::mostrarInicio));
            navegacion.add(administrar);
        }
        JButton salir = new JButton("Cerrar sesión");
        salir.addActionListener(e -> mostrarLogin());
        navegacion.add(salir);
        menu.setVisible(true);
        inicio = new InicioView(clubes, club -> mostrarClub(club, this::mostrarInicio));
        contenido.add(inicio, "inicio");
        mostrarInicio();
    }
    public void mostrarInicio() {
        inicio.refrescar();
        tarjetas.show(contenido, "inicio");
        contenido.revalidate();
        contenido.repaint();
    }
    public void mostrarMisClubes() {
        if (!(usuario instanceof Estudiante)) return;
        if (misClubes != null) contenido.remove(misClubes);
        misClubes = new MisClubsView((Estudiante) usuario, clubes,
                club -> mostrarClub(club, this::mostrarMisClubes), this::mostrarMisClubes);
        contenido.add(misClubes, "mis");
        tarjetas.show(contenido, "mis");
        contenido.revalidate();
        contenido.repaint();
    }
    public void mostrarClub(Club club, Runnable volver) {
        if (detalle != null) contenido.remove(detalle);
        detalle = new ClubView(usuario, club, clubes, volver, () -> mostrarClub(club, volver));
        contenido.add(detalle, "detalle");
        tarjetas.show(contenido, "detalle");
        contenido.revalidate();
        contenido.repaint();
    }
    public static void mostrarError(Component padre, Exception ex) {
        String mensaje = ex.getMessage();
        JOptionPane.showMessageDialog(padre, mensaje, "Revisa la operación", JOptionPane.ERROR_MESSAGE);
    }
}
 
