package com.chalenge.exchangerate.data.model;

import java.util.Map;

public class Pair<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public K setKey(K key) {
        this.key = key;
        return this.key;
    }

    public V setValue(V value) {
        this.value = value;
        return this.value;
    }
}