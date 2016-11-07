package com.webmagic.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 生成MD5 16/32位
 */
public class _16MD5Utils {

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	  
	private static String toHexString(byte[] b) {  
	    StringBuilder sb = new StringBuilder(b.length * 2);  
	    for (int i = 0; i < b.length; i++) {  
	        sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);  
	        sb.append(HEX_DIGITS[b[i] & 0x0f]);  
	    }  
	    return sb.toString();  
	}  
	  
	public static String Bit32(String SourceString) throws Exception {  
	    MessageDigest digest = MessageDigest.getInstance("MD5");
	    digest.update(SourceString.getBytes());  
	    byte messageDigest[] = digest.digest();  
	    return toHexString(messageDigest);  
	}  
	  
	public static String Bit16(String SourceString) throws Exception {  
	    return Bit32(SourceString).substring(8, 24);  
	}

	/**
     * 获得12位UUID数
	 * @return
     */
	public static Long get12UUIDNum(){
		String s = UUID.randomUUID().toString();
		String temp = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
		return Long.valueOf(temp);
	}
}
