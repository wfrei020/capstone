package roboticArmGUI.src;
/* Introduction a l'informatique II (ITI 1521)
 * Introduction to Computing II (ITI 1121)
 */

/** Implements the interface <code>Queue</code> using a circular
 *  array.
 *
 * @author Marcel Turcotte, Universite d'Ottawa/University of Ottawa
 */

public class LinkedQueue<E> {

	/** A nested static class.  Linked Elements are used to store the
	 *  elements of this Queue.
	 */

	private static class Elem<T> {

		private T value;
		private Elem<T> next;

		private Elem( T value, Elem<T> next ) {
			this.value = value;
			this.next = next;
		}
	}

	/** Instance variable used to designate the front element (Node)
	 *  of the linked structure.
	 */

	private Elem<E> front;

	/** Instance variable variable used to designate the rear element
	 *  of the linked structure.
	 */

	private Elem<E> rear;

	/** Initializes an empty queue (does the same work as the default
	 * constructor).
	 */

	public LinkedQueue() {
		front = rear = null;
	}

	/**
	 * Tests if this Queue is empty.
	 *
	 * @return true if this Queue is empty; and false otherwise.
	 */

	public boolean isEmpty() {
		return front == null;
	}

	/**
	 * Puts an element at the rear of this Queue.
	 *
	 * @param obj the element be put at the rear of this Queue.
	 */

	public void enqueue( E obj ) {

		Elem<E> newElem = new Elem<E>( obj, null );

		if ( rear == null ) {
			rear = newElem;
			front = rear;
		} else {
			rear.next = newElem;
			rear = newElem;
		}
	}

	/**
	 * Removes and returns the front element of the Queue.
	 *
	 * @return the front element of the Queue.
	 */

	public E dequeue() {



		Elem<E> toBeRemoved = front;
		E savedValue = toBeRemoved.value;

		front = front.next;

		if ( front == null ) {
			rear = null;
		}

		return savedValue;
	}



}
