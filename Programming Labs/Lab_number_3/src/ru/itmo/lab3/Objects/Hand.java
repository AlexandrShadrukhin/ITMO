package ru.itmo.lab3.Objects;

import ru.itmo.lab3.Furniture.Door;

public class Hand {
    private String name;
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
