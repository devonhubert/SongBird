import java.util.ArrayList;

/**
 * <h1>Chord</h1>
 * The class describing Chord objects, which are made up of Notes played 
 * at the same time
 * 
 * UPDATE TO ADD METHODS FINDING INFO BASED ON KEY/SCALE DEGREE (for version 3.0)
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class Chord 
{
    private String letterName = "";
    private String qualityName;
    private int scaleDegree = 0; 
    private int inversion;
    private String function; 
    
    private ArrayList<Note> notes = new ArrayList<Note>();
    private int noteNumber;
    
    /**
     * The default constructor, which sets the Chord's noteNumber to 0 (int)
     */
    public Chord() {
        this.noteNumber = 0;
    }
    
    /**
     * Creates an abstract Chord based on its scale degree, and quality
     * @param scaleDegree The the degree of the Chord, based on its Key (int)
     * @param qualityName The quality of the Chord (minor, Major, etc.) (String)
     */
    public Chord(int scaleDegree, String qualityName) {
        this.scaleDegree = scaleDegree;
        this.qualityName = qualityName;
    }
    
    /**
     * Creates a Chord from an ArrayList of given Notes (Should analyze() to set other values)
     * @param chordNotes An ArrayList of all the Notes in a Chord
     */
    public Chord(ArrayList<Note> chordNotes) {
        this.noteNumber = chordNotes.size();
        
        for(int i = 0; i < noteNumber; i++) {
            Note temp = chordNotes.get(i);
            notes.add(temp);
        }
    }
    
    /**
     * Creates a Chord from a starting Note, and an ArrayList of Intervals 
     * (Should analyze() to set other values)
     * @param startingNote The lowest Note in the Chord, or the Note at which intervals
     * start being added
     * @param chordIntervals An ArrayList of all the Intervals to be added to the 
     * startingNote to create the Chord
     */
    public Chord(Note startingNote, ArrayList<Interval> chordIntervals) {
        noteNumber = chordIntervals.size() + 1;
        notes.add(startingNote);
        
        Note prevNote = startingNote;
        
        for(int i = 0; i < chordIntervals.size(); i++) {
            Note temp = new Note();
            temp.setValue(prevNote.addInterval(chordIntervals.get(i)));
            notes.add(temp);
            prevNote = temp;
        }
    }
    
    /**
     * Sets the Chord's letter name 
     * @param letterName The Chord's letter name, which is an indication of 
     * the Chord's root (String)
     */
    public void setLetterName(String letterName) {
        this.letterName = letterName;
    }
    
    /**
     * @return the Chord's letter name, an indication of the Chord's root (String)
     */
    public String getLetterName() {
        return letterName;
    }
    
    /**
     * Sets the Chord's quality (Major, minor, etc.) 
     * @param qualityName The name of the Chord's quality (String)
     */
    public void setQualityName(String qualityName) {
        this.qualityName = qualityName;
    }
    
    /**
     * @return the name of the Chord's quality (Major, minor, etc.) (String)
     */
    public String getQualityName() {
        return qualityName;
    }
    
    /**
     * Sets the Chord's scale degree
     * @param degree The scale degree of the Chord (int)
     */
    public void setScaleDegree(int degree) {
        this.scaleDegree = degree;
    }
    
    /**
     * Sets the Chord's notes
     * @param notes All notes in the Chord (ArrayList<Note>)
     */
    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
    
    /**
     * @return an ArrayList of all Notes in the Chord
     */
    public ArrayList<Note> getNotes() {
        return this.notes;
    }
    
    /**
     * @return the Chord's scale degree (int)
     */
    public int getScaleDegree() {
        return scaleDegree;
    }
    
    /**
     * Finds a Chord's intervals based on what type of chord it is
     * @param chordType The type of chord ("Major", "minor", etc.) (String)
     * @return an array of Intervals
     */
    public Interval[] findIntervalsFromChordType(String chordType) {
        ArrayList<Interval> tempList = new ArrayList<Interval>();
        
        if(chordType.equalsIgnoreCase("Major")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Perfect", 5));
        } else if(chordType.equalsIgnoreCase("minor")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("Perfect", 5));
        } else if(chordType.equalsIgnoreCase("diminished")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("diminished", 5));
        } else if(chordType.equalsIgnoreCase("Augmented")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Augmented", 5));
        } else if(chordType.equalsIgnoreCase("sus2")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 2));
            tempList.add(new Interval("Perfect", 5));
        } else if(chordType.equalsIgnoreCase("sus4")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Perfect", 4));
            tempList.add(new Interval("Perfect", 5));
        } else if(chordType.equalsIgnoreCase("Major 7")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("Major", 7));
        } else if(chordType.equalsIgnoreCase("Major add9")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("Major", 9));
        } else if(chordType.equalsIgnoreCase("minor add9")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("minor", 9));
        } else if(chordType.equalsIgnoreCase("minor 7")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("minor", 7));
        } else if(chordType.equalsIgnoreCase("diminished 7") || chordType.equalsIgnoreCase("dim 7")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("diminished", 5));
            tempList.add(new Interval("Major", 6));
        } else if(chordType.equalsIgnoreCase("-7 b5")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("diminished", 5));
            tempList.add(new Interval("minor", 7));
        } else if(chordType.equalsIgnoreCase("Dominant 7") || chordType.equalsIgnoreCase("Dom 7")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("minor", 7));
        } else if(chordType.equalsIgnoreCase("Major 6")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("Major", 6));
        } else if(chordType.equalsIgnoreCase("minor 6")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("minor", 6));
        } else if(chordType.equalsIgnoreCase("Major 9")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("Major", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("Major", 7));
            tempList.add(new Interval("Major", 9));
        } else if(chordType.equalsIgnoreCase("minor 9")) {
            tempList.add(new Interval("Perfect", 1));
            tempList.add(new Interval("minor", 3));
            tempList.add(new Interval("Perfect", 5));
            tempList.add(new Interval("minor", 7));
            tempList.add(new Interval("minor", 9));
        } else {
            
        }
        
        
        Interval[] finalList = new Interval[tempList.size()];
        for(int i = 0; i < tempList.size(); i++) {
            finalList[i] = tempList.get(i);
        }
        
        return finalList;
    }
    
    /**
     * Creates an array of note names for a Chord, based on its intervals and rootName
     * @param intervals An Interval array of intervals in the Chord 
     * @param rootName The name of the Chord's root
     * @return a String array of the names of all the Notes in the Chord
     */
    public String[] printNotes (Interval[] intervals, String rootName){ 
        int[] intervalDegreeTypes = new int[intervals.length]; 
        for(int i = 0; i < intervalDegreeTypes.length; i++) {
            intervalDegreeTypes[i] = intervals[i].getDegreeValue();
        }
        
        Interval temp = new Interval();
        
        String[] finalNotes = new String[intervals.length]; 
        for(int i = 0; i < finalNotes.length; i++) {
            if(intervals[i].getDistance() > 12) {
                intervals[i].setDistance(intervals[i].getDistance() - 12);
            }
            
            String tempString = "";
            char rawNote = temp.findLetterName(rootName, intervalDegreeTypes[i]);
            
            String[] operators = {"b", "", "#"};
            String tempNote = "";
            boolean found = false;
            
            for(int j = 0; j < 3; j++) {
                tempNote = rawNote + operators[j]; 
                
                int add = 0;
                if(new Note(rootName).getValue() > new Note(tempNote).getValue()) {
                    add = 1;
                }
                
                Interval tempInterval = new Interval(new Note(rootName, 0), new Note(tempNote, add));
                if(tempInterval.getDistance() == intervals[i].getDistance()) {
                    found = true;
                    break;
                }
            }
            if(found) {
                tempString = tempNote;
            } else {
                int try1;
                if(intervalDegreeTypes[i] - 1 == 0) {
                    try1 = intervalDegreeTypes[i] + 6;
                } else {
                    try1 = intervalDegreeTypes[i] - 1;
                }
                
                String below = "" + temp.findLetterName(rootName, try1);
                int add2 = 0;
                if(new Note(rootName).getValue() > new Note(below).getValue()) {
                    add2 = 1;
                }
                Interval tempInterval2 = new Interval(new Note(rootName, 0), new Note(below, add2));
                
                int try2;
                if(intervalDegreeTypes[i] + 1 == 14) {
                    try2 = intervalDegreeTypes[i] + 5;
                } else {
                    try2 = intervalDegreeTypes[i] + 1;
                }
                
                String above = "" + temp.findLetterName(rootName, try2);
                int add3 = 0;
                if(new Note(rootName).getValue() > new Note(above).getValue()) {
                    add3 = 1;
                }
                Interval tempInterval3 = new Interval(new Note(rootName, 0), new Note(above, add3));
                
                if(tempInterval2.getDistance() == intervals[i].getDistance()) {
                    tempString = below;
                } else if(tempInterval3.getDistance() == intervals[i].getDistance()) {
                    tempString = above;
                } else {
                    tempString = "null";
                }
            }
            
            finalNotes[i] = tempString;
        }
        
        return finalNotes;
    }
    
    /**
     * Prints the value of every Note in the Chord
     */
    public void printNoteValues() {
        for (int i = 0; i < notes.size(); i++) {
            Note temp = notes.get(i);
            System.out.print(temp.getValue());
                if (i == notes.size() - 1) {
                    break;
                } else {
                    System.out.print(", ");
                }
        }
        
        System.out.println("");
    }
    
    /**
     * Prints every Interval that exists between every pair of two adjacent Notes 
     * in the Chord
     */
    public void printIntervals() {
        for (int i = 0; i < notes.size() - 1; i++) {
            Note temp1 = notes.get(i);
            Note temp2 = notes.get(i + 1);
            
            Interval interval = new Interval(temp1, temp2);
            System.out.print(interval.computePossible(interval.getDistance()));
            
                if (i == notes.size() - 2) {
                    break;
                } else {
                    System.out.print(", ");
                }
        }
        
        System.out.println("");
    }
    
    /*
    public void getType() {
        this.letterName = notes.get(0).getNoteName();
        System.out.println(letterName);
    }
    */
    
    /**
     * @return a String representation of the given Chord
     */
    public String toString() {
        String chordName = "";
        if(!this.letterName.equals("")) {
            chordName = letterName + " " + qualityName;
        } else if(this.scaleDegree != 0) {
            chordName = scaleDegree + " " + qualityName;
        } else {
            chordName = "Not Yet Defined";
        }
        
        return chordName;
    }
}
