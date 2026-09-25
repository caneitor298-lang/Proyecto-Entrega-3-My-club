public class UsuarioController {
    private final DatosAplicacion datos;
    public UsuarioController(DatosAplicacion datos) { this.datos = datos; }
    
    public Usuario iniciarSesion(String correo, String contrasena) {
        if (correo == null || correo.trim().isEmpty() || contrasena == null || contrasena.isEmpty()) return null;
        for (Usuario usuario : datos.getUsuarios()) {
            if (usuario.getCorreo().equalsIgnoreCase(correo.trim())
                    && usuario.getContrasena().equals(contrasena)) return usuario;
        }
        return null;
    }
 
    
    public Estudiante registrarEstudiante(String nombre, String correo, String carrera, String contrasena) {
        // 1. Ningún campo vacío
        if (nombre == null || nombre.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || carrera == null || carrera.trim().isEmpty()
                || contrasena == null || contrasena.isEmpty()) {
            throw new IllegalArgumentException("Completa todos los campos.");
        }
 
        nombre = nombre.trim();
        correo = correo.trim();
        carrera = carrera.trim();
 
        // 2. Correo institucional
        if (!correo.toLowerCase().endsWith("@universidad.edu")) {
            throw new IllegalArgumentException("Usa tu correo institucional (@universidad.edu).");
        }
 
        // 3. Correo no repetido
        for (Usuario usuario : datos.getUsuarios()) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                throw new IllegalArgumentException("Ya existe una cuenta con ese correo.");
            }
        }
 
        // 4. Contraseña de al menos 8 caracteres
        if (contrasena.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
 
        // 5. Crear y guardar el estudiante
        Estudiante nuevo = new Estudiante(nombre, correo, carrera, contrasena);
        datos.getUsuarios().add(nuevo);
        return nuevo;
    }
}
