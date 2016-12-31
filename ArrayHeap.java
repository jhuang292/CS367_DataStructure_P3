
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WorldCloudGenerator.java
// File:             ArrayHeap.java
// Semester:         CS367 Spring 2016
//
// Author:           Junxiong Huang
// CS Login:         junxiong
// Lecturer's Name:  Amanda Strominger
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Kairat Satbekov
// Email:            satbekov@wisc.edu
// CS Login:         kairat
// Lecturer's Name:  Amanda Strominger
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.NoSuchElementException;

public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

	// default number of items the heap can hold before expanding
	private static final int INIT_SIZE = 100;

	// Add your code to implement the PriorityQueue ADT operations using a
	// heap whose underlying key structure is an array.

	private E[] key;
	private int size;

	/**
	 * No-argument constructor that constructs a heap whose underlying array has
	 * enough space to store INI_SIZE items before needing to expand
	 */
	public ArrayHeap() {
		this(INIT_SIZE);
	}

	/**
	 * 1-argument constructor that takes an integer parameter and constructs a
	 * heap whose underlying array has enough space to store the number of items
	 * given in the parameter before needing to expand. If the parameter value
	 * is less than 0, an IllegalArgumentException is thrown
	 * 
	 */
	public ArrayHeap(int initialSize) {
		if (initialSize < 0) {
			throw new IllegalArgumentException();
		}

		key = (E[]) (new Prioritizable[initialSize + 1]);
		size = 0;
	}

	/**
	 * Returns true if this priority queue contains no items.
	 * 
	 * @return true if this priority queue contains no items, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Adds the given item to the priority queue.
	 * 
	 * @return item - the item to insert into the priority queue
	 */
	public void insert(E item) {
		// If the array is full, Shadow array created

		if (key.length == size + 1) {
			@SuppressWarnings("unchecked")
			E[] newKey = (E[]) (new Prioritizable[key.length * 2]);

			// Copy the old array to the new array
			System.arraycopy(key, 0, newKey, 0, key.length);

			// Set new array as the old array reference
			key = newKey;
		}

		// insert the item into the heap, index increment
		key[++size] = item;

		// Heap the array by swapping up the value

		int child = size;
		while (key[child / 2] != null && key[child / 2].getPriority() < key[child].getPriority()) {

			E temp = key[child / 2];
			key[child / 2] = key[child];
			key[child] = temp;
			child = child / 2;
		}
	}

	/**
	 * Removes and returns the item with the highest priority.
	 * 
	 * @return the item with the highest priority
	 */
	public E removeMax() {

		if (size == 0) {
			throw new NoSuchElementException();
		}

		// set the root as the value to return
		E popMax = key[1];

		// set the last child as the root
		key[1] = key[size];

		// size decrement
		key[size--] = null;

		// heap by swapping down according to the max heap property
		int parent = 1;

		while (parent * 2 + 1 < size && key[parent * 2] != null) {
			// Swap the parent with the child if the children are bigger

			// Set temp pointing to original parent value
			E temp = key[parent];

			// If the right child is null and left child is larger than or equal
			// to parent, swap
			if (key[parent * 2 + 1] == null) {
				if (key[parent * 2].getPriority() >= key[parent].getPriority()) {
					key[parent] = key[parent * 2];
					key[parent * 2] = temp;
					parent = parent * 2;
				} else {
					break;
				}
			}
			// else if left and right are both not null
			else if (key[parent * 2 + 1] != null) {
				if (key[parent * 2].getPriority() > key[parent * 2 + 1].getPriority()) {
					if (key[parent * 2].getPriority() > key[parent].getPriority()) {
						key[parent] = key[parent * 2];
						key[parent * 2] = temp;
						parent = parent * 2;
					} else {
						break;
					}

				} else {
					if (key[parent * 2 + 1].getPriority() > key[parent].getPriority()) {
						key[parent] = key[parent * 2 + 1];
						key[parent * 2 + 1] = temp;
						parent = parent * 2 + 1;
					} else {
						break;
					}
				}

			}

		}
		return popMax;
	}

	/**
	 * Returns the item with the highest priority.
	 * 
	 * @return the item with the highest priority
	 */
	public E getMax() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return key[1];
	}

	public int size() {
		return size;
	}
}
