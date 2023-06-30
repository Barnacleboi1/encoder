package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine ();
        char[] array = input.toCharArray();
        System.out.println();
        System.out.println("The result:");
        for (char c : array) {
            System.out.printf("%c = %07d\n",c, Integer.parseInt(Integer.toBinaryString(c)));
        }
    }
}