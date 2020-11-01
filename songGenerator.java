import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JComboBox;
import java.io.BufferedWriter;
import java.io.*;

/**
 * <h1>Song Generator</h1>
 * 
 * The class that handles the user interface for SongBird's Song Generator function 
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class songGenerator extends Frame implements WindowListener, ActionListener {
        static TextField text1;
        static TextField text2;
        static TextArea text;
        static JComboBox genreBox;
        static JComboBox keyBox;
        static Button b;
        static Button c;
        static Button d;
        static String genre;
        static Progression progress;
        static String keyName;
        static int count;
        static boolean wasMixed;

        /**
         * The constructor that creates the user interface for generating new songs
         * 
         * @param title The title of the window 
         */
        public songGenerator(String title) {
                super(title);
                
                setLayout(new FlowLayout());
                addWindowListener(this);
                
                genre = "Pop/Rock";
                keyName = "Cb";
                count = 0;
                wasMixed = false;
                
                text1 = new TextField(12);
                text2 = new TextField(9);
                text = new TextArea(20, 40);
                String[] genres = {"Pop/Rock", "Jazz/Progressive", "Classical", "Mix it up!"};
                String[] keys = {"Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F", "C", "G", "D", 
                "A", "E", "B", "F#", "C#"};
                JComboBox genreBox = new JComboBox(genres);
                JComboBox keyBox = new JComboBox(keys);
                Button b = new Button("Generate new song");
                Button c = new Button("Print all Chords");
                Button d = new Button("Save Song");
                
                add(text1);
                add(genreBox);
                add(text2);
                add(keyBox);
                add(text);
                add(b);
                add(c);
                add(d);
                
                AL2 button = new AL2();
                b.addActionListener(button);
                
                AL3 keyButton = new AL3();
                keyBox.addActionListener(keyButton);
                
                AL4 printButton = new AL4();
                c.addActionListener(printButton);
                
                AL5 saveButton = new AL5();
                d.addActionListener(saveButton);
                
                genreBox.addActionListener(this);
                
                text.setFont(new Font("Helvetica", Font.PLAIN, 18));
                text.setEditable(false);
                text.setText("\n\n\n\n\n\n\n\n\n                   Select a genre and key, and " +
                "\n                     SongBird will do the rest!");
                
                text1.setEditable(false);
                text1.setText("Change Genre:");
                
                text2.setEditable(false);
                text2.setText("Transpose:");
        }

        /**
         * Handles the selection of an item from the "Genre" JComboBox
         * 
         * @param e The ActionEvent triggered by selecting an item from the "Genre" JComboBox
         */
        public void actionPerformed(ActionEvent e) {
            String keyName = this.keyName;
            if(this.keyName == null) {
                keyName = "Cb";
            }
            JComboBox gr = (JComboBox)e.getSource();
            String genre = (String)gr.getSelectedItem();
            count++;
            printStuff(genre, keyName);
        }
        
        /**
         * @return true if the song's genre was "mix it up" (boolean)
         */
        public boolean getWasMixed() {
            return wasMixed;
        }
        
        /**
         * Prints out relevant song information based on selections
         * 
         * @param genre1 The selected Genre of the song
         * @param key The selected song Key
         */
        public static void printStuff(String genre1, String key) {
            text.setFont(new Font("Helvetica", Font.PLAIN, 18));
            Progression prog = new Progression(new Genre(genre1));
            prog.changeKey(new Key(key, true));
            text.setText("Progressions generated: " + count + "\n" + prog.toString());
            
            progress = prog;
            genre = genre1;
            keyName = key;
        }
        
        /**
         * Prints out relevant song information based on selections
         * 
         * @param prog A progression of Chords from the song
         * @param keyName1 The name of the Key of the selected song
         */
        public static void printStuff(Progression prog, String keyName1) {//doesn't change progression ratios
            text.setFont(new Font("Helvetica", Font.PLAIN, 18));
            prog.changeKey(new Key(keyName1, true));
            text.setText("Progressions generated: " + count + "\n" + prog.toString());
            progress = prog;
            keyName = keyName1;
        }

        /**
         * Handles the closing of the Song Generator window, re-setting settings to
         * their defaults
         * 
         * @param e The WindowEvent triggered by closing the Song Generator window
         */
        public void windowClosing(WindowEvent e) {
            dispose();
            
            genre = "Pop/Rock";
            keyName = "Cb";
            count = 0;
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
}

