package linkedlists;

import java.util.Iterator;

public class DoublyLinkedList<T> {
    private int size = 0;
    private Node<T> head = new Node<>();
    private Node<T> tail = new Node<>();

    // D-LinkedList Utils
    public void clear(){}
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public void addLast(T key){
        if(isEmpty()){
        head.next = tail.previous = new Node<>(key, null, null);
        }
        else {
            Node<T> lastNode = tail.previous;
            Node<T> insertedNode = new Node<T>(key, lastNode, null);
            lastNode.next = insertedNode;
            tail.previous = insertedNode;
        }
        size++;
    }
    public void addFirst(T key){
        if(isEmpty()){
            head.next = tail.previous = new Node<>(key, null, null);
        }
        else {
            Node<T> firstNode = head.next;
            head.next = new Node<>(key, null, firstNode);
            firstNode.previous = head.next;
        }
        size++;
    }
    public void addAfter(T key, T insertedKey){}
    public void addBefore(T key, T insertedKey){}
    public T peekFirst(){return null;}
    public T peekLast(){return null;}
    public T removeFirst(){return null;}
    public T removeLast(){return null;}
    public T remove(Node<T> node){return null;}
    public int indexOf(Object obj){
        return -1;
    }
    public boolean contains(){return false;}

    // Node sub-class
    public static class Node<T>{
        private T key;
        private Node<T> previous, next;
        public Node(){}
        public Node(T key, Node<T> previous, Node<T> next) {
            this.key = key;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
    public java.util.Iterator<T> iterator(){
        return new Iterator<T>() {
            Node<T> node = head.next;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T key = node.key;
                node = node.next;
                return key;
            }
        };
    }

    @Override
    public String toString() {
        if(size() == 0){
            return "[]";
        }
        Iterator iterator = iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (iterator.hasNext()){
            sb.append(iterator.next() + ", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, "]");
        return sb.toString();
    }
}
