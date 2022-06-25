package main.stack;

public interface Stack<T> {
    public int size();
    public boolean isEmpty();
    public void push(T key);
    public T pop();
    public T peek();
}
