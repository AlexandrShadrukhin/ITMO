package ru.itmo.lab3.Enum;

public enum Place {
    HALLWAY("прихожую"),
    CANTEEN("столовой"),
    OUTSIDE("улицы"),
    WINDOW("окно");

    private String type;
    Place(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return type;
    }
}
