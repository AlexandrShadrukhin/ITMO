package ru.itmo.lab3.Objects;

import ru.itmo.lab3.Enum.Place;

public class Curtain {
    private String name;

    public Curtain(String name) {
        this.name = name;
    }
    public void separated(){
        System.out.print(", отделенную от " + Place.CANTEEN + " "+ name);
    }
    public void stopped(){
        System.out.print("остановилась перед " + name);
    }
}
