package main.stack;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
    private T[] array;
    private int size;
    private int capacity;
    public ArrayStack(){
        capacity = 16;
        size = 0;
        array = (T[]) new Object[capacity];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    @Override
    public void push(T key) {
        if(size == capacity){
            array = Arrays.copyOf(array, capacity << 1);
        }
        array[size++] = key;
    }

    @Override
    public T pop() {
        if(isEmpty()) throw new EmptyStackException();
        T key = array[--size];
        array[size] = null;
        return key;
    }

    @Override
    public T peek() {
        if(isEmpty()) throw new EmptyStackException();
        return array[size - 1];
    }
}
