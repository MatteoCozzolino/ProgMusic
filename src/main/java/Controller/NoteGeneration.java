package Controller;

import java.util.ArrayList;

import Model.Data;
import Model.DataBuilder;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;

import static java.lang.Math.random;
import static jm.constants.Durations.*;
import static jm.constants.ProgramChanges.PIANO;
import static jm.constants.Volumes.*;

public class NoteGeneration{

    /**
     * This class contains the methods to elaborate the dataset and produce the score.
     */


    private final ArrayList<Data> dataset = new DataBuilder().builder();
    public boolean panFlag = true;

    /**
     * The methods .getPitchesList, .getOctaveList and .getNotes elaborate the numbers of the dataset and create and array, though the method .getNotes containing the pitches
     * of the notes
     */

    private ArrayList<Integer> getPitchesList () {

        ArrayList<Integer> pitches = new ArrayList<>();

        for(Data object : dataset){
            pitches.add(object.getArrestNumber()%12);
        }

        return pitches;
    }

    private ArrayList<Integer> getOctaveList () {

        ArrayList<Integer> octaves = new ArrayList<>();

        for(Data object : dataset){
            octaves.add((object.getArrestNumber()%88)/12);
        }

        return octaves;
    }

    public Integer[] getNotes(){

        ArrayList<Integer> pitches = getPitchesList();
        ArrayList<Integer> octaves = getOctaveList();

        Integer[] notes = new Integer[dataset.size()];

        for (int i = 0; i < pitches.size(); i++) {

            int  notepitch = pitches.get(i);
            int  octave = octaves.get(i);
            notes[i] = (octave*12) + notepitch;

        }

        return notes;
    }

    private int getDynamic(int pickedDynamic){

        int dynamic;

        switch(pickedDynamic) {

            case 0:
                dynamic = PIANISSIMO;
                break;
            case 1:
                dynamic = MEZZO_PIANO;
                break;
            case 2:
                dynamic = MEZZO_FORTE;
                break;
            case 3:
                dynamic = FORTE;
                break;
            case 4:
                dynamic = FORTISSIMO;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pickedDynamic);
        }

        return dynamic;

    }

    private double getRhythm (int pickedRhythm){

        double rhythm;

        switch(pickedRhythm) {

            case 0:
                rhythm = random();
                break;
            case 1:
                rhythm = CROTCHET;
                break;
            case 2:
                rhythm = QUAVER;
                break;
            case 3:
                rhythm = SEMI_QUAVER;
                break;
            case 4:
                rhythm = DEMI_SEMI_QUAVER;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pickedRhythm);
        }

        return rhythm;
    }

    private double getPan (int pickedPan){

        double pan;

        if(pickedPan == 3){
            if(panFlag) {
                pan = 0;
                panFlag = false;
            }else{
                pan = 1.0;
                panFlag = true;
            }
        }
        else
            pan = pickedPan / 2.0;

        return pan;
    }

    /**
     * The following method generates the score by creating the noteArray[] array which will contain all the notes with pitches generated through the .getNotes method and with
     * the rhythm, dynamic and pan picked by the user during the customization phase and elaborated in the .getDynamic, .getRhythm and .getPan methods of the class.
     */

    public Score generateScore(int rhythm, int dynamic, int pan)  {

        Integer[] notes =  this.getNotes();

        Score score = new Score();
        Part part = new Part("Sonification", PIANO);
        Phrase phrase = new Phrase();

        Note[] noteArray =  new Note[dataset.size()];
        for(int i = 0; i < noteArray.length; i++) {

            noteArray[i] = new Note(notes[i], getRhythm(rhythm), getDynamic(dynamic), getPan(pan));

        }

        phrase.addNoteList(noteArray);
        part.addPhrase(phrase);
        score.addPart(part);

        return score;

    }

}
