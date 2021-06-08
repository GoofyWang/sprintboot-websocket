package com.dh.util;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Md5Util {

	public static String getDigestHash(String src){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");  
			byte[] b = md.digest(src.getBytes("utf-8"));  
			return byte2HexStr(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 字节数组转16进制
	 * @param b
	 * @return
	 */
	private static String byte2HexStr(byte[] b) {  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < b.length; i++) {  
            String s = Integer.toHexString(b[i] & 0xFF);  
            if (s.length() == 1) {  
                 sb.append("0");  
            }  
            sb.append(s);  
         }  
         return sb.toString();  
    } 
	
	public static String hamcsha1(String strToSign, String password) {
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "HmacSHA1");
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);
            byte[] rawHmac = mac.doFinal(strToSign.getBytes());
            return byte2HexStr(rawHmac).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
	public static void main(String[] args) {
		System.out.println(getDigestHash("username"));
	}
}
