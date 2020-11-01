/**
 * <h1>Interval</h1>
 * Contains information about Intervals, the distance between Notes
 * 
 * @author Devon Hubert
 * @version 3.0
 */

public class Interval
{
    private int distance;
    private String quality;
    private int degreeValue;
    private String degreeName;
    
    /**
     * The default constructor, which sets the Interval's distance to 0, quality to "Perfect,"
     * degreeValue to 1, and degreeName to "Unison"
     */
    public Interval() {
        this.distance = 0;
        this.quality = "Perfect";
        this.degreeValue = 1;
        this.degreeName = "Unison";
    }
    
    /**
     * The constructor that accepts the raw distance between two Notes, and sets 
     * it as the Interval's distance
     * @param distance The raw distance between two Notes
     */
    public Interval(int distance) {
        this.distance = distance;
    }
    
    /**
     * The constructor that accepts two Notes, calculates their raw distance, and 
     * sets this distance as the Interval's distance
     * @param firstNote The first Note
     * @param secondNote The second Note
     */
    public Interval(Note firstNote, Note secondNote) {
        this.distance = Math.abs(secondNote.getValue() - firstNote.getValue());
    }
    
    /**
     * The constructor that accepts the Interval's quality and degreeValue, and 
     * calculates and sets the remaining variables accordingly
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
     * @param degreeValue The Interval's degreeValue (third, fourth, ninth, octave, etc.) (int)
     */
    public Interval(String quality, int degreeValue) {
        this.distance = calculateDistance(quality, degreeValue); 
        this.quality = quality;
        this.degreeValue = degreeValue;
        this.degreeName = findDegreeName(degreeValue);
    }
    
    /**
     * The constructor that accepts the Interval's distance and quality, and 
     * calculates and sets the remaining variables accordingly
     * @param distance The raw distance that the Interval represents (int)
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
     */
    public Interval(int distance, String quality) {
        this.quality = quality;
        this.distance = distance;
        this.degreeValue = calculatedegreeValue(distance, quality);
        this.degreeName = findDegreeName(degreeValue);
    }
    
    /**
     * The constructor that accepts the Interval's degreeValue and distance, and 
     * calculates and sets the remaining variables accordingly
     * @param degreeValue The Interval's degreeValue (third, fourth, ninth, octave, etc.) (int)
     * @param distance The raw distance that the Interval represents (int)
     */
    public Interval(int degreeValue, int distance) {
        this.distance = distance;
        this.quality = calculateQuality(degreeValue, distance);
        this.degreeValue = degreeValue;
        this.degreeName = findDegreeName(degreeValue);
    }
    
    /**
     * The constructor that accepts two Notes, between which the Interval exists, 
     * as well as the desired quality of the interval, which are used to 
     * calculate and set the remaining variables 
     * @param firstNote The first Note
     * @param secondNote The second Note
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
     */
    public Interval(Note firstNote, Note secondNote, String quality) {
        this.quality = quality;
        int distance = Math.abs(secondNote.getValue() - firstNote.getValue());
        this.distance = distance;
        this.degreeValue = calculatedegreeValue(distance, quality);
        this.degreeName = findDegreeName(degreeValue);
    }
    
    /**
     * The constructor that accepts two Notes, between which the Interval exists, 
     * as well as the desired degreeValue of the interval, which are used to 
     * calculate and set the remaining variables 
     * @param firstNote The first Note
     * @param secondNote The second Note
     * @param degreeValue The Interval's degreeValue (third, fourth, ninth, octave, etc.) (int)
     */
    public Interval(Note firstNote, Note secondNote, int degreeValue) {
        int distance = Math.abs(secondNote.getValue() - firstNote.getValue());
        this.distance = distance;
        this.quality = calculateQuality(distance, degreeValue);
        this.degreeValue = degreeValue;
        this.degreeName = findDegreeName(degreeValue);
    }
    
    /**
     * The constructor where all information about the Interval is known, setting the Interval's
     * variables based on the parameter input
     * @param distance The raw distance that the Interval represents (int)
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
     * @param degreeValue The Interval's degreeValue (1 = "unison," 8 = "octave," etc.) (int)
     */
    public Interval(int distance, String quality, int degreeValue) {
        this.distance = distance;
        this.quality = quality;
        this.degreeValue = degreeValue;
        this.degreeName = findDegreeName(degreeValue);
    }
    
    /**
     * @return the raw distance that is the essence of the Interval object (int)
     */
    public int getDistance() {
        return distance;
    }
    
