package com.antipov.homework;

public class Person implements Comparable<Person>{
    private String lastName;
    private String phoneNumber;

    public Person(String lastName, String phoneNumber) {
        this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Person other) {
        return this.lastName.compareTo(other.getLastName());
    }



}
