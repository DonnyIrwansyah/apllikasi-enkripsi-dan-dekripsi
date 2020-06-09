
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Donny Irwansyah
 */
public class ViginereCipher {
	private static Scanner in;
    private static String message;
    private static String mappedKey;

    public static void main(String[] args){
        in = new Scanner(System.in);
        System.out.print("1. Encryption\n2. Decryption\nChoose(1,2): ");
        int choice = in.nextInt();
        in.nextLine();

        if(choice == 1){
            System.out.println("---Encryption---");
            msgAndKey();
            cipherEncryption(message, mappedKey);
        } else if(choice == 2){
            System.out.println("---Decryption---");
            msgAndKey();
            cipherDecryption(message, mappedKey);

        } else {
            System.out.println("Incorrect Choice");
        }
    }

    private static void cipherDecryption(String message, String mappedKey) {
        int[][] table = createVigenereTable();
        String decryptedText = "";

        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && mappedKey.charAt(i) == (char)32){
                decryptedText += " ";
            } else {
                decryptedText += (char)(65 + itrCount((int)mappedKey.charAt(i), (int)message.charAt(i)));
            }
        }

        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static int itrCount(int key, int msg) {
        int counter = 0;
        String result = "";
        for (int i = 0; i < 26; i++) {
            if(key+i > 90){
                //90 being ASCII of Z
                result += (char)(key+(i-26));

            } else {
                result += (char)(key+i);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == msg){
                break; // letter found
            } else {
                counter++;
            }
        }
        return counter;
    }

    private static void cipherEncryption(String message, String mappedKey) {
        int[][] table = createVigenereTable();
        String encryptedText = "";
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && mappedKey.charAt(i) == (char)32){
                encryptedText += " ";
            } else {
                encryptedText += (char)table[(int)message.charAt(i)-65][(int)mappedKey.charAt(i)-65];
            }
        }

        System.out.println("Encrypted Text: " + encryptedText);
    }

    private static int[][] createVigenereTable() {
        // creating 26x26 table containing alphabets
        int[][] tableArr = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if((i+65)+j > 90){
                    temp = ((i+65)+j) -26;
                    tableArr[i][j] = temp;
                } else {
                    temp = (i+65)+j;
                    tableArr[i][j] = temp;
                }
            }
        }


        return tableArr;
    }

    private static void msgAndKey() {
        System.out.println("***Message and key should be alphabetic***");

        //message input
        System.out.print("Enter Message: ");
        String msg = in.nextLine();
        msg = msg.toUpperCase();

        //key input
        System.out.print("Enter Key: ");
        String key = in.next();
        in.nextLine();
        key = key.toUpperCase();

        //mapping key to message
        String keyMap = "";
        for (int i = 0, j = 0; i < msg.length(); i++) {
            if(msg.charAt(i) == (char)32){
                //ignoring space
                keyMap += (char)32;

            } else {
                //mapping letters of key with message
                if(j < key.length()){
                    keyMap += key.charAt(j);
                    j++;
                } else {
                    j = 0;
                    keyMap += key.charAt(j);
                    j++; 
                }
            } //if-else

        } //for
        message = msg;
        mappedKey = keyMap;

        System.out.println("Message: " + message);
        System.out.println("key: " + mappedKey);
    }
}