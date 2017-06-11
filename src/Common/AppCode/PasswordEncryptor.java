package Common.AppCode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
	
	private static final String ENCODING_ALGORITHM = "SHA-1";


	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	public static String getEncryptedPassword(String inputString) throws NoSuchAlgorithmException {
		return hexToString(MessageDigest.getInstance(ENCODING_ALGORITHM).digest(inputString.getBytes()));
	}
}
