package main.dynamicarrays;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable{
    private T[] array;
    private int len;
    private int capacity;
    public DynamicArray(){
        this(16);
    }
    public DynamicArray(int capacity){
        if(capacity < 0)
            throw new IllegalArgumentException("Array Capacity: " + capacity);
        array = (T[]) new Object[capacity];
        this.len = 0;
        this.capacity = capacity;
    }
    public int size(){
        return len;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public T get(int index){
        return array[index];
    }
    public void set(int index, T key){
        array[index] = key;
    }
    public void clear(){
        for(int i = 0 ; i < len ; ++i){
            array[i] = null;
        }
        len = 0;
    }
    public void add(T key){
        if(len + 1 >= capacity){
            if (capacity == 0)
                capacity = 1;
            else
                capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for(int i = 0 ; i < len ; ++i){
                new_arr[i] = array[i];
            }
            array = new_arr;
        }
        array[len++] = key;
    }
    public void remove(){
        if(len > 0 && capacity > 0){
            array[--len] = null;
        }
    }
    public T removeAt(int index){
        if(index < 0 && index > len -1) throw new IndexOutOfBoundsException("Array Bounds are 0 and " + (len - 1) + " Index " + index + " is passed.");
        T key = array[index];
        for(int i = index ; i < len - 1 ; ++i){
            array[i] = array[i+1];
        }
        array[len-- - 1] = null;
        return key;
    }
    public boolean remove(T key){
        int index = indexOf(key);
        if(index == -1){
            return false;
        }
        removeAt(index);
        return true;
    }
    public int indexOf(T key){
        for(int i = 0 ; i < len ; ++i){
            if(key == null && array[i] == null){
                return i;
            }
            if(key.equals(array[i])){
                return i;
            }
        }
        return -1;
    }
    public boolean contains(T key){
        return indexOf(key) != -1;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                T key = array[index++];
                return key;
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        StringBuilder sb = new StringBuilder(len).append("[");
        int i = 0;
        Iterator iterator = iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next() + ", ");
        }
        sb.setCharAt(sb.length()-2,']');
        return sb.toString();
    }
}
