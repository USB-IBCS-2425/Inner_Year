


class Seal {
	String name;
	char sex; //'m' or 'f'
	byte age;
	String eyeColor; //"Black" or "Brown"

	// Accessors

	public String getName() {
		return this.name;
	}

	public char getSex() {
		return this.sex;
	}

	public byte getAge() {
		return this.age;
	}

	public String getEyeColor() {
		return this.eyeColor;
	}

	// Mutators

	public void setName(String newName) {
		this.name = newName;
	}

	public void setAge(byte newAge) {
		this.age = newAge;
	}

	// Constructor

	public Seal(String name, char sex, byte age, String eyeColor) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.eyeColor = eyeColor;
	}
}