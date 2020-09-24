package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack <Item> implements Iterable<Item>{

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

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item>{

        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (hasNext() == false)
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        
    }

}
