//thomas sands



import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;



class LitAnalysisLab {
    public JFrame mainframe;
    public JPanel output;

    public static JTextField fileNameInputField;

    public JButton buttonRead;
    public JButton buttonAvgWordLength;
    public JButton buttonAvgSentenceLength;
    public JButton buttonCommonWords;
    public JButton buttonRareWords;
    public JButton buttonLongWords;
    public JButton buttonShortWords;
    public JButton buttonManyVowelWords;
    public JButton buttonFewVowelWords;
    public JButton buttonUniqueSentence;

    public static JScrollPane scrollPane;
    public static JTextArea textArea;

    //regex constants
    public static final String SENTENCE_FINAL_PUNCTUATION_REGEX = "\\.|\\?|!|‽|⁉|⁈";
    public static final String GENERIC_PUNCTUATION_REGEX = "[\\p{Punct}&&[^'-]]"; //any punctuation except hyphens and apostrophes
    public static final String GENERIC_WHITESPACE_REGEX = "[\\p{Space}]+";

    //word data stuff
    public static ArrayList<String> words;
    public static ArrayList<ArrayList<String>> sentences;

    //more word data (but only calculated on request)
    public static HashMap<String, Integer> wordFrequencyDict;
    public static boolean wordFrequencyDictCalculated;

    public LitAnalysisLab() {
        //variable initialization
        words = new ArrayList<String>();
        sentences = new ArrayList<ArrayList<String>>();

        wordFrequencyDict = new HashMap<String, Integer>();
        wordFrequencyDictCalculated = false;


        //frame
        mainframe = new JFrame("Literature Analysis Lab");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(780, 740); // 100 - 680
        mainframe.setLayout(null);


        //filename input
        fileNameInputField = new JTextField("");
        fileNameInputField.setBounds(300, 50, 200, 40);
        mainframe.add(fileNameInputField);


        //buttons
        buttonRead = new JButton("Read File");
        buttonRead.setActionCommand("READ");
        buttonRead.addActionListener(new ButtonClickListener());
        buttonRead.setBounds(100, 150, 100, 100);
        mainframe.add(buttonRead);

        buttonAvgWordLength = new JButton("Average Word Length");
        buttonAvgWordLength.setActionCommand("AVGWORDLENGTH");
        buttonAvgWordLength.addActionListener(new ButtonClickListener());
        buttonAvgWordLength.setBounds(220, 150, 100, 100);
        mainframe.add(buttonAvgWordLength);

        buttonAvgSentenceLength = new JButton("Average Sentence Length");
        buttonAvgSentenceLength.setActionCommand("AVGSENTENCELENGTH");
        buttonAvgSentenceLength.addActionListener(new ButtonClickListener());
        buttonAvgSentenceLength.setBounds(340, 150, 100, 100);
        mainframe.add(buttonAvgSentenceLength);

        buttonCommonWords = new JButton("Most Common Word");
        buttonCommonWords.setActionCommand("COMMONWORD");
        buttonCommonWords.addActionListener(new ButtonClickListener());
        buttonCommonWords.setBounds(460, 150, 100, 100);
        mainframe.add(buttonCommonWords);

        buttonRareWords = new JButton("Least Common Word");
        buttonRareWords.setActionCommand("RAREWORD");
        buttonRareWords.addActionListener(new ButtonClickListener());
        buttonRareWords.setBounds(580, 150, 100, 100);
        mainframe.add(buttonRareWords);

        buttonLongWords = new JButton("Longest Word");
        buttonLongWords.setActionCommand("LONGESTWORD");
        buttonLongWords.addActionListener(new ButtonClickListener());
        buttonLongWords.setBounds(100, 270, 100, 100);
        mainframe.add(buttonLongWords);

        buttonShortWords = new JButton("Shortest Word");
        buttonShortWords.setActionCommand("SHORTESTWORD");
        buttonShortWords.addActionListener(new ButtonClickListener());
        buttonShortWords.setBounds(220, 270, 100, 100);
        mainframe.add(buttonShortWords);

        buttonManyVowelWords = new JButton("Most Vowely Word");
        buttonManyVowelWords.setActionCommand("MOSTVOWELWORD");
        buttonManyVowelWords.addActionListener(new ButtonClickListener());
        buttonManyVowelWords.setBounds(340, 270, 100, 100);
        mainframe.add(buttonManyVowelWords);

        buttonFewVowelWords = new JButton("Least Vowely Word");
        buttonFewVowelWords.setActionCommand("LEASTVOWELWORD");
        buttonFewVowelWords.addActionListener(new ButtonClickListener());
        buttonFewVowelWords.setBounds(460, 270, 100, 100);
        mainframe.add(buttonFewVowelWords);

        buttonUniqueSentence = new JButton("Uniquest Sentence");
        buttonUniqueSentence.setActionCommand("UNIQUESTSENTENCE");
        buttonUniqueSentence.addActionListener(new ButtonClickListener());
        buttonUniqueSentence.setBounds(580, 270, 100, 100);
        mainframe.add(buttonUniqueSentence);


        //text area setup
        textArea = new JTextArea(100, 10);
        textArea.setLineWrap(true);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(190, 400, 400, 240);
        mainframe.add(scrollPane);


        //show frame
        mainframe.setVisible(true);
    }

