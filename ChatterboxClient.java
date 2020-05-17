package u3_chatterbox;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ChatterboxClient {


    public static void main(String[] args) {
        new ChatterboxClient().runClient();
    }
    public void runClient(){ //throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //get ip-address
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            boolean connect = true;
            try {
                while(connect){
                    //connecting client to server
                    socket = new Socket(host.getHostName(), 8000);
                    //write a message to server
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    System.out.print("Write your message: ");
                    String message = reader.readLine();
                    oos.writeObject(message);
                    //read a message from server
                    System.out.println("Waiting for server to reply");
                    ois = new ObjectInputStream(socket.getInputStream());
                    message = (String) ois.readObject();
                    System.out.println("Server: " + message);
                    //close all
                    ois.close();
                    oos.close();
                    Thread.sleep(100);
                }
            }
            catch(Exception e) {
                //block of code to handle errors
                System.out.println("Server closed");
            }
        }catch (UnknownHostException e){
            e.getCause();
        }

    }
    public void test(){
        System.out.println("test");
    }
}
