package ru.itmo.lab4.objects;

import ru.itmo.lab4.enums.Place;
import ru.itmo.lab4.exceptions.CurtainException;

import java.util.Objects;

public class Curtain {
    private final String name;

    //lab4 unchecked exception
    public Curtain(String name) throws CurtainException {
        if(!Objects.equals(name, "занавесью.")) {
            throw new CurtainException("некорректное название предмета");
        }
        this.name = name;
    }
    public void separated(){
        System.out.print(", отделенную от\n" + Place.CANTEEN + " "+ name + ". ");
    }
    public void stopped(){
        System.out.print("остановилась перед " + name);
    }
    @Override
    public String toString(){
        return (" занавесь ");
    }
}
