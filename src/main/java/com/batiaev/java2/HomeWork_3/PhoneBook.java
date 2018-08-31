package com.batiaev.java2.HomeWork_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

    // Map для сохранения имен и номеров.
    private Map<String, Set<String>> book;

    PhoneBook() {
        book = new HashMap<>();
    }

    // Метод добавляет запись в книгу
    public void add(String name, String phone) {
        Set<String> numbers;

        //Если в Map<String, Set<String>> book уже есть данное имя,
        // которое здесь в роли ключа,
        if (book.containsKey(name)) {
            // то в Set<String> numbers присваивается ссылка на Set,
            // который уже находится в значении для данного имени(ключа).
            numbers = book.get(name);
        } else {
            // Если имени в Map<> нет, создается новый HasSet<>(),
            numbers = new HashSet<>();
        }
        // в который добавляется заданный номер,
        numbers.add(phone);
        // а имя(ключ) и сам HashSet<>()(значение)
        // с номером передаются в Map<...> book.
        book.put(name, numbers);

    }

    // Метод возвращает Set<String> с номерами соответствующими заданному имени.
    public Set<String> get(String lastName) {
        return book.get(lastName);
    }
}
