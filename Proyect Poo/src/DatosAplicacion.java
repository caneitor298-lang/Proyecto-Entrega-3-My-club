import java.util.ArrayList;

/** Objetos compartidos por las vistas y controladores durante la ejecución. */
public class DatosAplicacion {
    private final ArrayList<Club> clubes = new ArrayList<>();
    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Club> getClubes() { return clubes; }
    public ArrayList<Usuario> getUsuarios() { return usuarios; }

    public static DatosAplicacion crearDemostracion() {
        DatosAplicacion datos = new DatosAplicacion();
        datos.clubes.add(new Club("Videojuegos", "Partidas amistosas y torneos entre estudiantes.",
                "Entretenimiento", "Aula 101", "Viernes, 15:00 a 17:00"));
        datos.clubes.add(new Club("Robótica", "Construcción y programación de robots.",
                "Tecnología", "Laboratorio 2", "Martes, 14:00 a 16:00"));
        datos.clubes.add(new Club("Fútbol", "Entrenamientos para todos los niveles.",
                "Deportes", "Cancha", "Miércoles, 16:00 a 18:00"));
        datos.clubes.add(new Club("Fotografía", "Práctica de fotografía y composición visual.",
                "Arte", "Aula 202", "Jueves, 14:00 a 15:30"));
        datos.clubes.add(new Club("Matemática", "Resolución de problemas y grupos de estudio.",
                "Académicos", "Biblioteca", "Lunes, 13:00 a 14:00"));
        datos.clubes.add(new Club("Programación", "Proyectos de software en equipo.",
                "Tecnología", "Laboratorio 3", "Sábado, 09:00 a 11:00"));
        datos.usuarios.add(new Estudiante("Emilio Cano", "emilio.cano@universidad.edu",
                "Ciencias de la Computación", "Estudiante123"));
        datos.usuarios.add(new Estudiante("Estudiante de prueba", "estudiante@universidad.edu",
                "Ingeniería", "Estudiante123"));
        datos.usuarios.add(new Lider("Líder de Videojuegos", "lider.videojuegos@universidad.edu",
                "Lider1234", datos.clubes.get(0)));
        datos.usuarios.add(new Lider("Líder de Robótica", "lider.robotica@universidad.edu",
                "Lider1234", datos.clubes.get(1)));
        return datos;
    }
}
