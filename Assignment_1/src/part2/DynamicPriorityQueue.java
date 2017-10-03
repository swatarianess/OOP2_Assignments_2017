package part2;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Queue;

/**
 * Created by Stephen Adu on 03/10/2017.
 */
public class DynamicPriorityQueue<E extends Observable> implements Queue<E> {
    private DynamicSortedSet<E> internalSet;

    public DynamicPriorityQueue() {
        this.internalSet = new DynamicSortedSet<E>();
    }

    public DynamicPriorityQueue(Collection<? extends E> c) {
        this.internalSet = new DynamicSortedSet<E>();
        this.internalSet.addAll(c);
    }

    public DynamicPriorityQueue(Comparator<? super E> comparator) {
        this.internalSet = new DynamicSortedSet<E>(comparator);
    }

    public DynamicPriorityQueue(DynamicPriorityQueue<? extends E> c) {
        this.internalSet = new DynamicSortedSet<E>(c.internalSet);
    }

    public DynamicPriorityQueue(DynamicSortedSet<? extends E> c) {
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
        }
        else {
            return result;
        }
    }

    @Override
    public boolean offer(E e) {
        return this.internalSet.add(e);
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
        }
        else {
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
        }
        else {
            return result;
        }
    }

    public void setComparator(Comparator<? super E> comparator) {
        internalSet.setComparator(comparator);
    }
}
