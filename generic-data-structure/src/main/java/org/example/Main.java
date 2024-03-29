package org.example;

import org.example.interfaces.QueueADT;
import org.example.structures.*;

import java.util.Iterator;

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


        //LINKED QUEUE, CIRCULAR ARRAY QUEUE, ORDER QUEUE, QUEUE W/ 2 STACKS
        //ex1 - linked queue
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        System.out.println(queue);

        System.out.println("Elemento removido: " + queue.dequeue());

        System.out.println(queue);


        //ex2 - circular array queue
        CircularArrayQueue<String> circularArray = new CircularArrayQueue<>();

        circularArray.enqueue("a");
        circularArray.enqueue("b");
        circularArray.enqueue("c");
        circularArray.enqueue("d");
        circularArray.enqueue("e");

        System.out.println(circularArray);

        System.out.println("Elemento removido: " + circularArray.dequeue());

        System.out.println(circularArray);


        //ex3 - expandCapacity() e isFull()


        //ex4
        CircularArrayQueue<Character> circularArray1 = new CircularArrayQueue<>();

        String key = "317425317 42 53174";
        String message = "knowledge is power";
        String[] keyNumbers = key.split(" ");

        for(String number: keyNumbers)
        {
            for(char c : number.toCharArray())
            {
                circularArray1.enqueue(c);
            }
        }

        StringBuilder msgCodificada = new StringBuilder();

        for(char c : message.toCharArray())
        {
            if(Character.isLetter(c))
            {
                char keyNumber = circularArray1.dequeue();
                int shift = Character.getNumericValue(keyNumber) - 1;
                char codificado = (char) (c + shift);

                msgCodificada.append(codificado);
                circularArray1.enqueue(keyNumber);
            }
            else
            {
                msgCodificada.append(c);
            }
        }

        System.out.println("msg codificada: " + msgCodificada);

        //parte II
        //ex1
        //  circular array - FIFO, circularidade
        //  array stack - LIFO


        //ex2
        // BIG O dequeue:
        //  - array queue: O(1)
        //  - array circular queue: O(1)
        //  - linked list queue: O(1)


        //ex3 - order queue
        OrderedQueue<Character> orderedQueue = new OrderedQueue<>();

        orderedQueue.enqueue('R');
        orderedQueue.enqueue('F');
        orderedQueue.enqueue('M');
        orderedQueue.enqueue('T');
        orderedQueue.enqueue('A');
        orderedQueue.enqueue('Z');
        orderedQueue.enqueue('H');
        orderedQueue.enqueue('V');
        orderedQueue.enqueue('L');
        orderedQueue.enqueue('X');
        orderedQueue.enqueue('G');
        orderedQueue.enqueue('N');
        orderedQueue.enqueue('D');
        orderedQueue.enqueue('Q');
        orderedQueue.enqueue('K');
        orderedQueue.enqueue('O');
        orderedQueue.enqueue('E');
        orderedQueue.enqueue('C');
        orderedQueue.enqueue('I');
        orderedQueue.enqueue('B');
        orderedQueue.enqueue('W');
        orderedQueue.enqueue('Y');

        System.out.println(orderedQueue);


        OrderedQueue<Character> queue1 = new OrderedQueue<>();

        queue1.enqueue('J');
        queue1.enqueue('P');
        queue1.enqueue('S');
        queue1.enqueue('U');

        System.out.println(queue1);


        OrderedQueue<Character> mergeQueues = new OrderedQueue<>();
        QueueADT<Character> merge = mergeQueues.merge(orderedQueue, queue1);

        // Imprima a queue resultante
        System.out.println("queue merge ordenada:");

        while (!merge.isEmpty()) //enquanto a queue não estiver vazia
        {
            System.out.print(merge.dequeue() + " ");
        }


        //ex4 - queue with 2 stacks
        QueueWithTwoStacks<String> queueWithTwoStacks = new QueueWithTwoStacks<>();

        queueWithTwoStacks.enqueue("1");
        queueWithTwoStacks.enqueue("2");
        queueWithTwoStacks.enqueue("3");

        System.out.println("Front of the queue: " + queueWithTwoStacks.first());

        System.out.println("Dequeue: " + queueWithTwoStacks.dequeue());
        System.out.println("Dequeue: " + queueWithTwoStacks.dequeue());

        queueWithTwoStacks.enqueue("4");

        System.out.println("Front of the queue: " + queueWithTwoStacks.first());
        System.out.println("Dequeue: " + queueWithTwoStacks.dequeue());
        System.out.println("Dequeue: " + queueWithTwoStacks.dequeue());



        //ARRAY ORDERED LIST, DOUBLE LINKED ORDERED LIST, ARRAY UNORDERED LIST, DOUBLE LINKED UNORDERED LIST
        //ex1
        ArrayOrderedList<String> arrayOrderedList = new ArrayOrderedList<>();

        arrayOrderedList.add("O");
        arrayOrderedList.add("F");
        arrayOrderedList.add("M");
        arrayOrderedList.add("N");
        arrayOrderedList.add("A");
        arrayOrderedList.add("E");

        System.out.println(arrayOrderedList);


        //ex2
        DoubleLinkedOrderedList<String> doubleLinkedOrderedList = new DoubleLinkedOrderedList<>();

        doubleLinkedOrderedList.add("O");
        doubleLinkedOrderedList.add("F");
        doubleLinkedOrderedList.add("M");
        doubleLinkedOrderedList.add("N");
        doubleLinkedOrderedList.add("A");
        doubleLinkedOrderedList.add("E");

        System.out.println(doubleLinkedOrderedList);


        //ex4
        ArrayUnorderedList<String> arrayUnorderedList = new ArrayUnorderedList<>();

        arrayUnorderedList.addToFront("O");
        arrayUnorderedList.addToRear("F");
        arrayUnorderedList.addAfter("M", "O");
        arrayUnorderedList.addToFront("N");
        arrayUnorderedList.addToRear("A");
        arrayUnorderedList.addAfter("E", "F");

        System.out.println(arrayUnorderedList);


        //ex5
        DoubleLinkedUnorderedList<String> doubleLinkedUnorderedList = new DoubleLinkedUnorderedList<>();

        doubleLinkedUnorderedList.addToFront("O");
        doubleLinkedUnorderedList.addToRear("F");
        doubleLinkedUnorderedList.addAfter("M", "O");
        doubleLinkedUnorderedList.addToFront("N");
        doubleLinkedUnorderedList.addToRear("A");
        doubleLinkedUnorderedList.addAfter("E", "F");

        System.out.println(doubleLinkedUnorderedList);



        //ARRAY LINEAR SEARCH, ARRAY BINARY SEARCH
        //ex1
        ArraySortingAndSearching<Car> arraySortingAndSearching = new ArraySortingAndSearching<>();

        Car[] stand = {
                new Car("Toyota", "Corolla", 2022),
                new Car("Honda", "Civic", 2021),
                new Car("Ford", "Focus", 2020),
                new Car("BMW", "X5", 2023),
                new Car("Tesla", "Model 3", 2023)
        };

        boolean foundCarlinearSearch = arraySortingAndSearching.linearSearch(stand, 0, 4, new Car("", "X5", 0));

        if (foundCarlinearSearch) {
            System.out.println("Carro encontrado!");
        } else {
            System.out.println("Carro não encontrado.");
        }

        boolean foundCarbinarySearch = arraySortingAndSearching.binarySearch(stand, 0, 4, new Car("", "X5", 0));

        if (foundCarbinarySearch) {
            System.out.println("Carro encontrado!");
        } else {
            System.out.println("Carro não encontrado.");
        }



        //LINKED LIST LINEAR SEARCH, LINKED LIST BINARY SEARCH
        //ex2
        LinkedListSortingAndSearching<Car> linkedlistSortingAndSearching = new LinkedListSortingAndSearching<>();

        LinkedListNode<Car> head = new LinkedListNode<>(new Car("Toyota", "Corolla", 2022));
        LinkedListNode<Car> node2 = new LinkedListNode<>(new Car("Honda", "Civic", 2021));
        LinkedListNode<Car> node3 = new LinkedListNode<>(new Car("Ford", "Focus", 2020));
        LinkedListNode<Car> node4 = new LinkedListNode<>(new Car("BMW", "X5", 2023));
        LinkedListNode<Car> node5 = new LinkedListNode<>(new Car("Tesla", "Model 3", 2023));

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        boolean linkedListLinearSearch = linkedlistSortingAndSearching.linearSearch(head, new Car("Ford", "Focus", 2020));

        if (linkedListLinearSearch) {
            System.out.println("Carro encontrado!");
        } else {
            System.out.println("Carro não encontrado.");
        }


        boolean linkedListBinarySearch = linkedlistSortingAndSearching.binarySearch(head, new Car("Ford", "Focus", 2020));

        if (linkedListBinarySearch) {
            System.out.println("Carro encontrado!");
        } else {
            System.out.println("Carro não encontrado.");
        }



        //ARRAY SELECTION SORT, ARRAY INSERTION SORT, ARRAY BUBBLE SORT, ARRAY QUICK SORT, ARRAY MERGE SORT
        //ex3
        System.out.println("ARRAY SELECTION SORT");
        System.out.println("Carros antes da ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }

        //ArraySortingAndSearching.selectionSort(stand);

        System.out.println("\nCarros após a ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }


        System.out.println("ARRAY INSERTION SORT");
        System.out.println("Carros antes da ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }

        //ArraySortingAndSearching.insertionSort(stand);

        System.out.println("\nCarros após a ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }


        System.out.println("ARRAY BUBBLE SORT");
        System.out.println("Carros antes da ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }

        //ArraySortingAndSearching.bubbleSort(stand);

        System.out.println("\nCarros após a ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }



        System.out.println("ARRAY QUICK SORT");
        System.out.println("Carros antes da ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }

        //ArraySortingAndSearching.quickSort(stand, 0, stand.length - 1);

        System.out.println("\nCarros após a ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }



        System.out.println("ARRAY MERGE SORT");
        System.out.println("Carros antes da ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }

        //ArraySortingAndSearching.mergeSort(stand);

        System.out.println("\nCarros após a ordenação:");
        for (Car carro : stand) {
            System.out.println(carro);
        }



        //LINKED LIST SELECTION SORT, LINKED LIST INSERTION SORT, LINKED LIST BUBBLE SORT, LINKED LIST QUICK SORT, LINKED LIST MERGE SORT
        //ex3
        System.out.println("Lista antes da ordenação:");
        printLinkedList(head);

        linkedlistSortingAndSearching.selectionSort(head);

        System.out.println("\nLista após o Selection Sort:");
        printLinkedList(head);


        //LinkedListSortingAndSearching.insertionSort(head);

        System.out.println("\nLista após o INSERTION Sort:");
        printLinkedList(head);



        //LinkedListSortingAndSearching.bubbleSort(head);

        System.out.println("\nLista após o BUBBLE Sort:");
        printLinkedList(head);



        //LinkedListSortingAndSearching.quickSort(head, node5);

        System.out.println("\nLista após o QUICK Sort:");
        printLinkedList(head);


        //LINKED BINARY SEARCH TREE, ARRAY BINARY SEARCH TREE
        LinkedBinarySearchTree<Integer> linkedBinarySearchTree = new LinkedBinarySearchTree<>();

        linkedBinarySearchTree.addElement(10);
        linkedBinarySearchTree.addElement(5);
        linkedBinarySearchTree.addElement(3);
        linkedBinarySearchTree.addElement(7);
        linkedBinarySearchTree.addElement(15);
        linkedBinarySearchTree.addElement(13);

        System.out.println("\n\n"+linkedBinarySearchTree);

        linkedBinarySearchTree.removeElement(3);
        System.out.println("\n"+linkedBinarySearchTree);

        linkedBinarySearchTree.removeMin();
        System.out.println("\n"+linkedBinarySearchTree);

        linkedBinarySearchTree.removeAllOcurrences(15);
        System.out.println("\n"+linkedBinarySearchTree);


        System.out.println("\n"+linkedBinarySearchTree.findMax());



        ArrayBinarySearchTree<Integer> arrayBinarySearchTree = new ArrayBinarySearchTree<>();

        arrayBinarySearchTree.addElement(10);
        arrayBinarySearchTree.addElement(5);
        arrayBinarySearchTree.addElement(3);
        arrayBinarySearchTree.addElement(7);
        arrayBinarySearchTree.addElement(15);
        arrayBinarySearchTree.addElement(13);

        System.out.println("\n\n"+arrayBinarySearchTree);

        arrayBinarySearchTree.removeElement(3);
        System.out.println("\n"+arrayBinarySearchTree);

        arrayBinarySearchTree.removeMin();
        System.out.println("\n"+arrayBinarySearchTree);

        arrayBinarySearchTree.removeAllOcurrences(15);
        System.out.println("\n"+arrayBinarySearchTree);

        System.out.println("\n"+arrayBinarySearchTree.findMax());


        //ARRAY HEAP
        ArrayHeap<Integer> arrayHeap = new ArrayHeap<>();

        arrayHeap.addElement(3);
        arrayHeap.addElement(4);
        arrayHeap.addElement(5);
        arrayHeap.addElement(6);
        arrayHeap.addElement(7);
        arrayHeap.addElement(8);
        arrayHeap.addElement(9);

        System.out.println("\n\n"+arrayHeap);

        System.out.println("\n"+arrayHeap.removeMin());



        //PRIORITY QUEUE
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.addElement(3, 7);
        priorityQueue.addElement(4, 5);
        priorityQueue.addElement(5, 2);
        priorityQueue.addElement(6, 1);
        priorityQueue.addElement(7, 3);
        priorityQueue.addElement(8, 6);
        priorityQueue.addElement(9, 4);

        System.out.println("\n\n"+priorityQueue);

        System.out.println("\n"+priorityQueue.removeNext());


        //LINKED HEAP
        LinkedHeap<Integer> linkedHeap = new LinkedHeap<>();

        linkedHeap.addElement(3);
        linkedHeap.addElement(4);
        linkedHeap.addElement(5);
        linkedHeap.addElement(6);
        linkedHeap.addElement(7);
        linkedHeap.addElement(8);
        linkedHeap.addElement(9);

        System.out.println("\n\n"+linkedHeap);

        System.out.println("\n"+linkedHeap.removeMin());


        //UNWEIGHTED GRAPH USING AN ADJACENT MATRIX
        Graph<Integer> graph = new Graph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        System.out.println("Travessia em Largura (BFS):");
        Iterator<Integer> bfsIterator = graph.iteratorBFS(0);

        while (bfsIterator.hasNext())
        {
            System.out.print(bfsIterator.next() + " ");
        }

        System.out.println("\n\nTravessia em Profundidade (DFS):");
        Iterator<Integer> dfsIterator = graph.iteratorDFS(0);
        
        while (dfsIterator.hasNext())
        {
            System.out.print(dfsIterator.next() + " ");
        }
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

    public static void printLinkedList(LinkedListNode<Car> head) {
        LinkedListNode<Car> current = head;
        while (current != null) {
            System.out.print(current.getElement() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}