package ru.itmo.lab3.Persons;

import ru.itmo.lab3.Interfaces.BethanAndPellet;

public class Bethan extends Person implements BethanAndPellet {
    private String name;
    public Bethan(String name){
        super(name);
        this.name = name;
    }

    public void mumbleWasHeard(){
        System.out.print(" Слышалось бормотание " + name);
    }

    public void twilight(){
        System.out.print(" Сумерничала " + name);
    }

}
