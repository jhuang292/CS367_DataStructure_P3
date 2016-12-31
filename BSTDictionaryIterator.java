///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WorldCloudGenerator.java
// File:             BSTDictionaryIterator.java
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
import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary. The iterator iterates over the tree in order
 * of the key values (from smallest to largest).
 */
public class BSTDictionaryIterator<K> implements Iterator<K> {

	// TO DO:
	//
	// Add your code to implement the BSTDictionaryIterator. To receive full
	// credit:
	// - You must not use recursion in any of methods or constructor.
	// - The constructor must have a worst-case complexity of O(height of BST).
	//
	// Hint: use a Stack and push/pop nodes as you iterate through the BST.
	// The constructor should push all the nodes needed so the *first* call
	// to next() returns the value in the node with the smallest key.
	// (You can use the Java API Stack or implement your own Stack - if you
	// implement your own, make sure to hand it in.)

	// stack containing the BSTDictionary
	private Stack<BSTnode<K>> stack;

	public BSTDictionaryIterator(BSTnode<K> root) {
		stack = new Stack<BSTnode<K>>();

		// push necessary nodes from the root to the leftmost node
		BSTnode<K> n = root;
		while (n != null) {
			stack.push(n);
			n = n.getLeft();
		}
	}

	/**
	 * If the stack is not empty, return stack is not empty
	 */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/**
	 * Push nodes from the popped node to its in-order successor, when the
	 * necessary nodes are used, pop them
	 */
	public K next() {
		BSTnode<K> popNode = stack.pop();
		
		if (popNode.getRight() != null) {
			BSTnode<K> n = popNode.getRight();
			while (n!= null) {
				stack.push(n);
				n = n.getLeft();
			}
		}
		return popNode.getKey();
	}

	public void remove() {
		// DO NOT CHANGE: you do not need to implement this method
		throw new UnsupportedOperationException();
	}
}
