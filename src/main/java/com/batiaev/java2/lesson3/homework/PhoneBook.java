package com.batiaev.java2.lesson3.homework;

import java.util.ArrayList;
import java.util.Hashtable;

public class PhoneBook {

    Hashtable<String, ArrayList<String>> phoneMembers = new Hashtable<>();

    public static void main(String[] args) {

        String[] members = {"Петров", "Сидоров", "Иванов", "Петров", "Сидоров", "Иванов", "Петров", "Сидоров", "Петров", "Петров", "Иванов", "Сидоров", "Петров", "Иванов", "Сидоров", "Петров"};
        Hashtable<String, Integer> hashtableMembers = new Hashtable<>();
        for (int i = 0; i < members.length; i++) {
            if (hashtableMembers.containsKey(members[i])) {
                hashtableMembers.put(members[i], hashtableMembers.get(members[i]) + 1);
            } else {
                hashtableMembers.put(members[i], 1);
            }
        }

        for (String key :
                hashtableMembers.keySet()) {
            System.out.println(key + " - колличество " + hashtableMembers.get(key));
        }

        System.out.println();
        PhoneBook book = new PhoneBook();
        book.add("Иванов", "111-111");
        book.add("Петров", "222-222");
        book.add("Петров", "333-333");
        book.add("Иванов", "444-444");
        book.add("Иванов", "555-555");
        book.add("Сидоров", "666-666");
        book.add("Васечкин", "777-777");
        book.add("Пупкин", "888-888");
        book.add("Иванов", "999-999");
        book.get("Петров");
    }

    public void add(String fio, String phone) {
        if (phoneMembers.containsKey(fio)) {
            ArrayList<String> phones = phoneMembers.get(fio);
            phones.add(phone);
        } else {
            ArrayList<String> phones = new ArrayList<>();
            phoneMembers.put(fio, phones);
            phones.add(phone);
        }
    }

    public void get(String fio) {
        if (phoneMembers.containsKey(fio)) {
            System.out.println("По фамилии "+ fio+" найдены следующие телефоны:");
            for (String phone :
                    phoneMembers.get(fio)) {
                System.out.println(phone);
            }
        }
    }
}
