
public class Tester {

	public static void main(String[] args) {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String cipherText = Cipher.rotationCipherEncrypt(plaintext, 3, Cipher.ALPHABET);

		System.out.println("Plaintext: " + plaintext);
		System.out.println("Cipertext: " + cipherText);
	}

}