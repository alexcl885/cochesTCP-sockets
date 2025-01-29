package servidor.domain.interfaces;

import java.util.List;
/**
 * Interfaz donde genero metodos abstractos
 * para poder implementarlos en cualquier clase para 
 * realizar un CRUD normal.
 */
public interface GenericRepositoryInterface<T> {
    //Inserto un objeto genérico
   public void  add(T o);      
   
   //Devuelve un objeto genérico
   public T get(int i);      
   
   //Devuelve la lista de objetos genéricos
   public List<T> getAll();  

   //Devuelve un objeto genérico, dado el modelo.
   public T findByModelo(String modelo);

   //Devuelve un objeto generico , dado la id
   public T findByIdToRemove(int id);

   public void put(T c);

   public T findByEmailAndPassword(String email, String pass);

   public void addUser(T u);
 
}