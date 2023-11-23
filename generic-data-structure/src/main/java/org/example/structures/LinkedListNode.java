package org.example.structures;

public class LinkedListNode<T>
{
    private T element;
    private LinkedListNode<T> next;


    LinkedListNode()
    {
        element = null;
        next = null;
    }

    public LinkedListNode(T element)
    {
        this.element = element;
        this.next = null;
    }


    public T getElement()
    {
        return element;
    }

    public void setElement(T element)
    {
        this.element = element;
    }

    public LinkedListNode<T> getNext()
    {
        return next;
    }

    public void setNext(LinkedListNode<T> next)
    {
        this.next = next;
    }


    @Override
    public String toString()
    {
        return "{element = " + element + '}';
    }
}
