// Java program to illustrate Client side
// Implementation using DatagramSocket
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient
{
	public static void main(String args[]) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		// Step 1:Create the socket object for
		// carrying the data.
		DatagramSocket ds = new DatagramSocket();
        DatagramPacket DpRecive;
		InetAddress ip = InetAddress.getLocalHost();
		byte buf[] = null;
        byte[] bufRecive = new byte[65535];
		// loop while user not enters "bye"
		while (true)
		{

            for (int i = 0; i < 3; i++) {
                String val = sc.nextLine();
                buf = val.getBytes();
                // Step 2 : Create the datagramPacket for sending
                // the data.
                DatagramPacket DpSend =
                    new DatagramPacket(buf, buf.length, ip, 1234);
    
                // Step 3 : invoke the send call to actually send
                // the data.
                ds.send(DpSend);
                
                // break the loop if user enters "bye"
                if (val.equals("bye"))
                    break;
            }


            DpRecive = new DatagramPacket(bufRecive, bufRecive.length);
            ds.receive(DpRecive);
            System.out.println(data(bufRecive).toString());
			bufRecive = new byte[65535];
            
            
		}
	}

    // A utility method to convert the byte array
	// data into a string representation.
	public static StringBuilder data(byte[] a)
	{
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}
