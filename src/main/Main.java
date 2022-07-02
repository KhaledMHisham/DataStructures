package main;

import main.queues.ArrayQueue;
import main.trees.BinaryTree;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Integer[] array = new Integer[]{10, 1, 2, 5, 9, 12, 15, 1, 3, 6};
        binaryTree.initiateTree(array);
        System.out.print(binaryTree);
    }
}
