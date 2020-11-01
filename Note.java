/**
 * <h1>Note</h1>
 * A class containing information about an individual note. 
 * This includes the Note's "value," representing its 
 * position on the range between A0 and C8, "octave," the 
 * Note's octave, ranging from 0 to 8, and "noteName," the 
 * Note's letter name (A#, Bb, C, etc.)
 * 
 * @author Devon Hubert
 * @version 3.0
 */

public class Note
{
    private int value;
    private int octave;
    private String noteName;
    private boolean isRoot;
    
    /**
     * The default contructor, which sets the note's value to 0
     */
    public Note() {
        this.value = 0;  
        this.isRoot = false;
    }
    
    /**
     * @param value The note's value, from -3 to 84 (int)
     */
    public Note(int value) {
        this.value = value;
        this.isRoot = false;
    }
    
    public Note(String noteName) {
        if(noteName.equalsIgnoreCase("C")) {
            this.value = 0; 
        } else if(noteName.equalsIgnoreCase("C#") || noteName.equalsIgnoreCase("Db")) {
            this.value = 1;
        } else if(noteName.equalsIgnoreCase("D")) {
            this.value = 2;
        } else if(noteName.equalsIgnoreCase("D#") || noteName.equalsIgnoreCase("Eb")) {
            this.value = 3; 
        } else if(noteName.equalsIgnoreCase("E") || noteName.equalsIgnoreCase("Fb")) {
            this.value = 4; 
        } else if(noteName.equalsIgnoreCase("E#") || noteName.equalsIgnoreCase("F")) {
            this.value = 5; 
        } else if(noteName.equalsIgnoreCase("F#") || noteName.equalsIgnoreCase("Gb")) {
            this.value = 6; 
        } else if(noteName.equalsIgnoreCase("G")) {
            this.value = 7;
        } else if(noteName.equalsIgnoreCase("G#") || noteName.equalsIgnoreCase("Ab")) {
            this.value = 8; 
        } else if(noteName.equalsIgnoreCase("A")) {
            this.value = 9;
        } else if(noteName.equalsIgnoreCase("A#") || noteName.equalsIgnoreCase("Bb")) {
            this.value = 10; 
        } else if(noteName.equalsIgnoreCase("B") || noteName.equalsIgnoreCase("Cb")) {
            this.value = 11;
        } else if(noteName.equalsIgnoreCase("B#")) {
            this.value = 12;
        } else {
            this.value = -9;
        }
        
        this.noteName = noteName;
        this.isRoot = false;
    }
    
    public Note(String noteName, int octave) {
        if(noteName.equalsIgnoreCase("C")) {
            this.value = 0; 
        } else if(noteName.equalsIgnoreCase("C#") || noteName.equalsIgnoreCase("Db")) {
            this.value = 1;
        } else if(noteName.equalsIgnoreCase("D")) {
            this.value = 2;
        } else if(noteName.equalsIgnoreCase("D#") || noteName.equalsIgnoreCase("Eb")) {
            this.value = 3; 
        } else if(noteName.equalsIgnoreCase("E") || noteName.equalsIgnoreCase("Fb")) {
            this.value = 4; 
        } else if(noteName.equalsIgnoreCase("E#") || noteName.equalsIgnoreCase("F")) {
            this.value = 5; 
        } else if(noteName.equalsIgnoreCase("F#") || noteName.equalsIgnoreCase("Gb")) {
            this.value = 6; 
        } else if(noteName.equalsIgnoreCase("G")) {
            this.value = 7;
        } else if(noteName.equalsIgnoreCase("G#") || noteName.equalsIgnoreCase("Ab")) {
            this.value = 8; 
        } else if(noteName.equalsIgnoreCase("A")) {
            this.value = 9;
        } else if(noteName.equalsIgnoreCase("A#") || noteName.equalsIgnoreCase("Bb")) {
            this.value = 10; 
        } else if(noteName.equalsIgnoreCase("B") || noteName.equalsIgnoreCase("Cb")) {
            this.value = 11;
        } else if(noteName.equalsIgnoreCase("B#")) {
            this.value = 12;
        } else {
            this.value = -9;
        }
        
        int add = 0;
        
        if(octave == 0) {
            add = -12;
        } else if(octave == 1) {
            add = 0;
        } else if(octave == 2) {
            add = 12;
        } else if(octave == 3) {
            add = 24;
        } else if(octave == 4) {
            add = 36;
        } else if(octave == 5) {
            add = 48;
        } else if(octave == 6) {
            add = 60;
        } else if(octave == 7) {
            add = 72;
        } else if(octave == 8) {
            add = 84;
        }
        
        this.noteName = noteName;
        this.octave = octave;
        this.value = value + add;
        this.isRoot = false;
    }
    
