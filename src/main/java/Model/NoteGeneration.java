package Model;

import java.util.ArrayList;

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;

import static jm.constants.ProgramChanges.PIANO;

public class NoteGeneration {

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

    public String[] getNotes(){

        ArrayList<Integer> pitches = getPitchesList();
        ArrayList<Integer> octaves = getOctaveList();

        String[] notes = new String[dataset.size()];

        for (int i = 0; i < pitches.size(); i++) {

            String  note;
            int  notepitch = pitches.get(i);
            switch(notepitch) {

                case 0: note = "C";
                        break;
                case 1: note = "C#";
                        break;
                case 2: note = "D";
                        break;
                case 3: note = "Eb";
                        break;
                case 4: note = "E";
                        break;
                case 5: note = "F";
                        break;
                case 6: note = "F#";
                        break;
                case 7: note = "G";
                        break;
                case 8: note = "Ab";
                        break;
                case 9: note = "A";
                        break;
                case 10: note = "Bb";
                        break;
                case 11: note = "B";
                        break;
                default: note = new String();

            }

            int  octave = octaves.get(i);
            /*switch(octave) {

                case 0: note = note + "0";
                        break;
                case 1: note = note + "1";
                        break;
                case 2: note = note + "2";
                        break;
                case 3: note = note + "3";
                        break;
                case 4: note = note + "4";
                        break;
                case 5: note = note + "5";
                        break;
                case 6: note = note + "6";
                        break;
                case 7: note = note + "7";
                        break;

            }*/

            notes[i] = note;
        }

        return notes;
    }


    public Score generateSound(int rhytm, int dynamic, int duration, int pan)  {

        NoteGeneration noteGen = new NoteGeneration();
        String[] notes =  noteGen.getNotes();

        Score score = new Score();
        Part part = new Part("Sonification", PIANO);
        Phrase phrase = new Phrase();


        Note[] noteArray =  new Note[dataset.size()];
        for(int i = 0; i < noteArray.length; i++) {

            noteArray[i] = new Note(notes[i]);
            noteArray[i].setRhythmValue(rhytm + 5);     //temp numbers
            noteArray[i].setDynamic(dynamic + 100);
            noteArray[i].setDuration(duration + 0.2);
            noteArray[i].setPan(pan);
            noteArray[i].setFrequency(440.00);      //ottava da sistemare
            System.out.println(noteArray[i]);

        }

        phrase.addNoteList(noteArray);

        part.addPhrase(phrase);
        score.addPart(part);

        Play.midi(score);

        return score;

    }

}
