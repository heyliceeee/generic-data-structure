package org.example.structures;

public class LinkedListSortingAndSearching<T extends Comparable<T>> {

    static LinkedListNode<Car> sortedHead;

    /** procura um elemento especifico na lista utilizando a pesquisa linear
     * @param head elemento da cabeca
     * @param target elemento a ser procurado
     * @return true se o elemento procurado foi encontrado
     * @param <T>
     */
    public <T extends Comparable<? super T>> boolean linearSearch(LinkedListNode<T> head, T target){
        LinkedListNode<T> current = head;
        boolean found = false;

        while (current != null && ! found)
        {
            if(target.equals(current.getElement()))
            {
                found = true;
            }
            else
            {
                current = current.getNext();
            }
        }

        return found;
    }


    /** procura um elemento na lista utilizando a pesquisa binaria
     * @param head elemento do cabeca
     * @param target elemento a ser procurado
     * @return
     */
    public boolean binarySearch(LinkedListNode<T> head, T target) {
        LinkedListNode<T> start = head;
        LinkedListNode<T> end = null;

        while (end == null || end != start) {
            LinkedListNode<T> mid = getMid(start, end);
            if (mid == null) {
                break;
            }

            if (mid.getElement().compareTo(target) == 0) {
                return true;
            } else if (mid.getElement().compareTo(target) < 0) {
                start = mid.getNext();
            } else {
                end = mid;
            }
        }

        return false;
    }


    /**
     * obter o elemento do meio da lista
     * @param start elemento do inicio da lista
     * @param end elemento do fim da lista
     * @return node
     */
    private LinkedListNode<T> getMid(LinkedListNode<T> start, LinkedListNode<T> end) {
        if (start == null) {
            return null;
        }

        LinkedListNode<T> slow = start;
        LinkedListNode<T> fast = start.getNext();

        while (fast != end) {
            fast = fast != null ? fast.getNext() : null;
            if (fast != end) {
                slow = slow.getNext();
                fast = fast != null ? fast.getNext() : null;
            }
        }

        return slow;
    }

    public void selectionSort(LinkedListNode<T> head) {
        LinkedListNode<T> current = head;

        while (current != null) {
            LinkedListNode<T> min = findMin(current);
            swap(current, min);
            current = current.getNext();
        }
    }

    private LinkedListNode<T> findMin(LinkedListNode<T> start) {
        LinkedListNode<T> min = start;
        LinkedListNode<T> current = start.getNext();

        while (current != null) {
            if (current.getElement().compareTo(min.getElement()) < 0) {
                min = current;
            }
            current = current.getNext();
        }

        return min;
    }

    private void swap(LinkedListNode<T> a, LinkedListNode<T> b) {
        T temp = a.getElement();
        a.setElement(b.getElement());
        b.setElement(temp);
    }




    /** INSERTION SORT
     */
    public static LinkedListNode<Car> insertionSort(LinkedListNode<Car> head){

        sortedHead = null;
        LinkedListNode<Car> currentNode = head;

        while (currentNode != null){

            LinkedListNode<Car> nextNode = currentNode.getNext();
            insertNode(currentNode);
            currentNode = nextNode;
        }

        return sortedHead;
    }

    static void insertNode(LinkedListNode<Car> head){

        if(sortedHead == null || sortedHead.getElement().getYear() >= head.getElement().getYear()){

            head.setNext(sortedHead);
            sortedHead = head;

            return;
        }

        LinkedListNode<Car> currentNode = sortedHead;

        while (currentNode.getNext() != null && currentNode.getNext().getElement().getYear() < head.getElement().getYear()) {

            currentNode = currentNode.getNext();
        }

        head.setNext(currentNode.getNext());
        currentNode.setNext(head);

        return;
    }


