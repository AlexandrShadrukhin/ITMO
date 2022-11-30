package ru.itmo.lab3.Furniture;

import ru.itmo.lab3.Enum.Place;
import ru.itmo.lab3.Interfaces.Light;

public class Lamp implements Light {
    private boolean light;

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
            System.out.print(toString() + " в " + Place.CANTEEN + " не светила.");
        }
    }

}