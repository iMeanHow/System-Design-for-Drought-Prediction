package Entity;

import java.io.Serializable;

public class Station implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Starion_id;
	private String Strtion_name;
	private int year;
	private int month;
	private int x[]=new int[28];
	
	
	public int getStarion_id() {
		return Starion_id;
	}
	public void setStarion_id(int starion_id) {
		Starion_id = starion_id;
	}
	public String getStrtion_name() {
		return Strtion_name;
	}
	public void setStrtion_name(String strtion_name) {
		Strtion_name = strtion_name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
}
