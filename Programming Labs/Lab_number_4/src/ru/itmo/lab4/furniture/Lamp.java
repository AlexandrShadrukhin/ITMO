package ru.itmo.lab4.furniture;

import ru.itmo.lab4.enums.Place;
import ru.itmo.lab4.interfaces.Light;

public class Lamp implements Light {
    private final boolean light;

    public Lamp(boolean light) {

        this.light = light;
    }

    public boolean checkIfLight() {
        return light;
    }

    @Override
    public String toString() {
        return ("Лампа");
    }
    public void wasLighting() {
        if (!checkIfLight()) {
            System.out.print(this + " в " + Place.CANTEEN + " не светила.");
        }
    }

}