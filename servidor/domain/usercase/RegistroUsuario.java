package servidor.domain.usercase;

import java.io.PrintWriter;

import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.model.Usuario;

public class RegistroUsuario implements RestInterface{
    private GenericRepositoryInterface repository;

    public RegistroUsuario (GenericRepositoryInterface repository){
        this.repository = repository;
    }

    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }

    @Override
    public boolean execute(PrintWriter pw, String[] args, Thread context) {
        if (args.length < 3){
            responseHttp("Debes pasar el nombre, email y passw", pw);
            return false;
        }
        Usuario usuario = (Usuario)repository.findByEmailAndPassword(args[0], args[1]);

        if (usuario != null){
            responseHttp("Ese usuario ya está registrado ", pw);
            return false;
        }
        else{
            repository.addUser(new Usuario(args));
            responseHttp("-Usuario añadido: " + " [nombre:" + args[0]+" email:" + args[1]+" passw:"+ args[2] +"] -", pw);
            return true;
        }
    }
}
