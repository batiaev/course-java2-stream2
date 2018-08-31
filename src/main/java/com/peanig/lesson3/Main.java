package com.peanig.lesson3;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // 1 task
        String[] words = {"123", "123", "321", "321", "321", "111", "222"};
        getUniqueWords(words);

        // 2 task
        Phonebook myAwesomePhonebook = new Phonebook();

        myAwesomePhonebook.add("Петров", "89876543211");
        myAwesomePhonebook.add("Петров", "89876543212");
        myAwesomePhonebook.add("Иванов", "89876543213");
        myAwesomePhonebook.add("Иванов", "89876543214");
        myAwesomePhonebook.add("Сидоров", "89876543215");

        System.out.println("Numbers: ");
        System.out.println(myAwesomePhonebook.get("Петров"));
    }


    static void getUniqueWords(String[] words) {
        HashMap<String, Integer> uniqueWords = new HashMap<>();

        for (String word : words) {
            uniqueWords.put(word, uniqueWords.computeIfAbsent(word, k -> 0)+1);
        }

        System.out.println("Unique words:");
        uniqueWords.forEach( (k,v)->{
            if (v == 1) System.out.println(k);
        });

        System.out.println("Count words in array:");
        uniqueWords.forEach( (k,v)->{
            System.out.println(k+"-"+v);
        });
    }
}
