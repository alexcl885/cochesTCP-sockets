package servidor.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import servidor.application.Routing;
import servidor.infreastructure.server.ServidorCocheHilo;

public class ServidorCoche {
    private static Routing routing;
    
    public static void main(String[] args) {
        int puertoServidor = -1;

        if (args.length == 0) {
            System.out.println("Debes pasar el puerto a escuchar");
            System.exit(1); //error
        }
        //para comprobar que el puerto sea un entero para que no haya problemas
        try{
            puertoServidor = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.out.println("Error en el puerto");
            System.exit(2);  //cerramos la conexion
        }

        routing = new Routing();

        System.out.println("Servidor a la escucha del puerto " +puertoServidor);
        System.out.println("Esperando conexion.....");

        //al ponerlo aqui ya, luego no hay que cerrar el flujo del socket
        try (ServerSocket serverSocket = new ServerSocket(puertoServidor)) {
            while (true) {

                //Aceptamos conexión con cliente
                Socket socketClient = serverSocket.accept();
                System.out.printf("Establecida conexión con %s:%d%n",
                    socketClient.getInetAddress(),
                    socketClient.getPort()
                );
    
                //Creamos el hilo pasándole el administrador de servicios Rest
                new ServidorCocheHilo(socketClient,  routing).start();
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
     
    }
}
