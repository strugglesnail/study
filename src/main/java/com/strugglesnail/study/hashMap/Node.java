package com.strugglesnail.study.hashMap;

/**
 * @auther strugglesnail
 * @date 2020/11/16 19:43
 * @desc
 */
public class Node<K,V> {

    private V value;
    private Node<K,V> next;

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }
}
