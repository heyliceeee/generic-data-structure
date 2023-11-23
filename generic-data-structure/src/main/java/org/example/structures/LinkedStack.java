package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.StackADT;

public class LinkedStack<T> implements StackADT<T> {

    /**
     * node que representa o elemento top da stack
     */
    private LinkedListNode<T> top;

    /**
     * int que representa o tamanho da stack
     */
    private int size;


    /**
     * cria uma stack vazia e nula
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * cria uma stack
     * @param top representa o elemento top da stack
     * @param size representa o tamanho da stack
     */
    public LinkedStack(LinkedListNode<T> top, int size) {
        this.top = top;
        this.size = size;
    }


    /**
     * adiciona um elemento específico ao top da stack
     * @param element elemento a ser colocado na stack
     */
    @Override
    public void push(T element)
    {
        LinkedListNode<T> newNode = new LinkedListNode<T>(element); //novo elemento a ser adicionado

        newNode.setNext(top); //o elemento seguinte ao novo elemento = top
        top = newNode; //top = novo elemento

        size++;
    }


    /**
     * remove o elemento do top da stack e retorna o elemento
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento removido do top da stack
     * @throws EmptyCollectionException se a remoção foi tentada numa stack vazia
     */
    @Override
    public T pop() throws EmptyCollectionException
    {
        if(isEmpty()) //se stack estiver vazia
        {
            throw new EmptyCollectionException("Stack");
        }

        T result = top.getElement(); //elemento top
        top = top.getNext(); //top = elemento seguinte ao removido

        size--;

        return result;
    }

    /** retorna o elemento do top da stack. o elemento não é removido da stack
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento top da stack
     * @throws EmptyCollectionException se a observação foi tentada numa stack vazia
     */
    @Override
    public T peek() throws EmptyCollectionException
    {
        if(isEmpty()) //se stack estiver vazia
        {
            throw new EmptyCollectionException("Stack");
        }

        return top.getElement(); //elemento top
    }

    /**
     * retorna true se a stack não conter elementos
     * @return boolean dependendo se a stack está vazia
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * retorna o número de elementos da stack
     * @return int número de elementos da stack
     */
    @Override
    public int size() {
        return size;
    }


    @Override
    public String toString() {
        return "org.example.LinkedStack{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}