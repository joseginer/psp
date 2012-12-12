package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[2048];
		int port = 10001;
		
		
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		
		DatagramSocket datagramSocket = new DatagramSocket(port, inetAddress);
		
		while (true) {
			datagramPacket.setLength(buf.length);
			datagramSocket.receive(datagramPacket);
			
			String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.printf("Data='%s' InetAddress=%s Port=%d", 
					data, datagramPacket.getAddress(), datagramPacket.getPort());
			System.out.printf("length=%d\n", datagramPacket.getLength());
			
			data = data + data.toLowerCase();

			StringUtil.FillByteArray(buf, data);
			datagramPacket.setLength(data.getBytes().length);
			
			datagramSocket.send(datagramPacket);
		}
	}

}
