package chucknorris;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        char[] array = input.toCharArray();
        StringBuilder binaryInput = new StringBuilder();

        System.out.println();
        System.out.println("The result:");
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
    }
}