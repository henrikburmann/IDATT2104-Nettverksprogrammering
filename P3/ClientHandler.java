import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    // public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    public ClientHandler(Socket socket) throws IOException{
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        // clientHandlers.add(this);
    }


    @Override
    public void run() {
        int i = 0;
        writer.println("Welcome to this very simple calculator");
        while(true){
            try{
            int num1 = 0;
            int num2 = 0;
            i++;
            System.out.println(i);
            writer.println("Give us a number");
            num1 = getNumber();
            

            //Choose operator
            writer.println("Gives us either a plus or a minus");
            String op = reader.readLine();
            //Tall2
            writer.println("Another one");
            num2 = getNumber();      
            
            // String result = num1 + " + " + num2 + " = " + (num1 + num2);
            String result = calculate(op, num1, num2);
            writer.println(result);
        }
        catch(IOException e){
            closeEverything(socket, reader, writer);
        }
    }
    
        
    }

    public int getNumber() throws IOException{
        int i = 0;
        boolean correct = false;
        
        while(!correct){
        
            try{
            String num = reader.readLine();

                if(checkAnswer(num)){
                    i = Integer.parseInt(num);
                    writer.println("true");
                    correct = true;
                }
                else{
                    writer.println("Nope");
                    writer.println("Try at least");
                }
            }

            catch(IOException e){
                closeEverything(socket, reader, writer);
            }
        }
            
            
            return i;
    }

    public static boolean checkAnswer(String num){
        if(!num.matches("[0-9]+")){
            return false;
        }
        else{
            return true;
        }
    }

    public static String calculate(String op, int num1, int num2){
        if(op.equals("+")){
            return num1 + " " + op + " " + num2 + " = " + (num1 + num2);
        }
        else if(op.equals("-")){
            return num1 + " " + op + " " + num2 + " = " + (num1 - num2);
        }
        return "Unvalid";
    }

    public void closeEverything(Socket socket, BufferedReader reader, PrintWriter writer){
        try{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
            if(socket != null){
                socket.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
