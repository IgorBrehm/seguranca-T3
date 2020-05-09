package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class HashIt {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		try {
			Path path = Paths.get(args[0]);
			byte[] original = Files.readAllBytes(path);
			System.out.println(original.length);
			byte[] test = new byte[738];
			int aux = original.length-738;
			for(int i = 0; i < test.length; i++) {
				test[i] = original[aux];
				aux+=1;
			}
			String resp = makeHash(test);
			System.out.println(resp);
		} 
		catch (IOException exception) {
			System.out.println("Arquivo nao encontrado!");
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("Especifique o nome do arquivo!");
			System.out.println("Ex: java HashIt nomedoarquivo");
		}
	}

	public static String makeHash(byte[] original) throws NoSuchAlgorithmException {
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(original);
		
		return Hex.encodeHexString(encodedhash);
	}
}
