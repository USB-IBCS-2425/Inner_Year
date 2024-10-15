import java.util.*;



/*
name     	 sweet    asian   plant  hot
salt         no       no      no     no
hamburger    no       no      no     yes
cabbage  	 no       no      yes    no
fries        no       no      yes    yes
century egg  no       yes     no     no
pho          no       yes     no     yes
bok choy  	 no       yes     yes    no
hot tofu     no       yes     yes    yes
candy     	 yes      no      no     no
fresh-baked cake   yes      no      no     yes
apple        yes      no      yes    no
hot sweet potato       yes      no      yes    yes
mooncake   	 yes      yes     no     no
             yes      yes     no     yes
banana   	 yes      yes     yes    no
Doujiang    yes      yes     yes    yes
*/

class FoodItem {
	private String name;
	private boolean isSweet;
	private boolean isAsian;
	private boolean isPlant;
	private boolean isServedHot;

	public String getName() {
		return this.name;
	}

	public boolean isSweet() {
		return this.isSweet;
	}

	public boolean isAsian() {
		return this.isAsian;
	}

	public boolean isPlant() {
		return this.isPlant;
	}

	public boolean isServedHot(){
		return this.isServedHot;
	}

	public FoodItem(String name, boolean sweet, boolean asian, boolean plant, boolean servedHot) {  
		this.name = name;
		this.isSweet = sweet;
		this.isAsian = asian;
		this.isPlant = plant;
		this.isServedHot = servedHot;
	}
}

class TwentyQuestions {
	private FoodItem[] allOptions;
	private ArrayList<FoodItem> possibleOptions;

	private String[] questions = new String[] {"Is it sweet?", "Is it Asian?", "Is it primarily a plant?", "Is it served hot?"};
	private int questionsAsked = 0;

	public FoodItem[] getAllOptions() {
		return this.allOptions;
		// print for user to see
	}

	public String getQuestion() {
		// returns a string as the question to ask
		if (this.possibleOptions.size() == 1) {
			return "Is it " + this.possibleOptions.get(0).getName() + "?";
		}
		return this.questions[this.questionsAsked];
	}

	public void incrementQuestionsAsked() { //should be called every time a question is answered
		// changes what question will be outputted by getQuestion()
		//and also is used by updateAnswers()
		this.questionsAsked++;
	}

	public void updateAnswers(boolean userAnswer) {
		//takes whatever the 
		//call this before incrementQuestionsAsked()
		ArrayList<FoodItem> updatedOptions = new ArrayList<FoodItem>();
		switch(this.questionsAsked) {
			case 0:
				for (int i = 0; i < this.possibleOptions.size(); i++) {
					if(this.possibleOptions.get(i).isSweet() == userAnswer) {
						updatedOptions.add(this.possibleOptions.get(i));
					}
				}
				break;
			case 1:
				for (int i = 0; i < this.possibleOptions.size(); i++) {
					if(this.possibleOptions.get(i).isAsian() == userAnswer) {
						updatedOptions.add(this.possibleOptions.get(i));
					}
				}
				break;
			case 2:
				for (int i = 0; i < this.possibleOptions.size(); i++) {
					if(this.possibleOptions.get(i).isPlant() == userAnswer) {
						updatedOptions.add(this.possibleOptions.get(i));
					}
				}
				break;
			case 3: 
				for (int i = 0; i < this.possibleOptions.size(); i++) {
					if(this.possibleOptions.get(i).isServedHot() == userAnswer) {
						updatedOptions.add(this.possibleOptions.get(i));
					}
				}
				break;
			default:
				assert(false);
				break;
		}
		this.possibleOptions = updatedOptions;
	}

	public TwentyQuestions(FoodItem[] options) {
		this.allOptions = options;
		this.possibleOptions = new ArrayList<FoodItem>();
		for (int i = 0; i < options.length; i++) { //copies all the elements of the allOptions array into the possibleOptions arraylist as well
			this.possibleOptions.add(options[i]);
		}
		this.questionsAsked = 0;
	}


	public static void main(String[] args) {

		while (true){

		FoodItem[] items = {new FoodItem("salt", false, false, false, false), 
							new FoodItem("hamburger", false, false, false, true), 
							new FoodItem("cabbage", false, false, true, false), 
							new FoodItem("fries", false, false, true, true), 

							new FoodItem("century egg", false, true, false, false), 
							new FoodItem("pho", false, true, false, true), 
							new FoodItem("bok choy", false, true, true, false), 
							new FoodItem("hot tofu", false, true, true, true), 

							new FoodItem("candy", true, false, false, false), 
							new FoodItem("fresh-baked cake", true, false, false, true), 
							new FoodItem("apple", true, false, true, false), 
							new FoodItem("hot sweet potato", true, false, true, true), 

							new FoodItem("mooncake", true, true, false, false), 
							new FoodItem("banana", true, true, true, false), 
							new FoodItem("doujiang", true, true, true, true), 
							};


		TwentyQuestions game = new TwentyQuestions(items);

		System.out.println("Choose an option from the list below:");
		for (int i=0; i<items.length; i++){
			System.out.println(items[i].getName());
		}
		System.out.println("");

		Scanner s = new Scanner(System.in);
		boolean userAnswer;

		for (int i = 0; i < 5; i++) {
			System.out.println(game.getQuestion());

			System.out.println("true/false: ");
			userAnswer = s.nextBoolean();

			game.updateAnswers(userAnswer);
			game.incrementQuestionsAsked();
			System.out.println("");
		}

		System.out.println("Do you want to play again?");
		System.out.println("true/false: ");
		if (s.nextBoolean()== false){
			break;
		} else {
			System.out.println("");
			System.out.println("");
			System.out.println("");
		}

	}
		




	}
}





