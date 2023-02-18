package ru.itmo.lab4;
import ru.itmo.lab4.furniture.*;
import ru.itmo.lab4.objects.*;
import ru.itmo.lab4.persons.*;

/* Текст задания
Малыш считал, что можно с тем же успехом играть в палатку в его комнате, оставив в покое Бетан, но Карлсон никак не соглашался. И вот палатка начала двигаться к
двери. Малыш шел вслед за Карлсоном. Из-под одеяла показалась маленькая пухлая ручка и тихонько отворила дверь. Палатка вышла в прихожую, отделенную от
столовой плотной занавесью. Палатка неслышно пересекла прихожую и остановилась у занавеси. Бормотание Бетан и Пелле слышалось теперь явственнее, но все же
слов нельзя было разобрать. Лампа в столовой не горела. Бетан и Пелле сумерничали -- видимо, им было достаточно света, который проникал через окно с улицы.
Тихо-тихо палатка раздвинула занавесь и вошла в столовую. Бетан и Пелле сидели на маленьком диванчике у противоположной стены. Тихо-тихо приближалась к ним
палатка.
 */
public class Main {
    public static void main(String[] args) {
        Door d = new Door();
        SmallBoy s = new SmallBoy("Малыш");
        Carlson c = new Carlson("Карлсон");
        //lab4
        Tent t = new Tent("Палатка", "Тихо-тихо ");
        Tent.QuietlyActing qa = t.new QuietlyActing(); // non-static class
        Person formCarlson = new Person("Карлсном") { // anonymous class
            @Override
            public String toString() {
                return getName();
            }
        };
        SittingByTheWall sbtw = new SittingByTheWall(" на маленьком ", "диванчике ", "у противоположной ", "стены. ");// static class
        //lab3
        Hand h = new Hand("ручка");
        Blanket b = new Blanket();
        Curtain cu = new Curtain("занавесью.");
        Bethan be = new Bethan("Бетан");
        Pellet pe = new Pellet("Пелле");
        Words w = new Words();
        Lamp l = new Lamp(false);
        StreetLight li = new StreetLight(true);

        //lab4
        s.thought();
        s.play(t);
        s.leave(be);
        c.choice();
        //lab3
        t.moveTo(d);
        s.followed(formCarlson);
        h.seem(b);
        h.opened(d);
        t.cameOut();
        cu.separated();
        t.crossed();
        cu.stopped();
        be.mumbleWasHeard();
        pe.mumbleWasHeard();
        w.takeApart(w);
        l.wasLighting();
        be.twilight();
        pe.twilight();
        li.wasLighting();
        //lab4
        qa.parted(cu);
        be.sat();
        pe.sat();
        sbtw.output();
        qa.goes(be, pe);
    }
}