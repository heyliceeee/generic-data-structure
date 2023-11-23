package org.example.structures;

public class SentinelNode<T>
{
    private T element;
    private SentinelNode<T> next;


    SentinelNode(T element)
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

    public SentinelNode<T> getNext()
    {
        return next;
    }

    public void setNext(SentinelNode<T> next)
    {
        this.next = next;
    }


    @Override
    public String toString()
    {
        return "{element = " + element + '}';
    }
}