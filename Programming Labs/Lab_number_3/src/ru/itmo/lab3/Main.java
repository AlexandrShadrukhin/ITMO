package ru.itmo.lab3;
import ru.itmo.lab3.Furniture.*;
import ru.itmo.lab3.Objects.*;
import ru.itmo.lab3.Persons.*;

public class Main {
    public static void main(String[] args) {
        Tent t = new Tent("Палатка");
        Door d = new Door();
        SmallBoy s = new SmallBoy("Малыш");
        Carlson c = new Carlson("Карлсоном. ");
        Hand h = new Hand("ручка");
        Blanket b = new Blanket();
        Curtain cu = new Curtain("занавесью.");
        Bethan be = new Bethan("Бетан");
        Pellet p = new Pellet("Пелле");
        Words w = new Words();
        Lamp l = new Lamp(false);
        StreetLight li = new StreetLight(true);

        t.moveTo(d);
        s.followed(c);
        h.seem(b);
        h.opened(d);
        t.cameOut();
        cu.separated();
        t.crossed();
        cu.stopped();
        be.mumbleWasHeard();
        p.mumbleWasHeard();
        w.takeApart(w);
        l.wasLighting();
        be.twilight();
        p.twilight();
        li.wasLighting();
    }
}