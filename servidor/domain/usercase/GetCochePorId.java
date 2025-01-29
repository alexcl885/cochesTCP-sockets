package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Coche;

public class GetCochePorId  implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    public GetCochePorId(GenericRepositoryInterface repository) {
        this.repository = repository;
    }



    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {

        if (args.length < 1) {
            responseHttp("Debes pasar el  ID", pw);
            return false;
        }
    
        Coche cochePorModelo = (Coche)repository.get(Integer.parseInt(args[0]));
        System.out.println(cochePorModelo);
    
        if (cochePorModelo == null) {
            responseHttp("Ese coche no existe con ese modelo", pw);
            return false;
        } else {
            responseHttp("Datos del coche por id: " + cochePorModelo.toString(), pw);
            return true;
        }
    }
}
