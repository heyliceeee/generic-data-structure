package org.example;

import org.example.structures.ArrayStack;
import org.example.structures.DoublyLinkedList;
import org.example.structures.LinkedList;
import org.example.structures.LinkedStack;

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


        //ARRAY STACK, LINKED STACK
        //ex1
        ArrayStack<String> arrayStack = new ArrayStack<>();

        arrayStack.push("A");
        arrayStack.push("B");
        arrayStack.push("C");
        arrayStack.push("D");
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack);
        System.out.println(arrayStack.size()+ "\n");

        arrayStack.push("E");
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack);
        System.out.println(arrayStack.size()+ "\n");

        arrayStack.pop();
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack);
        System.out.println(arrayStack.size()+ "\n");



        //ex2, ex3 e ex4
        LinkedStack<String> linkedStack = new LinkedStack<>();

        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.push("D");
        linkedStack.push("E");
        System.out.println(linkedStack.peek());
        System.out.println(linkedStack);
        System.out.println(linkedStack.size()+ "\n");

        linkedStack.pop();
        System.out.println(linkedStack.peek());
        System.out.println(linkedStack);
        System.out.println(linkedStack.size()+ "\n");

        //postfix - arraystack
        System.out.println(evaluate_arraystack("7 4 -3 * 1 5 + / *"));

        //postfix - linkedstack
        System.out.println(evaluate_linkedstack("7 4 -3 * 1 5 + / *"));
    }


    private static double evaluate_arraystack(String s)
    {
        ArrayStack<Double> arrayStack = new ArrayStack<>();
        String[] tokens = s.split("\\s+");

        for(String token : tokens)
        {
            if("+-*/".contains(token))
            {
                double op2 = arrayStack.pop();
                double op1 = arrayStack.pop();

                switch (token)
                {
                    case "+":
                        arrayStack.push(op1 + op2);
                        break;

                    case "-":
                        arrayStack.push(op1 - op2);
                        break;

                    case "*":
                        arrayStack.push(op1 * op2);
                        break;

                    case "/":
                        arrayStack.push(op1 / op2);
                        break;
                }
            }
            else
            {
                arrayStack.push(Double.parseDouble(token));
            }
        }

        return arrayStack.pop();
    }


    private static double evaluate_linkedstack(String s)
    {
        LinkedStack<Double> linkedStack = new LinkedStack<>();
        String[] tokens = s.split("\\s+");

        for(String token : tokens)
        {
            if("+-*/".contains(token))
            {
                double op2 = linkedStack.pop();
                double op1 = linkedStack.pop();

                switch (token)
                {
                    case "+":
                        linkedStack.push(op1 + op2);
                        break;

                    case "-":
                        linkedStack.push(op1 - op2);
                        break;

                    case "*":
                        linkedStack.push(op1 * op2);
                        break;

                    case "/":
                        linkedStack.push(op1 / op2);
                        break;
                }
            }
            else
            {
                linkedStack.push(Double.parseDouble(token));
            }
        }

        return linkedStack.pop();
    }
}