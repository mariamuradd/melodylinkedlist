/*
 * c3 11/4/2024
 * Maria Murad
 * Class: TreeMelody
 * Description: This class represents a tree-structured melody sequence where each node holds a series of MIDI pitches. There are also different methods provided to train the tree with melody data, clear/print the tree as well as manage playback functionality (with play, start, loop or stop).
 */
package com.linked_list_music_template;

import java.util.ArrayList;
import java.util.Random;

public class TreeMelody extends LinkedListMelody {

    TreeMelodyNode root;
    TreeMelodyManager manager;
    boolean isPlaying = false;  
    boolean isLooping = false;  

    // Constructor
    public TreeMelody(TreeMelodyManager manager) {
        super();
        this.manager = manager;
        this.root = (TreeMelodyNode) header;
    }

    // Traverse & print the contents of the tree
    public void print() {
        if (root != null) {
            root.print();  // Call print on root to recursively print the entire tree
        } else {
            System.out.println("Tree is empty.");
        }
    }

    // Clear the tree by setting root to null
    public void clear() {
        root = null;
        header = null;
        System.out.println("Tree has been cleared.");
    }
   
    public void setRoot(TreeMelodyNode root){
        this.root = root;
    }
    public void setManager(TreeMelodyManager treeMelodyManager){
        manager = treeMelodyManager;
    }
    // train method to create a melody tree with the specified index in MelodyManager
    void train(int index, int motiveNoteCount) {
        manager.convertToMotivesAndReplace(motiveNoteCount);
        ArrayList<TreeMelodyNode> motives  = new ArrayList<>();
        if (index == -1) {
            index = new Random().nextInt(manager.melodySize());
        }
        int count=0;
        for( MelodyPlayer melodyPlayer:manager.players){
               melodyPlayer.getMelody();
              TreeMelodyNode  treeNode = new TreeMelodyNode(manager, count,melodyPlayer.getMelody());
              motives.add(treeNode);
              count++;
        }
      
        if (root == null) {
            root = motives.get(index);
        }
    
        motives.remove(index);
    
        if (root != null) {
            root.addNextNodes(motives);
        }
    
        System.out.println("Training tree with melody at index: " + index);
    }

    // train method with defaults
    void train() {
        train(0, 4);  // call the train method with default index 0 & motiveNoteCount to 4
    }

    //Start playing the melody
    public void start() {
        System.out.println("Starting melody playback.");
        isPlaying = true;
        isLooping = false;
        play();
        
    }

    // Loop the melody
    void loop() {
        System.out.println("Looping melody.");
        isPlaying = true;
        isLooping = true;
        play();
    }

    // Stop the melody
    public void stop() {
        System.out.println("Stopping melody playback.");
        isPlaying = false;
        isLooping = false;
    }

    // Play the melody
    public void play() {
        if (!isPlaying) {
            System.out.println("Playback is stopped.");
            return;
        }

        TreeMelodyNode currentNode = root;
        while (isPlaying && currentNode != null) {
            currentNode.start();

            while (!currentNode.atEnd() && isPlaying) {
        
            }

            currentNode = currentNode.getNext();  

            if (currentNode == null && isLooping) {
                currentNode = root;  
            }
        }

        if (!isLooping) {
            stop();  
        }

    }

    public TreeMelodyManager getTreeMelodyManager(){
        return manager;
    }
}
