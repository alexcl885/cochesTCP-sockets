package servidor.domain.model;
import java.util.Objects;

public class Usuario {
    private int id;  //Clave principal
    private String nombre;
    private String email;
    private String passwd;


    public Usuario(String[] args) {
        this.nombre = args[0];
        this.email = args[1];
        this.passwd = args[2];
    }

    public Usuario(int id, String nombre, String email, String passwd) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwd = passwd;
    }
    
    

    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Usuario id(int id) {
        setId(id);
        return this;
    }

    public Usuario nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Usuario email(String email) {
        setEmail(email);
        return this;
    }

    public Usuario passwd(String passwd) {
        setPasswd(passwd);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nombre, usuario.nombre) && Objects.equals(email, usuario.email) && Objects.equals(passwd, usuario.passwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, passwd);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", passwd='" + getPasswd() + "'" +
            "}";
    }
    
}
