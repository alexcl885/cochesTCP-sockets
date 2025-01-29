package servidor.application;

import java.io.PrintWriter;
import java.util.HashMap;

import servidor.data.repository.RepositoryCoche;
import servidor.domain.interfaces.GenericRepositoryInterface;
import servidor.domain.interfaces.RestInterface;
import servidor.domain.usercase.PostCoche;
import servidor.domain.usercase.DeleteCoche;
import servidor.domain.usercase.GetCochePorId;
import servidor.domain.usercase.GetCochePorModelo;
import servidor.domain.usercase.GetListaCoches;
import servidor.domain.usercase.LoginUsuario;
import servidor.domain.usercase.LogoutUsuario;
import servidor.domain.usercase.PutCoche;
import servidor.domain.usercase.RegistroUsuario;
import servidor.infreastructure.server.ServidorCocheHilo;

public class Routing {
    private final HashMap<String, RestInterface> maganerEndPoints;  
    private final GenericRepositoryInterface repository; 

    public Routing(){
        this.repository = new RepositoryCoche<>();  
        maganerEndPoints = new HashMap<>(); 
        maganerEndPoints.put("get?", new GetListaCoches(repository));
        maganerEndPoints.put("model", new GetCochePorModelo(repository));
        maganerEndPoints.put("put", new PutCoche(repository) );
        maganerEndPoints.put("post", new PostCoche(repository));
        maganerEndPoints.put("delete", new DeleteCoche(repository));
        maganerEndPoints.put("get", new GetCochePorId(repository));
        maganerEndPoints.put("log", new LoginUsuario(repository));
        maganerEndPoints.put("deslog", new LogoutUsuario(repository));
        maganerEndPoints.put("addUser", new RegistroUsuario(repository));
    }

    public void responseHttp(String response, PrintWriter pw){
        pw.println(response);
        pw.flush();
    }
    public boolean execute(PrintWriter pw, String body, ServidorCocheHilo context){
        System.out.println (body);
        String [] args = body.split(" ");
        try {
            String verb  = args[0];
            RestInterface endPoint = maganerEndPoints.get(verb);

            if (endPoint == null){
                responseHttp("Error, debes pasar un comando válido", pw);
                return false;
            }
            String [] operationsArgs = new String[args.length - 1]; 
            System.arraycopy(args, 1,  operationsArgs, 0, args.length - 1);
            return(endPoint.execute(pw, operationsArgs, context));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }
}
