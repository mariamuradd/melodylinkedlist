/*
 * c2024  [your name] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: [you fille in]
 * 
 */


package com.linked_list_music_template;

public class LinkedListMelody implements Drawable {
   
    MelodyNode header;
    MelodyNode curMelodyNode;


    // this will insert a node at the start of the List. if node is empty, new node becomes the header but if it's not, then node is inserted before current header.
    public void insertAtStart(MelodyNode node){
        if(isEmpty()){
            header = node;
        } else {
            node.setNext(header);
            header = node; // updates header to new node
        }
    }

    // this will insert a node at the end of the list
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

    // this will insert a node at a specified index point
    public void insert(int index, MelodyNode node){
        if(index == 0){
            insertAtStart(node); // call insertAtStart
        } else {
            MelodyNode current = header;
            for (int i = 0; i < index - 1 && current != null; i++){
                current = current.getNext();
            }
            if (current != null){
                node.setNext(current.getNext()); // link new node to the next
                current.setNext(node); // link current node to new one
            }
        }
    }

    // this will insert a node after a specified food type
    // public void insert(String food, MelodyNode node){
    //     int index = find(food);
    //     if(index != -1){
    //         insert(index + 1, node); // insert new node after found food item
    //     }
    // }

    // // this will help find the index of a food in the list
    // public int find(String food) {
    //     MelodyNode current = header;
    //     int index = 0;
    //     while (current != null) {
    //         if (current.getNext().equals(food)) {
    //             return index;
    //         }
    //         current = current.getNext();
    //         index++;
    //     }er
    //     return -1; // food not found!
    // }

    // this will remove a specified food type from the list
    // public void remove(String food) {
    //     if (isEmpty()) {
    //         return;
    //     }

    //     if (header.getFoodName().equals(food)) {
    //         header = header.getNextFood();
    //         return;
    //     }

    //     FoodNode current = header; // start from head
    //     while (current.getNextFood() != null) {
    //         if (current.getNextFood().getFoodName().equals(food)) {
    //             current.setNextFood(current.getNextFood().getNextFood());
    //             return;
    //         }
    //         current = current.getNextFood(); // move next node
    //     }
    // }

    // this will check if list is empty or not
    public boolean isEmpty(){
        return header == null; // list is empty if header is null
    }
    //fill in this class
    public void draw()
    {
        //fill this in to play melody
    }

    public void start(){
        if(header!=null){
            curMelodyNode = header;
            header.start();
        }
    }
}
