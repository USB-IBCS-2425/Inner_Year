// Java Problem Set 2 2024.09.06

import java.text.SimpleDateFormat;
import java.util.Date;

class ps2 {
	public static String getFormattedDate(){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
		Date currentDate = new Date();
		String formattedDate = (String) (dateFormatter.format(currentDate));
		return formattedDate;
	}

	public static void main(String[] args){
		String name = "Thomas";
		String date = getFormattedDate();

		System.out.println(name);
		System.out.println(date);
		System.out.println("");
		System.out.println("");

		byte studentCount = 10;
		System.out.println(studentCount + " students are in our class.");
		System.out.println("A new student has enrolled in our class.");
		studentCount++;
		System.out.println(studentCount + " students are in our class.");

		if (studentCount < 4){
			System.out.println("Class is cancelled.");
		} else {
			System.out.println("Class will continue.");
		}

		byte nameLength = (byte) name.length();

		studentCount -= nameLength;

		if (studentCount < 4){
			System.out.println("Class is cancelled.");
		} else {
			System.out.println("Class will continue.");
		}

		boolean classDivisibleByThree = (studentCount % 3 == 0);
		if(classDivisibleByThree){
			System.out.println(classDivisibleByThree);
			System.out.println(studentCount / 3);
		} else {
			System.out.println(3 - (studentCount % 3) + " more students are needed.");
		}
	}
}



