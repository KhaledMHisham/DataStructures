package main.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<T> implements Stack<T>{
    LinkedList<T> list;
    public ListStack() {
        list = new LinkedList<>();
    }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T key) {
        list.addLast(key);
    }

    @Override
    public T pop() {
        if(isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }
    public int search(T key){
        return list.lastIndexOf(key);
    }
    public Iterator<T> iterator(){
        return list.iterator();
    }
}
