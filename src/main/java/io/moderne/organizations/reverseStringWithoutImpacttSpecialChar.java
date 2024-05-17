package com.javaApp.javaAlgorithms;

public class reverseStringWithoutImpacttSpecialChar {

    public static void reverse(char str[]) {

        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            if (!Character.isAlphabetic(str[i])) {
                i++;
            } else if (!Character.isAlphabetic(str[j])) {
                j--;
            } else {
                char temp = str[i];
                str[i] = str[j];
                str[j] = temp;
                i++;
                j--;
            }
        }

    }
    // Diver Code
    public static void main(String[] args) {
        String str = "Ab,cnbfd,deuytf!$";
        char[] charArray = str.toCharArray();
        System.out.println("input string    " + str);
        reverse(charArray);
        String rev = new String(charArray);
        System.out.println("input string   " + rev);
    }
}

