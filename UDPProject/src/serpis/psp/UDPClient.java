package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		int port = 10001;
		
		DatagramSocket datagramSocket = new DatagramSocket();
		byte[] buf = new byte[2048];
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, port);
		
		while(true) {
			String text = "Hola desde UDPClient " + new Date();
			StringUtil.FillByteArray(buf, text);
			datagramPacket.setLength(text.getBytes().length);
			datagramSocket.send(datagramPacket);
			
			datagramPacket.setLength(buf.length);
			datagramSocket.receive(datagramPacket);
			
			String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.printf("Receive Data='%s'\n", data);
			
			Thread.sleep(5000); //milisecons
		}

		//System.out.println("UDPClient fin.");
	}

}
