package org.example;

import org.example.structures.DoublyLinkedList;
import org.example.structures.LinkedList;

public class Main {
    public static void main(String[] args) {
        // LINKED LIST, SENTINEL LIST & DOUBLY LIST
        //ex1 - linked list (usa apenas o head e associa o "tail" como o null da lista)
        LinkedList<String> list = new LinkedList<>();

        list.print();

        list.add("a");
        list.add("c");
        list.add("b");

        list.print();

        list.remove("b");

        list.print();


        //ex2 - sentinel node (usa o head e o tail)
        LinkedList<String> listSentinel = new LinkedList<>();

        listSentinel.printSentinel();

        listSentinel.addSentinel("a");
        listSentinel.addSentinel("c");
        listSentinel.addSentinel("b");

        listSentinel.printSentinel();

        listSentinel.removeSentinel("b");

        listSentinel.printSentinel();


        //ex3 - a diferença é que na linkedlist a lista pode ficar vazia, já na node sentinela ele não deixa que a lista seja nula

        //ex4 - doubly linkedlist
        DoublyLinkedList<String> listDoubly = new DoublyLinkedList<>();

        Object[] arr = listDoubly.printArray();
        listDoubly.printArrayString(arr);

        System.out.println(listDoubly.isEmpty());

        listDoubly.addHead("a");
        listDoubly.addHead("c");
        listDoubly.addHead("b");

        Object[] arr1 = listDoubly.printArray();
        listDoubly.printArrayString(arr1);

        System.out.println(listDoubly.isEmpty());

        listDoubly.removeHead();

        Object[] arr2 = listDoubly.printArray();
        listDoubly.printArrayString(arr2);

        System.out.println(listDoubly.isEmpty());

        listDoubly.removeTail();

        Object[] arr3 = listDoubly.printArray();
        listDoubly.printArrayString(arr3);

        System.out.println(listDoubly.isEmpty());


        // parte II
        // ex2
        listDoubly.addHead("a");
        listDoubly.addHead("c");
        listDoubly.addHead("b");

        Object[] arr4 = listDoubly.printArray();
        listDoubly.printArrayString(arr4);

        Object[] arr5 = listDoubly.printArrayUntilPosition(2);
        listDoubly.printArrayString(arr5);

        Object[] arr6 = listDoubly.printArrayAfterPosition(2);
        listDoubly.printArrayString(arr6);

        Object[] arr7 = listDoubly.printArrayBetweenPositions(0, 1);
        listDoubly.printArrayString(arr7);


        //ex3
        DoublyLinkedList<Integer> listDoublyInt = new DoublyLinkedList<>();

        listDoublyInt.addHead(1);
        listDoublyInt.addHead(3);
        listDoublyInt.addHead(2);

        listDoublyInt.getEvenElements().print();

        listDoubly.addHead("a");
        listDoubly.addHead("c");
        listDoubly.addHead("b");
        listDoubly.addHead("a");
        listDoubly.addHead("c");
        listDoubly.addHead("b");
        listDoubly.addHead("a");
        listDoubly.addHead("c");
        listDoubly.addHead("b");
        listDoubly.addHead("a");
        listDoubly.addHead("c");
        listDoubly.addHead("b");

        //ex4
        System.out.println(listDoubly.getManyElementsEquals("a"));
        listDoubly.removeDuplicateElements("a");

        listDoubly.print();
    }
}