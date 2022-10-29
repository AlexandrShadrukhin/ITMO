package mymoves;

import ru.ifmo.se.pokemon.*;

public class BugBuzz extends SpecialMove {
    public BugBuzz(){
        super(Type.BUG,90,100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 0.1)
            pokemon.setMod(Stat.SPECIAL_DEFENSE,-1);
    }

    @Override
    protected String describe(){
        return "использует Bug Buzz";
    }

}
