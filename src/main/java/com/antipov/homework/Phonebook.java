package com.antipov.homework;

import java.util.ArrayList;
import java.util.List;

public class Phonebook {
    private List<Person> persons = new ArrayList<>();

    /** Add last name and phone number into phonebook
     * @param lastName
     * @param phoneNumber
     */
    public void add(String lastName, String phoneNumber) {
        persons.add(new Person(lastName, phoneNumber));
        persons.sort(Person::compareTo);
    }

    public List<Person> getPersons(){
        return persons;
    }

    /** Get list of phone numbers of all persons with same last name
     * @param name - last name
     * @return
     */
    public ArrayList<String> getNumbersOf(String name){
        ArrayList<String> numbers = new ArrayList<>();
        boolean existEntry = false;
        for (Person person: persons) {
            if (person.getLastName().equals(name)){
                existEntry = true;
                numbers.add(person.getPhoneNumber());
            }
            else {
                if (existEntry) break; // т.к. лист отсортированный, то если уже нашли вхождение и далее
                // идут другие записи, то прерываем цикл
            }
        }

        return numbers;
    }
}
