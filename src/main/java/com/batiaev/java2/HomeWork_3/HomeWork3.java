package com.batiaev.java2.HomeWork_3;

import java.util.*;

public class HomeWork3 {

    /**
     * Класс телефонной книги был найден в интернете, так что комментарии писал
     * в стиле "пока объяснял, сам понял", что бы разобраться
     * что, как и почему работает.*/
    public static void main(String[] args) {
        // Заполнение списков ArrayList, именами, номерами и набором слов
        // для тестирования.
        Arrays.fillArrays();

        //countWords();

        //getUniqueWords(Arrays.words);

        PhoneBook pb = new PhoneBook();
        toPhoneBook(pb);
        System.out.println(pb.get("Magdalen"));
        System.out.println(pb.get("Ida"));
        System.out.println(pb.get("Sherilyn"));
    }

    // Метод заполняет телефонную книгу именами и номерами из списков.
    private static void toPhoneBook(PhoneBook pb) {
        for (int i = 0; i < Arrays.names.size(); i++) {
            pb.add(Arrays.names.get(i), Arrays.phoneNrs.get(i));
        }
    }

    // Метод подсчитывающий сколько раз повторяется слово в списке.
    private static void countWords() {

        // В Map<String, Integer> wordCount сохраняются слова из words,
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : Arrays.words) {

            // если слово уже находится в Map,
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 0);
            }

            // увеличивается значение Integer - количество повторений этого
            // слова.
            wordCount.put(word, wordCount.get(word) + 1);
        }

        System.out.println("Array of words:");
        for (String word : wordCount.keySet()) {
            System.out.printf(
                    "Word %s found %d times.\n", word, wordCount.get(word)
            );
        }
    }

    // Метод выводит список уникальных слов.
    private static void getUniqueWords(ArrayList<String> words) {

        System.out.println("Unique words:");

        // Используется интерфэйс Set, который не допускает дублирования
        // элементов.
        Set<String> unique = new TreeSet<>(words);
        for (String n : unique) {
            System.out.printf("%s\n", n);
        }

        System.out.printf("Total: %d, out of: %d\n", unique.size(), words.size());
    }
}
