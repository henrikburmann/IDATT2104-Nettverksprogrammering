import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;



public class Client {
    static Scanner scanner = new Scanner(System.in);
    public Client(){
       
    }
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner scanner = new Scanner(System.in);
        final int PORTNR = 1234;

        System.out.println("Navn maskin:...");
        String tjener = scanner.nextLine();

        Socket socket = new Socket(tjener, PORTNR);
        System.out.println("Kobling opprettet");

        InputStreamReader readConnection = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(readConnection);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        
   
        System.out.println(reader.readLine());
        // while(true){
        //     System.out.println(reader.readLine());
        //     String t = scanner.nextLine();
        //     writer.println(t);
        //     writeNumber(reader, writer);
        //     writeNumber(reader, writer);
        //     System.out.println(reader.readLine());
        //     System.out.println("----New Equation----");
        // }

        while(true){
            try{
            System.out.println("----New Equation----");
            //Tall1
            boolean approved = false;
            while(!approved){
                System.out.println(reader.readLine());
                String num = scanner.nextLine();
                writer.println(num);
                String msg = reader.readLine();
                if(msg.equals("true")){
                    approved = true;
                }
            }

            //Choose operator
            System.out.println(reader.readLine());
            String op = "";
            approved = false;
            while(!approved){
                op = scanner.nextLine();
                if(op.equals("+") || op.equals("-")){
                    writer.println(op);
                    approved = true;
                }
                else{
                    System.out.println("Pretty simple message tbh. Plus or minus");
                }
            }   

            //Tall2
            approved = false;
            while(!approved){
                System.out.println(reader.readLine());
                String num = scanner.nextLine();
                writer.println(num);
                String msg = reader.readLine();
                if(msg.equals("true")){
                    approved = true;
                }
            }
            System.out.println(reader.readLine());

            
            
        }
        catch(IOException e){
            closeEverything(socket, reader, writer);
        }
        
    }
    
    }
            

    
    public static void closeEverything(Socket socket, BufferedReader bufferedReader, PrintWriter bufferedWriter){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedReader.close();
            }
            if(socket != null){
                socket.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    // public static String writeNumber(BufferedReader reader, PrintWriter printWriter
    // ) throws IOException{
    //     System.out.println(reader.readLine());
    //     String num = scanner.nextLine();
    //     printWriter.println(num);;
    //     boolean approved = false;
    //     printWriter.println(num);
    //     while(!approved){
    //         String msg = reader.readLine();
    //         if(msg.equals("true")){
    //             System.out.println("Godkjent: " + reader.readLine());
    //         }
    //         else{
    //             System.out.println(reader.readLine());
    //             writeNumber(reader, printWriter);
    //         }
    //     }
    //     return num;
    // }



}
