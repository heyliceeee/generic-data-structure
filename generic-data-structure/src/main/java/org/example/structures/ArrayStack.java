package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.StackADT;

import java.util.Arrays;

public class ArrayStack<T> implements StackADT<T> {

    /**
     * constante para representar a capacidade default do array
     */
    protected final int DEFAULT_CAPACITY = 100;

    /**
     * int que representa o número de elementos e o seguinte posição disponivel no array
     */
    protected int top;

    /**
     * array de elementos genéricos que representam a stack
     */
    protected T[] stack;


    /**
     * cria uma stack vazia utilizando a capacidade default
     */
    public ArrayStack(){
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * cria uma stack vazia utilizando uma capacidade específica
     * @param initialCapacity representa a capacidade específica
     */
    public ArrayStack(int initialCapacity){
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }


    /**
     * adiciona um elemento específico ao top da stack, expandindo a capacidade da stack se necessário
     * @param element elemento genérico a ser colocado na stack
     */
    @Override
    public void push(T element) {
        if(size() == stack.length) //se a stack já atingiu a capacidade máx.
        {
            expandCapacity();
        }

        stack[top] = element; //o novo elemento fica no top
        top++; //top aponta para o elemento seguinte (vazio)
    }

    /**
     * expanda a capacidade da stack
     */
    private void expandCapacity() {
        T[] newStack = (T[]) new Object[stack.length * 2]; //cria uma nova stack com o dobro da capacidade da stack anterior

        //copiar os elementos da stack anterior para a nova stack
        for(int i=0; i < stack.length; i++)
        {
            newStack[i] = stack[i];
        }

        stack = newStack; //a stack agora é a stack expandida
    }

    /**
     * remove o elemento do top da stack e retorna a referência dele
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento removido do top da stack
     * @throws EmptyCollectionException se a remoção foi tentada numa stack vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {

        if(isEmpty()) //se stack estiver vazia
        {
            throw new EmptyCollectionException("Stack");
        }

        top--; //top aponta para o elemento anterior

        T result = stack[top]; //elemento top
        stack[top] = null; //elemento top removido

        return result;
    }

    /** retorna uma referência ao elemento do top da stack. o elemento não é removido da stack
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento do top da stack
     * @throws EmptyCollectionException se a observação foi tentada numa stack vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {

        if(isEmpty()) //se stack estiver vazia
        {
            throw new EmptyCollectionException("Stack");
        }

        return stack[top - 1]; //elemento top (oq n ta vazio)
    }

    /**
     * retorna true se a stack não conter elementos
     * @return boolean dependendo se a stack está vazia
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * retorna o número de elementos da stack
     * @return int número de elementos da stack
     */
    @Override
    public int size() {
        return top;
    }


    @Override
    public String toString() {
        return "ArrayStack{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", top=" + top +
                ", stack=" + Arrays.toString(stack) +
                '}';
    }
}

