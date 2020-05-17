package u3_chatterbox;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatterboxServer{

    private static ServerSocket server;
    //set port
    private static int port = 8000;
    public static void main(String[] args){
       new ChatterboxServer().runServer ();
    }
    public void runServer(){
        try{
            //create the socket server
            server = new ServerSocket(port);
            //to write a chat in console
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //conection status
            boolean connect = true;
            //connection process
            while(connect){
                System.out.println("Waiting for the client request");
                //listen for the client request
                Socket socket = server.accept();
                //read message from client
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                try{
                    String message = (String) ois.readObject();
                    System.out.println("Client: " + message);
                    //stop connection when client send "bye" message
                    if(message.equalsIgnoreCase("bye")) connect = false;
                    else{
                        //send message to client
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        System.out.print("write your message: ");
                        message = reader.readLine();
                        oos.writeObject(message);
                        //close all
                        ois.close();
                        oos.close();
                        socket.close();
                }
                }catch(ClassNotFoundException e){
                    e.getException();
                }
            }
            System.out.println("Server closed");
            //close socket server
            server.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void test(){
        System.out.println("TEST");
    }
}
