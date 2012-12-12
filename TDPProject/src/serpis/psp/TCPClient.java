package serpis.psp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class TCPClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//holaMundo();
		//pingPong();
		//connectAndRead(args);
		connectUpvWeb();
		System.out.println("TCPClient fin.");
	}
	
	private static void pingPong() {
		// message + message.toLowerCase();
		
	}
	
	private static void connectUpvWeb() throws IOException {
		String host = "www.upv.es";
		int port = 80;
		String line1 = "GET /index.html HTTP/1.1";
		String line2 = "";
		
		InetAddress inetAddress = InetAddress.getByName(host);
		Socket socket = new Socket(inetAddress, port);
		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true); //autoflush=true
		printWriter.println(line1);
		printWriter.println(line2); //->printWriter.println();
		
		Scanner scanner = new Scanner( socket.getInputStream() );
		
		while ( scanner.hasNextLine() ) 
			System.out.println( scanner.nextLine() );
		
		socket.close();
	}
	
	private static void connectAndRead(String[] args) throws IOException {
		System.out.println("Argumentos:");
		for (int index = 0; index < args.length; index++)
			System.out.println(args[index]);
	
		String host = args[0]; 
		int port = Integer.parseInt( args[1] );
		InetAddress inetAddress = InetAddress.getByName(host);
		Socket socket = new Socket(inetAddress, port);
		
		Scanner scanner = new Scanner( socket.getInputStream() );
		
		while ( scanner.hasNextLine() ) 
			System.out.println( scanner.nextLine() );
		
		socket.close();
	}
	
	private static void holaMundo() throws UnknownHostException, IOException {
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		int port = 10001;
		Socket socket = new Socket(inetAddress, port);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		printWriter.println("Hola desde TCPClient a las " + new Date());
		
		printWriter.close();
		socket.close();
	}
}
