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

	/** Map of the final codes once algorithm is constructed. **/
	Map<Character, String> codes;

	/**
	 * Constructor for the Huffman Algorithm. Calls all methods in this class to
	 * create the map of codes.
	 * 
	 * @param message
	 *            A string holding the entire string of the text file brought
	 *            in.
	 */
	public CodingTree(String message) {
		codes = new HashMap<Character, String>();
		/* Puts together a priority queue of all the single node trees. */
		PriorityQueue<Node<Integer>> q = singleNodes(countOfChars(message));
		/* Merge trees together. */
		Node<Integer> n_test = putTreesTogether(q);
		/* Recursive method that puts the codes together from the tree */
		codeBuilder(n_test, "");
	}

	/**
	 * Counts and puts count values to each character in the string.
	 * 
	 * @param message
	 *            The string to have it's characters counted.
	 * @return map of character keys with frequency values.
	 */
	private Map<Character, Integer> countOfChars(String message) {
		Map<Character, Integer> char_count = new HashMap<Character, Integer>();
		/*
		 * this for loop goes through each index of the string, and counts the
		 * frequency of characters in which it places in the map.
		 */
		for (int i = 0; i < message.length(); i++) {
			Character next = message.charAt(i);
			Integer count = char_count.get(next);
			/*
			 * if the character does not exist yet in the map, insert it and
			 * make the frequency 1.
			 */
			if (count == null) {
				char_count.put(next, 1);
			} else {
				char_count.put(next, count + 1);
			}
		}
		return char_count;
	}

	/**
	 * Places all character / integer pairs into individual trees and adds each
	 * entry into a priority queue.
	 * 
	 * @param the_map
	 *            map of character / frequency pairs.
	 * @return priority queue full of individual nodes that represent the
	 *         character and it's frequency.
	 */
	private PriorityQueue<Node<Integer>> singleNodes(
			Map<Character, Integer> the_map) {
		PriorityQueue<Node<Integer>> nodes = new PriorityQueue<Node<Integer>>();
		/*
		 * Must use the entrySet() method to get a set of keys to be able to
		 * iterate through without knowing the key values.
		 */
		for (Entry<Character, Integer> e : the_map.entrySet()) {
			Node<Integer> new_node = new Node<Integer>(e.getKey(), e.getValue());
			nodes.add(new_node);
		}
		return nodes;
	}

	/**
	 * Merges all trees together.
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
			codes.put(tree.c, s);
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
		private Character c;
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
		private Node(char the_char, T the_item) {
			count = the_item;
			c = the_char;
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
			c = null;
			left = null;
			right = null;
		}

		/**
		 * String representation of the node.
		 * 
		 * @return string of the information in this node.
		 */
		public String toString() {
			return "(" + c + ", " + count + ")";
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
