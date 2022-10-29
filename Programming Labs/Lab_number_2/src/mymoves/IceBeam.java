package mymoves;

import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove {
    public IceBeam() {
        super(Type.ICE, 0.9, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1){
            Effect.freeze(p);
        }
    }

    @Override
    protected String describe(){
        return "использует Ice Beam";
    }
}
