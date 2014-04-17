import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MyHashTable<K, V> {
	/** Capacity of this HashTable. */
	final private int my_capacity;

	/** Number of entries in the HashTable. */
	private int my_entries;

	/** Histogram of the Bucket sizes */
	private Map<Integer, Integer> my_bucket_sizes;

	private HashNode<K, V>[] my_array;

	MyHashTable(int the_capacity) {

		/* Capacity of the HashTable. */
		my_capacity = the_capacity;
		/* Array that holds data for the HashTable */
		my_array = new HashNode[my_capacity];
		/* ArrayList for histogram */
		my_bucket_sizes = new TreeMap<Integer, Integer>();

	}

	/**
	 * Places a key/value pair into the HashTable
	 * 
	 * @param the_searchKey
	 *            key value
	 * @param the_newValue
	 *            value to go with the key.
	 */
	public void put(K the_searchKey, V the_newValue) {
		if (my_array[hash(the_searchKey)] != null) {
			boolean added = false;
			for (HashNode<K, V> the_node = my_array[hash(the_searchKey)]; the_node != null; the_node = the_node.my_next) {
				if (the_node.my_key.equals(the_searchKey)) {
					the_node.my_value = the_newValue;
					added = true;
				}
				if (added == false && the_node.my_next == null) {
					the_node.my_next = new HashNode<K, V>(the_searchKey,
							the_newValue, null);
					my_entries++;
				}
			}
		} else {
			my_array[hash(the_searchKey)] = new HashNode<K, V>(the_searchKey,
					the_newValue, null);
			my_entries++;
		}
	}

	/**
	 * Grabs the value at the specified key.
	 * 
	 * @param the_searchKey
	 *            key to search by
	 * @return the value.
	 */
	public V get(K the_searchKey) {
		if (my_array[hash(the_searchKey)] != null) {
			HashNode<K, V> curr = my_array[hash(the_searchKey)];
			if (curr.my_key.equals(the_searchKey)) {
				return curr.my_value;
			}
			while (curr != null) {
				curr = curr.my_next;
				if (curr.my_key.equals(the_searchKey)) {
					return curr.my_value;
				}
			}
		}
		return null;
	}

	/**
	 * Prints off the stats of this HashTable.
	 */
	public void stats() {
		StringBuilder sb = new StringBuilder();
		sb.append("Hash Table Stats \n");
		sb.append("================\n");
		sb.append("Number of Entries: " + my_entries + "\n");
		sb.append("Number of Buckets: " + my_capacity + "\n");
		calculateHisto();
		sb.append("Histogram of Bucket Sizes: " + mapToString() + "\n");
		sb.append("Fill Percentage: "
				+ ((double) my_capacity / (double) my_entries) * 100 + "% \n");
		sb.append("Average Non-Empty Bucket: " + (double) my_entries
				/ (double) my_capacity + "\n");

		System.out.println(sb);

	}

	/**
	 * Calculates info nessecary for the histogram of this hashtable.
	 */
	private void calculateHisto() {
		// For each node, check to see if it is null or not. If the node is
		// place it into the map accordingly. If it is not null loop through the
		// specified node's links and add to the map accordingly.
		for (HashNode<K, V> node : my_array) {
			if (node == null) {
				if (my_bucket_sizes.get(0) == null) {
					my_bucket_sizes.put(0, 1);
				} else {
					my_bucket_sizes.put(0, my_bucket_sizes.get(0) + 1);
				}
			} else {
				int count_of_links = 1;
				while (node.my_next != null) {
					node = node.my_next;
					count_of_links++;
				}
				if (my_bucket_sizes.get(count_of_links) == null) {
					my_bucket_sizes.put(count_of_links, 1);
				} else {
					my_bucket_sizes.put(count_of_links,
							my_bucket_sizes.get(count_of_links) + 1);
				}
			}
		}
	}

	/**
	 * String representation of the map.
	 * 
	 * @return String representation of the map
	 */
	private String mapToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < my_bucket_sizes.size(); i++) {
			sb.append(my_bucket_sizes.get(i) + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Hashing function copy and pasted off of the PDF for this assignment.
	 * 
	 * @param the_key
	 *            value to be hashed.
	 * @return value after it went through the function.
	 */
	private int hash(K the_key) {
		return the_key.hashCode() % (my_capacity / 2) + (my_capacity / 2);
	}

}
