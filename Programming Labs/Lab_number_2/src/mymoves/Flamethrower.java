package mymoves;

import ru.ifmo.se.pokemon.*;

public class Flamethrower extends SpecialMove {
    public Flamethrower(){
        super(Type.FIRE, 0.9, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect.burn(p);
        }
    }

    @Override
    protected String describe() {
        return "использует Flamethrower";
    }
}
