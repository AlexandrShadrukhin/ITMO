package mymoves;

import ru.ifmo.se.pokemon.*;

public class ThunderWave extends StatusMove {

    public ThunderWave(){
        super(Type.ELECTRIC, 0, 0.9);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.paralyze(p);
        if (Math.random() < 0.25) {
            p.addEffect(new Effect().attack(0).stat(Stat.SPEED, -1));
        }
    }

    @Override
    protected String describe() {
        return "использует ThunderWave";
    }

}
