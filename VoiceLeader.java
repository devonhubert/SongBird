import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.util.ArrayList;

/**
 * <h1>Voice Leader</h1>
 * 
 * The class that handles the user interface for SongBird's Voice Leader window (called by the
 * "Print All Chords" button
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class VoiceLeader extends Frame implements ActionListener, WindowListener{
        TextField text;
        TextField text2;
        TextField text3;
        TextField text4;
        
        JComboBox ScaleRootBox;
        JComboBox ScaleTypeBox;
        Button b;
        static String scaleRoot;
        static String scaleType;
        static Note[] scale;
        
        TextField[] arr1;
        TextField[] arr2;
        TextField[] arr3;
        TextField[] arr4;
        JTextArea[] verseChordSlots;
        JTextArea[] prechorusChordSlots;
        JTextArea[] chorusChordSlots;
        JTextArea[] bridgeChordSlots;
        
        ArrayList<Chord> verseChords;
        ArrayList<Chord> prechorusChords;
        ArrayList<Chord> chorusChords;
        ArrayList<Chord> bridgeChords;
        
        Genre genre;
        Progression prog;
        
        /**
         * The constructor that sets up all of the song's Chords for printing to the screen
         * and voice leading
         */
        public VoiceLeader(String title) {
                super(title);
                prog = songGenerator.progress;
                this.genre = prog.getGenre();
                
                verseChords = prog.fillNotes(prog.getVerseChords());
                prechorusChords = prog.fillNotes(prog.getPrechorusChords());
                chorusChords = prog.fillNotes(prog.getChorusChords());
                bridgeChords = prog.fillNotes(prog.getBridgeChords());
                
                int verseChordNumber;
                if(verseChords != null) {
                    verseChordNumber = verseChords.size();
                } else {
                    verseChordNumber = 0;
                }
                int prechorusChordNumber;
                if(prechorusChords != null) {
                    prechorusChordNumber = prechorusChords.size();
                } else {
                    prechorusChordNumber = 0;
                }
                int chorusChordNumber;
                if(chorusChords != null) {
                    chorusChordNumber = chorusChords.size();
                } else {
                    chorusChordNumber = 0;
                }
                int bridgeChordNumber;
                if(bridgeChords != null) {
                    bridgeChordNumber = bridgeChords.size();
                } else {
                    bridgeChordNumber = 0;
                }
                
                printOut(verseChordNumber, prechorusChordNumber, chorusChordNumber, bridgeChordNumber);
                
                Button b = new Button("Voice Lead");
                add(b);
                b.addActionListener(this);
        }
        
        /**
         * Prints the song's Chords to the screen
         * 
         * @param verseChordNumber The number of Chords in each verse of the Song (int)
         * @param prechorusChordNumber The number of Chords in each prechorus of the Song (int)
         * @param chorusChordNumber The number of Chords in each chorus of the Song (int)
         * @param bridgeChordNumber The number of Chords in each bridge of the Song (int)
         */
        public void printOut(int verseChordNumber, int prechorusChordNumber, 
            int chorusChordNumber, int bridgeChordNumber) {  
                FlowLayout layout = new FlowLayout();
                layout.setHgap(layout.getHgap() + (17 * verseChordNumber));
                layout.setVgap(layout.getVgap() + verseChordNumber);
                setLayout(layout);
                
                addWindowListener(this);
                printChords(verseChordNumber, prechorusChordNumber, chorusChordNumber, 
                bridgeChordNumber);
        }
        public void printChords(int verseChordNumber, int prechorusChordNumber, 
            int chorusChordNumber, int bridgeChordNumber) {       
                int height = 35;
                int width = 130;
                 
                if(verseChordNumber != 0) {
                    text = new TextField(60);
                    text.setEditable(false);
                    text.setBackground(Color.GRAY);
                    text.setForeground(Color.WHITE);
                    text.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    
                    String str = "";
                    if(this.genre.toString().equalsIgnoreCase("Jazz/Progressive")) {
                        str = " A Section Chords:";
                    } else {
                        str = "   Verse Chords:";
                    }
                    text.setText("                                                " 
                    + str);
                    add(text);
                    
                    arr1 = new TextField[verseChordNumber];
                    verseChordSlots = new JTextArea[verseChordNumber];
                    for(int i = 0; i < verseChordSlots.length; i++) {
                        if(!this.genre.toString().equalsIgnoreCase("Jazz/Progressive")) {//if not jazz
                            verseChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(20 * verseChordNumber + 2));
                        } else {
                            verseChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(20 * verseChordNumber + 2));
                        }
                        verseChordSlots[i].setEditable(false);
                        verseChordSlots[i].setFont(new Font("Helvetica", Font.BOLD, 20));
                        verseChordSlots[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        
                        arr1[i] = new TextField(/*number of characters in chord*/ 64/verseChordNumber);
                        arr1[i].setEditable(false);
                        arr1[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
                    }
                    for(int i = 0; i < verseChordSlots.length; i++) {
                        //add the ith element of chord list to the slot to arr1[i];
                        add(arr1[i]);
                    }
                    for(int i = 0; i < verseChordSlots.length; i++) {
                        add(verseChordSlots[i]);
                    }
                    for(int i = 0; i < arr1.length; i++) {
                        arr1[i].setText("" + verseChords.get(i));
                    }
                    for(int i = 0; i < verseChordSlots.length; i++) {
                        String text = "";
                        for(int j = 0; j < verseChords.get(i).getNotes().size(); j++) {
                            text += verseChords.get(i).getNotes().get(j);
                            if(j != verseChords.get(i).getNotes().size() - 1) {
                                text += ", ";
                            }
                        }
                        verseChordSlots[i].setText("\n " + text);
                    }
                }
                
                if(prechorusChordNumber != 0) {
                    text2 = new TextField(60);
                    text2.setEditable(false);
                    text2.setBackground(Color.GRAY);
                    text2.setForeground(Color.WHITE);
                    text2.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    text2.setText("                                                 " 
                    + "Pre-Chorus Chords:");
                    add(text2);
                    
                    arr2 = new TextField[prechorusChordNumber];
                    prechorusChordSlots = new JTextArea[prechorusChordNumber];
                    for(int i = 0; i < prechorusChordSlots.length; i++) {
                        prechorusChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(20 * prechorusChordNumber + 2));
                        prechorusChordSlots[i].setEditable(false);
                        prechorusChordSlots[i].setFont(new Font("Helvetica", Font.BOLD, 20));
                        prechorusChordSlots[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        
                        arr2[i] = new TextField(/*number of characters in chord*/ 64/prechorusChordNumber);
                        arr2[i].setEditable(false);
                        arr2[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
                    }
                    for(int i = 0; i < prechorusChordSlots.length; i++) {
                        //add the ith element of chord list to the slot to arr2[i];
                        add(arr2[i]);
                    }
                    for(int i = 0; i < prechorusChordSlots.length; i++) {
                        add(prechorusChordSlots[i]);
                    }
                    for(int i = 0; i < arr2.length; i++) {
                        arr2[i].setText("" + prechorusChords.get(i));
                    }
                    for(int i = 0; i < prechorusChordSlots.length; i++) {
                        String text = "";
                        for(int j = 0; j < prechorusChords.get(i).getNotes().size(); j++) {
                            text += prechorusChords.get(i).getNotes().get(j);
                            if(j != prechorusChords.get(i).getNotes().size() - 1) {
                                text += ", ";
                            }
                        }
                        prechorusChordSlots[i].setText("\n " + text);
                    }
                }
                
                if(chorusChordNumber != 0) {
                    text3 = new TextField(60);
                    text3.setEditable(false);
                    text3.setBackground(Color.GRAY);
                    text3.setForeground(Color.WHITE);
                    text3.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    
                    String str = "";
                    
                    if(this.genre.toString().equalsIgnoreCase("Jazz/Progressive")) {
                        str = "                 B Section Chords:";
                    } else if(this.genre.toString().equalsIgnoreCase("Classical")) {
                        str = "   Exposition/Recapitulation Chords:";
                    } else {
                        str = "                   Chorus Chords:";
                    }
                    
                    text3.setText("                                " 
                    + str);
                    add(text3);
                    
                    arr3 = new TextField[chorusChordNumber];
                    chorusChordSlots = new JTextArea[chorusChordNumber];
                    for(int i = 0; i < chorusChordSlots.length; i++) {
                        if(!this.genre.toString().equalsIgnoreCase("Classical")) {//if not classical
                            chorusChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(20 * chorusChordNumber + 2));
                            arr3[i] = new TextField(/*number of characters in chord*/ 16);
                        } else {
                            if(prog.getKey().getKeyValue() < -5 || prog.getKey().getKeyValue() > 5) {
                                chorusChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(135));
                            } else {
                                chorusChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(130));
                            }
                            arr3[i] = new TextField(/*number of characters in chord*/ 10);
                        }
                        chorusChordSlots[i].setEditable(false);
                        chorusChordSlots[i].setFont(new Font("Helvetica", Font.BOLD, 20));
                        chorusChordSlots[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        
                        
                        arr3[i].setEditable(false);
                        arr3[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
                    }
                    for(int i = 0; i < chorusChordSlots.length; i++) {
                        //add the ith element of chord list to the slot to arr3[i];
                        add(arr3[i]);
                    }
                    for(int i = 0; i < chorusChordSlots.length; i++) {
                        add(chorusChordSlots[i]);
                    }
                    for(int i = 0; i < arr3.length; i++) {
                        arr3[i].setText("" + chorusChords.get(i));
                    }
                    for(int i = 0; i < chorusChordSlots.length; i++) {
                        String text = "";
                        for(int j = 0; j < chorusChords.get(i).getNotes().size(); j++) {
                            text += chorusChords.get(i).getNotes().get(j);
                            if(j != chorusChords.get(i).getNotes().size() - 1) {
                                text += ", ";
                            }
                        }
                        chorusChordSlots[i].setText("\n " + text);
                    }
                }
                
                if(bridgeChordNumber != 0) {
                    text4 = new TextField(60);
                    text4.setEditable(false);
                    text4.setBackground(Color.GRAY);
                    text4.setForeground(Color.WHITE);
                    text4.setFont(new Font("Helvetica", Font.PLAIN, 30));
                    
                    String str = "";
                    if(this.genre.toString().equalsIgnoreCase("Jazz/Progressive")) {
                        str = "       Intro Chords:";
                    } else if(this.genre.toString().equalsIgnoreCase("Classical")) {
                        str = "Development Chords:";
                    } else {
                        str = "     Bridge Chords:";
                    }
                    
                    text4.setText("                                              " 
                    + str);
                    add(text4);
                    
                    arr4 = new TextField[bridgeChordNumber];
                    bridgeChordSlots = new JTextArea[bridgeChordNumber];
                    for(int i = 0; i < bridgeChordSlots.length; i++) {
                        if(!this.genre.toString().equalsIgnoreCase("Classical")) {//if not classical
                            bridgeChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(20 * bridgeChordNumber + 2));
                            arr4[i] = new TextField(/*number of characters in chord*/ 16);
                        } else {
                            if(prog.getKey().getKeyValue() < -5 || prog.getKey().getKeyValue() > 5) {
                                bridgeChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(135));
                            } else {
                                bridgeChordSlots[i] = new JTextArea((7 * height)/(80), (7 * width)/(130));
                            }
                            arr4[i] = new TextField(/*number of characters in chord*/ 10);
                        }
                        bridgeChordSlots[i].setEditable(false);
                        bridgeChordSlots[i].setFont(new Font("Helvetica", Font.BOLD, 20));
                        bridgeChordSlots[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        
                        arr4[i].setEditable(false);
                        arr4[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
                    }
                    for(int i = 0; i < bridgeChordSlots.length; i++) {
                        //add the ith element of chord list to the slot to arr4[i];
                        add(arr4[i]);
                    }
                    for(int i = 0; i < bridgeChordSlots.length; i++) {
                        add(bridgeChordSlots[i]);
                    }
                    for(int i = 0; i < arr4.length; i++) {
                        arr4[i].setText("" + bridgeChords.get(i));
                    }
                    for(int i = 0; i < bridgeChordSlots.length; i++) {
                        String text = "";
                        for(int j = 0; j < bridgeChords.get(i).getNotes().size(); j++) {
                            text += bridgeChords.get(i).getNotes().get(j);
                            if(j != bridgeChords.get(i).getNotes().size() - 1) {
                                text += ", ";
                            }
                        }
                        bridgeChordSlots[i].setText("\n " + text);
                    }
                }
        }

        /**
         * Handles the closing of the Voice Leader window
         * 
         * @param e The WindowEvent triggered by closing the Voice Leader window
         */
        public void windowClosing(WindowEvent e) {
            dispose();
        }
        
        /**
         * Handles pressing of the "Voice Lead" button
         * 
         * @param e The ActionEvent triggered by pressing the "Voice Lead" button
         */
        public void actionPerformed(ActionEvent e) {
            
            if(this.genre.toString().equalsIgnoreCase("Classical")) {
                for(int i = 0; i < chorusChordSlots.length; i++) {
                    chorusChordSlots[i].setFont(new Font("Helvetica", Font.BOLD, 19));
                }
                for(int i = 0; i < bridgeChordSlots.length; i++) {
                    bridgeChordSlots[i].setFont(new Font("Helvetica", Font.BOLD, 19));
                }
            }
            
                int verseChordNumber;
                if(verseChords != null) {
                    verseChordNumber = verseChords.size();
                } else {
                    verseChordNumber = 0;
                }
                int prechorusChordNumber;
                if(prechorusChords != null) {
                    prechorusChordNumber = prechorusChords.size();
                } else {
                    prechorusChordNumber = 0;
                }
                int chorusChordNumber;
                if(chorusChords != null) {
                    chorusChordNumber = chorusChords.size();
                } else {
                    chorusChordNumber = 0;
                }
                int bridgeChordNumber;
                if(bridgeChords != null) {
                    bridgeChordNumber = bridgeChords.size();
                } else {
                    bridgeChordNumber = 0;
                }
            
            Progression prog = songGenerator.progress;
                
            verseChords = prog.voiceLead(prog.getVerseChords());
            prechorusChords = prog.voiceLead(prog.getPrechorusChords());
            chorusChords = prog.voiceLead(prog.getChorusChords());
            bridgeChords = prog.voiceLead(prog.getBridgeChords());
             
            if(verseChordNumber != 0) {
                for(int i = 0; i < arr1.length; i++) {
                    arr1[i].setText("" + verseChords.get(i));
                }
                for(int i = 0; i < verseChordSlots.length; i++) {
                    String text = "";
                    for(int j = 0; j < verseChords.get(i).getNotes().size(); j++) {
                        text += verseChords.get(i).getNotes().get(j);
                        if(j != verseChords.get(i).getNotes().size() - 1) {
                            text += ", ";
                        }
                    }
                    verseChordSlots[i].setText("\n " + text);
                }
            }
            
            if(prechorusChordNumber != 0) {
                for(int i = 0; i < arr2.length; i++) {
                    arr2[i].setText("" + prechorusChords.get(i));
                }
                for(int i = 0; i < prechorusChordSlots.length; i++) {
                    String text = "";
                    for(int j = 0; j < prechorusChords.get(i).getNotes().size(); j++) {
                        text += prechorusChords.get(i).getNotes().get(j);
                        if(j != prechorusChords.get(i).getNotes().size() - 1) {
                            text += ", ";
                        }
                    }
                    prechorusChordSlots[i].setText("\n " + text);
                }
            }
        
            if(chorusChordNumber != 0) {
                for(int i = 0; i < arr3.length; i++) {
                    arr3[i].setText("" + chorusChords.get(i));
                }
                for(int i = 0; i < chorusChordSlots.length; i++) {
                    String text = "";
                    for(int j = 0; j < chorusChords.get(i).getNotes().size(); j++) {
                        text += chorusChords.get(i).getNotes().get(j);
                        if(j != chorusChords.get(i).getNotes().size() - 1) {
                            text += ", ";
                        }
                    }
                    chorusChordSlots[i].setText("\n " + text);
                }
            }
        
            if(bridgeChordNumber != 0) {
                for(int i = 0; i < arr4.length; i++) {
                    arr4[i].setText("" + bridgeChords.get(i));
                }
                for(int i = 0; i < bridgeChordSlots.length; i++) {
                    String text = "";
                    for(int j = 0; j < bridgeChords.get(i).getNotes().size(); j++) {
                        text += bridgeChords.get(i).getNotes().get(j);
                        if(j != bridgeChords.get(i).getNotes().size() - 1) {
                            text += ", ";
                        }
                    }
                    bridgeChordSlots[i].setText("\n " + text);
                }
            }
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
}