package main.queues;

public class ArrayQueue<T> implements Queue<T> {

    T[] array;
    int read, write, capacity;
    public ArrayQueue(){
        read = 0; write = 0;
        capacity = 16;
        array = (T[]) new Object[capacity];
    }
    @Override
    public void offer(T key) {
        if(isFull()){
            array = resizeArray();
        }
        array[write] = key;
        write = write == array.length - 1? 0 : write + 1;
    }
    private T[] resizeArray(){
        if(capacity == Integer.MAX_VALUE){
            throw new RuntimeException("Maximum Size Queue Reached, cant add more values.");
        }
        capacity = (capacity << 1) < capacity ? Integer.MAX_VALUE : capacity << 1;
        T[] new_array = (T[]) new Object[capacity];
        int j = 0;
        if(write >= read){
            for(int i = read ; i < write ; ++i){
                new_array[j++] = array[i];
            }
        }
        else{
            for(int i = read; i < array.length; ++i){
                new_array[j++] = array[i];
            }
            for(int i = 0; i < write; ++i){
                new_array[j++] = array[i];
            }
        }
        read = 0; write = j;
        return new_array;
    }
    private boolean isFull() {
        return (read + array.length - write) % array.length == 1;
    }

    @Override
    public T poll() {
        if(isEmpty()) throw new RuntimeException("Empty Queue.");
        T key = array[read];
        array[read] = null;
        read = read == array.length - 1? 0 : read + 1;
        return key;
    }

    @Override
    public T peek() {
        if(isEmpty()) throw new RuntimeException("Empty Queue.");
        return array[read];
    }

    @Override
    public boolean isEmpty() {
        return read == write;
    }

    @Override
    public int size() {
        return write >= read? write - read : write + array.length - read;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Key Array : [");
        for(int i = 0 ; i < array.length - 1; ++i){
            sb.append(array[i] + ", ");
        }
        sb.append(array[array.length - 1]+"]");
        sb.append(
                "\n read : " + read
                +"\n write : " + write
                +"\n capacity : " + capacity);
        return sb.toString();
    }
}
