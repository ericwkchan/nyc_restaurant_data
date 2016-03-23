package proj1;

/* TODO: Implement and properly document
 * the Record class class. 
 * You may change the types of the data fields, if you wish. 
 * 
 * The Comparator classes are provided for your convenience. 
 * 
 */

/**
 * The record class with the 18 data fields that are in each record of the inspection csv file.
 * The comparators are written by Joanna Klukowska. 
 * 
 * @author Eric Chan
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Record {
		
	private String camis;
	private String dba;
	private String boro;
	private String building;
	private String street;
	private String zipcode;
	private String phone;
	private String cuisineDescription;
	private Date inspectionDate;
	private String action;
	private String violationCode;
	private String violationDescription;
	private String criticalFlag;
	private int score;
	private String grade;
	private Date gradeDate;
	private Date recordDate;
	private String inspectionType;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	
	
	/**
	 * Constructor for Record
	 * @param entries ArrayList<String> for data fields and corresponds to each line in the csv
	 */
	public Record ( ArrayList<String> entries ) {
		camis = entries.get( EntriesOrder.CAMIS.ordinal() );
		dba = entries.get( EntriesOrder.DBA.ordinal() );
		boro = entries.get( EntriesOrder.BORO.ordinal() );
		building = entries.get( EntriesOrder.BUILDING.ordinal() );
		street = entries.get( EntriesOrder.STREET.ordinal() );
		zipcode = entries.get( EntriesOrder.ZIPCODE.ordinal() );
		phone = entries.get( EntriesOrder.PHONE.ordinal() );
		cuisineDescription = entries.get( EntriesOrder.CUSINE_DESCRIPTION.ordinal() );
		action = entries.get(( EntriesOrder.ACTION.ordinal() ));
		violationCode = entries.get( EntriesOrder.VIOLATION_CODE.ordinal() );
		violationDescription = entries.get(( EntriesOrder.VIOLATION_DESCRIPTION.ordinal() ));
		criticalFlag = entries.get(( EntriesOrder.CRITICAL_FLAG.ordinal() ));
		grade = entries.get(( EntriesOrder.GRADE.ordinal() ));
		inspectionType = entries.get(( EntriesOrder.INSPECTION_TYPE.ordinal() ));

		try {
			inspectionDate = dateFormat.parse(entries.get(( EntriesOrder.INSPECTION_DATE.ordinal() )));
		} catch (ParseException e) {
			inspectionDate = null;	
		}
		
		try { 
			recordDate = dateFormat.parse(entries.get(( EntriesOrder.RECORD_DATE.ordinal() )));
		}
		catch (ParseException e){
			recordDate = null;
		}
		
		try { 
			gradeDate = dateFormat.parse(entries.get(( EntriesOrder.GRADE_DATE.ordinal() )));		}
		catch (ParseException e){
			gradeDate = null;
		}
		
		try {
			score = Integer.parseInt(entries.get((EntriesOrder.SCORE.ordinal())));
		}
		catch (NumberFormatException e) {
			score = -1;
		}

	}
	
	/**
	 * toString method that prints only 6 specific fields that are delimited by tabs
	 * used by the findBy methods
	 * 
	 * @return output returns a string with the formatted data fields
	 */
	public String toString () {
		
		
		String output = String.format("%10s\t%20s\t%20s\t%20s\t", camis, dba, building + "" + street, cuisineDescription);
		if (inspectionDate == null) {
			output += "\t";
		}
		else { 
			output += String.format("%10s\t", inspectionDate);
		}
		if (score < 0) {
			output += "\t";
		}
		else {
			output += String.format("%3s\t", score);
		}
		return output;
	}
	/**
	 * toString18() is used by the sortAll command and returns a string with all 18 data fields with quotes and delimited by commas
	 * 
	 * @return output returns all of the data fields and is formatted to look like a csv file
	 */
	public String toString18 () {
		String output = "\"" + camis + "\"," + "\"" + dba + "\"," + "\"" +  building + "\"," + "\"" + street + "\","  + 
						 "\"" + zipcode + "\"," +  "\"" + phone + "\"," + "\"" + cuisineDescription + "\",";
		if (inspectionDate == null) {
			output += "\"" + "\"";
		}
		else { 
			output += "\"" + inspectionDate + "\",";
		}
		output += "\"" + action + "\"," + "\"" + violationCode + "\"," + "\"" + violationDescription + "\"," + "\"" + criticalFlag + 
				"\",";
		if (score < 0) { 
			output += "\"" + "\",";
		}
		else {
			output += "\"" + score + "\",";
		}
		output += "\"" + grade + "\",";
		if (gradeDate == null) {
			output += "\"" + "\",";
		}
		else {
			output += "\"" + gradeDate + "\",";
		}
		
		if (recordDate == null) {
			output += "\"" + "\",";
		}
		
		else {
			output += "\"" + recordDate + "\",";
		}
		output += "\"" + inspectionType + "\"\n";
		
		return output;
	}
	/**
	 * 
	 * @return dba returns the name the restaurant 
	 */

	public String getDba() {
		
		return dba;
	}
	/**
	 * 
	 * @return inspectionDate returns date of inspection
	 */
	public Date getInspectionDate() {
		
		return inspectionDate;
	}
	/**
	 * 
	 * @return camis returns the unique number identifier of the restaurant
	 */

	public String getCamis() {
		
		return camis;
	}
	/**
	 * 
	 * @return cuisineDescription returns the description of the restaurant's cuisine
	 */
	public String getCuisineDescription() {
		
		return cuisineDescription;
	}

	/**
	 * 
	 * @return score returns score of the inspection
	 */
	public int getScore() {
		
		return score;
	}
	/**
	 * 
	 * @return building + " " + street returns the address formatted with a space in between building and street
	 */
	
	public String getAddress() {
		return building + " " + street;
	}
	/**
	 * 
	 * @return zipcode returns zip code of the restaurant
	 */
	public String getZip() {
		return zipcode;
	}
	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by their unique
 * camis number. 
 * 
 * @author Joanna Klukowska
 *
 */
