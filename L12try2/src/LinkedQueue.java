import java.util.concurrent.ArrayBlockingQueue;

/**
 * LinkedQueue is a FIFO (First In First Out) data structure that stores its elements
 * in non-contiguous chunks of memory that reference one another, forming a linked list.
 * <p>
 * The individual links in a doubly linked list have references to both the preceding and succeeding
 * links in the list. A link in a singly linked list only has a reference to the next link in the list.
 * <p>
 * A singly linked list is fine for our purposes here. It supports efficient removal of the head, and
 * (if you keep a reference to the tail) it supports efficient adding of new elements to the tail.
 * <p>
 * created by {@code cspfrederick} and {@code garethhalladay} Fall17 <br>
 * inspired by Chris Wilcox
 * @param <E> the type of elements held in this collection
 */
public class LinkedQueue<E> extends AQueue<E> implements IQueue<E>{
    /**
     * Underlying data structure to create a singly linked list.
     */
    class Link {
        /**
         * An element to store
         */
        E e;
        /**
         * A reference to the next Link in the list
         */
        Link next = null;

        /**
         * Initializes a newly created Link object.
         * Links are the backbone of LinkedQueue.
         * @param e the element
         */
        Link(E e) { this.e = e; }
    }

    /**
     * a reference to the head of the LinkedQueue
     */
    private Link head = null;
    /**
     * a reference to the tail of the LinkedQueue
     */
    private Link tail = null;
    /**
     * the current size of the queue
     */
    private int count = 0;
    /**
     * the maximum capacity of the queue
     */
    private final int maxCount;

    /**
     * Creates a queue that will allow up to {@code maxSize} elements.
     * @param maxSize The maximum size of the queue
     * @see #maxCount
     */
    LinkedQueue(int maxSize) {
        this.maxCount = maxSize;
        
    }

    /**
     * Adds an element to the queue. If the queue is full, do not modify it, and return false.
     * <p>
     * If the queue was empty, you will need to assign {@link #head} and {@link #tail} to
     * a new {@code Link} instance and set {@link #count} to one.
     * <p>
     * Otherwise, create a new Link, and link it in to the end of the queue.
     * Make sure to set {@link #tail} to reference the new Link, and increment {@link #count}.
     * @param e the element to be added to the queue
     * @return true if the element was added successfully, false otherwise.
     */
    @Override
    public boolean offer(E e) {
        if (this.isEmpty()) {
        	count = 1;
        	Link temp = new Link(e);
        	head = temp;
        	tail = temp;
        	return true;
        }
        if (count == maxCount) {
        	return false;
        }
        Link link = new Link(e);
        tail.next = link;
        tail = link;
        
        count++;
        return true;
        
        
        
    }

    /**
     * Removes and returns the oldest element from the queue. Returns null if empty.
     * <ol>
     *     <li> Decrement count
     *     <li> Store the value that is currently in the head node in a local variable
     *     <li> Assign {@link #head} to the second Link in the queue, effectively removing the head
     *     <li> If {@link #head} is <i>now</i> {@code null}, then set head and tail to null,
     *          because that was the last item in the queue, and it is now empty
     *     <li> Return the original head value
     * </ol>
     * @return the oldest element from the queue or null if empty
     */
    @Override
    public E poll() {
    	if (head == null && count == 0) {
        	
        	return null;
        }
        count--;
        E el =  head.e;
        head = head.next;
        if (head == null && count == 0) {
        	tail = null;
        	count = 0;
        	return el;
        }
        return el;
    }

    /**
     * Returns (but does not remove) the head element in the queue.
     * Returns null if the queue is empty.
     * @return the oldest element from the queue or null if empty
     */
    @Override
    public E peek() {
    	if (this.isEmpty()) {return null;}
       return head.e;
       
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Clears the queue.
     * This should set the head and tail back to null and the count back to 0.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public boolean contains(Object o) {
    	
    	
        
        
        for (Link cur = head; cur != null; cur = cur.next) {
        	if (cur.e.equals(o)) {
        		return true;
        	}
        }
        return false;
        
    }

    public static void main(String[] args) {
        IQueue<Integer> q = new LinkedQueue<>(10);
        q.add(1);
        q.offer(2);
        q.offer(3);
        System.out.println(q.contains(3));
        System.out.println(q.poll());
        System.out.println(q.size());

        // final testing uncomment the following line to get comprehensive testing.
        final int hundred_thousand = 100000;
        final int million = 1000000;

        QueueTestProgram.printFailedTests(hundred_thousand,
                                          ArrayBlockingQueue::new,
                                          LinkedQueue::new);

    }
}
