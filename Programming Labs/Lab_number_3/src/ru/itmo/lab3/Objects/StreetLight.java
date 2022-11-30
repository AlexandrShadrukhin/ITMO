package ru.itmo.lab3.Objects;

import ru.itmo.lab3.Enum.Place;
import ru.itmo.lab3.Interfaces.Light;

public class StreetLight implements Light{
    private boolean light;

    public StreetLight(boolean light) {
        this.light = light;
    }

    public boolean checkIfLight() {
        return light;
    }

    @Override
    public String toString() {
        return (", им было достаточно света, \nкоторый");
    }
    @Override
    public void wasLighting() {
        if (checkIfLight()) {
            System.out.print(toString() + " светил через " + Place.WINDOW + " с " + Place.OUTSIDE + ".");
        }
    }
}
