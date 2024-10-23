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

        melodyManager.start(whichMelody);
    }

    MelodyNode copy(){
        MelodyNode melodyNode = new MelodyNode(melodyManager, whichMelody,next);
        return melodyNode;
    }

    int getMelodyIndex(){
        return whichMelody;
    }


}
