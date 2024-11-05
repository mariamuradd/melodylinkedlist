/*
 * c3 11/4/2024
 * Maria Murad
 * Class: TreeMelodyNode
 * Description: This class represents a melody node with MIDI pitches and child nodes. There is also functionality to add matching nodes as children, retrieving a random child, and printing the tree.
 * 
 */

package com.linked_list_music_template;

import java.util.ArrayList;
import java.util.Random;

public class TreeMelodyNode extends MelodyNode {

    // child nodes
    ArrayList<TreeMelodyNode> nodes;
    
    // MIDI pitches list for node
    ArrayList<Integer> midiNotes;

    // Constructor
    TreeMelodyNode(MelodyManager melodyManager, int whichMelody) {
        super(melodyManager, whichMelody); // Explicit call to MelodyNode's constructor
        nodes = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    
    // this method allows to add next nodes to the tree based on matching melody pitches
    public void addNextNodes(ArrayList<TreeMelodyNode> motives) {
        ArrayList<TreeMelodyNode> nodesToAdd = new ArrayList<>();
        
        for (TreeMelodyNode curNode : motives) {
            if (!midiNotes.isEmpty() && !curNode.midiNotes.isEmpty() && 
                midiNotes.get(midiNotes.size() - 1).equals(curNode.midiNotes.get(0))) {

                curNode.midiNotes.remove(0);
                nodesToAdd.add(curNode);
            }
        }

        // removes added nodes from motives
        motives.removeAll(nodesToAdd);
        nodes.addAll(nodesToAdd);

        // add nodes to each child node
        for (TreeMelodyNode node : nodes) {
            node.addNextNodes(motives);
        }
    }

    // overriding here becasue I want getNext to return a random child node
    @Override
    public MelodyNode getNext() {
        if (nodes.isEmpty()) return null;
        return nodes.get(new Random().nextInt(nodes.size()));
    }

    // print method for tree formatting
public void print(int spacesBefore, int index) {
    // Print current node index and MIDI notes
    System.out.print(" ".repeat(spacesBefore));  
    System.out.print(index + ": ");              
    System.out.print(midiNotes);                  
    System.out.println();                        

    // Adds indentation for child nodes
    for (int i = 0; i < nodes.size(); i++) {
        System.out.print(" ".repeat(spacesBefore + 2));  
        System.out.print("-- ");                         
        nodes.get(i).print(spacesBefore + 2, i);       
    }
}

    // overloaded print method, calls with 0 spaces for root node
    public void print() {
        print(0,0);
    }
}