    /** BUBBLE SORT
     */
    public static void bubbleSort(LinkedListNode<Car> head){

        if (head != null) {
            LinkedListNode<Car> current=null, new_head=null, move_node=null;

            while (head != null) {

                current = head;
                move_node = head;

                while (current != null) {
                    //When current node value is grator than previous node
                    String currentNext = String.valueOf(current.getNext());
                    int currentNextElem = current.getNext().getElement().getYear();
                    int move_nodeElem = move_node.getElement().getYear();

                    if (currentNext != null && currentNextElem > move_nodeElem) {
                        //Swap node values
                        move_node = current.getNext();
                    }
                    current = current.getNext();
                }

                int move_nodeElem = move_node.getElement().getYear();
                int headElem = head.getElement().getYear();

                if (move_nodeElem == headElem) {
                    head = (head).getNext();
                }

                move_node.setNext(new_head);
                new_head = move_node;
            }
            //make new head
            head = new_head;
        } else {
            System.out.println("Empty Linked list");
        }
    }


    /** QUICK SORT
     * @param start
     * @param end
     * @return
     */
    private static LinkedListNode<Car> partitionLast(LinkedListNode<Car> start, LinkedListNode<Car> end){

        if(start == end || start == null || end == null){

            return start;
        }

        LinkedListNode<Car> pivot_prev = start;
        LinkedListNode<Car> curr = start;
        Car pivot = end.getElement();

        // iterate till one before the end,
        // no need to iterate till the end
        // because end is pivot
        while (start != end) {

            String yearStart = Integer.toString(start.getElement().getYear());
            String pivotStart = Integer.toString(pivot.getYear());

            if (yearStart.compareTo(pivotStart) < 0) {
                // keep tracks of last modified item
                pivot_prev = curr;
                Car temp = curr.getElement();
                curr.setElement(start.getElement());
                start.setElement(temp);
                curr = curr.getNext();
            }

            start = start.getNext();
        }

        // swap the position of curr i.e.
        // next suitable index and pivot
        Car temp = curr.getElement();
        curr.setElement(pivot);
        end.setElement(temp);

        // return one previous to current
        // because current is now pointing to pivot
        return pivot_prev;
    }

    public static <T extends Comparable<? super T>> void quickSort(LinkedListNode<Car> start, LinkedListNode<Car> end){

        if(start == end){

            return;
        }

        // split list and partion recurse
        LinkedListNode<Car> prev = partitionLast(start, end);
        quickSort(start, prev);

        // if pivot is picked and moved to the start,
        // that means start and pivot is same
        // so pick from next of pivot
        if(prev != null && prev == start){

            quickSort(prev.getNext(), end);

            // if pivot is in between of the list,
            // start from next of pivot,
            // since we have pivot_prev, so we move two nodes
        } else if(prev != null && prev.getElement() != null) {

            quickSort(prev.getNext().getNext(), end);
        }
    }


    /** MERGE SORT
     *
     */
    public static <T extends Comparable<? super T>> LinkedListNode<Car> mergeSort(LinkedListNode<Car> head){

        // Base case : if head is null
        if (head == null || head.getNext() == null) {
            return head;
        }

        // get the middle of the list
        LinkedListNode<Car> middle = getMiddle(head);
        LinkedListNode<Car> nextofmiddle = middle.getNext();

        // set the next of middle node to null
        middle.setNext(null);

        // Apply mergeSort on left list
        LinkedListNode<Car> left = mergeSort(head);

        // Apply mergeSort on right list
        LinkedListNode<Car> right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        LinkedListNode<Car> sortedlist = sortedMerge(left, right);

        return sortedlist;
    }

    public static LinkedListNode<Car> sortedMerge(LinkedListNode<Car> a, LinkedListNode<Car> b){

        LinkedListNode<Car> result = null;

        /* Base cases */
        if (a == null){

            return b;
        }

        if (b == null){

            return a;
        }

        /* Pick either a or b, and recur */
        int yearA = a.getElement().getYear();
        int yearB = b.getElement().getYear();

        if (yearA <= yearB) {

            result = a;
            result.setNext(sortedMerge(a.getNext(), b));

        } else {

            result = b;
            result.setNext(sortedMerge(a, b.getNext()));
        }

        return result;
    }

    public static LinkedListNode<Car> getMiddle(LinkedListNode<Car> head){

        // Base case
        if (head == null){

            return head;
        }

        LinkedListNode<Car> fastptr = head.getNext();
        LinkedListNode<Car> slowptr = head;

        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        while (fastptr != null) {

            fastptr = fastptr.getNext();

            if (fastptr != null) {

                slowptr = slowptr.getNext();
                fastptr = fastptr.getNext();
            }
        }

        return slowptr;
    }

}
