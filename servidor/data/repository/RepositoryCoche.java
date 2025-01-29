package servidor.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.model.Coche;
import servidor.domain.model.Usuario;

public class RepositoryCoche<T> implements GenericRepositoryInterface<T> {

    private AtomicInteger automaticId;
    private AtomicInteger automaticIdUser;
    
    private List<Coche> listCoches;

    private List<Usuario> listUsuarios;
    
    public RepositoryCoche() {
        automaticId = new AtomicInteger(3);
        listCoches = new ArrayList<Coche>();
        listCoches.add(new Coche(1,"mercedes", 2000));
        listCoches.add(new Coche(2,"BMW", 65000));
        automaticIdUser = new AtomicInteger(2);
        listUsuarios = new ArrayList<Usuario>();
        listUsuarios.add(new Usuario(1, "alex", "alex", "alex"));
    }

    @Override
    synchronized public void add(T o) {
        if (o instanceof Coche){
            Coche coche = (Coche) o;
            coche.setId(automaticId.incrementAndGet());
            listCoches.add(coche);
        } 
    }

    @Override
    public T get(int i) {
        Coche coche = listCoches
            .stream()
            .filter(u -> u.getId()==i)
            .findFirst()
            .orElse(null);
        return (T)coche;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return new ArrayList<>((List<T>) listCoches);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByModelo(String modelo) {
        return (T) listCoches.stream()
                 .filter(coche -> coche.getModelo().equalsIgnoreCase(modelo))
                 .findFirst()
                 .orElse(null);
    }

    @Override
    public T findByIdToRemove(int id) {
        Coche coche = listCoches
        .stream()
        .filter(c -> c.getId()== id)
        .findFirst()
        .orElse(null);
        
        listCoches.remove(coche); //borro el coche de la lista

        return (T)coche;

    }

    @Override
    public void put(T c) {
        if (c instanceof Coche) {
            Coche cocheActualizado = (Coche) c; //coche con los nuevos atributos
            int id = cocheActualizado.getId(); //id del coche que se ira a actualizar
    
            Coche cocheExistente = listCoches
                .stream()
                .filter(co -> co.getId() == id)
                .findFirst()
                .orElse(null);
    
            if (cocheExistente != null) {
                cocheExistente.setModelo(cocheActualizado.getModelo());
                cocheExistente.setCilindrada(cocheActualizado.getCilindrada());
               
            }
        }
        
    }

    @Override
    synchronized public T findByEmailAndPassword(String email, String pass) {
        Usuario usuario = listUsuarios.stream()
        .filter(
            (u) -> u.getEmail().equals(email) &&
                    u.getPasswd().equals(pass)
        ).findFirst()
        .orElse(null);
        return (T) usuario;
    }

    @Override
    public void addUser(T u) {
        if (u instanceof Usuario){
            Usuario user = (Usuario) u;
            user.setId(automaticIdUser.incrementAndGet());
            listUsuarios.add(user);
        }       
    }
    
}