class RecordComparatorByCamis implements Comparator<Record>{
	public int compare(Record arg0, Record arg1) { 
		return arg0.getCamis().compareTo( arg1.getCamis() ) ;
	}	
}



/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by the name of the business;
 * ties are resolved based on the unique camis number. 
 * 
 * @author Joanna Klukowska
 *
 */
class RecordComparatorByDBA implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResult = arg0.getDba().compareToIgnoreCase(arg1.getDba() ) ; 
		if ( compareResult == 0 ) {
			return  arg0.getCamis().compareTo( arg1.getCamis() );
		}
		else 
			return compareResult;
	}	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by the type of cuisine;
 * ties are resolved based on the unique camis number. 
 * 
 * @author Joanna Klukowska
 *
 */
class RecordComparatorByCuisine implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResult = arg0.getCuisineDescription().compareToIgnoreCase(
				arg1.getCuisineDescription() ) ; 
		if ( compareResult == 0 ) {
			return  arg0.getCamis().compareTo( arg1.getCamis() );
		}
		else 
			return compareResult;
	}	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by inspection scores;
 * ties are resolved based on the unique camis number. 
 * 
 * @author Joanna Klukowska
 *
 */
class RecordComparatorByScore implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResult = arg0.getScore() - arg1.getScore() ; 
		if ( compareResult == 0 ) {
			return  arg0.getCamis().compareTo( arg1.getCamis() );
		}
		else 
			return compareResult;
	}	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by inspection date;
 * ties are resolved based on the unique camis number. 
 * 
 * @author Joanna Klukowska
 *
 */
class RecordComparatorByDate implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResult = arg0.getInspectionDate().compareTo( arg1.getInspectionDate() ); 
		if ( compareResult == 0 ) {
			return  arg0.getCamis().compareTo( arg1.getCamis() );
		}
		else 
			return compareResult;
	}	
}



