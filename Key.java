import java.util.ArrayList;

/**
 * <h1>Key</h1>
 * Defines the Key of the song, with methods to analyze Chords, scales, 
 * Notes, etc. using the Key's information
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class Key
{
    private String keyName;
    private int keyValue;
    private boolean isMajor = true;
    private Note[] scaleSet = new Note[7];
    
    /**
     * The default constructor, which sets the Key to C Major
     */
    public Key() {
        NoteSet piano = new NoteSet();
        scaleSet[0] = piano.C;
        scaleSet[1] = piano.D;
        scaleSet[2] = piano.E;
        scaleSet[3] = piano.F;
        scaleSet[4] = piano.G;
        scaleSet[5] = piano.A;
        scaleSet[6] = piano.B;
        
        this.keyName = scaleSet[0].getNoteName() + " Major";
        this.keyValue = 0;
    }
    
    /**
     * The constructor that accepts a keyValue (a number representing
     * the number of sharps/flats in the Key), and a variable for
     * whether or not it is a Major or minor key
     * 
     * @param keyValue The integer value of the Key, representing the
     * totals number of sharps and flats int the Key (each flat subtracts
     * 1 from the value, and each flat adds 1)
     * @param isMajor A boolean value that returns true if the Key 
     * is Major
     */
    public Key(int keyValue, boolean isMajor) {
        this.isMajor = isMajor;
        
        NoteSet piano = new NoteSet();
        scaleSet[0] = piano.C;
        scaleSet[1] = piano.D;
        scaleSet[2] = piano.E;
        scaleSet[3] = piano.F;
        scaleSet[4] = piano.G;
        scaleSet[5] = piano.A;
        scaleSet[6] = piano.B;
        this.keyValue = 0;
        
        if(keyValue < 0) {
            addFlats(Math.abs(keyValue));
        }
        else if(keyValue > 0) {
            addSharps(keyValue);
        }
        
        String rootName = "";
        if(isMajor) {
            rootName = findMajorKeyName(this.keyValue);
        } else if(!isMajor) {
            rootName = findMinorKeyName(this.keyValue);
        }
        this.scaleSet = arrangeScale(scaleSet, rootName);
        
        this.keyName = rootName + " Major";
    }
    
    public Key(String keyName, boolean isMajor) {
        this.isMajor = isMajor;
        
        int keyValue = findMajorKeyValue(keyName);
        
        NoteSet piano = new NoteSet();
        scaleSet[0] = piano.C;
        scaleSet[1] = piano.D;
        scaleSet[2] = piano.E;
        scaleSet[3] = piano.F;
        scaleSet[4] = piano.G;
        scaleSet[5] = piano.A;
        scaleSet[6] = piano.B;
        this.keyValue = 0;
        
        
        if(keyValue < 0) {
            addFlats(Math.abs(keyValue));
        }
        else if(keyValue > 0) {
            addSharps(keyValue);
        }
        
        String rootName = "";
        if(isMajor) {
            rootName = findMajorKeyName(this.keyValue);
        } else if(!isMajor) {
            rootName = findMinorKeyName(this.keyValue);
        }
        this.scaleSet = arrangeScale(scaleSet, rootName);
        
        this.keyName = rootName + " Major";
    }
    
    /**
     * @return the Key's name (String)
     */
    public String getKeyName() {
        return keyName; 
    }
    
    /**
     * @return the Key's value (int)
     */
    public int getKeyValue() {
        return keyValue;
    }
    
    /**
     * @return the Key's scaleSet (Note[])
     */
    public Note[] getScaleSet() {
        return scaleSet;
    }
    
    /**
     * Sets the Key's name 
     * @param keyName The Key's name (String)
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName; 
    }
    
    /**
     * Sets the Key's scaleSet 
     * @param scaleSet The Key's scaleSet (Note[])
     */
    public void setScaleSet(Note[] scaleSet) {
        this.scaleSet = scaleSet;
    }
    
    /**
     * Sets the Key's keyValue
     * @param keyValue The Key's value (int)
     */
    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }
    
    public int findMajorKeyValue(String keyName) {
        int value;
        if(keyName.equalsIgnoreCase("Cb")) {
            value = -7;
        } else if(keyName.equalsIgnoreCase("Gb")) {
            value = -6;
        } else if(keyName.equalsIgnoreCase("Db")) {
            value = -5;
        } else if(keyName.equalsIgnoreCase("Ab")) {
            value = -4;
        } else if(keyName.equalsIgnoreCase("Eb")) {
            value = -3;
        } else if(keyName.equalsIgnoreCase("Bb")) {
            value = -2;
        } else if(keyName.equalsIgnoreCase("F")) {
            value = -1;
        } else if(keyName.equalsIgnoreCase("C")) {
            value = 0;
        } else if(keyName.equalsIgnoreCase("G")) {
            value = 1;
        } else if(keyName.equalsIgnoreCase("D")) {
            value = 2;
        } else if(keyName.equalsIgnoreCase("A")) {
            value = 3;
        } else if(keyName.equalsIgnoreCase("E")) {
            value = 4;
        } else if(keyName.equalsIgnoreCase("B")) {
            value = 5;
        } else if(keyName.equalsIgnoreCase("F#")) {
            value = 6;
        } else if(keyName.equalsIgnoreCase("C#")) {
            value = 7;
        } else {
            value = -20; 
        }
        
        return value;
    }
    
    /**
     * Adds flats to the Key in the order designated by the circle of
     * fifths, and adjusts the Key's keyValue accordingly
     * @param num The number of flats to add
     */
    public void addFlats(int num) {
        int numFlats = 0;
        
        for(int i = 0; i < num; i++) { 
            int noteToChange = -1;
            if(numFlats == 0) {
                noteToChange = 6;
                scaleSet[noteToChange].setNoteName("Bb");
                numFlats++;
            } else if(numFlats == 1) {
                noteToChange = 2;
                scaleSet[noteToChange].setNoteName("Eb");
                numFlats++;   
            } else if(numFlats == 2) {
                noteToChange = 5;
                scaleSet[noteToChange].setNoteName("Ab");
                numFlats++;  
            } else if(numFlats == 3) {
                noteToChange = 1;
                scaleSet[noteToChange].setNoteName("Db");
                numFlats++;  
            } else if(numFlats == 4) {
                noteToChange = 4;
                scaleSet[noteToChange].setNoteName("Gb");
                numFlats++;  
            } else if(numFlats == 5) {
                noteToChange = 0;
                scaleSet[noteToChange].setNoteName("Cb");
                numFlats++;  
            } else if(numFlats == 6) {
                noteToChange = 3;
                scaleSet[noteToChange].setNoteName("Fb");
                numFlats++;  
            } else {
                    
            }
                
            if(noteToChange != -1) {
                scaleSet[noteToChange].setValue(
                scaleSet[noteToChange].getValue() - 1);
            }
        }
        
        this.keyValue -= num;
    }
    
    /**
     * Adds sharps to the Key in the order designated by the circle of
     * fifths, and adjusts the Key's keyValue accordingly
     * @param num The number of sharps to add
     */
    public void addSharps(int num) {
        int numSharps = 0;
        
        for(int i = 0; i < num; i++) { 
            int noteToChange = -1;
            if(numSharps == 0) {
                noteToChange = 3;
                scaleSet[noteToChange].setNoteName("F#");
                numSharps++;
            } else if(numSharps == 1) {
                noteToChange = 0;
                scaleSet[noteToChange].setNoteName("C#");
                numSharps++;   
            } else if(numSharps == 2) {
                noteToChange = 4;
                scaleSet[noteToChange].setNoteName("G#");
                numSharps++;  
            } else if(numSharps == 3) {
                noteToChange = 1;
                scaleSet[noteToChange].setNoteName("D#");
                numSharps++;  
            } else if(numSharps == 4) {
                noteToChange = 5;
                scaleSet[noteToChange].setNoteName("A#");
                numSharps++;  
            } else if(numSharps == 5) {
                noteToChange = 2;
                scaleSet[noteToChange].setNoteName("E#");
                numSharps++;  
            } else if(numSharps == 6) {
                noteToChange = 6;
                scaleSet[noteToChange].setNoteName("B#");
                numSharps++;  
            } else {
                    
            }
                
            if(noteToChange != -1) {
                scaleSet[noteToChange].setValue(
                scaleSet[noteToChange].getValue() + 1);
            }
        }
        
        this.keyValue += num;
    }
    
    /**
     * Arranges the set of notes in the key into a scale, starting on the
     * given root. This can also be used to generate "modes," and other
     * modal scales
     * 
     * @param scale An array of Notes to be re-arranged into the new scale
     * @param rootName The name of the desired root (starting note), around
     * which the new scale will be based
     * @return the new scale, starting on the given root
     */
    public Note[] arrangeScale(Note[] scale, String rootName) {
        Note[] newScale = new Note[scale.length];
        int indexOfRoot = 0;
        for(int i = 0; i < scale.length; i++) {
            if(scale[i].getNoteName().equalsIgnoreCase(rootName)) {
                indexOfRoot = i;
                break;
            }
        }
        
        int j = 0;
        for(int i = indexOfRoot; i < scale.length; i++) {
            newScale[j] = scale[i];
            j++;
        }
        
        for(int i = 0, k = j; i < indexOfRoot; i++, k++) {
            newScale[k] = scale[i];
            newScale[k].setOctave(scale[i].getOctave() + 1);
        }
        
        return newScale;
    }
    
    /**
     * Finds the mode of given scaleType ("Ionian", "Dorian", etc.) 
     * with the same root name as the Major scale based off of the 
     * current Key
     * @param scaleType The type of scale desired ("Ionian", "Dorian", etc.) 
     * @return an array of Notes in the new mode
     */
    public Note[] turnToParallelMode(String scaleType) {
        
        Note[] scale = new Note[7];
        
        if(this.getKeyName().equalsIgnoreCase("Ionian")) {
            scale = this.getScaleSet();
        } 
        
        if(scaleType.equalsIgnoreCase("Ionian")) {
            scale = this.getScaleSet();
        } else if(scaleType.equalsIgnoreCase("Dorian")) {
            if(this.getKeyValue() - 2 < -7) {
                scale = this.getScaleSet();
                scale[0] = new Note("null");
            } else {
                Key temp = new Key(this.getKeyValue() - 2, true); 
                scale = temp.arrangeScale(temp.getScaleSet(), 
                temp.getScaleSet()[1].getNoteName());
            }
        } else if(scaleType.equalsIgnoreCase("Phrygian")) {
            if(this.getKeyValue() - 4 < -7) {
                scale = this.getScaleSet();
                scale[0] = new Note("null");
            } else {   
                Key temp = new Key(this.getKeyValue() - 4, true); 
                scale = temp.arrangeScale(temp.getScaleSet(), 
                temp.getScaleSet()[2].getNoteName());
            }
        } else if(scaleType.equalsIgnoreCase("Lydian")) {
            if(this.getKeyValue() + 1 > 7) {
                scale = this.getScaleSet();
                scale[0] = new Note("null");
            } else {    
                Key temp = new Key(this.getKeyValue() + 1, true); 
                scale = temp.arrangeScale(temp.getScaleSet(), 
                temp.getScaleSet()[3].getNoteName());
            }
        } else if(scaleType.equalsIgnoreCase("Mixolydian")) {
            if(this.getKeyValue() - 1 < -7) {
                scale = this.getScaleSet();
                scale[0] = new Note("null");
            } else {
                Key temp = new Key(this.getKeyValue() - 1, true); 
                scale = temp.arrangeScale(temp.getScaleSet(), 
                temp.getScaleSet()[4].getNoteName());
            }
        } else if(scaleType.equalsIgnoreCase("Aeolian")) {
            if(this.getKeyValue() - 3 < -7) {
                scale = this.getScaleSet();
                scale[0] = new Note("null");
            } else {
                Key temp = new Key(this.getKeyValue() - 3, true); 
                scale = temp.arrangeScale(temp.getScaleSet(), 
                temp.getScaleSet()[5].getNoteName());
                
            }
        } else if(scaleType.equalsIgnoreCase("Locrian")) {
            if(this.getKeyValue() - 5 < -7) {
                scale = this.getScaleSet();
                scale[0] = new Note("null");
            } else {
                Key temp = new Key(this.getKeyValue() - 5, true); 
                scale = temp.arrangeScale(temp.getScaleSet(), 
                temp.getScaleSet()[6].getNoteName());
            }
        }
        
        return scale;
    }
    
    /**
     * Finds the Key's name, given that the Key is Major
     * @param keyValue The integer value that represents the desired Key
     * @return the String representing the resulting Major key
     */
    public String findMajorKeyName(int keyValue) {
        String majorKeyName = "C"; 
        
        if(keyValue == -7) {
            majorKeyName = "Cb";
        } else if(keyValue == -6) {
            majorKeyName = "Gb";
        } else if(keyValue == -5) {
            majorKeyName = "Db";
        } else if(keyValue == -4) {
            majorKeyName = "Ab";
        } else if(keyValue == -3) {
            majorKeyName = "Eb";
        } else if(keyValue == -2) {
            majorKeyName = "Bb";
        } else if(keyValue == -1) {
            majorKeyName = "F";
        } else if(keyValue == 0) {
            majorKeyName = "C";
        } else if(keyValue == 1) {
            majorKeyName = "G";
        } else if(keyValue == 2) {
            majorKeyName = "D";
        } else if(keyValue == 3) {
            majorKeyName = "A";
        } else if(keyValue == 4) {
            majorKeyName = "E";
        } else if(keyValue == 5) {
            majorKeyName = "B";
        } else if(keyValue == 6) {
            majorKeyName = "F#";
        } else if(keyValue == 7) {
            majorKeyName = "C#";
        } else {
            
        }
        
        return majorKeyName;
    }
    
    /**
     * Finds the Key's name, given that the Key is minor
     * @param keyValue The integer value that represents the desired Key
     * @return the String representing the resulting minor key
     */
    public String findMinorKeyName(int keyValue) {
        Note[] temp = scaleSet;
        temp = arrangeScale(temp, findMajorKeyName(keyValue));
        return temp[6].getNoteName();
    }
    
    /**
     * Analyzes the given list of Chords by finding each Chord's Note name
     * @param chordList The list of Chords to be analyzed
     * @return the analyzed list
     */
    public ArrayList<Chord> analyze(ArrayList<Chord> chordList) {
        for(int i = 0; i < chordList.size(); i++) {
            int scaleDegree = chordList.get(i).getScaleDegree();
            if(scaleDegree == 0) {//for bug testing purposes
                System.out.print("Problem with chord " + i);
            }
            String chordName = scaleSet[scaleDegree - 1].getNoteName();
            chordList.get(i).setLetterName(chordName);
        }
        
        return chordList;
    }
    
    /**
     * @return a String representation of the given Key (String)
     */
    public String toString() {
        String key = getKeyName();
        /*
        if(isMajor) {
            key += " Major";
        } else {
            key += " minor";
        }
        */
        return key;
    }
}
