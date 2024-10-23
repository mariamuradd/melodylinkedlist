package com.linked_list_music_template;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class LinkedListMelodyManager extends MelodyManager implements Drawable{
    
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = "mid" + sys.getSeparator();

    String[] files = {"motive1Am", "motive2Am", "motive3Am", "motive1E", "motive2E", "motive3E"};

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
}
