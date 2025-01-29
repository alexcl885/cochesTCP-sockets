package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Coche;
import servidor.infreastructure.server.ServidorCocheHilo;

public class PostCoche  implements RestInterface{
    private GenericRepositoryInterface repository;
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }
    public PostCoche(GenericRepositoryInterface repository){
        this.repository = repository;
    }
    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (args.length < 2){
            responseHttp("Debes pasar el nombre del modelo", pw);
            return false;
        }

        if (!((ServidorCocheHilo)context).isLogged()){
            responseHttp("Acción no permitidq. Debes estar registrado!!", pw);
            return false;
        }
        
        Coche addCo = new Coche();
        addCo.setModelo(args[0]);
        addCo.setCilindrada(Integer.parseInt(args[1]));
        repository.add((Coche)addCo);
        responseHttp("Coche añadido: " + addCo.toString(), pw);
        return true;
    }

}