    /**
     * @return the quality that describes the interval ("Major," "Minor," etc.) (String)
     */
    public String getQuality() {
        return quality;
    }
    
    /**
     * @return the value of the Interval's degree type (1 for "unison," 8 for "octave," etc.) (int)
     */
    public int getDegreeValue() {
        return degreeValue;
    }
    
    /**
     * @return the name of the Interval's degree type ("Unison," "Third," "Octave," etc.) (String)
     */
    public String getDegreeName() {
        return degreeName;
    }
    
    /**
     * Sets the Interval's raw distance value 
     * @param distance The raw distance that the Interval represents (int)
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    /**
     * Sets the Interval's quality value
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
    */
    public void setQuality(String quality) {
        this.quality = quality;
    }
    
    /**
     * Sets the Interval's degree value
     * @param degreeValue The Interval's degreeValue (1 = "unison," 8 = "octave," etc.) (int)
    */
    public void setdegreeValue(int degreeValue) {
        this.degreeValue = degreeValue;
    }
    
    /**
     * Sets the Interval's degree Name
     * @param degreeName The Interval's degreeName ("Unison," "Third," "Octave," etc.) (int)
    */
    public void setdegreeName(String degreeName) {
        this.degreeName = degreeName;
    }
    
    /**
     * Calculates the Interval's raw distance, based on it's quality and degreeValue
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
     * @param degreeValue The Interval's degreeValue (1 = "unison," 8 = "octave," etc.) (int)
     */
    public int calculateDistance(String quality, int degreeValue) {
        int distance = -1;
        if(quality.equalsIgnoreCase("Diminished")) {
            if(degreeValue == 1) {
                distance = -1; //legal?
            } else if(degreeValue == 2) {
                distance = 0;
            } else if(degreeValue == 3) {
                distance = 2;
            } else if(degreeValue == 4) {
                distance = 4;
            } else if(degreeValue == 5) {
                distance = 6;
            } else if(degreeValue == 6) {
                distance = 7;
            } else if(degreeValue == 7) {
                degreeValue = 9;
            } else if(degreeValue == 8) {
                degreeValue = 11;
            } else if(degreeValue == 9) {
                distance = 12;
            } else if(degreeValue == 11) {
                distance = 16;
            } else if(degreeValue == 13) {
                distance = 19;
            } 
        } else if(quality.equalsIgnoreCase("minor")) {
            if(degreeValue == 2) {
                distance = 1;
            } else if(degreeValue == 3) {
                distance = 3;
            } else if(degreeValue == 6) {
                distance = 8;
            } else if(degreeValue == 7) {
                distance = 10;
            }else if(degreeValue == 9) {
                distance = 13;
            } else if(degreeValue == 13) {
                distance = 20;
            } 
        } else if(quality.equalsIgnoreCase("Perfect")) {
            if(degreeValue == 1) {
                distance = 0;
            } else if(degreeValue == 4) {
                distance = 5;
            } else if(degreeValue == 5) {
                distance = 7;
            } else if(degreeValue == 8) {
                distance = 12;
            } else if(degreeValue == 11) {
                distance = 17;
            } 
        } else if(quality.equalsIgnoreCase("Major")) {
            if(degreeValue == 2) {
                distance = 2;
            } else if(degreeValue == 3) {
                distance = 4;
            } else if(degreeValue == 6) {
                distance = 9;
            } else if(degreeValue == 7) {
                distance = 11;
            }else if(degreeValue == 9) {
                distance = 14;
            } else if(degreeValue == 13) {
                distance = 21;
            } 
        } else if(quality.equalsIgnoreCase("Augmented")) {
            if(degreeValue == 1) {
                distance = 1; 
            } else if(degreeValue == 2) {
                distance = 3;
            } else if(degreeValue == 3) {
                distance = 5;
            } else if(degreeValue == 4) {
                distance = 6;
            } else if(degreeValue == 5) {
                distance = 8;
            } else if(degreeValue == 6) {
                distance = 10;
            } else if(degreeValue == 7) {
                degreeValue = 12;
            } else if(degreeValue == 8) {
                degreeValue = 13;
            } else if(degreeValue == 9) {
                distance = 15;
            } else if(degreeValue == 11) {
                distance = 18;
            } else if(degreeValue == 13) {
                distance = 22;
            } 
        } else {
            
        }
        
        return distance;
    }
    
