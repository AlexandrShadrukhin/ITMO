package mypokemons;

import mymoves.*;
import ru.ifmo.se.pokemon.*;

public class Vibrava extends Trapinch {
    public Vibrava(String name,int level) {
        super(name, level);
        setStats(50, 70, 50, 50, 50, 70);
        setType(Type.GROUND, Type.DRAGON);
        super.addMove(new BugBuzz());
    }
}
