package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.infreastructure.server.ServidorCocheHilo;

public class LogoutUsuario implements RestInterface {
    private GenericRepositoryInterface repository;

    public LogoutUsuario (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (!((ServidorCocheHilo)context).isLogged()){
            responseHttp("Acción no permitida. Debes estar registrado!!", pw);
            return false;
        }
        //modificamos el contexto con el login a falso.
        ((ServidorCocheHilo)context).setLogged(false);
        ((ServidorCocheHilo)context).setExit();
        responseHttp("Usuario deslogueado", pw);
        return true;
    }
}
