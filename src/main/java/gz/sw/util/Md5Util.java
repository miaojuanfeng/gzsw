package gz.sw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	
	public static String execute(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if ((byteArray[i] >= 0) && (byteArray[i] < 16)) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(execute("123"));
//	}
}
