import java.util.concurrent.ArrayBlockingQueue;

/**
 * ResizingArrayQueue is a modification to {@link ArrayQueue} that is space efficient
 * at various sizes.
 * <p>
 * A plain ArrayQueue may have a maximum capacity of 1000, but many times
 * it will only use a fraction of that space. Or it may use that entire space for a period of
 * time, but will then drain down to a much smaller number of elements.
 * <p>
 * A ResizingArrayQueue will dynamically resize as it is used so that it does not waste space.
 * Hence you can have a queue of max capacity 1000, but not pay for that storage unless required.
 * Further, if the queue grows to that max capacity, but then drains down to a much smaller size,
 * a smaller array will be used, reclaiming the extra space not being used by the too-large array.
 * <p>
 * The trick with all of this is to do it efficiently, so that it is only slightly slower than {@link ArrayQueue}.
 * <p>
 * by {@code cspfrederick} and {@code garethhalladay} Fall17 <br>
 * inspired by Chris Wilcox
 * @param <E> the type of the elements in the queue
 */
public class ResizingArrayQueue<E> extends ArrayQueue<E> implements IQueue<E>{
    /**
     * the maximum size of the queue.
     */
    final int maxSize;

    /**
     * the minimum size that the queue can shrink to.
     */
    final int minSize;

    /**
     * Create a new queue with the given constraints on size.
     * @param minSize the minimum size of the queue
     * @param maxSize the maximum size of the queue
     */
    public ResizingArrayQueue(int minSize, int maxSize) {
        super(0);
        this.minSize = -1;
        this.maxSize = -1;
    }

    /**
     * Replaces the current {@code elements} array with an array of size {@code newSize}.
     * All the old elements are mapped to their proper locations in the new array.
     * <p>
     * First, check that {@code newSize} is greater than or equal to {@link #size()},
     * throw a new RuntimeException if the new size would not hold all the elements. <br>
     * Next, use {@link #newArray(int)} to create an array of the appropriate size. <br>
     * Then use a {@code for} loop that ranges from {@link #removed} to {@link #added},
     * and assign values from the old array into the new array.
     * <p>
     * If the loop variable is {@code i}, then the element at {@code elements[i % elements.length]}
     * belongs at {@code new_elements[i % new_elements.length]}. After copying over all the elements,
     * assign {@link #elements} to be the newly created array.
     * @param newSize the new size for the backing array
     * @throws RuntimeException if {@code newSize} is less than the current queue size
     */
    protected void resizeArrayTo(int newSize) {
        // code here
    }

    /**
     * Adds an element to the queue. <br>
     * If the queue is at maximum capacity, return false. <br>
     * If the array is full, grow it by calling {@link #resizeArrayTo(int)} with
     * either double the length of the current array or {@link #maxSize}, whichever is smaller.
     * <p>
     * Whether you had to resize or not, now add the new element in the usual way.
     * Don't forget to increment {@code added}.
     * @param e the element to be added to the queue
     * @return true if the element was added, false otherwise.
     */
    @Override
    public boolean offer(E e) {
        return false;
    }

    /**
     * Removes the oldest element from the queue. <br>
     * If the queue is empty, return null. <br>
     * Store the oldest element in the queue in a local variable. Decrement the {@code removed} variable.
     * <p>
     * If the size of the queue is less than a quarter of the length of the array,
     * and the array is not already at the minimum size, then it is time to shrink the array.
     * Call {@link #resizeArrayTo(int)} with either half the length of the current array or
     * {@link #minSize}, whichever is larger.
     * @return the item that was removed or null if the queue is empty
     */
    @Override
    public E poll() {
        return null;
    }

    public static void main(String[] args) {
        IQueue<Integer> q = new ResizingArrayQueue<>(2, 10);
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
                                          (max_size) -> new ResizingArrayQueue<>(5, max_size));
        */
    }
}
