///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WorldCloudGenerator.java
// File:             BSTDictionary.java
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
import java.util.Iterator;

public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
	private BSTnode<K> root; // the root node
	private int numItems; // the number of items in the dictionary

	/**
	 * None parameter constructor, used to initialize the node and number of the
	 * nodes in the tree
	 */
	public BSTDictionary() {
		root = null;
		numItems = 0;
	}

	/**
	 * Auxiliary insert method to insert nodes into the BST
	 */
	public void insert(K key) throws DuplicateException {
		root = insert(root, key);
	}

	private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
		if (n == null) {
			numItems++;
			return new BSTnode<K>(key, null, null);
		}

		if (n.getKey().equals(key)) {
			throw new DuplicateException();
		}

		if (key.compareTo(n.getKey()) < 0) {
			n.setLeft(insert(n.getLeft(), key));
			return n;
		}

		else {
			n.setRight(insert(n.getRight(), key));
			return n;
		}
	}

	/**
	 * Auxiliary delete method used to delete node
	 */
	public boolean delete(K key) {
		if (lookup(key) != null) {
			root = delete(root, key);
			numItems--;
			return true;
		}
		return false;
	}

	private BSTnode<K> delete(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}

		if (key.equals(n.getKey())) {
			if (n.getLeft() == null && n.getRight() == null) {
				return null;
			}

			if (n.getLeft() == null) {
				return n.getRight();
			}

			if (n.getRight() == null) {
				return n.getLeft();
			}

			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight(delete(n.getRight(), smallVal));
			return n;
		}

		else if (key.compareTo(n.getKey()) < 0) {
			n.setLeft(delete(n.getLeft(), key));
			return n;
		}

		else {
			n.setRight(delete(n.getRight(), key));
			return n;
		}
	}

	/**
	 * Method used to get the smallest node in the tree
	 * 
	 * @param BST
	 *            node
	 * @return smallest value
	 */
	private K smallest(BSTnode<K> n) {
		if (n.getLeft() == null) {
			return n.getKey();
		}

		else {
			return smallest(n.getLeft());
		}
	}

	/**
	 * Auxiliary lookup method used to look up the node
	 */
	public K lookup(K key) {

		return lookup(root, key);
	}

	private K lookup(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}

		if (n.getKey().equals(key)) {
			return n.getKey();
		}

		else if (key.compareTo(n.getKey()) < 0) {
			return lookup(n.getLeft(), key);
		}

		else
			return lookup(n.getRight(), key);
	}

	/**
	 * boolean method used to check whether the tree is empty
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * size method used to return the number of the nodes of BST
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Auxiliary total path length method to calculate the sum of the lengths of
	 * the paths to each key in the dictionary
	 */
	public int totalPathLength() {

		return totalPathLength(root, 1);
	}

	private int totalPathLength(BSTnode<K> n, int lvl) {

		if (n == null) {
			return 0;
		}

		if (n.getLeft() == null && n.getRight() == null) {

			return lvl;
		}

		else {

			return lvl + totalPathLength(n.getLeft(), lvl + 1) + totalPathLength(n.getRight(), lvl + 1);
		}

	}

	public Iterator<K> iterator() {
		return new BSTDictionaryIterator<K>(root);
	}
}