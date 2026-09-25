import java.io.Serializable;
import java.util.ArrayList;
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Anuncio> anuncios;

    private String nombre;
    private String descripcion;
    private String categoria;
    private String ubicacion;
    private String horario;

    public Club(String nombre, String descripcion, String categoria, String ubicacion, String horario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.horario = horario;
        this.anuncios = new ArrayList<>();
    }

    public ArrayList<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void agregarAnuncio(Anuncio anuncio) {
        anuncios.add(anuncio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Club{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}
