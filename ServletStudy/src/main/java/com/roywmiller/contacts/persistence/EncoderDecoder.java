package com.roywmiller.contacts.persistence;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncoderDecoder {
	protected BASE64Encoder encoder = new BASE64Encoder();
	protected BASE64Decoder decoder = new BASE64Decoder();	
	
	public EncoderDecoder() {
	}
	
	public String encode(String plainTextInput) {
		return encoder.encode(plainTextInput.getBytes());
	}

	public String decode(String encodedInput) {
		String decodedInput = null;
		try {
			decodedInput = new String(decoder.decodeBuffer(encodedInput));
		} catch (Exception e) {
			throw new RuntimeException("Error decoding input.", e);
		}
		
		return decodedInput;
	}
	
	public static void main(String[] args) {
		EncoderDecoder ed = new EncoderDecoder();
		System.out.println(ed.encode("somepassword"));		
	}
}
