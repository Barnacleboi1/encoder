package chucknorris;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loop: while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()){
                case ("encode") -> {
                    System.out.println("Input string:");
                    String input = scanner.nextLine();
                    encode(input);
                }
                case ("decode") -> {
                    System.out.println("Input encoded string:");
                    String input = scanner.nextLine();
                    decode(input);
                }
                case ("exit") -> {
                    System.out.println("Bye!");
                    break loop;
                }
                default -> {
                    System.out.println("There is no '" + choice + "' operation");
                    System.out.println();
                }
            }
        }
    }
    public static void encode(String input) {
        char[] array = input.toCharArray();
        StringBuilder binaryInput = new StringBuilder();

        System.out.println("Encoded string:");
        for (char c : array) {
            binaryInput.append(String.format("%7d", Integer.parseInt(Integer.toBinaryString(c))).replace(' ', '0'));
        }
        List<String> substrings = new ArrayList<>();
        int i = 0;
        while (i < binaryInput.length()) {
            int n = i;
            String currentString = "";
            while (n < binaryInput.length() && binaryInput.charAt(i) == binaryInput.charAt(n)) {
                n++;
            }
            currentString = binaryInput.substring(i, n);
            substrings.add(currentString);
            i = n;
        }
        StringBuilder result = new StringBuilder();
        for (String s : substrings) {
            if (s.contains("0")) {
                s = "00 " + "0".repeat(s.length()) + " ";
            } else if (s.contains("1")){
                s = "0 " + "0".repeat(s.length()) + " ";
            }
            result.append(s);
        }

        System.out.println(result);
        System.out.println();
    }
    public static void decode(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!input.matches("^[0 ]+$")) {
                System.out.println("Encoded string is not valid.");
                System.out.println();
                return;
            }
        }

        String[] array = input.split(" ");
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                if (!(Objects.equals(array[i], "00") || Objects.equals(array[i], "0"))) {
                    System.out.println("Encoded string is not valid.");
                    System.out.println();
                    return;
                }
            }
        }
        if (array.length % 2 != 0) {
            System.out.println("Encoded string is not valid.");
            System.out.println();
            return;
        }

        StringBuilder binaryInput = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                if (array[i].length() == 1) {
                    binaryInput.append("1".repeat(array[i + 1].length()));
                } else {
                    binaryInput.append("0".repeat(array[i + 1].length()));
                }
            }
        }
        String[] binary = binaryInput.toString().split("(?<=\\G.{" + 7 + "})");
        StringBuilder result = new StringBuilder();
        for (String s : binary) {
            if (s.length() != 7) {
                System.out.println("Encoded string is not valid.");
                System.out.println();
                return;
            }
            int decimal = Integer.parseInt(s, 2);
            result.append((char) decimal);
        }
        System.out.println("Decoded string:");
        System.out.println(result);
        System.out.println();
    }
}