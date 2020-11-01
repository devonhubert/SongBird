import java.util.ArrayList;

/**
 * <h1>Progression</h1>
 * Creates Chord progressions based on the song's Genre, with varying structure and Chord complexity
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class Progression
{
    private Key key;
    private boolean simplify;
    private ArrayList<Chord> verseChords;
    private ArrayList<Chord> prechorusChords;
    private ArrayList<Chord> chorusChords;
    private ArrayList<Chord> bridgeChords;
    private ArrayList<String> structure;
    private Genre genre;
    
    /**
     * The default constructor, which creates a simple pop progression in the Key of C Major
     */
    public Progression() {
        this.simplify = true;
        this.key = new Key(0, true);
        this.verseChords = makeProgression(key, 4);
        this.prechorusChords = makeProgression(key, 4);
        this.chorusChords = makeProgression(key, 4);
        this.bridgeChords = makeProgression(key, 4);
        
        String[] temp = {"chorus", "verse", "prechorus", "chorus", "verse", "prechorus", "chorus",
        "bridge", "chorus", "chorus"};
        for(int i = 0; i < temp.length; i++) {
            this.structure.add(temp[i]);
        }
    }
    
    /**
     * The constructor that creates chord progressions for each section 
     * of the song, based on the given Genre
     * @param genre The song's genre (Rock, Pop, Classical, etc.);
     */
    public Progression(Genre genre) {
        this.genre = genre;
        this.simplify = genre.getSimplify();
        Key randomKey = new Key(randomKey(), true);
        this.key = randomKey;
        this.structure = genre.getStructure();
        
        if(genre.getVerseChordNumber() > 0) {
            this.verseChords = makeProgression(randomKey, genre.getVerseChordNumber());
        } 
        
        if(genre.getPrechorusChordNumber() > 0) {
            this.prechorusChords = makeProgression(randomKey, genre.getPrechorusChordNumber());
        }
        
        if(genre.getChorusChordNumber() > 0) {
            this.chorusChords = makeProgression(randomKey, genre.getChorusChordNumber());
        }
        
        if(genre.getBridgeChordNumber() > 0) {
            this.bridgeChords = makeProgression(randomKey, genre.getBridgeChordNumber());
        }
    }
    
    /**
     * @return an ArrayList of all the Chords in the verse of the 
     * current Progression
     */
    public ArrayList<Chord> getVerseChords() {
        return this.verseChords;
    }
    
    /**
     * @return an ArrayList of all the Chords in the pre-chorus of the 
     * current Progression
     */
    public ArrayList<Chord> getPrechorusChords() {
        return this.prechorusChords;
    }
    
    /**
     * @return an ArrayList of all the Chords in the chorus of the 
     * current Progression
     */
    public ArrayList<Chord> getChorusChords() {
        return this.chorusChords;
    }
    
    /**
     * @return an ArrayList of all the Chords in the bridge of the 
     * current Progression
     */
    public ArrayList<Chord> getBridgeChords() {
        return this.bridgeChords;
    }
    
    /**
     * @return the Genre of the current Progression
     */
    public Genre getGenre() {
        return this.genre;
    }
    
    public Key getKey() {
        return this.key;
    }
    
    /**
     * Finds and sets the Notes in every Chord in a Progression based on the 
     * Chord type and root name of each Chord
     * @param chordList The ArrayList of Chords to be filled with Notes
     * @return the filled ArrayList of Chords
     */
    public ArrayList<Chord> fillNotes(ArrayList<Chord> chordList) {
        if(chordList == null) {
            return chordList;
        }
        
        ArrayList<Chord> allChords = new ArrayList<Chord>();
        
        for(int i = 0; i < chordList.size(); i++) {//for every chord in chord list
            String chordType = chordList.get(i).getQualityName();
            String rootName = chordList.get(i).getLetterName();
            
            Chord tempChord = new Chord(); 
            tempChord.setQualityName(chordType);
            tempChord.setLetterName(rootName);
            
            String[] tempNoteNames = tempChord.printNotes(tempChord.findIntervalsFromChordType(
            chordType), rootName);
            
            ArrayList<Note> tempNotesList = new ArrayList<Note>();
            int prevNoteValue = -100;
            
            for(int j = 0; j < tempNoteNames.length; j++) {
                Note tempNote;
                if(j == 0) {
                    tempNote = new Note(tempNoteNames[j], 0, true);
                } else {
                    tempNote = new Note(tempNoteNames[j], 0);
                }
                if(prevNoteValue > tempNote.getValue()) {
                    tempNote = new Note(tempNoteNames[j], 1);
                }
                
                tempNotesList.add(tempNote);
                prevNoteValue = tempNote.getValue();
            }
            
            tempChord.setNotes(tempNotesList);
            
            allChords.add(tempChord);
        }
        
        return allChords;
    }
    
    /**
     * Rearranges the notes of each Chord in a progression so that the
     * movement between Notes occurs with maximum efficiency (the least 
     * movement)
     * @param chordList The original ArrayList of Chords that make up the Progression
     * @return the updated ArrayList of Chords
     */
    public ArrayList<Chord> voiceLead(ArrayList<Chord> chordList) {
        if(chordList == null) {
            return chordList;
        }
        
        ArrayList<Chord> allChords = fillNotes(chordList);
        
        allChords.get(0).setNotes(makeFour(allChords.get(0).getNotes()));
        
        for(int k = 1; k < allChords.size(); k++) {//for every chord in the list after the first
            //get an array of notes from the previous chord
            
            ArrayList<Note> previousArray = allChords.get(k - 1).getNotes();
            
            //get the array of notes for the current chord
            ArrayList<Note> currentArray = allChords.get(k).getNotes();
            
            //new blank array to replace current array
            ArrayList<Note> tempArray = new ArrayList<Note>();
            
            currentArray = makeFour(currentArray);
            
            ArrayList<int[]> permutations = new ArrayList<int[]>();
            int[] perm1 = {0, 1, 2, 3};
            permutations.add(perm1);
            int[] perm2 = {0, 1, 3, 2};
            permutations.add(perm2);
            int[] perm3 = {0, 2, 1, 3};
            permutations.add(perm3);
            int[] perm4 = {0, 2, 3, 1};
            permutations.add(perm4);
            int[] perm5 = {0, 3, 1, 2};
            permutations.add(perm5);
            int[] perm6 = {0, 3, 2, 1};
            permutations.add(perm6);
            int[] perm7 = {1, 0, 2, 3};
            permutations.add(perm7);
            int[] perm8 = {1, 0, 3, 2};
            permutations.add(perm8);
            int[] perm9 = {1, 2, 0, 3};
            permutations.add(perm9);
            int[] perm10 = {1, 2, 3, 0};
            permutations.add(perm10);
            int[] perm11 = {1, 3, 0, 2};
            permutations.add(perm11);
            int[] perm12 = {1, 3, 2, 0};
            permutations.add(perm12);
            int[] perm13 = {2, 1, 0, 3};
            permutations.add(perm13);
            int[] perm14 = {2, 1, 3, 0};
            permutations.add(perm14);
            int[] perm15 = {2, 0, 1, 3};
            permutations.add(perm15);
            int[] perm16 = {2, 0, 3, 1};
            permutations.add(perm16);
            int[] perm17 = {2, 3, 1, 0};
            permutations.add(perm17);
            int[] perm18 = {2, 3, 0, 1};
            permutations.add(perm18);
            int[] perm19 = {3, 1, 2, 0};
            permutations.add(perm19);
            int[] perm20 = {3, 1, 0, 2};
            permutations.add(perm20);
            int[] perm21 = {3, 2, 1, 0};
            permutations.add(perm21);
            int[] perm22 = {3, 2, 0, 1};
            permutations.add(perm22);
            int[] perm23 = {3, 0, 1, 2};
            permutations.add(perm23);
            int[] perm24 = {3, 0, 2, 1};
            permutations.add(perm24);
            
            ArrayList<Note> bestNotes = currentArray;
            int smallestDistance = getChordDistance(previousArray, currentArray);
            for(int i = 0; i < permutations.size(); i++) {
                int[] tempInts = permutations.get(i);
                //initialize copy of current array
                ArrayList<Note> copyArray = new ArrayList<Note>();
                
                copyArray.add(currentArray.get(tempInts[0]));
                copyArray.add(currentArray.get(tempInts[1]));
                copyArray.add(currentArray.get(tempInts[2]));
                copyArray.add(currentArray.get(tempInts[3]));
                
                int prevNoteValue = -100;
                for(int j = 0; j < 4; j++) {
                    copyArray.set(j, new Note(copyArray.get(j).getNoteName(), 0));
                    if(copyArray.get(j).getValue() < prevNoteValue) {
                        copyArray.set(j, new Note(copyArray.get(j).getNoteName(), 1));
                    }
                    
                    prevNoteValue = copyArray.get(j).getValue();
                }
                
                int testDistance = getChordDistance(previousArray, copyArray);
                if(testDistance < smallestDistance) {
                    smallestDistance = testDistance;
                    bestNotes = copyArray;
                }
            }
            
            String prevName = bestNotes.get(0).getNoteName();
            for(int i = 1; i < bestNotes.size(); i++) {
                if(bestNotes.get(i).getNoteName().equalsIgnoreCase(prevName)) {
                    bestNotes.remove(i - 1);
                    i--;
                } 
                prevName = bestNotes.get(i).getNoteName();
            }
            
            allChords.get(k).setNotes(bestNotes);
        }
        
        return allChords;
    }
    
    /**
     * Calculates the total distance between all corresponding Chord Notes
     * 
     * @param chordList1 An ArrayList of Notes that represents the first Chord
     * @param chordList2 An ArrayList of Notes that represents the second Chord
     * 
     * @return the total distance value (int)
     */
    public int getChordDistance(ArrayList<Note> chordList1, ArrayList<Note> chordList2) {
        //ArrayLists must be same size
        
        int totalDistance = 0;
        for(int i = 0; i < chordList1.size(); i++) {
            Interval tempInterval = new Interval(chordList1.get(i), chordList2.get(i));
            totalDistance += tempInterval.getDistance();
        }
        
        return totalDistance;
    }
    
    /**
     * Converts a Chord with 3 or 5 notes into a Chord with 4 notes for voice leading
     * 
     * @param notes An ArrayList of Notes that represents the current Chord
     * @return the adjusted ArrayList of Notes
     */
    public ArrayList<Note> makeFour(ArrayList<Note> notes) {
        if(notes.size() == 4) {
            return notes;
        } else if(notes.size() == 3) {
            Note root = new Note();
            for(int i = 0; i < 3; i++) {
                if(notes.get(i).getIsRoot() == true) {
                    root = notes.get(i);
                }
            }
            notes.add(new Note(root.getNoteName(), root.getOctave() + 1));
        } else if(notes.size() == 5) {
            Note root = new Note();
            for(int i = 0; i < 5; i++) {
                if(notes.get(i).getIsRoot() == true) {
                    root = notes.get(i);
                }
            }
            
            int indexOfFifth = -1;
            for(int i = 1; i < 5; i++) {
                Interval testInterval = new Interval(root, notes.get(i));
                if(testInterval.getDistance() == 7) {
                    indexOfFifth = i;
                }
            }
            
            if(indexOfFifth == -1) {
                for(int i = 1; i < 5; i++) {
                    Interval testInterval = new Interval(root, notes.get(i));
                    if(testInterval.getDistance() == 6) {
                        indexOfFifth = i;
                    }
                }
            }
            
            if(indexOfFifth == -1) {
                for(int i = 1; i < 5; i++) {
                    Interval testInterval = new Interval(root, notes.get(i));
                    if(testInterval.getDistance() == 8) {
                        indexOfFifth = i;
                    }
                }
            }
            
            notes.remove(indexOfFifth);
        }
        
        return notes;
    }
    
    /**
     * Changes the key of the current progression, and adjusts its Chords
     * accordingly
     * @param key The destination Key 
     */
    public void changeKey(Key key) {
        if(this.verseChords != null) {
            this.verseChords = key.analyze(this.verseChords);
        }
        if(this.prechorusChords != null) {
            this.prechorusChords = key.analyze(this.prechorusChords);
        }
        if(this.chorusChords != null) {
            this.chorusChords = key.analyze(this.chorusChords);
        }
        if(this.bridgeChords != null) {
            this.bridgeChords = key.analyze(this.bridgeChords);
        }
        
        this.key = key;
    }
    
    /**
     * Combines methods from Progression to form a set of Chords
     * @param key The song's key 
     * @param chordNumber The number of Chords in the specified section of the song
     * @return an analyzed set of Chords
     */
    public ArrayList<Chord> makeProgression(Key key, int chordNumber) {
        return key.analyze(findChords(findFunctions(new ArrayList<String>(), chordNumber)));
    }
    
    /**
     * Creates a functionList of Chord functions, based on common song patterns and music theory 
     * ("T" for "Tonic", "SD" for "Subdominant", "D" for "Dominant", "D7" for Dominant that 
     * always resolves up a perfect fourth, and "R" for the result of Dominant resolution up 
     * a fourth)
     * 
     * @param functionList The empty ArrayList to be filled with function Strings
     * @param chordNumber The number of Chords desired in the functionList
     * @return the functionList, an ArrayList of Strings representing Chord functions
     */
    public ArrayList<String> findFunctions(ArrayList<String> functionList, int chordNumber) {
        String prevChord = "T"; 
        for(int i = 0; i < chordNumber; i++) {
            boolean isLast = false;
            
            if(i == (chordNumber - 1)) {
                functionList.add(prevChord);
                prevChord = "T";
                break;
            }
            if(i == (chordNumber - 2)) {
                isLast = true;
            }
            
            if(prevChord.equalsIgnoreCase("T")) {
                functionList.add(prevChord);
                if(!isLast) {
                    String[] options = {"T", "SD", "D", "SD", "D7"};
                    int rand = (int) Math.floor(Math.random() * 5);
                    prevChord = options[rand];
                } else {
                    String[] options = {"T", "SD", "D", "SD", "T"};
                    int rand = (int) Math.floor(Math.random() * 5);
                    prevChord = options[rand];
                    isLast = false;
                }
                
            } else if(prevChord.equalsIgnoreCase("SD")) {
                functionList.add(prevChord);
                if(!isLast) {
                    String[] options = {"T", "D", "T", "D", "SD", "D7"};
                    int rand = (int) Math.floor(Math.random() * 6);
                    prevChord = options[rand];
                } else {
                    String[] options = {"T", "D", "T", "D", "SD", "SD"};
                    int rand = (int) Math.floor(Math.random() * 6);
                    prevChord = options[rand];
                    isLast = false;
                }
                
            } else if(prevChord.equalsIgnoreCase("D")) {
                functionList.add(prevChord);
                if(!isLast) {
                    String[] options = {"T", "T", "T", "T", "SD", "D", "D7"};
                    int rand = (int) Math.floor(Math.random() * 7);
                    prevChord = options[rand];
                } else {
                    String[] options = {"T", "T", "T", "T", "SD", "D", "T"};
                    int rand = (int) Math.floor(Math.random() * 7);
                    prevChord = options[rand];
                    isLast = false;
                }
                
            } else if(prevChord.equalsIgnoreCase("D7")) {
                functionList.add(prevChord); 
                prevChord = "R";
            } else if(prevChord.equalsIgnoreCase("R")) {
                functionList.add(prevChord); 
                String[] options = {"R", "T", "T", "SD", "D"};
                int rand = (int) Math.floor(Math.random() * 5);
                prevChord = options[rand];
            } else {
                
            }
        }
        
        return functionList;
    }
    
    /**
     * Creates a "random" key, based on the complexity of the given genre
     * @return an integer which represents a Key, and can be translated into one
     */
    public int randomKey() {
        int keyNum;
        if(simplify) {
            int[] keyOptions = {4, 0, 1, 3, 4, 3, 2, 1, 2, 1, 
            -2, -2, -2, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 
            1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4};
            int randomNumber = (int)Math.floor((Math.random() * 40));
            keyNum = keyOptions[randomNumber];
        } else {
            int[] keyOptions = {-7, -6, -5, -4, -4, -3, -3, -3, -2, -2, 
            -2, -2, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 
            2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7};
            int randomNumber = (int)Math.floor((Math.random() * 40));
            keyNum = keyOptions[randomNumber];
        }
        
        return keyNum;
    }
    
    /**
     * Finds abstract Chord types based on the given list of functions ("T" for "Tonic", etc.)
     * @param functionList A list of Chord functions to be interpreted
     * @return an ArrayList of abstract Chords that fit the given functions
     */
    public ArrayList<Chord> findChords(ArrayList<String> functionList) {
        ArrayList<Chord> chordChoice = new ArrayList<Chord>();
        
        for(int i = 0; i < functionList.size(); i++) {
            String function = functionList.get(i);
            int scaleDegree = 0;
            String qualityName = "";
            
            if(function.equalsIgnoreCase("T")) {
                int[] degrees = {1, 1, 1, 3, 6, 6};
                int rn = (int) Math.floor(Math.random() * 6);
                scaleDegree = degrees[rn];
                
                    int rn2;
                    if(scaleDegree == 1) {
                        if(simplify) { 
                            rn2 = (int) Math.floor(Math.random() * 10);
                            String [] qualities = {"Major", "Major", "Major", "Major 7", 
                            "Major", "Major", "Major", "Major 7", "Major", "minor"};
                            qualityName = qualities[rn2];  
                        } else {
                            rn2 = (int) Math.floor(Math.random() * 16);
                            String [] qualities = {"Major", "sus2", "Major 6", "Major 7", 
                            "Major 9", "Major", "Major 7", "Major", "sus2", "Major 6", 
                            "Major 7", "Major 9", "Major", "Major 7", "minor 7", "minor"};
                            qualityName = qualities[rn2];  
                    }
                         
                    } else if(scaleDegree == 3) {
                        if(simplify) { 
                            rn2 = (int) Math.floor(Math.random() * 10);
                            String [] qualities = {"minor", "minor", "minor", "minor 7", 
                            "minor", "minor", "minor 7", "minor 7", "minor", "Major"};
                            qualityName = qualities[rn2];  
                        } else { 
                            rn2 = (int) Math.floor(Math.random() * 5);
                            String[] qualities = {"minor", "minor 6", "minor 7", "minor", "minor 7"};
                            qualityName = qualities[rn2];
                        }
                    } else if(scaleDegree == 6) {
                        if(simplify) { 
                            rn2 = (int) Math.floor(Math.random() * 10);
                            String [] qualities = {"minor", "minor", "minor", "minor 7", 
                            "minor", "minor", "minor 7", "minor 7", "minor", "Major"};
                            qualityName = qualities[rn2];  
                        } else { 
                            rn2 = (int) Math.floor(Math.random() * 16);
                            String[] qualities = {"minor", "sus2", "minor 6", "minor 7", "minor 9",
                            "minor", "minor 7", "minor", "sus2", "minor 6", "minor 7", "minor 9",
                            "minor", "minor 7", "Major", "Major 7"};
                            qualityName = qualities[rn2];
                        }
                    } else {
                        qualityName = "null";
                    }
                
            } else if(function.equalsIgnoreCase("SD")) {
                int[] degrees = {2, 4, 4};
                int rn = (int) Math.floor(Math.random() * 3);
                scaleDegree = degrees[rn];
                
                    int rn2; 
                    if(scaleDegree == 2) {
                        if(simplify) { 
                            rn2 = (int) Math.floor(Math.random() * 10);
                            String [] qualities = {"minor", "minor", "minor", "minor 7", 
                            "minor", "minor 7", "minor 7", "minor 7", "minor", "minor"};
                            qualityName = qualities[rn2];  
                        } else { 
                            rn2 = (int) Math.floor(Math.random() * 17);
                            String[] qualities = {"minor", "sus2", "minor 6", "minor 7", "minor 9",
                            "minor", "minor 7", "minor", "sus2", "minor 6", "minor 7", "minor 9",
                            "minor", "minor 7", "Major", "Major 7", "Major add9"};
                            qualityName = qualities[rn2]; 
                        }
                    } else if(scaleDegree == 4) {
                        if(simplify) { 
                            rn2 = (int) Math.floor(Math.random() * 10);
                            String [] qualities = {"Major", "Major", "Major", "Major 7", 
                            "Major", "Major", "Major", "Major 7", "minor 7", "minor"};
                            qualityName = qualities[rn2];  
                        } else {
                            rn2 = (int) Math.floor(Math.random() * 17);
                            String[] qualities = {"Major", "sus2", "Major 6", "Major 7", "Major 9",
                            "Major", "Major 7", "Major", "sus2", "Major 6", "Major 7", "Major 9",
                            "Major", "Major 7", "minor", "minor 7", "minor add9"};
                            qualityName = qualities[rn2]; 
                        }
                    } else {
                        qualityName = "null";
                    }
                
            } else if(function.equalsIgnoreCase("D")) {
                int[] degrees = {5, 5, 5, 7};
                int rn = (int) Math.floor(Math.random() * 4);
                scaleDegree = degrees[rn];
                
                    int rn2;
                    if(scaleDegree == 5) {
                        if(simplify) { 
                            rn2 = (int) Math.floor(Math.random() * 10);
                            String [] qualities = {"Major", "Major", "Major", "Dom 7", 
                            "Dom 7", "Major", "Major", "Major", "Dom 7", "Major"};
                            qualityName = qualities[rn2];  
                        } else {
                            rn2 = (int) Math.floor(Math.random() * 6);
                            String[] qualities = {"Major", "Major", "sus2", "Dom 7", "Dom 7", 
                            "Dom 7"};
                            qualityName = qualities[rn2];
                        }
                    } else if(scaleDegree == 7) {
                        if(simplify) {
                            qualityName = "-7 b5";
                        } else { 
                            rn2 = (int) Math.floor(Math.random() * 3);
                            String[] qualities = {"-7 b5", "dim 7", "-7 b5"};
                            qualityName = qualities[rn2]; 
                        }
                    } else {
                        qualityName = "null";
                    }
                
            } else if (function.equalsIgnoreCase("D7")) {
                int[] degrees = {1, 2, 3, 5, 6};
                int rn = (int) Math.floor(Math.random() * 5);
                scaleDegree = degrees[rn];
                
                qualityName = "Dom 7";
            } else if (function.equalsIgnoreCase("R")) {
                int prevScaleDegree = chordChoice.get(chordChoice.size() - 1).getScaleDegree();
                
                if(prevScaleDegree == 1) {
                    scaleDegree = 4; 
                } else if(prevScaleDegree == 2) {
                    scaleDegree = 5; 
                } else if(prevScaleDegree == 3) {
                    scaleDegree = 6; 
                } else if(prevScaleDegree == 5) {
                    scaleDegree = 1; 
                } else if(prevScaleDegree == 6) {
                    scaleDegree = 2; 
                } else {
                    scaleDegree = 1;
                    //check
                }
                
                if(functionList.size() > (i + 1)) {
                    if(functionList.get(i + 1).equalsIgnoreCase("R")) {
                        qualityName = "Dom 7";
                    } else {
                        int rn2;
                        if(scaleDegree == 1) {
                            if(simplify) { 
                                rn2 = (int) Math.floor(Math.random() * 10);
                                String [] qualities = {"Major", "Major", "Major", "Major 7", 
                                "Major", "Major", "Major", "Major 7", "Major", "minor"};
                                qualityName = qualities[rn2];  
                            } else {
                                rn2 = (int) Math.floor(Math.random() * 7);
                                String [] qualities = {"Major", "sus2", "Major 6", "Major 7", 
                                "Major 9", "Major", "Major 7"};
                                qualityName = qualities[rn2]; 
                            }
                        } else if(scaleDegree == 2) {
                            if(simplify) { 
                                rn2 = (int) Math.floor(Math.random() * 10);
                                String [] qualities = {"minor", "minor", "minor", "minor 7", 
                                "minor", "minor", "minor 7", "minor 7", "minor", "Major"};
                                qualityName = qualities[rn2];  
                            } else { 
                                rn2 = (int) Math.floor(Math.random() * 7);
                                String[] qualities = {"minor", "sus2", "minor 6", "minor 7", 
                                "minor 9", "minor", "minor 7"};
                                qualityName = qualities[rn2]; 
                            }
                        } else if(scaleDegree == 4) {
                            if(simplify) { 
                                rn2 = (int) Math.floor(Math.random() * 10);
                                String [] qualities = {"Major", "Major", "Major", "Major 7", 
                                "Major", "Major", "Major", "Major 7", "Major", "minor"};
                                qualityName = qualities[rn2];  
                            } else {
                                rn2 = (int) Math.floor(Math.random() * 7);
                                String[] qualities = {"Major", "sus2", "Major 6", "Major 7", 
                                "Major 9", "Major", "Major 7"};
                                qualityName = qualities[rn2]; 
                            }
                        } else if(scaleDegree == 5) {
                            if(simplify) { 
                                rn2 = (int) Math.floor(Math.random() * 10);
                                String [] qualities = {"Major", "Major", "Major", "Dom 7", 
                                "Dom 7", "Major", "Major", "Major", "Dom 7", "Major"};
                                qualityName = qualities[rn2];  
                            } else {
                                rn2 = (int) Math.floor(Math.random() * 6);
                                String[] qualities = {"Major", "Major", "sus2", "Dom 7", 
                                "Dom 7", "Dom 7"};
                                qualityName = qualities[rn2]; 
                            }
                        } else if(scaleDegree == 6) {
                            if(simplify) { 
                                rn2 = (int) Math.floor(Math.random() * 10);
                                String [] qualities = {"minor", "minor", "minor", "minor 7", 
                                "minor", "minor", "minor 7", "minor 7", "minor", "Major"};
                                qualityName = qualities[rn2];  
                            } else { 
                                rn2 = (int) Math.floor(Math.random() * 7);
                                String[] qualities = {"minor", "sus2", "minor 6", "minor 7", "minor 9",
                                "minor", "minor 7"};
                                qualityName = qualities[rn2];
                            }
                        } else {
                            qualityName = "null";
                        }
                    }
                } else {
                        int rn2;
                        if(scaleDegree == 1) {
                            rn2 = (int) Math.floor(Math.random() * 7);
                            String [] qualities = {"Major", "sus2", "Major 6", "Major 7", "Major 9",
                            "Major", "Major 7"};
                            qualityName = qualities[rn2];   
                        } else if(scaleDegree == 2) {
                            rn2 = (int) Math.floor(Math.random() * 7);
                            String[] qualities = {"minor", "sus2", "minor 6", "minor 7", "minor 9",
                            "minor", "minor 7"};
                            qualityName = qualities[rn2]; 
                        } else if(scaleDegree == 4) {
                            rn2 = (int) Math.floor(Math.random() * 7);
                            String[] qualities = {"Major", "sus2", "Major 6", "Major 7", "Major 9",
                            "Major", "Major 7"};
                            qualityName = qualities[rn2]; 
                        } else if(scaleDegree == 5) {
                            rn2 = (int) Math.floor(Math.random() * 6);
                            String[] qualities = {"Major", "Major", "sus2", "Dom 7", "Dom 7", 
                            "Dom 7"};
                            qualityName = qualities[rn2]; 
                        } else if(scaleDegree == 6) {
                            rn2 = (int) Math.floor(Math.random() * 7);
                            String[] qualities = {"minor", "sus2", "minor 6", "minor 7", "minor 9",
                            "minor", "minor 7"};
                            qualityName = qualities[rn2];
                        } else {
                            qualityName = "null";
                        }
                }
            } else {
                scaleDegree = -9;
                qualityName = "null";
            }
            
            chordChoice.add(new Chord(scaleDegree, qualityName));
        }
        
        return chordChoice;
    }
    
    /**
     * @return a String representation of the Progressions of a song, arranged in the order
     * specified by the song's Genre
     */
    public String toString() {
        String song = "Genre: " + genre.getGenreName() + "\nSong Key: " + key + "\n";
        int verseCount = 0;
        int prechorusCount = 0;
        int chorusCount = 0;
        int bridgeCount = 0;
        
        for(int i = 0; i < structure.size(); i++) {
            String temp = structure.get(i);
            if(temp.equalsIgnoreCase("verse")) {
                if(verseCount == 0) {
                    if(genre.getGenreName().equalsIgnoreCase("Jazz/Progressive")) {
                        song += "\nA Section:\n";
                    } else {
                        song += "\nVerse:\n";
                    }
                    for(int j = 0; j < verseChords.size(); j++) {
                        if(j == 4) {
                            song += "\n";
                        }
                        song += verseChords.get(j);
                        if(j != verseChords.size() - 1) {
                            song += ", ";
                        }
                    }
                    song += "\n";
                } else {
                    if(genre.getGenreName().equalsIgnoreCase("Jazz/Progressive")) {
                        song += "\n[A Section]\n";
                    } else {
                        song += "\n[Verse]\n";
                    }
                }
                
                verseCount++;
            } else if(temp.equalsIgnoreCase("prechorus")){
                if(prechorusCount == 0) {
                    song += "\nPrechorus:\n";
                    for(int j = 0; j < prechorusChords.size(); j++) {
                        if(j == 4) {
                            song += "\n";
                        }
                        
                        song += prechorusChords.get(j);
                        if(j != prechorusChords.size() - 1) {
                            song += ", ";
                        }
                    }
                    song += "\n";
                } else {
                    song += "\n[Prechorus]\n";
                }
                prechorusCount++;
            } else if(temp.equalsIgnoreCase("chorus")){
                if(chorusCount == 0) {
                    if(genre.getGenreName().equalsIgnoreCase("Jazz/Progressive")) {
                        song += "\nB Section:\n";
                    } else if(genre.getGenreName().equalsIgnoreCase("Classical")) {
                        song += "\nExposition:\n";
                    } else if(genre.getGenreName().equalsIgnoreCase("Pop/Rock")) {
                        song += "\nIntro Chorus:\n";
                    } else {
                        song += "\nChorus:\n";
                    }
                    for(int j = 0; j < chorusChords.size(); j++) {
                        if(j == 4) {
                            song += "\n";
                        }
                        
                        song += chorusChords.get(j);
                        if(j != chorusChords.size() - 1) {
                            song += ", ";
                        }
                    }
                    song += "\n";
                } else {
                    if(genre.getGenreName().equalsIgnoreCase("Jazz/Progressive")) {
                        song += "[B Section]\n";
                    } else if(genre.getGenreName().equalsIgnoreCase("Classical") && chorusCount > 0) {
                        song += "\nRecapitulation:\n[Same as Exposition]\n";
                    } else {
                        song += "\n[Chorus]\n";
                    }
                }
                
                chorusCount++;
            } else if(temp.equalsIgnoreCase("bridge")){
                if(bridgeCount == 0) {
                    if(genre.getGenreName().equalsIgnoreCase("Jazz/Progressive")) {
                        song += "\nIntro:\n";
                    } else if(genre.getGenreName().equalsIgnoreCase("Classical")) {
                        song += "\nDevelopment:\n";
                    } else {
                        song += "\nBridge:\n";
                    }
                    for(int j = 0; j < bridgeChords.size(); j++) {
                        if(j == 4) {
                            song += "\n";
                        }
                        
                        song += bridgeChords.get(j);
                        if(j != bridgeChords.size() - 1) {
                            song += ", ";
                        }
                    }
                    song += "\n";
                } else {
                    song += "\n[Bridge]\n";
                }
                
                bridgeCount++;
            } else {
                
            }
        }
        
        return song;
    }
}
