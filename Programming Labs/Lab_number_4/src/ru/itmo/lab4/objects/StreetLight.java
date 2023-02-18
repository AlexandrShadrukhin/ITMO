package ru.itmo.lab4.objects;

import ru.itmo.lab4.enums.Place;
import ru.itmo.lab4.interfaces.Light;

public class StreetLight implements Light{
    private final boolean light;

    public StreetLight(boolean light) {
        this.light = light;
    }

    public boolean checkIfLight() {
        return light;
    }

    @Override
    public String toString() {
        return (", им было достаточно света, который");
    }
    @Override
    public void wasLighting() {
        if (checkIfLight()) {
            System.out.println(this + " светил через " + Place.WINDOW + " с " + Place.OUTSIDE + ".");
        }
    }
}
