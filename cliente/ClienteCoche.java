package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteCoche {
    private static Socket socket; //socket muy importante
    public static void main(String[] args) {
        //PARAMETROS QUE VAMOS A IR NECESITANDO
        int puertoServidor; //el puerto que le asignare al servidor como argumento
        InetAddress serverIp;
        String lineaArgumento;
        /**
         * Comprobamos que el cliente ha puesto bien el 
         * numero de parametros para realizar el ejercicio
         */
        if (args.length < 2){
            System.out.println("Error en numero de argumentos");
            System.out.println("Asegurate de poner bien los argumentos");
            System.exit(1);
        }
        puertoServidor = Integer.parseInt(args[1]);

        try {
            socket = new Socket(args[0], puertoServidor);
            serverIp = socket.getInetAddress();
            System.out.printf("Cliente conectado con el servidor por el puerto %s....%n",
             serverIp.getHostAddress()); //saco informacion del socket para transmitirlo por pantalla
            
            //creo el flujo de entrada de los comandos que pondra el usuario en la linea de comandos
            Scanner scanner = new Scanner(System.in);
            //creo el flujo de salida que conectara la salida del cliente por la entrada del servidor
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            //creo un flujo de entrada para recibir los datos que me devuelva el servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            boolean exit=false;
            do {
                System.out.print("consulta > ");
                lineaArgumento = scanner.nextLine();
                pw.println(lineaArgumento);
                pw.flush();

                if (lineaArgumento.equals("fin")){ // si es fin se termina la interaccion con el cliente
                    exit=true;  //para salir del bucle
                    socket.close();
                    pw.close();
                    scanner.close();
                }else {
                    //si no nos preparamos para recibir lo que nos mande el servidor
                    String respuestaServidor = reader.readLine();
                    
                    System.out.println(respuestaServidor);
                }
                
            } while (!exit);

        }catch (UnknownHostException ex){
            System.out.printf("Servidor desconocido %s%n", args[0]);
            ex.printStackTrace();
            System.exit(2);
        }catch (IOException e){
            System.out.println("Error en flujo de E/S");
            e.printStackTrace();
            System.exit(3);    

        }
    }
    
}