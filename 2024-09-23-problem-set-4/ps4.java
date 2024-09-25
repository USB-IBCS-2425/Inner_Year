// Java Problem Set 4 2024.09.23

import java.lang.Math;

class ps4 {
	public static void main(String[] args) {
		Student bob = new Student("bob", (short) 3039);
		System.out.println(bob.getName());
		System.out.println(bob.getRaffleNumber());
		System.out.println(bob.isChosen());
		bob.setChosen(true);
		System.out.println(bob.isChosen());

		System.out.println("");
		System.out.println("");
		System.out.println("");

		bob.setChosen(false);
		Student bob1 = new Student("bob1", (short) 3124);
		Student bob2 = new Student("bob2", (short) 5696);
		Student bob3 = new Student("bob3", (short) 1201);
		Student bob4 = new Student("bob4", (short) 2933);
		Student bob5 = new Student("bob5", (short) 4567);
		Student bob6 = new Student("bob6", (short) 1068);
		Student bob7 = new Student("bob7", (short) 3046);
		Student bob8 = new Student("bob8", (short) 8225);
		Student bob9 = new Student("bob9", (short) 9090);
		Raffle tShirtRaffle = new Raffle(new Student[] {bob, bob1, bob2, bob3, bob4, bob5, bob6, bob7, bob8, bob9});
		System.out.println(tShirtRaffle.chooseWinner().getName() + " has won!");
		System.out.println(tShirtRaffle.chooseWinner().getName() + " has won!");
		System.out.println(tShirtRaffle.chooseWinner().getName() + " has won!");


		System.out.println("");
		System.out.println("");
		System.out.println("");

		Fibonnacci myFibonnacci = new Fibonnacci();
		myFibonnacci.printDigits();
		System.out.println("");
		myFibonnacci.updateDigits((byte) 4);
		myFibonnacci.printDigits();
		System.out.println("");
		myFibonnacci.updateDigits((byte) 16);
		myFibonnacci.printDigits();
		System.out.println("");
		myFibonnacci.updateDigits((byte) 20);
		myFibonnacci.printDigits();
		System.out.println("");
		myFibonnacci.updateDigits((byte) 40);
		myFibonnacci.printDigits();
	}
}

class Student {
	private String name;
	private short raffleNumber;
	private boolean chosen;

	public String getName() {
		return this.name;
	}

	public short getRaffleNumber() {
		return this.raffleNumber;
	}

	public boolean isChosen() {
		return this.chosen;
	}

	public void setChosen(boolean isChosen) {
		this.chosen = isChosen;
	}

	public Student(String name, short raffleNumber) {
		this.name = name;
		this.raffleNumber = raffleNumber;
		this.chosen = false;
	}
}

class Raffle {
	private Student[] participants = new Student[10];

	public Student[] getParticipants() {
		return this.participants;
	}

	public Student chooseWinner() {
		Student winner;
		while (true) {
			int rIndex = (int) Math.floor((double) 10 * Math.random());
			if (!this.participants[rIndex].isChosen()) {
				winner = this.participants[rIndex];
				this.participants[rIndex].setChosen(true);
				break;
			}
		}
		return winner;
	}

	public Raffle(Student[] participants) {
		this.participants = participants;
	}
}

class Fibonnacci {
	private int[] digits = new int[20];
	private byte correctDigits;

	public Fibonnacci() {
		this.digits[0] = 0;
		this.digits[1] = 1;
		this.correctDigits = 2;
	}

	public void updateDigits(byte digitsToUpdate) {
		if (digitsToUpdate > 20) {
			digitsToUpdate = 20;
		}
		for (int i = this.correctDigits; i < digitsToUpdate; i++) {
			this.digits[i] = this.digits[i - 1] + this.digits[i - 2];
		}
	}

	public void printDigits() {
		for (int i = 0; i < this.digits.length; i++) {
			System.out.println(this.digits[i]);
		}
	}
}