package Domain;

import java.util.*;

/**
 * Created by Stephen Adu on 14/09/2017.
 */
public class DynamicSortedSet<E extends Observable> implements SortedSet<E>, Observer {
    private SortedSet<E> internalSet;
    private Comparator<? super E>  comparator;

    public DynamicSortedSet() {
        super();
        this.internalSet = new TreeSet<>();
        this.comparator = null;
    }

    public DynamicSortedSet(Collection<? extends E> c) {
        this.internalSet = new TreeSet<>();
        this.internalSet.addAll(c);
        this.comparator = null;
    }

    public DynamicSortedSet(Comparator<? super E> comparator) {
        this.internalSet = new TreeSet<>(comparator);
        this.comparator = comparator;
    }

    public DynamicSortedSet(SortedSet<E> s) {
        this.internalSet = new TreeSet<>(s);
        this.comparator = s.comparator();
    }

    @Override
    public boolean add(E e) {
        e.addObserver(this);
        return internalSet.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object o: c) {
            Observable o2 = (Observable) o;
            o2.addObserver(this);
        }
        return internalSet.addAll(c);
    }

    @Override
    public void clear() {
        for (Observable o: internalSet) {
            o.deleteObserver(this);
        }
        internalSet.clear();
    }

    @Override
    public boolean contains(Object o) {
        return internalSet.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return internalSet.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return internalSet.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return internalSet.iterator();
    }

    @Override
    public boolean remove(Object o) {
        Observable o2 = (Observable) o;
        o2.deleteObserver(this);
        return internalSet.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o: c) {
            Observable o2 = (Observable) o;
            o2.deleteObserver(this);
        }
        return internalSet.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return internalSet.retainAll(c);
    }

    @Override
    public int size() {
        return internalSet.size();
    }

    @Override
    public Object[] toArray() {
        return internalSet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return internalSet.toArray(a);
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override
    public E first() {
        return internalSet.first();
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return internalSet.headSet(toElement);
    }

    @Override
    public E last() {
        return internalSet.last();
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return internalSet.subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return internalSet.tailSet(fromElement);
    }

    public void setComparator(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.update(null, null);
    }

    @Override
    public void update(Observable observable, Object argument) {
        SortedSet<E> tempSet = null;
        if (this.comparator != null) {
            tempSet = new TreeSet<>(this.comparator);
        }
        else {
            tempSet = new TreeSet<>();
        }
        tempSet.addAll(internalSet);
        internalSet = tempSet;
    }
}
