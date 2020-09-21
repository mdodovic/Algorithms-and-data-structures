package collections;

public class Stack <Item>{

    private Node first;
    private int size;
    
    private class Node {
        Item item;
        Node next;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public void push(Item item) {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        size++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

}
