package Domain;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stephen Adu on 03/10/2017.
 */
public class DynamicBlockingQueue<E extends Observable> implements BlockingQueue<E> {
    private DynamicSortedSet<E> internalSet;
    private int capacity;
    private AtomicInteger counter;
    private Semaphore lock;

    public DynamicBlockingQueue(int capacity) {
        this.internalSet = new DynamicSortedSet<>();
        this.capacity = capacity;
        this.lock = new Semaphore(capacity);
    }

    public DynamicBlockingQueue(Collection<? extends E> c) {
        this.internalSet = new DynamicSortedSet<>();
        this.internalSet.addAll(c);
    }

    public DynamicBlockingQueue(Comparator<? super E> comparator) {
        this.internalSet = new DynamicSortedSet<>(comparator);
    }

    public DynamicBlockingQueue(DynamicBlockingQueue<? extends E> c) {
        this.internalSet = new DynamicSortedSet<E>(c.internalSet);
    }

    public DynamicBlockingQueue(DynamicSortedSet<? extends E> c) {
        this.internalSet = new DynamicSortedSet<E>(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return this.internalSet.addAll(c);
    }

    @Override
    public void clear() {
        this.internalSet.clear();
    }

    @Override
    public boolean contains(Object o) {
        return this.internalSet.contains(o);
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        AtomicInteger counter = new AtomicInteger(0);
        internalSet.forEach(e -> {
            try {
                lock.tryAcquire();
                c.add(e);
                counter.incrementAndGet();
            } finally {
                lock.release();
            }
        });
        return counter.get();
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        AtomicInteger counter = new AtomicInteger(0);
        for (E e : internalSet){
            try {
                lock.tryAcquire();
                c.add(e);
            }finally {
                lock.release();
            }
        }
        return counter.get();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.internalSet.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return this.internalSet.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.internalSet.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return this.internalSet.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.internalSet.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.internalSet.retainAll(c);
    }

    @Override
    public int size() {
        return this.internalSet.size();
    }

    @Override
    public Object[] toArray() {
        return this.internalSet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.internalSet.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return this.internalSet.add(e);
    }

    @Override
    public E element() {
        E result = this.peek();
        if (result == null) {
            throw new NoSuchElementException();
        } else {
            return result;
        }
    }

    @Override
    public boolean offer(E e) {
        try {
            lock.tryAcquire();
            internalSet.add(e);
        } finally {
            lock.release();
        }
        return internalSet.contains(e);    }

    @Override
    public void put(E e) throws InterruptedException {
            try{
                lock.acquire();
                internalSet.add(e);
            }finally {
                lock.release();
            }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        try {
            lock.tryAcquire(timeout, unit);
            internalSet.add(e);
        } finally {
            lock.release();
        }
        return internalSet.contains(e);
    }

    @Override
    public E take() throws InterruptedException {
        try {
            lock.acquire();
            return this.internalSet.first();
        } finally {
            lock.release();
        }
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        try {
            lock.tryAcquire(timeout, unit);
            return peek();
        } finally {
            lock.release();
        }
    }

    @Override
    public int remainingCapacity() {
        return capacity;
    }

    @Override
    public E peek() {
        return this.internalSet.first();
    }

    @Override
    public E poll() {
        E element = null;
        if (this.internalSet.size() == 0) {
            return null;
        } else {
            element = this.internalSet.first();
            internalSet.remove(element);
            return element;
        }
    }

    @Override
    public E remove() {
        E result = this.poll();
        if (result == null) {
            throw new NoSuchElementException();
        } else {
            return result;
        }
    }

    public void setComparator(Comparator<? super E> comparator) {
        internalSet.setComparator(comparator);
    }
}
