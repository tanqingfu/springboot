package com.demo.common;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;




public class Md5 {
	
    /**
     * 对字符串进行MD5签名
     * @param text
     * @return 密文
     */
    public static String md5(String text) {
        return md5(text, null);
    }
	
    /**
     * 对字符串进行MD5签名
     * @param text
     * @param charaset
     * @return 密文
     */
    public static String md5(String text, String charaset) {
    	if(charaset == null)
    		charaset = Globals.CHARACTER_SET;
        return DigestUtils.md5Hex(getContentBytes(text, Globals.CHARACTER_SET));

    }

    /**
     * @param content
     * @param charset
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    
    public static void main(String[] a){
    	//a2edca0148d39fe67da230da9d20ec26
    	//a2edca0148d39fe67da230da9d20ec26
    	String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode=\"002\"  partnerid=\"10051\" version=\"1.0\" time=\"1409650653876\"/><body><ticketorder  lotteryId =\"JCSPF\"  ticketsnum=\"1\"  totalmoney=\"2\"><tickets><ticket ticketId=\"7037\" betType=\"P2_1\" issueNumber=\"2014001\" betUnits=\"1\" multiple=\"1\" betMoney =\"2\" isAppend =\"0\"><betContent>3-001:[胜]/3-002:[胜]</betContent></ticket></tickets></ticketorder></body></msg>";
    	System.out.println(Md5.md5("562829"));
    	System.out.println(s);

    }
}
