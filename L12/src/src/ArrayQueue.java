import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayQueue is a FIFO (First In First Out) data structure that stores its elements
 * in an array (or something like it, like an {@link java.util.ArrayList}).
 * <p>
 * Can we use an {@link java.util.ArrayList} to directly represent a queue? A FIFO needs to push on one end,
 * and pop from the other. The tail of an {@link java.util.ArrayList} can support pop and push efficiently,
 * but the front supports neither efficiently.
 * <p>
 * Instead, we use an array for storage, and we represent the head and tail of the queue
 * with indices into that array. When one of the indices would fall off the end of the array,
 * it just goes back to the start of the array. That is why this pattern is called a "circular"
 * array. Read more about that <a href=../queue.html>here</a>.
 * <p>
 * We can think of the head and tail indices "chasing" each other around the circular
 * storage. When you add an item, the tail moves. When you take an item, the head moves.
 * If the head catches the tail, the queue is empty. If the tail catches the head, the queue is full.
 * <p>
 * That's a lot to take in, but it's easier to code than it sounds. Notice that the member variables
 * "removed" and "added" are counters recording the <i>total</i> operation count. To see where the head and
 * tail of the queue are, just compute:
 * {@code (removed % elements.length)} or {@code (added % elements.length)}
 * <p>
 * by {@code cspfrederick} and {@code garethhalladay} Fall17 <br>
 * inspired by Chris Wilcox
 * @param <E> the type of elements held in this collection
 */
public class ArrayQueue<E> extends AQueue<E> implements IQueue<E>{

    /**
     * the underlying array for the queue
     */
    protected E[] elements;

    /**
     * the total elements added (set back to zero if clear is called)
     */
    protected int added = 0;

    /**
     * the total elements removed (set back to zero if clear is called)
     */
    protected int removed = 0;

    /**
     * Creates a new queue backed by an array of length {@code maxSize}.
     * Use {@link #newArray(int)} to create the elements array.
     * @param maxSize the capacity of the queue
     * @see #newArray(int)
     */
    public ArrayQueue(int maxSize) {
    }

    /**
     * A helper method to create a new array of the generic type.
     * Read the information provided on <a href=../generics.html>generics</a>.
     * It walks you through the behavior of this small method.
     * @param size the size of the new array
     * @return an new instance of an array of type E
     */
    protected E[] newArray(int size) {
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) new Object[size];
        return arr;
    }

    /**
     * Adds an element to the queue. If the queue is full, return false,
     * otherwise add to the next available position in the queue.
     * <p>
     * The index of the next available position can be found by calculating
     * the remainder of the total number of elements added by the length of the array.
     * <p>
     * Don't forget to increment the added field!
     * @param e the element to add
     * @return true if the element can be added, false otherwise
     * @see #added
     */
    @Override
    public boolean offer(E e) {
        return false;
    }

    /**
     * Removes the oldest element (the head) from the queue, and returns it.
     * If the queue is empty, return null.
     * <p>
     * The index of the oldest element can be determined by calculating the remainder
     * of the total elements removed by the length of the array.
     * <p>
     * Don't forget to increment the removed field!
     * <p>
     * @return the oldest element in the queue, or null if the queue is empty
     * @see #removed
     */
    @Override
    public E poll() {
        return null;
    }

    /**
     * Returns the oldest element in the queue (the element we would remove next),
     * but does not remove it.
     * If the queue is empty, return null.
     * <p>
     * The index of the oldest element can be determined by calculating the remainder
     * of the total elements removed by the length of the array.
     * @return the oldest element in the array, or null if the queue is empty
     */
    @Override
    public E peek() {
        return null;
    }

    /**
     * @return the difference between the total items added and removed
     */
    @Override
    public int size() {
        return -1;
    }

    /**
     * Clears the queue.
     * <p>
     * Reset the count for added and removed. Also, either set all slots in
     * the backing array to null, or reallocate a fresh array.
     * <p>
     * Why is this second step desirable? Why not just reset added and removed and call it done?
     * @see #newArray(int)
     */
    @Override
    public void clear() {
        // code here
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    public static void main(String[] args) {
        IQueue<Integer> q = new ArrayQueue<>(10);
        q.add(1);
        q.offer(2);
        q.offer(3);
        System.out.println(q.contains(3));
        System.out.println(q.poll());
        System.out.println(q.size());

        // final testing uncomment the following line to get comprehensive testing.
        final int hundred_thousand = 100000;
        final int million = 1000000;
        /*
        QueueTestProgram.printFailedTests(hundred_thousand,
                                          ArrayBlockingQueue::new,
                                          ArrayQueue::new);
        */
    }
}
