package com.peanig.lesson3;

import java.util.ArrayList;
import java.util.HashMap;


public class Phonebook {
    HashMap<String, ArrayList<String>> persons = new HashMap<>();

    public void add(String person, String number) {
        persons.computeIfAbsent(person, k -> new ArrayList()).add(number);
    }

    public ArrayList<String> get(String person) {
        return persons.get(person);
    }
}
