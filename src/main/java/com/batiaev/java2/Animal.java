package com.batiaev.java2;

import java.io.Serializable;
import java.util.List;

public abstract class Animal {
    protected long age;
    protected String name;

    abstract void makeSound();

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.makeSound();

        CanSwim dog = new Dog();
        doSwim(dog);

        List<CanSwim> data;
    }

    private static void doSwim(CanSwim item) {
        item.swim();
    }
}

interface CanSwim {
    void swim();
}

class Cat extends Animal {

    void makeSound() {
        System.out.println("meow");
    }
}

class Dog extends Animal implements CanSwim, Serializable, Cloneable {

    void makeSound() {
        System.out.println("gav");
    }

    public void swim() {
        this.doSwim();
    }

    public void doSwim() {
        System.out.println("");
    }
}
