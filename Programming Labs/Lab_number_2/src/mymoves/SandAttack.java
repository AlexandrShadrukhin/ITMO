package mymoves;

import ru.ifmo.se.pokemon.*;

public class SandAttack extends StatusMove {
    public SandAttack(){
        super(Type.GROUND, 0, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.ACCURACY, -1);
    }

    @Override
    protected String describe() {
        return "использует SandAttack";
    }
}
