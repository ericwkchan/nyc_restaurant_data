package proj1;

/* TODO: Implement and properly document
 * this class. 
 */

/**
 * This is the ListOfRecords class and has one data field which is a MyArrayList. In this program, allRecords is an instance of 
 * ListOfRecords. This class has a specific add method, a toString method to print all 18 data fields delimited with commas, 
 * a sort method that calls the MyArrayList sort, and various findBy methods that are implemented for the text commands. 
 * 
 * @author Eric Chan
 */

import java.util.ArrayList;
import java.util.Comparator;

public class ListOfRecords {

	private MyArrayList list;

	public ListOfRecords() {
		list = new MyArrayList();
	}
	/**
	 * Adds each element in words into the MyArrayList list.
	 * 
	 * @param words an ArrayList<String> of reach word (data field) in each record of the csv
	 */
	public void add(ArrayList<String> words) {
		Record r = new Record(words);
		list.add(r);
	}
	/**
	 * toString method that returns a string that has all 18 data fields delimited by commas. 
	 * 
	 * @return str returns str of the 18 data fields delimited by commas.
	 */
	public String toString() {
		String str = ""; 
		for(int i = 0; i < list.size(); i ++) {
			str = str + list.get(i).toString18();
		}
		return str;
	}
	/**
	 * Calls the sort method in MyArrayList
	 * 
	 * @param key key to sort by that is chosen by the user 
	 */
	public void sort(String key) {
		list.sort(key); 
	}
	/**
	 * Calls the sortFaster method in MyArrayList
	 * @param key key to sort by that is chosen by user
	 */
	public void sortFaster(String key) {
		list.sortFaster(key);
	}
	/**
	 * Wrapper method to check if array is sorted
	 * 
	 * @param key key to check if array is sorted by that key
	 * @return true or false depending on if the array is sorted
	 */
	public boolean isSorted(String key) {
		
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

		return isSorted(c);
	}
	/**
	 * Checks if the array is sorted
	 * 
	 * @param c comparator
	 * @return true or false depending on if the array is sorted
	 */
	private boolean isSorted(Comparator<? super Record> c) {
		boolean sorted = true;
		for (int i = 0; i < list.size()-1 ; i++) {
			if (c.compare(list.get(i), list.get(i+1)) > 0) {
				sorted = false;
				return sorted;
			}
		}
		return sorted;
	}


	/**
	 * 
	 * @param key key to sort by that is chosen by the user (in this case CAMIS, DBA, CUISINE, or SCORE)
	 * @return results returns a MyArrayList and is stored in mostRecentRecords
	 */
	public MyArrayList findByName(String key) {
		System.out.println(key);
		MyArrayList results = new MyArrayList();
		// If there is a match in dba, the element is added into results
		for (int i = 0; i < list.size(); i++) { 
			if (list.get(i).getDba().equals(key) ) {
				results.add(list.get(i));
			}
		}
		System.out.println(results.size());
		return results;
	}
	/**
	 * 
	 * @param key1 the DBA
	 * @param key2 the Address
	 * @param dateFlag first, last, or all 
	 * @return results a MyArrayList that is sorted by name and address and is stored in mostRecentRecords
	 */
	public MyArrayList findByNameAddress(String key1, String key2, String dateFlag) {
		
		MyArrayList results = new MyArrayList();
		// Sorts list by date.
		list.sort("DATE");
		// If the dba and addresses match, the element is added into results
		for (int i = 0; i < list.size(); i ++) {
			if (list.get(i).getDba().equals(key1) && list.get(i).getAddress().equals(key2)) {
				results.add(list.get(i));
			}
		}
		// If the dateFlag is first, it removes all records except the first
		if (dateFlag.equals("first")) {
			// add first inspection record
			for(int i = results.size()-1; i >= 1; i--) {
				results.remove(i);
			}
			System.out.println(results.toString());
		}
		// If dateFlag is last, it removes all records except the last
		else if (dateFlag.equals("last")) {
			// add most recent inspection record
			for (int i = results.size()-2; i >= 0; i--) {
				results.remove(i);
			}
		}
		// Sorts by camis
		results.sort("CAMIS");
		return results;
	}
	/**
	 * 
	 * @param numOfDates number of dates you want to print
	 * @return results MyArrayList of earliest number of dates
	 */
	public MyArrayList findByDate(int numOfDates) {	
		
		MyArrayList results = new MyArrayList();
		list.sort("DATE");
		for (int i = 0; i < numOfDates; i++) {
			results.add(list.get(i));
		}
		results.sort("CAMIS");
		return results;
	}
	/**
	 * 
	 * @param score score in the record
	 * @param zipcode zipcode in there record
	 * @return results returns a MyArrayList that has scores lower than the score parameter and matched the zipcode
	 */

	public MyArrayList findByScore(int score, String zipcode) {
		
		MyArrayList results = new MyArrayList();
		// Sorts list by score
		list.sort("SCORE");
		// Adds the element if the score is less than or equal and when the zip code matches.
		for (int i = 0; i < list.size(); i++) {
			if (score >= list.get(i).getScore() && list.get(i).getZip().equals(zipcode)) {
				results.add(list.get(i));
			}
		}
		// Sorts results by camis
		results.sort("CAMIS");
		return results;
	}
}