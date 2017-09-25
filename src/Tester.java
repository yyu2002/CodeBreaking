
public class Tester {

	public static void main(String[] args) {
		String cipherText, plainText, password;
		
		// -========== CRACK CIPHER 1 ===========-
		// cipherText1.txt was encoded with a rotation cipher
		// -=====================================-
		
		cipherText = Cipher.loadFileAsString("cipherText1.txt");
		System.out.println("Cipher text 1:\n" + cipherText);

		int rotAmount = Cipher.bruteForceCrackRotation(cipherText);
		System.out.println("CRACKED! Rotation amount is " + rotAmount);
		
		plainText = Cipher.rotationCipherDecrypt(cipherText, rotAmount);
		
		System.out.println("PLAINTEXT IS: " + plainText);		
		
		
		// -========== CRACK CIPHER 2 ===========-
		// cipherText2.txt was encoded with a 2-letter password
		// ---------------------------------------
		
//		cipherText = Cipher.loadFileAsString("cipherText2.txt");
//		System.out.println("Cipher text 2:\n" + cipherText);
//
//		password = Cipher.bruteForceCrackLength2Password(cipherText);
//		System.out.println("CRACKED! Rotation amount is " + rotAmount);
//		
//		plainText = Cipher.rotationCipherDecrypt(cipherText, rotAmount);
//		
//		System.out.println("PLAINTEXT IS: " + plainText);
		
		// -========== CRACK CIPHER 3 ===========-
		// cipherText2.txt was encoded with a 3-letter password
		// ---------------------------------------
		
//		cipherText = Cipher.loadFileAsString("cipherText2.txt");
//		System.out.println("Cipher text 3:\n" + cipherText);
//
//		password = Cipher.bruteForceCrackLength2Password(cipherText);
//		System.out.println("CRACKED! Rotation amount is " + rotAmount);
//		
//		plainText = Cipher.rotationCipherDecrypt(cipherText, rotAmount);
//		
//		System.out.println("PLAINTEXT IS: " + plainText);
	}
}