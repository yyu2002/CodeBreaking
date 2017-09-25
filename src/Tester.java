
public class Tester {

	public static void main(String[] args) {
		String cipherText = Cipher.loadFileAsString("plainText2.txt");
		System.out.println("TEXT IS:\n" + cipherText);
		
		String text = Cipher.rotationCipherEncrypt(cipherText, 20);

		String plainText = Cipher.rotationCipherDecrypt(text, 20);
		System.out.println("PLAINTEXT IS: \n" + plainText);
	}
}