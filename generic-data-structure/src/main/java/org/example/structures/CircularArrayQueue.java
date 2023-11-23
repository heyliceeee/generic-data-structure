package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.QueueADT;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class CircularArrayQueue<T> implements QueueADT<T>
{
    /**
     * constante para representar a capacidade default do array
     */
    private final int DEFAULT_CAPACITY = 100;

    /**
     * array de elementos genéricos que representam a queue
     */
    private T[] queue;

    /**
     * array de elementos genéricos que representam uma nova queue
     */
    private T[] newQueue;

    /**
     * int que representa o elemento rear da queue
     */
    private int rear;

    /**
     * int que representa o elemento front da queue
     */
    private int front;

    /**
     * int que representa o tamanho da queue
     */
    private int size;


    /**
     * cria um array vazia e nula
     */
    public CircularArrayQueue()
    {
        this.size = this.front = this.rear = 0;
        this.queue = (T[]) (new Object[this.DEFAULT_CAPACITY]);
    }

    /**
     * cria um array
     */
    public CircularArrayQueue(int initialCapacity)
    {
        this.size = this.front = this.rear = 0;
        this.queue = (T[]) (new Object[initialCapacity]);
    }


    @Override
    public void enqueue(T element)
    {
        if(isFull()) //se a queue tiver cheia
        {
            expandCapacity(); //expande
        } else
        {
            queue[rear] = element; //rear = novo elemento
            rear = (rear + 1) % queue.length; //rear volta á posicao inicial

            size++;
        }
    }

    @Override
    public T dequeue()
    {
        T result;

        if(isEmpty()) //se queue estiver vazia
        {
            throw new EmptyCollectionException("Queue");
        } else
        {
            result = queue[front]; //elemento front
            queue[front] = null; //elemento front passa a ser nulo (foi removido)

            front = (front + 1) % queue.length; //front volta á posicao inicial

            size--;
        }

        return result;
    }

    @Override
    public T first()
    {
        if(isEmpty()) //se queue estiver vazia
        {
            throw new EmptyCollectionException("Queue");
        }

        return queue[front];
    }

    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }

    /**
     * retorna true consoante se a queue tiver cheia
     * @return true consoante se a queue tiver cheia
     */
    public boolean isFull()
    {
        return (size == queue.length);
    }

    @Override
    public int size()
    {
        return size;
    }


    /**
     * expande o array
     */
    private void expandCapacity()
    {
        newQueue = (T[]) (new Object[DEFAULT_CAPACITY]);

        T[] copyQueue = Stream.concat(Arrays.stream(queue), Arrays.stream(newQueue)).toArray(size -> (T[]) Array.newInstance(queue.getClass().getComponentType(), size));

        queue = copyQueue;
    }


    @Override
    public String toString() {
        return "CircularArrayQueue{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", queue=" + Arrays.toString(queue) +
                ", newQueue=" + Arrays.toString(newQueue) +
                ", rear=" + rear +
                ", front=" + front +
                ", size=" + size +
                '}';
    }
}

