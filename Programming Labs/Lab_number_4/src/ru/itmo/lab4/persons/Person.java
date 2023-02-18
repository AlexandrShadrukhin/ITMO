package ru.itmo.lab4.persons;

import java.util.Objects;

public abstract class Person {
    private final String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName(){
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
