package mymoves;

import ru.ifmo.se.pokemon.*;

public class FeintAttack extends PhysicalMove{
    public FeintAttack() {
        super(Type.DARK, 0.6, Double.POSITIVE_INFINITY);
    }

    @Override
    protected void applySelfDamage(Pokemon p, double damage) {
        super.applySelfDamage(p, damage);
    }

    @Override
    protected String describe() {
        return "использует FeintAttack";
    }
}
