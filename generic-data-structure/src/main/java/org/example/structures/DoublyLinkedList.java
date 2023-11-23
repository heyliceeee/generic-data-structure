package org.example.structures;

public class DoublyLinkedList<T>
{
    protected DoublyNode<T> head, tail;
    protected int size;


    public DoublyLinkedList() {
        this.size = 0;
        this.head = this.tail = null;
    }

    /**
     * adiciona na cabeça
     *
     * @param element
     */
    public void addHead(T element) {
        DoublyNode<T> newNode = new DoublyNode<T>(element); //elemento

        if (head == null) { //caso a lista esteja vazia, o novo node fica tanto no head como no tail
            head = newNode;
            tail = newNode;

            size++;

        } else {
            newNode.setNext(head); //o próximo do novo node aponta para o head do node atual
            head.setPrev(newNode); //o anterior do node da head aponta para o novo node
            head = newNode; //o node atual da head é o novo node

            size++;
        }
    }

    /**
     * remover node da head
     *
     * @return
     */
    public boolean removeHead() {

        if (head == null || size == 0) { //lista vazia
            return false;
        }

        DoublyNode<T> removedNode = head; //elemento que está na head e q vai ser removido

        if (head == tail) { //se o node head é o único da lista
            head = tail = null;
            size--;
            return true;

        } else {
            head = head.getNext(); //a head fica com o valor do proximo node
            head.setPrev(null); //o elemento anterior ao head não existe
            size--;
            return true;
        }
    }

    /**
     * remover node da tail
     *
     * @return
     */
    public boolean removeTail() {

        if (tail == null || size == 0) { //lista vazia
            return false;
        }

        DoublyNode<T> removedNode = tail; //elemento que está na tail e q vai ser removido

        if (head == tail) { //se o node tail é o único da lista
            head = tail = null;
            size--;
            return true;

        } else {
            tail = tail.getPrev(); //a tail fica com o valor do proximo node
            tail.setNext(null); //o elemento seguinte ao tail não existe
            size--;
            return true;
        }
    }

    /**
     * verificar se a lista está vazia
     *
     * @return
     */
    public boolean isEmpty() {
        if (size > 0) {

            return false;
        }

        return true;
    }