    public Note(String noteName, int octave, boolean isRoot) {
        if(noteName.equalsIgnoreCase("C")) {
            this.value = 0; 
        } else if(noteName.equalsIgnoreCase("C#") || noteName.equalsIgnoreCase("Db")) {
            this.value = 1;
        } else if(noteName.equalsIgnoreCase("D")) {
            this.value = 2;
        } else if(noteName.equalsIgnoreCase("D#") || noteName.equalsIgnoreCase("Eb")) {
            this.value = 3; 
        } else if(noteName.equalsIgnoreCase("E") || noteName.equalsIgnoreCase("Fb")) {
            this.value = 4; 
        } else if(noteName.equalsIgnoreCase("E#") || noteName.equalsIgnoreCase("F")) {
            this.value = 5; 
        } else if(noteName.equalsIgnoreCase("F#") || noteName.equalsIgnoreCase("Gb")) {
            this.value = 6; 
        } else if(noteName.equalsIgnoreCase("G")) {
            this.value = 7;
        } else if(noteName.equalsIgnoreCase("G#") || noteName.equalsIgnoreCase("Ab")) {
            this.value = 8; 
        } else if(noteName.equalsIgnoreCase("A")) {
            this.value = 9;
        } else if(noteName.equalsIgnoreCase("A#") || noteName.equalsIgnoreCase("Bb")) {
            this.value = 10; 
        } else if(noteName.equalsIgnoreCase("B") || noteName.equalsIgnoreCase("Cb")) {
            this.value = 11;
        } else if(noteName.equalsIgnoreCase("B#")) {
            this.value = 12;
        } else {
            this.value = -9;
        }
        
        int add = 0;
        
        if(octave == 0) {
            add = -12;
        } else if(octave == 1) {
            add = 0;
        } else if(octave == 2) {
            add = 12;
        } else if(octave == 3) {
            add = 24;
        } else if(octave == 4) {
            add = 36;
        } else if(octave == 5) {
            add = 48;
        } else if(octave == 6) {
            add = 60;
        } else if(octave == 7) {
            add = 72;
        } else if(octave == 8) {
            add = 84;
        }
        
        this.noteName = noteName;
        this.octave = octave;
        this.value = value + add;
        this.isRoot = isRoot;
    }
    
    /**
     * The constructor where the note's value, and noteName
     * are known, which computes the note's octave accordingly
     * 
     * @param value The Note's value, from -3 to 84 (int)
     * @param noteName The Note's letter name (String)
     */
    public Note(int value, String noteName) {
        this.value = value;
        this.noteName = noteName;
        int octave = 0;
        if(value >= -3 && value <= 0) {
            octave = 0;
        } else if(value >= 1 && value <= 12) {
            octave = 1;
        } else if(value >= 13 && value <= 24) {
            octave = 2;
        } else if(value >= 25 && value <= 36) {
            octave = 3;
        } else if(value >= 37 && value <= 48) {
            octave = 4;
        } else if(value >= 49 && value <= 60) {
            octave = 5;
        } else if(value >= 61 && value <= 72) {
            octave = 6;
        } else if(value >= 73 && value <= 84) {
            octave = 7;
        } else {
            octave = -2;
        }
        if(noteName.equals("Cb") || noteName.equals("C")) {
            octave++;
        }
        
        this.octave = octave;
        this.isRoot = false;
    }
    
    /**
     * The constructor where the Note's value, noteName, and octave
     * are known
     * 
     * @param value The Note's value, from -3 to 84 (int)
     * @param noteName The Note's letter name (String)
     * @param octave The Note's octave, ranging from 0 to 8 (int)
     */
    public Note(int value, String noteName, int octave) {
        int add = 0;
        
        if(octave == 0) {
            add = -12;
        } else if(octave == 1) {
            add = 0;
        } else if(octave == 2) {
            add = 12;
        } else if(octave == 3) {
            add = 24;
        } else if(octave == 4) {
            add = 36;
        } else if(octave == 5) {
            add = 48;
        } else if(octave == 6) {
            add = 60;
        } else if(octave == 7) {
            add = 72;
        } else if(octave == 8) {
            add = 84;
        }
        
        this.octave = octave;
        this.value = value + add;
        this.noteName = noteName;
        this.isRoot = false;
    }
    
    /**
     * @return the Note's value, from -3 to 84 (int)
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Sets the note's value
     * @param value The Note's value. Should range from -3 to 84 (int)
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    /**
     * @return the Note's letter name (String)
     */
    public String getNoteName() {
        return noteName;
    }
    
    /**
     * Sets the Note's letter name
     * @param noteName The Note's letter name (String)
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    
    /**
     * @return the Note's octave, from 0 to 8 (int)
     */
    public int getOctave() {
        return octave;
    }
    
    /**
     * Sets the Note's octave
     * @param octave The Note's octave value, from 0 to 8 (int)
     */
    public void setOctave(int octave) {
        this.octave = octave;
        
        int add = 0;
        if(octave == 0) {
            add = -12;
        } else if(octave == 1) {
            add = 0;
        } else if(octave == 2) {
            add = 12;
        } else if(octave == 3) {
            add = 24;
        } else if(octave == 4) {
            add = 36;
        } else if(octave == 5) {
            add = 48;
        } else if(octave == 6) {
            add = 60;
        } else if(octave == 7) {
            add = 72;
        } else if(octave == 8) {
            add = 84;
        } 
        
        this.value = value + add;
    }
    
    /**
     * @return true if the Note has been designated as a root of a Chord
     */
    public boolean getIsRoot() {
        return this.isRoot;
    }
    
    /**
     * Sets the value of isRoot
     * @param isRoot A boolean that should be set to true if the Note 
     * is the root of a Chord
     */
    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }
    
    /**
     * Calculates the value of a note the given interval above the
     * given note
     * @param a The Interval to be added to the given Note
     */
    public int addInterval(Interval a) {
        return value + a.getDistance();
    }
    
    /**
     * @return a String representation of the Note object
     */
    public String toString() {
        return noteName;
    }
}
