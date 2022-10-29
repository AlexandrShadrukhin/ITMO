package mymoves;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {

    public Facade() {
        super(Type.NORMAL, 0.7, 1);
    }

    @Override
    protected void applyOppDamage(Pokemon defender, double damage) {
        Status defenderStatus = defender.getCondition();
        if (defenderStatus.equals(Status.BURN) || defenderStatus.equals(Status.POISON) || defenderStatus.equals(Status.PARALYZE)) {
            super.applyOppDamage(defender, damage*2);
        }
        else {
            super.applyOppDamage(defender, damage);
        }
    }

    @Override
    protected String describe() {
        return "использует Facade";
    }
}