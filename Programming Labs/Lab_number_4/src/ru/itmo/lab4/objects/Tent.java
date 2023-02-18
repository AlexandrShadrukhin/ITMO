package ru.itmo.lab4.objects;

import ru.itmo.lab4.enums.Place;
import ru.itmo.lab4.furniture.Door;
import ru.itmo.lab4.persons.Bethan;
import ru.itmo.lab4.persons.Pellet;
import ru.itmo.lab4.persons.Person;



public class Tent extends Person {
    private final String sound;
    public Tent(String name, String sound) {
        super(name);
        this.sound = sound;
    }

    public void moveTo(Door door){
        System.out.print(getName() + " начала двигаться к\n" + door.toString());

    }
    public void cameOut(){
        System.out.print(getName() + "вышла в "  + Place.HALLWAY);
    }
    public void crossed(){
        System.out.print(getName() + " пересекла " + Place.HALLWAY + " и ");
    }

    public class QuietlyActing{
        public void parted(Curtain cu){
            System.out.print(Tent.this.sound + getName() + " раздвинула " + cu.toString() + " вошла в " + Place.CANTEEN + ". ");
        }
        public void goes(Bethan be, Pellet pe){
            System.out.print(Tent.this.sound + getName() + " приблежалась к " + be.toString() + " и " + pe.toString() + ".");
        }
    }
}
