package servidor.infreastructure.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import servidor.application.Routing;
/**
 * Va a ser la clase por la cual el servidor 
 * va a llamar cuando un cliente manda una peticion
 * al servidor es decir; que a cada cliente se le aplica 
 * un hilo de ejecucion.
 */
public class ServidorCocheHilo extends Thread {
    private Routing routing;
    private Socket socketServidor;
    private PrintWriter pw;
    private Scanner sc;

    private boolean logged  = false;
    private boolean exit = false;

    public ServidorCocheHilo(Socket socketServidor, Routing routing) {
        this.socketServidor = socketServidor;
        this.routing = routing;
    }

    /**cada hilo ejecutara el run */
    @Override
    public void run() {
        try {
            pw = new PrintWriter(this.socketServidor.getOutputStream());
            sc = new Scanner(this.socketServidor.getInputStream());

            System.out.println("Esperando respuesta cliente");

            while (this.sc.hasNextLine()) {
              String line = this.sc.nextLine();
              InetAddress address = this.socketServidor.getInetAddress();
              System.out.printf("Recibida conexión desde %s:%d: %s%n", address.getHostAddress(), socketServidor.getPort(), line);
      
              //Ejecutamos el comando recibido del cliente.  
              this.routing.execute(pw, line, this);
              if (isExit()){
                socketServidor.close();
                pw.close();
                sc.close();
                System.exit(0);  //cerramos conexión sin errores.
              }
             
            }

        }catch (Exception e) {
            e.printStackTrace();
            try {
                socketServidor.close(); //cierro el flujo del socket
            } catch (IOException ex) {
                System.out.println("Error inesperado de E/S por el servidor");
            }
        }
    }


     /*
     * Método que setea cuando se loguea el usuario.
     */
    public boolean isLogged() {
        return this.logged;
    }

    /*
     * Método que devuelve si el cliente está logueado o no.
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isExit(){
        return this.exit;
    }

    public void setExit(){
        this.exit = true;
    }
    
}
