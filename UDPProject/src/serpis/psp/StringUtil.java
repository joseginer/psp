package serpis.psp;

public class StringUtil {

	public static void FillByteArray(byte[] buf, String data)
	{
		byte[] bufData = data.getBytes();
		for (int index=0; index < bufData.length; index++)
			buf[index] = bufData[index];
	}
}
