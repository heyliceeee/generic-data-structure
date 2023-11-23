package org.example.structures;

public class LinkedList<T>
{
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    private SentinelNode<T> sentinel;
    private int size;

    public LinkedList(){
        this.size = 0;
        this.head = null;
        sentinel = new SentinelNode<T>(null);
    }


    /**
     * adicionar a lista utilizando node sentinela
     * @param element
     */
    public void addSentinel(T element){
        SentinelNode<T> newNode = new SentinelNode<T>(element); //criar um novo node com os dados
        SentinelNode<T> current = sentinel; //elemento

        while (current.getNext() != null){ //vai correr a lista até á cauda (último elemento)
            current = current.getNext(); //vai para o seguinte elemento
        }

        current.setNext(newNode); //adicionar um novo node á cauda (último elemento)
        size++;
    }

    public void add(T element){
        LinkedListNode<T> newNode = new LinkedListNode<T>(element); //criar um novo node com os dados

        if(head == null){ //se a lista estiver vazia
            head = newNode; //o novo node fica na cabeca (torna-se o primeiro)
            tail = newNode; //o novo node fica na cauda (torna-se o último)

            size++;

        } else { //se a lista NAO estiver vazia
            tail.setNext(newNode); //adicionar um novo node ao próximo elemento de tail
            tail = newNode; //tail fica com o valor do novo node

            size++;
        }
    }

    /**
     * remover node sentinela
     * @param element
     * @return
     */
    public boolean removeSentinel(T element) {
        //remover um elemento do meio ou da cauda da lista
        SentinelNode<T> current = sentinel;  //elemento

        while (current.getNext() != null && !current.getNext().getElement().equals(element)){ //vai correr a lista até ao último elemento, se for necessário, até encontrar o elemento
            current = current.getNext(); //vai para o seguinte elemento
        }

        //se encontrou o elemento
        if(current.getNext() != null) {
            current.setNext(current.getNext().getNext()); //o elemento seguinte será o seguinte do seguinte elemento
            return true;
        }

        return false; //elemento não encontrado, ou seja, não removido
    }

    public T remove(T element) {

        if(head == null || size == 0){ //lista vazia
            return null;
        }

        if(head.getElement().equals(element)){ //remover o primeiro elemento

            T removedElement = head.getElement();

            head = head.getNext(); //cabeca fica com o valor do segundo elemento (agora é o primeiro elemento porque o primeiro elemento foi removido)

            size--;

            return removedElement; //elemento removido
        }

        //remover um elemento do meio ou da cauda da lista
        LinkedListNode<T> current = head;  //elemento da cabeca (primeiro elemento)
        while (current.getNext() != null && !current.getNext().getElement().equals(element)){  //vai correr a lista até á cauda (último elemento) se for necessário até encontrar o elemento
            current = current.getNext(); //vai para o seguinte elemento
        }

        //se encontrou o elemento
        if(current.getNext() != null) {

            T removedElement = current.getNext().getElement();

            current.setNext(current.getNext().getNext()); //o elemento seguinte será o seguinte do seguinte elemento

            size--;

            return removedElement;
        }

        return null; //elemento não encontrado, ou seja, não removido
    }

    /**
     * mostrar
     */
    public void printSentinel() {
        SentinelNode<T> current = sentinel; //elemento da cabeca (primeiro elemento)

        System.out.print("LinkedList Sentinel [");

        while (current != null){ //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }

    /**
     * mostrar
     */
    public void print() {
        LinkedListNode<T> current = head; //elemento da cabeca (primeiro elemento)

        System.out.print("LinkedList [");

        while (current != null){ //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }
}

