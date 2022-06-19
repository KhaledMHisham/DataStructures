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
    public void addAfter(T key, int index){
        Node<T> left_node = get(index);
        Node<T> right_node = left_node.next;
        Node<T> inserted_node = new Node<>(key, left_node, right_node);
        if(right_node != null){
            right_node.previous = inserted_node;
        } else tail.previous = inserted_node;

        left_node.next = inserted_node;
        size++;
    }
    public void addBefore(T key, int index){
        Node<T> right_node = get(index);
        Node<T> left_node = right_node.previous;
        Node<T> inserted_node = new Node<>(key, left_node, right_node);
        right_node.previous = inserted_node;
        if(left_node != null){
            left_node.next = inserted_node;
        } else head.next = inserted_node;
        size++;
    }
    public Node<T> get(int index){
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Actual Index Bounds are 0 and "
                    + ((size() == 0)? size() : (size() - 1))
                    + " , Input = " + index);

        Iterator iterator = iterator();
        Node<T> node = null;
        int i = 0;
        while(iterator.hasNext()){
            if(i == index){
                node = (Node<T>) iterator.next();
            }
            else iterator.next();
            ++i;
        }
        return node;
    }
    public T peekFirst(){
        if(isEmpty()){
            throw new RuntimeException("Empty List");
        }
        return head.next.key;
    }
    public T peekLast(){
        if(isEmpty())
            throw new RuntimeException("Empty List");
        return tail.previous.key;
    }
    public T removeFirst(){
        if(isEmpty())
            throw new RuntimeException("Empty List");
        T key;
        if(head.next == tail.previous){
            key = head.next.key;
            head.next = null;
            tail.previous = null;
        } else{
            Node<T> first_node = head.next;
            key = first_node.key();
            Node<T> new_first_node = first_node.next;
            new_first_node.previous = null;
            head.next = new_first_node;
        }
        size--;
        return key;
    }
    public T removeLast(){
        if(isEmpty())
            throw new RuntimeException("Empty List");
        T key;
        if(head.next == tail.previous){
            key = tail.previous.key;
            head.next = null;
            tail.previous = null;
        }
        else{
            Node<T> last_node = tail.previous;
            key = last_node.key;
            Node<T> new_last_node = last_node.previous;
            new_last_node.next = null;
            tail.previous = new_last_node;
        }
        size--;
        return key;
    }
    public T remove(Node<T> node){
        if(isEmpty()) throw new RuntimeException("Empty List");
        T key;
        if(head.next == tail.previous && node == head.next){
            key = tail.previous.key;
            head.next = null;
            tail.previous = null;
        }
        if (node.previous == null && node == head.next){
            key = removeFirst();
        }
        else if(node.next == null && node == tail.previous){
            key = removeLast();
        }
        else {
            key = node.key;
            Node<T> right_node = node.next;
            Node<T> left_node = node.previous;
            left_node.next = right_node;
            right_node.previous = left_node;
            // memory clean up.
            node.key = null; node.next = null; node.previous = null;
        }
        size--;
        return key;
    }
    public boolean remove(T key){
        Iterator iterator = iterator();
        Node<T> result = null;
        int i = 0;
        while (iterator.hasNext()){
            result = (Node<T>) iterator.next();
            if(key == null && result.key() == null){
                remove(result);
                return true;
            }
            if(key != null && key.equals(result.key())){
                remove(result);
                return true;
            }
            ++i;
        }
        return false;
    }
    public T removeAt(int index){
        return remove(get(index));
    }
    public int indexOf(T key){
        Iterator iterator = iterator();
        Node<T> result = null;
        int i = 0;
        while (iterator.hasNext()){
            result = (Node<T>) iterator.next();
            if(key == null && result.key() == null){
                return i;
            }
            if(key != null && key.equals(result.key())){
                return i;
            }
            ++i;
        }
        return -1;
    }
    public boolean contains(T key){return indexOf(key) != -1;}
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
        public T key(){
            return key;
        }
        @Override
        public String toString() {
            if(key == null){
                return null;
            }
            return key.toString();
        }
    }
    public java.util.Iterator<Node<T>> iterator(){
        return new Iterator<Node<T>>() {
            Node<T> node = head.next;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Node<T> next() {
                Node<T> key = node;
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