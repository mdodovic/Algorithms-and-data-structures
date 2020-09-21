package collections;

public class ResizingArrayStackOfString extends StackOfStrings {

    private String[] s;
    private int N;

    public ResizingArrayStackOfString() {
        s = new String[1];
    }

    @Override
    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4)
            resize(s.length/2);
        return item;
    }

    @Override
    public void push(String item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    private void resize(int size) {
        String[] copy = new String[size];
        for(int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
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

