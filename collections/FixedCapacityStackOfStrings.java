package collections;

public class FixedCapacityStackOfStrings extends StackOfStrings {

    private String[] s;
    private int N;
    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    @Override
    public String pop() {
        // return s[--N]; - loitering reference problem
        String item = s[--N];
        s[N] = null;
        return item;
    }

    @Override
    public void push(String item) {
        s[N++] = item;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

}
