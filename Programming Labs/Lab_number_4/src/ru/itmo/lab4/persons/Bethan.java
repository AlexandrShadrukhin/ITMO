package ru.itmo.lab4.persons;

import ru.itmo.lab4.interfaces.BethanAndPellet;

public class Bethan extends Person implements BethanAndPellet {

    public Bethan(String name){
        super(name);
    }

    public void mumbleWasHeard(){
        System.out.print(" Слышалось бормотание " + getName());
    }

    public void twilight(){
        System.out.print(" Сумерничала " + getName());
    }
    public void sat(){
        System.out.print("Сидела " + getName());
    }

}
