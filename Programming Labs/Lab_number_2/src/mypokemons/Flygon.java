package mypokemons;

import mymoves.RockTomb;
import ru.ifmo.se.pokemon.*;

public class Flygon extends Vibrava{
    public Flygon(String name, int level){
        super(name, level);
        setStats(80,100,80,80,80,100);
        setType(Type.GROUND,Type.DRAGON);
        super.addMove(new RockTomb());
    }
}
