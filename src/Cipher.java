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

		return "Need to implement rotationCipherEncrypt";
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
		return "Need to implement rotationCipherDeccrypt";
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
		return "Need to implement permutationCipherEncrypt";
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
		return "Need to implement permutationCipherDecrypt";
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
		return "Need to implement cigenereCipherEncrypt";
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
		return "need to implement vigenereCipherDecrypt";
	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return "need to implement vigenereCipherDecrypt";
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
	    int count = 0;
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else {
                // otherwise skip it &
                System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
                        // a
                        // message
                        + "\"");
            count++;
			}
		}

        System.out.println("StripInvalidChars removed a total of " + count + " chars.");
		return b.toString();
	}

	public static String loadFileAsString(String filepath) {
		StringBuilder output = new StringBuilder();

		try (Scanner scanner = new Scanner(new File(filepath))) {
		    while (scanner.hasNext()) {
		        String line = scanner.nextLine();
		        output.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error in loadFileAsString(...) with filepath: " + filepath);
		    e.printStackTrace();
        }

        return output.toString();
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
	
	/***
	 * Return true if input text is probably English (as opposed to cipherText)
	 * @param text
	 * @return true if input text is English, false otherwise
	 */
	public static boolean isEnglish(String text) {
		// you implement this!
		return false;
	}

	/***
	 * Brute force attack cipherText by trying all possible rotation amounts until we have plaintext.
	 * 
	 * @param cipherText the cipher text
	 * @return the rotation amount the original cipher was encoded with
	 */
	public static int bruteForceCrackRotation(String cipherText) {
		return 0;
	}

	/***
	 * Brute force attack cipherText by trying all possible passwords of length 2.
	 * @param cipherText
	 * @return the correct password for this text.
	 */
	public static String bruteForceCrackLength2Password(String cipherText) {
		// TODO Auto-generated method stub
		return null;
	}

	/***
	 * Brute force attack cipherText by trying all possible passwords of length 3.
	 * @param cipherText
	 * @return the correct password for this text.
	 */
	public static String bruteForceCrackLength3Password(String cipherText) {
		// TODO Auto-generated method stub
		return null;
	}
	
}