package proj1;

/* TODO: Implement and properly document
 * this class. 
 */

/**
 * This MyArrayList class extends ArrayList. This MyArrayList class has specific sort and toString methods implemented to work with 
 * the records. 
 *  
 * @author Eric Chan
 */


import java.util.ArrayList;
import java.util.Comparator;

@SuppressWarnings("serial")
public class MyArrayList extends ArrayList<Record> {
	

	/**
	 * Wrapper method for sort to select which comparator to use.
	 * @param key key that is specified in the command to indicate which comparator to use
	 */
	public void sort(String key) {
		
		Comparator<? super Record> c = null;
		// Depending on the key, a different comparator will be used in the sort.
		switch(key) {
		case "CAMIS": 
			c = new RecordComparatorByCamis();
			break;
		case "DBA": 
			c = new RecordComparatorByDBA();
			break;
		case "CUISINE": 
			c = new RecordComparatorByCuisine();
			break;
		case "SCORE": 
			c = new RecordComparatorByScore();
			break;
		case "DATE":
			c = new RecordComparatorByDate();
			break;
		}
		// Calls the other sort method with the appropriate comparator
		sort(c);

	}
	/**
	 * @param Comparator<? super Record> c comparator that matched the key
	 */
	public void sort(Comparator<? super Record> c) {
		
		// An implementation of selection sort that uses comparators, MyArrayList, Records
		for (int i = 0; i < this.size() - 1; i++) {
			Record currentMin = this.get(i);	
			int currentMinIndex = i;

			for (int j = i + 1; j < this.size(); j++) {
				if (c.compare(currentMin, this.get(j)) > 0) {
					currentMin = this.get(j);
					currentMinIndex = j;
				}
			}
			if (currentMinIndex != i) {
				this.set(currentMinIndex, this.get(i));
				this.set(i, currentMin);
			}
		}
	}
	/**
	 * Wrapper method for merge sort
	 * @param key key that user chooses to sort by
	 */
	public void sortFaster(String key) {
		Comparator<? super Record> c = null;
		// Depending on the key, a different comparator will be used in the sort.
		switch(key) {
		case "CAMIS": 
			c = new RecordComparatorByCamis();
			break;
		case "DBA": 
			c = new RecordComparatorByDBA();
			break;
		case "CUISINE": 
			c = new RecordComparatorByCuisine();
			break;
		case "SCORE": 
			c = new RecordComparatorByScore();
			break;
		
		}
		sort(0, this.size() - 1, c);
	}
	/**
	 * Implementation of merge sort
	 * 
	 * @param firstIndex the first index
	 * @param lastIndex the last index
	 * @param c comparator
	 */
	private void sort(int firstIndex, int lastIndex, Comparator<? super Record> c) {
		if (firstIndex < lastIndex) {
			int mid = (firstIndex + lastIndex)/2;
			sort(firstIndex, mid, c);
			sort(mid+1, lastIndex, c);
			merge(firstIndex, mid, mid+1, lastIndex, c);
		}
	
	}
	/**
	 * 
	 * Merges two sorted arrays to one sorted array
	 * 
	 * @param leftFirst first index of left array
	 * @param leftLast last index of left array
	 * @param rightFirst first index of right array
	 * @param rightLast last index of right array
	 * @param c comparator
	 */
	private void merge(int leftFirst, int leftLast, int rightFirst, int rightLast, Comparator<? super Record> c) {
		Record[] tmp = new Record[rightLast - leftFirst + 1];
		
		int indexLeft = leftFirst;
		int indexRight = rightFirst;
		int j = 0;
		
		while (indexLeft <= leftLast && indexRight <= rightLast) {
			if (c.compare(this.get(indexLeft), this.get(indexRight)) < 0) {
				tmp[j] = this.get(indexLeft);
				indexLeft++;
			}
			else {
				tmp[j] = this.get(indexRight);
				indexRight++;
			}
			j++;
		}
		while (indexLeft <= leftLast) {
			tmp[j] = this.get(indexLeft);
			indexLeft++;
			j++;
		}
		while (indexRight <= rightLast) {
			tmp[j] = this.get(indexRight);
			indexRight++;
			j++;
		}
		for (j = 0; j < tmp.length; j++) {
			this.set(leftFirst + j, tmp[j]);
		}
	}
	
	
	/**
	 * toString that is used by findBy methods and only prints 6 data fields
	 */
	public String toString ( ) {
		String str = "";
		for (int i = 0; i < this.size(); i++) {
			str = str + " " + this.get(i).toString();
			str+="\n";
		}
		return str;
	}
	

}
