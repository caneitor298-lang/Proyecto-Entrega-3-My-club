import java.io.Serializable;

public class Lider extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private Club clubAdministrado;

    public Lider(String nombre, String correo, String contrasena, Club clubAdministrado) {
        super(nombre, correo, contrasena);
        this.clubAdministrado = clubAdministrado;
    }

    public Club getClubAdministrado() {
        return clubAdministrado;
    }

    public void setClubAdministrado(Club clubAdministrado) {
        this.clubAdministrado = clubAdministrado;
    }

    public void agregarAnuncio( Anuncio anuncio) {
        clubAdministrado.agregarAnuncio(anuncio);
    }

    public void cambiarhorario(String nuevoHorario) {
        clubAdministrado.setHorario(nuevoHorario);
    }
}
