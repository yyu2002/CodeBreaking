
public class Tester {

	public static void main(String[] args) {
		String cipherText = Cipher.loadFileAsString("cipherText1.txt");
		System.out.println("CIPHER TEXT IS:\n" + cipherText);
		
		String plainText = Cipher.rotationCipherDecrypt(cipherText, -20);
		System.out.println("PLAINTEXT IS: \n" + plainText);
		
	}
}