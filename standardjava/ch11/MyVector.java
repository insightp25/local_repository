package ch11;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyVector implements List {
    Object[] data = null;
    int capacity = 0;
    int size = 0;

    public MyVector(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("invalid value : " + capacity);
        }
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public MyVector() {
        this(10);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity - data.length > 0)
            setCapacity(minCapacity);
    }

    public boolean add(Object obj) {
        ensureCapacity(size + 1);
        data[size++] = obj;
        return true;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of range.");
        }
        return data[index];
    }

    public Object remove(int index) {
        Object oldObj = null;

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of range.");
        oldObj = data[index];

        if (index != size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[size - 1] = null;
        size--;
        return oldObj;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void trimToSize() {
        setCapacity(size);
    }

    private void setCapacity(int capacity) {
        if (this.capacity == capacity) return;

        Object[] tmp = new Object[capacity];
        System.arraycopy(data, 0, tmp, 0, size);
        data = tmp;
        this.capacity = capacity;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(data, 0, result, 0, size);

        return result;
    }

    public boolean isEmpty() { return size == 0; }
    public int capacity() { return capacity; }
    public int size() { return size; }
    /****************************************/
    /*    List인터페이스로부터 상속받은 메서드들      */
    /****************************************/
//  public int size();
//  public boolean isEmpty();
    public boolean contains(Object o){ return false;}
    public Iterator iterator(){ return null; }
    //	public Object[] toArray();
    public Object[] toArray(Object a[]){ return null;}
    //  public boolean add(Object o);
//  public boolean remove(Object o);
    public boolean containsAll(Collection c){ return false; }
    public boolean addAll(Collection c){ return false; }
    public boolean addAll(int index, Collection c){ return false; }
    public boolean removeAll(Collection c){ return false; }
    public boolean retainAll(Collection c){ return false; }
    //	public void clear();
    public boolean equals(Object o){ return false; }
    //	public int hashCode();
//  public Object get(int index);
    public Object set(int index, Object element){ return null;}
    public void add(int index, Object element){}
    //  public Object remove(int index);
    public int indexOf(Object o){ return -1;}
    public int lastIndexOf(Object o){ return -1;}
    public ListIterator listIterator(){ return null; }
    public ListIterator listIterator(int index){ return null; }
    public List subList(int fromIndex, int toIndex){ return null; }

//    default void sort(Comparator c) { /* 내용생략 */ }                     // JDK1.8부터
//    default Spliterator spliterator() { /* 내용생략 */ }                  // JDK1.8부터
//    default void replaceAll(UnaryOperator operator){/* 내용생략 */} //JDK1.8부터
}
