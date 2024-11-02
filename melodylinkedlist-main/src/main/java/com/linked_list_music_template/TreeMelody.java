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

    // Constructor
    public TreeMelody(TreeMelodyManager manager) {
        super();
        this.manager = manager;
        this.root = (TreeMelodyNode) header;  // Cast head to TreeMelodyNode if necessary
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

    // train method to create a melody tree with the specified index in MelodyManager
    void train(int index, int motiveNoteCount) {
        ArrayList<TreeMelodyNode> motives = manager.convertToMotivesAndReplace(motiveNoteCount);
    
        if (index == -1) {
            index = new Random().nextInt(manager.melodySize());
        }
    
        for (int i = 0; i < manager.melodySize(); i++) {
            ArrayList<Integer> melodyPicthes = manager.getMelodyPitches(i);
            TreeMelodyNode node = new TreeMelodyNode(melodyPicthes);
            motives.add(node);
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

    // Start playing the melody
    public void start() {
        System.out.println("Starting melody playback.");
        // Add playback logic if required
    }

    // Deprecated weave function
    void weave() {
        System.out.println("Warning: weave() function is deprecated and not used.");
    }

    // Loop the melody
    void loop() {
        System.out.println("Looping melody.");
        // Add looping logic if required
    }

    // Stop the melody
    public void stop() {
        System.out.println("Stopping melody playback.");
        // Add stopping logic if required
    }

    // Play the melody
    public void play() {
        System.out.println("Playing melody.");
        // Add play logic if required
    }
}
