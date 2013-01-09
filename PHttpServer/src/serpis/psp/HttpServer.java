package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {

	
		public static void main (String[] args) throws IOException, InterruptedException{
			final int port = 8080;
			String newLine ="\r\n";
			final String fileNameError404 = "fileError404.html";
			final String response200 = "HTTP/1.0 200 OK";
			final String response404 = "HTTP/1.0 404 Not Found";
			ServerSocket serverSocket = new ServerSocket(port);
			
				Socket socket = serverSocket.accept();
				Scanner scanner = new Scanner(socket.getInputStream());
				
				String fileName="index.html";
				
				while(true){
					String line = scanner.nextLine();
					System.out.println(line);
					if (line.equals(""))
						break;
				}
				
				File file = new File (fileName);
				
				String responseFileName = file.exists() ? fileName : fileNameError404;
				String response = file.exists() ? response200 : response404;
				
				String header = response + newLine + newLine;
				
				byte[] headerBuffer = header.getBytes();
				
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(headerBuffer);
				
				final int bufferSize = 2048;
				byte[] buffer = new byte[bufferSize];
				
				FileInputStream fileInputStream = new FileInputStream(responseFileName);
				int count;
				
				while ((count = fileInputStream.read(buffer))!=-1){
				
					outputStream.write(buffer, 0, count);
				}
				//System.setProperty("line.separator", "\r\n");
				
				/*PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				
				printWriter.println("HTTP/1.0 404 Not Found");
				printWriter.println(newLine);
				
				printWriter.flush();*/
				
				fileInputStream.close();
				socket.close();
			
			serverSocket.close();
		}

}
