package main.trees;

import java.util.ArrayList;

public class NonBinaryTree<T> {
    private ArrayList<Node<T>> tree = new ArrayList();

    public Node<T> getRoot() {
        return root;
    }

    private Node<T> root;
    private class Node<T>{
        private Node<T> parent;

        private ArrayList<Node<T>> children;
        private T key;

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
        public void addChild(Node<T> child){
            this.children.add(child);
        }
        public Node(T key) {
            this.key = key;
        }
        private boolean hasChildren(){
            return this.children != null;
        }
        @Override
        public String toString() {
            ArrayList<T> childrenKeys = null;
            if(children != null){
                childrenKeys = new ArrayList<>();
                for(int i = 0 ; i < children.size(); ++i){
                    childrenKeys.add(children.get(i).key);
                }
            }
            T parent_key = null;
            if(parent != null){
                parent_key = parent.key;
            }
            return "Node{" +
                    "parent=" + parent_key +
                    ", children=" + childrenKeys +
                    ", key=" + key +
                    '}';
        }
    }

    public void initiateTree(T[] keys, int[] indices){
        for(int i =0; i < keys.length; ++i){
            tree.add(new Node(keys[i]));
        }
        for(int i = 0; i < indices.length; ++i){
            if(indices[i] == -1){
                this.root = tree.get(i);
                continue;
            }
            tree.get(i).setParent(tree.get(indices[i]));
            if(tree.get(indices[i]).children == null){
                tree.get(indices[i]).children = new ArrayList<>();
            }
            tree.get(indices[i]).addChild(tree.get(i));
        }
    }
    public int calculate_height(){
        return calculate_height(this.root);
    }
    public int calculate_height(Node<T>root){
        if(!root.hasChildren()){
            return 1;
        }
        int height = Integer.MIN_VALUE;
        for(int i = 0;i < root.children.size(); ++i){
            height = Math.max(height, 1 + calculate_height(root.children.get(i)));
        }
        return height;
    }
    @Override
    public String toString() {
        return "Tree{" +
                "tree=" + tree +
                '}';
    }
}
