import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class SolicitudLider implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id = UUID.randomUUID().toString();
    private final LocalDateTime fechaSolicitud = LocalDateTime.now();
    private final String carnet;
    private final int edad;
    private final String nombre;
    private final String correo;
    private final String carrera;
    private String contrasena;
    private final String nombreClub;
    private final String descripcion;
    private final String categoria;
    private final String ubicacion;
    private final String horario;
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;
    private LocalDateTime fechaRevision;
    private String revisadoPor = "";
    private String motivoDenegacion = "";

    // Se construye después de validar el formulario en SolicitudesController.
    public SolicitudLider(String carnet, String nombre, String correo, String carrera,
                          String contrasena, String nombreClub, String descripcion,
                          String categoria, String ubicacion, String horario) {
        this(carnet, nombre, 0, correo, carrera, contrasena, nombreClub,
                descripcion, categoria, ubicacion, horario);
    }

    public SolicitudLider(String carnet, String nombre, int edad, String correo, String carrera,
                          String contrasena, String nombreClub, String descripcion,
                          String categoria, String ubicacion, String horario) {
        this.carnet = carnet;
        this.edad = edad;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.contrasena = contrasena;
        this.nombreClub = nombreClub;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.horario = horario;
    }

    public String getId() { return id; }
    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public String getCarnet() { return carnet; }
    public int getEdad() { return edad; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getCarrera() { return carrera; }
    public String getNombreClub() { return nombreClub; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public String getUbicacion() { return ubicacion; }
    public String getHorario() { return horario; }
    public EstadoSolicitud getEstado() { return estado; }
    public LocalDateTime getFechaRevision() { return fechaRevision; }
    public String getRevisadoPor() { return revisadoPor; }
    public String getMotivoDenegacion() { return motivoDenegacion; }

    String getContrasena() { return contrasena; }

    void resolver(EstadoSolicitud estado, Administrador administrador, String motivo) {
        this.estado = estado;
        this.fechaRevision = LocalDateTime.now();
        this.revisadoPor = administrador.getCorreo();
        this.motivoDenegacion = motivo;
        this.contrasena = null;
    }

    // Permite deshacer la decisión si el archivo no se pudo guardar.
    void restaurarPendiente(String contrasena) {
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaRevision = null;
        this.revisadoPor = "";
        this.motivoDenegacion = "";
        this.contrasena = contrasena;
    }
}
