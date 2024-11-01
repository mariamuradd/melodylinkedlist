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
    public void print(int spacesBefore) {
        System.out.print(" ".repeat(spacesBefore));
        System.out.print("-- ");

        // prints MIDI notes
        System.out.print("MIDI Notes: " + midiNotes);
        System.out.println();

        // adds indentation to printed files
        for (TreeMelodyNode node : nodes) {
            node.print(spacesBefore + 2);
        }
    }

    // overloaded print method, calls with 0 spaces for root node
    public void print() {
        print(0);
    }
}