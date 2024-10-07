// Java Problem Set 5 2024.10.05

import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

class ChompBistro {
	private String[] employees;
	private String[] menuItems;
	private int dayOfWeek; //1-5, 1 = monday (ideally thered be a 'day' class)

	//constants
	private static final int dailyMenuLength = 3;
	private static final int dailyEmployeeCount = 2;

	public ChompBistro(String[] employees, String[] menuItems) {
		this.employees = employees;
		this.menuItems = menuItems;
		this.dayOfWeek = 1;
	}

	private static String arrayToString(String[] a) {
		String result = "";
		for (int i = 0; i < a.length; i++) {
			result += a[i];
			if (i < a.length - 1) {
				result += ", ";
			}
		}
		return result;
	}

	public String getEmployees() {
		return ChompBistro.arrayToString(this.employees);
	}

	public String whoWorking() {
		String[] dailyEmployees = new String[this.dailyEmployeeCount];
		for (int i = 0; i < this.dailyEmployeeCount; i++) {
			dailyEmployees[i] = this.employees[(this.dayOfWeek - 1 + i) % this.employees.length];
		}
		// doesnt work if more employees than weekdays (not like it breaks but it just wont ever assign the last employees)
		return ChompBistro.arrayToString(dailyEmployees);
	}

	public String getMenuItems() {
		return ChompBistro.arrayToString(this.menuItems);
	}

	public String generateMenu(int day) {
		// uses day as random seed so that each day has a random but consistent menu
		Random r = new Random((long) day);
		String[] dailyMenu = new String[this.dailyMenuLength];
		for (int i = 0; i < this.dailyMenuLength; i++) {
			dailyMenu[i] = this.menuItems[(int) Math.floor(r.nextDouble() * this.menuItems.length )]; 
		}
		return ChompBistro.arrayToString(dailyMenu);
	}

	public int getDay() {
		return this.dayOfWeek;
	}

	public void setDay(int day) {
		this.dayOfWeek = day;
	}

	public void incrementDay() {
		this.setDay((this.dayOfWeek % 5) + 1);
	}

	public static void main(String[] args) {
		ChompBistro myBistro = new ChompBistro(new String[] {"Bart", "Lisa", "Ralph", "Todd"}, new String[] {"Sandwich", "Milk", "Grape", "Orange", "Apple"});
		
		int day = 1;
		final String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

		Scanner s = new Scanner(System.in);
		int input = 0;
		boolean failedInput = false;
		String page = "main";

		while (true) {
			input = 0;
			System.out.print("\033\143"); // clears terminal
			System.out.println("Chomp's Bistro - " + weekdays[myBistro.getDay() - 1] + ", Day " + day);
			System.out.println("");
			if (page == "main") {
				System.out.println("Select an option:\n1) Menu of the day\n2) Current employees\n3) Next day\n4) Exit");
				System.out.println("");

				if (failedInput) {
					System.out.print("Please enter a valid input.");
					System.out.println("");
				}
				System.out.print(": ");

				try {
					input = s.nextInt();
					assert(input > 0 && input < 5);
					failedInput = false;
					switch (input) {
						case 1:
							page = "menu";
							break;
						case 2:
							page = "employees";
							break;
						case 3:
							day++;
							myBistro.incrementDay();
							break;
						case 4:
							System.exit(0);
							break;
						default:
							assert(false);
							break;
					}
				} catch (Exception e) {
					failedInput = true;
					System.exit(0); // exit cuz otherwise the whole program break idk why
				}
			} else if (page == "menu") {
				System.out.println(myBistro.generateMenu(day));
				System.out.println("");
				System.out.println("1) Back to main page");
				System.out.println("");

				if (failedInput) {
					System.out.print("Please enter a valid input.");
				}
				System.out.print(": ");

				try {
					input = s.nextInt();
					assert(input == 1);
					failedInput = false;
					page = "main";
				} catch (Exception e) {
					failedInput = true;
					System.exit(0);
				}
			} else if (page == "employees") {
				System.out.println(myBistro.whoWorking());
				System.out.println("");
				System.out.println("1) Back to main page");
				System.out.println("");

				if (failedInput) {
					System.out.print("Please enter a valid input.");
					System.out.println("");
				}
				System.out.print(": ");

				try {
					input = s.nextInt();
					assert(input == 1);
					failedInput = false;
					page = "main";
				} catch (Exception e) {
					failedInput = true;
					System.exit(0);
				}
			}
		}
	}
}







