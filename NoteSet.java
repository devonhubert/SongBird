/**
 * <h1>Note Set</h1>
 * A set of all the possible Notes in an octave. 
 * This includes the 11 standard semitones, as well 
 * as their enharmonically equivalent "names" (C# = Db, etc.)
 * 
 * @author Devon Hubert
 * @version 3.0
 */

public class NoteSet
{
    Note CF, C, CS, DF, D, DS, EF, E, ES, FF, F, FS, GF, G, GS, AF, A, AS, BF, B, BS;
       
    /**
     * The default constructor, which creates the set of all possible notes in an 
     * octave, from Cb to B#, assigning them integer note values -1 through 12, 
     * and String name values
     */
        public NoteSet() {
            this.CF = new Note(-1, "Cb");
            this.C = new Note(0, "C");
            this.CS = new Note(1, "C#");
            this.DF = new Note(1, "Db");
            this.D = new Note(2, "D");
            this.DS = new Note(3, "D#");
            this.EF = new Note(3, "Eb");
            this.E = new Note(4, "E");
            this.ES = new Note(5, "E#");
            this.FF = new Note(4, "Fb");
            this.F = new Note(5, "F");
            this.FS = new Note(6, "F#");
            this.GF = new Note(6, "Gb");
            this.G = new Note(7, "G");
            this.GS = new Note(8, "G#");
            this.AF = new Note(8, "Ab");
            this.A = new Note(9, "A");
            this.AS = new Note(10, "A#");
            this.BF = new Note(10, "Bb");
            this.B = new Note(11, "B");
            this.BS = new Note(12, "B#");
    }
}
