package mypokemons;

import mymoves.*;
import ru.ifmo.se.pokemon.*;

public class Rayquaza extends Pokemon {
    public Rayquaza(String name, int level){
        super(name, level);
        setType(Type.DRAGON, Type.FLYING);
        setStats( 105, 150, 90, 150, 90, 95);
        setMove(new ThunderWave(), new Facade(), new IceBeam(),new Flamethrower());
    }

}
