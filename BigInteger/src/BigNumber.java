/*
 * Big Number TCSS 342
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a number of any size in the positive integer range.
 * 
 * @author Nick Duncan
 * @version Winter 2014
 */

public class BigNumber {

	/** list used to represent the number. */
	private List<Integer> my_list = new ArrayList<Integer>();

	/**
	 * public constructor to create a BigNumber and start it out at the value 0.
	 */
	public BigNumber() {
		my_list.add(0);
	}

	/**
	 * Adds an Integer to the BigNumber. Runtime: O(n)
	 * 
	 * @param the_int
	 *            Integer passed in to be added to the BigNumber.
	 * @return A BigNumber with the value of the BigNumber + the_int.
	 */
	public BigNumber add(int the_int) {
		int result;
		int remainder = 0;
		// loop to go through the bigInt starting from the last index. (list)
		for (int i = my_list.size() - 1; i >= 0; i--) {
			/*
			 * Mod off the last digit of the integer being added and add that
			 * together with the current digit in the BigInt + the remainder if
			 * one was previously present.
			 */
			result = the_int % 10 + my_list.get(i) + remainder;
			/*
			 * If the numbers added together reach over 10 add 1 onto the
			 * remainder and the result needs to be changed to reflect the
			 * previous numbers added together % 10.
			 */
			if (result > 9) {
				result = result % 10;
				remainder = 1;
				/*
				 * If the numbers added together are less than 10 there is no
				 * remainder to be added.
				 */
			} else {
				remainder = 0;
			}
			/* Change the current index to the newly added together index. */
			my_list.set(i, result);
			/* Remove 1 digit off the of the end of the integer being added. */
			the_int = the_int / 10;
		}
		/*
		 * If the integer still has value left in it add the remainder onto the
		 * big int.
		 */
		if (the_int > 0) {
			while (the_int > 0) {
				my_list.add(0, the_int % 10 + remainder);
				remainder = 0;
				the_int = the_int / 10;
			}
		}
		/* If the remainder still exists, add it on to the front of the big int */
		if (remainder == 1) {
			my_list.add(0, remainder);
		}
		return this;
	}

	/**
	 * Adds another BigNumber to the BigNumber. Runtime: O(n)
	 * 
	 * @param the_bigInt
	 *            BigNumber passed in to be added to the BigNumber.
	 * @return A BigNumber with the value ofthe BigNumber + the_bigInt.
	 */
	public BigNumber add(BigNumber the_bigInt) {
		int result;
		int remainder = 0;
		List<Integer> copy_bigInt = new ArrayList<Integer>();
		/* Make a copy of the BigInt to not interfere with the current BigInt. */
		copy_bigInt.addAll(the_bigInt.my_list);
		for (int i = my_list.size() - 1; i >= 0; i--) {
			if (copy_bigInt.isEmpty()) {
				result = my_list.get(i) + remainder;
			} else {
				/*
				 * Add End of the copied brought in BigInt to the current index
				 * of the withstanding BigInt, and a remainder if need be.
				 */
				result = copy_bigInt.get(copy_bigInt.size() - 1)
						+ my_list.get(i) + remainder;
			}
			/*
			 * If result is bigger than 10 the remainder needs to be
			 * incrememnted from 0 to 1, and the number added to the
			 * withstanding BigInt needs to only be the last index (mod 10) of
			 * this result
			 */
			if (result > 9) {
				result = result % 10;
				remainder = 1;
				/* If remainder was exhausted change it back to 0. */
			} else {
				remainder = 0;
			}
			my_list.set(i, result);
			if (!copy_bigInt.isEmpty()) {
				copy_bigInt.remove(copy_bigInt.size() - 1);
			}
		}
		/*
		 * If there is still numbers left in the copy of BigInt to be added, add
		 * those.
		 */
		if (copy_bigInt.size() > 0) {
			for (int j = copy_bigInt.size() - 1; j >= 0; j--) {
				my_list.add(0, copy_bigInt.get(j) + remainder);
				remainder = 0;
			}
		}
		/* If the remainder still exists add it on too. */
		if (remainder == 1) {
			my_list.add(0, remainder);
		}
		return this;
	}

	/**
	 * Returns a String representation of the BigNumber. Runtime: O(n).
	 * 
	 * @return A string representation of the Bignumber.
	 */
	public String toString() {
		Iterator<Integer> itr = my_list.iterator();
		StringBuilder sb = new StringBuilder();
		/*
		 * Loop through the BigInt list and append the numbers to a
		 * stringbuilder to be converted to a string representation
		 */
		while (itr.hasNext()) {
			int next = itr.next();
			sb.append(next);
		}
		return sb.toString();
	}
}
