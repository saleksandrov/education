package com.asv.edu.generics;

/**
 * @author alexandrov
 * @since 11.04.2016
 */
public class TypeErasure {

    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn;
        n.setData("Hello"); // produces   java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        Integer x = mn.data;
    }


}


class Node<T> {

    public T data;

    public Node(T data) { this.data = data; }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}

