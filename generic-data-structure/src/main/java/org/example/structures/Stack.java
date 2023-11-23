package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.StackADT;

public class Stack<T> implements StackADT<T>
{

    private T[] stackArray;

    private int size;

    private static final int DEFAULT_CAPACITY = 100;


    public Stack()
    {
        this.stackArray = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public Stack(int initialCapacity)
    {
        this.stackArray = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public void push(T element)
    {
        if (size == stackArray.length)
        {
            expandCapacity();
        }

        stackArray[size] = element;

        size++;
    }

    @Override
    public T pop() {
        if (isEmpty())
        {
            throw new EmptyCollectionException("Stack");
        }

        size--;

        T removedElement = stackArray[size];
        stackArray[size] = null; // Remove the reference to the popped element

        return removedElement;
    }

    @Override
    public T peek()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("Stack");
        }

        return stackArray[size - 1];
    }

    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }

    @Override
    public int size()
    {
        return size;
    }

    private void expandCapacity()
    {
        T[] newStackArray = (T[]) new Object[stackArray.length * 2];
        System.arraycopy(stackArray, 0, newStackArray, 0, stackArray.length);
        stackArray = newStackArray;
    }
}

