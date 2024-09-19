import java.util.Random;

class SealEnclosure {
	private String enclosureName;
	private String seals;
	private boolean open;

	// Accessors

	public String getEnclosureName() {
		return this.enclosureName;
	}

	public String getSealNames() {
		return this.seals;
	}

	public byte getSealCount() {
		byte sealCount = 0;
		for(int i = 0; i < this.seals.length(); i++){
			if(this.seals.charAt(i) == ',') {
				sealCount++;
			}
		}
		return sealCount;
	}

	public boolean isOpen() {
		return this.open;
	}

	// Mutators

	public void addSeal(Seal newSeal) {
		this.seals += newSeal.getName();
		this.seals += ",";
	}

	public void setOpenStatus(boolean newIsOpen) {
		this.open = newIsOpen;
	}

	// Reproduction

	public static Seal breed(Seal partner1, Seal partner2, String offspringName) {
		String offspringEyeColor = partner1.getEyeColor() == partner2.getEyeColor() ? partner1.getEyeColor() : "Black"; //Only brown-eyed if both parents brown-eyed
		Random a = new Random();
		char[] offspringSexList = {'m', 'f'}; 
		char offspringSex = offspringSexList[a.nextInt(2)]; //Picks random from {'m', 'f'} 
		return new Seal(offspringName, offspringSex, (byte) 0, offspringEyeColor);
	}

	// Constuctor

	public SealEnclosure(String enclosureName, boolean open) {
		this.enclosureName = enclosureName;
		this.seals = "";
		this.open = open;
	}

	// Main (for testing)

	public static void main(String[] args) {
		// Test Seal Accessors & Constructor
		Seal aaron = new Seal("Aaron", 'm', (byte) 5, "Brown");
		System.out.println(aaron.getName());
		System.out.println(aaron.getSex());
		System.out.println(aaron.getAge());
		System.out.println(aaron.getEyeColor());
		System.out.println("");

		// Test Seal Mutators
		aaron.setName("aaron paul"); //like from breaking bad
		aaron.setAge((byte) (aaron.getAge() + 1));
		System.out.println(aaron.getName());
		System.out.println(aaron.getAge());
		System.out.println("");

		// Test SealEnclosure Accessors & Constructor
		SealEnclosure awesomeEnclosureOfSeals = new SealEnclosure("thomas's seal enclosure", true);
		awesomeEnclosureOfSeals.addSeal(aaron);
		System.out.println(awesomeEnclosureOfSeals.getEnclosureName());
		System.out.println(awesomeEnclosureOfSeals.getSealNames());
		System.out.println(awesomeEnclosureOfSeals.getSealCount());
		System.out.println(awesomeEnclosureOfSeals.isOpen());
		System.out.println("");

		// Test SealEnclosure Mutators
		Seal kenya = new Seal("kenya", 'f', (byte) 21, "Black");
		Seal smith = new Seal("smith", 'm', (byte) 23, "Brown");
		Seal bart = new Seal("bart", 'm', (byte) 11, "Black");
		Seal lisa = new Seal("lisa", 'f', (byte) 9, "Black");
		awesomeEnclosureOfSeals.addSeal(kenya);
		awesomeEnclosureOfSeals.addSeal(smith);
		awesomeEnclosureOfSeals.addSeal(bart);
		awesomeEnclosureOfSeals.addSeal(lisa);
		awesomeEnclosureOfSeals.setOpenStatus(false);
		System.out.println(awesomeEnclosureOfSeals.getSealNames());
		System.out.println(awesomeEnclosureOfSeals.getSealCount());
		System.out.println(awesomeEnclosureOfSeals.isOpen());
		System.out.println("");

		// Test Reproduction Method
		Seal timmy = SealEnclosure.breed(kenya, smith, "little timmy");
		System.out.println(timmy.getName());
		System.out.println(timmy.getSex());
		System.out.println(timmy.getAge());
		System.out.println(timmy.getEyeColor());
		System.out.println("");
	}
}



