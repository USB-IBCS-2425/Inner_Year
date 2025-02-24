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
    public JButton readB;
    public JButton avgB;
    public JButton writeB;

	public static JTextField toRead;
	public static JTextArea resultT;
	public static ArrayList<String> textTokens;
    public static ArrayList<String> allwords;

	public LitAnalysisLab() {

		textTokens = new ArrayList<String>();
        allwords = new ArrayList<String>();

		mainframe = new JFrame("Literature Analysis");
		mainframe.setSize(800, 600);

		mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });

		toRead = new JTextField("sample.txt");
		toRead.setBounds(300, 50, 200, 40);
        mainframe.setLayout(null);
        mainframe.add(toRead);

        output = new JPanel();
        output.setBounds(200, 250, 100, 40);
        mainframe.add(output);

        readB = new JButton("Read File");
        readB.setActionCommand("READ");
        readB.addActionListener(new ButtonClickListener());
        readB.setBounds(200, 150, 100, 100);
        mainframe.add(readB);

        avgB = new JButton("Average Word");
        avgB.setActionCommand("AVG");
        avgB.addActionListener(new ButtonClickListener());
        avgB.setBounds(340, 150, 100, 100);
        mainframe.add(avgB);

        writeB = new JButton("Write File");
        writeB.setActionCommand("WRITE");
        writeB.addActionListener(new ButtonClickListener());
        writeB.setBounds(480, 150, 100, 100);
        mainframe.add(writeB);

        resultT = new JTextArea("");
        resultT.setBounds(200, 300, 400, 240);
        mainframe.add(resultT);

        mainframe.setVisible(true);
	}

	public static void main(String[] args) {
		LitAnalysisLab fo = new LitAnalysisLab();
	}

    public static double round(double x, int places) {
        int mult = (int)Math.pow(10, places);
        int y = (int)(x*mult);
        double rounded = y / (double) mult;
        return rounded;
    }

    public static void readFile() {
        String fname = toRead.getText();
        textTokens.clear();

        try {
            File f = new File(fname);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String data = s.next();
                textTokens.add(data);
            }
            s.close();
        }
        catch (FileNotFoundException err) {
            System.out.println("An error occurred.");
            err.printStackTrace();
        }

        resultT.setText("File Read\nFile has " + textTokens.size() + " tokens");
    }

    public static void parseWords() {
        allwords.clear();
        for (int i = 0; i < textTokens.size(); i++) {
            String[] tempWords = textTokens.get(i).split("\\s|--");
            for (String s : tempWords) {
                s = s.replaceAll("[\\p{P}_]", "");
                s = s.toLowerCase();
                allwords.add(s);
            }
        }
    }

    public static void showAvg() {
        double totLen = 0;
        for (String w : allwords) {
            totLen = totLen + w.length();
        }
        double avgLen = totLen / allwords.size();
        avgLen = round(avgLen, 2);
        String res = "The average word length is:\n";
        res = res + avgLen + " characters";
        resultT.setText(res);
    }
    
    public static void writeFile() {
        // NEED TO CHECK WITH USER FIRST!
        String fname = toRead.getText();
        String toWrite = resultT.getText();

        try {
            FileWriter w = new FileWriter(fname);
            w.write(toWrite);
            w.close();
        }
        catch (IOException er) {
            System.out.println("Error message:");
            er.printStackTrace();
        }
    }



    public static void cropFileAndReparse() { //removes preamble added by project gutenburg
        
        String text = String.join(" ", textTokens); //joins text back together from tokens

        Pattern indicatorStart = Pattern.compile("^[*]{3} START OF THE PROJECT GUTENBERG EBOOK .* [*]{3}");
        Pattern indicatorEnd = Pattern.compile("^[*]{3} END OF THE PROJECT GUTENBERG EBOOK .* [*]{3}");

        text = indicatorStart.split("^[*]{3} START OF THE PROJECT GUTENBERG EBOOK .* [*]{3}", -1)[2]; //takes right
        text = indicatorEnd.split("^[*]{3} END OF THE PROJECT GUTENBERG EBOOK .* [*]{3}", -1)[0]; //takes left

        System.out.println(text); //remove later

        Scanner s2 = new Scanner(text);

        //copied from scanner
        textTokens.clear();
        while (s2.hasNext()) {
                String data = s2.next();
                textTokens.add(data);
            }
            s2.close();
        }

        allwords.clear();
        for (int i = 0; i < textTokens.size(); i++) {
            String[] tempWords = textTokens.get(i).split("\\s|--");
            for (String s : tempWords) {
                s = s.replaceAll("[\\p{P}_]", "");
                s = s.toLowerCase();
                allwords.add(s);
            }
        }
    }


	private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("WRITE")) {
                writeFile();
            }

            if (command.equals("READ")) {
                readFile();
            }

            if (command.equals("AVG")) {
                parseWords();
                
                showAvg();
            }
        }
    }
}