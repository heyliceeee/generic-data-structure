package org.example.structures;

public class DoublyNode<T>
{
    private T element;
    private DoublyNode<T> prev, next;


    DoublyNode(){
        this.element = null;
        this.prev = null;
        this.next = null;
    }

    DoublyNode(T element)
    {
        this.element = element;
        this.prev = null;
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

    public DoublyNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoublyNode<T> prev)
    {
        this.prev = prev;
    }

    public DoublyNode<T> getNext()
    {
        return next;
    }

    public void setNext(DoublyNode<T> next)
    {
        this.next = next;
    }


    @Override
    public String toString()
    {
        return "{element = " + element + '}';
    }
}
