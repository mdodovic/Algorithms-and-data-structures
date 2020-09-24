package collections;

public class LinkedQueueOfStrings extends QueueOfStrings {

    private Node first, last;
    private int size;
        
    private class Node {
        String item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public String dequeue() {
        String item = first.item;
        first = first.next;
        size--;
        if(isEmpty())
            last = null;
        return item;
    }

    @Override
    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        size++;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
    }

}
