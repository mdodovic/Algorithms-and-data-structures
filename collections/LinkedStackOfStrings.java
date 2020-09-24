package collections;

public class LinkedStackOfStrings extends StackOfStrings {

    private Node first;
    private int size;
    
    private class Node {
        String item;
        Node next;
    }

    @Override
    public String pop() {
        String item = first.item;
        first = first.next;
        size--;
        return item;
    }

    @Override
    public void push(String item) {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

}
