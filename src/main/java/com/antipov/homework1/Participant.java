package com.antipov.homework1;

public class Participant {
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    private final int strength;
    private final int dexterity;
    private final int endurance;
    private final int intellect;
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getEndurance() {
        return endurance;
    }
    public int getIntellect() {
        return intellect;
    }

    private boolean isPassing;
    public boolean isPassing() {
        return isPassing;
    }
    public void setPassing(boolean passing) {
        isPassing = passing;
    }

    public Participant(String name){
        this.name = name;
        strength = (int) (5 + Math.random() * 10);
        dexterity = (int) (5 + Math.random() * 10);
        endurance = (int) (5 + Math.random() * 10);
        intellect = (int) (5 + Math.random() * 10);
        isPassing = true;
    }
    public Participant(String name, int age){
        this(name);
        this.age = age;
    }
}
