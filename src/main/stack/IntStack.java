package main.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class IntStack implements Stack<Integer>{

    private int size;
    private int capacity;
    private int[] array;

    public IntStack() {
        size = 0;
        capacity = 16;
        array = new int[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(Integer key) {
        if(size == capacity){
            array = Arrays.copyOf(array, capacity  << 1);
        }
        array[size++] = key;
    }

    @Override
    public Integer pop() {
        if(isEmpty()) throw new EmptyStackException();
        Integer key = array[--size];
        array[size] = 0;
        return key;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) throw new EmptyStackException();
        return array[size-1];
    }
}
