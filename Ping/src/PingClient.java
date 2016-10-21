import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Client send ping requests over UDP.
 */
public class PingClient
{

	/**
	 * Test Ping Client
	 * takes in host and port number as arguments
	 * creates datagram socket and InetAddress
	 * sends a ping 10 times
	 * 
	 * @param args
	 * @throws Exception
	 */
   public static void main(String[] args) throws Exception
   {
      // Get command line argument.
      if (args.length != 2) {
         System.out.println("Required arguments: host port");
         return;
      }
      
      //host and port from arguments
      String host = args[0];
      int port = Integer.parseInt(args[1]);
      
      //create datagram socket and InetAddress
      DatagramSocket dg= new DatagramSocket();
      InetAddress IPAddress =InetAddress.getByName(host);
      
      //send ping 10 times, each ping contains a sequence number and a timestamp
      for(int i=0;i<10;i++){
    	  float SendTime = System.currentTimeMillis();
    	  String Message = "Ping "+ (i+1) + " " + SendTime + "\n";
    	  DatagramPacket request = new DatagramPacket(Message.getBytes(), Message.length(),IPAddress,port );
    			  
    	  dg.send(request);
    			 
    	  DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);

    	  dg.setSoTimeout(1000);
    			  
    	  try
    		{
    			  dg.receive(reply);
    		}catch(IOException E){}

    			  Thread.sleep(1000);

      }
      
      
      
      }
}
