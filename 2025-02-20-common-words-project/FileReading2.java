//thomas sands
//feb 20th 2025


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class FileReading2 {
    //elements
    public JFrame mainframe;
    public JPanel output;
    public JTextField toRead;
    public JButton readB;
    public JButton mostCommonB;
    public JButton leastCommonB;

    public JScrollPane scrollPane;
    public JTextArea textArea; //text area

    //word data stuff
    public ArrayList<String> wordList;
    public HashMap<String, Integer> wordDict;

    public FileReading2() {

        wordList = new ArrayList<String>();
        wordDict = new HashMap<String, Integer>();

        mainframe = new JFrame("File Reading example");
        mainframe.setSize(800, 600);

        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });

        toRead = new JTextField("");
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

        leastCommonB = new JButton("Get Rarest Word");
        leastCommonB.setActionCommand("GETRAREWORD");
        leastCommonB.addActionListener(new ButtonClickListener());
        leastCommonB.setBounds(480, 150, 100, 100);
        mainframe.add(leastCommonB);

        mostCommonB = new JButton("Get Commonest Word");
        mostCommonB.setActionCommand("GETCOMMONWORD");
        mostCommonB.addActionListener(new ButtonClickListener());
        mostCommonB.setBounds(340, 150, 100, 100);
        mainframe.add(mostCommonB);

        textArea = new JTextArea(100, 10);
        textArea.setLineWrap(true);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(200, 300, 400, 240);
        mainframe.add(scrollPane);

        mainframe.setVisible(true);

    }

    public static void main(String[] args) {
        FileReading2 fo = new FileReading2();
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("READ")) {
                wordList.clear();
                wordDict.clear();

                //reads file & sets up wordlist
                try {
                    File f = new File(toRead.getText()); //open file from toRead textfield
                    Scanner s = new Scanner(f);
                    while (s.hasNext()) { //hasnext is word by word
                        String data = s.next();
                        data = data.toLowerCase();
                        data = data.replaceAll("[^a-z]", "");
                        if (data != "") { //cuz what if theres a non-alpha character as its own word
                            wordList.add(data);
                        }
                    }
                    s.close();
                }
                catch (FileNotFoundException err) {
                    System.out.println("An error occurred.");
                    err.printStackTrace();
                }

                //sets up dictionary
                for (int i = 0; i < wordList.size(); i++) {
                    if (wordDict.containsKey(wordList.get(i))) {
                        wordDict.put(wordList.get(i), 1 + wordDict.get(wordList.get(i)));
                    } else {
                        wordDict.put(wordList.get(i), 1);
                    }
                }

                textArea.setText(String.join(" ", wordList));
            }

            if (command.equals("GETCOMMONWORD")) {
                //linear search

                int max = 0;
                ArrayList<String> words = new ArrayList<String>();

                for (String k : wordDict.keySet()) {
                    if (wordDict.get(k) > max) {
                        words.clear();
                        words.add(k);
                        max = wordDict.get(k);
                    } else if (wordDict.get(k) == max) {
                        words.add(k);
                    }
                }
                System.out.println(wordDict);
                textArea.setText(String.join(", ", words));
            }

            if (command.equals("GETRAREWORD")) {
                //linear search

                int min = wordDict.get(wordList.get(0));
                //min is the val of first worddict entry
                //idk how java infinity works so this is the next best thing

                ArrayList<String> words = new ArrayList<String>();

                for (String k : wordDict.keySet()) {
                    if (wordDict.get(k) < min) {
                        words.clear();
                        words.add(k);
                        min = wordDict.get(k);
                    } else if (wordDict.get(k) == min) {
                        words.add(k);
                    }
                }
                System.out.println(wordDict);
                textArea.setText(String.join(", ", words));
            }
        }
    }
}