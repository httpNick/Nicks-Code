/**
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version Mar 15, 2014 TCSS 342 Winter quarter
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class HashNode<K, V> {
	/** Key value for the node. */
	final K my_key;
	/** Value value for the node. */
	V my_value;
	/** Reference to the next node linked to this one. */
	HashNode<K, V> my_next;

	/**
	 * Constructor for a HashNode.
	 * 
	 * @param the_key
	 *            key value.
	 * @param the_value
	 *            value to attach to the key.
	 * @param the_next
	 *            reference to the next node linked.
	 */
	public HashNode(K the_key, V the_value, HashNode<K, V> the_next) {

		my_key = the_key;

		my_value = the_value;

		my_next = the_next;
	}
}
