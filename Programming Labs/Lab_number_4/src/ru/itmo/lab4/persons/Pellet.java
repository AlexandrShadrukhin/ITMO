package ru.itmo.lab4.persons;

import ru.itmo.lab4.interfaces.BethanAndPellet;

public class Pellet extends Person implements BethanAndPellet {

    public Pellet(String name){
        super(name);
    }

    public void mumbleWasHeard(){
        System.out.print(",cлышалось бормотание " + getName());
    }

    public void twilight(){
        System.out.print(", cумерничала " + getName());
    }
    public void sat() {
        System.out.print(", cидела " + getName());
    }
}
