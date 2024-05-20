---
title: swimm
---
# Introduction

This document will walk you through the implementation of two main features in our Java application: counting the number of pairs of the same number in an array list and reversing a string without impacting special characters.

We will cover:

1. Why we chose to use a HashMap for counting pairs of the same number in an array list.


2. How we implemented the string reversal without impacting special characters.


3. How these features fit into the larger Java application.

# Counting pairs of the same number in an array list

<SwmSnippet path="/src/main/java/io/moderne/organizations/PrintArrayListsameNumberPairsCount.java" line="1">

---

The first feature we'll discuss is the counting of pairs of the same number in an array list. This feature is implemented in the PrintArrayListsameNumberPairsCount class.

```java
package com.javaApp.javaAlgorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/PrintArrayListsameNumberPairsCount.java" line="8">

---

&nbsp;

```java

public class PrintArrayListsameNumberPairsCount {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String normalString = "@123Helloworld@563";
        System.out.println("The reversed string is: " + normalString);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/PrintArrayListsameNumberPairsCount.java" line="15">

---

We chose to use a HashMap to count the pairs because it allows us to store each number from the list as a key and its count as the value. This makes it easy to iterate over the list once and count the occurrences of each number.

```java


        List<Integer> list = Arrays.asList(4, 7, 3, 4, 9, 4, 3, 3, 3, 5, 5);
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/PrintArrayListsameNumberPairsCount.java" line="22">

---

After populating the HashMap, we then iterate over its entries. If the value of an entry (which represents the count of a number) is greater than or equal to 2, we increment a counter. This counter represents the number of pairs of the same number in the list.

```java

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
```

---

</SwmSnippet>

# Reversing a string without impacting special characters

<SwmSnippet path="/src/main/java/io/moderne/organizations/reverseStringWithoutImpacttSpecialChar.java" line="1">

---

The second feature we'll discuss is the reversal of a string without impacting special characters. This feature is implemented in the reverseStringWithoutImpacttSpecialChar class.

```java
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
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/io/moderne/organizations/reverseStringWithoutImpacttSpecialChar.java" line="22">

---

We chose to implement this feature by iterating over the string from both ends towards the center. If a character is not alphabetic, we skip it and move to the next character. If a character is alphabetic, we swap it with the corresponding character from the other end of the string. This ensures that the special characters remain in their original positions while the alphabetic characters are reversed.

```java

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
```

---

</SwmSnippet>

# Integration into the larger application

<SwmSnippet path="/src/main/java/io/moderne/organizations/JavaAlgorithmsApplication.java" line="1">

---

Both of these features are part of a larger Java application, which is launched from the JavaAlgorithmsApplication class.

```java
package com.javaApp.javaAlgorithms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaAlgorithmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAlgorithmsApplication.class, args);
	}

}
```

---

</SwmSnippet>

This class uses the Spring Boot framework to run the application. The features we discussed are just a part of the larger set of algorithms and functionalities provided by the application.

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBbW9kZXJuZS1vcmdhbml6YXRpb25zJTNBJTNBYW1vZ2gtYXhw"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
