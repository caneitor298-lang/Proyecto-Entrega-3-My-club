public class UsuarioController {
    private final DatosAplicacion datos;
    public UsuarioController(DatosAplicacion datos) { this.datos = datos; }
    /** Devuelve el usuario real y su rol, o null si las credenciales son incorrectas. */
    public Usuario iniciarSesion(String correo, String contrasena) {
        if (correo == null || correo.trim().isEmpty() || contrasena == null || contrasena.isEmpty()) return null;
        for (Usuario usuario : datos.getUsuarios()) {
            if (usuario.getCorreo().equalsIgnoreCase(correo.trim())
                    && usuario.getContrasena().equals(contrasena)) return usuario;
        }
        return null;
    }
}