/**
 * The ActionListener class that handles the "Generate new song" button
 */
class AL2 implements ActionListener {
    /**
     * Handles the pressing of the "Generate new song" button
     * 
     * @param e The ActionEvent triggered by pressing the "Generate new song" button
     */
    public void actionPerformed(ActionEvent e) {
        String genre = songGenerator.genre;
        String keyName = songGenerator.keyName;
        if(songGenerator.genre == null) {
            genre = "Pop/Rock";
        }
        if(songGenerator.keyName == null) {
            keyName = "Cb";
        }
        songGenerator.count++;
        songGenerator.printStuff(genre, keyName);
    }
}

/**
 * The ActionListener class that handles the "Key" JComboBox
 */
class AL3 implements ActionListener {
    /**
     * Handles the selection of an item from the "Key" JComboBox
     * 
     * @param e The ActionEvent triggered by selecting an item from the "Key" JComboBox
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox kc = (JComboBox)e.getSource();
        String newKey = (String)kc.getSelectedItem();
        songGenerator.keyName = newKey;
        if(songGenerator.progress != null) {        
            songGenerator.printStuff(songGenerator.progress, newKey);
        }   
    }
}

/**
 * The ActionListener class that handles the "Print all Chords" button
 */
class AL4 implements ActionListener {
    /**
     * Handles the pressing of the "Print all Chords" button
     * 
     * @param e The ActionEvent triggered by pressing the "Print all Chords" button
     */
    public void actionPerformed(ActionEvent e) {
        if(songGenerator.progress != null && !songGenerator.genre.equalsIgnoreCase("Mix it up!")) {
            VoiceLeader myWindow = new VoiceLeader("SongBird: Song Printer");
            
            if(songGenerator.genre.equalsIgnoreCase("Jazz/Progressive")) {
                myWindow.setSize(1100, 530);
            } else if(songGenerator.genre.equalsIgnoreCase("Classical")) {
                myWindow.setSize(1100,350);
            } else {
                myWindow.setSize(1100,690);
            }
                
            myWindow.setResizable(false);
            myWindow.setVisible(true);
        } 
    }
}

/**
 * The ActionListener class that handles the "Save Song" button
 */
class AL5 implements ActionListener { 
    int count;
    
    /**
     * The default constructor, which sets the count (to be used in naming the destination file)
     * to 1 
     */
    public AL5() {
        count = 1;
    }
    
    /**
     * Handles the pressing of the "Save Song" button
     * 
     * @param e The ActionEvent triggered by pressing the "Save Song" button
     */
    public void actionPerformed(ActionEvent e) 
    {
        if(songGenerator.progress != null) {
            String text = songGenerator.progress.toString();
            fileWriter writer = new fileWriter();
        
            try {
                count = writer.writeToFile(text, count);
            } catch (java.io.IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }
}

/**
 * A class capable of writing song info into a ".txt" file and saving it to the user's Desktop
 * (May perform better on Macs)
 */
class fileWriter {
    /**
     * The method that writes the given info to a ".txt" file on the user's Desktop
     * 
     * @param text The text of the entire song (String)
     * @param count The number of times a new file has been saved (for naming purposes) (int)
     */
    public int writeToFile(String text, int count) throws java.io.IOException
    {
        String userHomeFolder = System.getProperty("user.home");
        File textFile = new File(userHomeFolder + "/Desktop/", "Song" + count + ".txt");
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
        (textFile), true), "utf-8"))) {
                  writer.write(text);
        }
        
        count++;
        return count;
    }
}