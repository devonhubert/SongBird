import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JComboBox;

/**
 * <h1>Scale Finder</h1>
 * 
 * The class that handles the user interface for SongBird's Scale Finder function 
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class ScaleFinder extends Frame implements WindowListener, ActionListener {
        TextField text;
        TextArea area;
        JComboBox ScaleRootBox;
        JComboBox ScaleTypeBox;
        Button b;
        static String scaleRoot;
        static String scaleType;
        static Note[] scale;

        /**
         * The constructor that creates the user interface for finding/selecting scales to print
         * 
         * @param title The title of the window 
         */
        public ScaleFinder(String title) {
                super(title);
                this.scaleRoot = "C";
                this.scaleType = "Ionian";
                
                text = new TextField(24);
                area = new TextArea(14, 58);
                setLayout(new FlowLayout());
                addWindowListener(this);
                
                String[] roots = {"C", "C#", "Db", "D", "D#", "Eb", "E", "E#", "Fb", "F", "F#",
                "Gb", "G", "G#", "Ab", "A", "A#", "Bb", "B", "B#", "Cb"};
                String[] types = {"Ionian (Major)", "Dorian", "Phrygian", "Lydian",
                "Mixolydian", "Aeolian (minor)", "Locrian" /*more?*/};
                
                JComboBox ScaleRootBox = new JComboBox(roots);
                JComboBox ScaleTypeBox = new JComboBox(types);
                Button b = new Button("Find Your Scale");
                
                add(text);
                add(ScaleRootBox);
                add(ScaleTypeBox);
                add(b);
                add(area);
                
                ScaleRootButton rootButton = new ScaleRootButton();
                ScaleRootBox.addActionListener(rootButton);
                ScaleTypeButton typeButton = new ScaleTypeButton();
                ScaleTypeBox.addActionListener(typeButton);
                b.addActionListener(this);
                
                text.setEditable(false);
                area.setEditable(false);
                
                text.setBackground(Color.WHITE);
                text.setForeground(Color.BLACK);
                text.setFont(new Font("Helvetica", Font.PLAIN, 30));
                text.setText(" Scale the Highest Mountains");
        }

        /**
         * Handles the pressing of the "Find your Scale" button
         * 
         * @param e The ActionEvent triggered by pressing the "Find your Scale" button
         */
        public void actionPerformed(ActionEvent e) {
            if(this.scaleRoot == null) {
                this.scaleRoot = "C";
            }
            if(this.scaleType == null) {
                this.scaleType = "Ionian";
            }
            if(this.scaleType.equalsIgnoreCase("Ionian (Major)")) {
                this.scaleType = "Ionian";
            }else if(this.scaleType.equalsIgnoreCase("Aeolian (minor)")) {
                this.scaleType = "Aeolian";
            }
            
            Key temp = new Key(this.scaleRoot, true);
            Note[] scaleArr = temp.turnToParallelMode(scaleType);
            scale = scaleArr;
            
            String text = "";
            
            if(scale[0].getNoteName().equalsIgnoreCase("null") || temp.getKeyValue() < -11) {
                text = "\n     Sorry, your scale is too hip... " + 
                "\n(i.e. no one other than a Jazz God " +
                "\n would even attempt such a scale)\n" +
                "\n        How about another one?";
                area.setFont(new Font("Helvetica", Font.PLAIN, 30));
            } else {
                text += "\n\n";
                for(int i = 0; i < scaleArr.length; i++) {
                    text += scaleArr[i].getNoteName();
                    if(i != scaleArr.length - 1) {
                        text += ", ";
                    }
                }
                area.setFont(new Font("Helvetica", Font.BOLD, 45));
            }
            
            area.setText(text);
        }

        /**
         * Handles the closing of the Scale Finder window, re-setting settings to
         * their defaults
         * 
         * @param e The WindowEvent triggered by closing the Scale Finder window
         */
        public void windowClosing(WindowEvent e) {
            dispose();
            
            this.scaleRoot = "C";
            this.scaleType = "Ionian";
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
}

/**
 * The ActionListener class that handles the JComboBox where the user can choose a scale's root
 */
class ScaleRootButton implements ActionListener {
    /**
     * Handles the selection of an item from the "Scale Root" JComboBox
     * 
     * @param e The ActionEvent triggered by making a selection from the "Scale Root" JComboBox
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox sc = (JComboBox)e.getSource();
        String root = (String)sc.getSelectedItem();
        ScaleFinder.scaleRoot = root;
    }
}

/**
 * The ActionListener class that handles the JComboBox where the user can choose a scale's type
 */
class ScaleTypeButton implements ActionListener {
    /**
     * Handles the selection of an item from the "Scale Type" JComboBox
     * 
     * @param e The ActionEvent triggered by making a selection from the "Scale Type" JComboBox
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox st = (JComboBox)e.getSource();
        String type = (String)st.getSelectedItem();
        ScaleFinder.scaleType = type;
    }
}