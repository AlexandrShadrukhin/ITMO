package mymoves;

import ru.ifmo.se.pokemon.*;

public class Swift extends SpecialMove {
    public Swift(){
        super(Type.NORMAL, 0.6, Double.POSITIVE_INFINITY);
    }

    @Override
    protected String describe() {
        return "использует Swift";
    }
}
