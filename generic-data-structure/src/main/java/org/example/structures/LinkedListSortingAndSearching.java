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
}
