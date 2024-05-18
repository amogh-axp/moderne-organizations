package com.javaApp.javaAlgorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class PrintArrayListsameNumberPairsCount {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String normalString = "@123Helloworld@563";
        System.out.println("The reversed string is: " + normalString);


        List<Integer> list = Arrays.asList(4, 7, 3, 4, 9, 4, 3, 3, 3, 5, 5);
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);

            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                count++;
            }
        }
        System.out.println("The number of same number pairs is: " + count);
    }
}