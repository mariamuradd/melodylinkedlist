/*
 * c3 - Courtney Brown - Maria Murad
 * 11/4/2024
 * Class: TreeMelodyManager
 * Description: Handles all the melody files for the TreeMelodyProject
 * 
 */

package com.linked_list_music_template;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class TreeMelodyManager extends MelodyManager implements Drawable {

    //used for loading files
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid"  + sys.getSeparator();
    static String appendType = ".mid";

    //changed 
    float tempo = 120; 
    String bus = "Microsoft GS Wavetable Synth";


    String[] files = {"MaryHadALittleLamb"};

    
    TreeMelodyManager()
    {
        super();
    }

    //if you would like to add your own file(s)
    void setFiles(String[] files_)
    {
        files = files_;
    }

    //add more melody players to the manager -- ie, more melodies that can be played
    void addPlayers(ArrayList<MelodyPlayer> p)
    {
        players.addAll(p);
    }

    //return a copy/clone of all the players
    ArrayList<MelodyPlayer> copyPlayers()
    {
        ArrayList<MelodyPlayer> list = new ArrayList<>();
        list.addAll(players);
        return list;
    }

    //call this to load all the files. Make sure you set the files you want first. 
    void setup()
    {
        for( int i=0; i<files.length; i++)
        {
            addmidiFile(prependPath+files[i]+appendType);
        }
    }

    //get rid of all the players
    void clear()
    {
        players.clear();
    }

    //the size of the files array -- this was size() before, but I changed it to clarify 
    int fileSize()
    {
        return files.length; 
    }

    //the size of the MELODIES -- CALL THIS
    int melodySize()
    {
        return players.size();
    }

    //get the midi note numbers of the melody of the player at index i
    ArrayList<Integer> getMelodyPitches(int i)
    {
        return players.get(i).getMelody(); 
    }

    //converts all the files to motives of size noteCount. the last motive may be less than noteCount long
    //for the extra credit you will want to call this directly
    ArrayList<MelodyPlayer> convertToMotives(int noteCount)
    {
        System.out.println("Converting to motives with " + noteCount + " notes. This will take time...");
        ArrayList<MelodyPlayer> newPlayers = new ArrayList<>();

        //for all the files, break each down in to noteCount smaller melodies -- i.e., motives
        for ( MidiFileToNotes notes : midiNotes )
        {
            ArrayList<Double> rhythms = notes.getRhythmArray(); 
            ArrayList<Double> times = notes.getStartTimeArray();
            ArrayList<Integer> pitches = notes.getPitchArray(); 

            //for all the notes in the melody, break it down in to noteCount smaller melodies
            double startTime = 0;
            for( int i=0; i<pitches.size(); i++ )
            {
                MelodyPlayer player = new MelodyPlayer(tempo, bus); //the new player for our new small motive

                //all the data we need for the melody -- rhythm, startTimes, pitches
                ArrayList<Double> playingRhythms = new ArrayList();
                ArrayList<Double> playingTimes = new ArrayList();
                ArrayList<Integer> playingPitches = new ArrayList(); 


                //now add noteCount notes to the melody. startTimes a tad tricky as they have to adjust
                double curZero = startTime; //restart the times at zero
                for(int j=0; j<noteCount && i+j<pitches.size(); j++)
                {    
                    playingRhythms.add( rhythms.get(i+j)) ;
                    playingPitches.add( pitches.get(i+j)) ;

                    if( j==0 ) //the first note of the new motive always starts at zero
                    {
                        playingTimes.add( 0.0 ) ;
                        curZero += (times.get(i+j)-curZero);
                    }
                    else
                    {
                        playingTimes.add(times.get(i+j)-curZero); //re-jigger melody so it starts at 0
                    }
                    startTime = times.get(i+j); //the last start time is now
                }

                player.setStartTimes(playingTimes);
                player.setMelody(playingPitches);
                player.setRhythm(playingRhythms);
                newPlayers.add(player);

                i+=(noteCount-1); //we have now added noteCount notes so skip those & on to next
            }
        }
        System.out.println("Finished converting. There are now " + players.size() + " melodies.");
        return newPlayers; 
    }

    ArrayList<MelodyPlayer> convertToMotivesAndReplace(int noteCount){
        players = convertToMotives(noteCount);
        return players;
    }

    String melodyToString(int i) 
    {
        ArrayList<Integer> pitches = players.get(i).getMelody();
        return pitches.toString();
    }

    String startTimesToString(int i)
     {
        return players.get(i).getStartTimes().toString();
    }

    //removes the first note from the melody in the player at index i
    void popNoteFromMelody(int i)
    {
        players.get(i).getMelody().remove(0);
        players.get(i).getStartTimes().remove(0);
        players.get(i).getRhythm().remove(0);
    }

    //sends noteOffs for all the playing notes at once
    void stopAll()
    {
        for( MelodyPlayer player : players )
        {
            player.stopAllNotes();
        }
    }

    //draw is an update function in Processing, so this updates what midi notes are being sent
    //it is time dependent.
    public void draw()
    {
        playMelodies();
    }

    public MelodyPlayer getPlayer(int index) {
        return players.get(index);
    }

    public int size() {
        return players.size();
    }

    public void print() 
    {
        StringBuilder melodyOutput = new StringBuilder("Tree Melody Manager: ");
        for (int i = 0; i < players.size(); i++) 
        {
            melodyOutput.append("Melody ").append(i).append(", ");
        }
        if (melodyOutput.length() > 0) 
        {
            melodyOutput.setLength(melodyOutput.length() - 2);
        }
        System.out.println(melodyOutput.toString());
    }
}
