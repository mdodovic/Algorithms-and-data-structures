package collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private boolean isStorageFull() {
        return items.length == size;
    }

    private void halveStorage() {
        resizeStorage(items.length / 2);
    }

    private void doubleStorage() {
        resizeStorage(items.length * 2);
    }

    private boolean isStorageOversized() {
        return items.length > 1 && size <= items.length / 4;
    }
    
    @SuppressWarnings("unchecked")
    private void resizeStorage(int newSize) {
        Item[] newItemStorage = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItemStorage[i] = items[i];
        }
        items = newItemStorage;
    }

    
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null-pointer to queue.");
        }

        if (isStorageFull()) {
            doubleStorage();
        }

        items[size++] = item;
    }

    public Item dequeue() {  // remove and return a random item
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove an item from an empty queue.");
        }

        int indexOfItemToReturn = StdRandom.uniform(size);
        Item returnValue = items[indexOfItemToReturn];
        size--;
        items[indexOfItemToReturn] = items[size];
        items[size] = null;

        if (isStorageOversized()) {
            halveStorage();
        }

        return returnValue;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot fetch an item from an empty queue.");
        }

        int indexOfItemToReturn = StdRandom.uniform(size);
        return items[indexOfItemToReturn];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }


    private class RandomIterator implements Iterator<Item> {

        private final Item[] iteratorItems;
        private int index;

        public RandomIterator() {
            iteratorItems = copyRandomQueueItems();
            StdRandom.shuffle(iteratorItems);
        }

        @SuppressWarnings("unchecked")
        private Item[] copyRandomQueueItems() {
            Item[] copiedItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copiedItems[i] = items[i];
            }
            return copiedItems;
        }

        @Override
        public boolean hasNext() {
            return index < iteratorItems.length;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return iteratorItems[index++];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation remove is not supported");
        }
    }
    
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> myRQ = new RandomizedQueue<Integer>();
        
        myRQ.enqueue(10);
        myRQ.enqueue(13);
        myRQ.enqueue(5);

        System.out.println("Size: " + myRQ.size());

        Iterator<Integer> it = myRQ.iterator();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
        System.out.println(myRQ.dequeue());
        System.out.println(myRQ.dequeue());
        System.out.println(myRQ.dequeue());
        
        System.out.println("Size: " + myRQ.size());
    }
}