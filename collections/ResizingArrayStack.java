package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack <Item> implements Iterable<Item>{
    private Item[] s;
    private int N;

    @SuppressWarnings("unchecked")
    public ResizingArrayStack() {
        s = (Item []) new Object[1];
    }

    
    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4)
            resize(s.length/2);
        return item;
    }

    
    public void push(Item item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    @SuppressWarnings("unchecked")
    private void resize(int size) {
        Item[] copy = (Item []) new Object[size];
        for(int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    
    public boolean isEmpty() {
        return N == 0;
    }

    
    public int size() {
        return N;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = N;
        
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return s[--i];
        }
        
    }
}
