package mymoves;

import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove{
    public ShadowBall() {
        super(Type.GHOST, 0.8, 1);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        if (Math.random() < 0.2) {
            p.setMod(Stat.DEFENSE, -1);
            super.applySelfEffects(p);
        }
    }

    @Override
    protected String describe() {
        return "использует Shadow Ball";
    }

}
