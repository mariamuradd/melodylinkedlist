/*
 * c3 10/28/2024 Maria Murad
 * Class: LinkedListMelodyManager
 * Description: This class manages and plays a collection of melodies from MIDI files, with the ability to handle multiple tracks simultaneously. It initializes melodies from a predefined set of files, allowing for playback and printing of each melody’s details.
 */

package com.linked_list_music_template;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class LinkedListMelodyManager extends MelodyManager implements Drawable{
    
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid";

    String[] files = {"Accordion", "Alto", "Bassoon", "Contrabass", "Electric_Guitar", "Piano" , "Pipe_Organ", "Tuba", "Violoncello"};

    LinkedListMelodyManager(){

        super();
    }

    void setup(){
        for(int i=0; i<files.length; i++)
        {
            addmidiFile(prependPath+files[i]+appendType);
        }
    }

    int size(){
        return files.length;
    }

    @Override
    public void draw() {
        playMelodies();
    }

    void print()
    {
        for( MelodyPlayer player : players )
        {
            System.out.println( player.getMelody().toString() );
        }
    }
}
