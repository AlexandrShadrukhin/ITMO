package ru.itmo.lab3.Persons;

import ru.itmo.lab3.Interfaces.BethanAndPellet;

public class Pellet extends Person implements BethanAndPellet {
    private String name;
    public Pellet(String name){
        super(name);
        this.name = name;
    }

    public void mumbleWasHeard(){
        System.out.print(",\ncлышалось бормотание " + name);
    }

    public void twilight(){
        System.out.print(", cумерничала " + name);
    }
}
