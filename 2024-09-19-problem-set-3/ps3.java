// Java Problem Set 3 2024.09.19

import java.lang.Math;

class ps3 {
	public static void main(String[] args) {
		Wallet joesWallet = new Wallet(100, "38019");
		System.out.println(joesWallet.getMoney());
		System.out.println(joesWallet.getIdNumber());
		joesWallet.setMoney(0); //hes lowkey broke
		joesWallet.setIdNumber("38020"); //he swapped id numbers with his number neighbor
		System.out.println(joesWallet.getMoney());
		System.out.println(joesWallet.getIdNumber());
		joesWallet.payday(10000); //bro was cracked at blackjack
		System.out.println(joesWallet.getMoney());
		joesWallet.pay(8200); //bought a 8200 dollar life sized statue of himself
		System.out.println(joesWallet.getMoney());
		System.out.println("");

		Shape myShape = new Shape();
		System.out.println(myShape.area());
		Rectangle myRectangle = new Rectangle(4, 5);
		System.out.println(myRectangle.getLength());
		System.out.println(myRectangle.getWidth());
		System.out.println(myRectangle.area());
		Circle myCircle = new Circle(5);
		System.out.println(myCircle.getRadius());
		System.out.println(myCircle.area());
	}
}

class Wallet {
	private int money;
	private String idNumber;

	// Accessors

	public int getMoney() {
		return this.money;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	// Mutators

	public void setMoney(int amount) {
		this.money = amount;
	}

	public void setIdNumber(String idNumber){
		this.idNumber = idNumber;
	}

	public void payday(int amount) {
		this.money += amount;
	}

	public void pay(int amount) {
		this.money -= amount;
	}

	// Constructor

	public Wallet(int money, String idNumber) {
		this.money = money;
		this.idNumber = idNumber;
	}
}





class Shape {
	public double area() {
		return (double) 0;
	}
}

class Rectangle extends Shape {
	int length;
	int width;

	public int getLength() {
		return this.length;
	}

	public int getWidth() {
		return this.width;
	}

	public double area() {
		return (double) (this.length * this.width);
	}

	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}
}

class Circle extends Shape {
	int radius;

	public int getRadius() {
		return this.radius;
	}

	public double area() {
		return (double) (this.radius * this.radius * Math.PI);
	}

	public Circle(int radius) {
		this.radius = radius;
	}
}


















