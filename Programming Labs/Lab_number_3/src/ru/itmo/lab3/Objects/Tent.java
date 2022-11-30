package ru.itmo.lab3.Objects;

import ru.itmo.lab3.Enum.Place;
import ru.itmo.lab3.Furniture.Door;
import ru.itmo.lab3.Persons.Person;


public class Tent extends Person {
    private String name;
    public Tent(String name) {
        super(name);
        this.name = name;
    }

    public void moveTo(Door door){
        System.out.print(name + " начала двигаться к " + door.toString());

    }
    public void cameOut(){
        System.out.print(name + "\nвышла в "  + Place.HALLWAY);
    }
    public void crossed(){
        System.out.print(name + " пересекла " + Place.HALLWAY + " и ");
    }

}
