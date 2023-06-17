package Collection;

import java.util.ArrayDeque;
import java.util.Vector;

public class Collection<E> {
    private Vector<E> vector;

    public Collection(Vector<E> vector) {
        this.vector = vector;
    }

    public Vector<E> getVector() {
        return vector;
    }

    public void setVector(Vector<E> vector) {
        this.vector = vector;
    }
}