    public static void main(String[] args) {
        LitAnalysisLab lab = new LitAnalysisLab();
    }

    //theres lowk some redundant stuff that removes empty strings from words at multiple different points from when i was trying to debug

    public static void readFile() {
        words.clear();
        sentences.clear();

        wordFrequencyDict.clear();
        wordFrequencyDictCalculated = false;

        //open file
        try {
            textArea.setText("Loading file...");

            File f = new File(fileNameInputField.getText());
            Scanner s = new Scanner(f);
            String text = "";
            while (s.hasNext()) { //assembles text file into string
                text += s.next() + " ";
            }
            text = text.substring(0, text.length() - 1); //cuts off last character, which is an extra space
            s.close();

            textArea.setText("Locating excerpt...");

            //removes preamble added by project gutenburg
            text = text.split("[*]{3} START OF THE PROJECT GUTENBERG EBOOK ", 2)[1]; //takes right
            text = text.split("[*]{3}", 2)[1]; //takes right

            text = text.split("[*]{3} END OF THE PROJECT GUTENBERG EBOOK .* [*]{3}", 2)[0]; //takes left

            textArea.setText("Interpreting em dashes...");

            text = text.replaceAll("[-]{2,3}|—", ","); //replaces reduplicated hyphens and em dashes with commas
            //this is necessary because p{Punct} doesnt include em dashes?? idk either man

            textArea.setText("Parsing sentences..");

            // sentence parsing
            ArrayList<String> wholeSentences = new ArrayList<>(Arrays.asList(text.split(SENTENCE_FINAL_PUNCTUATION_REGEX))); //temp list
            for (String sentence : wholeSentences) {
                sentence = sentence.toLowerCase();
                sentence = sentence.replaceAll(GENERIC_PUNCTUATION_REGEX, " ");
                sentences.add(new ArrayList<>(Arrays.asList(sentence.split(GENERIC_WHITESPACE_REGEX)))); //adds sentence wordlist to sentences arraylist
            }

            textArea.setText("Parsing words...");

            //words parsing
            text = text.toLowerCase();
            text = text.replaceAll(GENERIC_PUNCTUATION_REGEX, " "); //replace all punctuation with spaces
            words = new ArrayList<>(Arrays.asList(text.split(GENERIC_WHITESPACE_REGEX))); //separate all words

            //removes empty strings cuz there were issues with that i think
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).length() < 1) {
                    words.remove(i);
                }
            }

            System.out.println(words);

            textArea.setText("File read!");
        } catch (FileNotFoundException error) {
            System.out.println("file not found during readFile() parsing");
            error.printStackTrace();
        }
    }

    public static void calculateWordDict() {
        for (String word : words) {
            if (wordFrequencyDict.containsKey(word)) {
                wordFrequencyDict.put(word, 1 + wordFrequencyDict.get(word));
            } else {
                wordFrequencyDict.put(word, 1);
            }
        }
    }

    public static Integer getVowelCount(String a) {
        int x = 0;
        for (char c : a.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                x++;
            }
        }
        return x;
    }

    public static Float getUniqueness(ArrayList<String> a) { //avg uniqueness per word, higher = less unique
        if (!wordFrequencyDictCalculated) {
            calculateWordDict();
            wordFrequencyDictCalculated = true;
        }

        int sentenceLength = 0;
        int total = 0;

        for (String word : a) {
            if(wordFrequencyDict.containsKey(word)) {
                total += wordFrequencyDict.get(word);
                sentenceLength++;
            } else {
                System.out.println("nonfatal error in getUniqueness(): word not found in freqdict '" + word + "'");
            }
        }

        if (total == 0) {
            return Float.MAX_VALUE;
            //bc if every word occurs zero times then clearly it was comprised purely of 'words' that
            //werent in the freqdict, meaning they were probably empty strings that existed for some reason
        }

        return (float)total / (float)sentenceLength; //int divison so it is technically not as accurate, but it also gives more results so theres more to analyze
    }

    public static void getAvgWordLength() {
        int total = 0;

        for (String word : words) {
            total += word.length();
        }

        float avg = (float)total / (float)words.size();
        textArea.setText(String.format("%.2f", avg));
    }

    public static void getAvgSentenceLength() {
        int total = 0;

        for (ArrayList<String> sentence : sentences) {
            total += sentence.size();
        }

        float avg = (float)total / (float)sentences.size();
        textArea.setText(String.format("%.2f", avg));
    }

    public static void getCommonWord() {
        if (!wordFrequencyDictCalculated) {
            calculateWordDict();
            wordFrequencyDictCalculated = true;
        }

        int max = wordFrequencyDict.get(words.get(0));

        ArrayList<String> commonwords = new ArrayList<String>();

        for (String k : wordFrequencyDict.keySet()) {
            if (wordFrequencyDict.get(k) > max) {
                commonwords.clear();
                commonwords.add(k);
                max = wordFrequencyDict.get(k);
            } else if (wordFrequencyDict.get(k) == max) {
                commonwords.add(k);
            }
        }

        textArea.setText(String.join(", ", commonwords));
    }

    public static void getRareWord() {
        if (!wordFrequencyDictCalculated) {
            calculateWordDict();
            wordFrequencyDictCalculated = true;
        }

        int min = wordFrequencyDict.get(words.get(0));

        ArrayList<String> rarewords = new ArrayList<String>();

        for (String k : wordFrequencyDict.keySet()) {
            if (wordFrequencyDict.get(k) < min) {
                rarewords.clear();
                rarewords.add(k);
                min = wordFrequencyDict.get(k);
            } else if (wordFrequencyDict.get(k) == min) {
                rarewords.add(k);
            }
        }

        textArea.setText(String.join(", ", rarewords));
    }

    public static void getLongWord() {
        int max = words.get(0).length();

        ArrayList<String> longwords = new ArrayList<String>();

        for (String k : words) {
            if (k.length() > max) {
                longwords.clear();
                longwords.add(k);
                max = k.length();
            } else if (k.length() == max) {
                longwords.add(k);
            }
        }

        textArea.setText(String.join(", ", longwords));
    }

    public static void getShortWord() {
        int min = Integer.MAX_VALUE;

        ArrayList<String> shortwords = new ArrayList<String>();

        for (String k : words) {
            if (k.length() < min && k.length() > 0) {
                shortwords.clear();
                shortwords.add(k);
                min = k.length();
            } else if (k.length() == min) {
                shortwords.add(k);
            }
        }

        textArea.setText(String.join(", ", shortwords));
    }

    public static void getVowelyWord() {
        int max = getVowelCount(words.get(0));

        ArrayList<String> vwords = new ArrayList<String>();

        for (String k : words) {
            if (getVowelCount(k) > max) {
                vwords.clear();
                vwords.add(k);
                max = getVowelCount(k);
            } else if (getVowelCount(k) == max) {
                vwords.add(k);
            }
        }

        textArea.setText(String.join(", ", vwords));
    }

    public static void getNonVowelyWord() {
        int min = getVowelCount(words.get(0));

        ArrayList<String> vwords = new ArrayList<String>();

        for (String k : words) {
            if (getVowelCount(k) < min) {
                vwords.clear();
                vwords.add(k);
                min = getVowelCount(k);
            } else if (getVowelCount(k) == min) {
                vwords.add(k);
            }
        }

        textArea.setText(String.join(", ", vwords));
    }

    public static void getUniqueSentence() {
        float min = Float.MAX_VALUE;

        ArrayList<ArrayList<String>> vsents = new ArrayList<ArrayList<String>>();

        for (ArrayList<String> k : sentences) {
            if (k.size() > 7) { //sentences must be 8+ words per the assignment instructions
                if (getUniqueness(k) < min) {
                    vsents.clear();
                    vsents.add(k);
                    min = getUniqueness(k);
                } else if (getUniqueness(k) == min) {
                    vsents.add(k);
                }
                //System.out.println("" + System.currentTimeMillis() + "checked! min=" + min);
            } 
        }

        String result = "";

        for (ArrayList<String> b : vsents) {
            result += String.join(" ", b) + ", ";
        }

        textArea.setText(result);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            switch (event.getActionCommand()) {
            case "READ":
                readFile();
                break;
            case "AVGWORDLENGTH":
                getAvgWordLength();
                break;
            case "AVGSENTENCELENGTH":
                getAvgSentenceLength();
                break;
            case "COMMONWORD":
                getCommonWord();
                break;
            case "RAREWORD":
                getRareWord();
                break;
            case "LONGESTWORD":
                getLongWord();
                break;
            case "SHORTESTWORD":
                getShortWord();
                break;
            case "MOSTVOWELWORD":
                getVowelyWord();
                break;
            case "LEASTVOWELWORD":
                getNonVowelyWord();
                break;
            case "UNIQUESTSENTENCE":
                getUniqueSentence();
                break;
            default:
                System.out.println("error buttonclicklistener unknown command in switch case: " + event.getActionCommand());
                break;
            }
        }
    }
}