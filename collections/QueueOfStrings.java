package collections;

public abstract class QueueOfStrings {

    public abstract void enqueue(String item);
    public abstract String dequeue();

    public abstract boolean isEmpty();
    public abstract int size();
    
}
