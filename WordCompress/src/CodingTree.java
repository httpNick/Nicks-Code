/*
 * Huffman Compression TCSS 342
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Implementation of the Huffman Algorithm used to compress text files.
 * 
 * @author Nick Duncan
 * @version Winter 2014
 */

public class CodingTree {
	/** Capacity for the HashTable used. */
	private static final int CAPACITY = 16384;

	/** HashTable of the final codes once algorithm is constructed. **/
	public MyHashTable<String, String> codes;

	/**
	 * Constructor for the Huffman Algorithm. Calls all methods in this class to
	 * create the map of codes.
	 * 
	 * @param message
	 *            A string holding the entire string of the text file brought
	 *            in.
	 */
	public CodingTree(String message) {
		codes = new MyHashTable<String, String>(CAPACITY);
		/* Puts together a priority queue of all the single node trees. */
		PriorityQueue<Node<Integer>> q = singleNodes(countOfWords(message));
		/* Merge trees together. */
		Node<Integer> n_test = putTreesTogether(q);
		/* Recursive method that puts the codes together from the tree */
		codeBuilder(n_test, "");
	}

	/**
	 * Counts and puts count values to each word and separator in the string.
	 * 
	 * @param message
	 *            The string to have it's characters counted.
	 * @return map of character keys with frequency values.
	 */
	private static Map<String, Integer> countOfWords(String str) {
		Map<String, Integer> word_count = new HashMap<String, Integer>();
		StringBuffer wordBuffer = new StringBuffer();
		// Part of this next block was copy and pasted from your main method.
		for (int i = 0; i < str.length(); i++) {
			Character ch = str.charAt(i);
			if ((ch.compareTo('A') >= 0 && ch.compareTo('Z') <= 0)
					|| (ch.compareTo('a') >= 0 && ch.compareTo('z') <= 0)
					|| (ch.compareTo('0') >= 0 && ch.compareTo('9') <= 0)
					|| ch.equals('\'') || ch.compareTo('-') == 0) {
				wordBuffer.append(ch);
			} else { // separator
				String seperator = ch.toString();
				// If seperator is already in map, increment count by one.
				if (word_count.get(seperator) != null) {
					word_count.put(seperator, word_count.get(seperator) + 1);
					// If it's not in the map - add it.
				} else {
					word_count.put(seperator, 1);
				}
				// If word is already in map, increment count by one.
				if (word_count.get(wordBuffer.toString()) != null) {
					word_count.put(wordBuffer.toString(),
							word_count.get(wordBuffer.toString()) + 1);
				// If it's not in the map - add it.
				} else {
					word_count.put(wordBuffer.toString(), 1);
				}
				wordBuffer = new StringBuffer();
			}
		}
		return word_count;
	}

	/**
	 * Places all character / integer pairs into individual trees and adds each
	 * entry into a priority queue.
	 * 
	 * Runtime: O(n)
	 * 
	 * @param the_map
	 *            map of character / frequency pairs.
	 * @return priority queue full of individual nodes that represent the
	 *         character and it's frequency.
	 */
	private PriorityQueue<Node<Integer>> singleNodes(
			Map<String, Integer> the_map) {
		PriorityQueue<Node<Integer>> nodes = new PriorityQueue<Node<Integer>>();
		/*
		 * Must use the entrySet() method to get a set of keys to be able to
		 * iterate through without knowing the key values.
		 */
		for (Entry<String, Integer> e : the_map.entrySet()) {
			Node<Integer> new_node = new Node<Integer>(e.getKey(), e.getValue());
			nodes.add(new_node);
		}
		return nodes;
	}

	/**
	 * Merges all trees together.
	 * 
	 * Runtime: O(n)
	 * 
	 * @param the_q
	 *            Priority queue that holds trees wanting to be merged together
	 *            to be apart of the Huffman Algorithm.
	 * @return The final completely merged tree of all characters and
	 *         frequencies.
	 */
	private Node<Integer> putTreesTogether(PriorityQueue<Node<Integer>> the_q) {
		while (the_q.size() != 1) {
			Node<Integer> least = the_q.poll();
			Node<Integer> second_least = the_q.poll();
			/* Takes the two least valued nodes and puts them into one tree */
			Node<Integer> least_together = new Node<Integer>(least.count
					+ second_least.count);
			least_together.left = least;
			least_together.right = second_least;
			/* Throw the new merged node back into the Priority Queue. */
			the_q.add(least_together);
		}
		return the_q.poll();
	}

	/**
	 * Recursive method that assigns the binary code to each character in the
	 * tree.
	 * 
	 * O(log(n))
	 * 
	 * @param tree
	 *            Single tree of all character / frequency pairs which need new
	 *            binary strings.
	 * @param s
	 *            String which is built over the entirety of the method and at
	 *            the end will contain it's new binary string according to
	 *            Huffman's Algorithm
	 */
	private void codeBuilder(Node<Integer> tree, String s) {
		if (tree.right == null) {
			/* If you reach a leaf add the entry into the codes map */
			codes.put(tree.s, s);
		} else {
			codeBuilder(tree.left, s + "0");
			codeBuilder(tree.right, s + "1");
		}
	}

	/**
	 * Private inner class for the node
	 * 
	 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
	 * @version Feb 25, 2014
	 * @param <T>
	 *            Abstract type to open up the re-usability of the node.
	 */
	private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
		/**
		 * Data to be held by the node. In the case of Huffman it is the
		 * frequency of the character.
		 */
		private T count;
		/** Character held by this node. */
		private String s;
		/** Node reference to the left */
		private Node<T> left;
		/** Node reference to the right */
		private Node<T> right;

		/**
		 * Constructor for the Node.
		 * 
		 * @param the_char
		 *            character to be in the node
		 * @param the_item
		 *            Object other than the character to be in the node.
		 */
		private Node(String the_char, T the_item) {
			count = the_item;
			s = the_char;
			left = null;
			right = null;
		}

		/**
		 * An alternate constructor used for merging trees together where only
		 * the count is needed.
		 * 
		 * @param the_item
		 *            item to be held in the node.
		 */
		private Node(T the_item) {
			count = the_item;
			s = null;
			left = null;
			right = null;
		}

		/**
		 * String representation of the node.
		 * 
		 * @return string of the information in this node.
		 */
		public String toString() {
			return "(" + s + ", " + count + ")";
		}

		/**
		 * A compareTo method to compare two nodes.
		 * 
		 * @param o
		 *            Node to be compared.
		 * @return negative int if less than, 0 if equal, and positive number if
		 *         greater.
		 */
		@Override
		public int compareTo(Node<T> o) {
			return count.compareTo(o.count);
		}

	}

}