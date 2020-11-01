import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JComboBox;

/**
 * <h1>Chord Printer</h1>
 * 
 * The class that handles the user interface for SongBird's Chord Printer function 
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class ChordPrinter extends Frame implements WindowListener, ActionListener {
        TextField text;
        TextArea area;
        JComboBox ChordRootBox;
        JComboBox ChordTypeBox;
        Button b;
        static String chordRoot;
        static String chordType;
        static String[] chord;

        /**
         * The constructor that creates the user interface for finding/selecting Chords to print
         * 
         * @param title The title of the window 
         */
        public ChordPrinter(String title) {
                super(title);
                this.chordRoot = "C";
                this.chordType = "Major";
                text = new TextField(24);
                area = new TextArea(14, 58);
                setLayout(new FlowLayout());
                addWindowListener(this);
                
                String[] roots = {"C", "C#", "Db", "D", "D#", "Eb", "E", "E#", "Fb", "F", "F#",
                "Gb", "G", "G#", "Ab", "A", "A#", "Bb", "B", "B#", "Cb"};
                String[] types = {"Major", "minor", "diminished", "Augmented", "sus2", "sus4",
                "Major 7", "minor 7", "diminished 7", "-7 b5", "Dominant 7", "Major 6", "minor 6",
                "Major 9", "minor 9"};
                
                JComboBox ChordRootBox = new JComboBox(roots);
                JComboBox ChordTypeBox = new JComboBox(types);
                Button b = new Button("Find the Chord");
                
                add(text);
                add(ChordRootBox);
                add(ChordTypeBox);
                add(b);
                add(area);
                
                ChordRootButton rootButton = new ChordRootButton();
                ChordRootBox.addActionListener(rootButton);
                ChordTypeButton typeButton = new ChordTypeButton();
                ChordTypeBox.addActionListener(typeButton);
                b.addActionListener(this);
                
                text.setEditable(false);
                area.setEditable(false);
                
                text.setBackground(Color.WHITE);
                text.setForeground(Color.BLACK);
                text.setFont(new Font("Helvetica", Font.PLAIN, 30));
                text.setText("Choose Your Chords Wisely...");
        }

        /**
         * Handles the pressing of the "Find the Chord" button
         * 
         * @param e The ActionEvent triggered by pressing the "Find the Chord" button
         */
        public void actionPerformed(ActionEvent e) {
            if(this.chordRoot == null) {
                this.chordRoot = "C";
            }
            if(this.chordType == null) {
                this.chordType = "Major";
            }
            
            Chord temp = new Chord();
            
            String[] textArr = temp.printNotes(temp.findIntervalsFromChordType(chordType), chordRoot);
            chord = textArr;
            String text = "\n";
            for(int i = 0; i < 6 - textArr.length; i++) {
                if(i == 0) {
                    text += " ";
                } else {
                    text += "  ";
                } 
            }
            for(int i = 0; i < textArr.length; i++) {
                text += textArr[i];
                if(i != textArr.length - 1) {
                    text += ", ";
                }
            }
            area.setFont(new Font("Helvetica", Font.BOLD, 55));
            area.setText(text);
        }
        
        /**
         * Handles the closing of the Chord Printer window, re-setting settings to
         * their defaults
         * 
         * @param e The WindowEvent triggered by closing the Chord Printer window
         */
        public void windowClosing(WindowEvent e) {
            dispose();
            
            this.chordRoot = "C";
            this.chordType = "Major";
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
}

/**
 * The ActionListener class that handles the JComboBox where the user can choose a Chord's root
 */
class ChordRootButton implements ActionListener {
    /**
     * Handles the selection of an item from the "Chord Root" JComboBox
     * 
     * @param e The ActionEvent triggered by making a selection from the "Chord Root" JComboBox
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox rc = (JComboBox)e.getSource();
        String root = (String)rc.getSelectedItem();
        ChordPrinter.chordRoot = root;
    }
}

/**
 * The ActionListener class that handles the JComboBox where the user can choose a Chord's type
 */
class ChordTypeButton implements ActionListener {
    /**
     * Handles the pressing of the "Chord Type" JComboBox
     * 
     * @param e The ActionEvent triggered by making a selection from the "Chord Type" JComboBox
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox tc = (JComboBox)e.getSource();
        String type = (String)tc.getSelectedItem();
        ChordPrinter.chordType = type;
    }
}