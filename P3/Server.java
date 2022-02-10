import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
 

    public Server(ServerSocket serverSocket){
      
    }
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1234);
        try{

            while(true){
                
                new ClientHandler(serverSocket.accept()).start();
             
            }
        }
        catch(IOException e){
            e.printStackTrace();
            serverSocket.close();
        }
    }
   
}