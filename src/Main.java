
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 这个例子演示了java乱码及解决的原理
 * @author Administrator
 *
 */
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {		
		readStrFromFile("C:\\Users\\张嘉芮\\Desktop\\xue\\课堂源代码\\11课源代码\\ch11/1.txt");
		readStrFromFile("C:\\Users\\张嘉芮\\Desktop\\xue\\课堂源代码\\11课源代码\\ch11/2.txt"); 
		byteToStr();
	}
	public static void byteToStr() {
		String str="你好";
		String str2=new String(str.getBytes());
		System.out.println(str2);
		
		try {
			str2=new String(str.getBytes("gbk"),"utf-8");
			
			System.out.println(str2);
			str2=new String(str.getBytes("gbk"),"gbk");
			
			System.out.println(str2);
			str2=new String(str.getBytes("utf-8"),"utf-8");
			System.out.println(str2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void readStrFromFile(String filename) {

		try {
			
			byte[] data = readBytes(filename);
			String str=new String(data,"utf-8");
			System.out.println(str);
		} catch ( Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  
	}

	public static byte[] readBytes(String filename) throws FileNotFoundException, IOException {
		File src = null;
		int totalReaded = 0;
		int readed = 0;
		FileInputStream reader = null;
		byte data[];
		try {
			src = new File(filename);
			Long len = src.length();
			data = new byte[len.intValue()];
			for (reader = new FileInputStream(filename); reader.available() > 0;) {
				readed = reader.read(data, totalReaded, (len.intValue() - totalReaded));
				totalReaded += readed;
			}
		} finally {
			close(reader);
		}
		return data;
	}

	/**
	 * 关闭一个输入 输出流
	 * @param inout
	 */
	public static void close(Closeable inout) {
		if (inout != null) {
			try {
				inout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
