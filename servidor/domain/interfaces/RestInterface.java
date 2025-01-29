package servidor.domain.interfaces;

import java.io.PrintWriter;

public interface RestInterface {
    /*
     *  @param pw (flujo salida), args (argumentos del comando), context (hilo que atiende al cliente)
     *  @return boolean true (correcto), false(no correcto)
     * 
     */
    public boolean execute(PrintWriter pw, String [] args, Thread context);
}
