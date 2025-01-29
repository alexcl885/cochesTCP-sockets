package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Usuario;
import servidor.infreastructure.server.ServidorCocheHilo;

public class LoginUsuario implements RestInterface {
    private GenericRepositoryInterface repository;

    public LoginUsuario (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }
    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (args.length < 2){
            responseHttp("Debes pasar el email y passw", pw);
            return false;
        }
        Usuario usuario = (Usuario)repository.findByEmailAndPassword(args[0], args[1]);
        if (usuario== null){
            responseHttp("Usuario no encontrado", pw);
            return false;
        }
        else{
            responseHttp("Usuario logueado correctamente ", pw);
            ((ServidorCocheHilo)context).setLogged(true);
            return true;
        }
    }
    
}
