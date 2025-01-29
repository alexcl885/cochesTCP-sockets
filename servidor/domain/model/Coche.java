package servidor.domain.model;
import java.util.Objects;

/**
 * Clase objeto Coche
 */
public class Coche {
    private int id;
    private String modelo;
    private int cilindrada;


    public Coche() {
    }

    public Coche(int id, String modelo, int cilindrada) {
        this.id = id;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return this.cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Coche id(int id) {
        setId(id);
        return this;
    }

    public Coche modelo(String modelo) {
        setModelo(modelo);
        return this;
    }

    public Coche cilindrada(int cilindrada) {
        setCilindrada(cilindrada);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coche)) {
            return false;
        }
        Coche coche = (Coche) o;
        return id == coche.id && Objects.equals(modelo, coche.modelo) && cilindrada == coche.cilindrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, cilindrada);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", cilindrada='" + getCilindrada() + "'" +
            "}";
    }
    
}
