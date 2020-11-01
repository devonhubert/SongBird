import java.util.ArrayList;

/**
 * <h1>Genre</h1>
 * 
 * Defines the song's genre, which influences its structure, as
 * well as the complexity of its chord choices
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class Genre
{
    private String genreName;
    private int verseChordNumber;
    private int prechorusChordNumber;
    private int chorusChordNumber;
    private int bridgeChordNumber;
    private boolean simplify;
    private ArrayList<String> structure = new ArrayList<String>();
    
    /**
     * The default constructor, which automatically sets the Genre to "pop", 
     * and plans the song structure and complexity accordingly
     */
    public Genre() {
        this.genreName = "pop";
        this.simplify = true;
        planSong(genreName);
    }
    
    /**
     * The constructor that accepts a genreName, and plans the song structure and
     * complexity accordingly
     * @param genreName The name of the Genre (String)
     */
    public Genre(String genreName) {
        this.genreName = genreName;
        planSong(genreName);
    }
    
    /**
     * @return the number of Chords in each of the song's verses (int)
     */
    public int getVerseChordNumber() {
        return this.verseChordNumber;
    }
    
    /**
     * @return the name of the current Genre (String)
     */
    public String getGenreName() {
        return genreName;
    }
    
    /**
     * @return the number of Chords in each of the song's prechoruses (int)
     */
    public int getPrechorusChordNumber() {
        return this.prechorusChordNumber;
    }
    
    /**
     * @return the number of Chords in each of the song's choruses (int)
     */
    public int getChorusChordNumber() {
        return this.chorusChordNumber;
    }
    
    /**
     * @return the number of Chords in each of the song's bridges (int)
     */
    public int getBridgeChordNumber() {
        return this.bridgeChordNumber;
    }
    
    /**
     * @return true if the song's genre generally has relatively simple Chords
     */
    public boolean getSimplify() {
        return this.simplify;
    }
    
    /**
     * @return the general structure of a song in the given Genre
     */
    public ArrayList<String> getStructure() {
        return this.structure;
    }
    
    /**
     * Accepts a genreName, and plans the song structure and
     * complexity accordingly
     * @param genreName The name of the Genre (String)
     */
    public void planSong(String genreName) {
        if(genreName.equalsIgnoreCase("pop") 
        || genreName.equalsIgnoreCase("country")
        || genreName.equalsIgnoreCase("rap")
        || genreName.equalsIgnoreCase("Pop/Rock")) {
            this.verseChordNumber = 4;
            this.prechorusChordNumber = 4;
            this.chorusChordNumber = 4;
            this.bridgeChordNumber = 4;
            this.simplify = true;
            
            String[] temp = {"chorus", "verse", "prechorus", "chorus",
            "verse", "prechorus", "chorus", "bridge", "chorus",
            "chorus"};
            
            for(int i = 0; i < temp.length; i++) {
                this.structure.add(temp[i]);
            }
        } else if(genreName.equalsIgnoreCase("rock")
        || genreName.equalsIgnoreCase("hard rock")
        || genreName.equalsIgnoreCase("reggae")
        || genreName.equalsIgnoreCase("funk")
        || genreName.equalsIgnoreCase("alternative")
        || genreName.equalsIgnoreCase("metal")) {
            this.verseChordNumber = 4;
            this.prechorusChordNumber = 4;
            this.chorusChordNumber = 4;
            this.bridgeChordNumber = 4;
            this.simplify = true;
            
            String[] temp = {"bridge", "verse", "prechorus", "chorus",
            "verse", "prechorus", "chorus", "bridge", "chorus"};
            
            for(int i = 0; i < temp.length; i++) {
                this.structure.add(temp[i]);
            }
        } else if(genreName.equalsIgnoreCase("jazz")
        || genreName.equalsIgnoreCase("fusion")
        || genreName.equalsIgnoreCase("progressive")
        || genreName.equalsIgnoreCase("easy listening")
        || genreName.equalsIgnoreCase("Jazz/Progressive")) {
            this.verseChordNumber = 4;
            this.prechorusChordNumber = 0;
            this.chorusChordNumber = 4;
            this.bridgeChordNumber = 4;
            this.simplify = false;
            
            String[] temp = {"bridge", "verse", "verse", "chorus", 
            "verse"};
            
            for(int i = 0; i < temp.length; i++) {
                this.structure.add(temp[i]);
            }
        } else if(genreName.equalsIgnoreCase("classical")
        || genreName.equalsIgnoreCase("sonata")) {
            this.verseChordNumber = 0;
            this.prechorusChordNumber = 0;
            this.chorusChordNumber = 8;
            this.bridgeChordNumber = 8;
            this.simplify = true;
            
            String[] temp = {"chorus", "bridge", "chorus"};
            
            for(int i = 0; i < temp.length; i++) {
                this.structure.add(temp[i]);
            }
        } else if(genreName.equalsIgnoreCase("Mix it up!")) {
            this.verseChordNumber = (int) Math.floor(Math.random() * 8) + 1;
            this.prechorusChordNumber = (int) Math.floor(Math.random() * 8) + 1;
            this.chorusChordNumber = (int) Math.floor(Math.random() * 8) + 1;
            this.bridgeChordNumber = (int) Math.floor(Math.random() * 8) + 1;
            
            int test = (int) Math.floor(Math.random() * 2);
            if(test == 1) {
                this.simplify = true;
            } else {
                this.simplify = false;
            }
            
            int test2 = (int) Math.floor(Math.random() * 3);
            
            if(test2 == 0) { 
                String[] temp = {"chorus", "verse", "prechorus", "chorus",
                "verse", "prechorus", "chorus", "bridge", "chorus",
                "chorus"};
                for(int i = 0; i < temp.length; i++) {
                    this.structure.add(temp[i]);
                }
            } else if(test2 == 1) {
                String[] temp = {"bridge", "verse", "prechorus", "chorus",
                "verse", "prechorus", "chorus", "bridge", "chorus"};
                for(int i = 0; i < temp.length; i++) {
                    this.structure.add(temp[i]);
                }
            } else if(test2 == 2) {
                String[] temp = {"bridge", "verse", "verse", "chorus", 
                "verse"};
                for(int i = 0; i < temp.length; i++) {
                    this.structure.add(temp[i]);
                }
            } else {
                String[] temp = {"chorus", "bridge", "chorus"};
                for(int i = 0; i < temp.length; i++) {
                    this.structure.add(temp[i]);
                }
            }
        } else {
            this.verseChordNumber = 4;
            this.prechorusChordNumber = 4;
            this.chorusChordNumber = 4;
            this.bridgeChordNumber = 4;
            this.simplify = true;
            
            String[] temp = {"chorus", "verse", "prechorus", "chorus",
            "verse", "prechorus", "chorus", "bridge", "chorus",
            "chorus"};
            
            for(int i = 0; i < temp.length; i++) {
                this.structure.add(temp[i]);
            }
        }
    }
    
    /**
     * @return a String representation of a song's Genre
     */
    public String toString() {
        return this.genreName;
    }
}
