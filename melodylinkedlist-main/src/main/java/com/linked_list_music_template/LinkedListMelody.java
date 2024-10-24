/*
 * Maria Murad using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: [you fille in]
 * 
 */

package com.linked_list_music_template;

public class LinkedListMelody implements Drawable {
   
    MelodyNode header;
    MelodyNode curMelodyNode;

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
                System.out.println(", "); // this will add a comma if there is another node
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

    // Play the entire melody in the linked list
    public void play() {
        if (isEmpty()) {
            System.out.println("No melody to play.");
            return;
        }
        
        //MelodyNode current = header;
        while (curMelodyNode != null ) {
           if( curMelodyNode.atEnd() )
           {
                System.out.println("here");
                curMelodyNode.start(); // MelodyNode has a start() method
                curMelodyNode = curMelodyNode.getNext();
            }
        }
    }

    // Loop the melody
    public void loop(boolean loop_) {
        if (!loop_) {
            return; // if loop is false, exit
        }

        // Play the melody continuously until stopped
        while (loop_) {
            play();
        }
    }

    // Stop the playback of melodies
    public void stop() {
        curMelodyNode = null; // Reset the current node to stop playback
        System.out.println("Playback stopped.");
    }

    // Weave a MelodyNode count times every skip nodes
    public void weave(MelodyNode node, int count, int skip) {
        if (isEmpty()) {
            System.out.println("Cannot weave, the melody list is empty.");
            return;
        }

        MelodyNode current = header;
        int index = 0; // Start with the first node in the list

        while (current != null && count > 0) {
            // Skip the specified number of nodes
            for (int i = 0; i < skip && current != null; i++) {
                current = current.getNext();
                index++;
            }

            if (current != null) {
                // Insert the node after every 'skip' nodes
                insert(index, new MelodyNode(node.melodyManager, node.getMelodyIndex()));
                count--;
            }
        }
    }

    // Clear the melody list
    public void clear() {
        header = null; // Just set the header to null to remove all nodes
        System.out.println("Melody list cleared.");
    }

    // Reverse the melody list
    public void reverse() {
        MelodyNode prev = null;
        MelodyNode current = header;
        MelodyNode next = null;

        while (current != null) {
            next = current.getNext(); // Store next node
            current.setNext(prev); // Reverse the link
            prev = current; // Move prev one step forward
            current = next; // Move to the next node
        }
        header = prev; // Update header to the new front
        System.out.println("Melody list reversed.");
    }

    // Add a melody after every instance of another melody
    public void addAfterMelody(MelodyNode targetNode, MelodyNode newNode) {
        if (isEmpty()) {
            System.out.println("Cannot add; the melody list is empty.");
            return;
        }

        MelodyNode current = header;
        while (current != null) {
            if (current.getMelodyIndex() == targetNode.getMelodyIndex()) {
                MelodyNode temp = current.getNext(); // Store the next node
                current.setNext(newNode); // Insert the new node
                newNode.setNext(temp); // Link the new node to the next
                newNode = new MelodyNode(newNode.melodyManager, newNode.getMelodyIndex()); // Create a new instance for the next insertion
            }
            current = current.getNext();
        }
    }

    // Fill in this method to play melody in draw
    public void draw() {
        // Implement drawing logic for melody, possibly calling play()
        if (header != null) {
            play(); // Example: Just play the melody in the draw method
        }
    }

    public void start() {
        if (header != null) {
            curMelodyNode = header;
            header.start();
        }
    }

    public static class WeaveUnitTest {

        // Declare required objects
        MelodyManager manager;  // object reference to a MelodyManager class
        LinkedListMelody melodyList;  // LinkedListMelody object
    
        // Constructor to initialize the MelodyManager and LinkedListMelody
        public WeaveUnitTest() {
            manager = new MelodyManager();  // Assuming MelodyManager class exists
            melodyList = new LinkedListMelody();  // Initialize the linked list
        }
    
        // Test the first weave case
        public void testWeave1() {
            // Step 1: Add melody nodes with value 3 (12 nodes total)
            for (int i = 0; i < 12; i++) {
                MelodyNode node = new MelodyNode(manager, 3);  // Create a node with melody index 3
                melodyList.insertAtEnd(node);  // Add node to the end of the linked list
            }
    
            // Step 2: Create a node to weave, using melody index 0
            MelodyNode nodeToWeave = new MelodyNode(manager, 0);
    
            // Step 3: Weave the node 3 times every 4 nodes
            melodyList.weave(nodeToWeave, 3, 4);
    
            // Step 4: Print the melody list
            System.out.println("Test Weave 1 Result:");
            melodyList.print();  // Expected: 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0
        }
    
        // Test the second weave case
        public void testWeave2() {
            // Step 1: Add melody nodes with value 3 (12 nodes total)
            for (int i = 0; i < 12; i++) {
                MelodyNode node = new MelodyNode(manager, 3);  // Create a node with melody index 3
                melodyList.insertAtEnd(node);  // Add node to the end of the linked list
            }
    
            // Step 2: Create a node to weave, using melody index 0
            MelodyNode nodeToWeave = new MelodyNode(manager, 0);
    
            // Step 3: Weave the node 5 times every 10 nodes
            melodyList.weave(nodeToWeave, 5, 10);
    
            // Step 4: Print the melody list
            System.out.println("Test Weave 2 Result:");
            melodyList.print();  // Expected: 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 3, 3
        }
    }
}
    /* 
        public static void main(String[] args) {
            // Create an instance of WeaveUnitTest
            WeaveUnitTest test = new WeaveUnitTest();
    
            // Run the first test
            System.out.println("Running Test Weave 1:");
            test.testWeave1();
    
            // Run the second test
            System.out.println("Running Test Weave 2:");
            test.testWeave2();
        }

    }
        

}
*/