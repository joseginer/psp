package serpis.psp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String newLine = "\r\n";
		int port = 8080;
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner( socket.getInputStream() );
		
		while (true) {
			String line = scanner.nextLine();
			System.out.println(line);
			if (line.equals(""))
				break;
		}

		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		
		printWriter.print("HTTP/1.0 404 Not Found" + newLine);
		printWriter.print(newLine);
		
		printWriter.flush();
		
		socket.close();
		
		serverSocket.close();
	}

}
