package ru.itmo.lab3.Persons;

import java.util.Objects;

public class SmallBoy extends Person {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallBoy smallBoy = (SmallBoy) o;
        return Objects.equals(name, smallBoy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public SmallBoy(String name){
        super(name);
        this.name = name;
    }
    public void followed(Person c){
        System.out.print(name + " следует за " + c.toString());
    }
}