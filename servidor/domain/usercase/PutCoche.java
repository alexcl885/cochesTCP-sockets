package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Coche;
import servidor.infreastructure.server.ServidorCocheHilo;

public class PutCoche implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    public PutCoche(GenericRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (args.length < 1){
            responseHttp("Debes pasar el nombre del modelo", pw);
            return false;
        }
        try {
            // id del coche
            int id = Integer.parseInt(args[0]);

            if (!((ServidorCocheHilo)context).isLogged()){
            responseHttp("Acción no permitidq. Debes estar registrado!!", pw);
            return false;
            }
            // coche con los nuevos datos
            Coche cocheActualizado = new Coche();
            cocheActualizado.setId(id);
            cocheActualizado.setModelo(args[1]);
            cocheActualizado.setCilindrada(Integer.parseInt(args[2])); 
            
            repository.put(cocheActualizado);

            
            responseHttp("Coche actualizado correctamente.", pw);
            return true;
        }catch (Exception e) {
            responseHttp("Error al actualizar el coche: " + e.getMessage(), pw);
            return false;
        }
    }
}
