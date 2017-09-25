import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class Cipher {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%_;?-=:\n";
	public static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

	private static final int FORWARD = 1;
	private static final int BACKWARD = -1;

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plainText, int shiftAmount, String alphabet) {
		plainText = stripInvalidChars(plainText, alphabet);
		String output = "";

		for (int i = 0; i < plainText.length(); i++) {
			String plainLetter = plainText.substring(i, i + 1);
			output += rotateLetter(plainLetter, shiftAmount, alphabet);
		}

		return output;
	}

	private static String rotateLetter(String plainLetter, int shiftAmount, String alphabet) {
		int index = alphabet.indexOf(plainLetter);
		if (index == -1)
			return "";

		index += shiftAmount;
		while (index < 0)
			index += alphabet.length();
		while (index >= alphabet.length())
			index -= alphabet.length();

		return alphabet.substring(index, index + 1);
	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipherText, int shiftAmount, String alphabet) {
		return rotationCipherEncrypt(cipherText, -shiftAmount, alphabet);
	}

	public static String rotationCipherDecrypt(String cipherText, int shiftAmount) {
		return rotationCipherDecrypt(cipherText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns plaintext encrypted by the permutation cipher encoded with the
	 * input permutation. A permutation is given by a String the same length as
	 * alphabet that contains the exact same characters as alphabet but possibly
	 * in another ordering.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param permutation
	 *            a permutation of alphabet, to be used in encoding
	 * @return returns the encrypted plainText.
	 */
	public static String permutationCipherEncrypt(String plainText, String permutation, String alphabet) {
		return "";
	}

	/**
	 * Returns the result of decrypting cipherText with the key permutation.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String permutationCipherDecrypt(String cipherText, String code, String alphabet) {
		return "";
	}

	/***
	 * Returns a random permutation of the alphabet string
	 * 
	 * @param alphabet
	 *            represents the alphabet
	 * @return a random permutation of alphabet
	 */
	public static String generateRandomPermutation(String alphabet) {
		return "";
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plainText, String code, String alphabet) {
		plainText = stripInvalidChars(plainText, alphabet);
		return vigenereCipherShift(plainText, code, alphabet, FORWARD);
	}

	public static String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipherText, String code, String alphabet) {
		return vigenereCipherShift(cipherText, code, alphabet, BACKWARD);
	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	private static String vigenereCipherShift(String text, String code, String alphabet, int direction) {
		String output = "";
		int codeLetterIndex = 0;

		for (int i = 0; i < text.length(); i++) {
			String plainLetter = text.substring(i, i + 1);
			String shiftLetter = code.substring(codeLetterIndex, codeLetterIndex + 1);

			codeLetterIndex++;
			codeLetterIndex = codeLetterIndex % code.length();

			int shiftAmount = alphabet.indexOf(shiftLetter);
			if (shiftAmount != -1) {
				String encodedLetter = rotateLetter(plainLetter, direction * shiftAmount, alphabet);
				output += encodedLetter;
			}
		}

		return output;
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	public static String loadFileAsString(String filename) {
		String content = "ERROR"; // default value that gets overwritten
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			URL resource = classLoader.getResource(filename);
			File file = new File(resource.toURI());

			// Read File Content
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	public static boolean saveStringToFile(String contents, String filepath) {
		File outFile = new File(filepath);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
			writer.write(contents);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}