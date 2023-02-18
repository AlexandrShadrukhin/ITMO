package ru.itmo.lab4.objects;

import ru.itmo.lab4.furniture.Door;

public class Hand {
    private final String name;
    public Hand(String name) {
        this.name = name;
    }

    public void seem(Blanket blanket){
        System.out.print(blanket.toString() + " показалась маленькая пухлая " + name);
    }
    public void opened(Door door) {
        System.out.print(" и тихонько отворила " + door.toString());
    }
}
