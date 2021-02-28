import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;
    
    private static class Node<Item> {
        private Item value;
        private Node<Item> next, prev;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null-pointer to the deque.");
        
        if (first == null) {
            Node<Item> tmp = new Node<>();    
            tmp.value = item;
        
            tmp.next = null;
            tmp.prev = null;
            
            this.first = tmp;
            this.last = tmp;

        } else { 
            Node<Item> tmp = first;
            
            first = new Node<>();    
            first.value = item;
            
            first.prev = null;
            first.next = tmp;

            tmp.prev = first;
            
        }
        size++;        
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null-pointer to the deque.");
        
        if (first == null) {
            Node<Item> tmp = new Node<>();    
            tmp.value = item;
        
            tmp.next = null;
            tmp.prev = null;
            
            this.first = tmp;
            this.last = tmp;

        } else { 
            Node<Item> tmp = last;
            
            last = new Node<>();    
            last.value = item;
            
            last.prev = tmp;
            last.next = null;

            tmp.next = last;
            
        }
        size++;        
        
    }

    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Cannot remove an Item from empty deque.");
        }

        Node<Item> tmp = first;
        
        first = first.next;
        
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        
        size--;
        
        return tmp.value;        
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException("Cannot remove an Item from empty deque.");
        }

        Node<Item> tmp = last;
        
        last = last.prev;
        
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        
        size--;
        
        return tmp.value;        
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element available.");
            }
            
            Item value = current.value;
            current = current.next;
            return value;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation remove is not supported");
        }
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        
    }

}
