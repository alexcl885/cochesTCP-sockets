package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Coche;

/**
 * Caso de uso en el cual la intencion es buscar 
 * el coche segun el modelo del coche que ponga el cliente 
 * por la terminal.
 */
public class GetCochePorModelo  implements RestInterface{
    private GenericRepositoryInterface repository;
    
    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    public GetCochePorModelo(GenericRepositoryInterface repository) {
        this.repository = repository;
    }



    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {

        if (args.length < 1) {
            responseHttp("Debes pasar el nombre del modelo", pw);
            return false;
        }
    
        Coche cochePorModelo = (Coche)repository.findByModelo(args[0]);
        System.out.println(cochePorModelo);
    
        if (cochePorModelo == null) {
            responseHttp("Ese coche no existe con ese modelo", pw);
            return false;
        } else {
            responseHttp("Datos del coche: " + cochePorModelo.toString(), pw);
            return true;
        }
    }
    
}
