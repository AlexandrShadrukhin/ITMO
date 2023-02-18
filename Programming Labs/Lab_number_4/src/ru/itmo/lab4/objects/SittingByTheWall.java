package ru.itmo.lab4.objects;

import ru.itmo.lab4.interfaces.PlaceDescription;

public class SittingByTheWall implements PlaceDescription {
    private static String dimensions;
    private static String sofa;
    private static String lacation;
    private static String wall;
    public SittingByTheWall(String d, String s, String l, String w) {
            dimensions = d;
            sofa = s;
            lacation = l;
            wall = w;
        }
    public static class Sat{
        public static String infoDimensions(){
            return dimensions;
        }
        public static String infoSofa(){
            return sofa;
        }
        public static String infoLocation(){
            return lacation;
        }
        public static String infoWall(){
            return wall;
        }
    }
    @Override
    public String description(){
        return Sat.infoDimensions() + Sat.infoSofa() + Sat.infoLocation() + Sat.infoWall();
    }
    public void output(){
        System.out.print(description());
    }
}
