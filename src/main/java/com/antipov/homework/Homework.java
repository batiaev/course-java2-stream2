package com.antipov.homework;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(), прописывая add() и get().
*/
public class Homework {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(20);
        fillList(words);
        showUnique(words);
        Phonebook phonebook = new Phonebook();
        fillPhonebook(phonebook);
        showPhonesOf(phonebook, "Иванов");
        showPhonesOf(phonebook, "Петров");
        showPhonesOf(phonebook, "Кузнецов");
        showPhonesOf(phonebook, "Невидимов");

    }

    // Вывел заполнение массивов в отдельные методы, чтобы не мешали в main'е
    private static void fillList(List<String> list) {
        list.add("создать");
        list.add("массив");
        list.add("набором");
        list.add("слов");
        list.add("среди");
        list.add("10-20");
        list.add("должны");
        list.add("массив");
        list.add("которых");
        list.add("и");
        list.add("слов");
        list.add("вывести");
        list.add("тестировать");
        list.add("однофамильцев");
        list.add("набором");
        list.add("массив");
        list.add("слов");
        list.add("пользователем");
        list.add("тестировать");
        list.add("слов");
    }

    private static void fillPhonebook(Phonebook book) {
        book.add("Иванов", "89162354684");
        book.add("Петров", "89272684681");
        book.add("Сидоров", "89162354719");
        book.add("Галкина", "89735648575");
        book.add("Романов", "89113524528");
        book.add("Распутин", "89345684687");
        book.add("Кузнецов", "89176844358");
        book.add("Пирогов", "+7495365281");
        book.add("Нарышкин", "89162987899");
        book.add("Поклонская", "7499858654");
        book.add("Каспаров", "8495123352");
        book.add("Королев", "79245366545");
        book.add("Петров", "79584563245");
        book.add("Степняков", "+79485225465");
        book.add("Сидоров", "89195468585");
        book.add("Петров", "+79348546525");
        book.add("Галкина", "+7495546846");
        book.add("Сидоров", "8499563125");
        book.add("петроВ", "8499654123");
        book.add("Иванов", "89654854554");
    }

    private static void showPhonesOf(Phonebook phonebook, String name) {
        List<String> phones = phonebook.getNumbersOf(name);
        if (phones.isEmpty()) System.out.println(name + " не найден в справочнике");
        else
            for (String number : phones) {
                System.out.println(name + ", тел.: " + number);
            }
        System.out.println();
    }

    /* Выбрал, на мой взгляд, самое простое решение задачи "влоб": сортируем список и за один проход списка
     * выводим не повторяющиеся слова и их кол-во в списке.
     *  Хотя, вероятно, есть более эффективное и менее ресурсоемкое решение.
     */
    private static void showUnique(List<String> list) {
        // Решил, что не хочу менять изначальный массив слов, поэтому делаю его копию
        List<String> sorted = new ArrayList<>(list);
        sorted.sort(String::compareTo);
        String currentWord = sorted.get(0);
        int    count       = 0;
        for (String word : sorted) {
            if (!currentWord.equals(word)) {
                System.out.println("Слово \"" + currentWord + "\" встречается " + count + " раз.");
                currentWord = word;
                count = 0;
            }
            count++;
        }
        System.out.println();
    }
}
