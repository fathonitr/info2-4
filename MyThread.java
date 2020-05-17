package u3_chatterbox;

public class MyThread extends Thread{
    ChatterboxServer server=new ChatterboxServer();
    ChatterboxClient client=new ChatterboxClient();

    public void run(){
        System.out.println("Thread says Hello");
        server.runServer();
        client.runClient();
    }
    public static void main(String args[]){
        (new MyThread()).start();

    }
}
