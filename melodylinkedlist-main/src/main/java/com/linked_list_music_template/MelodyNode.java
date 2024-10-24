/*
 * c3 10/24/2024 Maria Murad
 * Class: Melody Node
 * Description: This class represents a node in a linked list of melodies. Each node holds a reference to a specific melody in the MelodyManager which is identified by an index. The melody can play when triggered. 
 */

package com.linked_list_music_template;

public class MelodyNode {
    
    MelodyManager melodyManager;
    MelodyNode next;
    int whichMelody;

    //Constructor
    MelodyNode(MelodyManager melodyManager,int whichMelody){
      this.melodyManager = melodyManager;
      this.whichMelody = whichMelody;
    }

    MelodyNode(MelodyManager melodyManager,int whichMelody,MelodyNode next){
         this.melodyManager = melodyManager;
         this.whichMelody = whichMelody;
         this.next = next;
    }

    MelodyNode getNext(){
        return next;
    }

    void setNext(MelodyNode next){
       this.next=next;
    }

    void start(){
        System.out.println("starting the melody");
        melodyManager.start(whichMelody);
    }

    MelodyNode copy(){
        MelodyNode melodyNode = new MelodyNode(melodyManager, whichMelody,next);
        return melodyNode;
    }

    int getMelodyIndex(){
        return whichMelody;
    }

    //check the melody manager if it is at the end
    boolean atEnd(){
        return melodyManager.atEnd(whichMelody);

    }

}
