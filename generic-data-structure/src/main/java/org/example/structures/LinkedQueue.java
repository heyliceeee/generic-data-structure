package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.QueueADT;

public class LinkedQueue<T> implements QueueADT<T>
{
    /**
     * node que representa o elemento front da queue
     */
    private LinkedListNode<T> front;

    /**
     * node que representa o elemento rear da queue
     */
    private LinkedListNode<T> rear;

    /**
     * int que representa o tamanho da queue
     */
    private int size;


    /**
     * cria uma queue vazia e nula
     */
    public LinkedQueue()
    {
        this.front = this.rear = null;
        this.size = 0;
    }

    /**
     * cria uma queue
     */
    public LinkedQueue(LinkedListNode<T> front, LinkedListNode<T> rear, int size)
    {
        this.front = front;
        this.rear = rear;
        this.size = size;
    }


    @Override
    public void enqueue(T element)
    {
        LinkedListNode<T> newNode = new LinkedListNode<T>(element); //novo elemento a ser adicionado

        if(isEmpty()) //se a queue tiver vazia
        {
            front = newNode; //front = novo elemento
            rear = newNode; //rear = novo elemento
        } else
        {
            rear.setNext(newNode); //o elemento seguinte do rear = novo elemento
            rear = newNode; //rear = novo elemento
        }

        size++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException
    {
        if(isEmpty()) //se queue estiver vazia
        {
            throw new EmptyCollectionException("Queue");
        }

        T result = front.getElement(); //elemento front
        front = front.getNext(); //front = elemento seguinte ao removido

        size--;

        if(isEmpty()) //se queue estiver vazia
        {
            rear = null; //entretanto a queue pode ficar vazia depois da remocao
        }

        return result;
    }

    @Override
    public T first() throws EmptyCollectionException
    {
        if(isEmpty()) //se queue estiver vazia
        {
            throw new EmptyCollectionException("Queue");
        }

        return front.getElement();
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


    @Override
    public String toString() {
        return "LinkedQueue{" +
                "front=" + front +
                ", rear=" + rear +
                ", size=" + size +
                '}';
    }
}
