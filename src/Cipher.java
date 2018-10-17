import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class Cipher {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%_;?-=:\n";
    public static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

    public static final String MOST_COMMON_ENGLISH_LETTERS = " ,etaoinsrhldc";
    // public static final String MOST_COMMON_ENGLISH_LETTERS = " etaoinsrhldcumfpgwyb,.vk()_;\"='-x/0$*1jqz" ;

    public static final Dictionary dictionary = Dictionary.buildDictionary("text/words.txt");

    private static final int FORWARD = 1;
    private static final int BACKWARD = -1;

    private static final double ENGLISH_PERCENT = .4;
    private static final int NUM_CHARS = 100;
    private static double HIGHEST_ENGLISH_PERCENT = 0;


    // Set this variable to the default alphabet you wish to use
    private static final String DEFAULT_ALPHABET = ALPHABET;

    /**
     * Returns the new character after character at index is shifted by shiftAmount
     *
     * @param index       The index of the letter in the alphabet
     * @param shiftAmount the amount that the character is shifted by
     * @param alphabet    the alphabet to be used for the encryption
     * @return returns the shifted character of the alphabet
     */
    public static String shift(int index, int shiftAmount, String alphabet) {
        int newIndex = (index + shiftAmount + Math.abs(alphabet.length() * shiftAmount)) % alphabet.length();
        return alphabet.substring(newIndex, newIndex + 1);
    }

    public static String shift(int index, int shiftAmount) {
        return shift(index, shiftAmount, ALPHABET);
    }

    /**
     * Returns plaintext encrypted by the rotation cipher with a shift of
     * movement.
     *
     * @param alphabet    the alphabet to be used for the encryption
     * @param plainText   the plain text to be encrypted.
     * @param shiftAmount the number of characters in ALPHABET to shift by.
     * @return returns the encrypted plainText.
     */
    public static String rotationCipherEncrypt(String plainText, int shiftAmount, String alphabet) {
        String word = "";
        for (int i = 0; i < plainText.length(); i++) {
            int newIndex = alphabet.indexOf(plainText.substring(i, i + 1));
            if (newIndex != -1) {
                word += shift(newIndex, shiftAmount, alphabet);
            }
        }
        return word;
    }

    public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
        return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
    }

    /**
     * Returns a the result of decrypting cipherText by shiftAmount using the
     * rotation cipher.
     *
     * @param alphabet    the alphabet to be used for the encryption
     * @param cipherText  the encrypted text.
     * @param shiftAmount the key to decrypt the cipher.
     * @return returns the decrypted cipherText.
     */
    public static String rotationCipherDecrypt(String cipherText, int shiftAmount, String alphabet) {
        return rotationCipherEncrypt(cipherText, shiftAmount * BACKWARD, alphabet);
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
     * @param alphabet    the alphabet to be used for the encryption
     * @param plainText   the plain text to be encrypted.
     * @param permutation a permutation of alphabet, to be used in encoding
     * @return returns the encrypted plainText.
     */
    public static String permutationCipherEncrypt(String plainText, String permutation, String alphabet) {
        return "Need to implement permutationCipherEncrypt";
    }

    /**
     * Returns the result of decrypting cipherText with the key permutation.
     *
     * @param alphabet   the alphabet to be used for the encryption
     * @param cipherText the encrypted text.
     * @param code       the decryption key
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


    public static String getShiftedLetter(String letter, int shiftAmount, String alphabet) {
        int index = alphabet.indexOf(letter);
        if (index != -1) {
            index += shiftAmount;
            while (index >= alphabet.length()) {
                index -= alphabet.length();
            }
            while (index < 0) {
                index += alphabet.length();
            }
            String newLetter = alphabet.substring(index, index + 1);
            return newLetter;
        }
        return "";
    }

    /**
     * Returns the result of decrypting cipherText with the key code.
     *
     * @param alphabet   the alphabet to be used for the encryption
     * @param cipherText the encrypted text.
     * @param code       the decryption key
     * @param shift      the shift direction
     * @return returns the decrypted cipherText.
     */
    public static String vigenereCipher(String cipherText, String code, String alphabet, int shift) {
        String encryptedText = "";
        // cipherText = stripInvalidChars(cipherText, alphabet);
        for (int i = 0; i < cipherText.length(); i++) {
            int codeIndex = i % code.length();
            String currentCodeLetter = code.substring(codeIndex, codeIndex + 1);
            int shiftAmount = alphabet.indexOf(currentCodeLetter);
            String letter = cipherText.substring(i, i + 1);
            encryptedText += getShiftedLetter(letter, shift * shiftAmount, alphabet);
        }
        return encryptedText;
    }

    public static String vigenereCipherDecrypt(String cipherText, String code, String alphabet) {
        return vigenereCipher(cipherText, code, alphabet, BACKWARD);
    }

    public static String vigenereCipherDecrypt(String cipherText, String code) {
        return vigenereCipherDecrypt(cipherText, code, ALPHABET);
    }

    public static String vigenereCipherEncrypt(String cipherText, String code, String alphabet) {
        return vigenereCipher(cipherText, code, alphabet, FORWARD);
    }

    public static String vigenereCipherEncrypt(String cipherText, String code) {
        return vigenereCipherEncrypt(cipherText, code, ALPHABET);
    }

    public static int[] randomPermutation(int length) {
        int[] permutation = new int[length];
        for (int i = 0; i < permutation.length; i++) {
            permutation[i] = i;
        }
        for (int i = 0; i < length; i++) {
            int a = (int) (Math.random() * length);
            int b = (int) (Math.random() * length);
            int temp = permutation[a];
            permutation[a] = permutation[b];
            permutation[b] = temp;
        }
        return permutation;
    }

    public static String substitutionCipherEncrypt(String plainText, int[] permutation, String alphabet) {
        return "Need to implement";
    }

    public static String substitutionCipherDecrypt(String plainText, int[] permutation, String alphabet) {
        return "Need to implement";
    }

    public static String polybiusSquareCipherEncrypt(String plainText, String alphabet) {
        String word = "";
        int size = (int) (Math.sqrt(alphabet.length())) + 1;
        String[][] arr = new String[size][size];

        int row = -1;
        for (int i = 0; i < alphabet.length(); i++) {
            if (i % size == 0) {
                row++;
            }
            arr[row][i % size] = alphabet.substring(i, i + 1);
        }

        for (int i = 0; i < plainText.length(); i++) {
            String letter = plainText.substring(i, i + 1);
            row = -1;
            for (int j = 0; j < alphabet.length(); j++) {
                if (j % size == 0) {
                    row++;
                }
                if (arr[row][j % size].equals(letter))
                    word += row + "" + j % size;
            }
        }
        return word;
    }

    public static String polybiusSquareCipherEncrypt(String plainText) {
        return polybiusSquareCipherEncrypt(plainText, DEFAULT_ALPHABET);
    }

    public static String polybiusSquareCipherDecrypt(String codeNumber, String alphabet) {
        String word = "";
        int size = (int) (Math.sqrt(alphabet.length()));
        String[][] arr = new String[size][size + 1];

        int row = -1;
        for (int i = 0; i < alphabet.length(); i++) {
            if (i % (size + 1) == 0) {
                row++;
            }
            arr[row][i % (size + 1)] = alphabet.substring(i, i + 1);
        }

        while (codeNumber.length() > 0) {
            int r = Integer.parseInt(codeNumber.substring(0, 1));
            int c = Integer.parseInt(codeNumber.substring(1, 2));
            word += arr[r][c];
            codeNumber = codeNumber.substring(2);
        }

        return word;
    }

    public static String polybiusSquareCipherDecrypt(String plainText) {
        return polybiusSquareCipherDecrypt(plainText, DEFAULT_ALPHABET);
    }

    /**
     * returns a copy of the input plaintext String with invalid characters
     * stripped out.
     *
     * @param plaintext The plaintext string you wish to remove illegal characters
     *                  from
     * @param alphabet  A string of all legal characters.
     * @return String A copy of plain with all characters not in alphabet
     * removed.
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
                output.append(line + "\n");
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
    public static boolean isEnglish(String text, double targetPercent, Dictionary dictionary) {
        ArrayList<String> words = splitSentence(text);
        int count = 0;
        for (String word : words) {
            if (dictionary.isWord(word))
                count++;
        }
        double percentEnglish = (double) (count) / words.size();
        return percentEnglish >= targetPercent;
    }

    public static ArrayList<String> splitSentence(String sentence) {
        ArrayList<String> words = new ArrayList<String>();

        int prevIndex = 0;
        while (sentence.indexOf(" ", prevIndex) != -1) {
            int indexOfSpace = sentence.indexOf(" ", prevIndex);
            words.add(sentence.substring(prevIndex, indexOfSpace));
            prevIndex = indexOfSpace + 1;
        }
        words.add(sentence.substring(prevIndex));
        return words;
    }

    /***
     * Brute force attack cipherText by trying all possible rotation amounts until we have plaintext.
     *
     * @param cipherText the cipher text
     * @return the rotation amount the original cipher was encoded with
     */
    public static int bruteForceCrackRotation(String cipherText) {
        for (int i = 0; i < ALPHABET.length(); i++) {
            String cipher = rotationCipherEncrypt(cipherText, i);
            if (isEnglish(cipherText, ENGLISH_PERCENT, dictionary)) {
                return i;
            }
        }
        return -1;
    }

    /***
     * Brute force attack cipherText by trying all possible passwords of length 2.
     * @param cipherText
     * @return the correct password for this text.
     */
    public static String bruteForceCrackLength2Password(String cipherText) {
        String[] alphabetArr = new String[ALPHABET.length()];
        String shortText = cipherText.substring(0, cipherText.length() / 2);
        int length = ALPHABET.length();
        for (int i = 0; i < length; i++) {
            alphabetArr[i] = ALPHABET.substring(i, i + 1);
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                String code = alphabetArr[i] + alphabetArr[j];
                String decryptedText = vigenereCipherDecrypt(cipherText.substring(0, NUM_CHARS), code);
                if (isEnglish(decryptedText, ENGLISH_PERCENT, dictionary)) {
                    return code;
                }

            }
        }
        return null;
    }


    /***
     * Brute force attack cipherText by trying all possible passwords of length 3.
     //* @param cipherText
     * @return the correct password for this text.
     */
    public static String bruteForceCrackLength3Password(String cipherText) {
        String[] alphabetArr = new String[ALPHABET.length()];
        String shortText = cipherText.substring(0, cipherText.length() / 2);
        for (int i = 0; i < ALPHABET.length(); i++) {
            alphabetArr[i] = ALPHABET.substring(i, i + 1);
        }
        for (int i = 0; i < ALPHABET.length(); i++) {
            for (int j = 0; j < ALPHABET.length(); j++) {
                for (int k = 0; k < ALPHABET.length(); k++) {
                    String testCode = alphabetArr[i] + alphabetArr[j] + alphabetArr[k];
                    String decryptedText = vigenereCipherDecrypt(cipherText.substring(0, NUM_CHARS), testCode);
                    if (isEnglish(decryptedText, ENGLISH_PERCENT, dictionary)) {
                        return testCode;
                    }

                }
            }
        }
        return null;
    }

    public static String bruteForceCrackLengthNPassword(String cipherText) {
        for (int length = 1; length < 10; length++) {
            String password = "";
            String[] passwordArr = new String[length];
            StatsObj[] statsObjs = new StatsObj[length];

            for (int i = 0; i < length; i++) {
                passwordArr[i] = "";
                statsObjs[i] = new StatsObj();
            }
            for (int i = 0; i < cipherText.length(); i++) {
                String temp = cipherText.substring(i, i + 1);
                statsObjs[(i + length) % length].add(temp);
            }
            for (int i = 0; i < length; i++) {
                String mostCommon = statsObjs[i].getMostFreq();
                int shiftAmount = (ALPHABET.indexOf(mostCommon) - ALPHABET.indexOf(" ") + ALPHABET.length()) % ALPHABET.length();
                passwordArr[i] = ALPHABET.substring(shiftAmount, shiftAmount + 1);
            }
            for (int i = 0; i < length; i++) {
                password += passwordArr[i];
            }
            if (isEnglish(vigenereCipherDecrypt(cipherText, password), ENGLISH_PERCENT, dictionary))
                return password;
        }
        return "failed";
    }


}