    /**
     * Calculates the Interval's quality type, based on it's degreeValue and raw distance
     * @param degreeValue The Interval's degreeValue (1 = "unison," 8 = "octave," etc.) (int)
     * @param distance The raw distance that the Interval represents (int)
     */
    public String calculateQuality(int degreeValue, int distance) {
        //distance = distance
        String quality = "";  
        
        if(distance % 12 == 0) {
            if(degreeValue % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 1) % 7 == 0) {
                quality = "Perfect";
            } else if((degreeValue - 2) % 7 == 0) {
                quality = "diminished";
            }
        } else if((distance - 1) % 12 == 0) {
            if((degreeValue - 1) % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 2) % 7 == 0) {
                quality = "minor";
            } 
        } else if((distance - 2) % 12 == 0) {
            if((degreeValue - 2) % 7 == 0) {
                quality = "Major";
            } else if((degreeValue - 3) % 7 == 0) {
                quality = "diminished";
            } 
        } else if((distance - 3) % 12 == 0) {
            if((degreeValue - 2) % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 3) % 7 == 0) {
                quality = "minor";
            } 
        } else if((distance - 4) % 12 == 0) {
            if((degreeValue - 3) % 7 == 0) {
                quality = "Major";
            } else if((degreeValue - 4) % 7 == 0) {
                quality = "diminished";
            } 
        } else if((distance - 5) % 12 == 0) {
            if((degreeValue - 3) % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 4) % 7 == 0) {
                quality = "Perfect";
            } 
        } else if((distance - 6) % 12 == 0) {
            if((degreeValue - 4) % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 5) % 7 == 0) {
                quality = "diminished";
            } 
        } else if((distance - 7) % 12 == 0) {
            if((degreeValue - 5) % 7 == 0) {
                quality = "Perfect";
            } else if((degreeValue - 6) % 7 == 0) {
                quality = "diminished";
            } 
        } else if((distance - 8) % 12 == 0) {
            if((degreeValue - 5) % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 6) % 7 == 0) {
                quality = "minor";
            } 
        } else if((distance - 9) % 12 == 0) {
            if((degreeValue - 6) % 7 == 0) {
                quality = "Major";
            } else if((degreeValue - 7) % 7 == 0) {
                quality = "diminished";
            } 
        } else if((distance - 10) % 12 == 0) {
            if((degreeValue - 6) % 7 == 0) {
                quality = "Augmented";
            } else if((degreeValue - 7) % 7 == 0) {
                quality = "minor";
            } 
        } else if((distance - 11) % 12 == 0) {
            if((degreeValue - 7) % 7 == 0) {
                quality = "Major";
            } else if((degreeValue - 8) % 7 == 0) {
                quality = "diminished";
            } 
        } else {
            quality = "Undefined";
        }
        
        return quality;
    }
    
    /**
     * Calculates the Interval's degree type, based on it's raw distance and quality type
     * @param distance The raw distance that the Interval represents (int)
     * @param quality The Interval's quality ("Perfect," "minor," "diminished," etc.) (String)
     */
    public int calculatedegreeValue(int distance, String quality) {//distance = distance 
        int degreeValue = -1;
        
        if(distance == 0) {
            if(quality.equalsIgnoreCase("Perfect")) {
                degreeValue = 1;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 2;
            }
        } else if(distance == 1) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 1;
            } else if(quality.equalsIgnoreCase("minor")) {
                degreeValue = 2;
            } 
        } else if(distance == 2) {
            if(quality.equalsIgnoreCase("Major")) {
                degreeValue = 2;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 3;
            }
        } else if(distance == 3) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 2;
            } else if(quality.equalsIgnoreCase("minor")) {
                degreeValue = 3;
            } 
        } else if(distance == 4) {
            if(quality.equalsIgnoreCase("Major")) {
                degreeValue = 3;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 4;
            }
        } else if(distance == 5) {
            if(quality.equalsIgnoreCase("Perfect")) {
                degreeValue = 4;
            } else if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 3;
            } 
        } else if(distance == 6) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 4;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 5;
            }
        } else if(distance == 7) {
            if(quality.equalsIgnoreCase("Perfect")) {
                degreeValue = 5;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 6;
            }
        } else if(distance == 8) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 5;
            } else if(quality.equalsIgnoreCase("minor")) {
                degreeValue = 6;
            } 
        } else if(distance == 9) {
            if(quality.equalsIgnoreCase("Major")) {
                degreeValue = 6;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 7;
            }
        } else if(distance == 10) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 6;
            } else if(quality.equalsIgnoreCase("minor")) {
                degreeValue = 7;
            } 
        } else if(distance == 11) {
            if(quality.equalsIgnoreCase("Major")) {
                degreeValue = 7;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 8;
            }
        } else if(distance == 12) {
            if(quality.equalsIgnoreCase("Perfect")) {
                degreeValue = 8;
            } else if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 7;
            } else if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 9;
            }
        } else if(distance == 13) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 8;
            } else if(quality.equalsIgnoreCase("minor")) {
                degreeValue = 9;
            } 
        } else if(distance == 14) {
            if(quality.equalsIgnoreCase("Major")) {
                degreeValue = 9;
            } 
        } else if(distance == 15) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 9;
            } 
        } else if(distance == 16) {
            if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 11;
            }
        } else if(distance == 17) {
            if(quality.equalsIgnoreCase("Perfect")) {
                degreeValue = 11;
            } 
        } else if(distance == 18) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 11;
            } 
        } else if(distance == 20) {
            if(quality.equalsIgnoreCase("diminished")) {
                degreeValue = 13;
            }
        } else if(distance == 21) {
            if(quality.equalsIgnoreCase("Perfect")) {
                degreeValue = 13;
            } 
        } else if(distance == 22) {
            if(quality.equalsIgnoreCase("Augmented")) {
                degreeValue = 13;
            }
        } else {
            degreeValue = -1;
        }
        
        return degreeValue;
    }
    
    /**
     * Calculates all possible interval types that an Interval of given distance could be
     * @return a String representation of the Interval's quality and degreeName (String)
     */
    public String computePossible(int distance) {
        String possibilities = "";
        
        if(distance % 12 == 0) {
            possibilities = "Augmented Seventh, Perfect Unison or diminished Second";
        } else if((distance - 1) % 12 == 0) {
            possibilities = "Augmented Unison or minor Second";
        } else if((distance - 2) % 12 == 0) {
            possibilities = "Major Second or diminished Third";
        } else if((distance - 3) % 12 == 0) {
            possibilities = "Augmented Second or minor Third";
        } else if((distance - 4) % 12 == 0) {
            possibilities = "Major Third or diminished Fourth";
        } else if((distance - 5) % 12 == 0) {
            possibilities = "Augmented Third or Perfect Fourth";
        } else if((distance - 6) % 12 == 0) {
            possibilities = "Augmented Fourth or diminished Fifth";
        } else if((distance - 7) % 12 == 0) {
            possibilities = "Perfect Fifth or diminished Sixth";
        } else if((distance - 8) % 12 == 0) {
            possibilities = "Augmented Fifth or minor Sixth";
        } else if((distance - 9) % 12 == 0) {
            possibilities = "Major Sixth or diminished Seventh";
        } else if((distance - 10) % 12 == 0) {
            possibilities = "Augmented Sixth or minor Seventh";
        } else if((distance - 11) % 12 == 0) {
            possibilities = "Major Seventh or diminished Octave";
        } 
        
        return possibilities;
    }
    
    public char findLetterName(String rootName, int degreeValue) { //C, 9
        String rawNoteName = "" + rootName.charAt(0);
        Key temp = new Key(rawNoteName, true);
        int tempDegree = degreeValue - 1;
        
        if(tempDegree >= 7) {
            tempDegree -= 7;
        } else if(tempDegree <= -1) {
            tempDegree += 7;
        }
        return temp.getScaleSet()[tempDegree].getNoteName().charAt(0);
    }
    
    /**
     * Finds an Interval's degreeName based on its degreeValue
     * @return the Interval's degreeName (String)
     */
    public String findDegreeName(int degreeValue) {
        String degreeName;
        
        if(degreeValue == 1) {
            degreeName = "Unison";
        } else if(degreeValue == 2) {
            degreeName = "Second";
        } else if(degreeValue == 3) {
            degreeName = "Third";
        } else if(degreeValue == 4) {
            degreeName = "Fourth";
        } else if(degreeValue == 5) {
            degreeName = "Fifth";
        } else if(degreeValue == 6) {
            degreeName = "Sixth";
        } else if(degreeValue == 7) {
            degreeName = "Seventh";
        } else if(degreeValue == 8) {
            degreeName = "Octave";
        } else if(degreeValue == 9) {
            degreeName = "Ninth";
        } else if(degreeValue == 10) {
            degreeName = "Tenth";
        } else if(degreeValue == 11) {
            degreeName = "Eleventh";
        } else if(degreeValue == 12) {
            degreeName = "Twelth";
        } else {
            degreeName = "Thirteenth";
        }
        
        return degreeName;
    }
       
    /**
     * A String representation of the Interval object, made up of its quality and degreeName
     */
    public String toString() {
        this.degreeName = findDegreeName(this.degreeValue);
        return quality + " " + degreeName;
    }
}

