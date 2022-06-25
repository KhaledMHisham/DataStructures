package main.queues;

public interface Queue<T> {
    public void offer(T key);
    public T poll();
    public T peek();
    public boolean isEmpty();
    public int size();
}