    /**
     * mostrar
     */
    public void print() {
        DoublyNode<T> current = head; //elemento da cabeca (primeiro elemento)

        System.out.print("Doubly LinkedList [");

        while (current != null) { //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }

    public void printArrayString(Object[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {  // Se não for o último elemento, imprima uma vírgula
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public Object[] printArray() {
        int i = 0;
        DoublyNode<T> current = head; //elemento da cabeca (primeiro elemento)

        while (current != null) {
            i++;
            current = current.getNext(); //vai para o seguinte elemento
        }

        // Crie um array do tamanho apropriado
        Object[] result = new Object[i];

        // Preencha o array com os dados dos nós
        current = head;
        int index = 0;

        while (current != null) {
            result[index] = current.getElement();
            index++;
            current = current.getNext();
        }

        return result;
    }


    public Object[] printArrayUntilPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("a posicao deve ser nao negativa");
        }

        DoublyNode<T> current = head;

        while (current != null) {
            current = current.getNext();
        }

        int effectiveSize = Math.min(position, size); //se a position for > doq o tamanho da list, limite-a ao tamanho

        Object[] result = new Object[effectiveSize]; //criar um array do tamanho apropriado

        //preencha o array c os dados dos nodes até a position
        current = head;
        int i = 0;

        while (current != null && i < effectiveSize) {
            result[i] = current.getElement();
            i++;
            current = current.getNext();
        }

        return result;
    }


    public Object[] printArrayAfterPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("a posicao deve ser nao negativa");
        }

        DoublyNode<T> current = head;
        int y = 0;

        while (current != null && y < position) {
            y++;
            current = current.getNext();
        }

        //se a position for >= doq o tamanho da list, retorne um array vazio
        if (current == null) {
            return new Object[0];
        }

        int effectiveSize = 0;
        DoublyNode<T> temp = current;

        while (temp != null) {
            effectiveSize++;
            temp = temp.getNext();
        }

        Object[] result = new Object[effectiveSize]; //criar um array do tamanho apropriado

        //preencha o array c os dados dos nodes até a position
        int i = 0;

        while (current != null) {
            result[i] = current.getElement();
            i++;
            current = current.getNext();
        }

        return result;
    }


    public Object[] printArrayBetweenPositions(int position1, int position2) {
        if (position1 < 0 || position2 < 0 || position1 > position2) {
            throw new IllegalArgumentException("a posicao deve ser nao negativa");
        }

        DoublyNode<T> current = head;

        //correr até a position1
        int j = 0;
        while (current != null && j < position1) {
            j++;
            current = current.getNext();
        }

        //se a position1 for >= ao tamanho da lista, retorne um array vazio
        if (current == null) {
            return new Object[0];
        }

        int length = 0;
        DoublyNode<T> temp = current;

        while (temp != null && j <= position2) {
            length++;
            j++;
            temp = temp.getNext();
        }

        // Crie um array do tamanho calculado
        Object[] result = new Object[length];

        // Preencha o array com os dados dos nós entre as posições especificadas
        int i = 0;
        while (current != null && i < length) {
            result[i] = current.getElement();
            i++;
            current = current.getNext();
        }

        return result;
    }


    public DoublyLinkedList<Integer> getEvenElements() {
        if (!(head.getElement() instanceof Integer)) {
            throw new UnsupportedOperationException("Esta operação só é válida para listas de inteiros.");
        }

        DoublyLinkedList<Integer> evenList = new DoublyLinkedList<>();

        DoublyNode<T> current = head;

        while (current != null) {
            Integer value = (Integer) current.getElement();
            if (value % 2 == 0) {
                evenList.addHead(value);
            }
            current = current.getNext();
        }
        return evenList;

    }


    public int getManyElementsEquals(T element) {
        DoublyNode<T> current = head; //elemento da cabeca (primeiro elemento)
        int quantity = 0;

        for (int i = 0; current != null; i++) {
            if (current.getElement().equals(element)) { //verifica se o elemento atual é = ao element
                quantity++;
            }

            current = current.getNext(); //vai para o seguinte elemento
        }

        return quantity;
    }


    public boolean removeDuplicateElements(T element) {
        DoublyNode<T> current = head; //elemento atual
        boolean foundFirst = false; //para controlar se já encontramos o primeiro elemento
        boolean elementRemoved = false;

        while (current != null) { //vai percorrendo os elementos
            if (current.getElement().equals(element)) { //elemento atual = elemento q vai ser removido
                if(foundFirst){ //se já encontrou o primeiro elemento, remove
                    elementRemoved = true;

                    if (current.getPrev() != null) { //existe o elemento anterior ao atual
                        current.getPrev().setNext(current.getNext()); //elemento seguinte ao anterior do atual = elemento seguinte do atual
                    }

                    if (current.getNext() != null) { //existe o elemento seguinte ao atual
                        current.getNext().setPrev(current.getPrev()); //elemento anterior ao seguinte do atual = elemento anterior do atual
                    }

                    //depois de remover um elemento...
                    DoublyNode<T> temp = current.getNext(); //temp = elemento seguinte do atual
                    current.setNext(null); //elemento seguinte do atual = nulo
                    current.setPrev(null); //elemento anterior do atual = nulo
                    current = temp; //atual = elemento seguinte do atual

                } else { //se NÃO já encontrou o primeiro elemento (primeira vez agora)
                    foundFirst = true;
                    current = current.getNext(); //atual = elemento seguinte do atual
                }

            } else { //elemento atual != elemento q vai ser removido
                current = current.getNext(); //atual = elemento seguinte do atual
            }
        }

        return elementRemoved;
    }
}

