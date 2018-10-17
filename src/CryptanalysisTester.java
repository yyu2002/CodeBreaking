public class CryptanalysisTester {

    public static void main(String[] args) {
        String cipherText, plainText, password;
        StatsObj statsObj = new StatsObj();

        // -========== CRACK CIPHER 1 ===========-
        // cipherText1.txt was encoded with a rotation cipher
        // -=====================================-

		/*
    cipherText = Cipher.loadFileAsString("text/cipherText1.txt");
    System.out.println("Cipher text 1:\n" + cipherText);

    int rotAmount = Cipher.bruteForceCrackRotation(cipherText);
    System.out.println("CRACKED! Rotation amount is " + rotAmount);

    plainText = Cipher.rotationCipherDecrypt(cipherText, rotAmount);

    System.out.println("PLAINTEXT IS: " + plainText);
    */

        // -========== CRACK CIPHER 2 ===========-
        // cipherText2.txt was encoded with a 2-letter password
        // ---------------------------------------

/*
        cipherText = Cipher.loadFileAsString("text/cipherText2.txt");
        System.out.println("Cipher text 2:\n" + cipherText);

        password = Cipher.bruteForceCrackLength2Password(cipherText);
        System.out.println("CRACKED! Password is " + password);

        plainText = Cipher.vigenereCipherDecrypt(cipherText, password);

        System.out.println("PLAINTEXT IS: " + plainText);
*/


        // -========== CRACK CIPHER 3 ===========-
        // cipherText2.txt was encoded with a 3-letter password
        // ---------------------------------------

/*
        cipherText = Cipher.loadFileAsString("text/cipherText3.txt");
        System.out.println("Cipher text 3:\n" + cipherText);

        password = Cipher.bruteForceCrackLength3Password(cipherText);
        System.out.println("CRACKED! Password is " + password);

        plainText = Cipher.vigenereCipherDecrypt(cipherText, password);

        System.out.println("PLAINTEXT IS: " + plainText);
*/


        cipherText = Cipher.loadFileAsString("text/cipherText4.txt");
        System.out.println("Cipher text r:\n" + cipherText);

        password = Cipher.bruteForceCrackLengthNPassword(cipherText);
        System.out.println("CRACKED! Password is " + password);

        plainText = Cipher.vigenereCipherDecrypt(cipherText, password);

        System.out.println("PLAINTEXT IS: " + plainText);


    }
}