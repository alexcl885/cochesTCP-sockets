package servidor.domain.usercase;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Coche;

public class GetListaCoches implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    public GetListaCoches(GenericRepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        
        //aqui obtengo la lista de coches
        List<Coche> coches = (List<Coche>)repository.getAll();
        
        responseHttp("Datos:" + coches.toString(), pw);

        return true;
    }
    
}
