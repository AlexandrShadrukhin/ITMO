import mypokemons.*;
import ru.ifmo.se.pokemon.*;

public class SecondLab {
    public static void main(String[] args) {
        Battle b = new Battle();
        Rayquaza p1 = new Rayquaza("Всевышний", 1);
        Eevee p2 = new Eevee("Джина Грей", 1);
        Espeon p3 = new Espeon("Танос", 1);
        Trapinch p4 = new Trapinch("Доктор Стрэндж", 1);
        Vibrava p5 = new Vibrava("Дэдпул", 1);
        Flygon p6 = new Flygon("Тор", 1);
        b.addAlly(p1);
        b.addAlly(p3);
        b.addAlly(p5);
        b.addFoe(p2);
        b.addFoe(p4);
        b.addFoe(p6);
        b.go();

    }

}