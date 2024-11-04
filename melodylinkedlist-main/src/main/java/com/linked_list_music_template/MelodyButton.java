/*
 * c3 11/4/2024 Maria Murad
 * 
 * Classes: 
 * MelodyButton - super class for all buttons that use the MelodyLinkedList
 * PlayButton - play the MelodyLinkedList
 * StopButton - stop the MelodyLinkedList
 * LoopButton - loop the MelodyLinkedList
 * WeaveButton - 3 options to choose from
 * SpecialButton - custom button to clear melody list
 * UnitTestButton - run unit tests on LinkedListMelody
 * Description: Button classes for the LinkedListMelody Generator
 * 
 */

package com.linked_list_music_template;

import processing.core.PApplet;

// ------
//* MelodyButton - super class for all buttons that use the MelodyLinkedList
// -------
public abstract class MelodyButton extends Button {

    LinkedListMelody melody; // the linked list melody to control
    Boolean changeLoop;

    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    MelodyButton(PApplet main_, LinkedListMelody melody_, String label_,float x_, float y_)
    {
        super(main_, label_, x_, y_); 
        melody = melody_;
        changeLoop = false;
    } 
}

// ------
//* PlayButton - play the MelodyLinkedList
// -------
class PlayButton extends MelodyButton {

    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    PlayButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Play", x_, y_); 
    } 

    // start the melody
    public void onPress()
    {
        melody.start();
    }
}

// ------
//* StopButton - Stop the MelodyLinkedList
// -------
class StopButton extends MelodyButton {

    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    StopButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Stop", x_, y_); 
    } 

    // start the melody
    public void onPress()
    {
        melody.stop();
    }
}


// ------
//* LoopButton - Loop the MelodyLinkedList
// -------
class LoopButton extends MelodyButton {

    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    LoopButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Loop", x_, y_); 
    } 

    // start the melody
    public void onPress()
    {
        changeLoop = !changeLoop;
        melody.loop(changeLoop);
    }
}

// ------
//* RetrainMelodyButton - 
// -------
class RetrainMelodyButton extends Button 
{
    TreeMelody treeMelody;

    RetrainMelodyButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) {
        super(main_, "Retrain Melody", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() 
    {
        treeMelody.train(4, (int) (Math.random() * treeMelody.getMelodyManager().size()));
    }
}
// ----------
//* RetrainMelodyAtZeroButton -
// ----------
class RetrainMelodyAtZeroButton extends Button 
{
    TreeMelody treeMelody;

    RetrainMelodyAtZeroButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) 
    {
        super(main_, "Retrain Melody at 0", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() {
        treeMelody.train(4, 0);
    }
}

// ------
//* SpecialButton - Clear Melody List button
// -------
class SpecialButton extends MelodyButton {

    SpecialButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, melody_, "Clear List", x_, y_);
    }

    public void onPress() {
        melody.clear(); // Clear all nodes in the melody list
        System.out.println("Melody list cleared!");
    }
}

// ------
//* UnitTestButton - Run unit tests on LinkedListMelody
// -------
class UnitTestButton extends MelodyButton {
    UnitTestButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, melody_, "Run Tests", x_, y_);
    }

    public void onPress() {
        WeaveUnitTest test = new WeaveUnitTest();
        System.out.println("Running unit tests...");
        test.testWeave1();
        test.testWeave2();
        System.out.println("Unit tests completed.");
    }
}