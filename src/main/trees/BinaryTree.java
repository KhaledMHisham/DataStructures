package main.trees;

import java.util.ArrayList;

public class BinaryTree<T> {
    ArrayList<Node<T>> tree = new ArrayList<>();
    Node<T> root;
    private class Node<T>{
        private Node parent;
        private Node left_child;
        private Node right_child;
        private T key;
        public Node(T key, Node<T> parent) {
            this.key = key;
            this.parent = parent;
        }

        @Override
        public String toString() {
            if(parent != null){
                return "Node{" +
                        "parent=" + parent.key +
                        ", key=" + key +
                        '}';
            }
            return "Node{" +
                    "parent=" + null +
                    ", key=" + key +
                    '}';
        }
    }
    public void initiateTree(T[] keys){
        root = new Node<>(keys[0], null);
        tree.add(root);
        for(int i = 1; i < keys.length; ++i){
            tree.add(new Node<>(keys[i], tree.get((i - 1) / 2)));
        }
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "tree=" + tree +
                ", root=" + root +
                '}';
    }
}
