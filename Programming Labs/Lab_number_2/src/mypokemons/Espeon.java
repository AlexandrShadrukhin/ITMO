package mypokemons;

import mymoves.DreamEater;
import ru.ifmo.se.pokemon.*;

public class Espeon extends Eevee{
    public Espeon(String name, int level){
        super(name, level);
        setType(Type.PSYCHIC);
        setStats(65, 65, 60, 130, 95, 110);
        super.addMove(new DreamEater());
    }
}
