package Controller;

import java.util.ArrayList;

import Model.Data;
import Model.DataBuilder;
import jm.constants.Pitches;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;

import static jm.constants.ProgramChanges.PIANO;
import static jm.constants.Volumes.*;

public class NoteGeneration implements Pitches{

    //Questa classe contiene i metodi per l'elaborazione del dataset in pitch e ottava corrispondenti, contiene inoltre il metodo generateSound() che genera la melodia da riprodurre

    private final ArrayList<Data> dataset = new DataBuilder().builder();

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


    public Score generateSound(int rhytm, int dynamic, int duration, double pan)  {

        Integer[] notes =  this.getNotes();

        Score score = new Score();
        Part part = new Part("Sonification", PIANO);
        Phrase phrase = new Phrase();


        Note[] noteArray =  new Note[dataset.size()];
        for(int i = 0; i < noteArray.length; i++) {

            noteArray[i] = new Note(notes[i], rhytm + 0.5, getDynamic(dynamic), pan);

            noteArray[i].setDuration(duration + 0.4);

            System.out.println(noteArray[i]);       //temp

        }

        phrase.addNoteList(noteArray);

        part.addPhrase(phrase);
        score.addPart(part);

        Play.midi(score);

        return score;

    }

}
