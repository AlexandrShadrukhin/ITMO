package ru.itmo.lab3.Persons;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
