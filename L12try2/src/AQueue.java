import java.util.NoSuchElementException;

/**
 * AQueue provides default implementations for several no-brainer methods in the IQueue interface.
 * <p>
 * Some methods in IQueue are pairs that do the same job, but report failure differently.
 * Implement the fail-by-throwing methods here, in this abstract class,
 * by calling the paired method and checking the return value, then throwing as appropriate.
 * Why is defining these methods here desirable? What are some alternatives?
 * <p>
 * created by {@code cspfrederick} and {@code garethhalladay} Fall17 <br>
 * inspired by Chris Wilcox
 */


public abstract class AQueue<E> implements IQueue<E> {
    // The add method is similar to the offer method except for handling failure.
    // Instead of returning false, it throws an IllegalStateException.
    @Override
    public void add(E item){
    	//codehere
    	
    	boolean result = offer(item);
    	if(!result) throw new IllegalStateException();
        
    }

    // The remove method is similar to the poll method except for handling failure.
    // Instead of returning null, it throws a NoSuchElementException.
    @Override
    public E remove(){
    	E el = poll();
    	if (el == null) throw new NoSuchElementException();
    	
    	
    	return el;
    }

    // The element method is similar to the peek method except for handling failure.
    // Instead of returning null, it throws a NoSuchElementException.
    @Override
    public E element(){
    	
    	if (peek() == null) throw new NoSuchElementException();
    	
    	
    	return peek();
    }

    // This method should utilize the size method to return the correct result.
    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
        	return true;
        }
        return false;
    }
}