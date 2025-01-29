package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Coche;
import servidor.infreastructure.server.ServidorCocheHilo;

public class DeleteCoche  implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }
    //constructor
    public DeleteCoche (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (args.length < 1){
            responseHttp("Debes pasar el nombre del modelo", pw);
            return false;
        }

        if (!((ServidorCocheHilo)context).isLogged()){
            responseHttp("Acción no permitidq. Debes estar registrado!!", pw);
            return false;
        }
        
        Coche delCoche = (Coche)repository.findByIdToRemove(Integer.parseInt(args[0]));
        if (delCoche == null){
            responseHttp("Ese coche no exixte", pw);
            return false;
        }
        else{
            responseHttp("Datos del coche borrado: " + delCoche.toString(), pw);
            return true;
        }
    }
    
}
