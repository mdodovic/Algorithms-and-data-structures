package collections;

public class FixedCapacityStack <Item> {

    private Item[] s;
    private int N;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity) {
        // s = new Item[capacity];
        // Java doesn't allow generic array creation
        s = (Item []) new Object[capacity];
    }

    
    public Item pop() {
        // return s[--N]; - loitering reference problem
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    
    public void push(Item item) {
        s[N++] = item;
    }

    
    public boolean isEmpty() {
        return N == 0;
    }

    
    public int size() {
        return N;
    }

}
