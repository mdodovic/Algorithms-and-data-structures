import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;
    
    private static class Node <Item> {
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
            throw new IllegalArgumentException("Can't add null to the deque.");
        
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

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        
    }

    // remove and return the item from the front
    public Item removeFirst() {
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }

}
