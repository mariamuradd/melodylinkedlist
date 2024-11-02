/*
 * c3 11/4/2024
 * Maria Murad
 * Class: WeaveUnitTest
 * Description: This class is designed to verify the weave method in LinkedListMelody by inserting specific melody nodes at intervals within the list. It includes two test cases that check if nodes are correctly woven at specified counts and skip intervals, ensuring the method's functionality.
 * 
 */

package com.linked_list_music_template;

public class WeaveUnitTest {
    
    private MelodyManager manager;
    private LinkedListMelody melodyList;

    // initializing MelodyManager and LinkedListMelody
    public WeaveUnitTest(){
        manager = new MelodyManager();
        melodyList = new LinkedListMelody();
    }

    // testing weave method with first case
    public void testWeave1(){
       melodyList = new LinkedListMelody();
        for(int i = 0; i<12; i++){
            //melodyList.addAfterMelody(node3, new MelodyNode(manager, 3));
            melodyList.insertAtEnd(new MelodyNode(manager,3));
        }

        // now have to weave node with index 0 3 times every 4 nodes
        MelodyNode node = new MelodyNode(manager, 0);
        melodyList.weave(node, 3, 4);

        //printing melody list to check the result
        System.out.print("Test Weave 1 - Expected Output: Melody: 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0\n");
        System.out.print("Actual Output: Melody: ");
        melodyList.print();
    }

    // testing weave method with case where count skip 
    public void testWeave2(){
        melodyList = new LinkedListMelody();
        for(int i = 0; i < 12; i++){
            melodyList.insertAtEnd(new MelodyNode(manager,3));
        }

        // weaving node with index 0 5 times every 10 nodes
        MelodyNode node = new MelodyNode(manager, 0);
        melodyList.weave(node, 5, 10);

        //printing melody list to check the result
        System.out.print("Test Weave 2 - Expected Output: Melody: 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 3, 3\n");
        System.out.print("Actual Output: Melody: ");
        melodyList.print();
    }

    // testing
    public class TestRunner {
        public static void main(String[] args) {
            WeaveUnitTest test = new WeaveUnitTest();
            System.out.println("Running testWeave1:");
            test.testWeave1();
            System.out.println("\nRunning testWeave2:");
            test.testWeave2();
        }
    }
}
