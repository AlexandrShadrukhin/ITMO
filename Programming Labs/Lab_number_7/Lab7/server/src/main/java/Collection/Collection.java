package Collection;

import java.util.Vector;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Collection<E> {
    private Vector<E> vector;
    private ReadWriteLock lock;

    public Collection(Vector<E> vector) {
        this.vector = vector;
        this.lock = new ReentrantReadWriteLock();
    }

    public Vector<E> getVector() {
        lock.readLock().lock();
        try {
            return vector;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setVector(Vector<E> vector) {
        lock.writeLock().lock();
        try {
            this.vector = vector;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
