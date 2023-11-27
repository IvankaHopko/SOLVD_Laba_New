package com.solvd.buildingCompany.CustomLinkedList;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    public int getSize(int i) {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CustomLinkedList() {
    }

    public void add(T data) {
        if (head == null) {
            this.head = new Node(data);
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            Node<T> newElement = new Node(data);
            current.setNext(newElement);
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrect index" + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public boolean remove(T data) {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.getNext();
        }
        if (current != null) {
            if (previous == null) {
                head = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }
            size--;
            return true;
        }
        return false;
    }

    public class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
