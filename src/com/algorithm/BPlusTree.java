package com.algorithm;

public class BPlusTree<K, V>{

    public final static int DEFAULT_ORDER = 16;
    private int order;
    private Node head;
    private LeafNode root;

    public BPlusTree() {
        this(DEFAULT_ORDER);
    }

    public BPlusTree(int order) {
        this.order = order;
    }

    class LeafNode {

    }

    class Node {
    }
}
