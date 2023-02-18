package ru.itmo.lab4.persons;

import ru.itmo.lab4.enums.Place;

public class SmallBoy extends Person{

    public SmallBoy(String name){
        super(name);
    }
    public void followed(Person c){
        System.out.print(getName() + " следует за " + c.toString() + ". ");
    }
    //lab4
    public void thought(){
        System.out.print(getName() + " считал, ");
    }
    public void play(Person t){
        System.out.print("что можно с тем же успехом играть в " + t.toString() + " в его " + Place.ROOM);
    }
    public void leave(Person be){
        System.out.print("оствавив в покое " + be.toString() + ", ");
    }
}