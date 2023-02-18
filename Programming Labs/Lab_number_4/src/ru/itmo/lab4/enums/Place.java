package ru.itmo.lab4.enums;

public enum Place {
    HALLWAY("прихожую"),
    CANTEEN("столовой"),
    OUTSIDE("улицы"),
    WINDOW("окно"),

    //lab4

    ROOM("комнате, ");
    private final String type;
    Place(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return type;
    }
}
