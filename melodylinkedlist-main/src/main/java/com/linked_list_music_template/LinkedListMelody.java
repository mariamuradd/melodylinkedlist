/*
 * c3 10/28/2024
 * Maria Murad using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: This class is a linked list structure for managing and playing sequences of melody nodes from MelodyNode. It also supports common operations like insertion, deletion, playback with looping, reversing, and adding nodes after specific melodies.
 */

package com.linked_list_music_template;

public class LinkedListMelody implements Drawable {
   
    MelodyNode header;
    MelodyNode curMelodyNode;
    private boolean isLooping = false;

    // Print all melody nodes in the list
    public void print() {
        if (isEmpty()) {
            System.out.println("Melody list is empty.");
            return;
        }

        MelodyNode current = header;
        System.out.print("Melody: ");

        //prints all nodes but the last one without comma at the end
        while (current != null) {
            System.out.print(current.getMelodyIndex());
            current = current.getNext();
            if(current != null){
                System.out.print(", "); // this will add a comma if there is another node
            }
        }
        System.out.println();
    }

    // Insert a MelodyNode at a specified index
    public void insert(int index, MelodyNode node) {
        if (index == 0) {
            insertAtStart(node); // use existing method
        } else {
            MelodyNode current = header;
            for (int i = 0; i < index - 1 && current != null; i++) {
                current = current.getNext();
            }
            if (current != null) {
                node.setNext(current.getNext()); // link new node to the next
                current.setNext(node); // link current node to new one
            }
        }
    }

    // Insert a MelodyNode at the start of the list
    public void insertAtStart(MelodyNode node) {
        if (isEmpty()) {
            header = node;
        } else {
            node.setNext(header);
            header = node; // updates header to new node
        }
    }

    // Insert a MelodyNode at the end of the list
    public void insertAtEnd(MelodyNode node) {
        if (isEmpty()) {
            header = node;
        } else {
            MelodyNode current = header;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(node); // appends new node to the last node
        }
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return header == null;
    }

    // method is controlling looping
    public void loop(boolean loop_){
        isLooping = loop_;
    }

    // Play the entire melody in the linked list
    public void play() {
        if (isEmpty()) {
            System.out.println("No melody to play.");
            return;
        }
        curMelodyNode = header;
        System.out.println(curMelodyNode);
        while(curMelodyNode != null ) {
            // Check if we’re at the end of the list
            if (curMelodyNode.atEnd()) {
                if (isLooping) {
                    curMelodyNode = header; // Reset to beginning
                    curMelodyNode.start();  // Start from beginning
                } else {
                    curMelodyNode = null; // Stop playback
                }
            } else {
                // Move to the next node and start it
                curMelodyNode = curMelodyNode.getNext();
                if (curMelodyNode != null) {
                    curMelodyNode.start();
                }
            }
        }
        
    }

    // Stop the playback of melodies
    public void stop() {
        curMelodyNode = null; // resets current node to stop playback
        System.out.println("Playback stopped.");
    }

    // this will weave a MelodyNode count times every skip nodes
    public void weave(MelodyNode node, int count, int skip) {
        if (isEmpty()) {
            System.out.println("Cannot weave, the melody list is empty.");
            return;
        }

        MelodyNode current = header;
        int index = 0; // first node in list starting

        while (current != null && count > 0) {
            for (int i = 0; i < skip && current != null; i++) {
                current = current.getNext();
                index++;
            }

            if (current != null) {
                insert(index, new MelodyNode(node.melodyManager, node.getMelodyIndex()));
                count--;
            }
        }
    }

    // clearing melody list
    public void clear() {
        header = null; // set to null to remove all nodes
        System.out.println("Melody list cleared.");
    }

    // reversing melody list
    public void reverse() {
        MelodyNode prev = null;
        MelodyNode current = header;
        MelodyNode next = null;

        while (current != null) {
            next = current.getNext(); // next node stored
            current.setNext(prev); // link reversed
            prev = current; // prev moves one step forward
            current = next; // moved to next node
        }
        header = prev; // update header to new front
        System.out.println("Melody list reversed.");
    }

    // adding melody after every instance of another melody
    public void addAfterMelody(MelodyNode targetNode, MelodyNode newNode) {
        if (isEmpty()) {
            System.out.println("Cannot add; the melody list is empty.");
            return;
        }

        MelodyNode current = header;
        while (current != null) {
            if (current.getMelodyIndex() == targetNode.getMelodyIndex()) {
                MelodyNode temp = current.getNext();
                current.setNext(newNode); 
                newNode.setNext(temp); 
                newNode = new MelodyNode(newNode.melodyManager, newNode.getMelodyIndex()); // Create a new instance for the next insertion
            }
            current = current.getNext();
        }
    }

    // melody played in draw
    public void draw() {
        if (header != null) {
          // play();
        }
    }

    public void start() {
        if (header != null) {
            curMelodyNode = header;
            header.start();
        }
    }

}
